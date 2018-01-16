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
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import com.liferay.portal.kernel.util.StringUtil;

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
import eu.strasbourg.utils.models.Pair;

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
		return ScheduleExceptionLocalServiceUtil.getBySubPlaceId(this.getSubPlaceId());
	}

	/**
	 * Retourne les Periods du sous-lieu
	 */
	@Override
	public List<Period> getPeriods() {
		return PeriodLocalServiceUtil.getBySubPlaceId(this.getSubPlaceId());
	}
	
	/**
	 * Retourne les périodes qui ne sont pas par défaut
	 */
	@Override 
	public List<Period> getNonDefaultPeriod() {
		return this.getPeriods().stream().filter(p -> !p.getDefaultPeriod()).collect(Collectors.toList());
	}
	
	/**
	 * Retourne la période par défaut
	 */
	@Override 
	public Period getDefaultPeriod() {
		for (Period period : this.getPeriods()) {
			if (period.getDefaultPeriod()) {
				return period;
			}
		}
		return null;
	}

	/**
	 * Retourne le lieu parent du sous-lieu
	 */
	@Override
	public Place getParentPlace() {
		return PlaceLocalServiceUtil.fetchPlace(this.getPlaceId());
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
	 * Retourne une map contennant les horaires de chaque jour des 7 jours suivants "startDate" (inclus)
	 */
	@Override
	public Map<String, List<PlaceSchedule>> getFollowingWeekSchedules(Date startDate, Locale locale) {
		Map<String, List<PlaceSchedule>> schedules = new LinkedHashMap<String, List<PlaceSchedule>>();
		LocalDate localDate = startDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

		DateTimeFormatter dtf = DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG).withLocale(locale).withZone(ZoneId.systemDefault());
		for (int dayIndex = 0; dayIndex < 7; dayIndex++) {
			List<PlaceSchedule> liste = getSubPlaceSchedule(localDate, locale);
			String dateString = localDate.atStartOfDay().format(dtf);
			dateString = localDate.format(DateTimeFormatter.ofPattern("EEEE", locale)) + " " + dateString;
			dateString = StringUtil.upperCaseFirstLetter(dateString);
			schedules.put(dateString, liste);
			localDate = localDate.plusDays(1);
		}
		return schedules;
	}

	/**
	 * Retourne une map contennant le jour et une liste de PlaceSchedule de la
	 * semaine en cours
	 */
	@Override
	public Map<String, List<PlaceSchedule>> getHoraire(Date dateJour, Locale locale) {
		Map<String, List<PlaceSchedule>> listHoraires = new LinkedHashMap<String, List<PlaceSchedule>>();

		// réupère le jour voulu de la semaine
		GregorianCalendar jourSemaine = new GregorianCalendar();
		jourSemaine.setTime(dateJour);
		jourSemaine.set(Calendar.HOUR_OF_DAY, 0);
		jourSemaine.clear(Calendar.MINUTE);
		jourSemaine.clear(Calendar.SECOND);
		jourSemaine.clear(Calendar.MILLISECOND);
		for (int jour = 0; jour <= 6; jour++) {
			jourSemaine.set(Calendar.DAY_OF_WEEK, (int) (jour == 6 ? 1 : jour + 2));

			List<PlaceSchedule> liste = getSubPlaceSchedule(jourSemaine, locale);

			DateFormat df = DateFormat.getDateInstance(DateFormat.FULL, locale);
			listHoraires.put(df.format(jourSemaine.getTime()), liste);
		}
		return listHoraires;
	}
	
	/**
	 * Retourne les horaires d'ouverture du jour passé en paramètre jusqu'à "date" + "daysCount" 
	 */
	@Override
	public Map<String, List<PlaceSchedule>> getSubPlaceSchedule(Date date, int daysCount, Locale locale) {

		Map<String, List<PlaceSchedule>> listHoraires = new LinkedHashMap<String, List<PlaceSchedule>>();

		// réupère le jour voulu de la semaine
		GregorianCalendar jourSemaine = new GregorianCalendar();
		jourSemaine.setTime(date);
		jourSemaine.set(Calendar.HOUR_OF_DAY, 0);
		jourSemaine.clear(Calendar.MINUTE);
		jourSemaine.clear(Calendar.SECOND);
		jourSemaine.clear(Calendar.MILLISECOND);
		for (int jour = 0; jour < daysCount; jour++) {
			List<PlaceSchedule> liste = getSubPlaceSchedule(jourSemaine, locale);
			DateFormat df = DateFormat.getDateInstance(DateFormat.FULL, locale);
			listHoraires.put(df.format(jourSemaine.getTime()), liste);
			jourSemaine.add(Calendar.DAY_OF_MONTH, 1);
		}
		return listHoraires;
	}


	/**
	 * Retourne les horaires d'ouverture du jour voulu
	 */
	private List<PlaceSchedule> getSubPlaceSchedule(LocalDate localDate, Locale locale) {
		GregorianCalendar gregorianCalendar = GregorianCalendar.from(localDate.atStartOfDay(ZoneId.systemDefault()));
		return this.getSubPlaceSchedule(gregorianCalendar, locale);
	}

	/**
	 * Retourne les horaires d'ouverture du jour voulu
	 */
	@Override
	public List<PlaceSchedule> getSubPlaceSchedule(GregorianCalendar jourSemaine, Locale locale) {
		List<PlaceSchedule> scheduleList = new ArrayList<PlaceSchedule>();
		List<PlaceSchedule> scheduleCompareList = new ArrayList<PlaceSchedule>();
		
		// Si le lieu parent est fermé à cette date, le sous-lieu est fermé
		List<PlaceSchedule> parentPlaceSchedules = this.getParentPlace().getPlaceSchedule(jourSemaine, locale);
		boolean parentPlaceIsClosed = parentPlaceSchedules.stream().anyMatch(s -> s.isClosed());
		if (parentPlaceIsClosed) {
			scheduleList.add(PlaceSchedule.createClosedSchedule(true));
			return scheduleList;
		}

		// vérifie si cette date n'est pas dans les horaires d'exception ni dans
		// les jours fériés
		scheduleList = getSubPlaceScheduleException(jourSemaine, false, locale);

		// s'il n'y a pas d'exception, on récupère les horaires de la
		// période concernée
		if (scheduleList.isEmpty()) {
			Period defaultPeriod = null;
			for (Period period : this.getPeriods()) 
			{
				// Soit la période en cours
				if (period.getStartDate() != null && period.getEndDate() != null
						&& period.getStartDate().compareTo(jourSemaine.getTime()) <= 0
						&& period.getEndDate().compareTo(jourSemaine.getTime()) >= 0) {
					int dayOfWeek = (jourSemaine.get(Calendar.DAY_OF_WEEK) == 1 ? 6
							: jourSemaine.get(Calendar.DAY_OF_WEEK) - 2);
					List<Slot> slots = period.getSlots().stream().filter(s -> s.getDayOfWeek() == dayOfWeek).collect(Collectors.toList());
					
					// On met alors dans la liste de comparaison pour la vérification d'horaire exceptionnel avec le lieu parent
					scheduleCompareList.add(PlaceSchedule.fromSlots(slots, period.getAlwaysOpen()));
				}
				// On met au cas où la période par défaut de côté
				if (period.getDefaultPeriod() ) 
				{
					defaultPeriod = period;
				}
			}
			// S'il n'y a aucune période en cours, la période par défaut
			if (defaultPeriod != null && scheduleCompareList.isEmpty()) 
			{
				int dayOfWeek = (jourSemaine.get(Calendar.DAY_OF_WEEK) == 1 ? 6
						: jourSemaine.get(Calendar.DAY_OF_WEEK) - 2);
				List<Slot> slots = defaultPeriod.getSlots().stream().filter(s -> s.getDayOfWeek() == dayOfWeek).collect(Collectors.toList());
				// On met alors dans la liste de comparaison pour la vérification d'horaire exceptionnel avec le lieu parent
				scheduleCompareList.add(PlaceSchedule.fromSlots(slots, defaultPeriod.getAlwaysOpen()));
			}
		}
		
		// On vérifie par rapport au lieu parent ...
		for (PlaceSchedule parentPlaceSchedule : parentPlaceSchedules)
		{
			// ... seulement en cas d'horaires exceptionnels
			if(parentPlaceSchedule.isException())
			{
				// on prépare les nouveaux objets accueillant les horaires
				PlaceSchedule scheduleParentException = new PlaceSchedule();				
				List<Pair<LocalTime, LocalTime>> openingTimes = new ArrayList<Pair<LocalTime, LocalTime>>();
				 
				// Un souci où l'on passait une fois avec le scheduleCompareList.get(0).getOpeningTimes() à null
				if(scheduleCompareList.get(0).getOpeningTimes() != null)
				{
					// On va comparer chaque horaire à parent ...
					for (Pair<LocalTime, LocalTime> parentTime : parentPlaceSchedule.getOpeningTimes())
					{
						// .. a chaque horaire du sous lieu
						for (Pair<LocalTime, LocalTime> subplaceTime : scheduleCompareList.get(0).getOpeningTimes())
						{
							// Si l'horaire du sous lieu chevauche celui du parent, on recrée un horaire pour le sous lieu qui est inclut dans les bornes de l'horaire parent
							if(parentTime.getFirst().compareTo(subplaceTime.getSecond()) <= 0 && parentTime.getSecond().compareTo(subplaceTime.getFirst()) >= 0)
							{
								LocalTime startHour =  parentTime.getFirst().isAfter(subplaceTime.getFirst()) ? parentTime.getFirst():subplaceTime.getFirst();
								LocalTime endHour = parentTime.getSecond().isBefore(subplaceTime.getSecond()) ? parentTime.getSecond():subplaceTime.getSecond();
								
								openingTimes.add(Pair.of(startHour, endHour));
							}
	
						}
					}
				}
				// Et on ajoute nos horaires à l'objet PlaceSchedule qui sera envoyé à la vue
				scheduleParentException.setOpeningTimes(openingTimes);
				scheduleParentException.setException(true);
				scheduleList.add(scheduleParentException);
			}

		}
		
		// Si cette liste est vide, c'est qu'il n'y a pas d'exceptions du Lieu parent et on remplit l'objet par les horaires de la période trouvée plus haut (période spécifique ou par défaut)
		if(scheduleList.isEmpty())
			scheduleList.addAll(scheduleCompareList);
		
		return scheduleList;
	}

	/**
	 * Retourne les PlaceSchedule des exceptions d'ouverture à partir du lundi
	 * de la semaine en cours
	 * 
	 * @param surPériode
	 *            (false = horaires d'une journée uniquement , true = horaires
	 *            sur une semaine)
	 */
	@Override
	public List<PlaceSchedule> getSubPlaceScheduleException(GregorianCalendar premierJour, Boolean surPeriode,
			Locale locale) {
		List<PlaceSchedule> listPlaceSchedules = new ArrayList<PlaceSchedule>();
		GregorianCalendar dernierJour = new GregorianCalendar();
		premierJour.set(Calendar.HOUR_OF_DAY, 0);
		premierJour.set(Calendar.MINUTE, 0);
		premierJour.set(Calendar.SECOND, 0);
		premierJour.set(Calendar.MILLISECOND, 0);
		dernierJour.setTime(premierJour.getTime());
		dernierJour.add(Calendar.DAY_OF_YEAR, 1);
		dernierJour.add(Calendar.MINUTE, -1);
		if (surPeriode) {
			premierJour.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
			dernierJour.add(Calendar.MONTH, 2);
		}

		// vérifie si cette date n'est pas dans les horaires d'exception
		for (ScheduleException scheduleException : this.getScheduleExceptions()) {
			if (scheduleException.getStartDate() != null && scheduleException.getEndDate() != null
					&& scheduleException.getStartDate().compareTo(dernierJour.getTime()) <= 0
					&& scheduleException.getEndDate().compareTo(premierJour.getTime()) >= 0) {
				if (scheduleException.isClosed()) {
					PlaceSchedule placeSchedule = new PlaceSchedule(scheduleException.getExceptionId(),
							scheduleException.getStartDate(), scheduleException.getEndDate(),
							scheduleException.getComment(locale), locale);
					placeSchedule.setException(true);
					placeSchedule.setClosed(true);
					listPlaceSchedules.add(placeSchedule);
				} else {
					PlaceSchedule placeSchedule = new PlaceSchedule(scheduleException.getExceptionId(),
							scheduleException.getStartDate(), scheduleException.getEndDate(),
							scheduleException.getComment(locale), locale);
					placeSchedule.setException(true);
					placeSchedule.setOpeningTimes(scheduleException.getOpeningLocalTimes());
					listPlaceSchedules.add(placeSchedule);
				}
			}
		}

		if (premierJour.compareTo(dernierJour) == 0 && !listPlaceSchedules.isEmpty()) {
			return listPlaceSchedules;
		}

		// vérifie si cette date n'est pas dans les horaires d'exception
		// vérifie si le lieu attaché est assujeti au jour férié
		Place place = this.getPlaceByPlaceId(this.getPlaceId());
		if (place.isSubjectToPublicHoliday()) {
			for (PublicHoliday publicHoliday : this.getPublicHolidays()) {
				if (publicHoliday.getDate() != null) {
					GregorianCalendar publicHolidayYear = new GregorianCalendar();
					publicHolidayYear.setTime(publicHoliday.getDate());
					publicHolidayYear.set(Calendar.YEAR, premierJour.get(Calendar.YEAR));
					if (publicHolidayYear.compareTo(premierJour) >= 0
							&& publicHolidayYear.compareTo(dernierJour) <= 0) {
						PlaceSchedule placeSchedule = new PlaceSchedule(publicHoliday.getPublicHolidayId(),
								publicHoliday.getDate(), publicHoliday.getDate(), publicHoliday.getName(locale),
								locale);
						placeSchedule.setPublicHoliday(true);
						placeSchedule.setClosed(true);
						listPlaceSchedules.clear();
						listPlaceSchedules.add(placeSchedule);
						break;
					}
				}
			}
		}
		listPlaceSchedules = listPlaceSchedules.stream()
				.sorted((s1, s2) -> s1.getStartDate().compareTo(s2.getStartDate())).collect(Collectors.toList());
		return listPlaceSchedules;
	}

	/**
	 * Retourne les PlaceSchedule des exceptions d'ouverture à partir du lundi
	 * de la semaine en cours, jusqu'à dans 2 mois (pour freemarker)
	 */
	@Override
	public List<PlaceSchedule> getSubPlaceScheduleExceptionFreeMarker(Date dateDeb, Boolean surPeriode, Locale locale) {
		GregorianCalendar premierJour = new GregorianCalendar();
		premierJour.setTime(dateDeb);
		return getSubPlaceScheduleException(premierJour, surPeriode, locale);
	}
}