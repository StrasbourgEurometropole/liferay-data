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
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
import com.liferay.asset.kernel.service.AssetVocabularyLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.BooleanClauseOccur;
import com.liferay.portal.kernel.search.BooleanQuery;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.IndexSearcherHelperUtil;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.search.ParseException;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.search.WildcardQuery;
import com.liferay.portal.kernel.search.generic.BooleanQueryImpl;
import com.liferay.portal.kernel.search.generic.MatchQuery;
import com.liferay.portal.kernel.search.generic.WildcardQueryImpl;
import com.liferay.portal.kernel.service.ClassNameLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import aQute.bnd.annotation.ProviderType;
import eu.strasbourg.service.activity.model.Activity;
import eu.strasbourg.service.activity.model.ActivityCourse;
import eu.strasbourg.service.activity.model.ActivityCoursePlace;
import eu.strasbourg.service.activity.model.ActivityCourseSchedule;
import eu.strasbourg.service.activity.model.PlaceAgenda;
import eu.strasbourg.service.activity.service.base.ActivityLocalServiceBaseImpl;

/**
 * The implementation of the activity local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link eu.strasbourg.service.activity.service.ActivityLocalService}
 * interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ActivityLocalServiceBaseImpl
 * @see eu.strasbourg.service.activity.service.ActivityLocalServiceUtil
 */
