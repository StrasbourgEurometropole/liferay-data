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

import java.util.List;
import java.util.Locale;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
import com.liferay.portal.kernel.util.Validator;

import aQute.bnd.annotation.ProviderType;
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
	 * Retourne la catégorie Territoire correspondant à la ville du lieu
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
	
}