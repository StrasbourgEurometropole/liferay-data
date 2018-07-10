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

package eu.strasbourg.service.project.model.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;

import aQute.bnd.annotation.ProviderType;
import eu.strasbourg.service.agenda.model.Event;
import eu.strasbourg.service.agenda.service.EventLocalServiceUtil;
import eu.strasbourg.service.project.model.Participation;
import eu.strasbourg.service.project.model.PlacitPlace;
import eu.strasbourg.service.project.service.PlacitPlaceLocalServiceUtil;
import eu.strasbourg.utils.AssetVocabularyHelper;
import eu.strasbourg.utils.FileEntryHelper;
import eu.strasbourg.utils.constants.VocabularyNames;

/**
 * The extended model implementation for the Participation service. Represents a row in the &quot;project_Participation&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * Helper methods and all application logic should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link eu.strasbourg.service.project.model.Participation} interface.
 * </p>
 *
 * @author Cedric Henry
 */
@ProviderType
public class ParticipationImpl extends ParticipationBaseImpl {

	private static final long serialVersionUID = 1311330918138728472L;

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. All methods that expect a participation model instance should use the {@link eu.strasbourg.service.project.model.Participation} interface instead.
	 */
	public ParticipationImpl() {
	}
	
	/**
	 * Retourne l'AssetEntry rattaché cet item
	 */
	@Override
	public AssetEntry getAssetEntry() {
		return AssetEntryLocalServiceUtil.fetchEntry(Participation.class.getName(),
			this.getParticipationId());
	}

	/**
	 * Retourne la liste des événements liés à la participation
	 */
	@Override
	public List<Event> getEvents() {
		List<Event> events = new ArrayList<Event>();
		for (String eventIdsStr : this.getEventsIds().split(",")) {
			Long eventId = GetterUtil.getLong(eventIdsStr);
			Event event = EventLocalServiceUtil.fetchEvent(eventId);
			if (event != null && event.getAssetEntry().isVisible()) {
				events.add(event);
			}
		}
		return events;
	}
	
	/**
	 * Retourne la liste des lieux placit liés à la participation
	 */
	@Override
	public List<PlacitPlace> getPlacitPlaces() {
		return PlacitPlaceLocalServiceUtil.getByParticipation(this.getParticipationId());
	}

	/**
	 * Retourne les noms des lieux placit de la participation
	 */
	@Override
	public List<String> getPlaceNames(Locale locale) {
		List<PlacitPlace> placitPlaces = this.getPlacitPlaces();
		return placitPlaces.stream().map(c -> c.getPlaceAlias(locale)).distinct().collect(Collectors.toList());
	}

