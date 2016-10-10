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
import eu.strasbourg.service.edition.model.Edition;
import eu.strasbourg.service.edition.model.EditionGallery;
import eu.strasbourg.service.edition.service.base.EditionGalleryLocalServiceBaseImpl;

/**
 * The implementation of the edition gallery local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link eu.strasbourg.service.edition.service.EditionGalleryLocalService}
 * interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 *
 * @author BenjaminBini
 * @see EditionGalleryLocalServiceBaseImpl
 * @see eu.strasbourg.service.edition.service.EditionGalleryLocalServiceUtil
 */
@ProviderType
public class EditionGalleryLocalServiceImpl
	extends EditionGalleryLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link
	 * eu.strasbourg.service.edition.service.EditionGalleryLocalServiceUtil} to
	 * access the edition gallery local service.
	 */


	/**
	 * Crée un lien vide avec une PK, non ajouté à la base de donnée
	 */
	public EditionGallery createEditionGallery(ServiceContext sc) throws PortalException {
		User user = UserLocalServiceUtil.getUser(sc.getUserId());

		long pk = counterLocalService.increment();

		EditionGallery gallery = this.editionGalleryLocalService.createEditionGallery(pk);

		gallery.setGroupId(sc.getScopeGroupId());
		gallery.setUserName(user.getFullName());
		gallery.setUserId(sc.getUserId());

		gallery.setStatus(WorkflowConstants.STATUS_DRAFT);

		return gallery;
	}

	/**
	 * Met à jour un lien et l'enregistre en base de données
	 */
	public EditionGallery updateEditionGallery(EditionGallery gallery, ServiceContext sc)
		throws PortalException {
		User user = UserLocalServiceUtil.getUser(sc.getUserId());

		gallery.setStatusByUserId(sc.getUserId());
		gallery.setStatusByUserName(user.getFullName());
		gallery.setStatusDate(sc.getModifiedDate());

		// Si on n'utilise pas le framework workflow, simple gestion
		// brouillon/publié
		if (!WorkflowDefinitionLinkLocalServiceUtil.hasWorkflowDefinitionLink(
			sc.getCompanyId(), sc.getScopeGroupId(), EditionGallery.class.getName())) {
			if (sc.getWorkflowAction() == WorkflowConstants.ACTION_PUBLISH) {
				gallery.setStatus(WorkflowConstants.STATUS_APPROVED);
			} else {
				gallery.setStatus(WorkflowConstants.STATUS_DRAFT);
				// Si le statut est "DRAFT" et qu'il y a une version live, on
				// supprime cette dernière
				EditionGallery liveEditionGallery = gallery.getLiveVersion();
				if (liveEditionGallery != null) {
					this.removeGallery(liveEditionGallery.getGalleryId());
				}
			}
			gallery = this.editionGalleryLocalService.updateEditionGallery(gallery);
			this.updateAssetEntry(gallery, sc);
			this.reindex(gallery, false);
		} else { // Si le framework worflow est actif, c'est celui-ci qui gère
				 // l'enregistrement
			gallery = this.editionGalleryLocalService.updateEditionGallery(gallery);
			WorkflowHandlerRegistryUtil.startWorkflowInstance(
				gallery.getCompanyId(), gallery.getGroupId(), gallery.getUserId(),
				EditionGallery.class.getName(), gallery.getPrimaryKey(), gallery, sc);
		}

		return gallery;
	}

	/**
	 * Met à jour l'AssetEntry rattaché à la galerie d'éditions
	 */
	private void updateAssetEntry(EditionGallery editionGallery,
		ServiceContext sc) throws PortalException {

		this.assetEntryLocalService.updateEntry(sc.getUserId(), // User ID
			sc.getScopeGroupId(), // Group ID
			editionGallery.getCreateDate(), // Date of creation
			editionGallery.getModifiedDate(), // Date of modification
			EditionGallery.class.getName(), // Class name
			editionGallery.getPrimaryKey(), // Class PK
			editionGallery.getUuid(), // UUID
			0, // Class type ID
			sc.getAssetCategoryIds(), // Categories IDs
			sc.getAssetTagNames(), // Tags IDs
			true, // Listable
			editionGallery.getStatus() == WorkflowConstants.STATUS_APPROVED, // Visible
			editionGallery.getPublicationDate(), // Start date
			null, // End date
			editionGallery.getPublicationDate(), // Publication date
			null, // Date of expiration
			ContentTypes.TEXT_HTML, // Content type
			editionGallery.getTitle(), // Title
			editionGallery.getDescription(), // Description
			editionGallery.getDescription(), // Summary
			null, // URL
			null, // Layout uuid
			0, // Width
			0, // Height
			null); // Priority

		// Réindexe la galerie
		this.reindex(editionGallery, false);
	}

	/**
	 * Met à jour le statut de la galerie par le framework workflow
	 */
	public EditionGallery updateStatus(long userId, long entryId, int status,
		ServiceContext sc, Map<String, Serializable> workflowContext)
		throws PortalException {
		EditionGallery gallery = this.getEditionGallery(entryId);
		User user = UserLocalServiceUtil.getUser(userId);

		gallery.setStatus(status);
		gallery.setStatusByUserId(user.getUserId());
		gallery.setStatusByUserName(user.getFullName());
		gallery.setStatusDate(new Date());

		gallery = this.editionGalleryLocalService.updateEditionGallery(gallery);

		AssetEntry entry = this.assetEntryLocalService
			.getEntry(EditionGallery.class.getName(), gallery.getPrimaryKey());
		entry.setVisible(status == WorkflowConstants.STATUS_APPROVED);
		this.assetEntryLocalService.updateAssetEntry(entry);
		
		this.reindex(gallery, false);

		// Si le nouveau statut est "DRAFT" et qu'il y a une version live, on
		// supprime cette dernière
		EditionGallery liveGallery = gallery.getLiveVersion();
		if (status == WorkflowConstants.STATUS_DRAFT && liveGallery != null) {
			this.removeGallery(liveGallery.getGalleryId());
		}

		return gallery;
	}

	/**
	 * Met à jour le statut de la galerie "manuellement" (pas via le workflow)
	 */
	public void updateStatus(EditionGallery gallery, int status)
		throws PortalException {
		this.updateStatus(gallery.getUserId(), gallery.getGalleryId(), status, null, null);
	}


	/**
	 * Delete an Edition Gallery
	 * 
	 * @param galleryId
	 *            The ID of the edition gallery to delete
	 * @return The deleted EditionGallery
	 * @throws PortalException
	 */
	public EditionGallery removeGallery(long galleryId) throws PortalException {
		AssetEntry entry = AssetEntryLocalServiceUtil
			.fetchEntry(EditionGallery.class.getName(), galleryId);

		if (entry != null) {

			// Supprime le lien avec les catégories
			long[] categoryIds = entry.getCategoryIds();
			for (int i = 0; i < categoryIds.length; i++) {
				AssetEntryLocalServiceUtil.deleteAssetCategoryAssetEntry(
					categoryIds[i], entry.getEntryId());
			}

			// Supprime le lien avec les tags
			long[] tagIds = AssetEntryLocalServiceUtil
				.getAssetTagPrimaryKeys(entry.getEntryId());
			for (int i = 0; i < tagIds.length; i++) {
				AssetEntryLocalServiceUtil.deleteAssetTagAssetEntry(tagIds[i],
					entry.getEntryId());
			}

			// Supprime le lien avec les autres entités
			List<AssetLink> links = this.assetLinkLocalService
				.getLinks(entry.getEntryId());
			for (AssetLink link : links) {
				AssetLinkLocalServiceUtil.deleteAssetLink(link);
			}

			// Supprime l'AssetEntry
			AssetEntryLocalServiceUtil
				.deleteEntry(EditionGallery.class.getName(), galleryId);
			
		}

		// Supprime la galerie
		EditionGallery gallery = editionGalleryPersistence
			.remove(galleryId);

		// Supprime l'index
		this.reindex(gallery, true);

		// Supprime ce qui a rapport au workflow
		WorkflowInstanceLinkLocalServiceUtil.deleteWorkflowInstanceLinks(
			gallery.getCompanyId(), gallery.getGroupId(), Edition.class.getName(),
			gallery.getGalleryId());

		// S'il existe une version live de la galerie, on la supprime
		EditionGallery liveGallery = gallery.getLiveVersion();
		if (liveGallery != null) {
			this.removeGallery(liveGallery.getGalleryId());
		}
		
		return gallery;
	}
	
	/**
	 * Reindex la galerie d'éditions dans le moteur de recherche
	 */
	private void reindex(EditionGallery editionGallery, boolean delete)
		throws SearchException {
		Indexer<EditionGallery> indexer = IndexerRegistryUtil
			.nullSafeGetIndexer(EditionGallery.class);
		if (delete) {
			indexer.delete(editionGallery);
		} else {
			indexer.reindex(editionGallery);
		}
	}

	public List<AssetVocabulary> getAttachedVocabularies(long groupId) {
		List<AssetVocabulary> vocabularies = AssetVocabularyLocalServiceUtil
			.getAssetVocabularies(-1, -1);
		List<AssetVocabulary> attachedVocabularies = new ArrayList<AssetVocabulary>();
		long classNameId = ClassNameLocalServiceUtil
			.getClassNameId(EditionGallery.class);
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
	 * Retourne toutes les galeries éditions d'un groupe
	 */
	public List<EditionGallery> getByGroupId(long groupId) {
		return this.editionGalleryPersistence.findByGroupId(groupId);
	}

	public List<EditionGallery> findByKeyword(String keyword, long groupId,
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

		return editionPersistence.findWithDynamicQuery(dynamicQuery, start,
			end);
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

		return editionPersistence.countWithDynamicQuery(dynamicQuery);
	}

	public Hits search(SearchContext searchContext) throws SearchException {
		Indexer<EditionGallery> indexer = IndexerRegistryUtil
			.nullSafeGetIndexer(EditionGallery.class);
		return indexer.search(searchContext);
	}
}