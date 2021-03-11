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
import eu.strasbourg.service.help.exception.NoSuchHelpRequestException;
import eu.strasbourg.service.help.model.HelpProposal;
import eu.strasbourg.service.help.model.HelpRequest;
import eu.strasbourg.service.help.service.base.HelpRequestLocalServiceBaseImpl;

import org.osgi.service.component.annotations.Component;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * The implementation of the help request local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the <code>eu.strasbourg.service.help.service.HelpRequestLocalService</code> interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see HelpRequestLocalServiceBaseImpl
 */
@Component(
	property = "model.class.name=eu.strasbourg.service.help.model.HelpRequest",
	service = AopService.class
)
public class HelpRequestLocalServiceImpl
	extends HelpRequestLocalServiceBaseImpl {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Use <code>eu.strasbourg.service.help.service.HelpRequestLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>eu.strasbourg.service.help.service.HelpRequestLocalServiceUtil</code>.
	 */

	/**
	 * Methode de creation d'une demande d'aide
	 * @param sc Le contexte de la requete.
	 * @return L'aide cree.
	 */
	@Override
	public HelpRequest createHelpRequest(ServiceContext sc){
		long pk = this.counterLocalService.increment();

		HelpRequest result = this.helpRequestLocalService.createHelpRequest(pk);

		result.setGroupId(sc.getScopeGroupId());
		result.setCreateDate(new Date());

		return result;
	}

	/**
	 * Supprimer une demande d'aide
	 * @param helpRequestId Id de la demande d'aide
	 */
	@Override
	public HelpRequest removeHelpRequest(long helpRequestId) throws PortalException {
		AssetEntry entry = AssetEntryLocalServiceUtil
				.fetchEntry(HelpRequest.class.getName(), helpRequestId);

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
			AssetEntryLocalServiceUtil.deleteEntry(HelpRequest.class.getName(),
					helpRequestId);
		}

		// Supprime la helpProposal
		HelpRequest helpRequest = helpRequestPersistence.remove(helpRequestId);

		// Delete the index
		this.reindex(helpRequest, true);

		// Supprime ce qui a rapport au workflow
		WorkflowInstanceLinkLocalServiceUtil.deleteWorkflowInstanceLinks(
				helpRequest.getCompanyId(), helpRequest.getGroupId(),
				HelpRequest.class.getName(), helpRequest.getHelpRequestId());

		return helpRequest;
	}

	/**
	 * Met à jour une helpProposal et l'enregistre en base de données
	 * @throws IOException
	 */
	@Override
	public HelpRequest updateHelpRequest(HelpRequest helpRequest, ServiceContext sc)
			throws PortalException {
		User user = UserLocalServiceUtil.getUser(sc.getUserId());

		helpRequest.setStatusByUserId(sc.getUserId());
		helpRequest.setStatusByUserName(user.getFullName());
		helpRequest.setStatusDate(sc.getModifiedDate());

		helpRequest = this.helpRequestLocalService.updateHelpRequest(helpRequest);

		this.updateAssetEntry(helpRequest, sc);
		this.reindex(helpRequest, false);

		return helpRequest;
	}

	/**
	 * Met à jour le statut de la helpProposal "manuellement" (pas via le workflow)
	 */
	@Override
	public void updateStatus(HelpRequest helpRequest, int status) throws PortalException {
		this.updateStatus(helpRequest.getUserId(), helpRequest.getHelpRequestId(), status, null,
				null);
	}

	/**
	 * Met à jour le statut de la helpProposal par le framework workflow
	 */
	@Override
	public HelpRequest updateStatus(long userId, long entryId, int status,
									 ServiceContext sc, Map<String, Serializable> workflowContext)
			throws PortalException {
		Date now = new Date();
		// Statut de l'entité
		HelpRequest helpRequest = this.getHelpRequest(entryId);
		helpRequest.setStatus(status);
		User user = UserLocalServiceUtil.fetchUser(userId);
		if (user != null) {
			helpRequest.setStatusByUserId(user.getUserId());
			helpRequest.setStatusByUserName(user.getFullName());
		}
		helpRequest.setStatusDate(new Date());
		helpRequest = this.helpRequestLocalService.updateHelpRequest(helpRequest);

		// Statut de l'entry
		AssetEntry entry = this.assetEntryLocalService
				.getEntry(HelpRequest.class.getName(), helpRequest.getPrimaryKey());
		entry.setVisible(status == WorkflowConstants.STATUS_APPROVED);
		if (entry.isVisible()) {
			entry.setPublishDate(now);
		}
		this.assetEntryLocalService.updateAssetEntry(entry);

		this.reindex(helpRequest, false);

		return helpRequest;
	}

	/**
	 * Met à jour l'AssetEntry rattachée à la helpProposal
	 */
	private void updateAssetEntry(HelpRequest helpRequest, ServiceContext sc)
			throws PortalException {
		this.assetEntryLocalService.updateEntry(sc.getUserId(), // User ID
				sc.getScopeGroupId(), // Group ID
				helpRequest.getCreateDate(), // Date of creation
				helpRequest.getModifiedDate(), // Date of modification
				HelpProposal.class.getName(), // Class name
				helpRequest.getPrimaryKey(), // Class PK
				helpRequest.getUuid(), // UUID
				0, // Class type ID
				sc.getAssetCategoryIds(), // Categories IDs
				sc.getAssetTagNames(), // Tags IDs
				true, // Listable
				helpRequest.isApproved(), // Visible
				helpRequest.getCreateDate(), // Start date
				null, // End date
				helpRequest.getCreateDate(), // Publication date
				null, // Date of expiration
				ContentTypes.TEXT_HTML, // Content type
				helpRequest.getFormatedMessage(), // Title
				helpRequest.getFormatedMessage(), // Description
				helpRequest.getFormatedMessage(), // Summary
				null, // URL
				null, // Layout uuid
				0, // Width
				0, // Height
				null); // Priority

		// Réindexe la helpRequest
		this.reindex(helpRequest, false);
	}

	/**
	 * Reindex la helpProposal dans le moteur de recherche
	 */
	private void reindex(HelpRequest helpRequest, boolean delete) throws SearchException {
		Indexer<HelpRequest> indexer = IndexerRegistryUtil
				.nullSafeGetIndexer(HelpRequest.class);
		if (delete) {
			indexer.delete(helpRequest);
		} else {
			indexer.reindex(helpRequest);
		}
	}

	@Override
	public void reindexById(long helpRequestId) throws SearchException {
		HelpRequest helpRequest = fetchHelpRequest(helpRequestId);
		if (helpRequest != null)
			reindex(helpRequest, false);
	}

	/**
	 * Retourne les demandes d'aides pour un utilisateur
	 */
	@Override
	public List<HelpRequest> getByPublikId(String publikId) {
		return this.helpRequestPersistence.findByPublikId(publikId);
	}

	/**
	 * Retourne la liste des demands d'aides pour une proposition
	 */
	@Override
	public List<HelpRequest> getByHelpProposalId(long helpRequestId) {
		return this.helpRequestPersistence.findByHelpProposalId(helpRequestId);
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

		return helpRequestPersistence.countWithDynamicQuery(dynamicQuery);
	}


	/**
	 * Recherche par mot clés
	 */
	@Override
	public List<HelpRequest> findByKeyword(String keyword, long groupId, int start,
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

		return helpRequestPersistence.findWithDynamicQuery(dynamicQuery, start, end);
	}

	public final Log _log = LogFactoryUtil.getLog(this.getClass().getName());
}