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
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import eu.strasbourg.service.comment.model.Comment;
import eu.strasbourg.service.comment.service.CommentLocalServiceUtil;
import eu.strasbourg.service.project.model.BudgetParticipatif;
import eu.strasbourg.service.project.model.BudgetPhase;
import eu.strasbourg.service.project.model.PlacitPlace;
import eu.strasbourg.service.project.service.BudgetPhaseLocalServiceUtil;
import eu.strasbourg.service.project.service.PlacitPlaceLocalServiceUtil;
import eu.strasbourg.utils.AssetVocabularyHelper;
import eu.strasbourg.utils.FileEntryHelper;
import eu.strasbourg.utils.StringHelper;
import eu.strasbourg.utils.constants.VocabularyNames;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static eu.strasbourg.service.project.constants.ParticiperCategories.*;

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
	
	private static final long serialVersionUID = -2427479225001060332L;

	/*
     * NOTE FOR DEVELOPERS:
     *
     * Never reference this class directly. All methods that expect a budget participatif model instance should use the {@link eu.strasbourg.service.project.model.BudgetParticipatif} interface instead.
     */
    public BudgetParticipatifImpl() {
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
     * Retourne les catégories 'Territoire' correspondant aux pays du budget
     */
    @Override
    public List<AssetCategory> getTerritoryCategories() {
        return AssetVocabularyHelper.getAssetEntryCategoriesByVocabulary(this.getAssetEntry(),
                VocabularyNames.TERRITORY);
    }
    
    /**
     * Retourne les catégories 'Statut BP' du budget participatif
     */
    @Override
    public AssetCategory getBudgetParticipatifStatusCategory() {
    	List<AssetCategory> assetCategories = AssetVocabularyHelper.getAssetEntryCategoriesByVocabulary(this.getAssetEntry(),
                VocabularyNames.BUDGET_PARTICIPATIF_STATUS);
        if (assetCategories.size() > 0) {
        	return assetCategories.get(0);
        } else {
        	return null;
        }
    }
    
    @Override
    public String getBudgetParticipatifStatusTitle(Locale locale) {
        AssetCategory budgetParticipatifStatusCategory = this.getBudgetParticipatifStatusCategory();
        if (budgetParticipatifStatusCategory != null) {
        	return budgetParticipatifStatusCategory.getTitle(locale);
        } else {
        	return "";
        }
    }

    /**
     * Retourne la liste des lieux placit liés
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
        return FileEntryHelper.getFileEntryURL(this.getImageId());
    }

    /**
     * Retourne une chaine des 'Territoires' correspondant aux quartiers de la petition
     * @return : Chaine des quartiers ou description "Aucun" ou "Tous"
     */
    @Override
    public String getDistrictLabel(Locale locale) {
        List<AssetCategory> districts = getDistrictCategories();
        return AssetVocabularyHelper.getDistrictTitle(locale, districts);
    }

    /**
     * Retourne les sous-sous-catégories 'Territoire' correspondant aux quartiers de la petition
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
    
    @Override
    public String getBudgetParticipatifStatusCategoryColor() {
        AssetCategory statusCategory = this.getBudgetParticipatifStatusCategory();
        if (statusCategory != null) {
        	return AssetVocabularyHelper.getCategoryProperty(statusCategory.getCategoryId(), "color_code");
        } else {
        	return "";
        }
    }
    
    /**
	 * Retourne la categorie projet du BP
	 */
	@Override
	public AssetCategory getProjectCategory() {
		List<AssetCategory> assetCategories = AssetVocabularyHelper.getAssetEntryCategoriesByVocabulary(this.getAssetEntry(),
				VocabularyNames.PROJECT);
        if (assetCategories.size() > 0) {
        	return assetCategories.get(0);
        } else {
        	return null;
        }
	}
	
	/**
	 * Retourne la titre du projet du BP
	 */
	@Override
	public String getProjectName() {
        AssetCategory project = getProjectCategory();
        return (project != null) ? project.getTitle(Locale.FRANCE) : "";
    }

    @Override
    public String getAuthor(){
        return this.getCitoyenFirstname() + " " + this.getCitoyenLastname();
    }
    
    /**
	 * Peut apporter une reaction (commenter, liker, participer) a l'entite
	 */
	@Override
	public boolean isJudgeable() {
		return true;
	}
	
	/**
	 * Est en periode de vote
	 */
	@Override
	public boolean isVotable() {
		BudgetPhase budgetPhase = this.getPhase();
		if (budgetPhase != null) {
			if (budgetPhase.isInVotingPeriod())
				return true;
		}
		return false;
	}
    
    @Override
    public BudgetPhase getPhase() {
    	if (this.getBudgetPhaseId() > 0) {
			try {
				return BudgetPhaseLocalServiceUtil.getBudgetPhase(this.getBudgetPhaseId());
			} catch (PortalException e) {
				e.printStackTrace();
			}
    	}
    	return null;
    }
    
    @Override
    public String getPhaseTitleLabel() {
        BudgetPhase budgetPhase = this.getPhase();
        if (budgetPhase != null) {
        	return budgetPhase.getTitle();
        } else {
        	return "Aucune phase";
        }
    }
    
    /**
     * Le budget a-t-il ete evalue par l'administration ?
     * @note : doit alors posseder l'un des statuts adequat
     */
    @Override
    public boolean hasBeenEvaluated() {
        AssetCategory bpStatus = this.getBudgetParticipatifStatusCategory();
        if (StringHelper.compareIgnoringAccentuation(bpStatus.getTitle(Locale.FRANCE), BP_NON_FEASIBLE.getName()) 
        		|| StringHelper.compareIgnoringAccentuation(bpStatus.getTitle(Locale.FRANCE), BP_FEASIBLE.getName())) {
        	return true;
        }
        return false;
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
     * Retourne la version JSON de l'entité
     */
    public JSONObject toJSON(String publikUserId) {
    	
        // Initialisation des variables tempons et résultantes
        JSONObject jsonBudget = JSONFactoryUtil.createJSONObject();
        JSONArray jsonPlacitPlaces = JSONFactoryUtil.createJSONArray();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        jsonBudget.put("id", this.getBudgetParticipatifId());
        jsonBudget.put("createDate", dateFormat.format(this.getCreateDate()));
        jsonBudget.put("imageURL", this.getImageURL());
        jsonBudget.put("userName", HtmlUtil.stripHtml(HtmlUtil.escape(this.getUserName())));
        jsonBudget.put("author", HtmlUtil.stripHtml(HtmlUtil.escape(this.getAuthor())));
        jsonBudget.put("title", HtmlUtil.stripHtml(HtmlUtil.escape(this.getTitle())));
        jsonBudget.put("isCrush", this.getIsCrush());
        
        // Champs : Catégorisations
        jsonBudget.put("BPStatus", HtmlUtil.stripHtml(HtmlUtil.escape(this.getBudgetParticipatifStatusTitle(Locale.FRENCH))));
        jsonBudget.put("districtsLabel", HtmlUtil.stripHtml(HtmlUtil.escape(this.getDistrictLabel(Locale.FRENCH))));
        jsonBudget.put("thematicsLabel", HtmlUtil.stripHtml(HtmlUtil.escape(this.getThematicsLabel(Locale.FRENCH))));
        jsonBudget.put("projectName", this.getProjectName());
        
        // Champs : Intéractivités
        jsonBudget.put("nbApprovedComments", this.getNbApprovedComments());
        jsonBudget.put("nbSupports", this.getNbSupports());

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

    private long getNbSupports() {
        return 0;
    }
}