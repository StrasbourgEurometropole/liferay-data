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

package eu.strasbourg.service.video.service.impl;

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
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
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
import eu.strasbourg.service.video.model.Video;
import eu.strasbourg.service.video.model.VideoGallery;
import eu.strasbourg.service.video.service.base.VideoGalleryLocalServiceBaseImpl;

/**
 * The implementation of the video gallery local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link eu.strasbourg.service.video.service.VideoGalleryLocalService}
 * interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 *
 * @author BenjaminBini
 * @see VideoGalleryLocalServiceBaseImpl
 * @see eu.strasbourg.service.video.service.VideoGalleryLocalServiceUtil
 */
@ProviderType
public class VideoGalleryLocalServiceImpl
	extends VideoGalleryLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link
	 * eu.strasbourg.service.video.service.VideoGalleryLocalServiceUtil} to
	 * access the video gallery local service.
	 */

	/**
	 * Crée un lien vide avec une PK, non ajouté à la base de donnée
	 */
	@Override
	public VideoGallery createVideoGallery(ServiceContext sc)
		throws PortalException {
		User user = UserLocalServiceUtil.getUser(sc.getUserId());

		long pk = counterLocalService.increment();

		VideoGallery gallery = this.videoGalleryLocalService
			.createVideoGallery(pk);

		gallery.setGroupId(sc.getScopeGroupId());
		gallery.setUserName(user.getFullName());
		gallery.setUserId(sc.getUserId());

		gallery.setStatus(WorkflowConstants.STATUS_DRAFT);

		return gallery;
	}

	/**
	 * Met à jour un lien et l'enregistre en base de données
	 */
	@Override
	public VideoGallery updateVideoGallery(VideoGallery gallery,
		ServiceContext sc) throws PortalException {
		User user = UserLocalServiceUtil.getUser(sc.getUserId());

		gallery.setStatusByUserId(sc.getUserId());
		gallery.setStatusByUserName(user.getFullName());
		gallery.setStatusDate(sc.getModifiedDate());

		// Si on n'utilise pas le framework workflow, simple gestion
		// brouillon/publié
		if (!WorkflowDefinitionLinkLocalServiceUtil.hasWorkflowDefinitionLink(
			sc.getCompanyId(), sc.getScopeGroupId(),
			VideoGallery.class.getName())) {
			if (sc.getWorkflowAction() == WorkflowConstants.ACTION_PUBLISH) {
				// Si l'action est la publication, on met le statut "publié" si
				// la date de publication est dans le passé et on met
				// "scheduled" si la date est dans le futur
				if (gallery.getPublicationDate().after(new Date())) {
					gallery.setStatus(WorkflowConstants.STATUS_SCHEDULED);
				} else {
					gallery.setStatus(WorkflowConstants.STATUS_APPROVED);
				}
			} else {
				gallery.setStatus(WorkflowConstants.STATUS_DRAFT);
				// Si le statut est "DRAFT" et qu'il y a une version live, on
				// supprime cette dernière
				VideoGallery liveVideoGallery = gallery.getLiveVersion();
				if (liveVideoGallery != null) {
					this.removeGallery(liveVideoGallery.getGalleryId());
				}
			}
			gallery = this.videoGalleryLocalService.updateVideoGallery(gallery);
			this.updateAssetEntry(gallery, sc);
			this.reindex(gallery, false);
		} else { // Si le framework worflow est actif, c'est celui-ci qui gère
				 // l'enregistrement
			gallery = this.videoGalleryLocalService.updateVideoGallery(gallery);
			WorkflowHandlerRegistryUtil.startWorkflowInstance(
				gallery.getCompanyId(), gallery.getGroupId(),
				gallery.getUserId(), VideoGallery.class.getName(),
				gallery.getPrimaryKey(), gallery, sc);
		}

		return gallery;
	}

	/**
	 * Met à jour l'AssetEntry rattaché à la galerie d'éditions
	 */
	private void updateAssetEntry(VideoGallery videoGallery, ServiceContext sc)
		throws PortalException {

		this.assetEntryLocalService.updateEntry(sc.getUserId(), // User ID
			sc.getScopeGroupId(), // Group ID
			videoGallery.getCreateDate(), // Date of creation
			videoGallery.getModifiedDate(), // Date of modification
			VideoGallery.class.getName(), // Class name
			videoGallery.getPrimaryKey(), // Class PK
			videoGallery.getUuid(), // UUID
			0, // Class type ID
			sc.getAssetCategoryIds(), // Categories IDs
			sc.getAssetTagNames(), // Tags IDs
			true, // Listable
			videoGallery.getStatus() == WorkflowConstants.STATUS_APPROVED, // Visible
			videoGallery.getCreateDate(), // Start date
			null, // End date
			videoGallery.getPublicationDate(), // Publication date
			null, // Date of expiration
			ContentTypes.TEXT_HTML, // Content type
			videoGallery.getTitle(), // Title
			videoGallery.getDescription(), // Description
			videoGallery.getDescription(), // Summary
			null, // URL
			null, // Layout uuid
			0, // Width
			0, // Height
			null); // Priority

		// Réindexe la galerie
		this.reindex(videoGallery, false);
	}

	/**
	 * Met à jour le statut de la galerie par le framework workflow
	 */
	@Override
	public VideoGallery updateStatus(long userId, long entryId, int status,
		ServiceContext sc, Map<String, Serializable> workflowContext)
		throws PortalException {
		Date now = new Date();

		VideoGallery gallery = this.getVideoGallery(entryId);
		gallery.setStatus(status);
		User user = UserLocalServiceUtil.fetchUser(userId);
		if (user != null) {
			gallery.setStatusByUserId(user.getUserId());
			gallery.setStatusByUserName(user.getFullName());
		}
		gallery.setStatusDate(new Date());
		if (status == WorkflowConstants.STATUS_APPROVED) {
			gallery.setPublicationDate(now);
		}
		gallery = this.videoGalleryLocalService.updateVideoGallery(gallery);

		AssetEntry entry = this.assetEntryLocalService
			.getEntry(VideoGallery.class.getName(), gallery.getPrimaryKey());
		entry.setVisible(status == WorkflowConstants.STATUS_APPROVED);
		if (entry.isVisible()) {
			entry.setPublishDate(now);
		}
		this.assetEntryLocalService.updateAssetEntry(entry);

		this.reindex(gallery, false);

		// Si le nouveau statut est "DRAFT" et qu'il y a une version live, on
		// supprime cette dernière
		VideoGallery liveGallery = gallery.getLiveVersion();
		if (status == WorkflowConstants.STATUS_DRAFT && liveGallery != null) {
			this.removeGallery(liveGallery.getGalleryId());
		}

		return gallery;
	}

	/**
	 * Met à jour le statut de la galerie "manuellement" (pas via le workflow)
	 */
	@Override
	public void updateStatus(VideoGallery gallery, int status)
		throws PortalException {
		this.updateStatus(gallery.getUserId(), gallery.getGalleryId(), status,
			null, null);
	}

	/**
	 * Modifie le statut de toutes les vidéos au statut "SCHEDULED" qui ont une
	 * date de publication dans le futur
	 */
	@Override
	public void checkGalleries() throws PortalException {
		List<VideoGallery> galleries = this.videoGalleryPersistence
			.findByPublicationDateAndStatus(new Date(),
				WorkflowConstants.STATUS_SCHEDULED);
		int n = 0;
		for (VideoGallery gallery : galleries) {
			this.updateStatus(gallery, WorkflowConstants.STATUS_APPROVED);
			n++;
		}
		if (n > 0) {
			_log.info("Published " + n + " galleries");
		}
	}

	/**
	 * Delete an Video Gallery
	 * 
	 * @param galleryId
	 *            The ID of the video gallery to delete
	 * @return The deleted VideoGallery
	 * @throws PortalException
	 */
	@Override
	public VideoGallery removeGallery(long galleryId) throws PortalException {
		AssetEntry entry = AssetEntryLocalServiceUtil
			.fetchEntry(VideoGallery.class.getName(), galleryId);

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
			AssetEntryLocalServiceUtil.deleteEntry(VideoGallery.class.getName(),
				galleryId);

		}

		// Supprime la galerie
		VideoGallery gallery = videoGalleryPersistence.remove(galleryId);

		// Supprime l'index
		this.reindex(gallery, true);

		// Supprime ce qui a rapport au workflow
		WorkflowInstanceLinkLocalServiceUtil.deleteWorkflowInstanceLinks(
			gallery.getCompanyId(), gallery.getGroupId(), Video.class.getName(),
			gallery.getGalleryId());

		return gallery;
	}

	/**
	 * Reindex la galerie d'éditions dans le moteur de recherche
	 */
	private void reindex(VideoGallery videoGallery, boolean delete)
		throws SearchException {
		Indexer<VideoGallery> indexer = IndexerRegistryUtil
			.nullSafeGetIndexer(VideoGallery.class);
		if (delete) {
			indexer.delete(videoGallery);
		} else {
			indexer.reindex(videoGallery);
		}
	}

	/**
	 * Retourne la liste des vocabulaires rattachés à l'entité
	 */
	@Override
	public List<AssetVocabulary> getAttachedVocabularies(long groupId) {
		List<AssetVocabulary> vocabularies = AssetVocabularyLocalServiceUtil
			.getAssetVocabularies(-1, -1);
		List<AssetVocabulary> attachedVocabularies = new ArrayList<AssetVocabulary>();
		long classNameId = ClassNameLocalServiceUtil
			.getClassNameId(VideoGallery.class);
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
	@Override
	public List<VideoGallery> getByGroupId(long groupId) {
		return this.videoGalleryPersistence.findByGroupId(groupId);
	}

	/**
	 * Recherche par mots clés
	 */
	public List<VideoGallery> findByKeyword(String keyword, long groupId,
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

		return videoPersistence.findWithDynamicQuery(dynamicQuery, start, end);
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

		return videoPersistence.countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Recherche par le moteur de recherche
	 */
	@Override
	public Hits search(SearchContext searchContext) throws SearchException {
		Indexer<VideoGallery> indexer = IndexerRegistryUtil
			.nullSafeGetIndexer(VideoGallery.class);
		return indexer.search(searchContext);
	}

	private final Log _log = LogFactoryUtil.getLog("strasbourg");
}