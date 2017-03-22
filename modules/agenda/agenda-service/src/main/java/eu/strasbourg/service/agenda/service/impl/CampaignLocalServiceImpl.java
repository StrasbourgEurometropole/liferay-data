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

package eu.strasbourg.service.agenda.service.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.model.AssetLink;
import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.service.ClassNameLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.service.WorkflowDefinitionLinkLocalServiceUtil;
import com.liferay.portal.kernel.service.WorkflowInstanceLinkLocalServiceUtil;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.kernel.workflow.WorkflowHandlerRegistryUtil;

import aQute.bnd.annotation.ProviderType;
import eu.strasbourg.service.agenda.model.Campaign;
import eu.strasbourg.service.agenda.model.CampaignEvent;
import eu.strasbourg.service.agenda.service.CampaignEventLocalServiceUtil;
import eu.strasbourg.service.agenda.service.base.CampaignLocalServiceBaseImpl;
import eu.strasbourg.utils.AssetVocabularyHelper;

/**
 * The implementation of the campaign local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link eu.strasbourg.service.campaign.service.CampaignLocalService}
 * interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 *
 * @author BenjaminBini
 * @see CampaignLocalServiceBaseImpl
 * @see eu.strasbourg.service.campaign.service.CampaignLocalServiceUtil
 */
