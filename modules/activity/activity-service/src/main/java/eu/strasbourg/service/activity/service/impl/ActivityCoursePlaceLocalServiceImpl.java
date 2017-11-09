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
import java.util.List;
import java.util.stream.Collectors;
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

import aQute.bnd.annotation.ProviderType;
import eu.strasbourg.service.activity.model.ActivityCoursePlace;
import eu.strasbourg.service.activity.model.ActivityCourseSchedule;
import eu.strasbourg.service.activity.service.base.ActivityCoursePlaceLocalServiceBaseImpl;

/**
 * The implementation of the activityCoursePlace local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link eu.strasbourg.service.activityCoursePlace.service.ActivityCoursePlaceLocalService}
 * interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ActivityCoursePlaceLocalServiceBaseImpl
 * @see eu.strasbourg.service.activityCoursePlace.service.ActivityCoursePlaceLocalServiceUtil
 */
@ProviderType
public class ActivityCoursePlaceLocalServiceImpl
	extends ActivityCoursePlaceLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link
	 * eu.strasbourg.service.activityCoursePlace.service.
	 * ActivityCoursePlaceLocalServiceUtil} to access the activityCoursePlace
	 * local service.
	 */
	/**
	 * Retourne les lieux d'un cours
	 */
	@Override
	public List<ActivityCoursePlace> getByActivityCourse(
		long activityCourseId) {
		return this.activityCoursePlacePersistence
			.findByActivityCourse(activityCourseId);
	}

	/**
	 * Crée une activité vide avec une PK, non ajouté à la base de donnée
	 */
	@Override
	public ActivityCoursePlace createActivityCoursePlace(ServiceContext sc)
		throws PortalException {
		User user = UserLocalServiceUtil.getUser(sc.getUserId());

		long pk = counterLocalService.increment();

		ActivityCoursePlace activityCoursePlace = this.activityCoursePlaceLocalService
			.createActivityCoursePlace(pk);

		activityCoursePlace.setGroupId(sc.getScopeGroupId());
		activityCoursePlace.setUserName(user.getFullName());
		activityCoursePlace.setUserId(sc.getUserId());

		return activityCoursePlace;
	}

	/**
	 * Met à jour une activité et l'enregistre en base de données
	 */
	@Override
	public ActivityCoursePlace updateActivityCoursePlace(
		ActivityCoursePlace activityCoursePlace, ServiceContext sc)
		throws PortalException {
		activityCoursePlace = this.activityCoursePlaceLocalService
			.updateActivityCoursePlace(activityCoursePlace);
		this.updateAssetEntry(activityCoursePlace, sc);
		this.reindex(activityCoursePlace, false);

		return activityCoursePlace;
	}

	/**
	 * Met à jour l'AssetEntry rattachée à l'entité
	 */
	private void updateAssetEntry(ActivityCoursePlace activityCoursePlace,
		ServiceContext sc) throws PortalException {
		this.assetEntryLocalService.updateEntry(sc.getUserId(), // User ID
			sc.getScopeGroupId(), // Group ID
			activityCoursePlace.getCreateDate(), // Date of creation
			activityCoursePlace.getModifiedDate(), // Date of modification
			ActivityCoursePlace.class.getName(), // Class name
			activityCoursePlace.getPrimaryKey(), // Class PK
			activityCoursePlace.getUuid(), // UUID
			0, // Class type ID
			sc.getAssetCategoryIds(), // Categories IDs
			sc.getAssetTagNames(), // Tags IDs
			true, // Listable
			true, // Visible
			activityCoursePlace.getCreateDate(), // Start date
			null, // End date
			activityCoursePlace.getCreateDate(), // Publication date
			null, // Date of expiration
			ContentTypes.TEXT_HTML, // Content type
			"", // Title
			"", // Description
			"", // Summary
			null, // URL
			null, // Layout uuid
			0, // Width
			0, // Height
			null); // Priority

		// Réindexe l'édition
		this.reindex(activityCoursePlace, false);
	}

	/**
	 * Supprime une entité
	 */
	@Override
	public ActivityCoursePlace removeActivityCoursePlace(
		long activityCoursePlaceId) throws PortalException {
		AssetEntry entry = AssetEntryLocalServiceUtil.fetchEntry(
			ActivityCoursePlace.class.getName(), activityCoursePlaceId);

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
				ActivityCoursePlace.class.getName(), activityCoursePlaceId);

			// Supprime les horaires
			List<ActivityCourseSchedule> activityCourseSchedules = this.activityCourseScheduleLocalService
				.getByActivityCoursePlace(activityCoursePlaceId);
			for (ActivityCourseSchedule activityCourseSchedule : activityCourseSchedules) {
				this.activityCourseScheduleLocalService
					.removeActivityCourseSchedule(
						activityCourseSchedule.getPrimaryKey());
			}
		}

		// Supprime l'entité
		ActivityCoursePlace activityCoursePlace = this.activityCoursePlacePersistence
			.remove(activityCoursePlaceId);

		// Supprime l'index
		this.reindex(activityCoursePlace, true);

		return activityCoursePlace;
	}

	/**
	 * Reindex l'entité dans le moteur de recherche
	 */
	private void reindex(ActivityCoursePlace activityCoursePlace,
		boolean delete) throws SearchException {
		Indexer<ActivityCoursePlace> indexer = IndexerRegistryUtil
			.nullSafeGetIndexer(ActivityCoursePlace.class);
		if (delete) {
			indexer.delete(activityCoursePlace);
		} else {
			indexer.reindex(activityCoursePlace);
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
			.getClassNameId(ActivityCoursePlace.class);
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
	 * Retourne tous les lieux de cours d'un groupe
	 */
	@Override
	public List<ActivityCoursePlace> getByGroupId(long groupId) {
		return this.activityCoursePlacePersistence.findByGroupId(groupId);
	}

	/**
	 * Retourne toutes les lieux de cours d'un lieu
	 */
	@Override
	public List<ActivityCoursePlace> getBySigId(String sigId) {
		return this.activityCoursePlacePersistence.findBySigId(sigId);
	}

	/**
	 * Lance une recherche selon le searchContext
	 */
	@Override
	public Hits search(SearchContext searchContext) throws SearchException {
		Indexer<ActivityCoursePlace> indexer = IndexerRegistryUtil
			.nullSafeGetIndexer(ActivityCoursePlace.class);
		return indexer.search(searchContext);
	}

	/**
	 * Lance une recherche par mots-clés
	 */
	@Override
	public List<ActivityCoursePlace> findByKeyword(String keyword, long groupId,
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

		return activityCoursePlacePersistence.findWithDynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	 * Récupère la liste des lieux sans horaires
	 */
	@Override
	public List<ActivityCoursePlace> findWithNoSchedule(long groupId) {
		DynamicQuery dynamicQuery = dynamicQuery();

		if (groupId > 0) {
			dynamicQuery
				.add(PropertyFactoryUtil.forName("groupId").eq(groupId));
		}

		List<ActivityCourseSchedule> schedules = activityCourseScheduleLocalService
			.getActivityCourseSchedules(-1, -1);
		List<Long> placesWithSchedulesIds = schedules.stream()
			.map(c -> c.getActivityCoursePlaceId()).distinct()
			.collect(Collectors.toList());

		if (placesWithSchedulesIds.size() > 0) {
			dynamicQuery.add(RestrictionsFactoryUtil.not(RestrictionsFactoryUtil
				.in("activityCoursePlaceId", placesWithSchedulesIds)));
		}
		
		return activityCoursePlacePersistence.findWithDynamicQuery(dynamicQuery,
			-1, -1);
	}

	/**
	 * Lance une recherche par liste d'ids
	 */
	@Override
	public List<ActivityCoursePlace> findByIds(
		List<Long> activityCoursePlaceIds) {
		DynamicQuery dynamicQuery = dynamicQuery();

		if (activityCoursePlaceIds.size() > 0) {
			dynamicQuery.add(RestrictionsFactoryUtil.in("activityCoursePlaceId",
				activityCoursePlaceIds));
		} else {
			return new ArrayList<ActivityCoursePlace>();
		}

		return activityCoursePlacePersistence.findWithDynamicQuery(dynamicQuery,
			-1, -1);
	}

}