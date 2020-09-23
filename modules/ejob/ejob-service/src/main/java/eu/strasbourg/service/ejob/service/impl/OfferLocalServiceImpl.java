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

package eu.strasbourg.service.ejob.service.impl;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.model.AssetLink;
import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.asset.kernel.service.AssetCategoryLocalServiceUtil;
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
import com.liferay.portal.kernel.service.ClassNameLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.service.WorkflowDefinitionLinkLocalServiceUtil;
import com.liferay.portal.kernel.service.WorkflowInstanceLinkLocalServiceUtil;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.kernel.workflow.WorkflowHandlerRegistryUtil;
import eu.strasbourg.service.agenda.model.AgendaExport;
import eu.strasbourg.service.agenda.model.AgendaExportPeriod;
import eu.strasbourg.service.agenda.service.AgendaExportPeriodLocalServiceUtil;
import eu.strasbourg.service.ejob.exception.NoSuchOfferException;
import eu.strasbourg.service.ejob.model.Offer;
import eu.strasbourg.service.ejob.service.base.OfferLocalServiceBaseImpl;
import eu.strasbourg.utils.AssetVocabularyHelper;

import java.io.Serializable;
import java.util.*;

/**
 * The implementation of the offer local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the <code>eu.strasbourg.service.ejob.service.OfferLocalService</code> interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see OfferLocalServiceBaseImpl
 */
public class OfferLocalServiceImpl extends OfferLocalServiceBaseImpl {

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never reference this class directly. Use <code>eu.strasbourg.service.ejob.service.OfferLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>eu.strasbourg.service.ejob.service.OfferLocalServiceUtil</code>.
     */
    public final static Log log = LogFactoryUtil.getLog(OfferLocalServiceImpl.class);

    /**
     * Crée une offre vide avec une PK, non ajouté à la base de donnée
     */
    @Override
    public Offer createOffer(ServiceContext sc) throws PortalException {
        User user = UserLocalServiceUtil.getUser(sc.getUserId());

        long pk = counterLocalService.increment();

        Offer offer = this.offerLocalService.createOffer(pk);

        offer.setGroupId(sc.getScopeGroupId());
        offer.setUserName(user.getFullName());
        offer.setUserId(sc.getUserId());

        offer.setStatus(WorkflowConstants.STATUS_DRAFT);

        return offer;
    }

    /**
     * Met à jour une offre et l'enregistre en base de données
     */
    @Override
    public Offer updateOffer(Offer offer, ServiceContext sc)
            throws PortalException {
        User user = UserLocalServiceUtil.getUser(sc.getUserId());

        offer.setStatusByUserId(sc.getUserId());
        offer.setStatusByUserName(user.getFullName());
        offer.setStatusDate(sc.getModifiedDate());

        // Si on n'utilise pas le framework workflow, simple gestion
        // brouillon/publié
        if (!WorkflowDefinitionLinkLocalServiceUtil.hasWorkflowDefinitionLink(
                sc.getCompanyId(), sc.getScopeGroupId(), Offer.class.getName())) {
            if (sc.getWorkflowAction() == WorkflowConstants.ACTION_PUBLISH) {
                offer.setStatus(WorkflowConstants.STATUS_APPROVED);
            } else {
                offer.setStatus(WorkflowConstants.STATUS_DRAFT);
            }
            offer = this.offerLocalService.updateOffer(offer);
            this.updateAssetEntry(offer, sc);
            this.reindex(offer, false);
        } else { // Si le framework worflow est actif, c'est celui-ci qui gère
            // l'enregistrement
            offer = this.offerLocalService.updateOffer(offer);
            this.updateAssetEntry(offer, sc);
            WorkflowHandlerRegistryUtil.startWorkflowInstance(
                    offer.getCompanyId(), offer.getGroupId(),
                    offer.getUserId(), Offer.class.getName(),
                    offer.getPrimaryKey(), offer, sc);
        }

        return offer;
    }

