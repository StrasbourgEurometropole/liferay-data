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
import eu.strasbourg.service.video.model.Video;
import eu.strasbourg.service.video.service.base.VideoLocalServiceBaseImpl;

/**
 * The implementation of the video local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link eu.strasbourg.service.video.service.VideoLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 *
 * @author BenjaminBini
 * @see VideoLocalServiceBaseImpl
 * @see eu.strasbourg.service.video.service.VideoLocalServiceUtil
 */
@ProviderType
public class VideoLocalServiceImpl extends VideoLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link
	 * eu.strasbourg.service.video.service.VideoLocalServiceUtil} to access
	 * the video local service.
	 */

	/**
	 * Crée une vidéo vide avec une PK, non ajouté à la base de donnée
	 */
	@Override
	public Video createVideo(ServiceContext sc) throws PortalException {
		User user = UserLocalServiceUtil.getUser(sc.getUserId());

		long pk = counterLocalService.increment();

		Video video = this.videoLocalService.createVideo(pk);

		video.setGroupId(sc.getScopeGroupId());
		video.setUserName(user.getFullName());
		video.setUserId(sc.getUserId());

		video.setStatus(WorkflowConstants.STATUS_DRAFT);

		return video;
	}

	/**
	 * Met à jour une vidéo et l'enregistre en base de données
	 */
	@Override
	public Video updateVideo(Video video, ServiceContext sc)
		throws PortalException {
		User user = UserLocalServiceUtil.getUser(sc.getUserId());

		video.setStatusByUserId(sc.getUserId());
		video.setStatusByUserName(user.getFullName());
		video.setStatusDate(sc.getModifiedDate());

		// Si on n'utilise pas le framework workflow, simple gestion
		// brouillon/publié
		if (!WorkflowDefinitionLinkLocalServiceUtil.hasWorkflowDefinitionLink(
			sc.getCompanyId(), sc.getScopeGroupId(), Video.class.getName())) {
			if (sc.getWorkflowAction() == WorkflowConstants.ACTION_PUBLISH) {
				video.setStatus(WorkflowConstants.STATUS_APPROVED);
			} else {
				video.setStatus(WorkflowConstants.STATUS_DRAFT);
				// Si le statut est "DRAFT" et qu'il y a une version live, on
				// supprime cette dernière
				Video liveVideo = video.getLiveVersion();
				if (liveVideo != null) {
					this.removeVideo(liveVideo.getVideoId());
				}
			}
			video = this.videoLocalService.updateVideo(video);
			this.updateAssetEntry(video, sc);
			this.reindex(video, false);
		} else { // Si le framework worflow est actif, c'est celui-ci qui gère
				 // l'enregistrement
			video = this.videoLocalService.updateVideo(video);
			WorkflowHandlerRegistryUtil.startWorkflowInstance(
				video.getCompanyId(), video.getGroupId(), video.getUserId(),
				Video.class.getName(), video.getPrimaryKey(), video, sc);
		}

		return video;
	}


	/**
	 * Met à jour l'AssetEntry rattachée à la vidéo
	 */
	private void updateAssetEntry(Video video, ServiceContext sc)
		throws PortalException {
		this.assetEntryLocalService.updateEntry(sc.getUserId(), // User ID
			sc.getScopeGroupId(), // Group ID
			video.getCreateDate(), // Date of creation
			video.getModifiedDate(), // Date of modification
			Video.class.getName(), // Class name
			video.getPrimaryKey(), // Class PK
			video.getUuid(), // UUID
			0, // Class type ID
			sc.getAssetCategoryIds(), // Categories IDs
			sc.getAssetTagNames(), // Tags IDs
			true, // Listable
			video.isApproved(), // Visible
			video.getCreateDate(), // Start date
			null, // End date
			video.getCreateDate(), // Publication date
			null, // Date of expiration
			ContentTypes.TEXT_HTML, // Content type
			video.getTitle(), // Title
			video.getDescription(), // Description
			video.getDescription(), // Summary
			null, // URL
			null, // Layout uuid
			0, // Width
			0, // Height
			null); // Priority

		// Réindexe la vidéo
		this.reindex(video, false);
	}

	/**
	 * Met à jour le statut de la vidéo par le framework workflow
	 */
	@Override
	public Video updateStatus(long userId, long entryId, int status,
		ServiceContext sc, Map<String, Serializable> workflowContext)
		throws PortalException {
		Video video = this.getVideo(entryId);
		User user = UserLocalServiceUtil.getUser(userId);

		video.setStatus(status);
		video.setStatusByUserId(user.getUserId());
		video.setStatusByUserName(user.getFullName());
		video.setStatusDate(new Date());

		video = this.videoLocalService.updateVideo(video);

		AssetEntry entry = this.assetEntryLocalService
			.getEntry(Video.class.getName(), video.getPrimaryKey());
		entry.setVisible(status == WorkflowConstants.STATUS_APPROVED);
		this.assetEntryLocalService.updateAssetEntry(entry);
		
		this.reindex(video, false);

		// Si le nouveau statut est "DRAFT" et qu'il y a une version live, on
		// supprime cette dernière
		Video liveVideo = video.getLiveVersion();
		if (status == WorkflowConstants.STATUS_DRAFT && liveVideo != null) {
			this.removeVideo(liveVideo.getVideoId());
		}

		return video;
	}

	/**
	 * Met à jour le statut de la vidéo "manuellement" (pas via le workflow)
	 */
	@Override
	public void updateStatus(Video video, int status)
		throws PortalException {
		this.updateStatus(video.getUserId(), video.getVideoId(), status, null, null);
	}

	/**
	 * Supprime une vidéo
	 */
	@Override
	public Video removeVideo(long videoId) throws PortalException {
		AssetEntry entry = AssetEntryLocalServiceUtil
			.fetchEntry(Video.class.getName(), videoId);

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
			AssetEntryLocalServiceUtil.deleteEntry(Video.class.getName(),
				videoId);

		}

		// Delete the Video
		Video video = videoPersistence.remove(videoId);

		// Delete the index
		this.reindex(video, true);
		
		// Supprime ce qui a rapport au workflow
		WorkflowInstanceLinkLocalServiceUtil.deleteWorkflowInstanceLinks(
			video.getCompanyId(), video.getGroupId(), Video.class.getName(),
			video.getVideoId());

		// S'il existe une version live de la vidéo, on la supprime
		Video liveVideo = video.getLiveVersion();
		if (liveVideo != null) {
			this.removeVideo(liveVideo.getVideoId());
		}

		return video;
	}

	/**
	 * Reindex la vidéo dans le moteur de recherche
	 */
	private void reindex(Video video, boolean delete)
		throws SearchException {
		Indexer<Video> indexer = IndexerRegistryUtil
			.nullSafeGetIndexer(Video.class);
		if (delete) {
			indexer.delete(video);
		} else {
			indexer.reindex(video);
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
			.getClassNameId(Video.class);
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
	 * Retourne toutes les vidéos d'un groupe
	 */
	@Override
	public List<Video> getByGroupId(long groupId) {
		return this.videoPersistence.findByGroupId(groupId);
	}

	/**
	 * Lance une recherche par mots-clés
	 */
	@Override
	public List<Video> findByKeyword(String keyword, long groupId, int start,
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

		return videoPersistence.findWithDynamicQuery(dynamicQuery, start,
			end);
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
	 * Lance une recherche selon le searchContext
	 */
	@Override
	public Hits search(SearchContext searchContext) throws SearchException {
		Indexer<Video> indexer = IndexerRegistryUtil
			.nullSafeGetIndexer(Video.class);
		return indexer.search(searchContext);
	}
}