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

package eu.strasbourg.service.project.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.model.AssetLink;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.service.WorkflowInstanceLinkLocalServiceUtil;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import eu.strasbourg.service.project.constants.ParticiperCategories;
import eu.strasbourg.service.project.model.BudgetParticipatif;
import eu.strasbourg.service.project.model.BudgetParticipatifModel;
import eu.strasbourg.service.project.model.BudgetPhase;
import eu.strasbourg.service.project.model.BudgetSupport;
import eu.strasbourg.service.project.model.PlacitPlace;
import eu.strasbourg.service.project.service.BudgetParticipatifLocalServiceUtil;
import eu.strasbourg.service.project.service.BudgetPhaseLocalServiceUtil;
import eu.strasbourg.service.project.service.base.BudgetParticipatifLocalServiceBaseImpl;
import eu.strasbourg.utils.AssetVocabularyHelper;

/**
 * The implementation of the budget participatif local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link eu.strasbourg.service.project.service.BudgetParticipatifLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Cedric Henry
 * @see BudgetParticipatifLocalServiceBaseImpl
 * @see eu.strasbourg.service.project.service.BudgetParticipatifLocalServiceUtil
 */
public class BudgetParticipatifLocalServiceImpl extends BudgetParticipatifLocalServiceBaseImpl {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never reference this class directly. Always use {@link eu.strasbourg.service.project.service.BudgetParticipatifLocalServiceUtil} to access the budget participatif local service.
     */

    /**
     * le logger
     */
    public final Log _log = LogFactoryUtil.getLog(this.getClass().getName());


    /**
     * Crée une participation vide avec une PK, non ajouté à la base de donnée
     */
    @Override
    public BudgetParticipatif createBudgetParticipatif(ServiceContext sc)
            throws PortalException {
        User user = UserLocalServiceUtil.getUser(sc.getUserId());

        long pk = counterLocalService.increment();

        BudgetParticipatif budget = this.budgetParticipatifLocalService.createBudgetParticipatif(pk);

        if (user != null) {
            budget.setUserName(user.getFullName());
            budget.setUserId(sc.getUserId());
        }

        budget.setGroupId(sc.getScopeGroupId());
        budget.setStatus(WorkflowConstants.STATUS_DRAFT);

        return budget;
    }

    @Override
    public void removeBudgetParticipatif(long budgetId) throws PortalException {
        AssetEntry entry = AssetEntryLocalServiceUtil
                .fetchEntry(BudgetParticipatif.class.getName(), budgetId);

        if (entry != null) {
            // Delete the link with categories
            for (long categoryId : entry.getCategoryIds()) {
                this.assetEntryLocalService.deleteAssetCategoryAssetEntry(
                        categoryId, entry.getEntryId());
            }

            // Delete the link with tags
            long[] tagIds = AssetEntryLocalServiceUtil
                    .getAssetTagPrimaryKeys(entry.getEntryId());
            if (tagIds != null && tagIds.length > 0) {
                for (long tagId : tagIds) {
                    AssetEntryLocalServiceUtil.deleteAssetTagAssetEntry(tagId,
                            entry.getEntryId());
                }
            }

            // Supprime lien avec les autres entries
            List<AssetLink> links = this.assetLinkLocalService
                    .getLinks(entry.getEntryId());
            if (links != null && !links.isEmpty()) {
                for (AssetLink link : links) {
                    this.assetLinkLocalService.deleteAssetLink(link);
                }
            }

            // Delete the AssetEntry
            AssetEntryLocalServiceUtil.deleteEntry(BudgetParticipatif.class.getName(),
                    budgetId);

            // Supprime les lieux
            List<PlacitPlace> placitPlaces = this.placitPlaceLocalService
                    .getByBudgetParticipatif(budgetId);
            if (placitPlaces != null && !placitPlaces.isEmpty()) {
                for (PlacitPlace placitPlace : placitPlaces) {
                    this.placitPlaceLocalService.removePlacitPlace(
                            placitPlace.getPlacitPlaceId());
                }
            }

            // Supprime le budgetParticipatif
            BudgetParticipatif budgetParticipatif = budgetParticipatifPersistence.remove(budgetId);

            // Delete the index
            this.reindex(budgetParticipatif, true);

            // Supprime ce qui a rapport au workflow
            WorkflowInstanceLinkLocalServiceUtil.deleteWorkflowInstanceLinks(
                    budgetParticipatif.getCompanyId(), budgetParticipatif.getGroupId(), BudgetParticipatif.class.getName(),
                    budgetParticipatif.getBudgetParticipatifId());
            _log.info("BudgetParticipatif numero : " + budgetId + " supprimé");
        }
    }

