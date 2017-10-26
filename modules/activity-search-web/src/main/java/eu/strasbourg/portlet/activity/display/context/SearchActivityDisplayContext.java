package eu.strasbourg.portlet.activity.display.context;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.asset.kernel.service.AssetCategoryLocalServiceUtil;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.module.configuration.ConfigurationException;
import com.liferay.portal.kernel.service.LayoutLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;
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
import eu.strasbourg.utils.Pager;
import eu.strasbourg.utils.constants.VocabularyNames;
import eu.strasbourg.utils.group.GroupHelper;

public class SearchActivityDisplayContext {

	private RenderRequest request;
	private RenderResponse response;
	private ThemeDisplay themeDisplay;
	private List<Map.Entry<Activity, List<ActivityCourse>>> results;
	private SearchContainer<Map.Entry<Activity, List<ActivityCourse>>> searchContainer;
	private SearchActivityConfiguration configuration;
	private List<AssetCategory> activityTypesFromConfiguration;
	private List<AssetCategory> courseTypesFromConfiguration;
	private List<AssetCategory> publicsFromConfiguration;
	private List<AssetCategory> territoriesFromConfiguration;
	private Log log = LogFactoryUtil.getLog(this.getClass());

	public SearchActivityDisplayContext(RenderRequest request, RenderResponse response) {
		this.request = request;
		this.response = response;
		this.themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
		try {
			configuration = this.themeDisplay.getPortletDisplay()
					.getPortletInstanceConfiguration(SearchActivityConfiguration.class);
		} catch (ConfigurationException e) {
			log.error(e);
		}
		activityTypesFromConfiguration = getCategoryIdsFromUuids(configuration.activityTypeUuids(),
				themeDisplay.getScopeGroupId());
		courseTypesFromConfiguration = getCategoryIdsFromUuids(configuration.courseTypeUuids(),
				themeDisplay.getScopeGroupId());
		publicsFromConfiguration = getCategoryIdsFromUuids(configuration.publicUuids(), themeDisplay.getScopeGroupId());
		territoriesFromConfiguration = getCategoryIdsFromUuids(configuration.territoryUuids(),
				themeDisplay.getCompanyGroupId());
	}

	public Map<String, Object> getTemplateContextObjects(Activity activity) {

		Map<String, Object> contextObjects = new HashMap<String, Object>();

		// Friendly URL des page de détail
		SearchActivityConfiguration configuration = null;
		try {
			configuration = themeDisplay.getPortletDisplay()
					.getPortletInstanceConfiguration(SearchActivityConfiguration.class);
		} catch (ConfigurationException e) {
			log.error(e);
		}
		String detailPageUuid = configuration.detailPageUuid();
		Layout layout = LayoutLocalServiceUtil.fetchLayoutByUuidAndGroupId(detailPageUuid,
				themeDisplay.getScopeGroupId(), false);
		if (layout != null) {
			String detailPageFriendlyURL = layout.getFriendlyURL(themeDisplay.getLocale());
			contextObjects.put("detailPageFriendlyURL", detailPageFriendlyURL);
		}

		String courseDetailPageUuid = configuration.courseDetailPageUuid();
		Layout courseLayout = LayoutLocalServiceUtil.fetchLayoutByUuidAndGroupId(courseDetailPageUuid,
				themeDisplay.getScopeGroupId(), false);
		if (courseLayout != null) {
			String courseDetailPageFriendlyURL = courseLayout.getFriendlyURL(themeDisplay.getLocale());
			contextObjects.put("courseDetailPageFriendlyURL", courseDetailPageFriendlyURL);
		}

		// Activité
		contextObjects.put("entry", activity);

		// Cours
		List<ActivityCourse> activityCourses = new ArrayList<ActivityCourse>();
		for (Map.Entry<Activity, List<ActivityCourse>> entry : this.getResults()) {
			if (entry.getKey() == activity) {
				activityCourses = entry.getValue();
			}
		}
		contextObjects.put("courses", activityCourses);

		// Périodes, avec celle qui a la propriété "default" à "true" en premier
		// si elle existe
		AssetVocabulary periodVocabulary = AssetVocabularyHelper.getVocabulary(VocabularyNames.ACTIVITY_PERIOD,
				GroupHelper.getScopeOrStagingGroupId(themeDisplay.getScopeGroupId()));
		List<AssetCategory> periodCategories = periodVocabulary.getCategories();
		List<AssetCategory> periods = new ArrayList<AssetCategory>(periodCategories.size());
		for (int i = 0; i < periodCategories.size(); i++) {
			AssetCategory period = periodCategories.get(i);
			String isDefault = AssetVocabularyHelper.getCategoryProperty(period.getCategoryId(), "default");
			if (Validator.isNotNull(isDefault) && isDefault.equals("true")) {
				periods.add(0, period);
			} else {
				periods.add(period);
			}
		}
		contextObjects.put("periods", periods);

		return contextObjects;
	}