	/**
	 * Retourne les ids SIG des lieux placit de la participation
	 */
	@Override
	public List<String> getPlaceSIGIds(Locale locale) {
		List<PlacitPlace> placitPlaces = this.getPlacitPlaces();
		return placitPlaces.stream().map(c -> c.getPlaceSIGId()).distinct().collect(Collectors.toList());
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
	 * Retourne le type de la participation (
	 */
	@Override
	public AssetCategory getTypeCategory() {
		return AssetVocabularyHelper.getAssetEntryCategoriesByVocabulary(this.getAssetEntry(),
				VocabularyNames.PARTICIPATION_TYPE).get(0);
	}
	
	/**
	 * Retourne le projet de la participation (
	 */
	@Override
	public AssetCategory getProjectCategory() {
		return AssetVocabularyHelper.getAssetEntryCategoriesByVocabulary(this.getAssetEntry(),
				VocabularyNames.PROJECT).get(0);
	}
	
	/**
	 * Retourne la couleur hexa du type de la participation contenu dans la propriete
	 * 'code_color' de la categorie associee
	 */
	@Override
	public String getProjectCategoryColor() {
		long categoryId = this.getTypeCategory().getCategoryId();
		return AssetVocabularyHelper.getCategoryProperty(categoryId, "color_code");
	}
	
	/**
	 * Retourne les thematiques de la participation (
	 */
	@Override
	public List<AssetCategory> getThematicCategories() {
		return AssetVocabularyHelper.getAssetEntryCategoriesByVocabulary(this.getAssetEntry(),
				VocabularyNames.THEMATIC);
	}
	
	/**
	 * Retourne les catégories 'Territoire' correspondant aux pays de la participation
	 */
	@Override
	public List<AssetCategory> getTerritoryCategories() {
		return AssetVocabularyHelper.getAssetEntryCategoriesByVocabulary(this.getAssetEntry(),
				VocabularyNames.TERRITORY);
	}
	
	/**
	 * Retourne les sous-catégories 'Territoire' correspondant aux villes de la participation
	 * @return : null si vide, sinon la liste des catégories 
	 */
	@Override
	public List<AssetCategory> getCityCategories() {
		List<AssetCategory> territories = this.getTerritoryCategories();
		List<AssetCategory> cities = new ArrayList<AssetCategory>();
		for (AssetCategory territory : territories) {
			try {
				if (territory.getAncestors().size() == 1) {
					cities.add(territory);
				}
			} catch (PortalException e) {
				continue;
			}
		}
		return cities;
	}

	/**
	 * Retourne les sous-sous-catégories 'Territoire' correspondant aux quartiers de la participation
	 * @return : null si vide, sinon la liste des catégories 
	 */
	@Override
	public List<AssetCategory> getDistrictCategories() {
		List<AssetCategory> territories = this.getTerritoryCategories();
		List<AssetCategory> districts = new ArrayList<AssetCategory>();
		for (AssetCategory territory : territories) {
			try {
				if (territory.getAncestors().size() == 2) {
					districts.add(territory);
				}
			} catch (PortalException e) {
				continue;
			}
		}
		return districts;
	}

	/**
	 * Retourne une chaine des 'Territoires' correspondant aux quartiers de la participation
	 * @return : Chaine des quartiers ou description "Aucun" ou "Tous"
	 */
	@Override
	public String getDistrictLabel(Locale locale) {
		StringBuilder result = new StringBuilder();
		List<AssetCategory> districts = getDistrictCategories();
		if (districts==null || districts.isEmpty()){
			result.append("Aucun quartier");
		} else if (AssetVocabularyHelper.isAllDistrict(districts.size())){
			result.append("Tous les quartiers");
		} else {
		    result.append(districts.stream()
                    .map(district -> district.getTitle(locale))
                    .collect(Collectors.joining(" - ")));
		}
		return result.toString();
	}

	/**
	 * Retourne le status de la participation
	 */
	@Override
	public AssetCategory getParticipationStatusCategory() {
		List<AssetCategory> listStatus = AssetVocabularyHelper.getAssetEntryCategoriesByVocabulary(this.getAssetEntry(),
				VocabularyNames.PARTICIPATION_STATUS);
		return listStatus.size() > 0 ? listStatus.get(0) : null;
	}
	
	/**
	 * Retourne le status de la participation selon la temporalité actuelle 
	 * @return le status suivant l'ordre :
	 * 		[soon_arrived] : date du jour antérieur à la date de publication
	 * 		[new] : 7 jour après la publication
	 * 		[in_progress] : toute la durée de la période de participation
	 * 		[soon_finished] : 7 jours avant l'expiration
	 * 		[finished] : date du jour postérieur à la date d'expiration
	 */
	@Override
	public String getParticipationStatus() {
		// Instanciation des variables
		Date todayDate = new Date();
		Date publicationDate = this.getPublicationDate();
		Date publicationDatePlus = this.getPublicationDate();
		Date expirationDate = this.getExpirationDate();
		Date expirationDateMinus = this.getExpirationDate();
		
		// Operation sur les ajouts et retraits de jours
		Calendar cal = Calendar.getInstance();
		
		// ... sur la date de publication
		cal.setTime(publicationDatePlus);
		cal.add(Calendar.DATE, 7);
		publicationDatePlus = cal.getTime();
		
		// ... sur la date d'expiration
		cal.setTime(expirationDateMinus);
		cal.add(Calendar.DATE, -7);
		expirationDateMinus = cal.getTime();
		
		if (todayDate.before(publicationDate)) {
			return "soon_arrived";
		} 
		else if (todayDate.after(expirationDate)) {
			return "finished";
		}
		else if (todayDate.after(expirationDateMinus)) {
			return "soon_finished";
		}
		else if (todayDate.before(publicationDatePlus)) {
			return "new";
		} 
		else {
			return "in_progress";
		}
	}
	
	/**
	 * Calcul la différence de jours entre la date du jour et celle de publication
	 */
	@Override
	public int getTodayPublicationDifferenceDays () {
		// Instanciation des variables
		Date todayDate = new Date();
		Date publicationDate = this.getPublicationDate();
		
		// Calcul du nombre de millisecondes entre les deux dates et
		// conversion en nombre de jours
		long diff = publicationDate.getTime() - todayDate.getTime();
	    return (int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
	}
	
	/**
	 * Calcul la différence de jours entre la date du jour et celle d'expiration
	 */
	@Override
	public int getTodayExpirationDifferenceDays () {
		// Instanciation des variables
		Date todayDate = new Date();
		Date expirationDate = this.getExpirationDate();
		
		// Calcul du nombre de millisecondes entre les deux dates et
		// conversion en nombre de jours
		long diff = expirationDate.getTime() - todayDate.getTime();
	    return (int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
	}
	
	/**
	 * Retourne la liste des URLs des documents
	 */
	@Override
	public List<String> getFilesURLs() {
		List<String> URLs = new ArrayList<String>();
		for (String fileIdStr : this.getFilesIds().split(",")) {
			Long fileId = GetterUtil.getLong(fileIdStr);
			if (Validator.isNotNull(fileId)) {
				String fileURL = FileEntryHelper.getFileEntryURL(fileId);
				URLs.add(fileURL);
			}
		}
		return URLs;
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
	
	private final static Log log = LogFactoryUtil.getLog(ParticipationImpl.class);
	
}