    /**
     * Méthode de mise à jour d'un budget
     *
     * @param budget le budget
     * @param sc     le service context
     * @return le budget
     * @throws PortalException exception
     */
    @Override
    public BudgetParticipatif updateBudgetParticipatif(BudgetParticipatif budget, ServiceContext sc) throws PortalException {
        User user = UserLocalServiceUtil.getUser(sc.getUserId());
        long groupId = sc.getThemeDisplay().getLayout().getGroupId();

        if (user != null) {
            budget.setStatusByUserId(sc.getUserId());
            budget.setStatusByUserName(user.getFullName());
            budget.setStatusDate(sc.getModifiedDate());
        }

        // Si la phase n'est pas definie, definir celle qui est active (si elle existe)
        if (budget.getBudgetPhaseId() < 1) {
        	
            BudgetPhase budgetPhaseActive = BudgetPhaseLocalServiceUtil.getActivePhase(groupId);
            if (budgetPhaseActive != null) {
                budget.setBudgetPhaseId(budgetPhaseActive.getBudgetPhaseId());
                AssetCategory phaseCat = budgetPhaseActive.getPhaseCategory();
                //Affecte la categorie "Phase du budget participatif" de la phase active au BP
                //La categorie est ajoutee dans le service context car le BP n'est pas encore cree
            	sc.setAssetCategoryIds(new long[] {phaseCat.getCategoryId()});
            }
        }

        if (sc.getWorkflowAction() == WorkflowConstants.ACTION_PUBLISH) {
            budget.setStatus(WorkflowConstants.STATUS_APPROVED);
        } else {
            budget.setStatus(WorkflowConstants.STATUS_DRAFT);
        }
        
        //Dans le cas d'un dépot de projet par un citoyen, on force le statut à déposé
	    if(budget.getAssetEntry() == null)    
	    	addCategoryDepose(sc);
        
        updateBudgetParticipatif(budget);
        updateAssetEntry(budget, sc);
        reindex(budget, false);
        return budget;
    }


    /**
     * méthode permettant d'ajouter la catégory déposé sur un nouveau budget
     *
     * @param sc le service context
     * @throws PortalException l'exception.
     */
    private void addCategoryDepose(ServiceContext sc) throws PortalException {
        AssetCategory category = AssetVocabularyHelper.getCategory(ParticiperCategories.BP_SUBMITTED.getName(), sc.getScopeGroupId());
        if (category == null) {
            throw new PortalException("aucunes catégories de connu");
        }
        long[] ids = sc.getAssetCategoryIds();
        if (ids.length != 0) {
            long[] newIds = new long[ids.length + 1];
            System.arraycopy(ids, 0, newIds, 0, ids.length);
            newIds[ids.length] = category.getCategoryId();
            sc.setAssetCategoryIds(newIds);
        } else {
            ids = new long[1];
            ids[0] = category.getCategoryId();
            sc.setAssetCategoryIds(ids);
        }

    }

    /**
     * Met à jour l'AssetEntry rattachée au budgetParticipatif
     */
    private void updateAssetEntry(BudgetParticipatif budget, ServiceContext sc)
            throws PortalException {
        assetEntryLocalService.updateEntry(sc.getUserId(), sc.getScopeGroupId(), budget.getCreateDate(),
                budget.getModifiedDate(), BudgetParticipatif.class.getName(), budget.getPrimaryKey(), budget.getUuid(),
                0, sc.getAssetCategoryIds(), sc.getAssetTagNames(), true, budget.isApproved(),
                budget.getCreateDate(), null, budget.getCreateDate(), null, ContentTypes.TEXT_HTML,
                budget.getTitle(), budget.getDescription(), budget.getDescription(), null, null,
                0, 0, null);
        reindex(budget, false);
    }

    /**
     * Reindex la budget dans le moteur de recherche
     */
    private void reindex(BudgetParticipatif budget, boolean delete) throws SearchException {
        Indexer<BudgetParticipatif> indexer = IndexerRegistryUtil
                .nullSafeGetIndexer(BudgetParticipatif.class);
        if (delete) {
            indexer.delete(budget);
        } else {
            indexer.reindex(budget);
        }
    }
    
