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
import eu.strasbourg.service.activity.model.AssociationActivity;
import eu.strasbourg.service.activity.service.base.AssociationActivityLocalServiceBaseImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.LongStream;

/**
 * The implementation of the association activity local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link eu.strasbourg.service.activity.service.AssociationActivityLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AssociationActivityLocalServiceBaseImpl
 * @see eu.strasbourg.service.activity.service.AssociationActivityLocalServiceUtil
 */
public class AssociationActivityLocalServiceImpl
	extends AssociationActivityLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link eu.strasbourg.service.activity.service.AssociationActivityLocalServiceUtil} to access the association activity local service.
	 */

	/**
	 * Crée une activité vide avec une PK, non ajouté à la base de donnée
	 */
	@Override
	public AssociationActivity createAssociationActivity(ServiceContext sc)
			throws PortalException {
		User user = UserLocalServiceUtil.getUser(sc.getUserId());

		long pk = counterLocalService.increment();

		AssociationActivity associationActivity = this.associationActivityLocalService
				.createAssociationActivity(pk);

		associationActivity.setGroupId(sc.getScopeGroupId());
		associationActivity.setUserName(user.getFullName());
		associationActivity.setUserId(sc.getUserId());

		return associationActivity;
	}

	/**
	 * Met à jour une activité et l'enregistre en base de données
	 */
	@Override
	public AssociationActivity updateAssociationActivity(
			AssociationActivity associationActivity, ServiceContext sc)
			throws PortalException {
		User user = UserLocalServiceUtil.getUser(sc.getUserId());

		associationActivity.setStatusByUserId(sc.getUserId());
		associationActivity.setStatusByUserName(user.getFullName());
		associationActivity.setStatusDate(sc.getModifiedDate());

		// Gestion du statut
		if (sc.getWorkflowAction() == WorkflowConstants.ACTION_PUBLISH) {
			associationActivity.setStatus(WorkflowConstants.STATUS_APPROVED);
		} else {
			associationActivity.setStatus(WorkflowConstants.STATUS_DRAFT);
			// Si le statut est "DRAFT" et qu'il y a une version live, on
			// supprime cette dernière
			AssociationActivity liveAssociationActivity = associationActivity
					.getLiveVersion();
			if (liveAssociationActivity != null) {
				this.removeAssociationActivity(
						liveAssociationActivity.getAssociationActivityId());
			}
		}
		associationActivity = this.associationActivityLocalService
				.updateAssociationActivity(associationActivity);
		this.updateAssetEntry(associationActivity, sc);
		this.reindex(associationActivity, false);

		return associationActivity;
	}

	/**
	 * Met à jour l'AssetEntry rattachée à l'entité
	 */
	private void updateAssetEntry(AssociationActivity associationActivity,
								  ServiceContext sc) throws PortalException {
		this.assetEntryLocalService.updateEntry(sc.getUserId(), // User ID
				sc.getScopeGroupId(), // Group ID
				associationActivity.getCreateDate(), // Date of creation
				associationActivity.getModifiedDate(), // Date of modification
				AssociationActivity.class.getName(), // Class name
				associationActivity.getPrimaryKey(), // Class PK
				associationActivity.getUuid(), // UUID
				0, // Class type ID
				sc.getAssetCategoryIds(), // Categories IDs
				sc.getAssetTagNames(), // Tags IDs
				true, // Listable
				true, // Visible
				associationActivity.getCreateDate(), // Start date
				null, // End date
				associationActivity.getCreateDate(), // Publication date
				null, // Date of expiration
				ContentTypes.TEXT_HTML, // Content type
				"Activité de " + associationActivity.getAssociation().getNameCurrentValue(), // Title
				"Activité de " + associationActivity.getAssociation().getNameCurrentValue(), // Description
				"Activité de " + associationActivity.getAssociation().getNameCurrentValue(), // Summary
				null, // URL
				null, // Layout uuid
				0, // Width
				0, // Height
				null); // Priority

		// Réindexe l'édition
		this.reindex(associationActivity, false);
	}

	/**
	 * Met à jour le statut de l'actvité de l'association par le framework workflow
	 */
	@Override
	public AssociationActivity updateStatus(long userId, long entryId, int status)
			throws PortalException {
		AssociationActivity associationActivity = this
				.getAssociationActivity(entryId);

		associationActivity.setStatus(status);
		User user = UserLocalServiceUtil.fetchUser(userId);
		if (user != null) {
			associationActivity.setStatusByUserId(user.getUserId());
			associationActivity.setStatusByUserName(user.getFullName());
		}
		associationActivity.setStatusDate(new Date());

		associationActivity = this.associationActivityLocalService
				.updateAssociationActivity(associationActivity);

		AssetEntry entry = associationActivity.getAssetEntry();
		entry.setVisible(associationActivity.isApproved());
		if (entry.isVisible()) {
			entry.setPublishDate(new Date());
		}
		this.assetEntryLocalService.updateAssetEntry(entry);

		this.reindex(associationActivity, false);

		// Si le nouveau statut est "DRAFT" et qu'il y a une version live, on
		// supprime cette dernière
		AssociationActivity liveAssociationActivity = associationActivity
				.getLiveVersion();
		if (status == WorkflowConstants.STATUS_DRAFT
				&& liveAssociationActivity != null) {
			this.removeAssociationActivity(liveAssociationActivity.getPrimaryKey());
		}

		return associationActivity;
	}

	/**
	 * Supprime une entité
	 */
	@Override
	public AssociationActivity removeAssociationActivity(
			long associationActivityId) throws PortalException {
		AssetEntry entry = AssetEntryLocalServiceUtil.fetchEntry(
				AssociationActivity.class.getName(), associationActivityId);

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
					AssociationActivity.class.getName(), associationActivityId);
		}

		// Supprime l'entité
		AssociationActivity associationActivity = this.associationActivityPersistence
				.remove(associationActivityId);

		// Supprime l'index
		this.reindex(associationActivity, true);

		return associationActivity;
	}

	/**
	 * Reindex l'entité dans le moteur de recherche
	 */
	private void reindex(AssociationActivity associationActivity,
						 boolean delete) throws SearchException {
		Indexer<AssociationActivity> indexer = IndexerRegistryUtil
				.nullSafeGetIndexer(AssociationActivity.class);
		if (delete) {
			indexer.delete(associationActivity);
		} else {
			indexer.reindex(associationActivity);
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
				.getClassNameId(AssociationActivity.class);
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
	public List<AssociationActivity> getByGroupId(long groupId) {
		return this.associationActivityPersistence.findByGroupId(groupId);
	}


	/**
	 * Lance une recherche selon le searchContext
	 */
	@Override
	public Hits search(SearchContext searchContext) throws SearchException {
		Indexer<AssociationActivity> indexer = IndexerRegistryUtil
				.nullSafeGetIndexer(AssociationActivity.class);
		return indexer.search(searchContext);
	}

	/**
	 * Lance une recherche par mots-clés
	 */
	@Override
	public List<AssociationActivity> findByKeyword(String keyword, long groupId,
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

		return associationActivityPersistence.findWithDynamicQuery(dynamicQuery,
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

		return associationActivityPersistence.countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Retourne les activités d'une association
	 */
	@Override
	public List<AssociationActivity> getByAssociation(
			long associationId) {
		return this.associationActivityPersistence
				.findByAssociation(associationId);
	}
}