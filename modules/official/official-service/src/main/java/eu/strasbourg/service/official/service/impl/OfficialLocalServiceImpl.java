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

package eu.strasbourg.service.official.service.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
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
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import aQute.bnd.annotation.ProviderType;
import eu.strasbourg.service.official.model.Official;
import eu.strasbourg.service.official.service.base.OfficialLocalServiceBaseImpl;
import eu.strasbourg.utils.AssetVocabularyHelper;

/**
 * The implementation of the official local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link official.service.OfficialLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 *
 * @author AngeliqueZUNINO
 * @see OfficialLocalServiceBaseImpl
 * @see official.service.OfficialLocalServiceUtil
 */
@ProviderType
public class OfficialLocalServiceImpl extends OfficialLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link
	 * official.service.OfficialLocalServiceUtil} to access the official local
	 * service.
	 */

	/**
	 * Crée un un élu vide avec une PK, non ajouté à la base de donnée
	 */
	@Override
	public Official createOfficial(ServiceContext sc) throws PortalException {
		User user = UserLocalServiceUtil.getUser(sc.getUserId());

		long pk = counterLocalService.increment();

		Official official = this.officialLocalService.createOfficial(pk);

		official.setGroupId(sc.getScopeGroupId());
		official.setUserName(user.getFullName());
		official.setUserId(sc.getUserId());

		official.setStatus(WorkflowConstants.STATUS_DRAFT);

		return official;
	}

	/**
	 * Met à jour un élu et l'enregistre en base de données
	 */
	@Override
	public Official updateOfficial(Official official, ServiceContext sc)
			throws PortalException {
		User user = UserLocalServiceUtil.getUser(sc.getUserId());

		official.setStatusByUserId(sc.getUserId());
		official.setStatusByUserName(user.getFullName());
		official.setStatusDate(sc.getModifiedDate());

		if (sc.getWorkflowAction() == WorkflowConstants.ACTION_PUBLISH) {
			official.setStatus(WorkflowConstants.STATUS_APPROVED);
		} else {
			official.setStatus(WorkflowConstants.STATUS_DRAFT);
			// Si le statut est "DRAFT" et qu'il y a une version live, on
			// supprime cette dernière
			Official liveOfficial = official.getLiveVersion();
			if (liveOfficial != null) {
				this.removeOfficial(liveOfficial.getOfficialId());
			}
		}
		official = this.officialLocalService.updateOfficial(official);
		this.updateAssetEntry(official, sc);
		this.reindex(official, false);

		return official;
	}

	/**
	 * Met à jour l'AssetEntry rattachée à l'élu
	 */
	private void updateAssetEntry(Official official, ServiceContext sc)
			throws PortalException {
		assetEntryLocalService.updateEntry(sc.getUserId(), // User ID
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
				official.getLastName() + " " + official.getFirstName(), // Title
				official.getLastName() + " " + official.getFirstName(), // Description
				official.getLastName() + " " + official.getFirstName(), // Summary
				null, // URL
				null, // Layout uuid
				0, // Height
				0, // Width
				null); // Priority
		
		// Réindexe l'edition
		this.reindex(official, false);
	}

	/**
	 * Met à jour le statut de l'élu par le framework workflow
	 */
	@Override
	public Official updateStatus(long userId, long entryId, int status,
			ServiceContext sc, Map<String, Serializable> workflowContext)
			throws PortalException {
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

		// Si le nouveau statut est "DRAFT" et qu'il y a une version live, on
		// supprime cette dernière
		Official liveOfficial = official.getLiveVersion();
		if (status == WorkflowConstants.STATUS_DRAFT && liveOfficial != null) {
			this.removeOfficial(liveOfficial.getOfficialId());
		}

		return official;
	}

	/**
	 * Met à jour le statut de l'élu "manuellement" (pas via le workflow)
	 */
	@Override
	public void updateStatus(Official official, int status)
			throws PortalException {
		this.updateStatus(official.getUserId(), official.getOfficialId(), status,
				null, null);
	}

	/**
	 * Supprime un élu
	 */
	@Override
	public Official removeOfficial(long officialId) throws PortalException {
		AssetEntry entry = AssetEntryLocalServiceUtil
				.fetchEntry(Official.class.getName(), officialId);

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

//			TODO suppression de ce morceau de code ou ajout de la ligne
//			<reference entity="AssetLink" package-path="com.liferay.portlet.asset" />
//			dans service.xml ?
//			// Supprime lien avec les autres entries
//			List<AssetLink> links = this.assetLinkLocalService
//					.getLinks(entry.getEntryId());
//			for (AssetLink link : links) {
//				this.assetLinkLocalService.deleteAssetLink(link);
//			}

			// Delete the AssetEntry
			AssetEntryLocalServiceUtil.deleteEntry(Official.class.getName(),
					officialId);

		}

		// Delete the official
		Official official = this.officialPersistence.remove(officialId);

		// Delete the index
		this.reindex(official, true);

		return official;
	}

	/**
	 * Reindex l'edition dans le moteur de recherche
	 */
	private void reindex(Official official, boolean delete)
			throws SearchException {
		Indexer<Official> indexer = IndexerRegistryUtil
				.nullSafeGetIndexer(Official.class);
		if (delete) {
			indexer.delete(official);
		} else {
			indexer.reindex(official);
		}
	}

	/**
	 * Retourne les vocabulaires rattrachés à ce type d'entité pour un groupe
	 */
	@Override
	public List<AssetVocabulary> getAttachedVocabularies(long groupId) {
		long classNameId = ClassNameLocalServiceUtil
			.getClassNameId(Official.class);
		return AssetVocabularyHelper.getVocabulariesForAssetType(groupId,
			classNameId);
	}

	/**
	 * Retourne tous les élus d'un groupe
	 */
	@Override
	public List<Official> getByGroupId(long groupId) {
		return this.officialPersistence.findByGroupId(groupId);
	}

	/**
	 * Lance une recherche selon le searchContext
	 */
	@Override
	public Hits search(SearchContext searchContext) throws SearchException {
		Indexer<Official> indexer = IndexerRegistryUtil
			.nullSafeGetIndexer(Official.class);
		return indexer.search(searchContext);
	}

	/**
	 * Lance une recherche par mots-clés
	 */
	@Override
	public List<Official> findByKeyword(String keyword, long groupId, int start,
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

		return this.officialPersistence.findWithDynamicQuery(dynamicQuery, start,
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

		return this.officialPersistence.countWithDynamicQuery(dynamicQuery);
	}
}