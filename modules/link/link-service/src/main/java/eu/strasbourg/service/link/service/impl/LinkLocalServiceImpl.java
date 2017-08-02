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

package eu.strasbourg.service.link.service.impl;

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
import eu.strasbourg.service.link.model.Link;
import eu.strasbourg.service.link.service.base.LinkLocalServiceBaseImpl;

/**
 * The implementation of the link local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link eu.strasbourg.service.link.service.LinkLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 *
 * @author BenjaminBini
 * @see LinkLocalServiceBaseImpl
 * @see eu.strasbourg.service.link.service.LinkLocalServiceUtil
 */
@ProviderType
public class LinkLocalServiceImpl extends LinkLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link
	 * eu.strasbourg.service.link.service.LinkLocalServiceUtil} to access the
	 * link local service.
	 */

	/**
	 * Crée un lien vide avec une PK, non ajouté à la base de donnée
	 */
	@Override
	public Link createLink(ServiceContext sc) throws PortalException {
		User user = UserLocalServiceUtil.getUser(sc.getUserId());

		long pk = counterLocalService.increment();

		Link link = this.linkLocalService.createLink(pk);

		link.setGroupId(sc.getScopeGroupId());
		link.setUserName(user.getFullName());
		link.setUserId(sc.getUserId());

		link.setStatus(WorkflowConstants.STATUS_DRAFT);

		return link;
	}

	/**
	 * Met à jour un lien et l'enregistre en base de données
	 */
	@Override
	public Link updateLink(Link link, ServiceContext sc)
		throws PortalException {
		User user = UserLocalServiceUtil.getUser(sc.getUserId());

		link.setStatusByUserId(sc.getUserId());
		link.setStatusByUserName(user.getFullName());
		link.setStatusDate(sc.getModifiedDate());

		// Si on n'utilise pas le framework workflow, simple gestion
		// brouillon/publié
		if (!WorkflowDefinitionLinkLocalServiceUtil.hasWorkflowDefinitionLink(
			sc.getCompanyId(), sc.getScopeGroupId(), Link.class.getName())) {
			if (sc.getWorkflowAction() == WorkflowConstants.ACTION_PUBLISH) {
				link.setStatus(WorkflowConstants.STATUS_APPROVED);
			} else {
				link.setStatus(WorkflowConstants.STATUS_DRAFT);
				// Si le statut est "DRAFT" et qu'il y a une version live, on
				// supprime cette dernière
				Link liveLink = link.getLiveVersion();
				if (liveLink != null) {
					this.removeLink(liveLink.getLinkId());
				}
			}
			link = this.linkLocalService.updateLink(link);
			this.updateAssetEntry(link, sc);
			this.reindex(link, false);
		} else { // Si le framework worflow est actif, c'est celui-ci qui gère
				 // l'enregistrement
			link = this.linkLocalService.updateLink(link);
			WorkflowHandlerRegistryUtil.startWorkflowInstance(
				link.getCompanyId(), link.getGroupId(), link.getUserId(),
				Link.class.getName(), link.getPrimaryKey(), link, sc);
		}

		return link;
	}

	/**
	 * Met à jour l'AssetEntry (à n'utiliser que quand on vient du formulaire
	 * d'édition)
	 */
	private void updateAssetEntry(Link link, ServiceContext sc)
		throws PortalException {
		this.assetEntryLocalService.updateEntry(sc.getUserId(), // User ID
			sc.getScopeGroupId(), // Group ID
			link.getCreateDate(), // Date of creation
			link.getModifiedDate(), // Date of modification
			Link.class.getName(), // Class name
			link.getPrimaryKey(), // Class PK
			link.getUuid(), // UUID
			0, // Class type ID
			sc.getAssetCategoryIds(), // Categories IDs
			sc.getAssetTagNames(), // Tags IDs
			true, // Listable
			link.getStatus() == WorkflowConstants.STATUS_APPROVED, // Visible
			link.getCreateDate(), // Start date
			null, // End date
			link.getCreateDate(), // Publication date
			null, // Date of expiration
			ContentTypes.TEXT_HTML, // Content type
			link.getTitle(), // Title
			link.getTitle(), // Description
			link.getHoverText(), // Summary
			null, // URL
			null, // Layout uuid
			0, // Width
			0, // Height
			null); // Priority
	}

	/**
	 * Met à jour le statut du lien par le framework workflow
	 */
	@Override
	public Link updateStatus(long userId, long entryId, int status,
		ServiceContext sc, Map<String, Serializable> workflowContext)
		throws PortalException {

		Link link = this.getLink(entryId);
		link.setStatus(status);
		User user = UserLocalServiceUtil.fetchUser(userId);
		if (user != null) {
			link.setStatusByUserId(user.getUserId());
			link.setStatusByUserName(user.getFullName());
		}
		link.setStatusDate(new Date());

		link = this.linkLocalService.updateLink(link);

		AssetEntry entry = this.assetEntryLocalService
			.getEntry(Link.class.getName(), link.getPrimaryKey());
		entry.setVisible(status == WorkflowConstants.STATUS_APPROVED);
		this.assetEntryLocalService.updateAssetEntry(entry);

		this.reindex(link, false);

		// Si le nouveau statut est "DRAFT" et qu'il y a une version live, on
		// supprime cette dernière
		Link liveLink = link.getLiveVersion();
		if (status == WorkflowConstants.STATUS_DRAFT && liveLink != null) {
			this.removeLink(liveLink.getLinkId());
		}

		return link;
	}

	/**
	 * Met à jour le statut du lien "manuellement" (pas via le workflow)
	 */
	@Override
	public void updateStatus(Link link, int status) throws PortalException {
		this.updateStatus(link.getUserId(), link.getLinkId(), status, null,
			null);
	}

	/**
	 * Supprime un lien
	 */
	@Override
	public Link removeLink(long linkId) throws PortalException {
		AssetEntry entry = this.assetEntryLocalService
			.fetchEntry(Link.class.getName(), linkId);

		if (entry != null) {
			// Supprime lien avec les catégories
			for (long categoryId : entry.getCategoryIds()) {
				this.assetEntryLocalService.deleteAssetCategoryAssetEntry(
					categoryId, entry.getEntryId());
			}

			// Supprime lien avec les tags
			long[] tagsIds = this.assetEntryLocalService
				.getAssetTagPrimaryKeys(entry.getEntryId());
			for (long tagId : tagsIds) {
				this.assetEntryLocalService.deleteAssetTagAssetEntry(tagId,
					entry.getEntryId());
			}

			// Supprime lien avec les autres entries
			List<AssetLink> links = this.assetLinkLocalService
				.getLinks(entry.getEntryId());
			for (AssetLink link : links) {
				this.assetLinkLocalService.deleteAssetLink(link);
			}

			// Supprime l'AssetEntry
			this.assetEntryLocalService.deleteEntry(entry);

		}

		// Supprime le lien
		Link link = this.linkPersistence.remove(linkId);

		// Supprime l'index
		reindex(link, true);

		// Supprime ce qui a rapport au workflow
		WorkflowInstanceLinkLocalServiceUtil.deleteWorkflowInstanceLinks(
			link.getCompanyId(), link.getGroupId(), Link.class.getName(),
			link.getLinkId());

		return link;
	}

	/**
	 * Retourne tous les liens d'un groupe
	 */
	@Override
	public List<Link> getByGroupId(long groupId) {
		return this.linkPersistence.findByGroupId(groupId);
	}

	/**
	 * Réindex le lien dans le moteur de recherche
	 */
	private void reindex(Link link, boolean delete) throws SearchException {
		Indexer<Link> indexer = IndexerRegistryUtil
			.nullSafeGetIndexer(Link.class);
		if (delete) {
			indexer.delete(link);
		} else {
			indexer.reindex(link);
		}
		indexer.reindex(link);
	}

	/**
	 * Retourne les vocabulaires rattachés à l'entité Link
	 */
	@Override
	public List<AssetVocabulary> getAttachedVocabularies(long groupId) {
		List<AssetVocabulary> vocabularies = AssetVocabularyLocalServiceUtil
			.getAssetVocabularies(-1, -1);
		List<AssetVocabulary> attachedVocabularies = new ArrayList<AssetVocabulary>();
		long classNameId = ClassNameLocalServiceUtil.getClassNameId(Link.class);
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
	 * Recherche par mot clés
	 */
	@Override
	public List<Link> findByKeyword(String keyword, long groupId, int start,
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

		return linkPersistence.findWithDynamicQuery(dynamicQuery, start, end);
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

		return linkPersistence.countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Recherche
	 */
	@Override
	public Hits search(SearchContext searchContext) throws SearchException {
		Indexer<Link> indexer = IndexerRegistryUtil
			.nullSafeGetIndexer(Link.class);
		return indexer.search(searchContext);
	}

}