    /**
	 * Retourne tous les budgets participatifs publies d'un groupe
	 */
	@Override
	public List<BudgetParticipatif> getPublishedByGroupId(long groupId) {
		return this.budgetParticipatifPersistence.findByStatusAndGroupId(WorkflowConstants.STATUS_APPROVED, groupId);
	}
	
	
	 /**
	 * Retourne tous les budgets participatifs publies d'un groupe
	 */
	public List<BudgetParticipatif> findActiveBPByGroupId(long groupId) {
		    return budgetParticipatifFinder.findByStatusAndActivePhaseAndGroupId(WorkflowConstants.STATUS_APPROVED, groupId);
		}
	
	 /**
     * Methode permettant de recuperer une liste de budgets participatifs trie par nombre de commentaires
     *
     * @param groupId ID du site
     * @return Liste des budgets participatifs triee par nombre de commentaires
     */
	@Override
    public List<BudgetParticipatif> getSortedByNbComments(long groupId, AssetCategory phase) {
        List<BudgetParticipatif> budgetsParticipatifs = this.budgetParticipatifPersistence.findByGroupId(groupId);
        
        // Verification d'un retour vide
        if (budgetsParticipatifs == null || budgetsParticipatifs.isEmpty())
            return new ArrayList<>();
        
        //Filtre les BP de la phase passee en parametre
        budgetsParticipatifs = budgetsParticipatifs
        		.stream()
        		.filter(budgetParticipatif -> budgetParticipatif.getStatus() == 0
        		&& AssetEntryLocalServiceUtil.hasAssetCategoryAssetEntry(phase.getCategoryId() ,budgetParticipatif.getAssetEntry().getEntryId()))
        		.collect(Collectors.toList());
        
        // Creation du comparateur
        Comparator<BudgetParticipatif> reversedMostPopularSizeComparator = Comparator
        		.comparingInt(BudgetParticipatif::getNbApprovedComments)
        		.reversed();
        
        return budgetsParticipatifs
                .stream()
                .sorted(reversedMostPopularSizeComparator)
                .collect(Collectors.toList());
    }
    
    /**
     * Methode permettant de recuperer une liste de budgets participatifs trie par nombre de soutiens
     *
     * @param groupId ID du site
     * @return Liste des budgets participatifs triee par nombre de soutiens
     */
	@Override
    public List<BudgetParticipatif> getSortedByNbSupports(long groupId, AssetCategory phase) {
        List<BudgetParticipatif> budgetsParticipatifs = this.budgetParticipatifPersistence.findByStatusAndGroupId(
        													WorkflowConstants.STATUS_APPROVED, 
        													groupId);
        
        // Verification d'un retour vide
        if (budgetsParticipatifs == null || budgetsParticipatifs.isEmpty())
            return new ArrayList<>();
        
        //Filtre les BP de la phase passee en parametre
        budgetsParticipatifs = budgetsParticipatifs
        		.stream()
        		.filter(bp -> AssetEntryLocalServiceUtil.hasAssetCategoryAssetEntry(phase.getCategoryId() ,bp.getAssetEntry().getEntryId()))
        		.collect((Collectors.toList()));
        
        // Creation du comparateur
        Comparator<BudgetParticipatif> reversedMostSupportedComparator = Comparator
        		.comparingLong(BudgetParticipatif::getNbSupports)
        		.reversed();
        
        return budgetsParticipatifs
                .stream()
                .sorted(reversedMostSupportedComparator)
                .collect(Collectors.toList());
    }
    
    /**
     * Recuperer le nombre voulu des budgets participatifs les plus commentes
     * @param groupId ID du site
     * @param delta Nombre de resultats max voulu
     * @return Liste des budgets participatifs les plus commentes triee.
     */
	@Override
    public List<BudgetParticipatif> getMostCommented(long groupId, int delta, AssetCategory phase) {
        List<BudgetParticipatif> budgetsParticipatifs = this.getSortedByNbComments(groupId, phase);      
        
        // Si la longueur de liste est inferieur a la taille voulu, aucun besoin de la couper
        if (budgetsParticipatifs.size() < delta)
            return budgetsParticipatifs;
        else 
        	return budgetsParticipatifs.stream().limit(delta).collect(Collectors.toList());
    }
	
