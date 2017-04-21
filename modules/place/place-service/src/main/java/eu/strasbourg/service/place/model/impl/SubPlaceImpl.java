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
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import aQute.bnd.annotation.ProviderType;
import eu.strasbourg.service.place.model.Period;
import eu.strasbourg.service.place.model.Place;
import eu.strasbourg.service.place.model.PlaceSchedule;
import eu.strasbourg.service.place.model.PublicHoliday;
import eu.strasbourg.service.place.model.ScheduleException;
import eu.strasbourg.service.place.model.Slot;
import eu.strasbourg.service.place.service.PeriodLocalServiceUtil;
import eu.strasbourg.service.place.service.PlaceLocalServiceUtil;
import eu.strasbourg.service.place.service.PublicHolidayLocalServiceUtil;
import eu.strasbourg.service.place.service.ScheduleExceptionLocalServiceUtil;

/**
 * The extended model implementation for the SubPlace service. Represents a row
 * in the &quot;place_SubPlace&quot; database table, with each column mapped to
 * a property of this class.
 *
 * <p>
 * Helper methods and all application logic should be put in this class.
 * Whenever methods are added, rerun ServiceBuilder to copy their definitions
 * into the {@link eu.strasbourg.service.place.model.SubPlace} interface.
 * </p>
 *
 * @author Angelique Zunino Champougny
 */
@ProviderType
public class SubPlaceImpl extends SubPlaceBaseImpl {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4091307024003680779L;

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. All methods that expect a sub place
	 * model instance should use the {@link
	 * eu.strasbourg.service.place.model.SubPlace} interface instead.
	 */
	public SubPlaceImpl() {
	}

	/**
	 * Retourne les ScheduleExceptions du sous-lieu
	 */
	@Override
	public List<ScheduleException> getScheduleExceptions() {
		return ScheduleExceptionLocalServiceUtil
				.getBySubPlaceId(this.getSubPlaceId());
	}

	/**
	 * Retourne les Periods du sous-lieu
	 */
	@Override
	public List<Period> getPeriods() {
		return PeriodLocalServiceUtil.getBySubPlaceId(this.getSubPlaceId());
	}

	/**
	 * Retourne le lieu parent du sous-lieu
	 */
	@Override
	public Place getPlaceByPlaceId(long placeId) {
		return PlaceLocalServiceUtil.fetchPlace(this.getPlaceId());
	}

	/**
	 * Retourne les PublicHolidays
	 */
	@Override
	public List<PublicHoliday> getPublicHolidays() {
		return PublicHolidayLocalServiceUtil.getPublicHolidaies(-1, -1);
	}

	/**
	 * Retourne une map contennant le jour et une liste de PlaceSchedule de la
	 * semaine en cours
	 */
	@Override
	public Map<String, List<PlaceSchedule>> getHoraire(Date dateJour,
			Locale locale) {
		Map<String, List<PlaceSchedule>> listHoraires = new LinkedHashMap<String, List<PlaceSchedule>>();

		// réupère le jour voulu de la semaine
		GregorianCalendar jourSemaine = new GregorianCalendar();
		jourSemaine.setTime(dateJour);
		jourSemaine.set(Calendar.HOUR_OF_DAY, 0);
		jourSemaine.clear(Calendar.MINUTE);
		jourSemaine.clear(Calendar.SECOND);
		jourSemaine.clear(Calendar.MILLISECOND);
		for (int jour = 0; jour <= 6; jour++) {
			jourSemaine.set(Calendar.DAY_OF_WEEK,
					(int) (jour == 6 ? 1 : jour + 2));

			List<PlaceSchedule> liste = getPlaceSchedule(jourSemaine, locale);

			DateFormat df = DateFormat.getDateInstance(DateFormat.FULL, locale);
			listHoraires.put(df.format(jourSemaine.getTime()), liste);
		}
		return listHoraires;
	}

