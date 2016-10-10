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

package eu.strasbourg.service.edition.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.LongStream;

import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.model.AssetLink;
import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
import com.liferay.asset.kernel.service.AssetVocabularyLocalServiceUtil;
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
import eu.strasbourg.service.edition.model.Edition;
import eu.strasbourg.service.edition.service.base.EditionLocalServiceBaseImpl;

/**
 * The implementation of the edition local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link eu.strasbourg.service.edition.service.EditionLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 *
 * @author BenjaminBini
 * @see EditionLocalServiceBaseImpl
 * @see eu.strasbourg.service.edition.service.EditionLocalServiceUtil
 */
@ProviderType
public class EditionLocalServiceImpl extends EditionLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link
	 * eu.strasbourg.service.edition.service.EditionLocalServiceUtil} to access
	 * the edition local service.
	 */

	/**
	 * Crée une édition vide avec une PK, non ajouté à la base de donnée
	 */
	public Edition createEdition(ServiceContext sc) throws PortalException {
		User user = UserLocalServiceUtil.getUser(sc.getUserId());

		long pk = counterLocalService.increment();

		Edition edition = this.editionLocalService.createEdition(pk);

		edition.setGroupId(sc.getScopeGroupId());
		edition.setUserName(user.getFullName());
		edition.setUserId(sc.getUserId());

		edition.setStatus(WorkflowConstants.STATUS_DRAFT);

		return edition;
	}

	/**
	 * Met à jour une édition et l'enregistre en base de données
	 */
	public Edition updateEdition(Edition edition, ServiceContext sc)
		throws PortalException {
		User user = UserLocalServiceUtil.getUser(sc.getUserId());

		edition.setStatusByUserId(sc.getUserId());
		edition.setStatusByUserName(user.getFullName());
		edition.setStatusDate(sc.getModifiedDate());

		// Si on n'utilise pas le framework workflow, simple gestion
		// brouillon/publié
		if (!WorkflowDefinitionLinkLocalServiceUtil.hasWorkflowDefinitionLink(
			sc.getCompanyId(), sc.getScopeGroupId(), Edition.class.getName())) {
			if (sc.getWorkflowAction() == WorkflowConstants.ACTION_PUBLISH) {
				edition.setStatus(WorkflowConstants.STATUS_APPROVED);
			} else {
				edition.setStatus(WorkflowConstants.STATUS_DRAFT);
				// Si le statut est "DRAFT" et qu'il y a une version live, on
				// supprime cette dernière
				Edition liveEdition = edition.getLiveVersion();
				if (liveEdition != null) {
					this.removeEdition(liveEdition.getEditionId());
				}
			}
			edition = this.editionLocalService.updateEdition(edition);
			this.updateAssetEntry(edition, sc);
			this.reindex(edition, false);
		} else { // Si le framework worflow est actif, c'est celui-ci qui gère
				 // l'enregistrement
			edition = this.editionLocalService.updateEdition(edition);
			WorkflowHandlerRegistryUtil.startWorkflowInstance(
				edition.getCompanyId(), edition.getGroupId(), edition.getUserId(),
				Edition.class.getName(), edition.getPrimaryKey(), edition, sc);
		}

		return edition;
	}


	/**
	 * Met à jour l'AssetEntry rattachée à l'édition
	 */
	private void updateAssetEntry(Edition edition, ServiceContext sc)
		throws PortalException {
		this.assetEntryLocalService.updateEntry(sc.getUserId(), // User ID
			sc.getScopeGroupId(), // Group ID
			edition.getCreateDate(), // Date of creation
			edition.getModifiedDate(), // Date of modification
			Edition.class.getName(), // Class name
			edition.getPrimaryKey(), // Class PK
			edition.getUuid(), // UUID
			0, // Class type ID
			sc.getAssetCategoryIds(), // Categories IDs
			sc.getAssetTagNames(), // Tags IDs
			true, // Listable
			edition.isApproved(), // Visible
			edition.getPublicationDate(), // Start date
			null, // End date
			edition.getPublicationDate(), // Publication date
			null, // Date of expiration
			ContentTypes.TEXT_HTML, // Content type
			edition.getTitle(), // Title
			edition.getDescription(), // Description
			edition.getDescription(), // Summary
			null, // URL
			null, // Layout uuid
			0, // Width
			0, // Height
			null); // Priority

		// Réindexe l'édition
		this.reindex(edition, false);
	}

	/**
	 * Met à jour le statut de l'édition par le framework workflow
	 */
	public Edition updateStatus(long userId, long entryId, int status,
		ServiceContext sc, Map<String, Serializable> workflowContext)
		throws PortalException {
		Edition edition = this.getEdition(entryId);
		User user = UserLocalServiceUtil.getUser(userId);

		edition.setStatus(status);
		edition.setStatusByUserId(user.getUserId());
		edition.setStatusByUserName(user.getFullName());
		edition.setStatusDate(new Date());

		edition = this.editionLocalService.updateEdition(edition);

		AssetEntry entry = this.assetEntryLocalService
			.getEntry(Edition.class.getName(), edition.getPrimaryKey());
		entry.setVisible(status == WorkflowConstants.STATUS_APPROVED);
		this.assetEntryLocalService.updateAssetEntry(entry);
		
		this.reindex(edition, false);

		// Si le nouveau statut est "DRAFT" et qu'il y a une version live, on
		// supprime cette dernière
		Edition liveEdition = edition.getLiveVersion();
		if (status == WorkflowConstants.STATUS_DRAFT && liveEdition != null) {
			this.removeEdition(liveEdition.getEditionId());
		}

		return edition;
	}

	/**
	 * Met à jour le statut de l'édition "manuellement" (pas via le workflow)
	 */
	public void updateStatus(Edition edition, int status)
		throws PortalException {
		this.updateStatus(edition.getUserId(), edition.getEditionId(), status, null, null);
	}

	/**
	 * Delete an Edition
	 * 
	 * @param editionId
	 *            The ID of the edition to delete
	 * @return The deleted Edition
	 * @throws PortalException
	 */
	public Edition removeEdition(long editionId) throws PortalException {
		AssetEntry entry = AssetEntryLocalServiceUtil
			.fetchEntry(Edition.class.getName(), editionId);

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
			AssetEntryLocalServiceUtil.deleteEntry(Edition.class.getName(),
				editionId);

		}

		// Delete the Edition
		Edition edition = editionPersistence.remove(editionId);

		// Delete the index
		this.reindex(edition, true);
		
		// Supprime ce qui a rapport au workflow
		WorkflowInstanceLinkLocalServiceUtil.deleteWorkflowInstanceLinks(
			edition.getCompanyId(), edition.getGroupId(), Edition.class.getName(),
			edition.getEditionId());

		// S'il existe une version live de l'édition, on la supprime
		Edition liveEdition = edition.getLiveVersion();
		if (liveEdition != null) {
			this.removeEdition(liveEdition.getEditionId());
		}

		return edition;
	}

	/**
	 * Reindex l'édition dans le moteur de recherche
	 */
	private void reindex(Edition edition, boolean delete)
		throws SearchException {
		Indexer<Edition> indexer = IndexerRegistryUtil
			.nullSafeGetIndexer(Edition.class);
		if (delete) {
			indexer.delete(edition);
		} else {
			indexer.reindex(edition);
		}
	}

	/**
	 * Renvoie la liste des vocabulaires rattachés à l'entité Edition
	 */
	public List<AssetVocabulary> getAttachedVocabularies(long groupId) {
		List<AssetVocabulary> vocabularies = AssetVocabularyLocalServiceUtil
			.getAssetVocabularies(-1, -1);
		List<AssetVocabulary> attachedVocabularies = new ArrayList<AssetVocabulary>();
		long classNameId = ClassNameLocalServiceUtil
			.getClassNameId(Edition.class);
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
	 * Retourne toutes les éditions d'un groupe
	 */
	public List<Edition> getByGroupId(long groupId) {
		return this.editionPersistence.findByGroupId(groupId);
	}

	/**
	 * Lance une recherche selon le searchContext
	 */
	public Hits search(SearchContext searchContext) throws SearchException {
		Indexer<Edition> indexer = IndexerRegistryUtil
			.nullSafeGetIndexer(Edition.class);
		return indexer.search(searchContext);
	}

	/**
	 * Lance une recherche par mots-clés
	 */
	public List<Edition> findByKeyword(String keyword, long groupId, int start,
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

		return editionPersistence.findWithDynamicQuery(dynamicQuery, start,
			end);
	}

	/**
	 * Compte de la recherche par mots-clés
	 */
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

		return editionPersistence.countWithDynamicQuery(dynamicQuery);
	}

}