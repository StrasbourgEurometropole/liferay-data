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

package eu.strasbourg.service.agenda.model.impl;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.util.DateFormatFactoryUtil;
import com.liferay.portal.kernel.util.Validator;

import aQute.bnd.annotation.ProviderType;
import eu.strasbourg.service.agenda.model.Event;
import eu.strasbourg.service.agenda.model.EventPeriod;
import eu.strasbourg.service.agenda.model.Manifestation;
import eu.strasbourg.service.agenda.service.EventLocalServiceUtil;
import eu.strasbourg.service.agenda.service.EventPeriodLocalServiceUtil;
import eu.strasbourg.service.agenda.service.ManifestationLocalServiceUtil;
import eu.strasbourg.service.place.model.Place;
import eu.strasbourg.service.place.service.PlaceLocalServiceUtil;
import eu.strasbourg.utils.AssetVocabularyHelper;
import eu.strasbourg.utils.DateHelper;
import eu.strasbourg.utils.FileEntryHelper;
import eu.strasbourg.utils.JSONHelper;
import eu.strasbourg.utils.StrasbourgPropsUtil;
import eu.strasbourg.utils.constants.VocabularyNames;

/**
 * The extended model implementation for the Event service. Represents a row in
 * the &quot;event&quot; database table, with each column mapped to a property
 * of this class.
 *
 * <p>
 * Helper methods and all application logic should be put in this class.
 * Whenever methods are added, rerun ServiceBuilder to copy their definitions
 * into the {@link eu.strasbourg.service.agenda.model.Event} interface.
 * </p>
 *
 * @author BenjaminBini
 */
@ProviderType
public class EventImpl extends EventBaseImpl {

	private static final long serialVersionUID = -263639533491031888L;

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. All methods that expect a event
	 * model instance should use the {@link
	 * eu.strasbourg.service.agenda.model.Event} interface instead.
	 */
	public EventImpl() {
	}

	/**
	 * Retourne l'AssetEntry rattaché cet item
	 */
	@Override
	public AssetEntry getAssetEntry() {
		return AssetEntryLocalServiceUtil.fetchEntry(Event.class.getName(),
			this.getEventId());
	}

	/**
	 * Renvoie la liste des AssetCategory rattachées à cet item (via
	 * l'assetEntry)
	 */
	@Override
	public List<AssetCategory> getCategories() {
		return AssetVocabularyHelper
			.getAssetEntryCategories(this.getAssetEntry());
	}

	/**
	 * Retourne l'URL de l'image à partir de l'id du DLFileEntry
	 */
	@Override
	public String getImageURL() {
		if (Validator.isNotNull(this.getExternalImageURL())) {
			return this.getExternalImageURL();
		} else {
			return FileEntryHelper.getFileEntryURL(this.getImageId());
		}
	}

	/**
	 * Retourne le copyright de l'image principale
	 */
	@Override
	public String getImageCopyright(Locale locale) {
		if (Validator.isNotNull(this.getExternalImageCopyright())) {
			return this.getExternalImageCopyright();
		} else {
			return FileEntryHelper.getImageCopyright(this.getImageId(), locale);
		}
	}

	/**
	 * Retourne la liste des manifestations auxquelles cette édition appartient
	 */
	@Override
	public List<Manifestation> getManifestations() {
		return ManifestationLocalServiceUtil
			.getEventManifestations(this.getEventId());
	}

	/**
	 * Retourne la liste des IDs des manifestations auxquelles cette édition
	 * appartient sous forme de String
	 */
	@Override
	public String getManifestationsIds() {
		List<Manifestation> manifestations = this.getManifestations();
		String ids = "";
		for (Manifestation manifestation : manifestations) {
			if (ids.length() > 0) {
				ids += ",";
			}
			ids += manifestation.getManifestationId();
		}
		return ids;
	}

	/**
	 * Retourne la liste des galeries publiées
	 */
	@Override
	public List<Manifestation> getPublishedManifestations() {
		List<Manifestation> manifestations = this.getManifestations();
		List<Manifestation> result = new ArrayList<Manifestation>();
		for (Manifestation manifestation : manifestations) {
			if (manifestation.isApproved()) {
				result.add(manifestation);
			}
		}
		return result;
	}

	/**
	 * Retourne la liste des périodes auxquelles l'événement à lieu (classées
	 * par date de début croissante)
	 */
	@Override
	public List<EventPeriod> getEventPeriods() {
		List<EventPeriod> periods = EventPeriodLocalServiceUtil
			.getByEventId(this.getEventId());
		List<EventPeriod> sortedPeriods = new ArrayList<EventPeriod>(periods);
		sortedPeriods
			.sort((p1, p2) -> p1.getStartDate().compareTo(p2.getStartDate()));
		return sortedPeriods;
	}