    /**
     * Met à jour l'AssetEntry rattachée à l'offre
     */
    private void updateAssetEntry(Offer offer, ServiceContext sc)
            throws PortalException {
        this.assetEntryLocalService.updateEntry(sc.getUserId(), // User ID
                sc.getScopeGroupId(), // Group ID
                offer.getCreateDate(), // Date of creation
                offer.getModifiedDate(), // Date of modification
                Offer.class.getName(), // Class name
                offer.getPrimaryKey(), // Class PK
                offer.getUuid(), // UUID
                0, // Class type ID
                sc.getAssetCategoryIds(), // Categories IDs
                sc.getAssetTagNames(), // Tags IDs
                true, // Listable
                offer.isApproved(), // Visible
                offer.getPublicationStartDate(), // Start date
                offer.getPublicationEndDate(), // End date
                offer.getPublicationStartDate(), // Publication date
                offer.getPublicationEndDate(), // Date of expiration
                ContentTypes.TEXT_HTML, // Content type
                offer.getPost(), // Title
                offer.getIntroduction(), // Description
                offer.getIntroduction(), // Summary
                null, // URL
                null, // Layout uuid
                0, // Width
                0, // Height
                null); // Priority

        // Réindexe l'edition
        this.reindex(offer, false);
    }

    /**
     * Met à jour le statut de l'edition par le framework workflow
     */
    @Override
    public Offer updateStatus(long userId, long entryId, int status,
                              ServiceContext sc, Map<String, Serializable> workflowContext)
            throws PortalException {
        Date now = new Date();
        // Statut de l'entité
        Offer offer = this.getOffer(entryId);
        offer.setStatus(status);
        User user = UserLocalServiceUtil.fetchUser(userId);
        if (user != null) {
            offer.setStatusByUserId(user.getUserId());
            offer.setStatusByUserName(user.getFullName());
        }
        offer.setStatusDate(new Date());
        offer = this.offerLocalService.updateOffer(offer);

        // Statut de l'entry
        AssetEntry entry = this.assetEntryLocalService
                .getEntry(Offer.class.getName(), offer.getPrimaryKey());
        entry.setVisible(status == WorkflowConstants.STATUS_APPROVED);
        if (entry.isVisible()) {
            entry.setPublishDate(now);
        }
        this.assetEntryLocalService.updateAssetEntry(entry);

        this.reindex(offer, false);

        return offer;
    }

    /**
     * Met à jour le statut de l'edition "manuellement" (pas via le workflow)
     */
    @Override
    public void updateStatus(Offer offer, int status)
            throws PortalException {
        this.updateStatus(offer.getUserId(), offer.getOfferId(), status,
                null, null);
    }

    /**
     * Supprime une edition
     */
    @Override
    public Offer removeOffer(long offerId) throws PortalException {
        AssetEntry entry = AssetEntryLocalServiceUtil
                .fetchEntry(Offer.class.getName(), offerId);

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
            AssetEntryLocalServiceUtil.deleteEntry(Offer.class.getName(),
                    offerId);

        }

        // Delete the Edition
        Offer offer = offerPersistence.remove(offerId);

        // Delete the index
        this.reindex(offer, true);

        // Supprime ce qui a rapport au workflow
        WorkflowInstanceLinkLocalServiceUtil.deleteWorkflowInstanceLinks(
                offer.getCompanyId(), offer.getGroupId(),
                Offer.class.getName(), offer.getOfferId());

