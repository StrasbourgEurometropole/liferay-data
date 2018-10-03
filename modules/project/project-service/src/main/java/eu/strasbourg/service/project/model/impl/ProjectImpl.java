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
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import aQute.bnd.annotation.ProviderType;
import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import eu.strasbourg.service.agenda.model.Event;
import eu.strasbourg.service.agenda.service.EventLocalServiceUtil;
import eu.strasbourg.service.comment.model.Comment;
import eu.strasbourg.service.comment.service.CommentLocalServiceUtil;
import eu.strasbourg.service.project.model.Participation;
import eu.strasbourg.service.project.model.Petition;
import eu.strasbourg.service.project.model.PlacitPlace;
import eu.strasbourg.service.project.model.Project;
import eu.strasbourg.service.project.model.ProjectFollowed;
import eu.strasbourg.service.project.model.ProjectTimeline;
import eu.strasbourg.service.project.service.ParticipationLocalServiceUtil;
import eu.strasbourg.service.project.service.PetitionLocalServiceUtil;
import eu.strasbourg.service.project.service.PlacitPlaceLocalServiceUtil;
import eu.strasbourg.service.project.service.ProjectFollowedLocalServiceUtil;
import eu.strasbourg.service.project.service.ProjectTimelineLocalServiceUtil;
import eu.strasbourg.utils.AssetVocabularyHelper;
import eu.strasbourg.utils.FileEntryHelper;
import eu.strasbourg.utils.constants.VocabularyNames;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

/**
 * The extended model implementation for the Project service. Represents a row in the &quot;project_Project&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * Helper methods and all application logic should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link eu.strasbourg.service.project.model.Project} interface.
 * </p>
 *
 * @author Cedric Henry
 */
@ProviderType
public class ProjectImpl extends ProjectBaseImpl {

	private static final long serialVersionUID = -5328915283253003964L;

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. All methods that expect a project model instance should use the {@link eu.strasbourg.service.project.model.Project} interface instead.
	 */
	public ProjectImpl() {
	}

    /**
     * Retourne la liste des follower au projet
     */
    @Override
    public List<ProjectFollowed> getProjectFollower() {
        return ProjectFollowedLocalServiceUtil.getByProjectId(this.getProjectId());
    }

	/**
	 * Retourne le nombre de follower au projet
	 */
	@Override
	public int getNbFollower() {
		return ProjectFollowedLocalServiceUtil.getByProjectId(this.getProjectId()).size();
	}

	/**
	 * Retourne le label de 5 digits du nombre de follower au projet
	 */
	@Override
	public String getNbFollowerLabel() {
		// Transforme le numero en chaine de caractere
		String stringNum = Integer.toString(this.getNbFollower());
		// Recupere le nombre de chiffre
		int nbDigits = stringNum.length();
		// Ajoute les zeros manquants avant la chaine
		stringNum = new String(new char[5 - nbDigits]).replace("\0", "0") + stringNum;

		return stringNum;
	}

	/**
	 * Retourne l'AssetEntry rattaché cet item
	 */
	@Override
	public AssetEntry getAssetEntry() {
		return AssetEntryLocalServiceUtil.fetchEntry(Project.class.getName(),
			this.getProjectId());
	}
	
	/**
	 * Retourne la liste des lieux placit liés au projet
	 */
	@Override
	public List<PlacitPlace> getPlacitPlaces() {
		return PlacitPlaceLocalServiceUtil.getByProject(this.getProjectId());
	}

	/**
	 * Retourne les noms des lieux placit au projet
	 */
	@Override
	public List<String> getPlaceNames(Locale locale) {
		List<PlacitPlace> placitPlaces = this.getPlacitPlaces();
		return placitPlaces.stream().map(c -> c.getPlaceAlias(locale)).distinct().collect(Collectors.toList());
	}

	/**
	 * Retourne les ids SIG des lieux placit au projet
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
	 * Retourne les catégories 'Territoire' correspondant aux pays du projet
	 */
	@Override
	public List<AssetCategory> getTerritoryCategories() {
		return AssetVocabularyHelper.getAssetEntryCategoriesByVocabulary(this.getAssetEntry(),
				VocabularyNames.TERRITORY);
	}
	
