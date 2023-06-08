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

package eu.strasbourg.service.artwork.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.LongStream;

import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.model.AssetLink;
import com.liferay.asset.kernel.model.AssetVocabulary;
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
import eu.strasbourg.service.artwork.model.Artwork;
import eu.strasbourg.service.artwork.service.base.ArtworkLocalServiceBaseImpl;

/**
 * The implementation of the artwork local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link eu.strasbourg.service.artwork.service.ArtworkLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 *
 * @author BenjaminBini
 * @see ArtworkLocalServiceBaseImpl
 * @see eu.strasbourg.service.artwork.service.ArtworkLocalServiceUtil
 */
@ProviderType
public class ArtworkLocalServiceImpl extends ArtworkLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link
	 * eu.strasbourg.service.artwork.service.ArtworkLocalServiceUtil} to access
	 * the artwork local service.
	 */

	/**
	 * Crée une édition vide avec une PK, non ajouté à la base de donnée
	 */
	@Override
	public Artwork createArtwork(ServiceContext sc) throws PortalException {
		User user = UserLocalServiceUtil.getUser(sc.getUserId());

		long pk = counterLocalService.increment();

		Artwork artwork = this.artworkLocalService.createArtwork(pk);

		artwork.setGroupId(sc.getScopeGroupId());
		artwork.setUserName(user.getFullName());
		artwork.setUserId(sc.getUserId());

		artwork.setStatus(WorkflowConstants.STATUS_DRAFT);

		return artwork;
	}

	/**
	 * Met à jour une édition et l'enregistre en base de données
	 */
	@Override
	public Artwork updateArtwork(Artwork artwork, ServiceContext sc)
		throws PortalException {
		User user = UserLocalServiceUtil.getUser(sc.getUserId());

		artwork.setStatusByUserId(sc.getUserId());
		artwork.setStatusByUserName(user.getFullName());
		artwork.setStatusDate(sc.getModifiedDate());

		// Si on n'utilise pas le framework workflow, simple gestion
		// brouillon/publié
		if (!WorkflowDefinitionLinkLocalServiceUtil.hasWorkflowDefinitionLink(
			sc.getCompanyId(), sc.getScopeGroupId(), Artwork.class.getName())) {
			if (sc.getWorkflowAction() == WorkflowConstants.ACTION_PUBLISH) {
				artwork.setStatus(WorkflowConstants.STATUS_APPROVED);
			} else {
				artwork.setStatus(WorkflowConstants.STATUS_DRAFT);
				// Si le statut est "DRAFT" et qu'il y a une version live, on
				// supprime cette dernière
				Artwork liveArtwork = artwork.getLiveVersion();
				if (liveArtwork != null) {
					this.removeArtwork(liveArtwork.getArtworkId());
				}
			}
			artwork = this.artworkLocalService.updateArtwork(artwork);
			this.updateAssetEntry(artwork, sc);
			this.reindex(artwork, false);
		} else { // Si le framework worflow est actif, c'est celui-ci qui gère
				 // l'enregistrement
			artwork = this.artworkLocalService.updateArtwork(artwork);
			WorkflowHandlerRegistryUtil.startWorkflowInstance(
				artwork.getCompanyId(), artwork.getGroupId(),
				artwork.getUserId(), Artwork.class.getName(),
				artwork.getPrimaryKey(), artwork, sc);
		}

		return artwork;
	}

	/**
	 * Update the AssetEntry linked to the artwork
	 */
	private void updateAssetEntry(Artwork artwork, ServiceContext sc)
		throws PortalException {
		this.assetEntryLocalService.updateEntry(sc.getUserId(), // User ID
			sc.getScopeGroupId(), // Group ID
			artwork.getCreateDate(), // Date of creation
			artwork.getModifiedDate(), // Date of modification
			Artwork.class.getName(), // Class name
			artwork.getPrimaryKey(), // Class PK
			artwork.getUuid(), // UUID
			0, // Class type ID
			sc.getAssetCategoryIds(), // Categories IDs
			sc.getAssetTagNames(), // Tags IDs
			true, // Listable
			artwork.isApproved(), // Visible
			artwork.getCreateDate(), // Start date
			null, // End date
			artwork.getCreateDate(), // Date of publication
			null, // Date of expiration
			ContentTypes.TEXT_HTML, // Content type
			artwork.getTitle(), // Title
			artwork.getDescription(), // Description
			artwork.getDescription(), // Summary
			null, // URL
			null, // Layout uuid
			0, // Width
			0, // Height
			null); // Priority
	}

	/**
	 * Met à jour le statut de l'oeuvre par le framework workflow
	 */
	@Override
	public Artwork updateStatus(long userId, long entryId, int status,
		ServiceContext sc, Map<String, Serializable> workflowContext)
		throws PortalException {
		Artwork artwork = this.getArtwork(entryId);

		artwork.setStatus(status);
		User user = UserLocalServiceUtil.fetchUser(userId);
		if (user != null) {
			artwork.setStatusByUserId(user.getUserId());
			artwork.setStatusByUserName(user.getFullName());
		}
		artwork.setStatusDate(new Date());

		artwork = this.artworkLocalService.updateArtwork(artwork);

		AssetEntry entry = this.assetEntryLocalService
			.getEntry(Artwork.class.getName(), artwork.getPrimaryKey());
		entry.setVisible(status == WorkflowConstants.STATUS_APPROVED);
		this.assetEntryLocalService.updateAssetEntry(entry);

		this.reindex(artwork, false);

		// Si le nouveau statut est "DRAFT" et qu'il y a une version live, on
		// supprime cette dernière
		Artwork liveArtwork = artwork.getLiveVersion();
		if (status == WorkflowConstants.STATUS_DRAFT && liveArtwork != null) {
			this.removeArtwork(liveArtwork.getArtworkId());
		}

		return artwork;
	}

	/**
	 * Met à jour le statut de l'oeuvre "manuellement" (pas via le workflow)
	 */
	@Override
	public void updateStatus(Artwork artwork, int status)
		throws PortalException {
		this.updateStatus(artwork.getUserId(), artwork.getArtworkId(), status,
			null, null);
	}

	/**
	 * Delete an artwork
	 */
	@Override
	public Artwork removeArtwork(long artworkId) throws PortalException {
		AssetEntry entry = this.assetEntryLocalService
			.fetchEntry(Artwork.class.getName(), artworkId);

		if (entry != null) {

			// Supprime le lien avec les catégories
			for (long categoryId : entry.getCategoryIds()) {
				this.assetEntryLocalService.deleteAssetCategoryAssetEntry(
					categoryId, entry.getEntryId());
			}

			// Supprime le lien avec les tags
			long[] tagsIds = this.assetEntryLocalService
				.getAssetTagPrimaryKeys(entry.getEntryId());
			for (long tagId : tagsIds) {
				this.assetEntryLocalService.deleteAssetTagAssetEntry(tagId,
					entry.getEntryId());
			}

			// Supprime les liens avec les autres AssetEntries
			List<AssetLink> links = this.assetLinkLocalService
				.getLinks(entry.getEntryId());
			for (AssetLink link : links) {
				this.assetLinkLocalService.deleteAssetLink(link);
			}

			// Supprime l'AssetEntry
			this.assetEntryLocalService.deleteEntry(entry);
		}

		// Supprime l'oeuvre
		Artwork artwork = this.artworkPersistence.remove(artworkId);

		// Supprime l'index
		reindex(artwork, true);

		// Supprime ce qui a rapport au workflow
		WorkflowInstanceLinkLocalServiceUtil.deleteWorkflowInstanceLinks(
			artwork.getCompanyId(), artwork.getGroupId(),
			Artwork.class.getName(), artwork.getArtworkId());

		return artwork;
	}

	/**
	 * Reindex the artwork in the search engine
	 */
	private void reindex(Artwork artwork, boolean delete)
		throws SearchException {
		Indexer<Artwork> indexer = IndexerRegistryUtil
			.nullSafeGetIndexer(Artwork.class);
		if (delete) {
			indexer.delete(artwork);
		} else {
			indexer.reindex(artwork);
		}
	}

	/**
	 * Return the vocabularies attached to the Artwork entity
	 */
	@Override
	public List<AssetVocabulary> getAttachedVocabularies(long groupId) {
		List<AssetVocabulary> vocabularies = AssetVocabularyLocalServiceUtil
			.getAssetVocabularies(-1, -1);
		List<AssetVocabulary> attachedVocabularies = new ArrayList<AssetVocabulary>();
		long classNameId = ClassNameLocalServiceUtil
			.getClassNameId(Artwork.class);
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
	 * Retourne toutes les oeuvres d'un groupe
	 */
	@Override
	public List<Artwork> getByGroupId(long groupId) {
		return this.artworkPersistence.findByGroupId(groupId);
	}

	@Override
	public List<Artwork> findByKeyword(String keyword, long groupId, int start,
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

		return artworkPersistence.findWithDynamicQuery(dynamicQuery, start,
			end);
	}

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

		return artworkPersistence.countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Search
	 */
	@Override
	public Hits search(SearchContext searchContext) throws SearchException {
		Indexer<Artwork> indexer = IndexerRegistryUtil
			.nullSafeGetIndexer(Artwork.class);
		return indexer.search(searchContext);
	}
}