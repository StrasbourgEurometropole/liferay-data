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
 * The extended model interface for the Place service. Represents a row in the &quot;place_Place&quot; database table, with each column mapped to a property of this class.
 *
 * @author Angelique Zunino Champougny
 * @see PlaceModel
 * @see eu.strasbourg.service.place.model.impl.PlaceImpl
 * @see eu.strasbourg.service.place.model.impl.PlaceModelImpl
 * @generated
 */
@ImplementationClassName("eu.strasbourg.service.place.model.impl.PlaceImpl")
@ProviderType
public interface Place extends PlaceModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link eu.strasbourg.service.place.model.impl.PlaceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<Place, Long> PLACE_ID_ACCESSOR = new Accessor<Place, Long>() {
			@Override
			public Long get(Place place) {
				return place.getPlaceId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<Place> getTypeClass() {
				return Place.class;
			}
		};

	/**
	* Retourne l'AssetEntry rattaché cet item
	*/
	public com.liferay.asset.kernel.model.AssetEntry getAssetEntry();

	/**
	* Renvoie la liste des AssetCategory rattachées à cet item (via
	* l'assetEntry)
	*/
	public java.util.List<com.liferay.asset.kernel.model.AssetCategory> getCategories();

	/**
	* Renvoie l'URL de l'image à partir de l'id du DLFileEntry
	*/
	public java.lang.String getImageURL();

	/**
	* Retourne le copyright de l'image principale
	*/
	public java.lang.String getImageCopyright(java.util.Locale locale);

	/**
	* Retourne le prix rattaché au lieu
	*/
	public eu.strasbourg.service.place.model.Price getPrice();

	/**
	* Retourne les ScheduleExceptions du lieu
	*/
	public java.util.List<eu.strasbourg.service.place.model.ScheduleException> getScheduleExceptions();

	/**
	* Retourne les PublicHolidays
	*/
	public java.util.List<eu.strasbourg.service.place.model.PublicHoliday> getPublicHolidays();

	/**
	* Renvoie la liste des IDs des ScheduleExceptions auxquelles ce lieu
	* appartient sous forme de String
	*/
	public java.lang.String getScheduleExceptionsIds();

	/**
	* Retourne les sous lieux du lieux
	*/
	public java.util.List<eu.strasbourg.service.place.model.SubPlace> getSubPlaces();

	/**
	* Retourne les sous lieux publiés du lieu
	*/
	public java.util.List<eu.strasbourg.service.place.model.SubPlace> getPublishedSubPlaces();

	/**
	* Retourne les Periods du lieux
	*/
	public java.util.List<eu.strasbourg.service.place.model.Period> getPeriods();

	/**
	* Retourne les périodes qui ne sont pas par défaut (uniquement les périodes en cours ou futures)
	*/
	public java.util.List<eu.strasbourg.service.place.model.Period> getNonDefaultPeriods();

	/**
	* Retourne la période par défaut
	*/
	public eu.strasbourg.service.place.model.Period getDefaultPeriod();

	/**
	* Retourne les territoire du lieu
	*/
	public java.util.List<com.liferay.asset.kernel.model.AssetCategory> getTerritories();

	/**
	* Retourne les types du lieu
	*/
	public java.util.List<com.liferay.asset.kernel.model.AssetCategory> getTypes();

	/**
	* Retourne le label des types de l'événement
	*/
	public java.lang.String getTypeLabel(java.util.Locale locale);

	/**
	* Retourne la ville
	*/
	public java.lang.String getCity(java.util.Locale locale);

	/**
	* Vérifie si le lieu à accès au temps réel
	*
	* @throws PortalException
	*/
	public java.lang.Boolean isEnabled()
		throws com.liferay.portal.kernel.exception.PortalException;

	/**
	* Retourne true si le type du lieu doit avoir un calendrier d'horaires
	*/
	public java.lang.Boolean hasScheduleTable();

	/**
	* Retourne la catégorie Territoire correspondant à la ville du lieu
	*/
	public com.liferay.asset.kernel.model.AssetCategory getCityCategory();

	/**
	* Retourne le quartier
	*/
	public java.lang.String getDistrict(java.util.Locale locale);

	/**
	* Retourne la catégorie Territoire correspondant au quartier du lieu
	*/
	public com.liferay.asset.kernel.model.AssetCategory getDistrictCategory();

	/**
	* Retourne la liste des URL publiques des images additionnelles
	*/
	public java.util.List<java.lang.String> getImagesURLs();

	/**
	* Retourne une map d'URL et de titre des images additionnelles et des
	* vidéos
	*
	* @throws PortalException
	*/
	public java.util.List<com.liferay.asset.kernel.model.AssetEntry> getRandomContents()
		throws com.liferay.portal.kernel.exception.PortalException;

	/**
	* Retourne l'URL publiques de l'image
	*/
	public java.lang.String getImageURL(java.lang.Long imageId);

	/**
	* Retourne le copyright publiques de l'image
	*/
	public java.lang.String getImageCopyright(java.lang.Long imageId,
		java.util.Locale locale);

	/**
	* Retourne la légende publiques de l'image
	*/
	public java.lang.String getImageLegend(java.lang.Long imageId,
		java.util.Locale locale);

