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

package eu.strasbourg.service.project.service.impl;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.model.AssetLink;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.service.WorkflowInstanceLinkLocalServiceUtil;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import eu.strasbourg.service.project.model.BudgetParticipatif;
import eu.strasbourg.service.project.model.Initiative;
import eu.strasbourg.service.project.model.InitiativeModel;
import eu.strasbourg.service.project.model.PlacitPlace;
import eu.strasbourg.service.project.model.Project;
import eu.strasbourg.service.project.service.base.InitiativeLocalServiceBaseImpl;

/**
 * The implementation of the initiative local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link eu.strasbourg.service.project.service.InitiativeLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Cedric Henry
 * @see InitiativeLocalServiceBaseImpl
 * @see eu.strasbourg.service.project.service.InitiativeLocalServiceUtil
 */
public class InitiativeLocalServiceImpl extends InitiativeLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link eu.strasbourg.service.project.service.InitiativeLocalServiceUtil} to access the initiative local service.
	 */
	
	
	/**
	 * Crée une initiative vide avec une PK, non ajouté à la base de donnée
	 */
	@Override
	public Initiative createInitiative(ServiceContext sc)
		throws PortalException {
		User user = UserLocalServiceUtil.getUser(sc.getUserId());

		long pk = counterLocalService.increment();

		Initiative initiative = this.initiativeLocalService
			.createInitiative(pk);

		initiative.setGroupId(sc.getScopeGroupId());
		initiative.setUserName(user.getFullName());
		initiative.setUserId(sc.getUserId());

		initiative.setStatus(WorkflowConstants.STATUS_DRAFT);

		return initiative;
	}
	
	/**
	 * Met à jour une initiative et l'enregistre en base de données
	 * @throws IOException 
	 */
	@Override
	public Initiative updateInitiative(Initiative initiative, ServiceContext sc)
			throws PortalException {
		User user = UserLocalServiceUtil.getUser(sc.getUserId());

		initiative.setStatusByUserId(sc.getUserId());
		initiative.setStatusByUserName(user.getFullName());
		initiative.setStatusDate(sc.getModifiedDate());

		if (sc.getWorkflowAction() == WorkflowConstants.ACTION_PUBLISH) {
			initiative.setStatus(WorkflowConstants.STATUS_APPROVED);
			initiative.setStatusDate(new Date());
		} else {
			initiative.setStatus(WorkflowConstants.STATUS_DRAFT);
		}
		initiative = this.initiativeLocalService.updateInitiative(initiative);
		
		this.updateAssetEntry(initiative, sc);
		this.reindex(initiative, false);

		return initiative;
	}
	
	/**
     * Met à jour le statut de l'initiative "manuellement" (pas via le workflow)
     */
    @Override
    public void updateStatus(Initiative initiative, int status) throws PortalException {
        this.updateStatus(initiative.getUserId(), initiative.getInitiativeId(), status, null,
                null);
    }
    
    /**
	 * Met à jour le statut de l'initiative par le framework workflow
	 */
	@Override
	public Initiative updateStatus(long userId, long entryId, int status,
								ServiceContext sc, Map<String, Serializable> workflowContext)
			throws PortalException {
		Date now = new Date();
		// Statut de l'entité
		Initiative initiative = this.getInitiative(entryId);
		initiative.setStatus(status);
		User user = UserLocalServiceUtil.fetchUser(userId);
		if (user != null) {
			initiative.setStatusByUserId(user.getUserId());
			initiative.setStatusByUserName(user.getFullName());
		}
		initiative.setStatusDate(new Date());
		initiative = this.initiativeLocalService.updateInitiative(initiative);

		// Statut de l'entry
		AssetEntry entry = this.assetEntryLocalService
				.getEntry(Initiative.class.getName(), initiative.getPrimaryKey());
		entry.setVisible(status == WorkflowConstants.STATUS_APPROVED);
		if (entry.isVisible()) {
			entry.setPublishDate(now);
		}
		this.assetEntryLocalService.updateAssetEntry(entry);

		this.reindex(initiative, false);

		return initiative;
	}
	
	/**
	 * Met à jour l'AssetEntry rattachée à la initiative
	 */
	private void updateAssetEntry(Initiative initiative, ServiceContext sc)
			throws PortalException {
		this.assetEntryLocalService.updateEntry(sc.getUserId(), // User ID
				sc.getScopeGroupId(), // Group ID
				initiative.getCreateDate(), // Date of creation
				initiative.getModifiedDate(), // Date of modification
				Initiative.class.getName(), // Class name
				initiative.getPrimaryKey(), // Class PK
				initiative.getUuid(), // UUID
				0, // Class type ID
				sc.getAssetCategoryIds(), // Categories IDs
				sc.getAssetTagNames(), // Tags IDs
				true, // Listable
				initiative.isApproved(), // Visible
				initiative.getCreateDate(), // Start date
				null, // End date
				initiative.getCreateDate(), // Publication date
				null, // Date of expiration
				ContentTypes.TEXT_HTML, // Content type
				initiative.getTitle(), // Title
				initiative.getDescription(), // Description
				initiative.getDescription(), // Summary
				null, // URL
				null, // Layout uuid
				0, // Width
				0, // Height
				null); // Priority

		// Réindexe la initiative
		this.reindex(initiative, false);
	}
	
	/**
	 * Retourne toutes les initiatives d'un groupe
	 */
	@Override
	public List<Initiative> getByGroupId(long groupId) {
		return this.initiativePersistence.findByGroupId(groupId);
	}
	
	/**
	 * Retourne toutes les initiatives publiées d'un groupe
	 */
	@Override
	public List<Initiative> getPublishedByGroupId(long groupId) {
		return this.initiativePersistence.findByStatusAndGroupId(WorkflowConstants.STATUS_APPROVED, groupId);
	}
	
	/**
	 * Supprime une initiative
	 */
	@Override
	public Initiative removeInitiative(long initiativeId) throws PortalException {
		AssetEntry entry = AssetEntryLocalServiceUtil
				.fetchEntry(Initiative.class.getName(), initiativeId);

		if (entry != null) {
			// Delete the link with categories
			for (long categoryId : entry.getCategoryIds()) {
				this.assetEntryLocalService.deleteAssetCategoryAssetEntry(
						categoryId, entry.getEntryId());
			}

			// Delete the link with tags
			long[] tagIds = AssetEntryLocalServiceUtil
					.getAssetTagPrimaryKeys(entry.getEntryId());
			for (int i = 0; i < tagIds.length; i++) {
				AssetEntryLocalServiceUtil.deleteAssetTagAssetEntry(tagIds[i],
						entry.getEntryId());
			}

			// Supprime lien avec les autres entries
			List<AssetLink> links = this.assetLinkLocalService
					.getLinks(entry.getEntryId());
			for (AssetLink link : links) {
				this.assetLinkLocalService.deleteAssetLink(link);
			}

			// Delete the AssetEntry
			AssetEntryLocalServiceUtil.deleteEntry(Initiative.class.getName(),
					initiativeId);
			
			// Supprime les lieux
			List<PlacitPlace> placitPlaces = this.placitPlaceLocalService
				.getByInitiative(initiativeId);
			for (PlacitPlace placitPlace : placitPlaces) {
				this.placitPlaceLocalService.removePlacitPlace(
						placitPlace.getPlacitPlaceId());
			}

		}

		// Supprime la initiative
		Initiative initiative = initiativePersistence.remove(initiativeId);

		// Delete the index
		this.reindex(initiative, true);

		// Supprime ce qui a rapport au workflow
		WorkflowInstanceLinkLocalServiceUtil.deleteWorkflowInstanceLinks(
				initiative.getCompanyId(), initiative.getGroupId(),
				Initiative.class.getName(), initiative.getInitiativeId());

		return initiative;
	}
	
	/**
	 * Reindex la initiative dans le moteur de recherche
	 */
	private void reindex(Initiative initiative, boolean delete) throws SearchException {
		Indexer<Initiative> indexer = IndexerRegistryUtil
				.nullSafeGetIndexer(Initiative.class);
		if (delete) {
			indexer.delete(initiative);
		} else {
			indexer.reindex(initiative);
		}
	}
	
	/**
	 * Recherche par mot clés (compte)
	 */
	@Override
	public long findByKeywordCount(String keyword, long groupId) {
		DynamicQuery dynamicQuery = dynamicQuery();
		if (keyword.length() > 0) {
			dynamicQuery.add(
				RestrictionsFactoryUtil.like("title", "%" + keyword + "%"));
		}
		if (groupId > 0) {
			dynamicQuery
				.add(PropertyFactoryUtil.forName("groupId").eq(groupId));
		}

		return initiativePersistence.countWithDynamicQuery(dynamicQuery);
	}

	public List<Initiative> findByPublikUserId(String publikUserId){
		List<Initiative> result = initiativePersistence.findBypublikId(publikUserId);
		
		return result.stream()
                .filter(InitiativeModel::isApproved)
                .collect(Collectors.toList());
	}
	
	public List<Initiative> findHelpedByPublikUserId(String publikUserId){
		List<Initiative> result = initiativePersistence.findBypublikId(publikUserId);
		return result;
	}
	
	/**
	 * Recherche par mot clés
	 */
	@Override
	public List<Initiative> findByKeyword(String keyword, long groupId, int start,
		int end) {
		DynamicQuery dynamicQuery = dynamicQuery();

		if (keyword.length() > 0) {
			dynamicQuery.add(
				RestrictionsFactoryUtil.like("title", "%" + keyword + "%"));
		}
		if (groupId > 0) {
			dynamicQuery
				.add(PropertyFactoryUtil.forName("groupId").eq(groupId));
		}

		return initiativePersistence.findWithDynamicQuery(dynamicQuery, start, end);
	}
	
	/**
     * Methode permettant de recuperer une liste d'initiatives triee par nombre de commentaires
     *
     * @param groupId ID du site
     * @return Liste des initiatives triee par nombre de commentaires
     */
	@Override
    public List<Initiative> getSortedByNbComments(long groupId) {
        List<Initiative> initiatives = this.initiativePersistence.findByGroupId(groupId);
        
        // Verification d'un retour vide
        if (initiatives == null || initiatives.isEmpty())
            return new ArrayList<>();
        
        initiatives = initiatives
        		.stream()
        		.filter(initiative -> initiative.getStatus() == 0)
        		.collect(Collectors.toList());
        
        // Creation du comparateur
        Comparator<Initiative> reversedMostPopularSizeComparator = Comparator
        		.comparingInt(Initiative::getNbApprovedComments)
        		.reversed();
        
        return initiatives
                .stream()
                .sorted(reversedMostPopularSizeComparator)
                .collect(Collectors.toList());
    }
    
    /**
     * Methode permettant de recuperer une liste d'initiatives triee par nombre de soutiens
     *
     * @param groupId ID du site
     * @return Liste des budgets participatifs triee par nombre de soutiens
     */
	@Override
    public List<Initiative> getSortedByNbHelps(long groupId) {
        List<Initiative> initiatives = this.initiativePersistence.findByStatusAndGroupId(
        													WorkflowConstants.STATUS_APPROVED, 
        													groupId);
        
        // Verification d'un retour vide
        if (initiatives == null || initiatives.isEmpty())
            return new ArrayList<>();
        
        // Creation du comparateur
        Comparator<Initiative> reversedMostHelpedComparator = Comparator
        		.comparingLong(Initiative::getNbHelps)
        		.reversed();
        
        return initiatives
                .stream()
                .sorted(reversedMostHelpedComparator)
                .collect(Collectors.toList());
    }
	
	/**
     * Methode permettant de recuperer une liste d'initiatives triee par nombre de soutiens
     *
     * @param groupId ID du site
     * @return Liste d'initiatives triee par nombre de soutiens
     */
	@Override
    public List<Initiative> getSortedByNbLikes(long groupId) {
        List<Initiative> initiatives = this.initiativePersistence.findByStatusAndGroupId(
        													WorkflowConstants.STATUS_APPROVED, 
        													groupId);
        
        // Verification d'un retour vide
        if (initiatives == null || initiatives.isEmpty())
            return new ArrayList<>();
        
        // Creation du comparateur
        Comparator<Initiative> reversedMostLikedComparator = Comparator
        		.comparingLong(Initiative::getNbLikes)
        		.reversed();
        
        return initiatives
                .stream()
                .sorted(reversedMostLikedComparator)
                .collect(Collectors.toList());
    }
    
    /**
     * Recuperer le nombre voulu d'initiatives les plus commentes
     * @param groupId ID du site
     * @param delta Nombre de resultats max voulu
     * @return Liste d'initiatives les plus commentes triee.
     */
	@Override
    public List<Initiative> getMostCommented(long groupId, int delta) {
        List<Initiative> initiatives = this.getSortedByNbComments(groupId);
        
        // Si la longueur de liste est inferieur a la taille voulu, aucun besoin de la couper
        if (initiatives.size() < delta)
            return initiatives;
        else
        	return initiatives.stream().limit(delta).collect(Collectors.toList());
    }
	
	/**
     * Recuperer le nombre voulu d'initiatives les plus soutenus
     * @param groupId ID du site
     * @param delta Nombre de resultats max voulu
     * @return Liste d'initiatives les plus aidé triee.
     */
	@Override
    public List<Initiative> getMostHelped(long groupId, int delta) {
        List<Initiative> initiatives = this.getSortedByNbHelps(groupId);
        
        // Si la longueur de liste est inferieur a la taille voulu, aucun besoin de la couper
        if (initiatives.size() < delta)
            return initiatives;
        else 
        	return initiatives.stream().limit(delta).collect(Collectors.toList());
    }
	
	/**
     * Recuperer le nombre voulu d'initiatives les plus soutenus
     * @param groupId ID du site
     * @param delta Nombre de resultats max voulu
     * @return Liste d'initiatives les plus aidé triee.
     */
	@Override
    public List<Initiative> getMostLiked(long groupId, int delta) {
        List<Initiative> initiatives = this.getSortedByNbLikes(groupId);
        
        // Si la longueur de liste est inferieur a la taille voulu, aucun besoin de la couper
        if (initiatives.size() < delta)
            return initiatives;
        else 
        	return initiatives.stream().limit(delta).collect(Collectors.toList());
    }
	
	 @Override
    public List<Initiative> getByPublikUserID(String publikId){
		 return initiativePersistence.findBypublikId(publikId);
    }
	
}