@ProviderType
public class CampaignLocalServiceImpl extends CampaignLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link
	 * eu.strasbourg.service.campaign.service.CampaignLocalServiceUtil} to
	 * access the campaign local service.
	 */

	/**
	 * Crée une campaign vide avec une PK, non ajouté à la base de donnée
	 */
	@Override
	public Campaign createCampaign(ServiceContext sc) throws PortalException {
		User user = UserLocalServiceUtil.getUser(sc.getUserId());

		long pk = counterLocalService.increment();

		Campaign campaign = this.campaignLocalService.createCampaign(pk);

		campaign.setGroupId(sc.getScopeGroupId());
		campaign.setUserName(user.getFullName());
		campaign.setUserId(sc.getUserId());

		campaign.setStatus(WorkflowConstants.STATUS_DRAFT);

		return campaign;
	}

	/**
	 * Met à jour une campaign et l'enregistre en base de données
	 */
	@Override
	public Campaign updateCampaign(Campaign campaign, ServiceContext sc) throws PortalException {
		User user = UserLocalServiceUtil.getUser(sc.getUserId());

		campaign.setStatusByUserId(sc.getUserId());
		campaign.setStatusByUserName(user.getFullName());
		campaign.setStatusDate(sc.getModifiedDate());

		// Si on n'utilise pas le framework workflow, simple gestion
		// brouillon/publié
		if (!WorkflowDefinitionLinkLocalServiceUtil.hasWorkflowDefinitionLink(sc.getCompanyId(), sc.getScopeGroupId(),
				Campaign.class.getName())) {
			if (sc.getWorkflowAction() == WorkflowConstants.ACTION_PUBLISH) {
				campaign.setStatus(WorkflowConstants.STATUS_APPROVED);
			} else {
				campaign.setStatus(WorkflowConstants.STATUS_DRAFT);
			}
			campaign = this.campaignLocalService.updateCampaign(campaign);
			this.updateAssetEntry(campaign, sc);
			this.reindex(campaign, false);
		} else { // Si le framework worflow est actif, c'est celui-ci qui gère
					// l'enregistrement
			campaign = this.campaignLocalService.updateCampaign(campaign);
			WorkflowHandlerRegistryUtil.startWorkflowInstance(campaign.getCompanyId(), campaign.getGroupId(),
					campaign.getUserId(), Campaign.class.getName(), campaign.getPrimaryKey(), campaign, sc);
		}

		return campaign;
	}

	/**
	 * Met à jour l'AssetEntry rattachée à l'campaign
	 */
	private void updateAssetEntry(Campaign campaign, ServiceContext sc) throws PortalException {
		this.assetEntryLocalService.updateEntry(sc.getUserId(), // User ID
				sc.getScopeGroupId(), // Group ID
				campaign.getCreateDate(), // Date of creation
				campaign.getModifiedDate(), // Date of modification
				Campaign.class.getName(), // Class name
				campaign.getPrimaryKey(), // Class PK
				campaign.getUuid(), // UUID
				0, // Class type ID
				sc.getAssetCategoryIds(), // Categories IDs
				sc.getAssetTagNames(), // Tags IDs
				true, // Listable
				campaign.isApproved(), // Visible
				campaign.getCreateDate(), // Start date
				null, // End date
				campaign.getCreateDate(), // Publication date
				null, // Date of expiration
				ContentTypes.TEXT_HTML, // Content type
				campaign.getTitle(), // Title
				campaign.getTitle(), // Description
				campaign.getTitle(), // Summary
				null, // URL
				null, // Layout uuid
				0, // Width
				0, // Height
				null); // Priority

		// Réindexe l'campaign
		this.reindex(campaign, false);
	}

	/**
	 * Met à jour le statut de l'campaign par le framework workflow
	 */
	@Override
	public Campaign updateStatus(long userId, long entryId, int status, ServiceContext sc,
			Map<String, Serializable> workflowContext) throws PortalException {
		Date now = new Date();
		// Statut de l'entité
		Campaign campaign = this.getCampaign(entryId);
		campaign.setStatus(status);
		User user = UserLocalServiceUtil.fetchUser(userId);
		if (user != null) {
			campaign.setStatusByUserId(user.getUserId());
			campaign.setStatusByUserName(user.getFullName());
		}
		campaign.setStatusDate(new Date());
		campaign = this.campaignLocalService.updateCampaign(campaign);

		// Statut de l'entry
		AssetEntry entry = this.assetEntryLocalService.getEntry(Campaign.class.getName(), campaign.getPrimaryKey());
		entry.setVisible(status == WorkflowConstants.STATUS_APPROVED);
		if (entry.isVisible()) {
			entry.setPublishDate(now);
		}
		this.assetEntryLocalService.updateAssetEntry(entry);

		this.reindex(campaign, false);

		return campaign;
	}

	/**
	 * Met à jour le statut de l'campaign "manuellement" (pas via le workflow)
	 */
	@Override
	public void updateStatus(Campaign campaign, int status) throws PortalException {
		this.updateStatus(campaign.getUserId(), campaign.getCampaignId(), status, null, null);
	}

	/**
	 * Supprime une campaign
	 */
	@Override
	public Campaign removeCampaign(long campaignId) throws PortalException {
		AssetEntry entry = AssetEntryLocalServiceUtil.fetchEntry(Campaign.class.getName(), campaignId);

		if (entry != null) {
			// Delete the link with categories
			for (long categoryId : entry.getCategoryIds()) {
				this.assetEntryLocalService.deleteAssetCategoryAssetEntry(categoryId, entry.getEntryId());
			}

			// Delete the link with tags
			long[] tagIds = AssetEntryLocalServiceUtil.getAssetTagPrimaryKeys(entry.getEntryId());
			for (int i = 0; i < tagIds.length; i++) {
				AssetEntryLocalServiceUtil.deleteAssetTagAssetEntry(tagIds[i], entry.getEntryId());
			}

			// Supprime lien avec les autres entries
			List<AssetLink> links = this.assetLinkLocalService.getLinks(entry.getEntryId());
			for (AssetLink link : links) {
				this.assetLinkLocalService.deleteAssetLink(link);
			}

			// Delete the AssetEntry
			AssetEntryLocalServiceUtil.deleteEntry(Campaign.class.getName(), campaignId);

		}

		// Delete the Campaign
		Campaign campaign = campaignPersistence.remove(campaignId);
		
		// On supprime les événements liés à la campagne
		List<CampaignEvent> events = campaign.getEvents();
		for (CampaignEvent event : events) {
			CampaignEventLocalServiceUtil.removeCampaignEvent(event.getCampaignEventId());
		}

		// Delete the index
		this.reindex(campaign, true);

		// Supprime ce qui a rapport au workflow
		WorkflowInstanceLinkLocalServiceUtil.deleteWorkflowInstanceLinks(campaign.getCompanyId(), campaign.getGroupId(),
				Campaign.class.getName(), campaign.getCampaignId());


		return campaign;
	}

	/**
	 * Reindex l'campaign dans le moteur de recherche
	 */
	private void reindex(Campaign campaign, boolean delete) throws SearchException {
		Indexer<Campaign> indexer = IndexerRegistryUtil.nullSafeGetIndexer(Campaign.class);
		if (delete) {
			indexer.delete(campaign);
		} else {
			indexer.reindex(campaign);
		}
	}

	/**
	 * Retourne les vocabulaires rattrachés à ce type d'entité pour un groupe
	 */
	@Override
	public List<AssetVocabulary> getAttachedVocabularies(long groupId) {
		long classNameId = ClassNameLocalServiceUtil.getClassNameId(Campaign.class);
		return AssetVocabularyHelper.getVocabulariesForAssetType(groupId, classNameId);
	}

	/**
	 * Retourne toutes les campaigns d'un groupe
	 */
	@Override
	public List<Campaign> getByGroupId(long groupId) {
		return this.campaignPersistence.findByGroupId(groupId);
	}

	/**
	 * Lance une recherche selon le searchContext
	 */
	@Override
	public Hits search(SearchContext searchContext) throws SearchException {
		Indexer<Campaign> indexer = IndexerRegistryUtil.nullSafeGetIndexer(Campaign.class);
		return indexer.search(searchContext);
	}

	/**
	 * Lance une recherche par mots-clés
	 */
	@Override
	public List<Campaign> findByKeyword(String keyword, long groupId,
		int start, int end) {
		DynamicQuery dynamicQuery = dynamicQuery();

		if (keyword.length() > 0) {
			dynamicQuery.add(
				RestrictionsFactoryUtil.like("title", "%" + keyword + "%"));
		}
		if (groupId > 0) {
			dynamicQuery
				.add(PropertyFactoryUtil.forName("groupId").eq(groupId));
		}

		return campaignEventPersistence.findWithDynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	 * Compte de la recherche par mots-clés
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

		return campaignEventPersistence.countWithDynamicQuery(dynamicQuery);
	}
	
	/**
	 * Exporte toutes les campagne exportables dans le dossier d'import des événements
	 */
	@Override
	public void exportCampaigns() {
		List<Campaign> campaigns = this.getCampaigns(-1, -1).stream().filter(c -> c.getExportEnabled()).collect(Collectors.toList());
		for (Campaign campaign : campaigns) {
			campaign.export();
		}
	}
}