	/**
	 * Retourne les horaires d'ouverture de la semaine en cours
	 */
	@Override
	public List<PlaceSchedule> getPlaceSchedule(GregorianCalendar jourSemaine,
			Locale locale) {
		List<PlaceSchedule> listHoraires = new ArrayList<PlaceSchedule>();

		// vérifie si cette date n'est pas dans les horaires d'exception
		for (ScheduleException scheduleException : this
				.getScheduleExceptions()) {
			if (scheduleException.getStartDate() != null
					&& scheduleException.getEndDate() != null
					&& scheduleException.getStartDate()
							.compareTo(jourSemaine.getTime()) <= 0
					&& scheduleException.getEndDate()
							.compareTo(jourSemaine.getTime()) >= 0) {
				PlaceSchedule placeSchedule = new PlaceSchedule(
						scheduleException.getExceptionId(),
						scheduleException.getStartDate(),
						scheduleException.getEndDate(),
						scheduleException.getComment(locale), locale);
				placeSchedule.setException(true);
				if (scheduleException.isClosed()) {
					placeSchedule.setClosed(true);
				} else {
					String[] heure = scheduleException.getStartHour()
							.split(":");
					LocalTime startHour = LocalTime.of(
							Integer.parseInt(heure[0]),
							Integer.parseInt(heure[1]));
					heure = scheduleException.getEndHour().split(":");
					LocalTime endHour = LocalTime.of(Integer.parseInt(heure[0]),
							Integer.parseInt(heure[1]));
					placeSchedule.setStartTime(startHour);
					placeSchedule.setEndTime(endHour);
				}
				listHoraires.add(placeSchedule);
				return listHoraires;
			}
		}

		// vérifie si cette date n'est pas dans les horaires d'exception
		// vérifie si le lieu attaché est assujeti au jour férié
		Place place = this.getPlaceByPlaceId(this.getPlaceId());
		if (place.isSubjectToPublicHoliday()) {
			for (PublicHoliday publicHoliday : this.getPublicHolidays()) {
				if (publicHoliday.getDate() != null && publicHoliday.getDate()
						.compareTo(jourSemaine.getTime()) == 0) {
					PlaceSchedule placeSchedule = new PlaceSchedule(
							publicHoliday.getPublicHolidayId(),
							publicHoliday.getDate(), publicHoliday.getDate(),
							publicHoliday.getName(locale), locale);
					placeSchedule.setPublicHoliday(true);
					placeSchedule.setClosed(true);
					listHoraires.add(placeSchedule);
					return listHoraires;
				}
			}
		}

		// s'il n'y a pas d'exception, on récupère les horaires de la
		// période concernée
		if (listHoraires.isEmpty()) {
			for (Period period : this.getPeriods()) {
				if (!period.getDefaultPeriod()) {
					if (period.getStartDate() != null
							&& period.getEndDate() != null
							&& period.getStartDate()
									.compareTo(jourSemaine.getTime()) <= 0
							&& period.getEndDate()
									.compareTo(jourSemaine.getTime()) >= 0) {
						listHoraires.clear();
						// on vérifie si le lieu n'est pas ouvert 24h/24
						// 7j/7
						if (period.getAlwaysOpen()) {
							PlaceSchedule placeSchedule = new PlaceSchedule(
									period.getPeriodId(), period.getStartDate(),
									period.getEndDate(), period.getName(locale),
									locale);
							placeSchedule.setAlwaysOpen(true);
							listHoraires.add(placeSchedule);
						} else {
							for (Slot slot : period.getSlots()) {
								if (slot.getDayOfWeek() == (jourSemaine
										.get(Calendar.DAY_OF_WEEK) == 1
												? 6
												: jourSemaine
														.get(Calendar.DAY_OF_WEEK)
														- 2)) {
									PlaceSchedule placeSchedule = new PlaceSchedule(
											period.getPeriodId(),
											period.getStartDate(),
											period.getEndDate(),
											period.getName(locale), locale);
									String[] heure = slot.getStartHour()
											.split(":");
									LocalTime startHour = LocalTime.of(
											Integer.parseInt(heure[0]),
											Integer.parseInt(heure[1]));
									heure = slot.getEndHour().split(":");
									LocalTime endHour = LocalTime.of(
											Integer.parseInt(heure[0]),
											Integer.parseInt(heure[1]));
									placeSchedule.setStartTime(startHour);
									placeSchedule.setEndTime(endHour);
									listHoraires.add(placeSchedule);
								}
							}
						}
						if (listHoraires.isEmpty()) {
							PlaceSchedule placeSchedule = new PlaceSchedule(
									period.getPeriodId(), period.getStartDate(),
									period.getEndDate(), period.getName(locale),
									locale);
							placeSchedule.setClosed(true);
							listHoraires.add(placeSchedule);
						}
						return listHoraires;
					}
				} else {
					// on vérifie si le lieu n'est pas ouvert 24h/24
					// 7j/7
					if (period.getAlwaysOpen()) {
						PlaceSchedule placeSchedule = new PlaceSchedule(
								period.getPeriodId(), period.getStartDate(),
								period.getEndDate(), period.getName(locale),
								locale);
						placeSchedule.setAlwaysOpen(true);
						listHoraires.add(placeSchedule);
					} else {
						for (Slot slot : period.getSlots()) {
							if (slot.getDayOfWeek() == (jourSemaine
									.get(Calendar.DAY_OF_WEEK) == 1
											? 6
											: jourSemaine
													.get(Calendar.DAY_OF_WEEK)
													- 2)) {
								PlaceSchedule placeSchedule = new PlaceSchedule(
										period.getPeriodId(),
										period.getStartDate(),
										period.getEndDate(),
										period.getName(locale), locale);
								String[] heure = slot.getStartHour().split(":");
								LocalTime startHour = LocalTime.of(
										Integer.parseInt(heure[0]),
										Integer.parseInt(heure[1]));
								heure = slot.getEndHour().split(":");
								LocalTime endHour = LocalTime.of(
										Integer.parseInt(heure[0]),
										Integer.parseInt(heure[1]));
								placeSchedule.setStartTime(startHour);
								placeSchedule.setEndTime(endHour);
								listHoraires.add(placeSchedule);
							}
						}
						if (listHoraires.isEmpty()) {
							PlaceSchedule placeSchedule = new PlaceSchedule(
									period.getPeriodId(), period.getStartDate(),
									period.getEndDate(), period.getName(locale),
									locale);
							placeSchedule.setClosed(true);
							listHoraires.add(placeSchedule);
						}
					}
				}
			}
		}
		return listHoraires;
	}
}