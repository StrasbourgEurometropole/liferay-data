package eu.strasbourg.portlet.activity.display.context;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.portlet.PortletRequest;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.service.AssetCategoryLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.module.configuration.ConfigurationException;
import com.liferay.portal.kernel.service.LayoutLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import eu.strasbourg.portlet.activity.configuration.SearchActivityConfiguration;
import eu.strasbourg.service.activity.model.Activity;
import eu.strasbourg.service.activity.model.ActivityCourse;
import eu.strasbourg.service.activity.model.ActivityCoursePlace;
import eu.strasbourg.service.activity.model.ActivityCourseSchedule;
import eu.strasbourg.service.activity.service.ActivityCourseLocalServiceUtil;
import eu.strasbourg.service.activity.service.ActivityCoursePlaceLocalServiceUtil;
import eu.strasbourg.service.activity.service.ActivityCourseScheduleLocalServiceUtil;
import eu.strasbourg.service.activity.service.ActivityLocalServiceUtil;
import eu.strasbourg.service.place.model.Place;
import eu.strasbourg.service.place.service.PlaceLocalServiceUtil;
import eu.strasbourg.utils.AssetVocabularyHelper;

public class SearchActivityDisplayContext {

	private PortletRequest request;
	private ThemeDisplay themeDisplay;
	private Map<Activity, List<ActivityCourse>> results;

	private Log log = LogFactoryUtil.getLog(this.getClass());

	public SearchActivityDisplayContext(PortletRequest request) {
		this.request = request;
		this.themeDisplay = (ThemeDisplay) request
			.getAttribute(WebKeys.THEME_DISPLAY);
	}

	public Map<String, Object> getTemplateContextObjects(Activity activity) {

		// Friendly URL de la page de détail
		SearchActivityConfiguration configuration = null;
		try {
			configuration = themeDisplay.getPortletDisplay()
				.getPortletInstanceConfiguration(
					SearchActivityConfiguration.class);
		} catch (ConfigurationException e) {
			log.error(e);
		}
		String detailPageUuid = configuration.detailPageUuid();
		Layout layout = LayoutLocalServiceUtil.fetchLayoutByUuidAndGroupId(
			detailPageUuid, themeDisplay.getScopeGroupId(), false);
		Map<String, Object> contextObjects = new HashMap<String, Object>();
		if (layout != null) {
			String detailPageFriendlyURL = layout
				.getFriendlyURL(themeDisplay.getLocale());
			contextObjects.put("detailPageFriendlyURL", detailPageFriendlyURL);

		}
		contextObjects.put("entry", activity);
		contextObjects.put("courses", results.get(activity));
		return contextObjects;
	}

	public Map<Activity, List<ActivityCourse>> getResults() {
		if (this.results == null) {
			Map<Activity, List<ActivityCourse>> results = new HashMap<Activity, List<ActivityCourse>>();

			/**
			 * Paramètres de la recherche
			 */
			// Paramètres du schedule
			boolean monday = ParamUtil.getBoolean(request, "monday");
			boolean tuesday = ParamUtil.getBoolean(request, "tuesday");
			boolean wednesday = ParamUtil.getBoolean(request, "wednesday");
			boolean thursday = ParamUtil.getBoolean(request, "thursday");
			boolean friday = ParamUtil.getBoolean(request, "friday");
			boolean saturday = ParamUtil.getBoolean(request, "saturday");
			boolean sunday = ParamUtil.getBoolean(request, "sunday");
			boolean[] days = { monday, tuesday, wednesday, thursday, friday,
				saturday, sunday };
			String startTime = ParamUtil.getString(request, "startTime");
			String endTime = ParamUtil.getString(request, "endTime");

			// Paramètres du lieu
			long territoryId = ParamUtil.getLong(request, "territoryId");
			String sigId = ParamUtil.getString(request, "placeSIGId");

			// Paramètre de l'activité
			long activityId = ParamUtil.getLong(request, "activityId");
			long typeId = ParamUtil.getLong(request, "typeId");
			long publicId = ParamUtil.getLong(request, "publicId");

			/**
			 * Filtres
			 */
			// Liste des schedules correspondant aux critères de recherche
			List<ActivityCourseSchedule> schedules = ActivityCourseScheduleLocalServiceUtil
				.findByDaysAndTimes(themeDisplay.getScopeGroupId(), days,
					startTime, endTime);

			// On récupère les lieux correspondant à cette liste de schedules
			List<Long> coursePlaceIds = schedules.stream()
				.map(ActivityCourseSchedule::getActivityCoursePlaceId)
				.distinct().collect(Collectors.toList());
			List<ActivityCoursePlace> coursePlacesWithSchedules = ActivityCoursePlaceLocalServiceUtil
				.findByIds(coursePlaceIds);

			// Mais on veut aussi récupérer les lieux qui n'ont pas de schedule
			// !
			List<ActivityCoursePlace> coursePlacesWithNoSchedule = ActivityCoursePlaceLocalServiceUtil
				.findWithNoSchedule(themeDisplay.getScopeGroupId());

			// On filtre ces lieux par territoryId et sigId
			List<ActivityCoursePlace> coursePlaces = new ArrayList<ActivityCoursePlace>(
				coursePlacesWithSchedules);
			coursePlaces.addAll(coursePlacesWithNoSchedule);
			coursePlaces = this.filterActivityPlacesBySIGIdAndTerritoryId(
				coursePlaces, sigId, territoryId);

			// On récupère les cours publiés correspondant à ces critères
			// On filtre également par public si le public a été renseigné
			List<Long> courseIds = coursePlaces.stream()
				.map(ActivityCoursePlace::getActivityCourseId).distinct()
				.collect(Collectors.toList());
			List<ActivityCourse> courses = ActivityCourseLocalServiceUtil
				.findByIds(courseIds).stream()
				.filter(c -> c.isApproved() && (publicId == 0 || c.getPublics()
					.stream().anyMatch(p -> p.getCategoryId() == publicId)))
				.collect(Collectors.toList());

			// On récupère la liste des activités correspondant à ces cours
			List<Long> activityIds = courses.stream()
				.map(ActivityCourse::getActivityId).distinct()
				.collect(Collectors.toList());
			List<Activity> activities = ActivityLocalServiceUtil
				.findByIds(activityIds);

			// On filtre ces activités par statut, typeId, publicId et
			// activityId
			activities = this.filterActivitiesByTypeAndId(activities, typeId,
				activityId);

			// On rempli maintenant la map pour l'affichage
			// On souhaite afficher les activites et les cours correspondant aux
			// critères
			// Tous les lieux et tous les horaires des cours correspondant aux
			// critères seront affichés
			// C'est à dire que si un cours possède un horaire qui correspond
			// aux critère et un qui ne correspond pas, on affichera les deux
			// horaires tout de même
			for (Activity activity : activities) {
				List<ActivityCourse> activityCourses = courses.stream()
					.filter(c -> c.getActivityId() == activity.getActivityId())
					.collect(Collectors.toList());

				results.put(activity, activityCourses);

			}

			this.results = results;
		}
		return this.results;
	}

