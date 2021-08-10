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

package eu.strasbourg.service.council.service.impl;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.model.AssetLink;
import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.asset.kernel.service.AssetCategoryLocalServiceUtil;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
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
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import eu.strasbourg.service.council.constants.DeliberationDataConstants;
import eu.strasbourg.service.council.constants.StageDeliberation;
import eu.strasbourg.service.council.model.CouncilSession;
import eu.strasbourg.service.council.model.Deliberation;
import eu.strasbourg.service.council.model.Type;
import eu.strasbourg.service.council.service.CouncilSessionLocalServiceUtil;
import eu.strasbourg.service.council.service.VoteLocalServiceUtil;
import eu.strasbourg.service.council.service.base.DeliberationLocalServiceBaseImpl;
import eu.strasbourg.utils.AssetVocabularyHelper;
import eu.strasbourg.utils.constants.VocabularyNames;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

/**
 * The implementation of the deliberation local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link eu.strasbourg.service.council.service.DeliberationLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see DeliberationLocalServiceBaseImpl
 * @see eu.strasbourg.service.council.service.DeliberationLocalServiceUtil
 */
public class DeliberationLocalServiceImpl extends DeliberationLocalServiceBaseImpl {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never reference this class directly. Always use {@link eu.strasbourg.service.council.service.DeliberationLocalServiceUtil} to access the deliberation local service.
     */

    public final static Log log = LogFactoryUtil.getLog(DeliberationLocalServiceImpl.class);

    /**
     * Crée une entité vide avec une PK, non ajouté à la base de donnée
     */
    @Override
    public Deliberation createDeliberation(ServiceContext sc) throws PortalException {
        User user = UserLocalServiceUtil.getUser(sc.getUserId());

        long pk = this.counterLocalService.increment();
        Deliberation deliberation = this.deliberationLocalService.createDeliberation(pk);

        deliberation.setUserName(user.getFullName());
        deliberation.setUserId(sc.getUserId());
        deliberation.setGroupId(sc.getScopeGroupId());
        deliberation.setStatus(WorkflowConstants.STATUS_DRAFT);

        return deliberation;
    }

    /**
     * Met à jour une entité et l'enregistre en base de données
     */
    @Override
    public Deliberation updateDeliberation(Deliberation deliberation, ServiceContext sc) throws PortalException {
        User user = UserLocalServiceUtil.getUser(sc.getUserId());

        deliberation.setStatusByUserId(sc.getUserId());
        deliberation.setStatusByUserName(user.getFullName());
        deliberation.setStatusDate(sc.getModifiedDate());

        if (sc.getWorkflowAction() == WorkflowConstants.ACTION_PUBLISH) {
            deliberation.setStatus(WorkflowConstants.STATUS_APPROVED);
        } else {
            deliberation.setStatus(WorkflowConstants.STATUS_DRAFT);
        }
        deliberation = this.deliberationLocalService.updateDeliberation(deliberation);

        this.updateAssetEntry(deliberation, sc);
        this.reindex(deliberation, false);

        return deliberation;
    }

    /**
     * Met à jour l'AssetEntry rattachée à l'entité
     */
    private void updateAssetEntry(Deliberation deliberation, ServiceContext sc)
            throws PortalException {
        this.assetEntryLocalService.updateEntry(sc.getUserId(), // User ID
                sc.getScopeGroupId(), // Group ID
                deliberation.getCreateDate(), // Date of creation
                deliberation.getModifiedDate(), // Date of modification
                Deliberation.class.getName(), // Class name
                deliberation.getPrimaryKey(), // Class PK
                deliberation.getUuid(), // UUID
                0, // Class type ID
                sc.getAssetCategoryIds(), // Categories IDs
                sc.getAssetTagNames(), // Tags IDs
                true, // Listable
                deliberation.isApproved(), // Visible
                deliberation.getCreateDate(), // Start date
                null, // End date
                deliberation.getCreateDate(), // Publication date
                null, // Date of expiration
                ContentTypes.TEXT_HTML, // Content type
                deliberation.getTitle(), // Title
                deliberation.getTitle(), // Description
                deliberation.getTitle(), // Summary
                null, // URL
                null, // Layout uuid
                0, // Width
                0, // Height
                null); // Priority

        // Réindexe l'entité
        this.reindex(deliberation, false);
    }

