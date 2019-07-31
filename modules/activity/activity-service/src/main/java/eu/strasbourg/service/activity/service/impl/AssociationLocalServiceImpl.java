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

package eu.strasbourg.service.activity.service.impl;

import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
import com.liferay.asset.kernel.service.AssetVocabularyLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.*;
import com.liferay.portal.kernel.service.ClassNameLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import eu.strasbourg.service.activity.model.Association;
import eu.strasbourg.service.activity.model.Practice;
import eu.strasbourg.service.activity.service.base.AssociationLocalServiceBaseImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.LongStream;

/**
 * The implementation of the association local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link eu.strasbourg.service.activity.service.AssociationLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AssociationLocalServiceBaseImpl
 * @see eu.strasbourg.service.activity.service.AssociationLocalServiceUtil
 */
public class AssociationLocalServiceImpl extends AssociationLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link eu.strasbourg.service.activity.service.AssociationLocalServiceUtil} to access the association local service.
	 */

	/**
	 * Crée une association vide avec une PK, non ajouté à la base de donnée
	 */
	@Override
	public Association createAssociation(ServiceContext sc)
			throws PortalException {
		User user = UserLocalServiceUtil.getUser(sc.getUserId());

		long pk = counterLocalService.increment();

		Association association = this.associationLocalService
				.createAssociation(pk);

		association.setGroupId(sc.getScopeGroupId());
		association.setUserName(user.getFullName());
		association.setUserId(sc.getUserId());

		association.setStatus(WorkflowConstants.STATUS_DRAFT);

		return association;
	}

	/**
	 * Met à jour une association et l'enregistre en base de données
	 */
	@Override
	public Association updateAssociation(Association association,
													 ServiceContext sc) throws PortalException {
		User user = UserLocalServiceUtil.getUser(sc.getUserId());

		association.setStatusByUserId(sc.getUserId());
		association.setStatusByUserName(user.getFullName());
		association.setStatusDate(sc.getModifiedDate());

		// Gestion du statut
		if (sc.getWorkflowAction() == WorkflowConstants.ACTION_PUBLISH) {
			association.setStatus(WorkflowConstants.STATUS_APPROVED);
		} else {
			association.setStatus(WorkflowConstants.STATUS_DRAFT);
			// Si le statut est "DRAFT" et qu'il y a une version live, on
			// supprime cette dernière
			Association liveAssociation = association
					.getLiveVersion();
			if (liveAssociation != null) {
				this.removeAssociation(
						liveAssociation.getAssociationId());
			}
		}
		association = this.associationLocalService
				.updateAssociation(association);
		this.updateAssetEntry(association, sc);
		this.reindex(association, false);

		return association;
	}

	/**
	 * Met à jour l'AssetEntry rattachée à l'entité
	 */
	private void updateAssetEntry(Association association,
								  ServiceContext sc) throws PortalException {
		this.assetEntryLocalService.updateEntry(sc.getUserId(), // User ID
				sc.getScopeGroupId(), // Group ID
				association.getCreateDate(), // Date of creation
				association.getModifiedDate(), // Date of modification
				Association.class.getName(), // Class name
				association.getPrimaryKey(), // Class PK
				association.getUuid(), // UUID
				0, // Class type ID
				sc.getAssetCategoryIds(), // Categories IDs
				sc.getAssetTagNames(), // Tags IDs
				true, // Listable
				association.isApproved(), // Visible
				association.getCreateDate(), // Start date
				null, // End date
				association.getCreateDate(), // Publication date
				null, // Date of expiration
				ContentTypes.TEXT_HTML, // Content type
				association.getName(), // Title
				association.getName(), // Description
				association.getName(), // Summary
				null, // URL
				null, // Layout uuid
				0, // Width
				0, // Height
				null); // Priority

		// Réindexe l'édition
		this.reindex(association, false);
	}

	/**
	 * Met à jour le statut de l'association par le framework workflow
	 */
	@Override
	public Association updateStatus(long userId, long entryId, int status)
			throws PortalException {
		Association association = this
				.getAssociation(entryId);

		association.setStatus(status);
		User user = UserLocalServiceUtil.fetchUser(userId);
		if (user != null) {
			association.setStatusByUserId(user.getUserId());
			association.setStatusByUserName(user.getFullName());
		}
		association.setStatusDate(new Date());

		association = this.associationLocalService
				.updateAssociation(association);

		AssetEntry entry = association.getAssetEntry();
		entry.setVisible(association.isApproved());
		if (entry.isVisible()) {
			entry.setPublishDate(new Date());
		}
		this.assetEntryLocalService.updateAssetEntry(entry);

		this.reindex(association, false);

		// Si le nouveau statut est "DRAFT" et qu'il y a une version live, on
		// supprime cette dernière
		Association liveAssociation = association
				.getLiveVersion();
		if (status == WorkflowConstants.STATUS_DRAFT
				&& liveAssociation != null) {
			this.removeAssociation(liveAssociation.getPrimaryKey());
		}

		return association;
	}

	/**
	 * Supprime une entité
	 */
	@Override
	public Association removeAssociation(long associationId)
			throws PortalException {
		AssetEntry entry = AssetEntryLocalServiceUtil
				.fetchEntry(Association.class.getName(), associationId);

		if (entry != null) {
			// Supprime le lien avec les catégories
			for (long categoryId : entry.getCategoryIds()) {
				this.assetEntryLocalService.deleteAssetCategoryAssetEntry(
						categoryId, entry.getEntryId());
			}

			// Supprime le lien avec les tags
			long[] tagIds = AssetEntryLocalServiceUtil
					.getAssetTagPrimaryKeys(entry.getEntryId());
			for (int i = 0; i < tagIds.length; i++) {
				AssetEntryLocalServiceUtil.deleteAssetTagAssetEntry(tagIds[i],
						entry.getEntryId());
			}

			// Supprime l'assetEntry
			AssetEntryLocalServiceUtil.deleteEntry(
					Association.class.getName(), associationId);

			// Supprime les cours
			List<Practice> practices = this.practiceLocalService.getByAssociation(associationId);
			for (Practice practice : practices) {
				this.practiceLocalService.removePractice(practice.getPracticeId());
			}

		}

		// Supprime l'entité
		Association association = this.associationPersistence
				.remove(associationId);

		// Supprime l'index
		this.reindex(association, true);

		return association;
	}

	/**
	 * Reindex l'entité dans le moteur de recherche
	 */
	private void reindex(Association association, boolean delete)
			throws SearchException {
		Indexer<Association> indexer = IndexerRegistryUtil
				.nullSafeGetIndexer(Association.class);
		if (delete) {
			indexer.delete(association);
		} else {
			indexer.reindex(association);
		}
	}

	/**
	 * Renvoie la liste des vocabulaires rattachés à l'entité
	 */
	@Override
	public List<AssetVocabulary> getAttachedVocabularies(long groupId) {
		List<AssetVocabulary> vocabularies = AssetVocabularyLocalServiceUtil
				.getAssetVocabularies(-1, -1);
		List<AssetVocabulary> attachedVocabularies = new ArrayList<AssetVocabulary>();
		long classNameId = ClassNameLocalServiceUtil
				.getClassNameId(Association.class);
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
	@Override
	public List<Association> getByGroupId(long groupId) {
		return this.associationPersistence.findByGroupId(groupId);
	}

	/**
	 * Lance une recherche selon le searchContext
	 */
	@Override
	public Hits search(SearchContext searchContext) throws SearchException {
		Indexer<Association> indexer = IndexerRegistryUtil
				.nullSafeGetIndexer(Association.class);
		return indexer.search(searchContext);
	}

	/**
	 * Lance une recherche par mots-clés
	 */
	@Override
	public List<Association> findByKeyword(String keyword, long groupId,
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
		dynamicQuery.add(PropertyFactoryUtil.forName("status")
				.eq(WorkflowConstants.STATUS_APPROVED));

		return associationPersistence.findWithDynamicQuery(dynamicQuery,
				start, end);
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

		return associationPersistence.countWithDynamicQuery(dynamicQuery);
	}
}