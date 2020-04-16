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

package eu.strasbourg.service.council.service.impl;

import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.model.AssetLink;
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
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import eu.strasbourg.service.council.model.Official;
import eu.strasbourg.service.council.service.base.OfficialLocalServiceBaseImpl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * The implementation of the official local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link eu.strasbourg.service.council.service.OfficialLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see OfficialLocalServiceBaseImpl
 * @see eu.strasbourg.service.council.service.OfficialLocalServiceUtil
 */
public class OfficialLocalServiceImpl extends OfficialLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link eu.strasbourg.service.council.service.OfficialLocalServiceUtil} to access the official local service.
	 */

	public final static Log log = LogFactoryUtil.getLog(OfficialLocalServiceImpl.class);

	/**
	 * Crée une entité vide avec une PK, non ajouté à la base de donnée
	 */
	@Override
	public Official createOfficial(ServiceContext sc) throws PortalException {
		User user = UserLocalServiceUtil.getUser(sc.getUserId());

		long pk = this.counterLocalService.increment();
		Official official = this.officialLocalService.createOfficial(pk);

		official.setUserName(user.getFullName());
		official.setUserId(sc.getUserId());
		official.setGroupId(sc.getScopeGroupId());
		official.setStatus(WorkflowConstants.STATUS_DRAFT);

		return official;
	}

	/**
	 * Met à jour une entité et l'enregistre en base de données
	 */
	@Override
	public Official updateOfficial(Official official, ServiceContext sc) throws PortalException {
		User user = UserLocalServiceUtil.getUser(sc.getUserId());

		official.setStatusByUserId(sc.getUserId());
		official.setStatusByUserName(user.getFullName());
		official.setStatusDate(sc.getModifiedDate());

		if (sc.getWorkflowAction() == WorkflowConstants.ACTION_PUBLISH) {
			official.setStatus(WorkflowConstants.STATUS_APPROVED);
		} else {
			official.setStatus(WorkflowConstants.STATUS_DRAFT);
		}
		official = this.officialLocalService.updateOfficial(official);

		this.updateAssetEntry(official, sc);
		this.reindex(official, false);

		return official;
	}

	/**
	 * Met à jour l'AssetEntry rattachée à l'entité
	 */
	private void updateAssetEntry(Official official, ServiceContext sc)
			throws PortalException {
		this.assetEntryLocalService.updateEntry(sc.getUserId(), // User ID
				sc.getScopeGroupId(), // Group ID
				official.getCreateDate(), // Date of creation
				official.getModifiedDate(), // Date of modification
				Official.class.getName(), // Class name
				official.getPrimaryKey(), // Class PK
				official.getUuid(), // UUID
				0, // Class type ID
				sc.getAssetCategoryIds(), // Categories IDs
				sc.getAssetTagNames(), // Tags IDs
				true, // Listable
				official.isApproved(), // Visible
				official.getCreateDate(), // Start date
				null, // End date
				official.getCreateDate(), // Publication date
				null, // Date of expiration
				ContentTypes.TEXT_HTML, // Content type
				official.getFullName(), // Title
				official.getEmail(), // Description
				official.getEmail(), // Summary
				null, // URL
				null, // Layout uuid
				0, // Width
				0, // Height
				null); // Priority

		// Réindexe l'entité
		this.reindex(official, false);
	}

	/**
	 * Met à jour le statut de l'entité par le framework workflow
	 */
	@Override
	public Official updateStatus(long userId, long entryId, int status, ServiceContext sc,
								 Map<String, Serializable> workflowContext) throws PortalException {
		Date now = new Date();
		// Statut de l'entité
		Official official = this.getOfficial(entryId);
		official.setStatus(status);
		User user = UserLocalServiceUtil.fetchUser(userId);
		if (user != null) {
			official.setStatusByUserId(user.getUserId());
			official.setStatusByUserName(user.getFullName());
		}
		official.setStatusDate(new Date());
		official = this.officialLocalService.updateOfficial(official);

		// Statut de l'entry
		AssetEntry entry = this.assetEntryLocalService
				.getEntry(Official.class.getName(), official.getPrimaryKey());
		entry.setVisible(status == WorkflowConstants.STATUS_APPROVED);
		if (entry.isVisible()) {
			entry.setPublishDate(now);
		}
		this.assetEntryLocalService.updateAssetEntry(entry);

		this.reindex(official, false);

		return official;
	}

	/**
	 * Met à jour le statut de l'entité "manuellement" (pas via le workflow)
	 */
	@Override
	public void updateStatus(Official official, int status) throws PortalException {
		this.updateStatus(official.getUserId(), official.getOfficialId(), status, null,
				null);
	}

	/**
	 * Supprime une entité
	 */
	@Override
	public Official removeOfficial(long officialId) throws PortalException {
		AssetEntry entry = AssetEntryLocalServiceUtil
				.fetchEntry(Official.class.getName(), officialId);

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
			AssetEntryLocalServiceUtil.deleteEntry(Official.class.getName(), officialId);
		}

		// Supprime l'entité
		Official official = this.officialPersistence.remove(officialId);

		// Supprime l'index
		this.reindex(official, true);

		// Supprime ce qui a rapport au workflow
		WorkflowInstanceLinkLocalServiceUtil.deleteWorkflowInstanceLinks(
				official.getCompanyId(), official.getGroupId(), Official.class.getName(),
				official.getOfficialId());

		return official;
	}

	/**
	 * Reindex l'entité dans le moteur de recherche
	 */
	private void reindex(Official official, boolean delete) throws SearchException {
		Indexer<Official> indexer = IndexerRegistryUtil.nullSafeGetIndexer(Official.class);
		if (delete) {
			indexer.delete(official);
		} else {
			indexer.reindex(official);
		}
	}

	/**
	 * Recherche par ID de CouncilSession
	 */
	@Override
	public List<Official> findByEmail(String email) {
		return this.officialPersistence.findByEmail(email);
	}

}