	/**
     * Recuperer le nombre voulu des budgets participatifs les plus soutenus
     * @param groupId ID du site
     * @param delta Nombre de resultats max voulu
     * @return Liste des budgets participatifs les plus soutenus triee.
     */
	@Override
    public List<BudgetParticipatif> getMostSupported(long groupId, int delta, AssetCategory phase) {
        List<BudgetParticipatif> budgetsParticipatifs = this.getSortedByNbSupports(groupId, phase);
        
        // Si la longueur de liste est inferieur a la taille voulu, aucun besoin de la couper
        if (budgetsParticipatifs.size() < delta)
            return budgetsParticipatifs;
        else 
        	return budgetsParticipatifs.stream().limit(delta).collect(Collectors.toList());
    }
    
    /**
     * Recuperer les budgets participatifs "coup de coeur" les plus recents
     * @param groupId ID du site
     * @param delta Nombre de resultats max voulu
     * @return Liste des budgets participatifs coup de coeurs recent
     */
	@Override
    public List<BudgetParticipatif> getRecentIsCrushed(long groupId, int delta, AssetCategory phase) {
        List<BudgetParticipatif> budgetsParticipatifs = this.budgetParticipatifPersistence.findByisCrushAndPublished(
        													true,
        													WorkflowConstants.STATUS_APPROVED,
        													groupId);
        //Filtre les BP de la phase passee en parametre
        budgetsParticipatifs = budgetsParticipatifs
        		.stream()
        		.filter(bp -> AssetEntryLocalServiceUtil.hasAssetCategoryAssetEntry(phase.getCategoryId() ,bp.getAssetEntry().getEntryId()))
        		.collect((Collectors.toList()));
        
        
        // Si la longueur de liste est inferieur a la taille voulu, aucun besoin de la couper
        if (budgetsParticipatifs.size() < delta)
            return budgetsParticipatifs;
        else 
        	return budgetsParticipatifs.stream().limit(delta).collect(Collectors.toList());
    }
    
    /**
	 * Retourne tous les budgets participatifs d'une phase donnee
     */
	@Override
    public List<BudgetParticipatif> getByBudgetPhase(long budgetPhaseId) {
        return this.budgetParticipatifPersistence.findByBudgetPhaseId(budgetPhaseId);
    }
    
    /**
	 * Retourne tous les budgets participatifs suivis par un utilisateur et une phase donnes
     */
	@Override
    public List<BudgetParticipatif> getBudgetSupportedByPublikUserInPhase(String publikUserId, long budgetPhaseId) {
    	// Recuperation des soutiens de l'utilisateur
    	List<BudgetSupport> budgetSupports = this.budgetSupportPersistence.findByPublikUserId(publikUserId);
    	
    	List<BudgetParticipatif> budgetParticipatifs = new ArrayList<BudgetParticipatif>();
    	
    	try {
	    	// Recuperation de budgets correspondants
	    	for (BudgetSupport budgetSupport : budgetSupports) {
	    		BudgetParticipatif budgetParticipatif = BudgetParticipatifLocalServiceUtil.getBudgetParticipatif(budgetSupport.getBudgetParticipatifId());
	    		
	    		// Verification d'une phase existante pour les dits budgets
	    		if (budgetParticipatif.getPhase() != null) {
	    			budgetParticipatifs.add(budgetParticipatif);
	    		}
	    	}
	    	
	    	// Tri sur ceux correspondant a la phase donnee
	    	budgetParticipatifs = budgetParticipatifs
	        		.stream()
	        		.filter(budgetParticipatif -> budgetParticipatif.getPhase().getBudgetPhaseId() == budgetPhaseId)
	        		.collect(Collectors.toList());
	    	
    	} catch (PortalException e) {
    		_log.error("Erreur lors du retour des budgets soutenus par un utilisateur dans une phase donnee \n:" + e.getStackTrace());
		}
    	
    	return budgetParticipatifs;
    }
    
    /**
	 * Retourne le nombre de budgets participatifs suivis par un utilisateur et une phase donnes
     */
	@Override
    public int countBudgetSupportedByPublikUserInPhase(String publikUserId, long budgetPhaseId) {
    	List<BudgetParticipatif> budgetParticipatif = this.getBudgetSupportedByPublikUserInPhase(publikUserId, budgetPhaseId);
    	
    	return budgetParticipatif.size();
    }

