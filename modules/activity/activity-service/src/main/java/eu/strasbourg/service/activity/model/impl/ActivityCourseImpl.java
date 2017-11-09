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

package eu.strasbourg.service.activity.model.impl;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.service.AssetCategoryLocalServiceUtil;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;

import aQute.bnd.annotation.ProviderType;
import eu.strasbourg.service.activity.model.Activity;
import eu.strasbourg.service.activity.model.ActivityCourse;
import eu.strasbourg.service.activity.model.ActivityCoursePlace;
import eu.strasbourg.service.activity.model.ActivityCourseSchedule;
import eu.strasbourg.service.activity.model.ActivityOrganizer;
import eu.strasbourg.service.activity.model.CourseAgenda;
import eu.strasbourg.service.activity.model.CourseAgenda.CoursePeriodAgenda;
import eu.strasbourg.service.activity.model.CourseAgenda.CoursePlaceAgenda;
import eu.strasbourg.service.activity.service.ActivityCourseLocalServiceUtil;
import eu.strasbourg.service.activity.service.ActivityCoursePlaceLocalServiceUtil;
import eu.strasbourg.service.activity.service.ActivityLocalServiceUtil;
import eu.strasbourg.service.activity.service.ActivityOrganizerLocalServiceUtil;
import eu.strasbourg.utils.AssetVocabularyHelper;
import eu.strasbourg.utils.constants.VocabularyNames;

/**
 * The extended model implementation for the ActivityCourse service. Represents
 * a row in the &quot;activity_ActivityCourse&quot; database table, with each
 * column mapped to a property of this class.
 *
 * <p>
 * Helper methods and all application logic should be put in this class.
 * Whenever methods are added, rerun ServiceBuilder to copy their definitions
 * into the {@link eu.strasbourg.service.activity.model.ActivityCourse}
 * interface.
 * </p>
 *
 * @author Brian Wing Shun Chan
 */
@ProviderType
public class ActivityCourseImpl extends ActivityCourseBaseImpl {
	private static final long serialVersionUID = 2578567090018184821L;

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. All methods that expect a activity
	 * course model instance should use the {@link
	 * eu.strasbourg.service.activity.model.ActivityCourse} interface instead.
	 */
	public ActivityCourseImpl() {
	}
	
	/**
	 * Retourne le nom de l'organisateur du cours : 
	 * soit via le service, soit l'organisateur d'activité
	 */
	@Override
	public String getOrganizerName(Locale locale) {
		if (this.getService() != null) {
			return this.getService().getTitle(locale);
		}
		if (this.getActivityOrganizer() != null) {
			return this.getActivityOrganizer().getName(locale);
		}
		return "";
	}

	/**
	 * Retourne l'organisateur du cours
	 */
	@Override
	public ActivityOrganizer getActivityOrganizer() {
		return ActivityOrganizerLocalServiceUtil.fetchActivityOrganizer(this.getOrganizerId());
	}

	/**
	 * Retourne le service du cours
	 */
	@Override
	public AssetCategory getService() {
		AssetCategory service = null;
		if (this.getServiceId() > 0) {
			service = AssetCategoryLocalServiceUtil.fetchAssetCategory(this.getServiceId());
		}
		return service;
	}

	/**
	 * Retourne l'activité du cours
	 */
	@Override
	public Activity getActivity() {
		return ActivityLocalServiceUtil.fetchActivity(this.getActivityId());
	}

	/**
	 * Retourne les horaires par lieu du cours
	 */
	@Override
	public List<ActivityCoursePlace> getActivityCoursePlaces() {
		return ActivityCoursePlaceLocalServiceUtil.getByActivityCourse(this.getActivityCourseId());
	}
	
	/**
	 * Retourne les noms des lieux du cours
	 */
	@Override
	public List<String> getPlaceNames(Locale locale) {
		List<ActivityCoursePlace> coursePlaces = this.getActivityCoursePlaces();
		return coursePlaces.stream().map(c -> c.getPlaceAlias(locale)).distinct().collect(Collectors.toList());
	}
	
	/**
	 * Retourne les ids SIG des lieux duc ours 
	 */
	@Override
	public List<String> getPlaceSIGIds(Locale locale) {
		List<ActivityCoursePlace> coursePlaces = this.getActivityCoursePlaces();
		return coursePlaces.stream().map(c -> c.getPlaceSIGId()).distinct().collect(Collectors.toList());
	}

	/**
	 * Retourne les publics dui cours
	 */
	@Override
	public List<AssetCategory> getPublics() {
		return AssetVocabularyHelper.getAssetEntryCategoriesByVocabulary(this.getAssetEntry(),
				VocabularyNames.ACTIVITY_COURSE_PUBLIC);
	}

	/**
	 * Retourne le texte à afficher pour les publics du cours
	 */
	@Override
	public String getPublicsLabel(Locale locale) {
		List<AssetCategory> publics = this.getPublics();
		String label = "";
		for (AssetCategory publicCategory : publics) {
			if (label.length() > 0) {
				label += ", ";
			}
			label += publicCategory.getTitle(locale);
		}
		return label;
	}