	/**
	 * Retourne la période principale de l'événement (de la première date de
	 * début à la dernière date de fin) sous forme de String dans la locale
	 * passée en paramètre
	 */
	@Override
	public String getEventScheduleDisplay(Locale locale) {
		return DateHelper.displayPeriod(this.getFirstStartDate(),
			this.getLastEndDate(), locale);
	}

	/**
	 * Retourne la version live de l'édition, si elle existe
	 */
	@Override
	public Event getLiveVersion() {
		long groupId = this.getGroupId();
		Group group = GroupLocalServiceUtil.fetchGroup(groupId);
		if (group == null || !group.isStagingGroup()) {
			return null;
		}
		long liveGroupId = group.getLiveGroupId();
		Event liveEvent = EventLocalServiceUtil
			.fetchEventByUuidAndGroupId(this.getUuid(), liveGroupId);
		return liveEvent;
	}

	/**
	 * Retourne l'objet "Place" correspondant au lieu de l'événement, s'il
	 * existe
	 */
	private Place place = null;

	private Place getPlace() {
		if (place == null && Validator.isNotNull(this.getPlaceSIGId())) {
			place = PlaceLocalServiceUtil.getPlaceBySIGId(this.getPlaceSIGId());
		}
		return place;
	}

	/**
	 * Retourne le nom de la ville, provenant du lieu interne s'il existe, du
	 * lieu lié sinon
	 */
	@Override
	public String getCity(Locale locale) {
		return this.getPlace() == null ? super.getPlaceCity()
			: this.getPlace().getCity(locale);
	}

	/**
	 * Retourne le nom du lieu, provenant du lieu interne s'il existe, du lieu
	 * lié sinon
	 */
	@Override
	public String getPlaceAlias(Locale locale) {
		return this.getPlace() == null ? super.getPlaceName(locale)
			: this.getPlace().getAlias(locale);
	}

	/**
	 * Retourne l'adresse sans la ville
	 */
	@Override
	public String getPlaceAddress(Locale locale) {
		if (this.getPlace() == null) {
			return this.getPlaceStreetNumber() + " "
				+ this.getPlaceStreetName();
		} else {
			return this.getPlace().getAddressStreet() + " "
				+ this.getPlace().getAddressComplement();
		}
	}

	/**
	 * Retourne la ville
	 */
	@Override
	public String getPlaceCity(Locale locale) {
		return this.getPlace() == null ? super.getPlaceCity()
			: this.getPlace().getCity(locale);
	}

	/**
	 * Retourne le code postal
	 */
	@Override
	public String getPlaceZipCode() {
		return this.getPlace() == null ? super.getPlaceZipCode()
			: this.getPlace().getAddressZipCode();
	}

	/**
	 * Retourne l'accès
	 */
	@Override
	public String getAccess(Locale locale) {
		return this.getPlace() == null ? super.getAccess(locale)
			: this.getPlace().getAccess(locale);
	}

	@Override
	public Boolean getAccessForBlind() {
		return this.getPlace() == null ? super.getAccessForBlind()
			: this.getPlace().getAccessForBlind();
	}

	@Override
	public Boolean getAccessForDeaf() {
		return this.getPlace() == null ? super.getAccessForDeaf()
			: this.getPlace().getAccessForDeaf();
	}

	@Override
	public Boolean getAccessForDeficient() {
		return this.getPlace() == null ? super.getAccessForDeficient()
			: this.getPlace().getAccessForDeficient();
	}

	@Override
	public Boolean getAccessForElder() {
		return this.getPlace() == null ? super.getAccessForElder()
			: this.getPlace().getAccessForElder();
	}

	@Override
	public Boolean getAccessForWheelchair() {
		return this.getPlace() == null ? super.getAccessForWheelchair()
			: this.getPlace().getAccessForWheelchair();
	}

	/**
	 * Retourne true si l'événement est accessible pour au moins un type de
	 * handicap
	 */
	@Override
	public boolean hasAnyAccessForDisabled() {
		return (this.getAccessForBlind() || this.getAccessForDeaf()
			|| this.getAccessForWheelchair() || this.getAccessForDeficient()
			|| this.getAccessForElder());
	}