    /**
     * Recherche par mot clés
     */
    @Override
    public List<BudgetParticipatif> findByKeyword(String keyword, long groupId, int start, int end) {
        DynamicQuery dynamicQuery = dynamicQuery();

        if (keyword.length() > 0) {
            dynamicQuery.add(RestrictionsFactoryUtil.like("title", "%" + keyword + "%"));
        }
        if (groupId > 0) {
            dynamicQuery.add(PropertyFactoryUtil.forName("groupId").eq(groupId));
        }

        return budgetParticipatifPersistence.findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * Recherche par mot clés (compte)
     */
    @Override
    public long findByKeywordCount(String keyword, long groupId) {
        DynamicQuery dynamicQuery = dynamicQuery();
        if (keyword.length() > 0) {
            dynamicQuery.add(RestrictionsFactoryUtil.like("title", "%" + keyword + "%"));
        }
        if (groupId > 0) {
            dynamicQuery.add(PropertyFactoryUtil.forName("groupId").eq(groupId));
        }
        return budgetParticipatifPersistence.countWithDynamicQuery(dynamicQuery);
    }

    /**
     * Met à jour le statut du budgetParticipatif "manuellement" (pas via le workflow)
     */
    @Override
    public void updateStatus(BudgetParticipatif budgetParticipatif, int status) throws PortalException {
        this.updateStatus(budgetParticipatif.getUserId(), budgetParticipatif.getBudgetParticipatifId(),
                status, null, null);
    }

    /**
     * mise a jour du status
     *
     * @param userId               l'identifiant de l'utilisateur
     * @param budgetParticipatifId l'identifiant du budget
     * @param status               le status
     * @param serviceContext       le service context
     * @param workflowContext      le context du workflow
     * @return le budget
     * @throws PortalException
     */
    @Override
    public BudgetParticipatif updateStatus(long userId, long budgetParticipatifId, int status,
                                           ServiceContext serviceContext,
                                           Map<String, Serializable> workflowContext)
            throws PortalException {
        Date now = new Date();
        BudgetParticipatif budgetParticipatif = this.getBudgetParticipatif(budgetParticipatifId);
        budgetParticipatif.setStatus(status);
        User user = UserLocalServiceUtil.fetchUser(userId);
        if (user != null) {
            budgetParticipatif.setStatusByUserId(user.getUserId());
            budgetParticipatif.setStatusByUserName(user.getFullName());
        }
        budgetParticipatif.setStatusDate(new Date());
        budgetParticipatif = updateBudgetParticipatif(budgetParticipatif);
        AssetEntry entry = assetEntryLocalService.getEntry(BudgetParticipatif.class.getName(), budgetParticipatif.getPrimaryKey());
        entry.setVisible(status == WorkflowConstants.STATUS_APPROVED);
        if (entry.isVisible()) {
            entry.setPublishDate(now);
        }
        assetEntryLocalService.updateAssetEntry(entry);
        reindex(budgetParticipatif, false);
        return budgetParticipatif;
    }
    
    @Override
    public List<BudgetParticipatif> getBudgetParticipatifByPublikUserID(String publikId){
        List<BudgetParticipatif> bpList = budgetParticipatifPersistence.findByPublikId(publikId);
        return bpList.stream()
                .filter(BudgetParticipatifModel::isApproved)
                .collect(Collectors.toList());
    }
    
    /**
     * Retourne les budgets votes par en utilisateur pour la phase en cours en ne prenant pas en compte les doublons
     * et les brouillons
     * @param publikUserId
     * @param budgetPhaseId
     */
    @Override
    public List<BudgetParticipatif> getPublishedAndVotedByPublikUserInPhase(String publikUserId, long budgetPhaseId) {
        List<BudgetParticipatif> bpList = this.getBudgetSupportedByPublikUserInPhase(publikUserId, budgetPhaseId);
        
        // Creation d'une liste sans doublons
        List<BudgetParticipatif> results = new ArrayList<BudgetParticipatif>();
        for (BudgetParticipatif budgetParticipatif : bpList) {
        	if (!results.contains(budgetParticipatif)) {
        		results.add(budgetParticipatif);
        	}
        }
        
        return results.stream()
                .filter(BudgetParticipatifModel::isApproved)
                .collect(Collectors.toList());
    }
    
    
    @Override
    public List<BudgetParticipatif> getByPublikUserID(String publikId){
        return budgetParticipatifPersistence.findByPublikId(publikId);
    }
    
}