	/**
	 * Filtre les lieux par sigId et territoryId
	 */
	private List<ActivityCoursePlace> filterActivityPlacesBySIGIdAndTerritoryId(
		List<ActivityCoursePlace> allPlaces, String sigId, long territoryId) {

		List<ActivityCoursePlace> places = new ArrayList<ActivityCoursePlace>();
		for (ActivityCoursePlace place : allPlaces) {
			boolean okToAddPlace = true;

			// Lieu
			if (Validator.isNotNull(sigId)
				&& !place.getPlaceSIGId().equals(sigId)) {
				okToAddPlace = false;
			}

			// Territoire
			if (territoryId > 0) {
				if (Validator.isNotNull(place.getPlaceCityId())) {
					// Lieu manuel
					long cityId = place.getPlaceCityId();
					AssetCategory city = AssetCategoryLocalServiceUtil
						.fetchAssetCategory(cityId);
					if (city != null) {
						try {
							if (!city.getAncestors().stream().anyMatch(
								c -> c.getCategoryId() == territoryId)) {
								okToAddPlace = false;
							}
						} catch (PortalException e) {
							okToAddPlace = false;
						}
					} else {
						okToAddPlace = false;
					}
				} else {
					// Lieu SIG
					Place sigPlace = PlaceLocalServiceUtil
						.getPlaceBySIGId(place.getPlaceSIGId());
					List<AssetCategory> territories = sigPlace.getTerritories();
					List<AssetCategory> allPlaceTerritories = AssetVocabularyHelper
						.getFullHierarchyCategories(territories);
					if (!allPlaceTerritories.stream()
						.anyMatch(c -> c.getCategoryId() == territoryId)) {
						okToAddPlace = false;
					}
				}
			}
			if (okToAddPlace) {
				places.add(place);
			}
		}
		return places;
	}

	/**
	 * Filtre les activités par type, public et id
	 */
	private List<Activity> filterActivitiesByTypeAndId(
		List<Activity> allActivities, long typeId, long activityId) {

		List<Activity> activities = new ArrayList<Activity>();
		for (Activity activity : allActivities) {
			boolean okToAddActivity = true;

			// Statut
			if (!activity.isApproved()) {
				okToAddActivity = false;
			}

			// Activité
			if (activityId > 0 && activity.getActivityId() != activityId) {
				okToAddActivity = false;
			}

			// Type
			List<AssetCategory> categories = activity.getCategories();
			if (typeId > 0 && !categories.stream()
				.anyMatch(c -> c.getCategoryId() == typeId)) {
				okToAddActivity = false;
			}

			if (okToAddActivity) {
				activities.add(activity);
			}
		}

		return activities;
	}

}