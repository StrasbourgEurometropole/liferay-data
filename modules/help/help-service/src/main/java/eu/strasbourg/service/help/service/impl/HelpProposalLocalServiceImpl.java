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

package eu.strasbourg.service.help.service.impl;

import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.model.AssetLink;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
import com.liferay.portal.aop.AopService;

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
import eu.strasbourg.service.help.model.HelpProposal;
import eu.strasbourg.service.help.model.HelpProposalModel;
import eu.strasbourg.service.help.service.base.HelpProposalLocalServiceBaseImpl;

import org.osgi.service.component.annotations.Component;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * The implementation of the help proposal local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the <code>eu.strasbourg.service.help.service.HelpProposalLocalService</code> interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see HelpProposalLocalServiceBaseImpl
 */
@Component(
	property = "model.class.name=eu.strasbourg.service.help.model.HelpProposal",
	service = AopService.class
)
public class HelpProposalLocalServiceImpl
	extends HelpProposalLocalServiceBaseImpl {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Use <code>eu.strasbourg.service.help.service.HelpProposalLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>eu.strasbourg.service.help.service.HelpProposalLocalServiceUtil</code>.
	 */

	/**
	 * Crée une helpProposal vide avec une PK, non ajouté à la base de donnée
	 */
	@Override
	public HelpProposal createInitiative(ServiceContext sc)
			throws PortalException {
		User user = UserLocalServiceUtil.getUser(sc.getUserId());

		long pk = counterLocalService.increment();

		HelpProposal helpProposal = this.helpProposalLocalService
				.createHelpProposal(pk);

		helpProposal.setGroupId(sc.getScopeGroupId());
		helpProposal.setUserName(user.getFullName());
		helpProposal.setUserId(sc.getUserId());

		helpProposal.setStatus(WorkflowConstants.STATUS_APPROVED);

		return helpProposal;
	}

	/**
	 * Met à jour une helpProposal et l'enregistre en base de données
	 * @throws IOException
	 */
	@Override
	public HelpProposal updateHelpProposal(HelpProposal helpProposal, ServiceContext sc)
			throws PortalException {
		User user = UserLocalServiceUtil.getUser(sc.getUserId());

		helpProposal.setStatusByUserId(sc.getUserId());
		helpProposal.setStatusByUserName(user.getFullName());
		helpProposal.setStatusDate(sc.getModifiedDate());

		if (sc.getWorkflowAction() == WorkflowConstants.ACTION_PUBLISH) {
			helpProposal.setStatus(WorkflowConstants.STATUS_APPROVED);
			helpProposal.setStatusDate(new Date());
		} else {
			helpProposal.setStatus(WorkflowConstants.STATUS_DRAFT);
		}
		helpProposal = this.helpProposalLocalService.updateHelpProposal(helpProposal);

		this.updateAssetEntry(helpProposal, sc);
		this.reindex(helpProposal, false);

		return helpProposal;
	}

	/**
	 * Met à jour le statut de la helpProposal "manuellement" (pas via le workflow)
	 */
	@Override
	public void updateStatus(HelpProposal helpProposal, int status) throws PortalException {
		this.updateStatus(helpProposal.getUserId(), helpProposal.getHelpProposalId(), status, null,
				null);
	}

	/**
	 * Met à jour le statut de la helpProposal par le framework workflow
	 */
	@Override
	public HelpProposal updateStatus(long userId, long entryId, int status,
								   ServiceContext sc, Map<String, Serializable> workflowContext)
			throws PortalException {
		Date now = new Date();
		// Statut de l'entité
		HelpProposal helpProposal = this.getHelpProposal(entryId);
		helpProposal.setStatus(status);
		User user = UserLocalServiceUtil.fetchUser(userId);
		if (user != null) {
			helpProposal.setStatusByUserId(user.getUserId());
			helpProposal.setStatusByUserName(user.getFullName());
		}
		helpProposal.setStatusDate(new Date());
		helpProposal = this.helpProposalLocalService.updateHelpProposal(helpProposal);

		// Statut de l'entry
		AssetEntry entry = this.assetEntryLocalService
				.getEntry(HelpProposal.class.getName(), helpProposal.getPrimaryKey());
		entry.setVisible(status == WorkflowConstants.STATUS_APPROVED);
		if (entry.isVisible()) {
			entry.setPublishDate(now);
		}
		this.assetEntryLocalService.updateAssetEntry(entry);

		this.reindex(helpProposal, false);

		return helpProposal;
	}

	/**
	 * Met à jour l'AssetEntry rattachée à la helpProposal
	 */
	private void updateAssetEntry(HelpProposal helpProposal, ServiceContext sc)
			throws PortalException {
		this.assetEntryLocalService.updateEntry(sc.getUserId(), // User ID
				sc.getScopeGroupId(), // Group ID
				helpProposal.getCreateDate(), // Date of creation
				helpProposal.getModifiedDate(), // Date of modification
				HelpProposal.class.getName(), // Class name
				helpProposal.getPrimaryKey(), // Class PK
				helpProposal.getUuid(), // UUID
				0, // Class type ID
				sc.getAssetCategoryIds(), // Categories IDs
				sc.getAssetTagNames(), // Tags IDs
				true, // Listable
				helpProposal.isApproved(), // Visible
				helpProposal.getCreateDate(), // Start date
				null, // End date
				helpProposal.getCreateDate(), // Publication date
				null, // Date of expiration
				ContentTypes.TEXT_HTML, // Content type
				helpProposal.getTitle(), // Title
				helpProposal.getDescription(), // Description
				helpProposal.getDescription(), // Summary
				null, // URL
				null, // Layout uuid
				0, // Width
				0, // Height
				null); // Priority

		// Réindexe la helpProposal
		this.reindex(helpProposal, false);
	}

	/**
	 * Supprime une helpProposal
	 */
	@Override
	public HelpProposal removeHelpProposal(long helpProposalId) throws PortalException {
		AssetEntry entry = AssetEntryLocalServiceUtil
				.fetchEntry(HelpProposal.class.getName(), helpProposalId);

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
			AssetEntryLocalServiceUtil.deleteEntry(HelpProposal.class.getName(),
					helpProposalId);
		}

		// Supprime la initiative
		HelpProposal helpProposal = helpProposalPersistence.remove(helpProposalId);

		// Delete the index
		this.reindex(helpProposal, true);

		// Supprime ce qui a rapport au workflow
		WorkflowInstanceLinkLocalServiceUtil.deleteWorkflowInstanceLinks(
				helpProposal.getCompanyId(), helpProposal.getGroupId(),
				HelpProposal.class.getName(), helpProposal.getHelpProposalId());

		return helpProposal;
	}

	/**
	 * Reindex la helpProposal dans le moteur de recherche
	 */
	private void reindex(HelpProposal helpProposal, boolean delete) throws SearchException {
		Indexer<HelpProposal> indexer = IndexerRegistryUtil
				.nullSafeGetIndexer(HelpProposal.class);
		if (delete) {
			indexer.delete(helpProposal);
		} else {
			indexer.reindex(helpProposal);
		}
	}

	// FINDERS
	/**
	 * Retourne toutes les helpProposal d'un groupe
	 */
	@Override
	public List<HelpProposal> getByGroupId(long groupId) {
		return this.helpProposalPersistence.findByGroupId(groupId);
	}

	/**
	 * Retourne toutes les helpProposal d'un utilisateur (MonStrasbourg)
	 */
	@Override
	public List<HelpProposal> getByPublikID(String publikId){
		return helpProposalPersistence.findBypublikId(publikId);
	}

	/**
	 * Retourne toutes les helpProposal publiées d'un groupe
	 */
	@Override
	public List<HelpProposal> getPublishedByGroupId(long groupId) {
		return this.helpProposalPersistence.findByStatusAndGroupId(WorkflowConstants.STATUS_APPROVED, groupId);
	}

	// Les HelpProposal sont approuvées par défaut, à garder malgré tout ?
	/*
	public List<HelpProposal> findByPublikId(String publikUserId){
		List<HelpProposal> result = helpProposalPersistence.findBypublikId(publikUserId);

		return result.stream()
				.filter(HelpProposalModel::isApproved)
				.collect(Collectors.toList());
	}
	*/
	// Différence avec le finder ? Pq pas de @Override
	public List<HelpProposal> findHelpedByPublikId(String publikUserId){
		List<HelpProposal> result = helpProposalPersistence.findBypublikId(publikUserId);
		return result;
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

		return helpProposalPersistence.countWithDynamicQuery(dynamicQuery);
	}

	public List<HelpProposal> findByPublikUserId(String publikUserId){
		List<HelpProposal> result = helpProposalPersistence.findBypublikId(publikUserId);

		return result.stream()
				.filter(HelpProposalModel::isApproved)
				.collect(Collectors.toList());
	}

	/**
	 * Recherche par mot clés
	 */
	@Override
	public List<HelpProposal> findByKeyword(String keyword, long groupId, int start,
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

		return helpProposalPersistence.findWithDynamicQuery(dynamicQuery, start, end);
	}
}