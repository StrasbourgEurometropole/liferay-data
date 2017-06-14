package eu.strasbourg.portlet.activity.display.context;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.portlet.PortletRequest;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetVocabulary;
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
import eu.strasbourg.utils.constants.VocabularyNames;

public class SearchActivityDisplayContext {

	private PortletRequest request;
	private ThemeDisplay themeDisplay;
	private Map<Activity, List<ActivityCourse>> results;
	private SearchActivityConfiguration configuration;
	private List<AssetCategory> activityTypesFromConfiguration;
	private List<AssetCategory> courseTypesFromConfiguration;
	private List<AssetCategory> publicsFromConfiguration;
	private List<AssetCategory> territoriesFromConfiguration;
	private Log log = LogFactoryUtil.getLog(this.getClass());

	public SearchActivityDisplayContext(PortletRequest request) {
		this.request = request;
		this.themeDisplay = (ThemeDisplay) request
			.getAttribute(WebKeys.THEME_DISPLAY);
		try {
			configuration = this.themeDisplay.getPortletDisplay()
				.getPortletInstanceConfiguration(
					SearchActivityConfiguration.class);
		} catch (ConfigurationException e) {
			log.error(e);
		}
		activityTypesFromConfiguration = getCategoryIdsFromUuids(
			configuration.activityTypeUuids(), themeDisplay.getScopeGroupId());
		courseTypesFromConfiguration = getCategoryIdsFromUuids(
			configuration.courseTypeUuids(), themeDisplay.getScopeGroupId());
		publicsFromConfiguration = getCategoryIdsFromUuids(
			configuration.publicUuids(), themeDisplay.getScopeGroupId());
		territoriesFromConfiguration = getCategoryIdsFromUuids(
			configuration.territoryUuids(), themeDisplay.getCompanyGroupId());
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

			// Paramètres du cours
			long publicId = ParamUtil.getLong(request, "publicId");

			// Paramètre de l'activité
			long activityId = ParamUtil.getLong(request, "activityId");
			long activityTypeId = ParamUtil.getLong(request, "activityTypeId");

			/**
			 * Listes de catégories sur lesquels filtrer (via paramètres de
			 * recherche ou configuration)
			 */
			// Types d'activité
			List<Long> activityTypeIds = new ArrayList<Long>();
			if (activityTypeId > 0) {
				activityTypeIds.add(activityTypeId);
			} else {
				activityTypeIds.addAll(activityTypesFromConfiguration.stream()
					.map(AssetCategory::getCategoryId)
					.collect(Collectors.toList()));
			}
			// Types de cours
			List<Long> courseTypeIds = courseTypesFromConfiguration.stream()
				.map(AssetCategory::getCategoryId).collect(Collectors.toList());

			// Publics
			List<Long> publicIds = new ArrayList<Long>();
			if (activityTypeId > 0) {
				publicIds.add(publicId);
			} else {
				publicIds.addAll(publicsFromConfiguration.stream()
					.map(AssetCategory::getCategoryId)
					.collect(Collectors.toList()));
			}
			// Territoires
			List<Long> territoryIds = new ArrayList<Long>();
			if (activityTypeId > 0) {
				territoryIds.add(territoryId);
			} else {
				territoryIds.addAll(territoriesFromConfiguration.stream()
					.map(AssetCategory::getCategoryId)
					.collect(Collectors.toList()));
			}

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
				coursePlaces, sigId, territoryIds);

			// On récupère les cours publiés correspondant à ces critères
			// On filtre également par public si le public a été renseigné
			List<Long> courseIds = coursePlaces.stream()
				.map(ActivityCoursePlace::getActivityCourseId).distinct()
				.collect(Collectors.toList());
			List<ActivityCourse> courses = ActivityCourseLocalServiceUtil
				.findByIds(courseIds).stream()
				// Cours publié
				.filter(course -> course.isApproved())
				// Public
				.filter(course -> publicIds.size() == 0
					|| course.getPublics().stream()
						.anyMatch(p -> publicIds.stream()
							.anyMatch(c -> c == p.getCategoryId())))
				// Type de cours
				.filter(course -> courseTypeIds.size() == 0
					|| course.getTypes().stream()
						.anyMatch(t -> courseTypeIds.stream()
							.anyMatch(c -> c == t.getCategoryId())))
				.collect(Collectors.toList());

			// On récupère la liste des activités correspondant à ces cours
			List<Long> activityIds = courses.stream()
				.map(ActivityCourse::getActivityId).distinct()
				.collect(Collectors.toList());
			List<Activity> activities = ActivityLocalServiceUtil
				.findByIds(activityIds);