	/**
	* Retourne la liste des URL des documents de ce lieu
	*/
	public java.util.List<java.lang.String> getDocumentURLs();

	/**
	* Retourne une map de titre et d'URL des documents de ce lieu
	*/
	public java.util.Map<java.lang.String, java.lang.String> getDocuments();

	/**
	* Retourne la liste des vidéos de ce lieu
	*/
	public java.util.List<eu.strasbourg.service.video.model.Video> getVideos();

	/**
	* Retourne une list d'évènements lié à ce lieu
	*/
	public java.util.List<eu.strasbourg.service.agenda.model.Event> getEvents();

	/**
	* Retourne une list d'évènements lié à ce lieu
	*/
	public java.util.List<eu.strasbourg.service.agenda.model.Event> getPublishedEvents();

	/**
	* Retourne une list d'évènements lié à ce lieu
	*/
	public java.util.List<eu.strasbourg.service.agenda.model.Event> getCurrentAndFuturePublishedEvents();

	/**
	* Retourne true si l'événement est accessible pour au moins un type de
	* handicap
	*/
	public boolean hasAnyAccessForDisabled();

	/**
	* Retourne true si le lieu est ouvert à l'instant passé en paramètre
	*/
	public java.lang.Boolean isOpen(java.time.LocalDateTime localDateTime);

	/**
	* Retourne true si le lieu est ouvert à l'instant présent
	*/
	public java.lang.Boolean isOpenNow();

	/**
	* Vérifie si le lieu est fermé un jour donné
	*/
	public java.lang.Boolean isClosed(java.util.GregorianCalendar jourSemaine);

	/**
	* Retourne true si le lieu est une piscine
	*
	* @return
	*/
	public boolean isSwimmingPool();

	/**
	* Retourne true si le lieu est un parking
	*
	* @return
	*/
	public boolean isParking();

	/**
	* Retourne true si le lieu est une mairie
	*
	* @return
	*/
	public boolean isMairie();

	/**
	* Retourne le temps réel (en gérant automatiquement le fait que ce soit une
	* piscine,une mairie ou un parking)
	*
	* @throws Exception
	*/
	public eu.strasbourg.utils.OccupationState getRealTime();

	/**
	* Retourne le temps réel (couleur de fond,valeur)
	*
	* @param type (1 = piscine, 2 = parking, 3 = mairie)
	* @throws Exception
	*/
	public eu.strasbourg.utils.OccupationState getRealTime(
		java.lang.String type);

	/**
	* Retourne une map contennant les horaires de chaque jour des 7 jours
	* suivants "startDate" (inclus)
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
	* Retourne le PlaceSchedule de la prochaine ouverture (pour X jour)
	*/
	public eu.strasbourg.service.place.model.PlaceSchedule getNextScheduleOpening(
		java.util.GregorianCalendar today, int nbDays, java.util.Locale locale);

	/**
	* Retourne le PlaceSchedule de la prochaine ouverture (sous quinzaine)
	*/
	public eu.strasbourg.service.place.model.PlaceSchedule getNextScheduleOpening(
		java.util.GregorianCalendar today, java.util.Locale locale);

	/**
	* Retourne les horaires d'ouverture du jour passé en paramètre jusqu'à
	* "date" + "daysCount"
	*/
	public java.util.Map<java.lang.String, java.util.List<eu.strasbourg.service.place.model.PlaceSchedule>> getPlaceSchedule(
		java.util.Date date, int daysCount, java.util.Locale locale);

	/**
	* Retourne les horaires d'ouverture du jour
	*/
	public java.util.List<eu.strasbourg.service.place.model.PlaceSchedule> getPlaceSchedule(
		java.util.GregorianCalendar jourSemaine, java.util.Locale locale);

	/**
	* Retourne les horaires des exceptions d'ouverture à partir du lundi de la
	* semaine en cours
	*
	* @param surPeriode (false = horaires d'une journée uniquement , true = horaires
	  sur 2 mois à partir du jour + le début de la semaine)
	*/
	public java.util.List<eu.strasbourg.service.place.model.PlaceSchedule> getPlaceScheduleException(
		java.util.GregorianCalendar dateChoisie, java.lang.Boolean surPeriode,
		java.util.Locale locale);

	/**
	* Retourne les PlaceSchedule des exceptions d'ouverture à partir du lundi
	* de la semaine en cours, jusqu'à dans 2 mois (pour freemarker)
	*/
	public java.util.List<eu.strasbourg.service.place.model.PlaceSchedule> getPlaceScheduleExceptionFreeMarker(
		java.util.Date dateDeb, java.lang.Boolean surPeriode,
		java.util.Locale locale);

	/**
	* Retourne la version JSON du lieu
	*/
	public com.liferay.portal.kernel.json.JSONObject toJSON();

	/**
	* Retourne la version GeoJSON du lieu
	*/
	public com.liferay.portal.kernel.json.JSONObject toGeoJSON();

	/**
	* Reprise de l'horriblissime webservice des lieux de LR6
	*/
	public com.liferay.portal.kernel.json.JSONObject toLegacyJSON();
}