	/**
	 * Retourne les sous-catégories 'Territoire' correspondant aux villes du projet
	 * @return : la liste des catégories 
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
	 * Retourne les sous-sous-catégories 'Territoire' correspondant aux quartiers du projet
	 * @return : la liste des catégories 
	 */
	@Override
	public List<AssetCategory> getDistrictCategories() {
		List<AssetCategory> territories = this.getTerritoryCategories();
		List<AssetCategory> districts = new ArrayList<>();
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
	 * Retourne les quartiers du projet
	 */
	@Override
	public String getDistrictCategories(Locale locale) {
		List<AssetCategory> districts = getDistrictCategories();
		return AssetVocabularyHelper.getDistrictTitle(locale,districts);
	}
	
	/**
	 * Retourne une chaine des 'Territoires' correspondant aux quartiers du projet
	 * @return : Chaine des quartiers ou description "Aucun" ou "Tous"
	 */
	@Override
	public String getDistrictLabel(Locale locale) {
		StringBuilder result = new StringBuilder();
		List<AssetCategory> districts = this.getDistrictCategories();
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
	 * Retourne la liste des entrées timelines du projet
	 */
	@Override
	public List<ProjectTimeline> getProjectTimelines() {
		List<ProjectTimeline> projectTimelines = ProjectTimelineLocalServiceUtil.getByProjectId(this.getProjectId());
		return projectTimelines;
	}

	/*
	 * Retourne le statut du projet
	 */
	@Override
	public String getProjectStatus(Locale locale) {
		List<AssetCategory> pStatus = getAllStatus();
		
		String result = "";
		
		//Retourne le premier element de la liste
		if(pStatus.size() >= 1)
			result = pStatus.get(0).getTitle(locale);
		
		return result;
	}
	
	/**
	 * Retourne les statuts du projet
	 */
	@Override
	public List<AssetCategory> getAllStatus() {
		return AssetVocabularyHelper.getAssetEntryCategoriesByVocabulary(this.getAssetEntry(),
				VocabularyNames.PROJECT_STATUS);
	}
	
	/**
	 * Retourne l'asset category du projet (normalement du même non que le projet)
	 */
	@Override
	public AssetCategory getProjectCategory() {
		return AssetVocabularyHelper.getAssetEntryCategoriesByVocabulary(this.getAssetEntry(),
				VocabularyNames.PROJECT).stream().findFirst().orElse(null);
	}
	
	/**
	 * Retourne la liste des participations du projet
	 */
	@Override
	public List<Participation> getParticipations() {
		List<AssetEntry> assetResults = new ArrayList<AssetEntry>();
		List<Participation> participationResults = new ArrayList<Participation>();

		if(getProjectCategory() != null)
			assetResults = AssetEntryLocalServiceUtil
					.getAssetCategoryAssetEntries(getProjectCategory()
							.getCategoryId()).stream()
					.filter(cat -> cat.getClassName().equals(Participation.class.getName()) && cat.isVisible())
					.collect(Collectors.toList());

		for (AssetEntry assetEntry : assetResults) {
			participationResults.add(ParticipationLocalServiceUtil.fetchParticipation(assetEntry.getClassPK()));
		}

		return participationResults;
	}

	@Override
	public List<Petition> getPetitions() {
		List<AssetEntry> assetResults = new ArrayList<>();
		List<Petition> petitionsResults = new ArrayList<>();

		if(getProjectCategory() != null)
			assetResults = AssetEntryLocalServiceUtil
					.getAssetCategoryAssetEntries(getProjectCategory()
							.getCategoryId()).stream()
					.filter(cat -> cat.getClassName().equals(Petition.class.getName()) && cat.isVisible())
					.collect(Collectors.toList());

		for (AssetEntry assetEntry : assetResults) {
            petitionsResults.add(PetitionLocalServiceUtil.fetchPetition(assetEntry.getClassPK()));
		}

		return petitionsResults;
	}

	/**
	 * Retourne la liste des évènements du projet
	 */
	@Override
	public List<Event> getEvents() {
		List<AssetEntry> assetResults = new ArrayList<AssetEntry>();
		List<Event> eventResults = new ArrayList<Event>();
		
		if(getProjectCategory() != null)
			assetResults = AssetEntryLocalServiceUtil
			.getAssetCategoryAssetEntries(getProjectCategory()
			.getCategoryId()).stream()
			.filter(cat -> cat.getClassName().equals(Event.class.getName()) && cat.isVisible())
			.collect(Collectors.toList());
		
			for (AssetEntry assetEntry : assetResults) {
				eventResults.add(EventLocalServiceUtil.fetchEvent(assetEntry.getClassPK()));
			}

		return eventResults;
	}

	
	/**
	 * Retourne les thematiques du projet
	 */
	@Override
	public List<AssetCategory> getThematicCategories() {
		return AssetVocabularyHelper.getAssetEntryCategoriesByVocabulary(this.getAssetEntry(),
				VocabularyNames.THEMATIC);
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
	 * Demande si l'utilisateur demandé suit le projet
	 */
	@Override
	public boolean isUserFollows(String publikUserId) {
		if (!publikUserId.isEmpty()) {
			if (ProjectFollowedLocalServiceUtil.getByPublikUserIdAndProjectId(publikUserId, this.getProjectId()) != null)
				return true;
		}
		return false;
	}

	/**
	 * Retourne la version JSON de l'entité
	 */
	@Override
	public JSONObject toJSON(String publikUserId) {
		// Initialisation des variables tempons et résultantes
		JSONObject jsonProject = JSONFactoryUtil.createJSONObject();
		JSONArray jsonPlacitPlaces = JSONFactoryUtil.createJSONArray();
		JSONArray jsonProjectTimelines = JSONFactoryUtil.createJSONArray();

		// Champs de gestion
		jsonProject.put("id", this.getProjectId());

		// Champs : Header
		jsonProject.put("title", HtmlUtil.stripHtml(HtmlUtil.escape(this.getTitle())));
		jsonProject.put("imageURL", this.getImageURL());
		jsonProject.put("description", HtmlUtil.stripHtml(HtmlUtil.escape(this.getDescription())));
		jsonProject.put("detailURL", this.getDetailURL());

		// Champs : En bref
		jsonProject.put("budget", HtmlUtil.stripHtml(HtmlUtil.escape(this.getBudget())));
		jsonProject.put("label", HtmlUtil.stripHtml(HtmlUtil.escape(this.getLabel())));
		jsonProject.put("duration", HtmlUtil.stripHtml(HtmlUtil.escape(this.getDuration())));
		jsonProject.put("partners", HtmlUtil.stripHtml(HtmlUtil.escape(this.getPartners())));
		
		// Champs : Contact
		jsonProject.put("contactName", HtmlUtil.stripHtml(HtmlUtil.escape(this.getContactName())));
		jsonProject.put("contactLine1", HtmlUtil.stripHtml(HtmlUtil.escape(this.getContactLine1())));
		jsonProject.put("contactLine2", HtmlUtil.stripHtml(HtmlUtil.escape(this.getContactLine2())));
		jsonProject.put("contactPhoneNumber", this.getContactPhoneNumber());

		// Champs : Autres
		jsonProject.put("districtLabel", HtmlUtil.stripHtml(HtmlUtil.escape(this.getDistrictLabel(Locale.FRENCH))));
		jsonProject.put("thematicsLabel", HtmlUtil.stripHtml(HtmlUtil.escape(this.getThematicsLabel(Locale.FRENCH))));
		jsonProject.put("nbFollowers", this.getNbFollower());
		
		// Lieux placit
		for (PlacitPlace placitPlace : this.getPlacitPlaces()) {
			jsonPlacitPlaces.put(placitPlace.toJSON());
		}
		jsonProject.put("placitPlaces", jsonPlacitPlaces);

		// Timeline
		for (ProjectTimeline projectTimeline : this.getProjectTimelines()) {
			jsonProjectTimelines.put(projectTimeline.toJSON());
		}
		jsonProject.put("projectTimelines", jsonProjectTimelines);

		// Liste des Ids des catégories Territoire
		JSONArray jsonTerritories = AssetVocabularyHelper.getExternalIdsJSONArray(this.getTerritoryCategories());
		if (jsonTerritories.length() > 0) {
			jsonProject.put("territories", jsonTerritories);
		}

		// Liste des Ids des catégories Status
		JSONArray jsonStatus = AssetVocabularyHelper.getExternalIdsJSONArray(this.getAllStatus());
		if (jsonStatus.length() > 0) {
			jsonProject.put("status", jsonStatus);
		}

		// Liste des Ids des catégories Thématiques
		JSONArray jsonThematics = AssetVocabularyHelper.getExternalIdsJSONArray(this.getThematicCategories());
		if (jsonThematics.length() > 0) {
			jsonProject.put("thematics", jsonThematics);
		}

		return jsonProject;
	}

}