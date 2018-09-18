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

import aQute.bnd.annotation.ProviderType;
import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import eu.strasbourg.service.agenda.model.Event;
import eu.strasbourg.service.agenda.service.EventLocalServiceUtil;
import eu.strasbourg.service.comment.model.Comment;
import eu.strasbourg.service.comment.service.CommentLocalServiceUtil;
import eu.strasbourg.service.like.model.Like;
import eu.strasbourg.service.like.service.LikeLocalServiceUtil;
import eu.strasbourg.service.project.model.Participation;
import eu.strasbourg.service.project.model.PlacitPlace;
import eu.strasbourg.service.project.service.PlacitPlaceLocalServiceUtil;
import eu.strasbourg.utils.AssetVocabularyHelper;
import eu.strasbourg.utils.FileEntryHelper;
import eu.strasbourg.utils.StringHelper;
import eu.strasbourg.utils.constants.VocabularyNames;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static eu.strasbourg.service.project.constants.ParticiperCategories.FINISHED;
import static eu.strasbourg.service.project.constants.ParticiperCategories.IN_PROGRESS;
import static eu.strasbourg.service.project.constants.ParticiperCategories.NEW;
import static eu.strasbourg.service.project.constants.ParticiperCategories.SOON_ARRIVED;
import static eu.strasbourg.service.project.constants.ParticiperCategories.SOON_FINISHED;

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
	 * Retourne la liste des like/dislike de l'entité
	 * @see eu.strasbourg.service.like.model.LikeType
	 */
	@Override
	public List<Like> getLikesDislikes() {
		return LikeLocalServiceUtil.getByEntityIdAndTypeId(
				this.getParticipationId(), 
				15);
	}
	
	/**
	 * Retourne la liste des likes de l'entité
	 *  @see eu.strasbourg.service.like.model.LikeType
	 */
	@Override
	public List<Like> getLikes() {
		return LikeLocalServiceUtil.getByEntityIdAndTypeIdAndIsDislike(
				this.getParticipationId(), 
				15, 
				false);
	}
	
	/**
	 * Retourne la liste des dislikes de l'entité
	 *  @see eu.strasbourg.service.like.model.LikeType
	 */
	@Override
	public List<Like> getDislikes() {
		return LikeLocalServiceUtil.getByEntityIdAndTypeIdAndIsDislike(
				this.getParticipationId(), 
				15, 
				true);
	}
	
	/**
	 * Retourne le nombre de likes/dislikes de l'entité
	 * @see eu.strasbourg.service.like.model.LikeType
	 */
	@Override
	public int getNbLikesDislikes() {
		return LikeLocalServiceUtil.getByEntityIdAndTypeId(
				this.getParticipationId(), 
				15).size();
	}
	
	/**
	 * Retourne le nombre de likes de l'entité
	 *  @see eu.strasbourg.service.like.model.LikeType
	 */
	@Override
	public int getNbLikes() {
		return LikeLocalServiceUtil.getByEntityIdAndTypeIdAndIsDislike(
				this.getParticipationId(), 
				15, 
				false).size();
	}
	
	/**
	 * Retourne le nombre de dislikes de l'entité
	 *  @see eu.strasbourg.service.like.model.LikeType
	 */
	@Override
	public int getNbDislikes() {
		return LikeLocalServiceUtil.getByEntityIdAndTypeIdAndIsDislike(
				this.getParticipationId(), 
				15, 
				true).size();
	}
	
	/**
	 * Peut apporter une reaction (commenter, liker, participer) a l'entite
	 */
	@Override
	public boolean isJudgeable() {
		AssetCategory status = this.getParticipationStatusCategory();
		
		if (status == null) {
			return false;
		} else if (StringHelper.compareIgnoringAccentuation(status.getTitle(Locale.FRENCH), "A venir")) {
			return false;
		} else if (StringHelper.compareIgnoringAccentuation(status.getTitle(Locale.FRENCH), "Terminee")) {
			return false;
		}
		
		return true;
	}
	
	/**
	 * Retourne les commentaires de l'entité
	 */
	@Override
	public List<Comment> getApprovedComments() {
		return CommentLocalServiceUtil.getByAssetEntry(
				this.getAssetEntry().getEntryId(),
				WorkflowConstants.STATUS_APPROVED);
	}
	
	/**
	 * Retourne le nombre de commentaires de l'entité
	 */
	@Override
	public int getNbApprovedComments() {
		return CommentLocalServiceUtil.getByAssetEntry(
				this.getAssetEntry().getEntryId(),
				WorkflowConstants.STATUS_APPROVED).size();
	}
	
	/**
	 * Retourne le label de 5 digits du nombre de commentaires de l'entité
	 */
	@Override
	public String getNbApprovedCommentsLabel() {
		// Transforme le numero en chaine de caractere
		String stringNum = Integer.toString(this.getNbApprovedComments());
		// Recupere le nombre de chiffre
		int nbDigits = stringNum.length();
		// Ajoute les zeros manquants avant la chaine
		stringNum = new String(new char[5 - nbDigits]).replace("\0", "0") + stringNum;
		return stringNum;
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
	public String getTypeCategoryColor() {
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
		List<AssetCategory> districts = getDistrictCategories();
		return AssetVocabularyHelper.getDistrictTitle(locale,districts);
	}
	
	/**
	 * Retourne une chaine des 'Thematics' sépararée d'un '-'
	 */
	@Override
	public String getThematicsLabel(Locale locale) {
		List<AssetCategory> thematics = this.getThematicCategories();
        String thematicTitle = AssetVocabularyHelper.getThematicTitle(locale, thematics);
        return thematicTitle;
	}

	/**
	 * Retourne le status de la participation
	 */
	@Override
	public AssetCategory getParticipationStatusCategory() {
		List<AssetCategory> listStatus = AssetVocabularyHelper.getAssetEntryCategoriesByVocabulary(this.getAssetEntry(),
				VocabularyNames.PLACIT_STATUS);
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
			return SOON_ARRIVED.getName();
		} 
		else if (todayDate.after(expirationDate)) {
			return FINISHED.getName();
		}
		else if (todayDate.after(expirationDateMinus)) {
			return SOON_FINISHED.getName();
		}
		else if (todayDate.before(publicationDatePlus)) {
			return NEW.getName();
		} 
		else {
			return IN_PROGRESS.getName();
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
	 * Retourne le label d'affichage détaillant le statut
	 */
	@Override
	public String getStatusDetailLabel() {
		String result = "";
		String participationStatus = this.getParticipationStatus();
		if (SOON_ARRIVED.getName().equals(participationStatus))
			result = "Commence dans " + this.getTodayPublicationDifferenceDays() + " jour(s)";
		else if (SOON_FINISHED.getName().equals(participationStatus))
			result = "Fin dans " + this.getTodayExpirationDifferenceDays() + "jour(s)";
		else if (FINISHED.getName().equals(participationStatus))
			result = "Finie";
		return result;
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
	 * Retourne la version JSON de l'entité
	 */
	@Override
	public JSONObject toJSON() {
		// Initialisation des variables tempons et résultantes
		JSONObject jsonParticipation = JSONFactoryUtil.createJSONObject();
		JSONArray jsonPlacitPlaces = JSONFactoryUtil.createJSONArray();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		
		// Champs de gestion
		jsonParticipation.put("id", this.getParticipationId());
		jsonParticipation.put("createDate", dateFormat.format(this.getCreateDate()));
		
		// Champs : Header
		jsonParticipation.put("title", this.getTitle());
		jsonParticipation.put("author", this.getAuthor());
		
		// Champs : Contact
		jsonParticipation.put("contactName", this.getContactName());
		jsonParticipation.put("contactLine1", this.getContactLine1());
		jsonParticipation.put("contactLine2", this.getContactLine2());
		jsonParticipation.put("contactPhoneNumber", this.getContactPhoneNumber());
		
		// Champs : Médias
		jsonParticipation.put("videoUrl", this.getVideoUrl());
		jsonParticipation.put("imageURL", this.getImageURL());
		jsonParticipation.put("mediaChoice", this.getMediaChoice());
		jsonParticipation.put("contactPhoneNumber", this.getContactPhoneNumber());
		
		// Champs : Description
		jsonParticipation.put("descriptionChapeau", this.getDescriptionChapeau());
		jsonParticipation.put("descriptionBody", this.getDescriptionBody());
		
		// Champs : Description
		jsonParticipation.put("consultationPlacesBody", this.getConsultationPlacesBody());
		
		// Champs : Dates
		jsonParticipation.put("publicationDate", this.getPublicationDate());
		jsonParticipation.put("expirationDate", this.getExpirationDate());
		
		// Champs : Intéractivités
		jsonParticipation.put("nbApprovedComments", this.getNbApprovedComments());
		jsonParticipation.put("nbLikes", this.getNbLikes());
		jsonParticipation.put("nbDislikes", this.getNbDislikes());
		
		// Label des vocabulaires
		AssetCategory projectCategory = this.getProjectCategory();
		AssetCategory statusCategory = this.getParticipationStatusCategory();
		AssetCategory typeCategory = this.getTypeCategory();
		
		jsonParticipation.put("districtsLabel", this.getDistrictLabel(Locale.FRENCH));
		jsonParticipation.put("thematicsLabel", this.getThematicsLabel(Locale.FRENCH));
		jsonParticipation.put("typeLabel", typeCategory != null ? typeCategory.getTitle(Locale.FRENCH) : "");
		jsonParticipation.put("typeColor", this.getTypeCategoryColor());
		jsonParticipation.put("projectName", projectCategory != null ? projectCategory.getTitle(Locale.FRENCH) : "");
		jsonParticipation.put("statusId", statusCategory != null ? statusCategory.getCategoryId() : "");
		jsonParticipation.put("statusCode", this.getParticipationStatus());
		jsonParticipation.put("statusLabel", statusCategory != null ? statusCategory.getTitle(Locale.FRENCH) : "");
		jsonParticipation.put("statusDetailLabel", this.getStatusDetailLabel());
		
		// Lieux placit
		for (PlacitPlace placitPlace : this.getPlacitPlaces()) {
			jsonPlacitPlaces.put(placitPlace.toJSON());
		}
		jsonParticipation.put("placitPlaces", jsonPlacitPlaces);
		
		// Liste des Ids des catégories Territoire
		JSONArray jsonTerritories = AssetVocabularyHelper.getExternalIdsJSONArray(this.getTerritoryCategories());
		if (jsonTerritories.length() > 0) {
			jsonParticipation.put("territories", jsonTerritories);
		}
		
		// Liste des Ids des catégories Thématiques
		JSONArray jsonThematics = AssetVocabularyHelper.getExternalIdsJSONArray(this.getThematicCategories());
		if (jsonThematics.length() > 0) {
			jsonParticipation.put("thematics", jsonThematics);
		}
		
		return jsonParticipation;
	}
	
	private final static Log log = LogFactoryUtil.getLog(ParticipationImpl.class);
	
}