			// On filtre ces activités par statut, typeId, publicId et
			// activityId
			activities = this.filterActivitiesByTypeAndId(activities,
				activityTypeIds, activityId);

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
	 * Filtre les lieux par sigId, territoryId et si territoryId est vide par le
	 * préfiltre territoryIds (s'il existe)
	 */
	private List<ActivityCoursePlace> filterActivityPlacesBySIGIdAndTerritoryId(
		List<ActivityCoursePlace> allPlaces, String sigId,
		List<Long> territoryIds) {

		List<ActivityCoursePlace> places = new ArrayList<ActivityCoursePlace>();
		for (ActivityCoursePlace place : allPlaces) {
			boolean okToAddPlace = true;

			// Lieu
			if (Validator.isNotNull(sigId)
				&& !place.getPlaceSIGId().equals(sigId)) {
				okToAddPlace = false;
			}

			// Territoire
			if (territoryIds.size() > 0) {
				if (Validator.isNotNull(place.getPlaceCityId())) {
					// Lieu manuel
					long cityId = place.getPlaceCityId();
					AssetCategory city = AssetCategoryLocalServiceUtil
						.fetchAssetCategory(cityId);
					if (city != null) {
						try {
							if (!city.getAncestors().stream()
								.anyMatch(c -> territoryIds.stream()
									.anyMatch(t -> c.getCategoryId() == t))) {
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
					if (!allPlaceTerritories.stream().anyMatch(c -> territoryIds
						.stream().anyMatch(t -> c.getCategoryId() == t))) {
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
	 * Filtre les activités par public, id et type et si le type est vide, par
	 * préfiltre type d'activité (si il a été renseigné)
	 */
	private List<Activity> filterActivitiesByTypeAndId(
		List<Activity> allActivities, List<Long> activityTypeIds,
		long activityId) {

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
			if (activityTypeIds.size() > 0
				&& !categories.stream().anyMatch(c -> activityTypeIds.stream()
					.anyMatch(t -> t == c.getCategoryId()))) {
				okToAddActivity = false;
			}

			if (okToAddActivity) {
				activities.add(activity);
			}
		}

		return activities;
	}

	/**
	 * Retourne la liste des catégories correspondant aux uuids (séparés par des
	 * virgules) et au groupId passés en paramètres
	 */
	private List<AssetCategory> getCategoryIdsFromUuids(String uuids,
		long groupId) {
		List<AssetCategory> categories = new ArrayList<AssetCategory>();
		if (Validator.isNotNull(uuids)) {
			for (String uuid : uuids.split(",")) {
				AssetCategory category = AssetCategoryLocalServiceUtil
					.fetchAssetCategoryByUuidAndGroupId(uuid, groupId);
				if (Validator.isNotNull(category)) {
					categories.add(category);
				}
			}
		}
		return categories;
	}

	/**
	 * Liste des types d'activité à afficher dans la liste déroulante du moteur
	 * de recherche
	 */
	public List<AssetCategory> getActivityTypes() {
		return this.getCategoriesForDropdowns(VocabularyNames.ACTIVITY_TYPE,
			this.activityTypesFromConfiguration,
			themeDisplay.getScopeGroupId());
	}

	/**
	 * Liste des publics à afficher dans la liste déroulante du moteur de
	 * recherche
	 */
	public List<AssetCategory> getPublics() {
		return this.getCategoriesForDropdowns(
			VocabularyNames.ACTIVITY_COURSE_PUBLIC,
			this.publicsFromConfiguration, themeDisplay.getScopeGroupId());
	}

	/**
	 * Liste des territoires à afficher dans la liste déroulante du moteur de
	 * recherche
	 */
	public List<AssetCategory> getTerritories() {
		return this.getCategoriesForDropdowns(VocabularyNames.TERRITORY,
			this.territoriesFromConfiguration,
			themeDisplay.getCompanyGroupId());
	}

	/**
	 * Retourne la liste des catégories à afficher dans la liste déroulante pour
	 * un vocabulaire
	 */
	private List<AssetCategory> getCategoriesForDropdowns(String vocabularyName,
		List<AssetCategory> categoriesFromConfiguration, long groupId) {

		// Si il n'y a pas de préfiltre dans la configuration, on renvoie toutes
		// les catégories racines
		if (categoriesFromConfiguration.size() == 0) {
			AssetVocabulary vocabulary = AssetVocabularyHelper
				.getVocabulary(vocabularyName, groupId);
			return vocabulary.getCategories().stream()
				.filter(c -> c.isRootCategory()).collect(Collectors.toList());
		}

		// S'il y a une seule catégorie dans la configuration, on renvoie tous
		// ses enfants
		else if (categoriesFromConfiguration.size() == 1) {
			return AssetCategoryLocalServiceUtil.getChildCategories(
				categoriesFromConfiguration.get(0).getCategoryId());
		}

		// S'il y a plusieurs catégories dans la configuration, on les renvoie
		else {
			return categoriesFromConfiguration;
		}
	}

}