	/**
	 * Renvoie true si le jour donné en paramètre a été sélectionné par
	 * l'utilisateur
	 */
	public boolean isDaySelected(String day) {
		// Soit un paramètre par jour, soit un array des jours sélectionnés
		String[] selectedDays = ParamUtil.getStringValues(request, "days");
		return ArrayUtil.contains(selectedDays, day);
	}

	public List<Map.Entry<Activity, List<ActivityCourse>>> getResults() {
		if (this.results == null) {
			List<Map.Entry<Activity, List<ActivityCourse>>> results = new ArrayList<Map.Entry<Activity, List<ActivityCourse>>>();

			/**
			 * Paramètres de la recherche
			 */
			// Jours : soit un paramètre par jour, soit un array des jours
			// sélectionnés
			boolean monday = isDaySelected("monday");
			boolean tuesday = isDaySelected("tuesday");
			boolean wednesday = isDaySelected("wednesday");
			boolean thursday = isDaySelected("thursday");
			boolean friday = isDaySelected("friday");
			boolean saturday = isDaySelected("saturday");
			boolean sunday = isDaySelected("sunday");
			boolean[] days = { monday, tuesday, wednesday, thursday, friday, saturday, sunday };

			// Heure de début : soit paramètre "startTime" au format "hh:mm"
			String startTime = ParamUtil.getString(request, "startTime");
			String startTimeHour = ParamUtil.getString(request, "startTimeHour");
			String startTimeMinute = ParamUtil.getString(request, "startTimeMinute");
			// Soit paramètres "startTimeHour" et "startTimeMinute"à concaténer
			if (Validator.isNull(startTime) && !Validator.isNull(startTimeHour) && !Validator.isNull(startTimeMinute)) {
				startTime = startTimeHour + ":" + startTimeMinute;
			}
			// Idem pour heure de fin
			String endTime = ParamUtil.getString(request, "endTime");
			String endTimeHour = ParamUtil.getString(request, "endTimeHour");
			String endTimeMinute = ParamUtil.getString(request, "endTimeMinute");
			if (Validator.isNull(endTime) && !Validator.isNull(endTimeHour) && !Validator.isNull(endTimeMinute)) {
				endTime = endTimeHour + ":" + endTimeMinute;
			}

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
				List<AssetCategory> allActivityTypes = AssetVocabularyHelper.getCategoryWithChild(activityTypeId);
				List<Long> allActivityTypesIds = allActivityTypes.stream().map(AssetCategory::getCategoryId)
						.collect(Collectors.toList());
				activityTypeIds.addAll(allActivityTypesIds);
			} else {
				List<Long> allActivityTypeIds = AssetVocabularyHelper
						.getCategoriesWithChild(activityTypesFromConfiguration).stream()
						.map(AssetCategory::getCategoryId).collect(Collectors.toList());
				activityTypeIds.addAll(allActivityTypeIds);
			}
			// Types de cours
			List<Long> courseTypeIds = AssetVocabularyHelper.getCategoriesWithChild(courseTypesFromConfiguration)
					.stream().map(AssetCategory::getCategoryId).collect(Collectors.toList());

			// Publics
			List<Long> publicIds = new ArrayList<Long>();
			if (publicId > 0) {
				List<AssetCategory> allPublics = AssetVocabularyHelper.getCategoryWithChild(publicId);
				List<Long> allPublicsIds = allPublics.stream().map(AssetCategory::getCategoryId)
						.collect(Collectors.toList());
				publicIds.addAll(allPublicsIds);
			} else {
				List<Long> allPublicIds = AssetVocabularyHelper.getCategoriesWithChild(publicsFromConfiguration)
						.stream().map(AssetCategory::getCategoryId).collect(Collectors.toList());
				publicIds.addAll(allPublicIds);
			}
			// Territoires
			List<Long> territoryIds = new ArrayList<Long>();
			if (territoryId > 0) {
				List<AssetCategory> allTerritorys = AssetVocabularyHelper.getCategoryWithChild(territoryId);
				List<Long> allTerritorysIds = allTerritorys.stream().map(AssetCategory::getCategoryId)
						.collect(Collectors.toList());
				territoryIds.addAll(allTerritorysIds);
			} else {
				List<Long> allTerritoryIds = AssetVocabularyHelper.getCategoriesWithChild(territoriesFromConfiguration)
						.stream().map(AssetCategory::getCategoryId).collect(Collectors.toList());
				territoryIds.addAll(allTerritoryIds);
			}

			/**
			 * Filtres
			 */
			// Liste des schedules correspondant aux critères de recherche
			List<ActivityCourseSchedule> schedules = ActivityCourseScheduleLocalServiceUtil
					.findByDaysAndTimes(themeDisplay.getScopeGroupId(), days, startTime, endTime);

			// On récupère les lieux correspondant à cette liste de schedules
			List<Long> coursePlaceIds = schedules.stream().map(ActivityCourseSchedule::getActivityCoursePlaceId)
					.distinct().collect(Collectors.toList());
			List<ActivityCoursePlace> coursePlacesWithSchedules = ActivityCoursePlaceLocalServiceUtil
					.findByIds(coursePlaceIds);

			// Mais si on n'a pas de critère temporel (jours ou heures) on veut
			// aussi récupérer les lieux qui n'ont pas de schedule !
			List<ActivityCoursePlace> coursePlacesWithNoSchedule = new ArrayList<ActivityCoursePlace>();
			if (!monday && !tuesday && !thursday && !wednesday && !thursday && !friday && !saturday && !sunday
					&& Validator.isNull(startTime) && Validator.isNull(endTime)) {
				coursePlacesWithNoSchedule = ActivityCoursePlaceLocalServiceUtil
						.findWithNoSchedule(themeDisplay.getScopeGroupId());
			}

			// On filtre ces lieux par territoryId et sigId
			List<ActivityCoursePlace> coursePlaces = new ArrayList<ActivityCoursePlace>(coursePlacesWithSchedules);
			coursePlaces.addAll(coursePlacesWithNoSchedule);
			coursePlaces = this.filterActivityPlacesBySIGIdAndTerritoryId(coursePlaces, sigId, territoryIds);

			// On récupère les cours publiés correspondant à ces critères
			// On filtre également par public si le public a été renseigné
			List<Long> courseIds = coursePlaces.stream().map(ActivityCoursePlace::getActivityCourseId).distinct()
					.collect(Collectors.toList());
			List<ActivityCourse> courses = ActivityCourseLocalServiceUtil.findByIds(courseIds).stream()
					// Cours publié
					.filter(course -> course.isApproved())
					// Public
					.filter(course -> publicIds.size() == 0 || course.getPublics().stream()
							.anyMatch(p -> publicIds.stream().anyMatch(c -> c == p.getCategoryId())))
					// Type de cours
					.filter(course -> courseTypeIds.size() == 0 || course.getTypes().stream()
							.anyMatch(t -> courseTypeIds.stream().anyMatch(c -> c == t.getCategoryId())))
					.collect(Collectors.toList());

			// On récupère la liste des activités correspondant à ces cours
			List<Long> activityIds = courses.stream().map(ActivityCourse::getActivityId).distinct()
					.collect(Collectors.toList());
			List<Activity> activities = ActivityLocalServiceUtil.findByIds(activityIds);

			// On filtre ces activités par statut, typeId, publicId et
			// activityId
			activities = this.filterActivitiesByTypeAndId(activities, activityTypeIds, activityId);

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
						.filter(c -> c.getActivityId() == activity.getActivityId()).collect(Collectors.toList());

				Map.Entry<Activity, List<ActivityCourse>> entry = new AbstractMap.SimpleEntry<Activity, List<ActivityCourse>>(
						activity, activityCourses);
				results.add(entry);

			}

