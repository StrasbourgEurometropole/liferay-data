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

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.model.AssetLink;
import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
import com.liferay.asset.kernel.service.AssetVocabularyLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.service.ClassNameLocalServiceUtil;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.service.WorkflowInstanceLinkLocalServiceUtil;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import eu.strasbourg.service.project.model.Participation;
import eu.strasbourg.service.project.model.PlacitPlace;
import eu.strasbourg.service.project.service.base.ParticipationLocalServiceBaseImpl;
import eu.strasbourg.utils.AssetVocabularyHelper;
import eu.strasbourg.utils.constants.FriendlyURLs;
import eu.strasbourg.utils.constants.VocabularyNames;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

/**
 * The implementation of the participation local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link eu.strasbourg.service.project.service.ParticipationLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Cedric Henry
 * @see ParticipationLocalServiceBaseImpl
 * @see eu.strasbourg.service.project.service.ParticipationLocalServiceUtil
 */
public class ParticipationLocalServiceImpl
        extends ParticipationLocalServiceBaseImpl {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never reference this class directly. Always use {@link eu.strasbourg.service.project.service.ParticipationLocalServiceUtil} to access the participation local service.
     */

    private final Log _log = LogFactoryUtil.getLog(this.getClass());

    /**
     * Crée une participation vide avec une PK, non ajouté à la base de donnée
     */
    @Override
    public Participation createParticipation(ServiceContext sc)
            throws PortalException {
        User user = UserLocalServiceUtil.getUser(sc.getUserId());

        long pk = counterLocalService.increment();

        Participation participation = this.participationLocalService
                .createParticipation(pk);

        participation.setGroupId(sc.getScopeGroupId());
        participation.setUserName(user.getFullName());
        participation.setUserId(sc.getUserId());

        participation.setStatus(WorkflowConstants.STATUS_DRAFT);

        return participation;
    }

    /**
     * Met à jour une participation et l'enregistre en base de données
     *
     * @throws IOException
     */
    @Override
    public Participation updateParticipation(Participation participation, ServiceContext sc)
            throws PortalException {
        User user = UserLocalServiceUtil.getUser(sc.getUserId());

        participation.setStatusByUserId(sc.getUserId());
        participation.setStatusByUserName(user.getFullName());
        participation.setStatusDate(sc.getModifiedDate());

        if (sc.getWorkflowAction() == WorkflowConstants.ACTION_PUBLISH) {
            participation.setStatus(WorkflowConstants.STATUS_APPROVED);
        } else {
            participation.setStatus(WorkflowConstants.STATUS_DRAFT);
        }
        participation = this.participationLocalService.updateParticipation(participation);

        this.updateAssetEntry(participation, sc);
        this.reindex(participation, false);

        this.updateAllParticipationsStatus();

        return participation;
    }

    /**
     * Met à jour l'AssetEntry rattachée à la participation
     */
    private void updateAssetEntry(Participation participation, ServiceContext sc)
            throws PortalException {
        this.assetEntryLocalService.updateEntry(sc.getUserId(), // User ID
                sc.getScopeGroupId(), // Group ID
                participation.getCreateDate(), // Date of creation
                participation.getModifiedDate(), // Date of modification
                Participation.class.getName(), // Class name
                participation.getPrimaryKey(), // Class PK
                participation.getUuid(), // UUID
                0, // Class type ID
                sc.getAssetCategoryIds(), // Categories IDs
                sc.getAssetTagNames(), // Tags IDs
                true, // Listable
                participation.isApproved(), // Visible
                participation.getCreateDate(), // Start date
                null, // End date
                participation.getCreateDate(), // Publication date
                null, // Date of expiration
                ContentTypes.TEXT_HTML, // Content type
                participation.getTitle(), // Title
                participation.getDescriptionBody(), // Description
                participation.getDescriptionBody(), // Summary
                null, // URL
                null, // Layout uuid
                0, // Width
                0, // Height
                null); // Priority

        // Réindexe la participation
        this.reindex(participation, false);
    }

    /**
     * Met à jour le statut de la participation par le framework workflow
     */
    @Override
    public Participation updateStatus(long userId, long entryId, int status,
                                      ServiceContext sc, Map<String, Serializable> workflowContext)
            throws PortalException {
        Date now = new Date();
        // Statut de l'entité
        Participation participation = this.getParticipation(entryId);
        participation.setStatus(status);
        User user = UserLocalServiceUtil.fetchUser(userId);
        if (user != null) {
            participation.setStatusByUserId(user.getUserId());
            participation.setStatusByUserName(user.getFullName());
        }
        participation.setStatusDate(new Date());
        participation = this.participationLocalService.updateParticipation(participation);

        // Statut de l'entry
        AssetEntry entry = this.assetEntryLocalService
                .getEntry(Participation.class.getName(), participation.getPrimaryKey());
        entry.setVisible(status == WorkflowConstants.STATUS_APPROVED);
        if (entry.isVisible()) {
            entry.setPublishDate(now);
        }
        this.assetEntryLocalService.updateAssetEntry(entry);

        this.reindex(participation, false);

        return participation;
    }

    /**
     * Met à jour le statut de la participation "manuellement" (pas via le workflow)
     */
    @Override
    public void updateStatus(Participation participation, int status) throws PortalException {
        this.updateStatus(participation.getUserId(), participation.getParticipationId(), status, null,
                null);
    }

    /**
     * Supprime une participation
     */
    @Override
    public Participation removeParticipation(long participationId) throws PortalException {
        AssetEntry entry = AssetEntryLocalServiceUtil
                .fetchEntry(Participation.class.getName(), participationId);

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
            AssetEntryLocalServiceUtil.deleteEntry(Participation.class.getName(),
                    participationId);

            // Supprime les lieux
            List<PlacitPlace> placitPlaces = this.placitPlaceLocalService
                    .getByParticipation(participationId);
            for (PlacitPlace placitPlace : placitPlaces) {
                this.placitPlaceLocalService.removePlacitPlace(
                        placitPlace.getPlacitPlaceId());
            }

        }

        // Supprime la participation
        Participation participation = participationPersistence.remove(participationId);

        // Delete the index
        this.reindex(participation, true);

        // Supprime ce qui a rapport au workflow
        WorkflowInstanceLinkLocalServiceUtil.deleteWorkflowInstanceLinks(
                participation.getCompanyId(), participation.getGroupId(),
                Participation.class.getName(), participation.getParticipationId());

        return participation;
    }

    /**
     * Met a jour le statut de toutes les participations
     *
     * @throws PortalException
     */
    public void updateAllParticipationsStatus() throws PortalException {
        _log.info("Start checking participations status");

        // Recupere l'ID par defaut du portal
        long companyId = PortalUtil.getDefaultCompanyId();
        // Recupere le groupe du site via son nom
        Group group = GroupLocalServiceUtil.getFriendlyURLGroup(companyId, FriendlyURLs.PLACIT_URL);

        if (group != null) {
            // Recupere l'ID du site via son nom
            long groupId = group.getGroupId();
            // Recupere toutes les participations du site
            List<Participation> participations = this.participationPersistence.findByGroupId(groupId);
            // Recupere l'ID du vocabulaire de 'Statut participer'
            long vocId = AssetVocabularyHelper.getVocabulary(VocabularyNames.PLACIT_STATUS, groupId).getVocabularyId();
            AssetEntry entry = null;
            AssetCategory removedCategory = null;
            AssetCategory addedCategory = null;
            int nbUpdatedPart = 0;

            for (Participation participation : participations) {
                entry = participation.getAssetEntry();

                // Cherche le precedent statut
                for (AssetCategory cat : entry.getCategories()) {
                    if (cat.getVocabularyId() == vocId) {
                        removedCategory = cat;
                    }
                }

                // Calcule le statut et cherche la categorie associee
                switch (participation.getParticipationStatus()) {
                    case "soon_arrived":
                        addedCategory = AssetVocabularyHelper.getCategory("a venir", groupId);
                        break;
                    case "finished":
                        addedCategory = AssetVocabularyHelper.getCategory("terminee", groupId);
                        break;
                    case "soon_finished":
                        addedCategory = AssetVocabularyHelper.getCategory("bientot terminee", groupId);
                        break;
                    case "new":
                        addedCategory = AssetVocabularyHelper.getCategory("nouvelle", groupId);
                        break;
                    case "in_progress":
                        addedCategory = AssetVocabularyHelper.getCategory("en cours", groupId);
                        break;
                }

                // Si il y a eu changement de statut
                boolean isChanged = removedCategory != null && removedCategory.getCategoryId() != addedCategory.getCategoryId();

                // Supprime le precedent statut
                if (isChanged) {
                    AssetVocabularyHelper.removeCategoryToAssetEntry(removedCategory, entry);
                }

                // Ajoute la categorie
                if ((addedCategory != null && removedCategory == null) || (addedCategory != null && isChanged)) {
                    AssetVocabularyHelper.addCategoryToAssetEntry(addedCategory, entry);
                    this.reindex(participation, false);
                    nbUpdatedPart++;
                }

            }

            _log.info("Updated " + nbUpdatedPart + " participations status");
        }

    }

    /**
     * méthode permettant de récupérer les 3 premières participations de la liste.
     * @param groupId le grouptId
     * @return la liste de participation.
     */
    public List<Participation> getMostCommented(long groupId) {
        List<Participation> participationList = getSortedParticipations(groupId);
        return participationList.stream().limit(3).collect(Collectors.toList());
    }

    /**
     * méthode permettant de récuperer une liste de participation trié par nombre de commentaires
     *
     * @param groupId le grouptId
     * @return la liste de participation.
     */
    private List<Participation> getSortedParticipations(long groupId) {
        List<Participation> participations = participationPersistence.findByGroupId(groupId);
        participations = participations.stream().filter(participation -> participation.getStatus() == 0).collect(Collectors.toList());
        Comparator<Participation> reversedMostPopularSizeComparator
                = Comparator.comparingInt(Participation::getNbApprovedComments).reversed();
        return participations
                .stream()
                .sorted(reversedMostPopularSizeComparator)
                .collect(Collectors.toList());
    }

    /**
     * méthode permettant de récupérer les 3 dernieres participations de la liste.
     * @param groupId le grouptId
     * @return la liste de participation.
     */
    public List<Participation> getLessCommented(long groupId) {
        List<Participation> participationList = getSortedParticipations(groupId);
        return participationList.stream().skip(participationList.size() - 3).collect(Collectors.toList());
    }

    /**
     * Reindex la participation dans le moteur de recherche
     */
    private void reindex(Participation participation, boolean delete) throws SearchException {
        Indexer<Participation> indexer = IndexerRegistryUtil
                .nullSafeGetIndexer(Participation.class);
        if (delete) {
            indexer.delete(participation);
        } else {
            indexer.reindex(participation);
        }
    }

    /**
     * Renvoie la liste des vocabulaires rattachés à une participation
     */
    @Override
    public List<AssetVocabulary> getAttachedVocabularies(long groupId) {
        List<AssetVocabulary> vocabularies = AssetVocabularyLocalServiceUtil
                .getAssetVocabularies(-1, -1);
        List<AssetVocabulary> attachedVocabularies = new ArrayList<AssetVocabulary>();
        long classNameId = ClassNameLocalServiceUtil
                .getClassNameId(Participation.class);
        for (AssetVocabulary vocabulary : vocabularies) {
            if (vocabulary.getGroupId() == groupId
                    && LongStream.of(vocabulary.getSelectedClassNameIds())
                    .anyMatch(c -> c == classNameId)) {
                attachedVocabularies.add(vocabulary);
            }
        }
        return attachedVocabularies;
    }

    /**
     * Retourne toutes les participations d'un groupe
     */
    @Override
    public List<Participation> getByGroupId(long groupId) {
        return this.participationPersistence.findByGroupId(groupId);
    }

    /**
     * Recherche par mot clés
     */
    @Override
    public List<Participation> findByKeyword(String keyword, long groupId, int start,
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

        return participationPersistence.findWithDynamicQuery(dynamicQuery, start, end);
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

        return participationPersistence.countWithDynamicQuery(dynamicQuery);
    }

}