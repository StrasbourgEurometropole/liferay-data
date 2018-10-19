/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 * <p>
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 * <p>
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
     * Retourne le projet de la participation (
     */
    @Override
    public AssetCategory getProjectCategory() {
        return AssetVocabularyHelper.getAssetEntryCategoriesByVocabulary(this.getAssetEntry(), VocabularyNames.PROJECT)
                .get(0);
    }

    public String getProjectTitle(Locale locale) {
        AssetCategory project = getProjectCategory();
        return (project != null) ? project.getName() : "";
    }


    /**
     * Retourne l'AssetEntry rattaché cet item
     */
    @Override
    public AssetEntry getAssetEntry() {
        return AssetEntryLocalServiceUtil.fetchEntry(BudgetParticipatif.class.getName(),
                this.getBudgetParticipatifId());
    }

    /**
     * Retourne les thematiques de la participation (
     */
    @Override
    public List<AssetCategory> getThematicCategories() {
        return AssetVocabularyHelper.getAssetEntryCategoriesByVocabulary(this.getAssetEntry(),
                VocabularyNames.THEMATIC);
    }

    @Override
    public String getThematicTitle(Locale locale) {
        List<AssetCategory> thematics = getThematicCategories();
        return AssetVocabularyHelper.getThematicTitle(locale, thematics);
    }

    /**
     * Retourne les catégories 'Territoire' correspondant aux pays de la petition
     */
    @Override
    public List<AssetCategory> getTerritoryCategories() {
        return AssetVocabularyHelper.getAssetEntryCategoriesByVocabulary(this.getAssetEntry(),
                VocabularyNames.TERRITORY);
    }

    /**
     * Retourne la liste des lieux placit liés à la participation
     */
    @Override
    public List<PlacitPlace> getPlacitPlaces() {
        return PlacitPlaceLocalServiceUtil.getByBudgetParticipatif(this.getBudgetParticipatifId());
    }

    /**
     * retourne les catégories
     *
     * @return
     */
    public List<AssetCategory> getCategories() {
        return AssetVocabularyHelper
                .getAssetEntryCategories(this.getAssetEntry());
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
    @Override
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

    public AssetCategory getTypeCategory() {
        return AssetVocabularyHelper
                .getAssetEntryCategoriesByVocabulary(this.getAssetEntry(), VocabularyNames.BUDGET_PARTICIPATIF_STATUS).get(0);
    }

    public String getTypeCategoryColor() {
        long categoryId = this.getTypeCategory().getCategoryId();
        return AssetVocabularyHelper.getCategoryProperty(categoryId, "color_code");
    }

    @Override
    public String getAuthor(){
        return this.getCitoyenFirstname() + " " + this.getCitoyenLastname();
    }

    /**
     * Retourne la version JSON de l'entité
     */
    @Override
    public JSONObject toJSON(String publikUserId) {
        // Initialisation des variables tempons et résultantes
        JSONObject jsonBudget = JSONFactoryUtil.createJSONObject();
        AssetCategory projectCategory = this.getProjectCategory();
        JSONArray jsonPlacitPlaces = JSONFactoryUtil.createJSONArray();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        jsonBudget.put("id", this.getBudgetParticipatifId());
        jsonBudget.put("createDate", dateFormat.format(this.getCreateDate()));
        jsonBudget.put("imageURL", this.getExternalImageURL());
        jsonBudget.put("userName", HtmlUtil.stripHtml(HtmlUtil.escape(this.getUserName())));
        jsonBudget.put("districtLabel", HtmlUtil.stripHtml(HtmlUtil.escape(this.getDistrictLabel(Locale.FRENCH))));
        jsonBudget.put("projectName", projectCategory != null ? projectCategory.getTitle(Locale.FRENCH) : "");
        jsonBudget.put("title", HtmlUtil.stripHtml(HtmlUtil.escape(this.getTitle())));

        // Lieux placit
        for (PlacitPlace placitPlace : this.getPlacitPlaces()) {
            jsonPlacitPlaces.put(placitPlace.toJSON());
        }
        jsonBudget.put("placitPlaces", jsonPlacitPlaces);

        // Liste des Ids des catégories Thématiques
        JSONArray jsonThematics = AssetVocabularyHelper.getExternalIdsJSONArray(this.getThematicCategories());
        if (jsonThematics.length() > 0) {
            jsonBudget.put("thematics", jsonThematics);
        }

        return jsonBudget;
    }

    private long getNombreSoutien() {
        return 0;
    }
}