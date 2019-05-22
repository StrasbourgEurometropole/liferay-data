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
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.search.BooleanClause;
import com.liferay.portal.kernel.search.BooleanClauseFactoryUtil;
import com.liferay.portal.kernel.search.BooleanClauseOccur;
import com.liferay.portal.kernel.search.BooleanQuery;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchContextFactory;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.search.generic.BooleanQueryImpl;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.util.DateFormatFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import aQute.bnd.annotation.ProviderType;
import eu.strasbourg.service.adict.AdictService;
import eu.strasbourg.service.adict.AdictServiceTracker;
import eu.strasbourg.service.agenda.model.Event;
import eu.strasbourg.service.agenda.model.EventParticipation;
import eu.strasbourg.service.agenda.model.EventPeriod;
import eu.strasbourg.service.agenda.model.Manifestation;
import eu.strasbourg.service.agenda.service.EventLocalServiceUtil;
import eu.strasbourg.service.agenda.service.EventParticipationLocalServiceUtil;
import eu.strasbourg.service.agenda.service.EventPeriodLocalServiceUtil;
import eu.strasbourg.service.agenda.service.ManifestationLocalServiceUtil;
import eu.strasbourg.service.comment.model.Comment;
import eu.strasbourg.service.comment.service.CommentLocalServiceUtil;
import eu.strasbourg.service.place.model.Place;
import eu.strasbourg.service.place.service.PlaceLocalServiceUtil;
import eu.strasbourg.utils.AssetVocabularyHelper;
import eu.strasbourg.utils.DateHelper;
import eu.strasbourg.utils.FileEntryHelper;
import eu.strasbourg.utils.JSONHelper;
import eu.strasbourg.utils.RodigueSOAPClient;
import eu.strasbourg.utils.StrasbourgPropsUtil;
import eu.strasbourg.utils.constants.VocabularyNames;
import eu.strasbourg.utils.models.RodrigueEventSession;

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

	private AdictService adictService;

	private AdictServiceTracker adictServiceTracker;

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. All methods that expect a event model
	 * instance should use the {@link eu.strasbourg.service.agenda.model.Event}
	 * interface instead.
	 */
	public EventImpl() {
	}

	/**
	 * Retourne l'AssetEntry rattaché cet item
	 */
	@Override
	public AssetEntry getAssetEntry() {
		return AssetEntryLocalServiceUtil.fetchEntry(Event.class.getName(), this.getEventId());
	}

	/**
	 * Renvoie la liste des AssetCategory rattachées à cet item (via l'assetEntry)
	 */
	@Override
	public List<AssetCategory> getCategories() {
		return AssetVocabularyHelper.getAssetEntryCategories(this.getAssetEntry());
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
		return ManifestationLocalServiceUtil.getEventManifestations(this.getEventId());
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
	 * Retourne la liste des périodes auxquelles l'événement à lieu (classées par
	 * date de début croissante)
	 */
	@Override
	public List<EventPeriod> getEventPeriods() {
		List<EventPeriod> periods = EventPeriodLocalServiceUtil.getByEventId(this.getEventId());
		List<EventPeriod> sortedPeriods = new ArrayList<EventPeriod>(periods);
		sortedPeriods.sort((p1, p2) -> p1.getStartDate().compareTo(p2.getStartDate()));
		return sortedPeriods;
	}

	/**
	 * Retourne la liste des périodes courantes et futures auxuqlles l'événement à
	 * lieu
	 */
	@Override
	public List<EventPeriod> getCurrentAndFuturePeriods() {
		List<EventPeriod> allPeriods = this.getEventPeriods();

		Calendar todayAtMidnight = new GregorianCalendar();
		todayAtMidnight.set(Calendar.HOUR_OF_DAY, 0);
		todayAtMidnight.set(Calendar.MINUTE, 0);
		todayAtMidnight.set(Calendar.SECOND, 0);
		todayAtMidnight.set(Calendar.MILLISECOND, 0);

		List<EventPeriod> currentAndFuturePeriods = allPeriods.stream()
				.filter(p -> p.getEndDate().compareTo(todayAtMidnight.getTime()) >= 0).collect(Collectors.toList());
		return currentAndFuturePeriods;
	}
	
	/**
	 * Retourne la période courrante, ou la prochaine
	 */
	@Override
	public EventPeriod getCurrentOrFuturePeriod() {
		return getCurrentAndFuturePeriods().isEmpty() ? null
				: getCurrentAndFuturePeriods().get(0);
	}

	/**
	 * Retourne la date de début de la future ou courante période de l'événement
	 */
	public Date getStartDateFirstCurrentAndFuturePeriod() {
		return getCurrentAndFuturePeriods().isEmpty() ? new Date(0)
				: getCurrentAndFuturePeriods().get(0).getStartDate();
	}

	/**
	 * Retourne la période principale de l'événement (de la première date de début à
	 * la dernière date de fin) sous forme de String dans la locale passée en
	 * paramètre
	 */
	@Override
	public String getEventScheduleDisplay(Locale locale) {
		return DateHelper.displayPeriod(this.getFirstStartDate(), this.getLastEndDate(), locale, true, false);
	}
	
	/**
	 * Retourne la période principale de l'événement (de la première date de début à
	 * la dernière date de fin) sous forme de String dans la locale passée en
	 * paramètre
	 */
	@Override
	public String getEventScheduleDisplay(Locale locale, boolean dispYear, boolean dispShortMonth) {
		return DateHelper.displayPeriod(this.getFirstStartDate(), this.getLastEndDate(), locale, dispYear, dispShortMonth);
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
		Event liveEvent = EventLocalServiceUtil.fetchEventByUuidAndGroupId(this.getUuid(), liveGroupId);
		return liveEvent;
	}

	/**
	 * Retourne l'objet "Place" correspondant au lieu de l'événement, s'il existe
	 */
	private Place place = null;

	private Place getPlace() {
		if (place == null && Validator.isNotNull(this.getPlaceSIGId())) {
			try {
				place = PlaceLocalServiceUtil.getPlaceBySIGId(this.getPlaceSIGId());
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return place;
	}

	/**
	 * Retourne l'id du lieu de l'événement s'il existe, 0 sinon
	 */
	@Override
	public long getPlaceId() {
		Place place = this.getPlace();
		return place == null ? 0 : place.getPlaceId();
	}

	/**
	 * Retourne le nom de la ville, provenant du lieu interne s'il existe, du lieu
	 * lié sinon
	 */
	@Override
	public String getCity(Locale locale) {
		return this.getPlace() == null ? super.getPlaceCity() : this.getPlace().getCity(locale);
	}

	/**
	 * Retourne le nom du lieu, provenant du lieu interne s'il existe, du lieu lié
	 * sinon
	 */
	@Override
	public String getPlaceAlias(Locale locale) {
		return this.getPlace() == null ? super.getPlaceName(locale) : this.getPlace().getAlias(locale);
	}

	/**
	 * Retourne l'adresse sans la ville
	 */
	@Override
	public String getPlaceAddress(Locale locale) {
		if (this.getPlace() == null) {
			return this.getPlaceStreetNumber() + " " + this.getPlaceStreetName();
		} else {
			return this.getPlace().getAddressStreet() + " " + this.getPlace().getAddressComplement();
		}
	}

	/**
	 * Retourne la ville
	 */
	@Override
	public String getPlaceCity(Locale locale) {
		return this.getPlace() == null ? super.getPlaceCity() : this.getPlace().getCity(locale);
	}

	/**
	 * Retourne le code postal
	 */
	@Override
	public String getPlaceZipCode() {
		return this.getPlace() == null ? super.getPlaceZipCode() : this.getPlace().getAddressZipCode();
	}

	/**
	 * Retourne l'adresse complete du lieu SIG ou "manuel"
	 */
	@Override
	public String getCompleteAddress(Locale locale) {
		return this.getPlaceStreetNumber() + " " + this.getPlaceStreetName() + ", " + this.getPlaceZipCode() + " "
				+ this.getCity(locale);
	}

	/**
	 * Retourne les coordonnees mercator en axe X (longitude)
	 */
	@Override
	public String getMercatorX() {
		if (this.getPlace() == null) {
			// Appel a Addict pour trouver les coordonnees selon l'adresse
			JSONArray coorResult = null;
			try {
				coorResult = getAdictService().getCoordinateForAddress(this.getCompleteAddress(Locale.FRENCH));
			} catch (Exception e) {
				e.printStackTrace();
			}

			return coorResult != null ? coorResult.get(0).toString() : "";
		} else {
			return this.getPlace().getMercatorX();
		}
	}

	/**
	 * Retourne les coordonnees mercator en axe Y (latitude)
	 */
	@Override
	public String getMercatorY() {
		if (this.getPlace() == null) {
			// Appel a Addict pour trouver les coordonnees selon l'adresse
			JSONArray coorResult = null;
			try {
				coorResult = getAdictService().getCoordinateForAddress(this.getCompleteAddress(Locale.FRENCH));
			} catch (Exception e) {
				e.printStackTrace();
			}

			return coorResult != null ? coorResult.get(1).toString() : "";
		} else {
			return this.getPlace().getMercatorY();
		}
	}

	/**
	 * Retourne les coordonnees mercator en axe X et Y Notes : permet de ne pas
	 * multiplier les appels
	 * 
	 * @return tableau [mercatorX, mercatorY] sinon tableau vide
	 */
	@Override
	public List<String> getMercators() {
		if (this.getPlace() == null) {
			// Appel a Addict pour trouver les coordonnees selon l'adresse
			JSONArray coorResult = null;
			try {
				coorResult = getAdictService().getCoordinateForAddress(this.getCompleteAddress(Locale.FRENCH));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (coorResult != null) {
				String mercatorX = coorResult.get(0).toString();
				String mercatorY = coorResult.get(1).toString();

				return Arrays.asList(mercatorX, mercatorY);
			} else {
				return new ArrayList<String>();
			}
		} else {
			return Arrays.asList(this.getPlace().getMercatorX(), this.getPlace().getMercatorY());
		}
	}

	/**
	 * Retourne la liste des participations de l'evenement
	 */
	@Override
	public List<EventParticipation> getEventParticipations() {
		return EventParticipationLocalServiceUtil.getByEventId(this.getEventId());
	}

	/**
	 * Retourne le nombre de participation a l'evenement
	 */
	@Override
	public int getNbEventParticipations() {
		return EventParticipationLocalServiceUtil.getByEventId(this.getEventId()).size();
	}

	/**
	 * Retourne les commentaires de l'entité
	 */
	@Override
	public List<Comment> getApprovedComments() {
		return CommentLocalServiceUtil.getByAssetEntry(this.getAssetEntry().getEntryId(),
				WorkflowConstants.STATUS_APPROVED);
	}

	/**
	 * Retourne le nombre de commentaires de l'entité
	 */
	@Override
	public int getNbApprovedComments() {
		return CommentLocalServiceUtil
				.getByAssetEntry(this.getAssetEntry().getEntryId(), WorkflowConstants.STATUS_APPROVED).size();
	}

	/**
	 * L'evenement est-il terminee ?
	 */
	@Override
	public boolean isFinished() {
		Date now = new Date();

		if (this.getLastEndDate() != null) {
			if (this.getLastEndDate().compareTo(now) > 1) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Retourne le label de 5 digits du nombre de participation a l'evenement
	 */
	@Override
	public String getNbEventParticipationsLabel() {
		// Transforme le numero en chaine de caractere
		String stringNum = Integer.toString(this.getNbEventParticipations());
		// Recupere le nombre de chiffre
		int nbDigits = stringNum.length();
		// Ajoute les zeros manquants avant la chaine
		stringNum = new String(new char[5 - nbDigits]).replace("\0", "0") + stringNum;

		return stringNum;
	}

	/**
	 * Retourne l'accès
	 */
	@Override
	public String getAccess(Locale locale) {
		return this.getPlace() == null ? super.getAccess(locale) : this.getPlace().getAccess(locale);
	}

	@Override
	public Boolean getAccessForBlind() {
		return this.getPlace() == null ? super.getAccessForBlind() : this.getPlace().getAccessForBlind();
	}

	@Override
	public Boolean getAccessForDeaf() {
		return this.getPlace() == null ? super.getAccessForDeaf() : this.getPlace().getAccessForDeaf();
	}

	@Override
	public Boolean getAccessForDeficient() {
		return this.getPlace() == null ? super.getAccessForDeficient() : this.getPlace().getAccessForDeficient();
	}

	@Override
	public Boolean getAccessForElder() {
		return this.getPlace() == null ? super.getAccessForElder() : this.getPlace().getAccessForElder();
	}

	@Override
	public Boolean getAccessForWheelchair() {
		return this.getPlace() == null ? super.getAccessForWheelchair() : this.getPlace().getAccessForWheelchair();
	}

	/**
	 * Retourne true si l'événement est accessible pour au moins un type de handicap
	 */
	@Override
	public boolean hasAnyAccessForDisabled() {
		return (this.getAccessForBlind() || this.getAccessForDeaf() || this.getAccessForWheelchair()
				|| this.getAccessForDeficient() || this.getAccessForElder());
	}

	/**
	 * Retourne l'adresse complète du lieu, provenant du lieu interne s'il existe,
	 * du lieu lié sinon
	 */
	@Override
	public String getPlaceAddressHTML(Locale locale) {
		if (Validator.isNotNull(this.getPlaceName())) {
			return this.getPlaceStreetNumber() + " " + this.getPlaceStreetName() + "<br>" + this.getPlaceZipCode() + " "
					+ this.getCity(locale);
		} else if (Validator.isNotNull(this.getPlace())) {
			Place place = this.getPlace();
			return place.getAddressStreet() + "<br>" + place.getAddressZipCode() + " " + place.getCity(locale);
		} else {
			return "";
		}
	}

	/**
	 * Retourne les types de l'événement
	 */
	@Override
	public List<AssetCategory> getTypes() {
		return AssetVocabularyHelper.getAssetEntryCategoriesByVocabulary(this.getAssetEntry(),
				VocabularyNames.EVENT_TYPE);
	}
	
	/**
	 * Retourne les typologie de l'événement (Catégorie du site de l'OPS)
	 */
	@Override
	public List<AssetCategory> getTypologies() {
		return AssetVocabularyHelper.getAssetEntryCategoriesByVocabulary(this.getAssetEntry(),
				VocabularyNames.OPS_TYPOLOGIE);
	}
	
	/**
	 * Retourne le label des typologies de l'événement (Catégorie du site de l'OPS)
	 */
	@Override
	public String getLabelTypologies(Locale locale) {
		String typos = "";
		for (AssetCategory typo : this.getTypologies()) {
			if (typos.length() > 0) {
				typos += " - ";
			}
			typos += typo.getTitle(locale);
		}
		return typos;
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
		return AssetVocabularyHelper.getAssetEntryCategoriesByVocabulary(this.getAssetEntry(),
				VocabularyNames.EVENT_THEME);
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
		return AssetVocabularyHelper.getAssetEntryCategoriesByVocabulary(this.getAssetEntry(),
				VocabularyNames.EVENT_PUBLIC);
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
		return AssetVocabularyHelper.getAssetEntryCategoriesByVocabulary(this.getAssetEntry(),
				VocabularyNames.TERRITORY);
	}

	/**
	 * Retourne le label des territoires de l'événement
	 */
	@Override
	public String getTerritoryLabel(Locale locale) {
		List<AssetCategory> districts = getTerritories();
		return AssetVocabularyHelper.getDistrictTitle(locale, districts);
	}

	/**
	 * Retourne les territoires de l'événement
	 */
	@Override
	public List<AssetCategory> getServices() {
		return AssetVocabularyHelper.getAssetEntryCategoriesByVocabulary(this.getAssetEntry(),
				VocabularyNames.EVENT_SERVICE);
	}

	@Override
	public LocalDate getNextOpenDate() {
		if (eventIsHappeningToday()) {
			return LocalDate.now();
		} else {
			for (EventPeriod period : this.getEventPeriods()) {
				LocalDate startDate = period.getStartDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
				if (startDate.isAfter(LocalDate.now())) {
					return startDate;
				}
			}
		}
		return LocalDate.MAX;
	}

	private boolean eventIsHappeningToday() {
		LocalDate today = LocalDate.now(ZoneId.of("Europe/Berlin"));
		for (EventPeriod period : this.getEventPeriods()) {
			LocalDate startDate = period.getStartDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			LocalDate endDate = period.getEndDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			endDate = endDate.plusDays(1);
			if (today.isAfter(startDate) && today.isBefore(endDate) || today.isEqual(startDate)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Recupere le service du module Adict sans passer par reference
	 */
	private AdictService getAdictService() {
		if (adictService == null) {
			adictServiceTracker = new AdictServiceTracker(this);
			adictServiceTracker.open();
			adictService = adictServiceTracker.getService();
		}
		return adictService;
	}

	/**
	 * Demande si l'utilisateur demandé participe à l'événement
	 */
	@Override
	public boolean isUserParticipates(String publikUserId) {
		if (!publikUserId.isEmpty()) {
			if (EventParticipationLocalServiceUtil.getByPublikUserIdAndEventId(publikUserId, this.getEventId()) != null)
				return true;
		}
		return false;
	}

	/**
	 * Retourne la version JSON de l'événenement
	 */
	@Override
	public JSONObject toJSON() {
		JSONObject jsonEvent = JSONFactoryUtil.createJSONObject();

		jsonEvent.put("id", this.getEventId());
		jsonEvent.put("externalId", this.getIdSource());

		jsonEvent.put("title", JSONHelper.getJSONFromI18nMap(this.getTitleMap()));

		if (Validator.isNotNull(this.getSubtitle())) {
			jsonEvent.put("subtitle", JSONHelper.getJSONFromI18nMap(this.getSubtitleMap()));
		}

		Map<Locale, String> descriptionMap = this.getDescriptionMap();
		Map<Locale, String> descriptionWithNewURLsMap = new HashMap<Locale, String>();
		for (Map.Entry<Locale, String> descriptionEntry : descriptionMap.entrySet()) {
			String description = descriptionEntry.getValue().replace("\"/documents/",
					"\"" + StrasbourgPropsUtil.getURL() + "/documents/");
			descriptionWithNewURLsMap.put(descriptionEntry.getKey(), description);
		}
		jsonEvent.put("description", JSONHelper.getJSONFromI18nMap(descriptionWithNewURLsMap));

		String imageURL = this.getImageURL();
		if (!imageURL.startsWith("http")) {
			imageURL = StrasbourgPropsUtil.getURL() + this.getImageURL();
		}
		jsonEvent.put("imageURL", imageURL);

		jsonEvent.put("imageCopyright", this.getImageCopyright(Locale.getDefault()));

		if (Validator.isNotNull(this.getPlaceSIGId())) {
			jsonEvent.put("placeSIGId", this.getPlaceSIGId());
		} else {
			JSONObject jsonPlace = JSONFactoryUtil.createJSONObject();
			jsonPlace.put("name", JSONHelper.getJSONFromI18nMap(this.getPlaceNameMap()));
			jsonPlace.put("streetNumber", this.getPlaceStreetNumber());
			jsonPlace.put("streetName", this.getPlaceStreetName());
			jsonPlace.put("zipCode", this.getPlaceZipCode());
			jsonPlace.put("city", this.getPlaceCity());
			jsonPlace.put("access", JSONHelper.getJSONFromI18nMap(this.getAccessMap()));
			jsonPlace.put("accessForDisabled", JSONHelper.getJSONFromI18nMap(this.getAccessForDisabledMap()));
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
			jsonEvent.put("websiteURL", JSONHelper.getJSONFromI18nMap(this.getWebsiteURLMap()));
		}

		if (Validator.isNotNull(this.getWebsiteName())) {
			jsonEvent.put("websiteName", JSONHelper.getJSONFromI18nMap(this.getWebsiteNameMap()));
		}

		jsonEvent.put("freeEntry", this.getFree());

		if (Validator.isNotNull(this.getPrice())) {
			jsonEvent.put("price", JSONHelper.getJSONFromI18nMap(this.getPriceMap()));
		}

		JSONArray periodsJSON = JSONFactoryUtil.createJSONArray();
		for (EventPeriod period : this.getEventPeriods()) {
			JSONObject periodJSON = JSONFactoryUtil.createJSONObject();

			DateFormat dateFormat = DateFormatFactoryUtil.getSimpleDateFormat("yyyy-MM-dd");
			periodJSON.put("startDate", dateFormat.format(period.getStartDate()));
			periodJSON.put("endDate", dateFormat.format(period.getEndDate()));

			if (Validator.isNotNull(period.getTimeDetail())) {
				periodJSON.put("timeDetail", JSONHelper.getJSONFromI18nMap(period.getTimeDetailMap()));
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

		JSONArray jsonCategories = AssetVocabularyHelper.getExternalIdsJSONArray(this.getCategories());
		if (jsonCategories.length() > 0) {
			jsonEvent.put("categories", jsonCategories);
		}

		JSONArray jsonThemes = AssetVocabularyHelper.getExternalIdsJSONArray(this.getThemes());
		if (jsonThemes.length() > 0) {
			jsonEvent.put("themes", jsonThemes);
		}

		JSONArray jsonTypes = AssetVocabularyHelper.getExternalIdsJSONArray(this.getTypes());
		if (jsonTypes.length() > 0) {
			jsonEvent.put("types", jsonTypes);
		}

		JSONArray jsonTerritories = AssetVocabularyHelper.getExternalIdsJSONArray(this.getTerritories());
		if (jsonTerritories.length() > 0) {
			jsonEvent.put("territories", jsonTerritories);
		}

		JSONArray jsonPublics = AssetVocabularyHelper.getExternalIdsJSONArray(this.getPublics());
		if (jsonPublics.length() > 0) {
			jsonEvent.put("publics", jsonPublics);
		}

		JSONArray jsonServices = AssetVocabularyHelper.getExternalIdsJSONArray(this.getServices());
		if (jsonServices.length() > 0) {
			jsonEvent.put("services", jsonServices);
		}

		jsonEvent.put("eventURL", StrasbourgPropsUtil.getAgendaDetailURL() + "/-/entity/id/" + this.getEventId());

		List<String> mercators = this.getMercators();
		jsonEvent.put("mercatorX", mercators.size() == 2 ? mercators.get(0) : 0);
		jsonEvent.put("mercatorY", mercators.size() == 2 ? mercators.get(1) : 0);
		
		jsonEvent.put("firstDate", getCurrentOrFuturePeriodStringDate());
		jsonEvent.put("completeAddress", this.getCompleteAddress(Locale.FRENCH));
		jsonEvent.put("nbPart", this.getNbEventParticipations());

		return jsonEvent;

	}

	/**
	 * Retourne la version JSON de l'événenement avec la participation ou non d'un
	 * utilisateur potentiel en incluant l'escape des caractères / balises pouvant
	 * casser l'utilisation des données et le split de l'HTML en général
	 */
	@Override
	public JSONObject toJSON(String publikUserID) {
		JSONObject jsonEvent = JSONFactoryUtil.createJSONObject();

		jsonEvent.put("id", this.getEventId());
		jsonEvent.put("externalId", this.getIdSource());

		// Ce truc imbitable utilisant le meilleur de ce que propose Java 8
		// vous propose d'appliquer la fonction escape à l'ensemble de la map
		jsonEvent.put("title", JSONHelper.getJSONFromI18nMap(this.getTitleMap().entrySet().stream()
				.collect(Collectors.toMap(Entry::getKey, e -> HtmlUtil.stripHtml(HtmlUtil.escape(e.getValue()))))));

		// Meme procédé
		if (Validator.isNotNull(this.getSubtitle())) {
			jsonEvent.put("subtitle", JSONHelper.getJSONFromI18nMap(this.getSubtitleMap().entrySet().stream()
					.collect(Collectors.toMap(Entry::getKey, e -> HtmlUtil.stripHtml(HtmlUtil.escape(e.getValue()))))));
		}

		Map<Locale, String> descriptionMap = this.getDescriptionMap();
		Map<Locale, String> descriptionWithNewURLsMap = new HashMap<Locale, String>();
		for (Map.Entry<Locale, String> descriptionEntry : descriptionMap.entrySet()) {
			String description = descriptionEntry.getValue().replace("\"/documents/",
					"\"" + StrasbourgPropsUtil.getURL() + "/documents/");
			description = HtmlUtil.stripHtml(HtmlUtil.escape(description));
			descriptionWithNewURLsMap.put(descriptionEntry.getKey(), description);
		}

		jsonEvent.put("description", JSONHelper.getJSONFromI18nMap(descriptionWithNewURLsMap));

		String imageURL = this.getImageURL();
		if (!imageURL.startsWith("http")) {
			imageURL = StrasbourgPropsUtil.getURL() + this.getImageURL();
		}
		jsonEvent.put("imageURL", imageURL);

		jsonEvent.put("imageCopyright",
				HtmlUtil.stripHtml(HtmlUtil.escape(this.getImageCopyright(Locale.getDefault()))));

		if (Validator.isNotNull(this.getPlaceSIGId())) {
			jsonEvent.put("placeSIGId", this.getPlaceSIGId());
		} else {
			JSONObject jsonPlace = JSONFactoryUtil.createJSONObject();
			jsonPlace.put("name", JSONHelper.getJSONFromI18nMap(this.getPlaceNameMap().entrySet().stream()
					.collect(Collectors.toMap(Entry::getKey, e -> HtmlUtil.stripHtml(HtmlUtil.escape(e.getValue()))))));
			jsonPlace.put("streetNumber", this.getPlaceStreetNumber());
			jsonPlace.put("streetName", HtmlUtil.stripHtml(HtmlUtil.escape(this.getPlaceStreetName())));
			jsonPlace.put("zipCode", this.getPlaceZipCode());
			jsonPlace.put("city", HtmlUtil.stripHtml(HtmlUtil.escape(this.getPlaceCity())));
			jsonPlace.put("access", JSONHelper.getJSONFromI18nMap(this.getAccessMap().entrySet().stream()
					.collect(Collectors.toMap(Entry::getKey, e -> HtmlUtil.stripHtml(HtmlUtil.escape(e.getValue()))))));
			jsonPlace.put("accessForDisabled",
					JSONHelper.getJSONFromI18nMap(this.getAccessForDisabledMap().entrySet().stream().collect(
							Collectors.toMap(Entry::getKey, e -> HtmlUtil.stripHtml(HtmlUtil.escape(e.getValue()))))));
			jsonPlace.put("accessForBlind", this.getAccessForBlind());
			jsonPlace.put("accessForDeaf", this.getAccessForDeaf());
			jsonPlace.put("accessForWheelchair", this.getAccessForWheelchair());
			jsonPlace.put("accessForDeficient", this.getAccessForDeficient());
			jsonPlace.put("accessForElder", this.getAccessForElder());
			jsonEvent.put("place", jsonPlace);
		}

		if (Validator.isNotNull(this.getPromoter())) {
			jsonEvent.put("promoter", HtmlUtil.stripHtml(HtmlUtil.escape(this.getPromoter())));
		}

		if (Validator.isNotNull(this.getPhone())) {
			jsonEvent.put("phone", this.getPhone());
		}

		if (Validator.isNotNull(this.getEmail())) {
			jsonEvent.put("mail", this.getEmail());
		}

		if (Validator.isNotNull(this.getWebsiteURL())) {
			jsonEvent.put("websiteURL", JSONHelper.getJSONFromI18nMap(this.getWebsiteURLMap()));
		}

		if (Validator.isNotNull(this.getWebsiteName())) {
			jsonEvent.put("websiteName",
					JSONHelper.getJSONFromI18nMap(this.getWebsiteNameMap().entrySet().stream().collect(Collectors
							.toMap(Entry::getKey, e -> HtmlUtil.stripHtml(HtmlUtil.escape((e.getValue())))))));
		}

		jsonEvent.put("freeEntry", this.getFree());

		if (Validator.isNotNull(this.getPrice())) {
			jsonEvent.put("price", JSONHelper.getJSONFromI18nMap(this.getPriceMap().entrySet().stream()
					.collect(Collectors.toMap(Entry::getKey, e -> HtmlUtil.stripHtml(HtmlUtil.escape(e.getValue()))))));
		}

		JSONArray periodsJSON = JSONFactoryUtil.createJSONArray();
		for (EventPeriod period : this.getEventPeriods()) {
			JSONObject periodJSON = JSONFactoryUtil.createJSONObject();

			DateFormat dateFormat = DateFormatFactoryUtil.getSimpleDateFormat("yyyy-MM-dd");
			periodJSON.put("startDate", dateFormat.format(period.getStartDate()));
			periodJSON.put("endDate", dateFormat.format(period.getEndDate()));

			if (Validator.isNotNull(period.getTimeDetail())) {
				periodJSON.put("timeDetail", JSONHelper.getJSONFromI18nMap(period.getTimeDetailMap()));
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

		JSONArray jsonCategories = AssetVocabularyHelper.getExternalIdsJSONArray(this.getCategories());
		if (jsonCategories.length() > 0) {
			jsonEvent.put("categories", jsonCategories);
		}

		JSONArray jsonThemes = AssetVocabularyHelper.getExternalIdsJSONArray(this.getThemes());
		if (jsonThemes.length() > 0) {
			jsonEvent.put("themes", jsonThemes);
		}

		JSONArray jsonTypes = AssetVocabularyHelper.getExternalIdsJSONArray(this.getTypes());
		if (jsonTypes.length() > 0) {
			jsonEvent.put("types", jsonTypes);
		}

		JSONArray jsonTerritories = AssetVocabularyHelper.getExternalIdsJSONArray(this.getTerritories());
		if (jsonTerritories.length() > 0) {
			jsonEvent.put("territories", jsonTerritories);
		}

		JSONArray jsonPublics = AssetVocabularyHelper.getExternalIdsJSONArray(this.getPublics());
		if (jsonPublics.length() > 0) {
			jsonEvent.put("publics", jsonPublics);
		}

		JSONArray jsonServices = AssetVocabularyHelper.getExternalIdsJSONArray(this.getServices());
		if (jsonServices.length() > 0) {
			jsonEvent.put("services", jsonServices);
		}
		
		jsonEvent.put("eventURL", StrasbourgPropsUtil.getAgendaDetailURL() + "/-/entity/id/" + this.getEventId());

		List<String> mercators = this.getMercators();
		jsonEvent.put("mercatorX", mercators.size() == 2 ? mercators.get(0) : 0);
		jsonEvent.put("mercatorY", mercators.size() == 2 ? mercators.get(1) : 0);
		
		jsonEvent.put("firstDate",  getCurrentOrFuturePeriodStringDate());
		jsonEvent.put("completeAddress", HtmlUtil.stripHtml(HtmlUtil.escape(this.getCompleteAddress(Locale.FRENCH))));
		jsonEvent.put("nbPart", this.getNbEventParticipations());

		jsonEvent.put("isUserPart", this.isUserParticipates(publikUserID));

		return jsonEvent;
	}
	
	@Override
	public String getCurrentOrFuturePeriodStringDate() {
		String date = "";
		if(this.getCurrentOrFuturePeriod() != null) {
			SimpleDateFormat df = new SimpleDateFormat("dd MMMM yyyy");
			date = "Le " + df.format(this.getCurrentOrFuturePeriod().getStartDate());
			
			if(this.getCurrentOrFuturePeriod().getTimeDetail() != "")
				date = date + " &agrave; " + this.getCurrentOrFuturePeriod().getTimeDetail();
		}
		return date;
	}

	/**
	 * Retourne X suggestions max pour un événement
	 *
	 * @param request  la requete
	 * @param nbSuggestions le nombre de suggestions.
	 * @param tag le tag a appliquer
	 * @param category la categorie a appliquer
	 * @return la liste d'événements.
	 */
	@Override
	public List<Event> getSuggestions(HttpServletRequest request, int nbSuggestions, String tag, String category) throws SearchException, PortalException {
		
		List<Event> suggestions = new ArrayList<>();
		
		try {
			//Récupération du scope de recherche global
			long globalGroupId = GroupLocalServiceUtil.getCompanyGroup(PortalUtil.getDefaultCompanyId()).getGroupId(); 
			
			//Initialisation du seachContext
			SearchContext searchContext = SearchContextFactory.getInstance(request);
			searchContext.setGroupIds(new long[] {globalGroupId});
			searchContext.setStart(0);
			searchContext.setEnd(nbSuggestions);
			
			//utilisation de l'indexer de l'entite event (Permet de rechercher uniquement des event)
			Indexer indexer = IndexerRegistryUtil.getIndexer(Event.class.getName());
			
			//création de la query avec des filtre sur les entités publiées uniquement
			BooleanQuery query = new BooleanQueryImpl();
			query.addRequiredTerm(Field.STATUS, WorkflowConstants.STATUS_APPROVED);
			query.addRequiredTerm("visible", true);
			
			if(tag != null && !tag.equals("")) {
				//Ajout du filtre sur le tag, si présent
				BooleanQuery tagQuery = new BooleanQueryImpl();
				tagQuery.addExactTerm(Field.ASSET_TAG_NAMES, tag);
				query.add(tagQuery, BooleanClauseOccur.MUST);
			}
			
			List<AssetCategory> categories = new ArrayList<AssetCategory>();
			
			if(category.equals("theme"))
				categories = this.getThemes();
			else if(category.equals("typologie"))
				categories = this.getTypologies();
			
			//La suggestion se fait uniquement sur la même catégorie "thème"
			for (AssetCategory cat : categories) {
				BooleanQuery categoryQuery = new BooleanQueryImpl();
				categoryQuery.addRequiredTerm(Field.ASSET_CATEGORY_IDS, String.valueOf(cat.getCategoryId()));
				query.add(categoryQuery, BooleanClauseOccur.MUST);
			}
			
			BooleanClause booleanClause = BooleanClauseFactoryUtil.create(query, BooleanClauseOccur.MUST.getName());
			searchContext.setBooleanClauses(new BooleanClause[] {booleanClause});
			
			//Lance la recherche elasticSearch
		    Hits hits = indexer.search(searchContext);
			
		    //Generation de notre liste de suggestion
		    for (Document document : hits.getDocs()) {
		    	//récupération de l'élément en base
	            Event event = EventLocalServiceUtil.fetchEvent(GetterUtil.getLong(document.get(Field.ENTRY_CLASS_PK)));
	            
	            //Vérification que l'event recherché existe bien base (En théorie ne devrait pas arriver) et qu'il est différent de l'event courrant.
	            if(event != null && event.getEventId() != this.getEventId())
	            	suggestions.add(event);
	        }
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	    
		return suggestions;
	}
	
	/**
	 * Renvoi les sessions de l'evenement obtenues par le webService Rodrigue
	 * @return
	 */
	@Override
	public List<RodrigueEventSession> getSessionsFromRodrigue() {
		if (this.getConcertId() != null && !this.getConcertId().isEmpty()) {
			return RodigueSOAPClient.getSessionListOfEvent(this.getConcertId());
		} else {
			return new ArrayList<RodrigueEventSession>();
		}
	}
	
	/**
	 * Renvoi les sessions de l'evenement obtenues par le webService Rodriguesous format JSON
	 * @return
	 */
	@Override
	public JSONArray getSessionsFromRodrigueInJSON() {
		JSONArray sessionsJSON = JSONFactoryUtil.createJSONArray();
		
		for(RodrigueEventSession session : this.getSessionsFromRodrigue()) {
			JSONObject sessionJSON = JSONFactoryUtil.createJSONObject();
			
			sessionJSON.put("eventID", session.getEventID());
			sessionJSON.put("eventName", session.getEventName());
			sessionJSON.put("eventCode", session.getEventCode());
			sessionJSON.put("eventDescription1", session.getEventDescription1());
			sessionJSON.put("eventDescription2", session.getEventDescription2());
			sessionJSON.put("eventDescription3", session.getEventDescription3());
			sessionJSON.put("sessionID", session.getSessionID());
			sessionJSON.put("sessionDate", session.getSessionDate());
			sessionJSON.put("placeID", session.getPlaceID());
			sessionJSON.put("placeName", session.getPlaceName());
			sessionJSON.put("placeCode", session.getPlaceCode());
			sessionJSON.put("nbSeat", session.getNbSeat());
			sessionJSON.put("nbSeatMin", session.getNbSeatMin());
			sessionJSON.put("nbSeatMax", session.getNbSeatMax());
			
			sessionsJSON.put(sessionJSON);
		}
		
		return sessionsJSON;
	}

}