	/**
	 * Retourne les types du cours
	 */
	@Override
	public List<AssetCategory> getTypes() {
		return AssetVocabularyHelper.getAssetEntryCategoriesByVocabulary(this.getAssetEntry(),
				VocabularyNames.ACTIVITY_COURSE_TYPE);
	}

	/**
	 * Retourne le texte à afficher pour les types du cours
	 */
	@Override
	public String getTypesLabels(Locale locale) {
		List<AssetCategory> types = this.getTypes();
		String label = "";
		for (AssetCategory typeCategory : types) {
			if (label.length() > 0) {
				label += ", ";
			}
			label += typeCategory.getTitle(locale);
		}
		return label;
	}

	/**
	 * Retourne la version live de cette entité
	 */
	@Override
	public ActivityCourse getLiveVersion() {
		long groupId = this.getGroupId();
		Group group = GroupLocalServiceUtil.fetchGroup(groupId);
		if (group == null || !group.isStagingGroup()) {
			return null;
		}
		long liveGroupId = group.getLiveGroupId();
		ActivityCourse liveActivity = ActivityCourseLocalServiceUtil.fetchActivityCourseByUuidAndGroupId(this.getUuid(),
				liveGroupId);
		return liveActivity;
	}

	/**
	 * Retourne l'AssetEntry rattaché à cette entité
	 */
	@Override
	public AssetEntry getAssetEntry() {
		return AssetEntryLocalServiceUtil.fetchEntry(ActivityCourse.class.getName(), this.getPrimaryKey());
	}

	/**
	 * Renvoie la liste des AssetCategory rattachées à cette entité (via
	 * l'assetEntry)
	 */
	@Override
	public List<AssetCategory> getCategories() {
		return AssetVocabularyHelper.getAssetEntryCategories(this.getAssetEntry());
	}

	/**
	 * Retourne l'affichage de l'agenda d'un cours
	 * Transformation de :
	 * ActivityCourse -> ActivityCoursePlace -> ActivityCourseSchedule
	 *						   ^                        ^
	 * 			               |                        |
	 * 			  			 Place                   Period
	 * 
	 * En :
	 * Period -> Place -> Schedule
	 */
	@Override
	public CourseAgenda getCourseAgenda(long groupId, Locale locale) {
		CourseAgenda courseAgenda = new CourseAgenda();

		// Liste de tous les lieux dans lesquelles se déroulent le cours
		List<ActivityCoursePlace> coursePlaces = this.getActivityCoursePlaces();

		// Liste de tous les schedules du cours
		List<ActivityCourseSchedule> courseSchedules = coursePlaces.stream()
				.map(ActivityCoursePlace::getActivityCourseSchedules)
				.flatMap(List::stream)
				.collect(Collectors.toList());

		// On récupère les différentes périodes concernées par le cours
		List<AssetCategory> periods = courseSchedules.stream()
				.map(ActivityCourseSchedule::getPeriods)
				.flatMap(List::stream)
				.filter(distinctByKey(p -> p.getCategoryId()))
				.collect(Collectors.toList());

		// On crée les objets agenda pour ces périodes
		List<CoursePeriodAgenda> periodAgendas = periods.stream().map(p -> new CoursePeriodAgenda(p.getCategoryId(), p.getTitle(locale)))
				.collect(Collectors.toList());
		courseAgenda.setPeriods(periodAgendas);

		// On assigne à chaque période ses lieux
		for (CoursePeriodAgenda period : periodAgendas) {
			List<ActivityCourseSchedule> courseSchedulesForPeriod = courseSchedules.stream()
					.filter(s -> s.getPeriods().stream().anyMatch(p -> period.getPeriodId() == p.getCategoryId())).collect(Collectors.toList());
			Stream<ActivityCoursePlace> coursePlacesForPeriod = coursePlaces.stream()
					.filter(p -> courseSchedulesForPeriod.stream().anyMatch(s -> s.getActivityCoursePlaceId() == p.getActivityCoursePlaceId()));
			List<CoursePlaceAgenda> placeAgendasForPeriod = coursePlacesForPeriod
					.map(p -> new CoursePlaceAgenda(p.getActivityCoursePlaceId(), p.getPlaceSIGId(), p.getPlaceAlias(locale))).collect(Collectors.toList());
			
			// On assigne aux lieux les horaires
			placeAgendasForPeriod.stream()
				.forEach(p -> p.setSchedules(
						courseSchedulesForPeriod.stream()
							.filter(s -> s.getActivityCoursePlaceId() == p.getCoursePlaceId())
							.collect(Collectors.toList())));
			
			// On assigne les lieux aux périodes
			period.setPlaces(placeAgendasForPeriod);
		}
	
		return courseAgenda;
	}
	
	private static <T> Predicate<T> distinctByKey(Function<? super T,Object> keyExtractor) {
	    Map<Object,Boolean> seen = new ConcurrentHashMap<>();
	    return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
	}

}