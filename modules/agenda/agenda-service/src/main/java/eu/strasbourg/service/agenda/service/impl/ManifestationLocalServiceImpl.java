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
import eu.strasbourg.service.agenda.model.Event;
import eu.strasbourg.service.agenda.model.Manifestation;
import eu.strasbourg.service.agenda.service.base.ManifestationLocalServiceBaseImpl;

/**
 * The implementation of the event manifestation local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link eu.strasbourg.service.agenda.service.ManifestationLocalService}
 * interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 *
 * @author BenjaminBini
 * @see ManifestationLocalServiceBaseImpl
 * @see eu.strasbourg.service.agenda.service.ManifestationLocalServiceUtil
 */
@ProviderType
public class ManifestationLocalServiceImpl
	extends ManifestationLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link
	 * eu.strasbourg.service.agenda.service.ManifestationLocalServiceUtil} to
	 * access the event manifestation local service.
	 */

	/**
	 * Crée un lien vide avec une PK, non ajouté à la base de donnée
	 */
	@Override
	public Manifestation createManifestation(ServiceContext sc)
		throws PortalException {
		User user = UserLocalServiceUtil.getUser(sc.getUserId());

		long pk = counterLocalService.increment();

		Manifestation manifestation = this.manifestationLocalService
			.createManifestation(pk);

		manifestation.setGroupId(sc.getScopeGroupId());
		manifestation.setUserName(user.getFullName());
		manifestation.setUserId(sc.getUserId());

		manifestation.setStatus(WorkflowConstants.STATUS_DRAFT);

		return manifestation;
	}

	/**
	 * Met à jour un lien et l'enregistre en base de données
	 */
	@Override
	public Manifestation updateManifestation(Manifestation manifestation,
		ServiceContext sc) throws PortalException {
		User user = UserLocalServiceUtil.getUser(sc.getUserId());

		manifestation.setStatusByUserId(sc.getUserId());
		manifestation.setStatusByUserName(user.getFullName());
		manifestation.setStatusDate(sc.getModifiedDate());

		// Si on n'utilise pas le framework workflow, simple gestion
		// brouillon/publié
		if (!WorkflowDefinitionLinkLocalServiceUtil.hasWorkflowDefinitionLink(
			sc.getCompanyId(), sc.getScopeGroupId(),
			Manifestation.class.getName())) {
			if (sc.getWorkflowAction() == WorkflowConstants.ACTION_PUBLISH) {
				if (manifestation.getPublicationDate().after(new Date())) {
					manifestation.setStatus(WorkflowConstants.STATUS_SCHEDULED);
				} else {
					manifestation.setStatus(WorkflowConstants.STATUS_APPROVED);
				}
			} else {
				manifestation.setStatus(WorkflowConstants.STATUS_DRAFT);
				// Si le statut est "DRAFT" et qu'il y a une version live, on
				// supprime cette dernière
				Manifestation liveManifestation = manifestation
					.getLiveVersion();
				if (liveManifestation != null) {
					this.removeManifestation(
						liveManifestation.getManifestationId());
				}
			}
			manifestation = this.manifestationLocalService
				.updateManifestation(manifestation);
			this.updateAssetEntry(manifestation, sc);
			this.reindex(manifestation, false);
		} else { // Si le framework worflow est actif, c'est celui-ci qui gère
				 // l'enregistrement
			manifestation = this.manifestationLocalService
				.updateManifestation(manifestation);
			WorkflowHandlerRegistryUtil.startWorkflowInstance(
				manifestation.getCompanyId(), manifestation.getGroupId(),
				manifestation.getUserId(), Manifestation.class.getName(),
				manifestation.getPrimaryKey(), manifestation, sc);
		}

		return manifestation;
	}

	/**
	 * Met à jour l'AssetEntry rattaché à la galerie d'éditions
	 */
	private void updateAssetEntry(Manifestation manifestation,
		ServiceContext sc) throws PortalException {

		this.assetEntryLocalService.updateEntry(sc.getUserId(), // User ID
			sc.getScopeGroupId(), // Group ID
			manifestation.getCreateDate(), // Date of creation
			manifestation.getModifiedDate(), // Date of modification
			Manifestation.class.getName(), // Class name
			manifestation.getPrimaryKey(), // Class PK
			manifestation.getUuid(), // UUID
			0, // Class type ID
			sc.getAssetCategoryIds(), // Categories IDs
			sc.getAssetTagNames(), // Tags IDs
			true, // Listable
			manifestation.getStatus() == WorkflowConstants.STATUS_APPROVED, // Visible
			manifestation.getPublicationDate(), // Start date
			null, // End date
			manifestation.getPublicationDate(), // Publication date
			null, // Date of expiration
			ContentTypes.TEXT_HTML, // Content type
			manifestation.getTitle(), // Title
			manifestation.getDescription(), // Description
			manifestation.getDescription(), // Summary
			null, // URL
			null, // Layout uuid
			0, // Width
			0, // Height
			null); // Priority

		// Réindexe la galerie
		this.reindex(manifestation, false);
	}

	/**
	 * Met à jour le statut de la galerie par le framework workflow
	 */
	@Override
	public Manifestation updateStatus(long userId, long entryId, int status,
		ServiceContext sc, Map<String, Serializable> workflowContext)
		throws PortalException {
		Date now = new Date();
		Manifestation manifestation = this.getManifestation(entryId);
		manifestation.setStatus(status);
		User user = UserLocalServiceUtil.fetchUser(userId);
		if (user != null) {
			manifestation.setStatusByUserId(user.getUserId());
			manifestation.setStatusByUserName(user.getFullName());
		}
		manifestation.setStatusDate(new Date());
		if (manifestation.isApproved()) {
			manifestation.setPublicationDate(now);
		}

		manifestation = this.manifestationLocalService
			.updateManifestation(manifestation);

		AssetEntry entry = this.assetEntryLocalService.getEntry(
			Manifestation.class.getName(), manifestation.getPrimaryKey());
		entry.setVisible(status == WorkflowConstants.STATUS_APPROVED);
		if (entry.isVisible()) {
			entry.setPublishDate(now);
		}
		this.assetEntryLocalService.updateAssetEntry(entry);

		this.reindex(manifestation, false);

		// Si le nouveau statut est "DRAFT" et qu'il y a une version live, on
		// supprime cette dernière
		Manifestation liveManifestation = manifestation.getLiveVersion();
		if (status == WorkflowConstants.STATUS_DRAFT
			&& liveManifestation != null) {
			this.removeManifestation(liveManifestation.getManifestationId());
		}

		return manifestation;
	}

	/**
	 * Met à jour le statut de la galerie "manuellement" (pas via le workflow)
	 */
	@Override
	public void updateStatus(Manifestation manifestation, int status)
		throws PortalException {
		this.updateStatus(manifestation.getUserId(),
			manifestation.getManifestationId(), status, null, null);
	}

	/**
	 * Modifie le statut de tous les manifestations au statut "SCHEDULED" qui
	 * ont une date de publication dans le futur
	 */
	@Override
	public void checkManifestations() throws PortalException {
		List<Manifestation> manifestations = this.manifestationPersistence
			.findByPublicationDateAndStatus(new Date(),
				WorkflowConstants.STATUS_SCHEDULED);
		int n = 0;
		for (Manifestation manifestation : manifestations) {
			this.updateStatus(manifestation, WorkflowConstants.STATUS_APPROVED);
			n++;
		}
		if (n > 0) {
			_log.info("Published " + n + " manifestations");
		}
	}

	/**
	 * Delete an Event Manifestation
	 * 
	 * @param manifestationId
	 *            The ID of the event manifestation to delete
	 * @return The deleted Manifestation
	 * @throws PortalException
	 */
	@Override
	public Manifestation removeManifestation(long manifestationId)
		throws PortalException {
		AssetEntry entry = AssetEntryLocalServiceUtil
			.fetchEntry(Manifestation.class.getName(), manifestationId);

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
				.deleteEntry(Manifestation.class.getName(), manifestationId);

		}

		// Supprime la galerie
		Manifestation manifestation = manifestationPersistence
			.remove(manifestationId);

		// Supprime l'index
		this.reindex(manifestation, true);

		// Supprime ce qui a rapport au workflow
		WorkflowInstanceLinkLocalServiceUtil.deleteWorkflowInstanceLinks(
			manifestation.getCompanyId(), manifestation.getGroupId(),
			Event.class.getName(), manifestation.getManifestationId());

		// S'il existe une version live de la galerie, on la supprime
		Manifestation liveManifestation = manifestation.getLiveVersion();
		if (liveManifestation != null) {
			this.removeManifestation(liveManifestation.getManifestationId());
		}

		return manifestation;
	}

	/**
	 * Reindex la galerie d'éditions dans le moteur de recherche
	 */
	private void reindex(Manifestation manifestation, boolean delete)
		throws SearchException {
		Indexer<Manifestation> indexer = IndexerRegistryUtil
			.nullSafeGetIndexer(Manifestation.class);
		if (delete) {
			indexer.delete(manifestation);
		} else {
			indexer.reindex(manifestation);
		}
	}

	@Override
	public List<AssetVocabulary> getAttachedVocabularies(long groupId) {
		List<AssetVocabulary> vocabularies = AssetVocabularyLocalServiceUtil
			.getAssetVocabularies(-1, -1);
		List<AssetVocabulary> attachedVocabularies = new ArrayList<AssetVocabulary>();
		long classNameId = ClassNameLocalServiceUtil
			.getClassNameId(Manifestation.class);
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
	public List<Manifestation> getByGroupId(long groupId) {
		return this.manifestationPersistence.findByGroupId(groupId);
	}

	@Override
	public List<Manifestation> findByKeyword(String keyword, long groupId,
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

		return eventPersistence.findWithDynamicQuery(dynamicQuery, start, end);
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

		return eventPersistence.countWithDynamicQuery(dynamicQuery);
	}

	@Override
	public Hits search(SearchContext searchContext) throws SearchException {
		Indexer<Manifestation> indexer = IndexerRegistryUtil
			.nullSafeGetIndexer(Manifestation.class);
		return indexer.search(searchContext);
	}

	private final Log _log = LogFactoryUtil.getLog("strasbourg");
}