			this.results = results;
		}
		return this.results;
	}

	/**
	 * Retourne les résultats entre les indexes start (inclu) et end (non inclu)
	 */
	public List<Entry<Activity, List<ActivityCourse>>> getPaginatedResults() {
		return ListUtil.subList(getResults(), this.getSearchContainer().getStart(), this.getSearchContainer().getEnd());
	}

	/**
	 * Retourne le nombre total de résultats
	 */
	public int getResultCount() {
		return getResults().size();
	}

	/**
	 * Retourne le searchContainer
	 */
	public SearchContainer<Map.Entry<Activity, List<ActivityCourse>>> getSearchContainer() {
		if (searchContainer == null) {
			Map<String, String[]> parameterMap = request.getParameterMap();
			PortletURL iteratorURL = response.createRenderURL();
			iteratorURL.setParameters(parameterMap);
			searchContainer = new SearchContainer<Map.Entry<Activity, List<ActivityCourse>>>(request, iteratorURL, null,
					"no-entries-were-found");
			searchContainer.setDelta(this.getDelta());
			searchContainer.setTotal(this.getResultCount());
			searchContainer.setResults(results);
		}
		return searchContainer;
	}
	
	/**
	 * Retourne le nombre d'items par page à afficher
	 */
	public int getDelta() {
		int deltaFromParam = ParamUtil.getInteger(this.request, "delta");
		if (deltaFromParam > 0) {
			return deltaFromParam;
		}
		return 10;
	}
	
	/**
	 * Retourne le pager de la page
	 */
	public Pager getPager() {
		return new Pager(this.getSearchContainer().getTotal(), (int) this.getDelta(), this.getSearchContainer().getCur());
	}

	/**
	 * Retourne l'URL sur laquelle aller pour avoir X items par page
	 */
	public String getURLForDelta(long delta) {
		PortletURL url = this.getSearchContainer().getIteratorURL();
		url.setParameter("delta", String.valueOf(delta));
		String valueToReturn = url.toString();
		url.setParameter("delta", String.valueOf(this.getDelta()));
		return valueToReturn;
	}

	
	/**
	 * Retourne l'URL sur laquelle aller pour accéder à la Xième page
	 */
	public String getURLForPage(long pageIndex) {
		PortletURL url = this.getSearchContainer().getIteratorURL();
		url.setParameter("cur", String.valueOf(pageIndex));
		String valueToReturn = url.toString();
		url.setParameter("cur", String.valueOf(this.getSearchContainer().getCur()));
		return valueToReturn;
	}
	
	/**
	 * Filtre les lieux par sigId, territoryId et si territoryId est vide par le
	 * préfiltre territoryIds (s'il existe)
	 */
	private List<ActivityCoursePlace> filterActivityPlacesBySIGIdAndTerritoryId(List<ActivityCoursePlace> allPlaces,
			String sigId, List<Long> territoryIds) {

		List<ActivityCoursePlace> places = new ArrayList<ActivityCoursePlace>();
		for (ActivityCoursePlace place : allPlaces) {
			boolean okToAddPlace = true;

			// Lieu
			if (Validator.isNotNull(sigId) && !place.getPlaceSIGId().equals(sigId)) {
				okToAddPlace = false;
			}

			// Territoire
			if (territoryIds.size() > 0) {
				if (Validator.isNotNull(place.getPlaceCityId())) {
					// Lieu manuel
					if (!territoryIds.contains(place.getPlaceCityId())) {
						okToAddPlace = false;
					}
				} else {
					// Lieu SIG
					Place sigPlace = PlaceLocalServiceUtil.getPlaceBySIGId(place.getPlaceSIGId());
					List<AssetCategory> territories = sigPlace.getTerritories();
					if (!territoryIds.stream()
							.anyMatch(c -> territories.stream().anyMatch(t -> c == t.getCategoryId()))) {
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
	private List<Activity> filterActivitiesByTypeAndId(List<Activity> allActivities, List<Long> activityTypeIds,
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
			if (activityTypeIds.size() > 0 && !categories.stream()
					.anyMatch(c -> activityTypeIds.stream().anyMatch(t -> t == c.getCategoryId()))) {
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
	private List<AssetCategory> getCategoryIdsFromUuids(String uuids, long groupId) {
		List<AssetCategory> categories = new ArrayList<AssetCategory>();
		if (Validator.isNotNull(uuids)) {
			for (String uuid : uuids.split(",")) {
				AssetCategory category = AssetCategoryLocalServiceUtil.fetchAssetCategoryByUuidAndGroupId(uuid,
						groupId);
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
		return this.getCategoriesForDropdown(VocabularyNames.ACTIVITY_TYPE, this.activityTypesFromConfiguration,
				themeDisplay.getScopeGroupId());
	}

	/**
	 * Liste des publics à afficher dans la liste déroulante du moteur de
	 * recherche
	 */
	public List<AssetCategory> getPublics() {
		return this.getCategoriesForDropdown(VocabularyNames.ACTIVITY_COURSE_PUBLIC, this.publicsFromConfiguration,
				themeDisplay.getScopeGroupId());
	}

	/**
	 * Liste des territoires à afficher dans la liste déroulante du moteur de
	 * recherche
	 */
	public List<AssetCategory> getTerritories() {
		return this.getCategoriesForDropdown(VocabularyNames.TERRITORY, this.territoriesFromConfiguration,
				themeDisplay.getCompanyGroupId());
	}

	/**
	 * Retourne la liste des catégories à afficher dans la liste déroulante pour
	 * un vocabulaire
	 */
	private List<AssetCategory> getCategoriesForDropdown(String vocabularyName,
			List<AssetCategory> categoriesFromConfiguration, long groupId) {

		// Si il n'y a pas de préfiltre dans la configuration, on renvoie toutes
		// les catégories racines
		if (categoriesFromConfiguration.size() == 0) {
			AssetVocabulary vocabulary = AssetVocabularyHelper.getVocabulary(vocabularyName, groupId);
			return vocabulary.getCategories().stream().filter(c -> c.isRootCategory()).collect(Collectors.toList());
		}

		// S'il y a une seule catégorie dans la configuration, on renvoie tous
		// ses enfants
		else if (categoriesFromConfiguration.size() == 1) {
			return AssetCategoryLocalServiceUtil.getChildCategories(categoriesFromConfiguration.get(0).getCategoryId());
		}

		// S'il y a plusieurs catégories dans la configuration, on les renvoie
		else {
			return categoriesFromConfiguration;
		}
	}

	/**
	 * Retourne la liste des activités à afficher dans la liste déroulante du
	 * moteur de recherche
	 */
	public List<Activity> getActivitiesForDropdown() {
		List<Activity> activities = ActivityLocalServiceUtil.getActivities(-1, -1);

		List<Long> activityTypeIds = AssetVocabularyHelper.getCategoriesWithChild(activityTypesFromConfiguration)
				.stream().map(AssetCategory::getCategoryId).collect(Collectors.toList());

		Stream<Activity> activitiesStream = activities.stream().filter(a -> a.isApproved())
				.filter(a -> a.getGroupId() == themeDisplay.getScopeGroupId());

		if (activityTypeIds.size() > 0) {
			activitiesStream = activitiesStream
					.filter(a -> a.getTypes().stream().anyMatch(activityType -> activityTypeIds.stream()
							.anyMatch(typeIdFromConf -> typeIdFromConf == activityType.getCategoryId())));
		}

		return activitiesStream.collect(Collectors.toList());
	}

	/**
	 * Retourne le texte à afficher après le formulaire et avant les résultats
	 */
	public String getText() {
		String textXML = this.configuration.textXML();
		Map<Locale, String> localizationMap = LocalizationUtil.getLocalizationMap(textXML);
		return localizationMap.get(this.themeDisplay.getLocale());
	}

}