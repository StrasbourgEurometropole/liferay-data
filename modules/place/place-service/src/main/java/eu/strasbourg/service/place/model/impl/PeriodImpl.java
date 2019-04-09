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
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.DateFormatFactoryUtil;
import com.liferay.portal.kernel.util.Validator;

import aQute.bnd.annotation.ProviderType;
import eu.strasbourg.service.place.model.PlaceSchedule;
import eu.strasbourg.service.place.model.Slot;
import eu.strasbourg.service.place.service.SlotLocalServiceUtil;
import eu.strasbourg.utils.DateHelper;
import eu.strasbourg.utils.JSONHelper;

/**
 * The extended model implementation for the Period service. Represents a row in
 * the &quot;place_Period&quot; database table, with each column mapped to a
 * property of this class.
 *
 * <p>
 * Helper methods and all application logic should be put in this class.
 * Whenever methods are added, rerun ServiceBuilder to copy their definitions
 * into the {@link eu.strasbourg.service.place.model.Period} interface.
 * </p>
 *
 * @author Angelique Zunino Champougny
 */
@ProviderType
public class PeriodImpl extends PeriodBaseImpl {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1091689203853712173L;

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. All methods that expect a period
	 * model instance should use the {@link
	 * eu.strasbourg.service.place.model.Period} interface instead.
	 */
	public PeriodImpl() {
	}

	/**
	 * Retourne les Slots de la période pour un lieu
	 */
	@Override
	public List<Slot> getAllSlots() {
		List<Slot> slots = SlotLocalServiceUtil.getByPeriodId(this.getPeriodId());
		return slots;
	}

	/**
	 * Retourne les Slots de la période pour un lieu
	 */
	@Override
	public List<Slot> getSlots() {
		List<Slot> slots = SlotLocalServiceUtil.getByPeriodId(this.getPeriodId());
		return slots.stream().filter(s -> s.getSubPlaceId() == 0).collect(Collectors.toList());
	}

	/**
	 * Retourne les Slots de la période pour un sous-lieu
	 */
	@Override
	public List<Slot> getSlots(long subPlaceId) {
		List<Slot> slots = SlotLocalServiceUtil.getByPeriodId(this.getPeriodId());
		return slots.stream().filter(s -> s.getSubPlaceId() == subPlaceId).collect(Collectors.toList());
	}

	/**
	 * Retourne la liste des horaires par jour (0 = lundi, 1 = mardi, etc.)
	 */
	@Override
	public List<PlaceSchedule> getWeekSchedule() {
		List<PlaceSchedule> weekSchedule = new ArrayList<PlaceSchedule>();
		List<Slot> slots = this.getSlots();
		for (int i = 0; i < 7; i++) {
			int day = i;
			List<Slot> slotsOfDay = slots.stream()
					.filter(s -> s.getDayOfWeek() == day).collect(Collectors.toList());
			weekSchedule.add(PlaceSchedule.fromSlots(slotsOfDay, this.getAlwaysOpen()));
		}
		return weekSchedule;
	}

	/**
	 * Retourne la liste des horaires par jour pour le sous lieu (0 = lundi, 1 = mardi, etc.)
	 */
	@Override
	public List<PlaceSchedule> getWeekSchedule(long subPlaceId) {
		List<PlaceSchedule> weekSchedule = new ArrayList<PlaceSchedule>();
		List<Slot> slots = this.getSlots(subPlaceId);
		for (int i = 0; i < 7; i++) {
			int day = i;
			List<Slot> slotsOfDay = slots.stream()
					.filter(s -> s.getDayOfWeek() == day).collect(Collectors.toList());
			weekSchedule.add(PlaceSchedule.fromSlots(slotsOfDay, this.getAlwaysOpen()));
		}
		return weekSchedule;
	}
	
	@Override
	public String getDisplay(Locale locale) {
		return DateHelper.displayPeriod(this.getStartDate(), this.getEndDate(), locale, true, false);
	}

	/**
	 * Retourne la version JSON de la période
	 */
	@Override
	public JSONObject toJSON() {
		JSONObject periodJSON = JSONFactoryUtil.createJSONObject();

		periodJSON.put("periodName", JSONHelper.getJSONFromI18nMap(this.getNameMap()));
		// TODO périod par défaut ?
		if (!this.getDefaultPeriod()) {
			DateFormat dateFormat = DateFormatFactoryUtil.getSimpleDateFormat("yyyy-MM-dd");
			if (Validator.isNotNull(this.getStartDate())) {
				periodJSON.put("startDate", dateFormat.format(this.getStartDate()));
			}
			if (Validator.isNotNull(this.getEndDate())) {
				periodJSON.put("endDate", dateFormat.format(this.getEndDate()));
			}
		}
		if (Validator.isNotNull(this.getLinkLabelMap())) {
			periodJSON.put("scheduleLinkName", JSONHelper.getJSONFromI18nMap(this.getLinkLabelMap()));
		}
		if (Validator.isNotNull(this.getLinkURLMap())) {
			periodJSON.put("scheduleLinkURL", JSONHelper.getJSONFromI18nMap(this.getLinkURLMap()));
		}
		periodJSON.put("alwaysOpen", this.getAlwaysOpen() ? 1 : 0);

		if (!this.getAlwaysOpen()) {
			JSONArray slotsJSON = JSONFactoryUtil.createJSONArray();
			for (Slot slot : this.getSlots()) {
				slotsJSON.put(slot.toJSON());
			}
			if (slotsJSON.length() > 0) {
				periodJSON.put("schedules", slotsJSON);
			}
		}

		return periodJSON;
	}
}