	/**
	 * Retourne l'adresse complète du lieu, provenant du lieu interne s'il
	 * existe, du lieu lié sinon
	 */
	@Override
	public String getPlaceAddressHTML(Locale locale) {
		if (Validator.isNotNull(this.getPlaceName())) {
			return this.getPlaceStreetNumber() + " " + this.getPlaceStreetName()
				+ "<br>" + this.getPlaceZipCode() + " " + this.getCity(locale);
		} else if (Validator.isNotNull(this.getPlace())) {
			Place place = this.getPlace();
			return place.getAddressStreet() + "<br>" + place.getAddressZipCode()
				+ " " + place.getCity(locale);
		} else {
			return "";
		}
	}

	/**
	 * Retourne les types de l'événement
	 */
	@Override
	public List<AssetCategory> getTypes() {
		return AssetVocabularyHelper.getAssetEntryCategoriesByVocabulary(
			this.getAssetEntry(), VocabularyNames.EVENT_TYPE);
	}

	/**
	 * Retourne le label des types de l'événement
	 */
	@Override
	public String getTypeLabel(Locale locale) {
		String types = "";
		for (AssetCategory type : this.getTypes()) {
			if (types.length() > 0) {
				types += " - ";
			}
			types += type.getTitle(locale);
		}
		return types;
	}

	/**
	 * Retourne les themes de l'événement
	 */
	@Override
	public List<AssetCategory> getThemes() {
		return AssetVocabularyHelper.getAssetEntryCategoriesByVocabulary(
			this.getAssetEntry(), VocabularyNames.EVENT_THEME);
	}

	/**
	 * Retourne le label des thèmes de l'événement
	 */
	@Override
	public String getThemeLabel(Locale locale) {
		String themes = "";
		for (AssetCategory theme : this.getThemes()) {
			if (themes.length() > 0) {
				themes += " - ";
			}
			themes += theme.getTitle(locale);
		}
		return themes;
	}

	/**
	 * Retourne les publics de l'événement
	 */
	@Override
	public List<AssetCategory> getPublics() {
		return AssetVocabularyHelper.getAssetEntryCategoriesByVocabulary(
			this.getAssetEntry(), VocabularyNames.EVENT_PUBLIC);
	}

	/**
	 * Retourne le label des publics de l'événement
	 */
	@Override
	public String getPublicLabel(Locale locale) {
		String publics = "";
		for (AssetCategory eventPublic : this.getPublics()) {
			if (publics.length() > 0) {
				publics += " - ";
			}
			publics += eventPublic.getTitle(locale);
		}
		return publics;
	}

	/**
	 * Retourne les territoires de l'événement
	 */
	@Override
	public List<AssetCategory> getTerritories() {
		return AssetVocabularyHelper.getAssetEntryCategoriesByVocabulary(
			this.getAssetEntry(), VocabularyNames.TERRITORY);
	}

	/**
	 * Retourne les territoires de l'événement
	 */
	@Override
	public List<AssetCategory> getServices() {
		return AssetVocabularyHelper.getAssetEntryCategoriesByVocabulary(
			this.getAssetEntry(), VocabularyNames.EVENT_SERVICE);
	}

