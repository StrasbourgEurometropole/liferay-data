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
import eu.strasbourg.service.project.model.Petition;
import eu.strasbourg.service.project.model.PlacitPlace;
import eu.strasbourg.service.project.service.base.PetitionLocalServiceBaseImpl;
import eu.strasbourg.utils.constants.FriendlyURLs;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.LongStream;

/**
 * The implementation of the petition local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link eu.strasbourg.service.project.service.PetitionLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Cedric Henry
 * @see PetitionLocalServiceBaseImpl
 * @see eu.strasbourg.service.project.service.PetitionLocalServiceUtil
 */
public class PetitionLocalServiceImpl extends PetitionLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link eu.strasbourg.service.project.service.PetitionLocalServiceUtil} to access the petition local service.
	 */

	public final Log _log = LogFactoryUtil.getLog(this.getClass().getName());

	@Override
	public Petition createPetition(long petitionId) {
		return super.createPetition(petitionId);
	}
    @Override
	public Petition updatePetition(Petition petition, ServiceContext sc) throws PortalException {
		if (sc.getWorkflowAction()==WorkflowConstants.ACTION_PUBLISH){
			petition.setStatus(WorkflowConstants.STATUS_APPROVED);
		}else {
			petition.setStatus(WorkflowConstants.STATUS_DRAFT);
		}
		updatePetition(petition);
		updateAssetEntry(petition,sc);
		reindex(petition,false);
		return petition;
	}

    /**
     * Met à jour l'AssetEntry rattachée à la petition
     */
    private void updateAssetEntry(Petition petition, ServiceContext sc)
            throws PortalException {
        assetEntryLocalService.updateEntry(sc.getUserId(),sc.getScopeGroupId(),petition.getCreateDate(),
                petition.getModifiedDate(),Petition.class.getName(), petition.getPrimaryKey(),petition.getUuid(),
                0,sc.getAssetCategoryIds(),sc.getAssetTagNames(),true,petition.isApproved(),
                petition.getCreateDate(),null,petition.getCreateDate(),null, ContentTypes.TEXT_HTML,
                petition.getTitle(),petition.getDescription(),petition.getDescription(),null,null,
                0,0,null);
        reindex(petition,false);
    }
    /**
     * Met à jour le statut du petition "manuellement" (pas via le workflow)
     */
    @Override
    public void updateStatus(Petition petition, int status) throws PortalException {
        this.updateStatus(petition.getUserId(), petition.getPetitionId(), status, null,
                null);
    }
    @Override
    public Petition updateStatus(long userId, long petitionId, int status,
                                  ServiceContext serviceContext,
                                  Map<String, Serializable> workflowContext)
            throws PortalException {
        Date now = new Date();
        Petition petition = this.getPetition(petitionId);
        petition.setStatus(status);
        User user = UserLocalServiceUtil.fetchUser(userId);
        if (user != null){
            petition.setStatusByUserId(user.getUserId());
            petition.setStatusByUserName(user.getFullName());
        }
        petition.setStatusDate(new Date());
        petition = updatePetition(petition);
        AssetEntry entry = assetEntryLocalService.getEntry(Petition.class.getName(),petition.getPrimaryKey());
        entry.setVisible(status == WorkflowConstants.STATUS_APPROVED);
        if (entry.isVisible()){
            entry.setPublishDate(now);
        }
        assetEntryLocalService.updateAssetEntry(entry);
        reindex(petition,false);
        return petition;
    }

    @Override
    public void updateAllPetitionsStatus() throws PortalException {
        _log.info("Start checking petitions status");

        // Recupere l'ID par defaut du portal
        long companyId = PortalUtil.getDefaultCompanyId();
        int petitionUpdatedCount = 0;
        // Recupere le groupe du site via son nom
        Group group = GroupLocalServiceUtil.getFriendlyURLGroup(companyId, FriendlyURLs.PLACIT_URL);

        List<Petition> petitionList = getPublishedByGroupId(group.getGroupId());
        if (petitionList != null && !petitionList.isEmpty()) {
            for (Petition petition : petitionList) {
                if (petition.getPublicationDate() != null && petition.getExpirationDate() != null) {
                    LocalDateTime now = LocalDateTime.now();
                    LocalDateTime expirationTime = new Timestamp(petition.getExpirationDate().getTime()).toLocalDateTime();
                    LocalDateTime publicationTime = new Timestamp(petition.getPublicationDate().getTime()).toLocalDateTime();
                    boolean isExpired = now.isAfter(expirationTime);
                    boolean quotaSignatureAtteint = petition.getNombreSignature() >= petition.getQuotaSignature();
                    if (quotaSignatureAtteint && !isExpired)
                        petition.setPetitionStatus("Aboutie");
                    else if (isExpired && !quotaSignatureAtteint)
                        petition.setPetitionStatus("Non aboutie");
                    else {
                        long periodTemp = ChronoUnit.DAYS.between(now, expirationTime);
                        long periodNews = ChronoUnit.DAYS.between(publicationTime, now);
                        if (!isExpired && periodNews <= 7)
                            petition.setPetitionStatus("Nouveau");
                        else if (!isExpired && periodTemp <= 7)
                            petition.setPetitionStatus("Bient&ocirc;t termin&eacute;e");
                        else petition.setPetitionStatus("En cours");
                    }
                    updatePetition(petition);
                    petitionUpdatedCount++;
                }
            }
        }

        _log.info("Updated " + petitionUpdatedCount + " petitions status");
    }

    /**
     * Supprime une petition
     */
    @Override
    public Petition removePetition(long petitionId) throws PortalException {
        AssetEntry entry = AssetEntryLocalServiceUtil
                .fetchEntry(Petition.class.getName(), petitionId);

        if (entry != null) {
            // Delete the link with categories
            for (long categoryId : entry.getCategoryIds()) {
                this.assetEntryLocalService.deleteAssetCategoryAssetEntry(
                        categoryId, entry.getEntryId());
            }

            // Delete the link with tags
            long[] tagIds = AssetEntryLocalServiceUtil
                    .getAssetTagPrimaryKeys(entry.getEntryId());
            if (tagIds!=null&&tagIds.length>0){
                for (long tagId : tagIds) {
                    AssetEntryLocalServiceUtil.deleteAssetTagAssetEntry(tagId,
                            entry.getEntryId());
                }
            }

            // Supprime lien avec les autres entries
            List<AssetLink> links = this.assetLinkLocalService
                    .getLinks(entry.getEntryId());
            if (links!=null&&!links.isEmpty()){
                for (AssetLink link : links) {
                    this.assetLinkLocalService.deleteAssetLink(link);
                }
            }

            // Delete the AssetEntry
            AssetEntryLocalServiceUtil.deleteEntry(Petition.class.getName(),
                    petitionId);

            // Supprime les lieux
            List<PlacitPlace> placitPlaces = this.placitPlaceLocalService
                    .getByPetition(petitionId);
            if (placitPlaces!=null&&!placitPlaces.isEmpty()){
                for (PlacitPlace placitPlace : placitPlaces) {
                    this.placitPlaceLocalService.removePlacitPlace(
                            placitPlace.getPlacitPlaceId());
                }
            }
        }

        // Supprime la petition
        Petition petition = petitionPersistence.remove(petitionId);

        // Delete the index
        this.reindex(petition, true);

        // Supprime ce qui a rapport au workflow
        WorkflowInstanceLinkLocalServiceUtil.deleteWorkflowInstanceLinks(
                petition.getCompanyId(), petition.getGroupId(), Petition.class.getName(),
                petition.getPetitionId());
        return petition;
    }

    /**
     * Renvoie la liste des vocabulaires rattachés à une petition
     */
    @Override
    public List<AssetVocabulary> getAttachedVocabularies(long groupId) {
        List<AssetVocabulary> vocabularies = AssetVocabularyLocalServiceUtil
                .getAssetVocabularies(-1, -1);
        List<AssetVocabulary> attachedVocabularies = new ArrayList<AssetVocabulary>();
        long classNameId = ClassNameLocalServiceUtil
                .getClassNameId(Petition.class);
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
	 * Reindex la petition dans le moteur de recherche
	 */
	private void reindex(Petition petition, boolean delete) throws SearchException {
		Indexer<Petition> indexer = IndexerRegistryUtil
				.nullSafeGetIndexer(Petition.class);
		if (delete) {
			indexer.delete(petition);
		} else {
			indexer.reindex(petition);
		}
	}

    /**
     * Retourne tous les petitions d'un groupe
     */
    @Override
     public List<Petition> getByGroupId(long groupId) {
        return this.petitionPersistence.findByGroupId(groupId);
    }

    /**
     * Retourne tous les petitions publiés d'un groupe
     */
    @Override
    public List<Petition> getPublishedByGroupId(long groupId) {
        return this.petitionPersistence.findByStatusAndGroupId(WorkflowConstants.STATUS_APPROVED, groupId);
    }
    @Override
    public List<Petition> findByKeyword(String keyword, long groupId, int start, int end){
		DynamicQuery dynamicQuery = dynamicQuery();
		if (keyword.length() > 0) {
			dynamicQuery.add(
					RestrictionsFactoryUtil.like("title", "%" + keyword + "%"));
		}
		if (groupId > 0) {
			dynamicQuery
					.add(PropertyFactoryUtil.forName("groupId").eq(groupId));
		}
		return petitionPersistence.findWithDynamicQuery(dynamicQuery,start,end);
	}

	/**
	 * Crée une participation vide avec une PK, non ajouté à la base de donnée
	 */
	@Override
	public Petition createPetition(ServiceContext sc)
			throws PortalException {
		User user = UserLocalServiceUtil.getUser(sc.getUserId());

		long pk = counterLocalService.increment();

		Petition petition = this.petitionLocalService
				.createPetition(pk);

		petition.setGroupId(sc.getScopeGroupId());
		petition.setUserName(user.getFullName());
		petition.setUserId(sc.getUserId());

		petition.setStatus(WorkflowConstants.STATUS_DRAFT);

		return petition;
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
		return petitionPersistence.countWithDynamicQuery(dynamicQuery);
	}

}