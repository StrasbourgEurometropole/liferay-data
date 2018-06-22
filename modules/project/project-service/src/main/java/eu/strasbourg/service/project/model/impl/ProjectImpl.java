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

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.Validator;

import aQute.bnd.annotation.ProviderType;
import eu.strasbourg.service.agenda.model.Event;
import eu.strasbourg.service.project.model.Participation;
import eu.strasbourg.service.project.model.Project;
import eu.strasbourg.service.project.model.ProjectTimeline;
import eu.strasbourg.service.project.service.ProjectTimelineLocalServiceUtil;
import eu.strasbourg.utils.AssetVocabularyHelper;
import eu.strasbourg.utils.FileEntryHelper;
import eu.strasbourg.utils.constants.VocabularyNames;

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
	 * Retourne l'AssetEntry rattaché cet item
	 */
	@Override
	public AssetEntry getAssetEntry() {
		return AssetEntryLocalServiceUtil.fetchEntry(Project.class.getName(),
			this.getProjectId());
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
	public List<AssetEntry> getParticipations() {
		List<AssetEntry> result = new ArrayList<AssetEntry>();
		
		if(getProjectCategory() != null)
			result = AssetEntryLocalServiceUtil
			.getAssetCategoryAssetEntries(getProjectCategory()
			.getCategoryId()).stream()
			.filter(cat -> cat.getClassName().equals(Participation.class.getName()))
			.collect(Collectors.toList());
		
		return result;
	}
	
	/**
	 * Retourne la liste des évènements du projet
	 */
	@Override
	public List<AssetEntry> getEvents() {
		List<AssetEntry> result = new ArrayList<AssetEntry>();
		
		if(getProjectCategory() != null)
			result = AssetEntryLocalServiceUtil
			.getAssetCategoryAssetEntries(getProjectCategory()
			.getCategoryId()).stream()
			.filter(cat -> cat.getClassName().equals(Event.class.getName()))
			.collect(Collectors.toList());
		
		return result;
	}

	
	/**
	 * Retourne les thematiques du projet
	 */
	@Override
	public List<AssetCategory> getThematicCategories() {
		return AssetVocabularyHelper.getAssetEntryCategoriesByVocabulary(this.getAssetEntry(),
				VocabularyNames.THEMATIC);
	}
}