    /**
     * Met à jour le statut de l'entité par le framework workflow
     */
    @Override
    public Deliberation updateStatus(long userId, long entryId, int status,
                                     ServiceContext sc, Map<String, Serializable> workflowContext)
            throws PortalException {
        Date now = new Date();
        // Statut de l'entité
        Deliberation deliberation = this.getDeliberation(entryId);
        deliberation.setStatus(status);
        User user = UserLocalServiceUtil.fetchUser(userId);
        if (user != null) {
            deliberation.setStatusByUserId(user.getUserId());
            deliberation.setStatusByUserName(user.getFullName());
        }
        deliberation.setStatusDate(new Date());
        deliberation = this.deliberationLocalService.updateDeliberation(deliberation);

        // Statut de l'entry
        AssetEntry entry = this.assetEntryLocalService
                .getEntry(Deliberation.class.getName(), deliberation.getPrimaryKey());
        entry.setVisible(status == WorkflowConstants.STATUS_APPROVED);
        if (entry.isVisible()) {
            entry.setPublishDate(now);
        }
        this.assetEntryLocalService.updateAssetEntry(entry);

        this.reindex(deliberation, false);

        return deliberation;
    }

    /**
     * Met à jour le statut de l'entité "manuellement" (pas via le workflow)
     */
    @Override
    public void updateStatus(Deliberation deliberation, int status) throws PortalException {
        this.updateStatus(deliberation.getUserId(), deliberation.getDeliberationId(), status, null,
                null);
    }

    /**
     * Supprime une entité
     */
    @Override
    public Deliberation removeDeliberation(long deliberationId) throws PortalException {
        AssetEntry entry = AssetEntryLocalServiceUtil
                .fetchEntry(Deliberation.class.getName(), deliberationId);

        if (entry != null) {
            // Supprime les liens avec les catégories
            for (long categoryId : entry.getCategoryIds()) {
                this.assetEntryLocalService.deleteAssetCategoryAssetEntry(
                        categoryId, entry.getEntryId());
            }

            // Supprime les liens avec les étiquettes
            long[] tagIds = AssetEntryLocalServiceUtil
                    .getAssetTagPrimaryKeys(entry.getEntryId());
            for (long tagId : tagIds) {
                AssetEntryLocalServiceUtil.deleteAssetTagAssetEntry(tagId,
                        entry.getEntryId());
            }

            // Supprime lien avec les autres entries
            List<AssetLink> links = this.assetLinkLocalService
                    .getLinks(entry.getEntryId());
            for (AssetLink link : links) {
                this.assetLinkLocalService.deleteAssetLink(link);
            }

            // Supprime l'AssetEntry
            AssetEntryLocalServiceUtil.deleteEntry(Deliberation.class.getName(), deliberationId);

            // Supprime les votes associés
            VoteLocalServiceUtil.removeVotesFromDeliberation(deliberationId);
        }

        // Supprime l'entité
        Deliberation deliberation = deliberationPersistence.remove(deliberationId);

        // Supprime l'index
        this.reindex(deliberation, true);

        // Supprime ce qui a rapport au workflow
        WorkflowInstanceLinkLocalServiceUtil.deleteWorkflowInstanceLinks(
                deliberation.getCompanyId(), deliberation.getGroupId(), Deliberation.class.getName(),
                deliberation.getDeliberationId());

        return deliberation;
    }

    /**
     * Reindex l'entité dans le moteur de recherche
     */
    private void reindex(Deliberation deliberation, boolean delete) throws SearchException {
        Indexer<Deliberation> indexer = IndexerRegistryUtil
                .nullSafeGetIndexer(Deliberation.class);
        if (delete) {
            indexer.delete(deliberation);
        } else {
            indexer.reindex(deliberation);
        }
    }

    /**
     * Recherche par ID de CouncilSession
     */
    @Override
    public List<Deliberation> findByCouncilSessionId(long councilSessionId) {
        return this.deliberationPersistence.findByCouncilSessionId(councilSessionId);
    }