        return offer;
    }

    /**
     * Reindex l'edition dans le moteur de recherche
     */
    private void reindex(Offer offer, boolean delete)
            throws SearchException {
        Indexer<Offer> indexer = IndexerRegistryUtil
                .nullSafeGetIndexer(Offer.class);
        if (delete) {
            indexer.delete(offer);
        } else {
            indexer.reindex(offer);
        }
    }

    /**
     * Retourne les vocabulaires rattrachés à ce type d'entité pour un groupe
     */
    @Override
    public List<AssetVocabulary> getAttachedVocabularies(long groupId) {
        long classNameId = ClassNameLocalServiceUtil
                .getClassNameId(Offer.class);
        return AssetVocabularyHelper.getVocabulariesForAssetType(groupId,
                classNameId);
    }

    @Override
    public List<Offer> findByKeyword(String keyword, long groupId,
                                     int start, int end) {
        DynamicQuery dynamicQuery = dynamicQuery();

        if (keyword.length() > 0) {
            dynamicQuery.add(
                    RestrictionsFactoryUtil.like("post", "%" + keyword + "%"));
        }
        if (groupId > 0) {
            dynamicQuery
                    .add(PropertyFactoryUtil.forName("groupId").eq(groupId));
        }

        return offerPersistence.findWithDynamicQuery(dynamicQuery, start,
                end);
    }

    @Override
    public long findByKeywordCount(String keyword, long groupId) {
        DynamicQuery dynamicQuery = dynamicQuery();
        if (keyword.length() > 0) {
            dynamicQuery.add(
                    RestrictionsFactoryUtil.like("post", "%" + keyword + "%"));
        }
        if (groupId > 0) {
            dynamicQuery
                    .add(PropertyFactoryUtil.forName("groupId").eq(groupId));
        }

        return offerPersistence.countWithDynamicQuery(dynamicQuery);
    }

    /**
     * Retourne une offre via son publicationId
     */
    @Override
    public Offer findByPublicationId(String publicationId) {
        try {
            return this.offerPersistence.findByPublicationId(publicationId);
        } catch (NoSuchOfferException e) {
            return null;
        }
    }

    /**
     * Retourne les offres via sa date de début de publication
     */
    @Override
    public List<Offer> findByPublicationStartDate(Date date) {
        return this.offerPersistence.findByPublicationStartDate(date);
    }

    /**
     * Retourne les offres qui n'ont pas été exportées
     */
    @Override
    public List<Offer> findOffersNotExported() {
        return this.offerPersistence.findByExport(1);
    }


    /**
     * Duplique une offre et la retourne
     */
    @Override
    public Offer copyOffer(ServiceContext sc, long offerId) throws PortalException {
        User user = UserLocalServiceUtil.getUser(sc.getUserId());
        long pk = counterLocalService.increment();
        String uuid = PortalUUIDUtil.generate();

        Offer offerToCopy = this.getOffer(offerId);
        Offer offer = (Offer) offerToCopy.clone();

        offer.setGroupId(sc.getScopeGroupId());
        offer.setUserName(user.getFullName());
        offer.setUserId(sc.getUserId());
        offer.setNew(true);
        // Champ : isExported (1 si offre interne)
        if (Validator.isNotNull(offer.getTypePublication())) {
            if (offer.getTypePublication().getName().equals("Interne uniquement") || offer.getTypePublication().getName().equals("Interne et externe"))
                offer.setIsExported(1);
            else
                offer.setIsExported(0);
        }
        offer.setEmailSend(0);
        offer.setEmailPartnerSent(0);
        String publicationId = AssetVocabularyHelper.getCategoryProperty(offer.getOfferTypeRecrutement().getCategoryId(), "acro");
        publicationId += String.format("%06d", pk);
        offer.setPublicationId(publicationId);
        offer.setUuid(uuid);
        offer = this.updateOffer(offer, sc);

        this.setCategoriesForCopy(offerToCopy, offer, sc);

        publicationId = AssetVocabularyHelper.getCategoryProperty(offer.getOfferTypeRecrutement().getCategoryId(), "acro");
        publicationId += String.format("%06d", offer.getOfferId());
        offer.setPublicationId(publicationId);
        offer = this.updateOffer(offer, sc);

        this.setCategoriesForCopy(offerToCopy, offer, sc);

        return offer;
    }

    @Override
    public void setCategoriesForCopy(Offer offerToCopy, Offer offer, ServiceContext sc){


        List<AssetCategory> categories = AssetVocabularyHelper.getAssetEntryCategories(offerToCopy.getAssetEntry());
        long[] categoryIds = new long[categories.size()];
        for (int i = 0; i < categories.size(); i++) {
            if (Validator.isNotNull(categories.get(i))) {
                categoryIds[i] = Long.parseLong(categories.get(i).getCategoryId()+"");
            }
        }

        try {
            this.assetEntryLocalService.updateEntry(sc.getUserId(),
                    offer.getGroupId(), Offer.class.getName(),
                    offer.getOfferId(), categoryIds, null);
        } catch (PortalException e) {
            e.printStackTrace();
        }
    }


}