@ProviderType
public class ActivityLocalServiceImpl extends ActivityLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link
	 * eu.strasbourg.service.activity.service.ActivityLocalServiceUtil} to
	 * access the activity local service.
	 */

	private Log log = LogFactoryUtil.getLog(this.getClass());

	/**
	 * Crée une activité vide avec une PK, non ajouté à la base de donnée
	 */
	@Override
	public Activity createActivity(ServiceContext sc) throws PortalException {
		User user = UserLocalServiceUtil.getUser(sc.getUserId());

		long pk = counterLocalService.increment();

		Activity activity = this.activityLocalService.createActivity(pk);

		activity.setGroupId(sc.getScopeGroupId());
		activity.setUserName(user.getFullName());
		activity.setUserId(sc.getUserId());
		activity.setImageId(0);

		activity.setStatus(WorkflowConstants.STATUS_DRAFT);

		return activity;
	}

	/**
	 * Met à jour une activité et l'enregistre en base de données
	 */
	@Override
	public Activity updateActivity(Activity activity, ServiceContext sc) throws PortalException {
		User user = UserLocalServiceUtil.getUser(sc.getUserId());

		activity.setStatusByUserId(sc.getUserId());
		activity.setStatusByUserName(user.getFullName());
		activity.setStatusDate(sc.getModifiedDate());

		// Gestion du statut
		if (sc.getWorkflowAction() == WorkflowConstants.ACTION_PUBLISH) {
			activity.setStatus(WorkflowConstants.STATUS_APPROVED);
		} else {
			activity.setStatus(WorkflowConstants.STATUS_DRAFT);
			// Si le statut est "DRAFT" et qu'il y a une version live, on
			// supprime cette dernière
			Activity liveActivity = activity.getLiveVersion();
			if (liveActivity != null) {
				this.removeActivity(liveActivity.getActivityId());
			}
		}
		activity = this.activityLocalService.updateActivity(activity);
		this.updateAssetEntry(activity, sc);
		this.reindex(activity, false);

		return activity;
	}

	/**
	 * Met à jour l'AssetEntry rattachée à l'entité
	 */
	private void updateAssetEntry(Activity activity, ServiceContext sc) throws PortalException {
		this.assetEntryLocalService.updateEntry(sc.getUserId(), // User ID
				sc.getScopeGroupId(), // Group ID
				activity.getCreateDate(), // Date of creation
				activity.getModifiedDate(), // Date of modification
				Activity.class.getName(), // Class name
				activity.getPrimaryKey(), // Class PK
				activity.getUuid(), // UUID
				0, // Class type ID
				sc.getAssetCategoryIds(), // Categories IDs
				sc.getAssetTagNames(), // Tags IDs
				true, // Listable
				activity.isApproved(), // Visible
				activity.getCreateDate(), // Start date
				null, // End date
				activity.getCreateDate(), // Publication date
				null, // Date of expiration
				ContentTypes.TEXT_HTML, // Content type
				activity.getTitle(), // Title
				activity.getDescription(), // Description
				activity.getDescription(), // Summary
				null, // URL
				null, // Layout uuid
				0, // Width
				0, // Height
				null); // Priority

		// Réindexe l'édition
		this.reindex(activity, false);
	}

	/**
	 * Met à jour le statut de l'entité
	 */
	@Override
	public Activity updateStatus(long userId, long entryId, int status) throws PortalException {
		Activity activity = this.getActivity(entryId);

		activity.setStatus(status);
		User user = UserLocalServiceUtil.fetchUser(userId);
		if (user != null) {
			activity.setStatusByUserId(user.getUserId());
			activity.setStatusByUserName(user.getFullName());
		}
		activity.setStatusDate(new Date());

		activity = this.activityLocalService.updateActivity(activity);

		AssetEntry entry = activity.getAssetEntry();
		entry.setVisible(activity.isApproved());
		if (entry.isVisible()) {
			entry.setPublishDate(new Date());
		}
		this.assetEntryLocalService.updateAssetEntry(entry);

		this.reindex(activity, false);

		// Si le nouveau statut est "DRAFT" et qu'il y a une version live, on
		// supprime cette dernière
		Activity liveActivity = activity.getLiveVersion();
		if (status == WorkflowConstants.STATUS_DRAFT && liveActivity != null) {
			this.removeActivity(liveActivity.getPrimaryKey());
		}

		return activity;
	}

	/**
	 * Supprime une entité
	 */
	@Override
	public Activity removeActivity(long activityId) throws PortalException {
		AssetEntry entry = AssetEntryLocalServiceUtil.fetchEntry(Activity.class.getName(), activityId);

		if (entry != null) {
			// Supprime le lien avec les catégories
			for (long categoryId : entry.getCategoryIds()) {
				this.assetEntryLocalService.deleteAssetCategoryAssetEntry(categoryId, entry.getEntryId());
			}

			// Supprime le lien avec les tags
			long[] tagIds = AssetEntryLocalServiceUtil.getAssetTagPrimaryKeys(entry.getEntryId());
			for (int i = 0; i < tagIds.length; i++) {
				AssetEntryLocalServiceUtil.deleteAssetTagAssetEntry(tagIds[i], entry.getEntryId());
			}

			// Supprime l'assetEntry
			AssetEntryLocalServiceUtil.deleteEntry(Activity.class.getName(), activityId);

			// Supprime les cours
			List<ActivityCourse> activityCourses = this.activityCourseLocalService.getByActivity(activityId);
			for (ActivityCourse activityCourse : activityCourses) {
				this.activityCourseLocalService.removeActivityCourse(activityCourse.getActivityCourseId());
			}
		}

		// Supprimé l'entité
		Activity activity = this.activityPersistence.remove(activityId);

		// Supprime l'index
		this.reindex(activity, true);

		return activity;
	}

	/**
	 * Reindex l'entité dans le moteur de recherche
	 */
	private void reindex(Activity activity, boolean delete) throws SearchException {
		Indexer<Activity> indexer = IndexerRegistryUtil.nullSafeGetIndexer(Activity.class);
		if (delete) {
			indexer.delete(activity);
		} else {
			indexer.reindex(activity);
		}
	}

	/**
	 * Renvoie la liste des vocabulaires rattachés à l'entité
	 */
	@Override
	public List<AssetVocabulary> getAttachedVocabularies(long groupId) {
		List<AssetVocabulary> vocabularies = AssetVocabularyLocalServiceUtil.getAssetVocabularies(-1, -1);
		List<AssetVocabulary> attachedVocabularies = new ArrayList<AssetVocabulary>();
		long classNameId = ClassNameLocalServiceUtil.getClassNameId(Activity.class);
		for (AssetVocabulary vocabulary : vocabularies) {
			if (vocabulary.getGroupId() == groupId
					&& LongStream.of(vocabulary.getSelectedClassNameIds()).anyMatch(c -> c == classNameId)) {
				attachedVocabularies.add(vocabulary);
			}
		}
		return attachedVocabularies;
	}

	/**
	 * Retourne toutes les éditions d'un groupe
	 */
	@Override
	public List<Activity> getByGroupId(long groupId) {
		return this.activityPersistence.findByGroupId(groupId);
	}

	/**
	 * Lance une recherche selon le searchContext
	 */
	@Override
	public Hits search(SearchContext searchContext) throws SearchException {
		Indexer<Activity> indexer = IndexerRegistryUtil.nullSafeGetIndexer(Activity.class);
		return indexer.search(searchContext);
	}

	/**
	 * Lance une recherche par mots-clés
	 */
	@Override
	public List<Activity> findByKeyword(String keyword, long groupId, int start, int end) {
		DynamicQuery dynamicQuery = dynamicQuery();

		if (keyword.length() > 0) {
			dynamicQuery.add(RestrictionsFactoryUtil.like("title", "%" + keyword + "%"));
		}
		if (groupId > 0) {
			dynamicQuery.add(PropertyFactoryUtil.forName("groupId").eq(groupId));
		}
		dynamicQuery.add(PropertyFactoryUtil.forName("status").eq(WorkflowConstants.STATUS_APPROVED));

		return activityPersistence.findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Compte de la recherche par mots-clés
	 */
	@Override
	public long findByKeywordCount(String keyword, long groupId) {
		DynamicQuery dynamicQuery = dynamicQuery();
		if (keyword.length() > 0) {
			dynamicQuery.add(RestrictionsFactoryUtil.like("title", "%" + keyword + "%"));
		}
		if (groupId > 0) {
			dynamicQuery.add(PropertyFactoryUtil.forName("groupId").eq(groupId));
		}

		return activityPersistence.countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Lance une recherche par liste d'ids
	 */
	@Override
	public List<Activity> findByIds(List<Long> activityIds) {
		DynamicQuery dynamicQuery = dynamicQuery();

		if (activityIds.size() > 0) {
			dynamicQuery.add(RestrictionsFactoryUtil.in("activityId", activityIds));
		} else {
			return new ArrayList<Activity>();
		}

		return activityPersistence.findWithDynamicQuery(dynamicQuery, -1, -1);
	}

	/**
	 * Retourne les Hits correspondant aux paramètres pour le webservice des
	 * activités
	 */
	@Override
	public List<Activity> searchActivities(long groupId, String keywords, Locale locale) {
		try {
			SearchContext searchContext = new SearchContext();
			searchContext.setCompanyId(PortalUtil.getDefaultCompanyId());

			// Query
			BooleanQuery query = new BooleanQueryImpl();
			try {

				query.addRequiredTerm(Field.ENTRY_CLASS_NAME, Activity.class.getName(), false);
				query.addRequiredTerm(Field.STATUS, WorkflowConstants.STATUS_APPROVED);
				query.addRequiredTerm(Field.GROUP_ID, groupId);

				// Mots-clés
				if (Validator.isNotNull(keywords)) {
					BooleanQuery keywordQuery = new BooleanQueryImpl();
					MatchQuery titleQuery = new MatchQuery(Field.TITLE, keywords);
					titleQuery.setFuzziness(new Float(10));
					keywordQuery.add(titleQuery, BooleanClauseOccur.SHOULD);

					WildcardQuery titleWildcardQuery = new WildcardQueryImpl(Field.TITLE, "*" + keywords + "*");
					keywordQuery.add(titleWildcardQuery, BooleanClauseOccur.SHOULD);

					query.add(keywordQuery, BooleanClauseOccur.MUST);
				}
			} catch (ParseException e) {
				log.error(e);
				return new ArrayList<Activity>();
			}
			// Recherche
			Hits hits = IndexSearcherHelperUtil.search(searchContext, query);
			List<Activity> activities = new ArrayList<Activity>();
			for (Document document : hits.getDocs()) {
				Long activityId = GetterUtil.getLong(document.get(Field.ENTRY_CLASS_PK));
				Activity activity = this.fetchActivity(activityId);
				if (activity != null) {
					activities.add(activity);
				}
			}

			return activities;
		} catch (SearchException e) {
			log.error(e);
			return new ArrayList<Activity>();
		}
	}

	/**
	 * Retourne l'affichage de l'agenda des cours pour un lieu
	 * Transformation de :
	 * ActivityCourse - ActivityCoursePlace - ActivityCourseSchedule
	 *						   ^                        ^
	 * 			               |                        |
	 * 			  			 Place                   Period
	 * 
	 * En :
	 * Period -> Course -> Schedule
	 */
	@Override
	public PlaceAgenda getPlaceAgenda(String sigId, Locale locale) {
		PlaceAgenda placeAgenda = new PlaceAgenda();

		// Liste de tous les cours
		List<ActivityCourse> courses = this.activityCourseLocalService.getActivityCourses(-1, -1).stream()
				.filter(c -> c.isApproved()).collect(Collectors.toList());

		// Liste de tous les cours de lieu du lieu
		List<ActivityCoursePlace> coursePlaces = this.activityCoursePlaceLocalService.getBySigId(sigId);

		// Liste de tous les schedules du lieu
		List<ActivityCourseSchedule> courseSchedules = coursePlaces.stream()
				.map(ActivityCoursePlace::getActivityCourseSchedules).flatMap(List::stream)
				.collect(Collectors.toList());

		// Via ces schedules, on récupère les différentes périodes concernées par le lieu
		List<AssetCategory> activityPeriods = courseSchedules.stream().map(ActivityCourseSchedule::getPeriods)
				.flatMap(List::stream).filter(distinctByKey(p -> p.getCategoryId())).collect(Collectors.toList());

		// On ajoute ces périodes à l'agenda
		List<PlaceAgenda.Period> periods = activityPeriods.stream()
				.map(p -> new PlaceAgenda.Period(p.getCategoryId(), p.getTitle(locale))).collect(Collectors.toList());
		placeAgenda.setPeriods(periods);

		// On assigne à chaque période ses cours
		for (PlaceAgenda.Period period : periods) {
			// On récupère d'abord les schedules de ces périodes
			List<ActivityCourseSchedule> courseSchedulesForPeriod = courseSchedules.stream()
					.filter(s -> s.getPeriods().stream().anyMatch(p -> period.getPeriodId() == p.getCategoryId()))
					.collect(Collectors.toList());
			// On récupère ensuite les lieux de cours liés à ces schedules
			List<ActivityCoursePlace> coursePlacesForPeriod = coursePlaces.stream()
					.filter(p -> courseSchedulesForPeriod.stream()
							.anyMatch(s -> s.getActivityCoursePlaceId() == p.getActivityCoursePlaceId()))
					.collect(Collectors.toList());
			// Puis les cours liés à ces lieux de cours
			List<ActivityCourse> activityCoursesForPeriod = courses.stream()
					.filter(c -> coursePlacesForPeriod.stream()
							.anyMatch(p -> p.getActivityCourseId() == c.getActivityCourseId()))
					.filter(distinctByKey(c -> c.getActivityCourseId())).collect(Collectors.toList());
			// On les transforme en objet pour l'affichage
			List<PlaceAgenda.Course> coursesForPeriod = activityCoursesForPeriod.stream()
					.map(c -> new PlaceAgenda.Course(c.getActivityCourseId(), c.getName(locale)))
					.collect(Collectors.toList());

			// On assigne à ces cours leurs horaires
			for (ActivityCoursePlace coursePlace : coursePlacesForPeriod) {
				coursesForPeriod.stream()
						.forEach(c -> c.setSchedules(courseSchedulesForPeriod.stream()
								.filter(s -> s.getActivityCoursePlaceId() == coursePlace.getActivityCoursePlaceId())
								.collect(Collectors.toList())));
			}

			// Et on ajoute les cours à la période
			period.setCourses(coursesForPeriod);
		}
		return placeAgenda;
	}

	private static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
		Map<Object, Boolean> seen = new ConcurrentHashMap<>();
		return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
	}

}