    /**
     * Import des délibérations à partir du fichier csv
     * Création ou mise à jour ou suppression en base de données
     */
    @Override
    public String importData(List<Map<String, String>> recordsMapList, ServiceContext serviceContext, long councilSessionId, ThemeDisplay themeDisplay) throws PortalException {

        Deliberation deliberation;

        CouncilSession council = CouncilSessionLocalServiceUtil.fetchCouncilSession(councilSessionId);
        updateServiceContextForCategory(serviceContext, themeDisplay, council);

        // Récupération des délibérations à partir du councilId et transformation en map pour les parcourir
        List<Deliberation> deliberationsInDbList = deliberationLocalService.findByCouncilSessionId(councilSessionId);

        Map<Integer, Deliberation> deliberationMap = new HashMap<>();
        for (Deliberation delib : deliberationsInDbList) {
            deliberationMap.put(delib.getOrder(), delib);
        }

        int i = 0;

        List<Integer> orderFileList = new ArrayList<>();
        for (Map<String, String> record : recordsMapList) {
            i++;
            if (i % 1000 == 0) {
                log.info("Importing deliberation " + i);
            }

            // Récupération des données de la ligne CSV
            try {
                int order = Integer.parseInt(record.get(DeliberationDataConstants.ORDER));
                String title = record.get(DeliberationDataConstants.TITLE);

                orderFileList.add(order);
                if (!deliberationMap.containsKey(order)) {
                    // Création d'une nouvelle délibération
                    deliberation = this.createDeliberation(serviceContext);
                    deliberation.setOrder(order);
                    deliberation.setTitle(title);
                    deliberation.setCouncilSessionId(councilSessionId);
                    deliberation.setStage(StageDeliberation.get(1).getName());

                    this.updateDeliberation(deliberation, serviceContext);

                    deliberation.setStatusDate(new Date());
                    deliberationLocalService.updateDeliberation(deliberation);
                } else {
                    Deliberation deliberationBDD = deliberationMap.get(order);
                    if (deliberationBDD.isCree()) {
                        if (!deliberationBDD.getTitle().equals(title)) {
                            // Mise à jour d'une délibération existante
                            deliberationBDD.setTitle(title);
                            deliberationBDD.setOrder(order);
                            this.updateDeliberation(deliberationBDD, serviceContext);
                            deliberationBDD.setStatusDate(new Date());
                            deliberationLocalService.updateDeliberation(deliberationBDD);
                        }
                    }
                }
            } catch (NumberFormatException e) {
                log.error(e);
                return "Une erreur s'est produite, l'order : " + record.get(DeliberationDataConstants.ORDER) + " n'est pas un entier";
            }
        }
        // Si la déliberation n'existe pas dans le fichier
        List<Deliberation> deliberationsStatutCreesList = deliberationsInDbList.stream().filter(Deliberation::isCree).collect(Collectors.toList());

        for (Deliberation delib : deliberationsStatutCreesList) {
            if (!orderFileList.contains(delib.getOrder())) {
                this.removeDeliberation(delib.getDeliberationId());
            }
        }
        return null;
    }

    /**
     * Permet de mettre à jour le ServiceContext avec les informations des catégories
     */
    private void updateServiceContextForCategory(ServiceContext serviceContext, ThemeDisplay themeDisplay, CouncilSession council) {

        // Récupère la catégorie du conseil
        AssetVocabulary conseil = AssetVocabularyHelper.getVocabulary(VocabularyNames.COUNCIL_SESSION, themeDisplay.getScopeGroupId());
        Type type = this.typeLocalService.fetchType(council.getTypeId());
        AssetCategory typeCategory = conseil.getCategories().stream().filter(c -> c.getName().equals(type.getTitle())).findFirst().get();
        AssetCategory councilCategory = AssetCategoryLocalServiceUtil.getChildCategories(typeCategory.getCategoryId()).stream().filter(c -> c.getName().equals(council.getTitle())).findFirst().get();

        AssetCategory stageCategory = AssetVocabularyHelper.getCategory(StageDeliberation.CREE.getName(), themeDisplay.getScopeGroupId());

        if (councilCategory != null && stageCategory != null) {
            serviceContext.setAssetCategoryIds(new long[]{councilCategory.getCategoryId(), stageCategory.getCategoryId()});
        } else if (councilCategory != null)
            serviceContext.setAssetCategoryIds(new long[]{councilCategory.getCategoryId()});
        else if (stageCategory != null)
            serviceContext.setAssetCategoryIds(new long[]{stageCategory.getCategoryId()});
    }
}