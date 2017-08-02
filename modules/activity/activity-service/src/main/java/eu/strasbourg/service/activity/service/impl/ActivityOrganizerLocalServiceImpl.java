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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.LongStream;

import com.liferay.asset.kernel.model.AssetEntry;
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
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import aQute.bnd.annotation.ProviderType;
import eu.strasbourg.service.activity.model.ActivityOrganizer;
import eu.strasbourg.service.activity.service.base.ActivityOrganizerLocalServiceBaseImpl;

/**
 * The implementation of the activityOrganizer local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link eu.strasbourg.service.activityOrganizer.service.ActivityOrganizerLocalService}
 * interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ActivityOrganizerLocalServiceBaseImpl
 * @see eu.strasbourg.service.activityOrganizer.service.ActivityOrganizerLocalServiceUtil
 */
@ProviderType
public class ActivityOrganizerLocalServiceImpl
	extends ActivityOrganizerLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link
	 * eu.strasbourg.service.activityOrganizer.service.
	 * ActivityOrganizerLocalServiceUtil} to access the activityOrganizer local
	 * service.
	 */

	/**
	 * Crée une activité vide avec une PK, non ajouté à la base de donnée
	 */
	@Override
	public ActivityOrganizer createActivityOrganizer(ServiceContext sc)
		throws PortalException {
		User user = UserLocalServiceUtil.getUser(sc.getUserId());

		long pk = counterLocalService.increment();

		ActivityOrganizer activityOrganizer = this.activityOrganizerLocalService
			.createActivityOrganizer(pk);

		activityOrganizer.setGroupId(sc.getScopeGroupId());
		activityOrganizer.setUserName(user.getFullName());
		activityOrganizer.setUserId(sc.getUserId());

		activityOrganizer.setStatus(WorkflowConstants.STATUS_DRAFT);

		return activityOrganizer;
	}

	/**
	 * Met à jour une activité et l'enregistre en base de données
	 */
	@Override
	public ActivityOrganizer updateActivityOrganizer(ActivityOrganizer activityOrganizer,
		ServiceContext sc) throws PortalException {
		User user = UserLocalServiceUtil.getUser(sc.getUserId());

		activityOrganizer.setStatusByUserId(sc.getUserId());
		activityOrganizer.setStatusByUserName(user.getFullName());
		activityOrganizer.setStatusDate(sc.getModifiedDate());

		// Gestion du statut
		if (sc.getWorkflowAction() == WorkflowConstants.ACTION_PUBLISH) {
			activityOrganizer.setStatus(WorkflowConstants.STATUS_APPROVED);
		} else {
			activityOrganizer.setStatus(WorkflowConstants.STATUS_DRAFT);
			// Si le statut est "DRAFT" et qu'il y a une version live, on
			// supprime cette dernière
			ActivityOrganizer liveActivityOrganizer = activityOrganizer
				.getLiveVersion();
			if (liveActivityOrganizer != null) {
				this.removeActivityOrganizer(
					liveActivityOrganizer.getActivityOrganizerId());
			}
		}
		activityOrganizer = this.activityOrganizerLocalService
			.updateActivityOrganizer(activityOrganizer);
		this.updateAssetEntry(activityOrganizer, sc);
		this.reindex(activityOrganizer, false);

		return activityOrganizer;
	}

	/**
	 * Met à jour l'AssetEntry rattachée à l'entité
	 */
	private void updateAssetEntry(ActivityOrganizer activityOrganizer,
		ServiceContext sc) throws PortalException {
		this.assetEntryLocalService.updateEntry(sc.getUserId(), // User ID
			sc.getScopeGroupId(), // Group ID
			activityOrganizer.getCreateDate(), // Date of creation
			activityOrganizer.getModifiedDate(), // Date of modification
			ActivityOrganizer.class.getName(), // Class name
			activityOrganizer.getPrimaryKey(), // Class PK
			activityOrganizer.getUuid(), // UUID
			0, // Class type ID
			sc.getAssetCategoryIds(), // Categories IDs
			sc.getAssetTagNames(), // Tags IDs
			true, // Listable
			activityOrganizer.isApproved(), // Visible
			activityOrganizer.getCreateDate(), // Start date
			null, // End date
			activityOrganizer.getCreateDate(), // Publication date
			null, // Date of expiration
			ContentTypes.TEXT_HTML, // Content type
			activityOrganizer.getName(), // Title
			activityOrganizer.getName(), // Description
			activityOrganizer.getName(), // Summary
			null, // URL
			null, // Layout uuid
			0, // Width
			0, // Height
			null); // Priority

		// Réindexe l'édition
		this.reindex(activityOrganizer, false);
	}

	/**
	 * Met à jour le statut de l'édition par le framework workflow
	 */
	@Override
	public ActivityOrganizer updateStatus(long userId, long entryId, int status)
		throws PortalException {
		ActivityOrganizer activityOrganizer = this
			.getActivityOrganizer(entryId);

		activityOrganizer.setStatus(status);
		User user = UserLocalServiceUtil.fetchUser(userId);
		if (user != null) {
			activityOrganizer.setStatusByUserId(user.getUserId());
			activityOrganizer.setStatusByUserName(user.getFullName());
		}
		activityOrganizer.setStatusDate(new Date());

		activityOrganizer = this.activityOrganizerLocalService
			.updateActivityOrganizer(activityOrganizer);

		AssetEntry entry = activityOrganizer.getAssetEntry();
		entry.setVisible(activityOrganizer.isApproved());
		if (entry.isVisible()) {
			entry.setPublishDate(new Date());
		}
		this.assetEntryLocalService.updateAssetEntry(entry);

		this.reindex(activityOrganizer, false);

		// Si le nouveau statut est "DRAFT" et qu'il y a une version live, on
		// supprime cette dernière
		ActivityOrganizer liveActivityOrganizer = activityOrganizer
			.getLiveVersion();
		if (status == WorkflowConstants.STATUS_DRAFT
			&& liveActivityOrganizer != null) {
			this.removeActivityOrganizer(liveActivityOrganizer.getPrimaryKey());
		}

		return activityOrganizer;
	}

	/**
	 * Supprime une entité
	 */
	@Override
	public ActivityOrganizer removeActivityOrganizer(long activityOrganizerId)
		throws PortalException {
		AssetEntry entry = AssetEntryLocalServiceUtil
			.fetchEntry(ActivityOrganizer.class.getName(), activityOrganizerId);

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
				ActivityOrganizer.class.getName(), activityOrganizerId);

		}

		// Supprime l'entité
		ActivityOrganizer activityOrganizer = this.activityOrganizerPersistence
			.remove(activityOrganizerId);

		// Supprime l'index
		this.reindex(activityOrganizer, true);

		return activityOrganizer;
	}

	/**
	 * Reindex l'entité dans le moteur de recherche
	 */
	private void reindex(ActivityOrganizer activityOrganizer, boolean delete)
		throws SearchException {
		Indexer<ActivityOrganizer> indexer = IndexerRegistryUtil
			.nullSafeGetIndexer(ActivityOrganizer.class);
		if (delete) {
			indexer.delete(activityOrganizer);
		} else {
			indexer.reindex(activityOrganizer);
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
			.getClassNameId(ActivityOrganizer.class);
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
	public List<ActivityOrganizer> getByGroupId(long groupId) {
		return this.activityOrganizerPersistence.findByGroupId(groupId);
	}

	/**
	 * Lance une recherche selon le searchContext
	 */
	@Override
	public Hits search(SearchContext searchContext) throws SearchException {
		Indexer<ActivityOrganizer> indexer = IndexerRegistryUtil
			.nullSafeGetIndexer(ActivityOrganizer.class);
		return indexer.search(searchContext);
	}

	/**
	 * Lance une recherche par mots-clés
	 */
	@Override
	public List<ActivityOrganizer> findByKeyword(String keyword, long groupId,
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

		return activityOrganizerPersistence.findWithDynamicQuery(dynamicQuery,
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

		return activityOrganizerPersistence.countWithDynamicQuery(dynamicQuery);
	}
}