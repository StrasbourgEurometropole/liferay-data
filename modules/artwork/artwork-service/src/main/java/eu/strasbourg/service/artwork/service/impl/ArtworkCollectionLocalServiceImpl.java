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
import com.liferay.asset.kernel.service.AssetLinkLocalServiceUtil;
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
import eu.strasbourg.service.artwork.model.ArtworkCollection;
import eu.strasbourg.service.artwork.service.base.ArtworkCollectionLocalServiceBaseImpl;

/**
 * The implementation of the artworkCollection local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link eu.strasbourg.service.artworkCollection.service.ArtworkCollectionLocalService}
 * interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 *
 * @author BenjaminBini
 * @see ArtworkCollectionLocalServiceBaseImpl
 * @see eu.strasbourg.service.artworkCollection.service.ArtworkCollectionLocalServiceUtil
 */
@ProviderType
public class ArtworkCollectionLocalServiceImpl
	extends ArtworkCollectionLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link
	 * eu.strasbourg.service.artworkCollection.service.
	 * ArtworkCollectionLocalServiceUtil} to access the artworkCollection local
	 * service.
	 */

	/**
	 * Crée une édition vide avec une PK, non ajouté à la base de donnée
	 */
	public ArtworkCollection createArtworkCollection(ServiceContext sc) throws PortalException {
		User user = UserLocalServiceUtil.getUser(sc.getUserId());

		long pk = counterLocalService.increment();

		ArtworkCollection collection = this.artworkCollectionLocalService.createArtworkCollection(pk);

		collection.setGroupId(sc.getScopeGroupId());
		collection.setUserName(user.getFullName());
		collection.setUserId(sc.getUserId());

		collection.setStatus(WorkflowConstants.STATUS_DRAFT);

		return collection;
	}

	/**
	 * Met à jour une édition et l'enregistre en base de données
	 */
	public ArtworkCollection updateArtworkCollection(ArtworkCollection collection, ServiceContext sc)
		throws PortalException {
		User user = UserLocalServiceUtil.getUser(sc.getUserId());

		collection.setStatusByUserId(sc.getUserId());
		collection.setStatusByUserName(user.getFullName());
		collection.setStatusDate(sc.getModifiedDate());

		// Si on n'utilise pas le framework workflow, simple gestion
		// brouillon/publié
		if (!WorkflowDefinitionLinkLocalServiceUtil.hasWorkflowDefinitionLink(
			sc.getCompanyId(), sc.getScopeGroupId(), ArtworkCollection.class.getName())) {
			if (sc.getWorkflowAction() == WorkflowConstants.ACTION_PUBLISH) {
				collection.setStatus(WorkflowConstants.STATUS_APPROVED);
			} else {
				collection.setStatus(WorkflowConstants.STATUS_DRAFT);
				// Si le statut est "DRAFT" et qu'il y a une version live, on
				// supprime cette dernière
				ArtworkCollection liveArtworkCollection = collection.getLiveVersion();
				if (liveArtworkCollection != null) {
					this.removeArtworkCollection(liveArtworkCollection.getCollectionId());
				}
			}
			collection = this.artworkCollectionLocalService.updateArtworkCollection(collection);
			this.updateAssetEntry(collection, sc);
			this.reindex(collection, false);
		} else { // Si le framework worflow est actif, c'est celui-ci qui gère
				 // l'enregistrement
			collection = this.artworkCollectionLocalService.updateArtworkCollection(collection);
			WorkflowHandlerRegistryUtil.startWorkflowInstance(
				collection.getCompanyId(), collection.getGroupId(), collection.getUserId(),
				ArtworkCollection.class.getName(), collection.getPrimaryKey(), collection, sc);
		}

		return collection;
	}

	/**
	 * Update the AssetEntry linked to the artworkCollection
	 */
	private void updateAssetEntry(ArtworkCollection artworkCollection,
		ServiceContext sc) throws PortalException {
		this.assetEntryLocalService.updateEntry(sc.getUserId(), // User ID
			sc.getScopeGroupId(), // Group ID
			artworkCollection.getCreateDate(), // Date of creation
			artworkCollection.getModifiedDate(), // Date of modification
			ArtworkCollection.class.getName(), // Class name
			artworkCollection.getPrimaryKey(), // Class PK
			artworkCollection.getUuid(), // UUID
			0, // Class type ID
			sc.getAssetCategoryIds(), // Categories IDs
			sc.getAssetTagNames(), // Tags IDs
			true, // Listable
			artworkCollection.isApproved(), // Visible
			artworkCollection.getCreateDate(), // Start date
			null, // End date
			artworkCollection.getCreateDate(), // Date of publication
			null, // Date of expiration
			ContentTypes.TEXT_HTML, // Content type
			artworkCollection.getTitle(), // Title
			artworkCollection.getDescription(), // Description
			artworkCollection.getDescription(), // Summary
			null, // URL
			null, // Layout uuid
			0, // Width
			0, // Height
			null); // Priority
	}

	/**
	/**
	 * Met à jour le statut de l'oeuvre par le framework workflow
	 */
	public ArtworkCollection updateStatus(long userId, long entryId, int status,
		ServiceContext sc, Map<String, Serializable> workflowContext)
		throws PortalException {
		ArtworkCollection collection = this.getArtworkCollection(entryId);
		User user = UserLocalServiceUtil.getUser(userId);

		collection.setStatus(status);
		collection.setStatusByUserId(user.getUserId());
		collection.setStatusByUserName(user.getFullName());
		collection.setStatusDate(new Date());

		collection = this.artworkCollectionLocalService.updateArtworkCollection(collection);

		AssetEntry entry = this.assetEntryLocalService
			.getEntry(ArtworkCollection.class.getName(), collection.getPrimaryKey());
		entry.setVisible(status == WorkflowConstants.STATUS_APPROVED);
		this.assetEntryLocalService.updateAssetEntry(entry);
		
		this.reindex(collection, false);

		// Si le nouveau statut est "DRAFT" et qu'il y a une version live, on
		// supprime cette dernière
		ArtworkCollection liveArtworkCollection = collection.getLiveVersion();
		if (status == WorkflowConstants.STATUS_DRAFT && liveArtworkCollection != null) {
			this.removeArtworkCollection(liveArtworkCollection.getCollectionId());
		}

		return collection;
	}

	/**
	 * Met à jour le statut de l'oeuvre "manuellement" (pas via le workflow)
	 */
	public void updateStatus(ArtworkCollection collection, int status)
		throws PortalException {
		this.updateStatus(collection.getUserId(), collection.getCollectionId(), status, null, null);
	}

	/**
	 * Delete an artworkCollection
	 */
	public ArtworkCollection removeArtworkCollection(long artworkCollectionId)
		throws PortalException {
		AssetEntry entry = this.assetEntryLocalService
			.getEntry(ArtworkCollection.class.getName(), artworkCollectionId);

		// Supprime le lien avec les catégories
		for (long categoryId : entry.getCategoryIds()) {
			this.assetEntryLocalService
				.deleteAssetCategoryAssetEntry(categoryId, entry.getEntryId());
		}

		// Supprime le lien avec les tags
		long[] tagsIds = this.assetEntryLocalService
			.getAssetTagPrimaryKeys(entry.getEntryId());
		for (long tagId : tagsIds) {
			this.assetEntryLocalService.deleteAssetTagAssetEntry(tagId,
				entry.getEntryId());
		}

		// Supprime le lien avec les autres entries
		List<AssetLink> links = AssetLinkLocalServiceUtil
			.getLinks(entry.getEntryId());
		for (AssetLink link : links) {
			AssetLinkLocalServiceUtil.deleteAssetLink(link);
		}

		// Supprime l'AssetEntry
		this.assetEntryLocalService.deleteEntry(entry);

		// Supprime la collection
		ArtworkCollection collection = this.artworkCollectionPersistence
			.remove(artworkCollectionId);

		// Supprime l'index
		reindex(collection, true);
		
		// Supprime ce qui a rapport au workflow
		WorkflowInstanceLinkLocalServiceUtil.deleteWorkflowInstanceLinks(
			collection.getCompanyId(), collection.getGroupId(),
			Artwork.class.getName(), collection.getCollectionId());

		// S'il existe une version live de l'oeuvre, on la supprime
		ArtworkCollection liveCollection = collection.getLiveVersion();
		if (liveCollection != null) {
			this.removeArtworkCollection(liveCollection.getCollectionId());
		}


		return collection;
	}

	/**
	 * Reindex the artworkCollection in the search engine
	 */
	private void reindex(ArtworkCollection artworkCollection, boolean delete)
		throws SearchException {
		Indexer<ArtworkCollection> indexer = IndexerRegistryUtil
			.nullSafeGetIndexer(ArtworkCollection.class);
		if (delete) {
			indexer.delete(artworkCollection);
		} else {
			indexer.reindex(artworkCollection);
		}
	}

	/**
	 * Return the vocabularies attached to the ArtworkCollection entity
	 */
	public List<AssetVocabulary> getAttachedVocabularies(long groupId) {
		List<AssetVocabulary> vocabularies = AssetVocabularyLocalServiceUtil
			.getAssetVocabularies(-1, -1);
		List<AssetVocabulary> attachedVocabularies = new ArrayList<AssetVocabulary>();
		long classNameId = ClassNameLocalServiceUtil
			.getClassNameId(ArtworkCollection.class);
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
	 * Retourne toutes les collections d'oeuvres d'un groupe
	 */
	public List<ArtworkCollection> getByGroupId(long groupId) {
		return this.artworkCollectionPersistence.findByGroupId(groupId);
	}

	public List<ArtworkCollection> findByKeyword(String keyword, long groupId,
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

		return artworkCollectionPersistence.findWithDynamicQuery(dynamicQuery,
			start, end);
	}

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

		return artworkCollectionPersistence.countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Search
	 */
	public Hits search(SearchContext searchContext) throws SearchException {
		Indexer<ArtworkCollection> indexer = IndexerRegistryUtil
			.nullSafeGetIndexer(ArtworkCollection.class);
		return indexer.search(searchContext);
	}
}