	/**
	 * Retourne la version JSON de l'événenement
	 */
	@Override
	public JSONObject toJSON() {
		JSONObject jsonEvent = JSONFactoryUtil.createJSONObject();

		jsonEvent.put("id", this.getEventId());
		jsonEvent.put("externalId", this.getIdSource());

		jsonEvent.put("title",
			JSONHelper.getJSONFromI18nMap(this.getTitleMap()));

		if (Validator.isNotNull(this.getSubtitle())) {
			jsonEvent.put("subtitle",
				JSONHelper.getJSONFromI18nMap(this.getSubtitleMap()));
		}

		jsonEvent.put("description",
			JSONHelper.getJSONFromI18nMap(this.getDescriptionMap()));

		String imageURL = this.getImageURL();
		if (!imageURL.startsWith("http")) {
			imageURL = StrasbourgPropsUtil.getURL() + this.getImageURL();
		}
		jsonEvent.put("imageURL", imageURL);

		jsonEvent.put("imageCopyright",
			this.getImageCopyright(Locale.getDefault()));

		if (Validator.isNotNull(this.getPlaceSIGId())) {
			jsonEvent.put("placeSIGId", this.getPlaceSIGId());
		} else {
			JSONObject jsonPlace = JSONFactoryUtil.createJSONObject();
			jsonPlace.put("name",
				JSONHelper.getJSONFromI18nMap(this.getPlaceNameMap()));
			jsonPlace.put("streetNumber", this.getPlaceStreetNumber());
			jsonPlace.put("streetName", this.getPlaceStreetName());
			jsonPlace.put("zipCode", this.getPlaceZipCode());
			jsonPlace.put("city", this.getPlaceCity());
			jsonPlace.put("access",
				JSONHelper.getJSONFromI18nMap(this.getAccessMap()));
			jsonPlace.put("accessForDisabled",
				JSONHelper.getJSONFromI18nMap(this.getAccessForDisabledMap()));
			jsonPlace.put("accessForBlind", this.getAccessForBlind());
			jsonPlace.put("accessForDeaf", this.getAccessForDeaf());
			jsonPlace.put("accessForWheelchair", this.getAccessForWheelchair());
			jsonPlace.put("accessForDeficient", this.getAccessForDeficient());
			jsonPlace.put("accessForElder", this.getAccessForElder());
			jsonEvent.put("place", jsonPlace);
		}

		if (Validator.isNotNull(this.getPromoter())) {
			jsonEvent.put("promoter", this.getPromoter());
		}

		if (Validator.isNotNull(this.getPhone())) {
			jsonEvent.put("phone", this.getPhone());
		}

		if (Validator.isNotNull(this.getEmail())) {
			jsonEvent.put("mail", this.getEmail());
		}

		if (Validator.isNotNull(this.getWebsiteURL())) {
			jsonEvent.put("websiteURL",
				JSONHelper.getJSONFromI18nMap(this.getWebsiteURLMap()));
		}

		if (Validator.isNotNull(this.getWebsiteName())) {
			jsonEvent.put("websiteName",
				JSONHelper.getJSONFromI18nMap(this.getWebsiteNameMap()));
		}

		jsonEvent.put("freeEntry", this.getFree());

		if (Validator.isNotNull(this.getPrice())) {
			jsonEvent.put("price",
				JSONHelper.getJSONFromI18nMap(this.getPriceMap()));
		}

		JSONArray periodsJSON = JSONFactoryUtil.createJSONArray();
		for (EventPeriod period : this.getEventPeriods()) {
			JSONObject periodJSON = JSONFactoryUtil.createJSONObject();

			DateFormat dateFormat = DateFormatFactoryUtil
				.getSimpleDateFormat("yyyy-MM-dd");
			periodJSON.put("startDate",
				dateFormat.format(period.getStartDate()));
			periodJSON.put("endDate", dateFormat.format(period.getEndDate()));

			if (Validator.isNotNull(period.getTimeDetail())) {
				periodJSON.put("timeDetail",
					JSONHelper.getJSONFromI18nMap(period.getTimeDetailMap()));
			}
			periodsJSON.put(periodJSON);
		}

		jsonEvent.put("periods", periodsJSON);

		JSONArray jsonManifestations = JSONFactoryUtil.createJSONArray();
		for (Manifestation manifestation : this.getPublishedManifestations()) {
			jsonManifestations.put(manifestation.getManifestationId());
		}
		if (jsonManifestations.length() > 0) {
			jsonEvent.put("manifestations", jsonManifestations);
		}

		JSONArray jsonCategories = AssetVocabularyHelper
			.getExternalIdsJSONArray(this.getCategories());
		if (jsonCategories.length() > 0) {
			jsonEvent.put("categories", jsonCategories);
		}

		JSONArray jsonThemes = AssetVocabularyHelper
			.getExternalIdsJSONArray(this.getThemes());
		if (jsonThemes.length() > 0) {
			jsonEvent.put("themes", jsonThemes);
		}

		JSONArray jsonTypes = AssetVocabularyHelper
			.getExternalIdsJSONArray(this.getTypes());
		if (jsonTypes.length() > 0) {
			jsonEvent.put("types", jsonTypes);
		}

		JSONArray jsonTerritories = AssetVocabularyHelper
			.getExternalIdsJSONArray(this.getTerritories());
		if (jsonTerritories.length() > 0) {
			jsonEvent.put("territories", jsonTerritories);
		}

		JSONArray jsonPublics = AssetVocabularyHelper
			.getExternalIdsJSONArray(this.getPublics());
		if (jsonPublics.length() > 0) {
			jsonEvent.put("publics", jsonPublics);
		}

		JSONArray jsonServices = AssetVocabularyHelper
			.getExternalIdsJSONArray(this.getServices());
		if (jsonServices.length() > 0) {
			jsonEvent.put("services", jsonServices);
		}

		jsonEvent.put("eventURL", StrasbourgPropsUtil.getAgendaDetailURL()
			+ "/-/entity/id/" + this.getEventId());

		return jsonEvent;
	}
}