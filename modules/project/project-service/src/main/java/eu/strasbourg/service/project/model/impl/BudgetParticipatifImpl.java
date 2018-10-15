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
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.Validator;
import eu.strasbourg.service.project.model.BudgetParticipatif;
import eu.strasbourg.service.project.model.PlacitPlace;
import eu.strasbourg.service.project.service.PlacitPlaceLocalServiceUtil;
import eu.strasbourg.utils.AssetVocabularyHelper;
import eu.strasbourg.utils.FileEntryHelper;
import eu.strasbourg.utils.constants.VocabularyNames;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * The extended model implementation for the BudgetParticipatif service. Represents a row in the &quot;project_BudgetParticipatif&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * Helper methods and all application logic should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link eu.strasbourg.service.project.model.BudgetParticipatif} interface.
 * </p>
 *
 * @author Cedric Henry
 */
@ProviderType
public class BudgetParticipatifImpl extends BudgetParticipatifBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. All methods that expect a budget participatif model instance should use the {@link eu.strasbourg.service.project.model.BudgetParticipatif} interface instead.
	 */
	public BudgetParticipatifImpl() {
	}

	/**
	 * Retourne les catégories 'Territoire' correspondant aux pays de la petition
	 */
	public List<AssetCategory> getTerritoryCategories() {
		return AssetVocabularyHelper.getAssetEntryCategoriesByVocabulary(this.getAssetEntry(),
				VocabularyNames.TERRITORY);
	}

	/**
	 * Retourne l'AssetEntry rattaché cet item
	 */
	public AssetEntry getAssetEntry() {
		return AssetEntryLocalServiceUtil.fetchEntry(BudgetParticipatif.class.getName(),
				this.getBudgetParticipatifId());
	}

	/**
	 * Renvoie la liste des AssetCategory rattachées à cet item (via
	 * l'assetEntry)
	 */
	public List<AssetCategory> getCategories() {
		return AssetVocabularyHelper
				.getAssetEntryCategories(this.getAssetEntry());
	}

	/**
	 * Retourne la version JSON de l'entité
	 */
	@Override
	public JSONObject toJSON(String publikUserId) {
		// Initialisation des variables tempons et résultantes
		JSONObject jsonBudgetParticipatif = JSONFactoryUtil.createJSONObject();
		JSONArray jsonPlacitPlaces = JSONFactoryUtil.createJSONArray();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

		jsonBudgetParticipatif.put("id", this.getBudgetParticipatifId());
		jsonBudgetParticipatif.put("createDate", dateFormat.format(this.getCreateDate()));
		jsonBudgetParticipatif.put("imageURL", this.getImageURL());
		jsonBudgetParticipatif.put("userName", HtmlUtil.stripHtml(HtmlUtil.escape(this.getUserName())));
		jsonBudgetParticipatif.put("districtLabel", HtmlUtil.stripHtml(HtmlUtil.escape(this.getDistrictLabel(Locale.FRENCH))));
		jsonBudgetParticipatif.put("title", HtmlUtil.stripHtml(HtmlUtil.escape(this.getTitle())));

		// Lieux placit
		for (PlacitPlace placitPlace : this.getPlacitPlaces()) {
			jsonPlacitPlaces.put(placitPlace.toJSON());
		}
		jsonBudgetParticipatif.put("placitPlaces", jsonPlacitPlaces);

		return jsonBudgetParticipatif;
	}

	/**
	 * Retourne l'URL de l'image à partir de l'id du DLFileEntry
	 */
	public String getImageURL() {
		if (Validator.isNotNull(this.getExternalImageURL())) {
			return this.getExternalImageURL();
		} else {
			return FileEntryHelper.getFileEntryURL(this.getImageId());
		}
	}

	/**
	 * Retourne une chaine des 'Territoires' correspondant aux quartiers de la petition
	 *
	 * @return : Chaine des quartiers ou description "Aucun" ou "Tous"
	 */
	public String getDistrictLabel(Locale locale) {
		List<AssetCategory> districts = getDistrictCategories();
		return AssetVocabularyHelper.getDistrictTitle(locale, districts);
	}

	/**
	 * Retourne les sous-sous-catégories 'Territoire' correspondant aux quartiers de la petition
	 *
	 * @return : null si vide, sinon la liste des catégories
	 */
	public List<AssetCategory> getDistrictCategories() {
		List<AssetCategory> territories = getTerritoryCategories();
		List<AssetCategory> districts = new ArrayList<>();
		for (AssetCategory territory : territories) {
			try {
				if (territory.getAncestors().size() == 2) {
					districts.add(territory);
				}
			} catch (PortalException ignored) {
			}
		}
		return districts;
	}

	/**
	 * Retourne la liste des lieux placit liés à la petition
	 */

	public List<PlacitPlace> getPlacitPlaces() {
		List<PlacitPlace> resultList = PlacitPlaceLocalServiceUtil.getByBudgetParticipatif(this.getBudgetParticipatifId());
		return null;
	}

}