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

import static eu.strasbourg.service.project.constants.ParticiperCategories.BP_CANCELLED;
import static eu.strasbourg.service.project.constants.ParticiperCategories.BP_FEASIBLE;
import static eu.strasbourg.service.project.constants.ParticiperCategories.BP_IN_PROGRESS;
import static eu.strasbourg.service.project.constants.ParticiperCategories.BP_LAUREAT;
import static eu.strasbourg.service.project.constants.ParticiperCategories.BP_NON_ACCEPTABLE;
import static eu.strasbourg.service.project.constants.ParticiperCategories.BP_NON_FEASIBLE;
import static eu.strasbourg.service.project.constants.ParticiperCategories.BP_NON_SELECTED;
import static eu.strasbourg.service.project.constants.ParticiperCategories.BP_REALIZED;
import static eu.strasbourg.service.project.constants.ParticiperCategories.BP_SUSPENDED;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.stream.Stream;

import javax.servlet.http.HttpServletRequest;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
import com.liferay.asset.kernel.service.persistence.AssetEntryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
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
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import aQute.bnd.annotation.ProviderType;
import eu.strasbourg.service.comment.model.Comment;
import eu.strasbourg.service.comment.service.CommentLocalServiceUtil;
import eu.strasbourg.service.project.constants.ParticiperCategories;
import eu.strasbourg.service.project.model.BudgetParticipatif;
import eu.strasbourg.service.project.model.BudgetPhase;
import eu.strasbourg.service.project.model.BudgetSupport;
import eu.strasbourg.service.project.model.PlacitPlace;
import eu.strasbourg.service.project.service.BudgetParticipatifLocalServiceUtil;
import eu.strasbourg.service.project.service.BudgetPhaseLocalServiceUtil;
import eu.strasbourg.service.project.service.BudgetSupportLocalServiceUtil;
import eu.strasbourg.service.project.service.PlacitPlaceLocalServiceUtil;
import eu.strasbourg.utils.AssetVocabularyHelper;
import eu.strasbourg.utils.FileEntryHelper;
import eu.strasbourg.utils.StringHelper;
import eu.strasbourg.utils.constants.VocabularyNames;

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
     * Retourne l'URL de l'image de l'utilisateur
     */
    @Override
    public String getAuthorImageURL() {
        return "/o/plateforme-citoyenne-theme/images/medias/user_female_portrait.png";
    }

    /**
     * Retourne une chaine des 'Territoires' correspondant aux quartiers du bp
     * @return : Chaine des quartiers ou description "Aucun" ou "Tous"
     */
    @Override
    public String getDistrictLabel(Locale locale) {
        List<AssetCategory> districts = getDistrictCategories();
        return AssetVocabularyHelper.getDistrictTitle(locale, districts);
    }

    /**
     * Retourne les sous-sous-catégories 'Territoire' correspondant aux quartiers du bp
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
	 * A deja fait l'oeuvre d'un vote et/ou d'une decision administrative
	 */
	@Override
	public boolean hasBeenVoted() {
		AssetCategory BPStatus = this.getBudgetParticipatifStatusCategory();
		
		if (BPStatus != null) {
			if (StringHelper.compareIgnoringAccentuation(BPStatus.getTitle(Locale.FRENCH), BP_LAUREAT.getName()) 
					|| StringHelper.compareIgnoringAccentuation(BPStatus.getTitle(Locale.FRENCH), BP_REALIZED.getName()) 
					|| StringHelper.compareIgnoringAccentuation(BPStatus.getTitle(Locale.FRENCH), BP_NON_ACCEPTABLE.getName()) 
					|| StringHelper.compareIgnoringAccentuation(BPStatus.getTitle(Locale.FRENCH), BP_NON_SELECTED.getName()) 
					|| StringHelper.compareIgnoringAccentuation(BPStatus.getTitle(Locale.FRENCH), BP_NON_FEASIBLE.getName()) 
					|| StringHelper.compareIgnoringAccentuation(BPStatus.getTitle(Locale.FRENCH), BP_IN_PROGRESS.getName()) 
					|| StringHelper.compareIgnoringAccentuation(BPStatus.getTitle(Locale.FRENCH), BP_SUSPENDED.getName())
					|| StringHelper.compareIgnoringAccentuation(BPStatus.getTitle(Locale.FRENCH), BP_CANCELLED.getName())) 
				return true;
		}
		return false;
	}
	
	/**
	 * Est en periode et capacite de vote
	 */
	@Override
	public boolean isVotable() {
		BudgetPhase budgetPhase = this.getPhase();
		AssetCategory BPStatus = this.getBudgetParticipatifStatusCategory();
		
		if (budgetPhase != null && BPStatus != null) {
			if (budgetPhase.isInVotingPeriod() 
					&& StringHelper.compareIgnoringAccentuation(BPStatus.getTitle(Locale.FRENCH), BP_FEASIBLE.getName()))
				return true;
		}
		return false;
	}
	
	/**
	 *  Non faisable si le statut est : Non Recevable, Non faisable, Non retenu, Annulé, Suspendu
	 */
	@Override
	public boolean isNotDoable() {
		return Stream.of(
	    		ParticiperCategories.BP_NON_ACCEPTABLE.getName(),
	    		ParticiperCategories.BP_NON_FEASIBLE.getName(),
	    		ParticiperCategories.BP_NON_SELECTED.getName(),	               		
	    		ParticiperCategories.BP_CANCELLED.getName(),
	    		ParticiperCategories.BP_SUSPENDED.getName()
	    		).anyMatch(x -> x.equals(this.getBudgetParticipatifStatusCategory().getName()));
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
	 * Retourne les soutiens du budget participatif
	 * @return Liste des soutiens
	 */
	@Override
	public List<BudgetSupport> getSupports() {
        return BudgetSupportLocalServiceUtil.getBudgetSupportsByBudgetParticipatifId(this.getBudgetParticipatifId());
    }
	
	/**
	 * Retourne le nombre de soutien
	 */
	@Override
	public long getNbSupports() {
		return (long) BudgetSupportLocalServiceUtil.countBudgetSupportByBudgetParticipatifId(this.getBudgetParticipatifId());
    }
	
	/**
	 * Retourne le nombre de soutiens d'un utilisateur pour ce projet
	 */
	@Override
	public int getNbSupportOfUser(String publikUserId) {
		return BudgetSupportLocalServiceUtil
				.getBudgetSupportByBudgetParticipatifIdAndPublikUserId(this.getBudgetParticipatifId(), publikUserId)
				.size();
	}
	
	/**
	 * Retourne le nombre de soutiens d'un utilisateur pour la phase en cours, qu'importe le projet
	 */
	@Override
	public int getNbSupportOfUserInActivePhase(String publikUserId) {
		List<BudgetPhase> activePhases = BudgetPhaseLocalServiceUtil.getByIsActiveAndGroupId(true, this.getGroupId());
		
		if (activePhases.size() > 0) {
			return BudgetParticipatifLocalServiceUtil.countBudgetSupportedByPublikUserInPhase(
						publikUserId,
						activePhases.get(0).getBudgetPhaseId()
					);
		}
		
		return 0;
	}
	
	/**
     * Retourne le nombre de soutien sous le format 6 digits pour l'affichage
     * @return le nombre sous le format '000124'
     */
    @Override
    public String getNbSupportsBoard() {
        long nbResult = getNbSupports();
        return String.format("%06d", nbResult);
    }
    
    public String getPublicationDateFr(){
        Date date = this.getAssetEntry().getPublishDate();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(date);
    }
    
    /**
     * Remplace le statut bp actuel par celui fournis en paramètre de la méthode
     * @param budgetParticipatif
     * @param status
     */
    @Override
    public void setBPStatus(BudgetParticipatif budgetParticipatif, ParticiperCategories status, long groupID)
    {
    	AssetEntry entry = budgetParticipatif.getAssetEntry();
    	List<AssetCategory> statuses = AssetVocabularyHelper.getAssetEntryCategoriesByVocabulary(entry, VocabularyNames.BUDGET_PARTICIPATIF_STATUS);
    	AssetCategory category = AssetVocabularyHelper.getCategory(status.getName(), groupID);
    	
    	if(!statuses.isEmpty())
    		AssetEntryUtil.removeAssetCategory(entry.getEntryId(), statuses.get(0));
    	
    	AssetVocabularyHelper.addCategoryToAssetEntry(category, entry);
    }
    
    /**
     * Retourne X suggestions max pour un BP
     *
     * @param request la requete
     * @param nbSuggestions le nombre de suggestions.
     * @return la liste de bp.
     */
    @Override
    public List<BudgetParticipatif> getSuggestions(HttpServletRequest request, int nbSuggestions) throws SearchException, PortalException {
		
    	List<BudgetParticipatif> suggestions = new ArrayList<>();
		
		try {
			//Initialisation du seachContext
			SearchContext searchContext = SearchContextFactory.getInstance(request);
			searchContext.setStart(0);
			searchContext.setEnd(nbSuggestions);
			
			//utilisation de l'indexer de l'entite BP (Permet de rechercher uniquement des bp)
			Indexer indexer = IndexerRegistryUtil.getIndexer(BudgetParticipatif.class.getName());
			
			//création de la query avec des filtre sur les entités publiées uniquement
			BooleanQuery mainQuery = new BooleanQueryImpl();
			mainQuery.addRequiredTerm(Field.STATUS, WorkflowConstants.STATUS_APPROVED);
			mainQuery.addRequiredTerm("visible", true);
			
			//Un ou plusieurs territoire
			BooleanQuery territoryQuery = new BooleanQueryImpl();
			for (AssetCategory category : this.getTerritoryCategories()) {
				BooleanQuery categoryQuery = new BooleanQueryImpl();
				categoryQuery.addRequiredTerm(Field.ASSET_CATEGORY_IDS, String.valueOf(category.getCategoryId()));
				territoryQuery.add(categoryQuery, BooleanClauseOccur.SHOULD);
			}
			mainQuery.add(territoryQuery, BooleanClauseOccur.MUST);
			
			//Une ou plusieurs thématiques
			BooleanQuery thematiqueQuery = new BooleanQueryImpl();
			for (AssetCategory category : this.getThematicCategories()) {
				BooleanQuery categoryQuery = new BooleanQueryImpl();
				categoryQuery.addRequiredTerm(Field.ASSET_CATEGORY_IDS, String.valueOf(category.getCategoryId()));
				thematiqueQuery.add(categoryQuery, BooleanClauseOccur.SHOULD);
			}
			mainQuery.add(thematiqueQuery, BooleanClauseOccur.MUST);
			
			//Le même projet
			if(this.getProjectCategory() != null) {
				BooleanQuery projetQuery = new BooleanQueryImpl();
				projetQuery.addRequiredTerm(Field.ASSET_CATEGORY_IDS, String.valueOf(this.getProjectCategory().getCategoryId()));
				mainQuery.add(projetQuery, BooleanClauseOccur.MUST);
			}
			
			BooleanClause booleanClause = BooleanClauseFactoryUtil.create(mainQuery, BooleanClauseOccur.MUST.getName());
			searchContext.setBooleanClauses(new BooleanClause[] {booleanClause});
			
			//Lance la recherche elasticSearch
		    Hits hits = indexer.search(searchContext);
			
		    //Generation de notre liste de suggestion
		    for (Document document : hits.getDocs()) {
		    	//récupération de l'élément en base
		    	BudgetParticipatif bp = BudgetParticipatifLocalServiceUtil.fetchBudgetParticipatif(GetterUtil.getLong(document.get(Field.ENTRY_CLASS_PK)));
	            
	            //Vérification que le bp recherché existe bien base (En théorie ne devrait pas arriver) et qu'il est différente du bp courante
	            if(bp != null && bp.getBudgetParticipatifId() != this.getBudgetParticipatifId())
	            	suggestions.add(bp);
	        }
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	    
		return suggestions;
    }
    
    
    /**
     * Retourne la version JSON de l'entité
     */
    public JSONObject toJSON(String publikUserId) {
    	
        // Initialisation des variables tempons et resultantes
        JSONObject jsonBudget = JSONFactoryUtil.createJSONObject();
        JSONArray jsonPlacitPlaces = JSONFactoryUtil.createJSONArray();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        jsonBudget.put("id", this.getBudgetParticipatifId());
        jsonBudget.put("createDate", dateFormat.format(this.getCreateDate()));
        jsonBudget.put("publicationDate", dateFormat.format(this.getAssetEntry().getPublishDate()));
        
        jsonBudget.put("authorImageURL", this.getAuthorImageURL());
        jsonBudget.put("userName", HtmlUtil.stripHtml(HtmlUtil.escape(this.getUserName())));
        jsonBudget.put("author", HtmlUtil.stripHtml(HtmlUtil.escape(this.getAuthor())));
        jsonBudget.put("title", HtmlUtil.stripHtml(HtmlUtil.escape(this.getTitle())));
        jsonBudget.put("isCrush", this.getIsCrush());
        jsonBudget.put("BPStatusColor", this.getBudgetParticipatifStatusCategoryColor());
        jsonBudget.put("hasBeenVoted", this.hasBeenVoted());
        jsonBudget.put("hasBeendEvaluated", this.hasBeenEvaluated());
        jsonBudget.put("isNotDoable", this.isNotDoable());
        
        // Champs : Categorisations
        jsonBudget.put("BPStatus", HtmlUtil.stripHtml(HtmlUtil.escape(this.getBudgetParticipatifStatusTitle(Locale.FRENCH))));
        jsonBudget.put("districtsLabel", HtmlUtil.stripHtml(HtmlUtil.escape(this.getDistrictLabel(Locale.FRENCH))));
        jsonBudget.put("thematicsLabel", HtmlUtil.stripHtml(HtmlUtil.escape(this.getThematicsLabel(Locale.FRENCH))));
        jsonBudget.put("projectName", this.getProjectName());
        
        // Champs : Interactivités
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

}