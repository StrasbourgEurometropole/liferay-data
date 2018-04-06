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

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the SubPlace service. Represents a row in the &quot;place_SubPlace&quot; database table, with each column mapped to a property of this class.
 *
 * @author Angelique Zunino Champougny
 * @see SubPlaceModel
 * @see eu.strasbourg.service.place.model.impl.SubPlaceImpl
 * @see eu.strasbourg.service.place.model.impl.SubPlaceModelImpl
 * @generated
 */
@ImplementationClassName("eu.strasbourg.service.place.model.impl.SubPlaceImpl")
@ProviderType
public interface SubPlace extends SubPlaceModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link eu.strasbourg.service.place.model.impl.SubPlaceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<SubPlace, Long> SUB_PLACE_ID_ACCESSOR = new Accessor<SubPlace, Long>() {
			@Override
			public Long get(SubPlace subPlace) {
				return subPlace.getSubPlaceId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<SubPlace> getTypeClass() {
				return SubPlace.class;
			}
		};

	/**
	* Retourne les ScheduleExceptions du sous-lieu
	*/
	public java.util.List<eu.strasbourg.service.place.model.ScheduleException> getScheduleExceptions();

	/**
	* Retourne les Periods du lieu auquel appartient le sous-lieu
	*/
	public java.util.List<eu.strasbourg.service.place.model.Period> getPeriods();

	/**
	* Retourne les périodes qui ne sont pas par défaut
	*/
	public java.util.List<eu.strasbourg.service.place.model.Period> getNonDefaultPeriod();

	/**
	* Retourne la période par défaut
	*/
	public eu.strasbourg.service.place.model.Period getDefaultPeriod();

	/**
	* Retourne le lieu parent du sous-lieu
	*/
	public eu.strasbourg.service.place.model.Place getParentPlace();

	/**
	* Retourne le lieu parent du sous-lieu
	*/
	public eu.strasbourg.service.place.model.Place getPlaceByPlaceId(
		long placeId);

	/**
	* Retourne les PublicHolidays
	*/
	public java.util.List<eu.strasbourg.service.place.model.PublicHoliday> getPublicHolidays();

	/**
	* Retourne une map contennant les horaires de chaque jour des 7 jours suivants "startDate" (inclus)
	*/
	public java.util.Map<java.lang.String, java.util.List<eu.strasbourg.service.place.model.PlaceSchedule>> getFollowingWeekSchedules(
		java.util.Date startDate, java.util.Locale locale);

	/**
	* Retourne une map contennant le jour et une liste de PlaceSchedule de la
	* semaine en cours
	*/
	public java.util.Map<java.lang.String, java.util.List<eu.strasbourg.service.place.model.PlaceSchedule>> getHoraire(
		java.util.Date dateJour, java.util.Locale locale);

	/**
	* Retourne les horaires d'ouverture du jour passé en paramètre jusqu'à "date" + "daysCount"
	*/
	public java.util.Map<java.lang.String, java.util.List<eu.strasbourg.service.place.model.PlaceSchedule>> getSubPlaceSchedule(
		java.util.Date date, int daysCount, java.util.Locale locale);

	/**
	* Retourne les horaires d'ouverture du jour voulu
	*/
	public java.util.List<eu.strasbourg.service.place.model.PlaceSchedule> getSubPlaceSchedule(
		java.util.GregorianCalendar jourSemaine, java.util.Locale locale);

	/**
	* Retourne les PlaceSchedule des exceptions d'ouverture à partir du lundi
	* de la semaine en cours
	*
	* @param surPériode
	(false = horaires d'une journée uniquement , true = horaires
	sur une semaine)
	*/
	public java.util.List<eu.strasbourg.service.place.model.PlaceSchedule> getSubPlaceScheduleException(
		java.util.GregorianCalendar premierJour, java.lang.Boolean surPeriode,
		java.util.Locale locale);

	/**
	* Retourne les PlaceSchedule des exceptions d'ouverture à partir du lundi
	* de la semaine en cours, jusqu'à dans 2 mois (pour freemarker)
	*/
	public java.util.List<eu.strasbourg.service.place.model.PlaceSchedule> getSubPlaceScheduleExceptionFreeMarker(
		java.util.Date dateDeb, java.lang.Boolean surPeriode,
		java.util.Locale locale);
}