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
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

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
import eu.strasbourg.utils.AssetVocabularyHelper;
import eu.strasbourg.utils.DateHelper;
import eu.strasbourg.utils.FileEntryHelper;
import eu.strasbourg.utils.JSONHelper;
import eu.strasbourg.utils.models.LegacyPlace;

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
	 * Retourne true si l'événement est accessible pour au moins un type de
	 * handicap
	 */
	@Override
	public boolean hasAnyAccessForDisabled() {
		return this.getAccessForBlind() || this.getAccessForDeaf()
			|| this.getAccessForWheelchair() || this.getAccessForDeficient()
			|| this.getAccessForElder();
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
	 * Retourne l'objet "LegacyPlace" correspondant au lieu de l'événement, s'il
	 * existe
	 */
	@Override
	public LegacyPlace getLegacyPlace(Locale locale) {
		if (locale_legacyPlace == null) {
			locale_legacyPlace = new HashMap<Locale, LegacyPlace>();
		}
		if (locale_legacyPlace.get(locale) == null) {
			LegacyPlace legacyPlace = LegacyPlace
				.fromSIGId(this.getPlaceSIGId(), locale);
			if (legacyPlace != null) {
				locale_legacyPlace.put(locale, legacyPlace);
			}
		}
		return locale_legacyPlace.get(locale);
	}
	
	/**
	 * Retourne le nom de la ville, provenant du lieu interne s'il existe, du lieu lié sinon
	 */
	@Override
	public String getCity(Locale locale) {
		if (Validator.isNotNull(this.getPlaceCity())) {
			return this.getPlaceCity();
		} else if (Validator.isNotNull(this.getLegacyPlace(locale))) {
			return this.getLegacyPlace(locale).getCity();
		} else {
			return "";
		}
	}

	/**
	 * Retourne le nom du lieu, provenant du lieu interne s'il existe, du lieu lié sinon
	 */
	@Override
	public String getPlaceAlias(Locale locale) {
		if (Validator.isNotNull(this.getPlaceName(locale))) {
			return this.getPlaceName(locale);
		} else if (Validator.isNotNull(this.getLegacyPlace(locale))) {
			return this.getLegacyPlace(locale).getAlias();
		} else {
			return "";
		}
	}
	
	/**
	 * Retourne l'adresse complète du lieu, provenant du lieu interne s'il existe, du lieu lié sinon
	 */
	@Override
	public String getPlaceAddressHTML(Locale locale) {
		if (Validator.isNotNull(this.getPlaceName())) {
			return this.getPlaceStreetNumber() + " " + this.getPlaceStreetName() + "<br>" + this.getPlaceZipCode() + " " + this.getCity(locale);
		} else if (Validator.isNotNull(this.getLegacyPlace(locale))) {
			LegacyPlace place = this.getLegacyPlace(locale);
			return place.getStreet() + "<br>" + place.getZipCode() + " " + place.getCity();
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
			this.getAssetEntry(), "type agenda");
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
			this.getAssetEntry(), "theme agenda");
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
			this.getAssetEntry(), "public agenda");
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
			this.getAssetEntry(), "territoire");
	}

	/**
	 * Retourne les territoires de l'événement
	 */
	@Override
	public List<AssetCategory> getServices() {
		return AssetVocabularyHelper.getAssetEntryCategoriesByVocabulary(
			this.getAssetEntry(), "service gestionnaire");
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
		jsonEvent.put("imageURL", this.getImageURL());

		jsonEvent.put("imageCopyright",
			this.getImageCopyright(Locale.getDefault()));

		if (Validator.isNotNull(this.getPlaceSIGId())) {
			jsonEvent.put("placeSIGId", this.getPlaceSIGId());
		} else {
			JSONObject jsonPlace = JSONFactoryUtil.createJSONObject();
			jsonPlace.put("name", JSONHelper.getJSONFromI18nMap(this.getPlaceNameMap()));
			jsonPlace.put("streetNumber", this.getPlaceStreetNumber());
			jsonPlace.put("streetName", this.getPlaceStreetName());
			jsonPlace.put("zipCode", this.getPlaceZipCode());
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
			
			DateFormat dateFormat = DateFormatFactoryUtil.getSimpleDateFormat("yyyy-MM-dd");
			periodJSON.put("startDate", dateFormat.format(period.getStartDate()));
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

		JSONArray jsonCategories = JSONFactoryUtil.createJSONArray();
		for (AssetCategory category : this.getCategories()) {
			jsonCategories.put(category.getCategoryId());
		}
		if (jsonCategories.length() > 0) {
			jsonEvent.put("categories", jsonCategories);
		}

		JSONArray jsonThemes = JSONFactoryUtil.createJSONArray();
		for (AssetCategory category : this.getThemes()) {
			jsonThemes.put(category.getCategoryId());
		}
		if (jsonThemes.length() > 0) {
			jsonEvent.put("themes", jsonThemes);
		}

		JSONArray jsonTypes = JSONFactoryUtil.createJSONArray();
		for (AssetCategory category : this.getTypes()) {
			jsonTypes.put(category.getCategoryId());
		}
		if (jsonTypes.length() > 0) {
			jsonEvent.put("types", jsonTypes);
		}

		JSONArray jsonTerritories = JSONFactoryUtil.createJSONArray();
		for (AssetCategory category : this.getTerritories()) {
			jsonTerritories.put(category.getCategoryId());
		}
		if (jsonTerritories.length() > 0) {
			jsonEvent.put("territories", jsonTerritories);
		}

		JSONArray jsonPublics = JSONFactoryUtil.createJSONArray();
		for (AssetCategory category : this.getPublics()) {
			jsonPublics.put(category.getCategoryId());
		}
		if (jsonPublics.length() > 0) {
			jsonEvent.put("publics", jsonPublics);
		}

		JSONArray jsonServices = JSONFactoryUtil.createJSONArray();
		for (AssetCategory category : this.getServices()) {
			jsonServices.put(category.getCategoryId());
		}
		if (jsonServices.length() > 0) {
			jsonEvent.put("services", jsonServices);
		}

		return jsonEvent;
	}

	private Map<Locale, LegacyPlace> locale_legacyPlace;

}