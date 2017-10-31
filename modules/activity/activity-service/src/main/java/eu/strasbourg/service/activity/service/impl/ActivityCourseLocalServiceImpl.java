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
import eu.strasbourg.service.activity.model.ActivityCourse;
import eu.strasbourg.service.activity.model.ActivityCoursePlace;
import eu.strasbourg.service.activity.service.base.ActivityCourseLocalServiceBaseImpl;

/**
 * The implementation of the activityCourse local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link eu.strasbourg.service.activityCourse.service.ActivityCourseLocalService}
 * interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ActivityCourseLocalServiceBaseImpl
 * @see eu.strasbourg.service.activityCourse.service.ActivityCourseLocalServiceUtil
 */
@ProviderType
public class ActivityCourseLocalServiceImpl
	extends ActivityCourseLocalServiceBaseImpl {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link
	 * eu.strasbourg.service.activityCourse.service.
	 * ActivityCourseLocalServiceUtil} to access the activityCourse local
	 * service.
	 */
	/**
	 * Retourne les cours d'une activité
	 */
	@Override
	public List<ActivityCourse> getByActivity(long activityId) {
		return this.activityCoursePersistence.findByActivity(activityId);
	}

	/**
	 * Crée une activité vide avec une PK, non ajouté à la base de donnée
	 */
	@Override
	public ActivityCourse createActivityCourse(ServiceContext sc)
		throws PortalException {
		User user = UserLocalServiceUtil.getUser(sc.getUserId());

		long pk = counterLocalService.increment();

		ActivityCourse activityCourse = this.activityCourseLocalService
			.createActivityCourse(pk);

		activityCourse.setGroupId(sc.getScopeGroupId());
		activityCourse.setUserName(user.getFullName());
		activityCourse.setUserId(sc.getUserId());

		activityCourse.setStatus(WorkflowConstants.STATUS_DRAFT);

		return activityCourse;
	}

	/**
	 * Met à jour une activité et l'enregistre en base de données
	 */
	@Override
	public ActivityCourse updateActivityCourse(ActivityCourse activityCourse,
		ServiceContext sc) throws PortalException {
		User user = UserLocalServiceUtil.getUser(sc.getUserId());

		activityCourse.setStatusByUserId(sc.getUserId());
		activityCourse.setStatusByUserName(user.getFullName());
		activityCourse.setStatusDate(sc.getModifiedDate());

		// Gestion du statut
		if (sc.getWorkflowAction() == WorkflowConstants.ACTION_PUBLISH) {
			activityCourse.setStatus(WorkflowConstants.STATUS_APPROVED);
		} else {
			activityCourse.setStatus(WorkflowConstants.STATUS_DRAFT);
			// Si le statut est "DRAFT" et qu'il y a une version live, on
			// supprime cette dernière
			ActivityCourse liveActivityCourse = activityCourse.getLiveVersion();
			if (liveActivityCourse != null) {
				this.removeActivityCourse(
					liveActivityCourse.getActivityCourseId());
			}
		}
		activityCourse = this.activityCourseLocalService
			.updateActivityCourse(activityCourse);
		this.updateAssetEntry(activityCourse, sc);
		this.reindex(activityCourse, false);

		return activityCourse;
	}

	/**
	 * Met à jour l'AssetEntry rattachée à l'entité
	 */
	private void updateAssetEntry(ActivityCourse activityCourse,
		ServiceContext sc) throws PortalException {
		this.assetEntryLocalService.updateEntry(sc.getUserId(), // User ID
			sc.getScopeGroupId(), // Group ID
			activityCourse.getCreateDate(), // Date of creation
			activityCourse.getModifiedDate(), // Date of modification
			ActivityCourse.class.getName(), // Class name
			activityCourse.getPrimaryKey(), // Class PK
			activityCourse.getUuid(), // UUID
			0, // Class type ID
			sc.getAssetCategoryIds(), // Categories IDs
			sc.getAssetTagNames(), // Tags IDs
			true, // Listable
			activityCourse.isApproved(), // Visible
			activityCourse.getCreateDate(), // Start date
			null, // End date
			activityCourse.getCreateDate(), // Publication date
			null, // Date of expiration
			ContentTypes.TEXT_HTML, // Content type
			activityCourse.getName(), // Title
			activityCourse.getName(), // Description
			activityCourse.getName(), // Summary
			null, // URL
			null, // Layout uuid
			0, // Width
			0, // Height
			null); // Priority

		// Réindexe l'édition
		this.reindex(activityCourse, false);
	}

	/**
	 * Met à jour le statut de l'édition par le framework workflow
	 */
	@Override
	public ActivityCourse updateStatus(long userId, long entryId, int status)
		throws PortalException {
		ActivityCourse activityCourse = this.getActivityCourse(entryId);

		activityCourse.setStatus(status);
		User user = UserLocalServiceUtil.fetchUser(userId);
		if (user != null) {
			activityCourse.setStatusByUserId(user.getUserId());
			activityCourse.setStatusByUserName(user.getFullName());
		}
		activityCourse.setStatusDate(new Date());

		activityCourse = this.activityCourseLocalService
			.updateActivityCourse(activityCourse);

		AssetEntry entry = activityCourse.getAssetEntry();
		entry.setVisible(activityCourse.isApproved());
		if (entry.isVisible()) {
			entry.setPublishDate(new Date());
		}
		this.assetEntryLocalService.updateAssetEntry(entry);

		this.reindex(activityCourse, false);

		// Si le nouveau statut est "DRAFT" et qu'il y a une version live, on
		// supprime cette dernière
		ActivityCourse liveActivityCourse = activityCourse.getLiveVersion();
		if (status == WorkflowConstants.STATUS_DRAFT
			&& liveActivityCourse != null) {
			this.removeActivityCourse(liveActivityCourse.getPrimaryKey());
		}

		return activityCourse;
	}

	/**
	 * Supprime une entité
	 */
	@Override
	public ActivityCourse removeActivityCourse(long activityCourseId)
		throws PortalException {
		AssetEntry entry = AssetEntryLocalServiceUtil
			.fetchEntry(ActivityCourse.class.getName(), activityCourseId);

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
			AssetEntryLocalServiceUtil
				.deleteEntry(ActivityCourse.class.getName(), activityCourseId);

			// Supprime les lieux
			List<ActivityCoursePlace> activityCoursePlaces = this.activityCoursePlaceLocalService
				.getByActivityCourse(activityCourseId);
			for (ActivityCoursePlace activityCoursePlace : activityCoursePlaces) {
				this.activityCoursePlaceLocalService.removeActivityCoursePlace(
					activityCoursePlace.getActivityCoursePlaceId());
			}
		}

		// Supprime l'entité
		ActivityCourse activityCourse = this.activityCoursePersistence
			.remove(activityCourseId);

		// Supprime l'index
		this.reindex(activityCourse, true);

		return activityCourse;
	}

	/**
	 * Reindex l'entité dans le moteur de recherche
	 */
	private void reindex(ActivityCourse activityCourse, boolean delete)
		throws SearchException {
		Indexer<ActivityCourse> indexer = IndexerRegistryUtil
			.nullSafeGetIndexer(ActivityCourse.class);
		if (delete) {
			indexer.delete(activityCourse);
		} else {
			indexer.reindex(activityCourse);
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
			.getClassNameId(ActivityCourse.class);
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
	public List<ActivityCourse> getByGroupId(long groupId) {
		return this.activityCoursePersistence.findByGroupId(groupId);
	}

	/**
	 * Lance une recherche selon le searchContext
	 */
	@Override
	public Hits search(SearchContext searchContext) throws SearchException {
		Indexer<ActivityCourse> indexer = IndexerRegistryUtil
			.nullSafeGetIndexer(ActivityCourse.class);
		return indexer.search(searchContext);
	}

	/**
	 * Lance une recherche par mots-clés
	 */
	@Override
	public List<ActivityCourse> findByKeyword(String keyword, long groupId,
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

		return activityCoursePersistence.findWithDynamicQuery(dynamicQuery,
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

		return activityPersistence.countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Lance une recherche par liste d'ids
	 */
	@Override
	public List<ActivityCourse> findByIds(List<Long> activityCourseIds) {
		DynamicQuery dynamicQuery = dynamicQuery();

		if (activityCourseIds.size() > 0) {
			dynamicQuery.add(RestrictionsFactoryUtil.in("activityCourseId",
				activityCourseIds));
		} else {
			return new ArrayList<ActivityCourse>();
		}

		return activityCoursePersistence.findWithDynamicQuery(dynamicQuery, -1,
			-1);
	}
}