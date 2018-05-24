package eu.strasbourg.service.poi.impl;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.liferay.asset.kernel.service.persistence.AssetEntryQuery;
import org.osgi.service.component.annotations.Component;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.service.AssetCategoryLocalServiceUtil;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.Criterion;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import eu.strasbourg.service.agenda.model.Event;
import eu.strasbourg.service.agenda.service.EventLocalServiceUtil;
import eu.strasbourg.service.favorite.model.Favorite;
import eu.strasbourg.service.favorite.model.FavoriteType;
import eu.strasbourg.service.favorite.service.FavoriteLocalServiceUtil;
import eu.strasbourg.service.interest.model.Interest;
import eu.strasbourg.service.interest.service.InterestLocalServiceUtil;
import eu.strasbourg.service.place.model.Place;
import eu.strasbourg.service.place.model.PlaceSchedule;
import eu.strasbourg.service.place.service.PlaceLocalServiceUtil;
import eu.strasbourg.service.poi.PoiService;
import eu.strasbourg.utils.JSONHelper;
import eu.strasbourg.utils.OccupationState;
import eu.strasbourg.utils.StrasbourgPropsUtil;
import eu.strasbourg.utils.models.Pair;

/**
 * @author Benjamin Bini
 */
@Component(immediate = true, property = {}, service = PoiService.class)
public class PoiServiceImpl implements PoiService {

	public JSONObject getPois(String idInterests, long groupId) {
		return getPois(idInterests, "", "", groupId, Place.class.getName());
	}

	public JSONObject getPois(String idInterests, String idCategories, String prefilters, long groupId, String classNames) {
		JSONObject geoJson = null;

		long globalGroupId = -1;

		// Récupération des préfiltres
		Long[] prefiltersCategoryIds = new Long[0];
		if (prefilters.length() > 0) {
			String[] prefiltersParts = prefilters.split(",");
			prefiltersCategoryIds = new Long[prefiltersParts.length];
			for (int i = 0; i < prefiltersParts.length; i++) {
				prefiltersCategoryIds[i] = Long.valueOf(prefiltersParts[i]);
			}
		}

		// récupère les catégories ainsi que les catégories enfants des
		// catégories
		List<AssetCategory> filterCategories = new ArrayList<AssetCategory>();
		if (Validator.isNotNull(idCategories)) {
			for (String idCategory : idCategories.split(",")) {
				AssetCategory assetCategory = AssetCategoryLocalServiceUtil
						.fetchAssetCategory(Long.parseLong(idCategory));
				filterCategories.add(assetCategory);
				// récupère les catégories enfants
				List<AssetCategory> chilsCategories = AssetCategoryLocalServiceUtil
						.getChildCategories(assetCategory.getCategoryId());
				if (!chilsCategories.isEmpty()) {
					filterCategories.addAll(chilsCategories);
				}
				if (globalGroupId == -1) {
					globalGroupId = assetCategory.getGroupId();
				}
			}
		}
		// récupère les catégories ainsi que les catégories enfants des centres
		// d'intérêts
		if (Validator.isNotNull(idInterests)) {
			for (String idInterest : idInterests.split(",")) {
				Interest interest = InterestLocalServiceUtil.fetchInterest(Long.parseLong(idInterest));
				List<AssetCategory> categories = interest.getCategories();
				filterCategories.addAll(categories);
				for (AssetCategory assetCategory : categories) {
					// récupère les catégories enfants
					List<AssetCategory> chilsCategories = AssetCategoryLocalServiceUtil
							.getChildCategories(assetCategory.getCategoryId());
					if (!chilsCategories.isEmpty()) {
						filterCategories.addAll(chilsCategories);
					}
				}
				if (globalGroupId == -1) {
					globalGroupId = interest.getGroupId();
				}
			}
		}

		Long[] filterCategoryIds = new Long[filterCategories.size()];
		for (int i = 0; i < filterCategories.size(); i++) {
			filterCategoryIds[i] = filterCategories.get(i).getCategoryId();
		}

		long startTime = 0, endTime = 0, duration = 0;
		List<Place> places = new ArrayList<Place>();
		if (classNames.equals("all") || classNames.contains(Place.class.getName())) {
			// récupère les lieux des centres d'intérêt
			startTime = System.nanoTime();
			places = getPlaces(filterCategoryIds, prefiltersCategoryIds, globalGroupId);
			endTime = System.nanoTime();
			duration = (endTime - startTime) / 1_000_000;
			System.out.println("GetPlaces : " + duration + "ms (" + places.size() + " items)");
		}

		List<Event> events = new ArrayList<Event>();
		if (classNames.equals("all") || classNames.contains(Event.class.getName())) {
			// récupère les évènements des centres d'intérêt
			startTime = System.nanoTime();
			events = getEvents(filterCategoryIds, prefiltersCategoryIds, globalGroupId);
			endTime = System.nanoTime();
			duration = (endTime - startTime) / 1_000_000;
			System.out.println("GetEvents : " + duration + "ms (" + events.size() + " items)");
		}

		// récupère le fichier geoJson
		try {
			startTime = System.nanoTime();
			geoJson = getGeoJSON(places, events, groupId);
			endTime = System.nanoTime();
			duration = (endTime - startTime) / 1_000_000;
			System.out.println("getGeoJSON : " + duration + "ms");
			System.out.println();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return geoJson;
	}

	public int getPoisCategoryCount(long idCategory, String prefilters, long groupId, String classNames) {

		// récupère les catégories ainsi que les catégories enfants des
		// catégories
		long globalGroupId = -1;
		List<AssetCategory> categories = new ArrayList<AssetCategory>();
		if (Validator.isNotNull(idCategory)) {
			AssetCategory assetCategory = AssetCategoryLocalServiceUtil.fetchAssetCategory(idCategory);
			categories.add(assetCategory);
			// récupère les catégories enfants
			List<AssetCategory> childCategories = AssetCategoryLocalServiceUtil
					.getChildCategories(assetCategory.getCategoryId());
			if (!childCategories.isEmpty()) {
				categories.addAll(childCategories);
			}
			if (globalGroupId == -1) {
				globalGroupId = assetCategory.getGroupId();
			}
		}

		AssetEntryQuery query = new AssetEntryQuery();
		// Récupération des préfiltres
		long[] prefiltersCategoryIds = new long[0];
		if (prefilters.length() > 0) {
			String[] prefiltersParts = prefilters.split(",");
			prefiltersCategoryIds = new long[prefiltersParts.length];
			for (int i = 0; i < prefiltersParts.length; i++) {
				prefiltersCategoryIds[i] = Long.valueOf(prefiltersParts[i]);
			}
		}
		if (prefiltersCategoryIds.length > 0) {
			query.setAllCategoryIds(prefiltersCategoryIds);
		}
		query.setAnyCategoryIds(categories.stream().mapToLong(c -> c.getCategoryId()).toArray());


		List<AssetEntry> entriesForFiltersAndPrefilters = AssetEntryLocalServiceUtil.getEntries(query);

		List<AssetEntry> entries = new ArrayList<AssetEntry>();

		for (AssetEntry entry : entriesForFiltersAndPrefilters) {
			if (entry.getVisible() && entry.getListable()
					&& (classNames.contains(entry.getClassName()) || classNames.equals("all"))) {
				entries.add(entry);
			}
		}
		return entries.size();
	}

	public int getPoisInterestCount(long idInterest, long groupId, String classNames) {

        Interest interest = InterestLocalServiceUtil.fetchInterest(idInterest);
        List<AssetCategory> interestCategories = interest.getCategories();
        // récupère les catégories ainsi que les catégories enfants des
        // catégories
        long globalGroupId = -1;
        List<AssetCategory> categories = new ArrayList<AssetCategory>();
        for (AssetCategory interestCategory : interestCategories) {
            categories.add(interestCategory);
            // récupère les catégories enfants
            List<AssetCategory> childCategories = AssetCategoryLocalServiceUtil
                    .getChildCategories(interestCategory.getCategoryId());
            if (!childCategories.isEmpty()) {
                categories.addAll(childCategories);
            }
            if (globalGroupId == -1) {
                globalGroupId = interestCategory.getGroupId();
            }
        }

        AssetEntryQuery query = new AssetEntryQuery();
        query.setAnyCategoryIds(categories.stream().mapToLong(c -> c.getCategoryId()).toArray());


        List<AssetEntry> entriesForFilters = AssetEntryLocalServiceUtil.getEntries(query);

        List<AssetEntry> entries = new ArrayList<>();

        for (AssetEntry entry : entriesForFilters) {
            if (entry.getVisible() && entry.getListable()
                    && (classNames.contains(entry.getClassName()) || classNames.equals("all"))) {
                entries.add(entry);
            }
        }
        return entries.size();
	}

	private List<Place> getPlaces(Long[] categoryIds, Long[] prefilters, long globalGroupId) {

		List<AssetEntry> entriesFromFilters = new ArrayList<>();
		for (Long categoryId : categoryIds) {
			entriesFromFilters.addAll(AssetEntryLocalServiceUtil.getAssetCategoryAssetEntries(categoryId));
			List<AssetCategory> childCategories = AssetCategoryLocalServiceUtil
					.getChildCategories(categoryId);
			for (AssetCategory category : childCategories) {
				entriesFromFilters.addAll(AssetEntryLocalServiceUtil.getAssetCategoryAssetEntries(category.getCategoryId()));
			}
		}
		List<AssetEntry> entries = new ArrayList(entriesFromFilters);

		if (prefilters.length > 0) {
			List<AssetEntry> entriesFromPrefilters = new ArrayList<>();
			for (Long categoryId : prefilters) {
				entriesFromPrefilters.addAll(AssetEntryLocalServiceUtil.getAssetCategoryAssetEntries(categoryId));
			}

			entries = entriesFromFilters.stream()
					.filter(e -> entriesFromPrefilters.stream()
							.anyMatch(p -> p.getEntryId() == e.getEntryId())).collect(Collectors.toList());
		}

		List<Long> classPks = entries.stream().map(AssetEntry::getClassPK).collect(Collectors.toList());
		Criterion idCriterion = RestrictionsFactoryUtil.in("placeId", classPks);
		Criterion statusCriterion = RestrictionsFactoryUtil.eq("status", WorkflowConstants.STATUS_APPROVED);
		DynamicQuery placeQuery = PlaceLocalServiceUtil.dynamicQuery().add(idCriterion).add(statusCriterion);
		return PlaceLocalServiceUtil.dynamicQuery(placeQuery);
	}

	private List<Event> getEvents(Long[] categoryIds, Long[] prefilters, long globalGroupId) {

		List<AssetEntry> entriesFromFilters = new ArrayList<>();
		for (Long categoryId : categoryIds) {
			entriesFromFilters.addAll(AssetEntryLocalServiceUtil.getAssetCategoryAssetEntries(categoryId));
			List<AssetCategory> childCategories = AssetCategoryLocalServiceUtil
					.getChildCategories(categoryId);
			for (AssetCategory category : childCategories) {
				entriesFromFilters.addAll(AssetEntryLocalServiceUtil.getAssetCategoryAssetEntries(category.getCategoryId()));
			}
		}
		List<AssetEntry> entries = new ArrayList(entriesFromFilters);

		if (prefilters.length > 0) {
			List<AssetEntry> entriesFromPrefilters = new ArrayList<>();
			for (Long categoryId : prefilters) {
				entriesFromPrefilters.addAll(AssetEntryLocalServiceUtil.getAssetCategoryAssetEntries(categoryId));
			}

			entries = entriesFromFilters.stream()
					.filter(e -> entriesFromPrefilters.stream()
							.anyMatch(p -> p.getEntryId() == e.getEntryId())).collect(Collectors.toList());
		}

		List<Long> classPks = entries.stream().map(AssetEntry::getClassPK).collect(Collectors.toList());
		Criterion idCriterion = RestrictionsFactoryUtil.in("eventId", classPks);
		Criterion statusCriterion = RestrictionsFactoryUtil.eq("status", WorkflowConstants.STATUS_APPROVED);
		DynamicQuery eventQuery = EventLocalServiceUtil.dynamicQuery().add(idCriterion).add(statusCriterion);
		List<Event> events = EventLocalServiceUtil.dynamicQuery(eventQuery);

		return events;
	}

	public JSONObject getFavoritesPois(String userId, long groupId) {
		return getFavoritesPois(userId, groupId, Place.class.getName());
	}

	public JSONObject getFavoritesPois(String userId, long groupId, String classNames) {
		JSONObject geoJson = null;

		// récupère les favoris de l'uilisateur
		List<Favorite> favorites = FavoriteLocalServiceUtil.getByPublikUser(userId);
		List<Place> places = new ArrayList<Place>();
		List<Event> events = new ArrayList<Event>();
		if (favorites != null) {
			if (classNames.equals("all") || classNames.contains(Place.class.getName())) {
				Stream<Favorite> placeFavorites = favorites.stream()
						.filter(f -> f.getTypeId() == FavoriteType.PLACE.getId());
				if (placeFavorites != null) {
					for (Favorite favorite : placeFavorites.collect(Collectors.toList())) {
						Place place = PlaceLocalServiceUtil.fetchPlace(favorite.getEntityId());
						if (place != null) {
							places.add(place);
						}
					}
				}
			}
			if (classNames.equals("all") || classNames.contains(Event.class.getName())) {
				Stream<Favorite> eventFavorites = favorites.stream()
						.filter(f -> f.getTypeId() == FavoriteType.EVENT.getId());
				if (eventFavorites != null) {
					for (Favorite favorite : eventFavorites.collect(Collectors.toList())) {
						Event event = EventLocalServiceUtil.fetchEvent(favorite.getEntityId());
						if (event != null) {
							events.add(event);
						}
					}
				}
			}
		}

		// récupère le fichier geoJson
		try {
			geoJson = getGeoJSON(places, events, groupId);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return geoJson;
	}

	public int getFavoritesPoisCount(String userId, long groupId, String classNames) {
		int count = 0;

		// récupère les favoris de l'uilisateur
		List<Favorite> favorites = FavoriteLocalServiceUtil.getByPublikUser(userId);
		if (classNames.equals("all"))
			return favorites.size();
		else {
			if (classNames.contains(Place.class.getName()))
				count += favorites.stream().filter(f -> f.getTypeId() == FavoriteType.PLACE.getId())
						.collect(Collectors.toList()).size();
			if (classNames.contains(Event.class.getName()))
				count += favorites.stream().filter(f -> f.getTypeId() == FavoriteType.EVENT.getId())
						.collect(Collectors.toList()).size();
		}
		return count;
	}

	static private JSONObject getGeoJSON(List<Place> places, List<Event> events, long groupId) throws JSONException {
		JSONObject geoJSON = JSONFactoryUtil.createJSONObject();
		geoJSON.put("type", "FeatureCollection");

		JSONArray features = JSONFactoryUtil.createJSONArray();
		for (Place place : places) {

			JSONObject feature = JSONFactoryUtil.createJSONObject();
			feature.put("type", "Feature");

			JSONObject properties = JSONFactoryUtil.createJSONObject();
			properties.put("name", place.getAlias(Locale.FRANCE));
			properties.put("address", place.getAddressStreet() + " " + place.getAddressComplement() + " "
					+ place.getAddressZipCode() + " " + place.getCity(Locale.FRANCE));
			properties.put("visual", place.getImageURL());
			// récupère l'url de détail du poi
			Group group = GroupLocalServiceUtil.fetchGroup(groupId);
			if (group == null) {
				group = GroupLocalServiceUtil.fetchFriendlyURLGroup(PortalUtil.getDefaultCompanyId(), "/strasbourg.eu");
			}
			if (group != null) {
				String url = "";
				String virtualHostName = group.getPublicLayoutSet().getVirtualHostname();
				if (virtualHostName.isEmpty()) {
					url = "/web" + group.getFriendlyURL() + "/";
				} else {
					url = "https://" + virtualHostName + "/";
				}
				url += "lieu/-/entity/sig/" + place.getSIGid();
				properties.put("url", url);
			}

			// gestion des doublons
			properties.put("sigId", place.getSIGid());

			// bouton favoris
			properties.put("type", FavoriteLocalServiceUtil.getFavoriteTypeByClass(place.getModelClassName()));
			properties.put("id", place.getPlaceId());

			// Horaires
			if (place.hasScheduleTable()) {
				// récupère les horaires en cours
				GregorianCalendar now = new GregorianCalendar();
				List<PlaceSchedule> currentSchedules = place.getPlaceSchedule(now, Locale.FRENCH);
				if (currentSchedules.size() > 0) {
					PlaceSchedule currentSchedule = currentSchedules.get(0);
					String schedule = "";
					String opened = "";
					if (currentSchedule.isAlwaysOpen()) {
						schedule = "7j/7, 24h/24";
						opened = "Ouvert";
					} else if (place.isOpenNow()) {
						opened = "Ouvert";
						for (Pair<LocalTime, LocalTime> openingTime : currentSchedule.getOpeningTimes()) {
							if (schedule.length() > 0) {
								schedule += "<br>";
							}
							String startString = openingTime.getFirst().format(DateTimeFormatter.ofPattern("HH'h'mm"));
							String endString = openingTime.getSecond().format(DateTimeFormatter.ofPattern("HH'h'mm"));
							schedule += startString + " - " + endString;
						}
					} else {
						opened = "Ferm&eacute;";
						// on récupère le prochain horaire d'ouverture
						PlaceSchedule nextOpening = place.getNextScheduleOpening(now, 2, Locale.FRENCH);
						if (nextOpening == null) {
							opened = "Ferm&eacute; en ce moment";
							schedule += "";
						} else {
							Pair<LocalTime, LocalTime> openingTime = nextOpening.getOpeningTimes().get(0);
							String startString = openingTime.getFirst().format(DateTimeFormatter.ofPattern("HH'h'mm"));
							String endString = openingTime.getSecond().format(DateTimeFormatter.ofPattern("HH'h'mm"));
							schedule += "R&eacute;ouverture ";
							int diff = nextOpening.getStartDate().compareTo(now.getTime());
							if (diff > 0) {
								now.add(GregorianCalendar.DAY_OF_YEAR, 1);
								if (nextOpening.getStartDate().compareTo(now.getTime()) == 0) {
									schedule += "demain ";
								} else {
									schedule += "apr&egrave;s-demain ";
								}
							}
							schedule += "&agrave; " + startString;
							if (diff == 0) {
								schedule += " jusqu'&agrave; " + endString;
							}
						}
					}
					properties.put("opened", opened);
					properties.put("schedules", schedule);
				}
			}

			// Icône (on le prend dans la catégorie type de lieu)
			AssetCategory category = null;
			List<AssetCategory> categories = place.getTypes();
			if (!categories.isEmpty()) {
				category = categories.get(0);
			}

			String[] icons = null;
			if (category != null) {
				icons = category.getDescription(Locale.FRANCE).split(";");
			}
			String icon = "";
			// vérifi si le lieu dispose d'un horaire et s'il est fermé
			if (place.hasScheduleTable() && !place.isOpenNow() && icons.length > 1) {
				icon = icons[1];
			} else {
				if (icons.length > 0) {
					icon = icons[0];

				}
			}
			properties.put("icon", icon);

			// Temps réel
			if (place.getRTEnabled()) { // (place.isEnabled()) {
				OccupationState occupation = place.getRealTime();
				String frequentation = "";
				String color = occupation.getCssClass();
				if (place.isSwimmingPool()) {
					frequentation = occupation.getOccupation();
				} else if (place.isMairie()) {
					frequentation = occupation.getOccupation();
				} else {
					frequentation = occupation.getAvailable();
				}
				JSONObject amountProperty = JSONFactoryUtil.createJSONObject();
				amountProperty.put("frequentation", frequentation);
				amountProperty.put("color", color);
				properties.put("amount", amountProperty);
			}

			feature.put("properties", properties);

			JSONObject geometry = JSONFactoryUtil.createJSONObject();
			geometry.put("type", "Point");
			JSONArray coordinates = JSONFactoryUtil.createJSONArray();
			coordinates.put(Float.valueOf(place.getMercatorX()));
			coordinates.put(Float.valueOf(place.getMercatorY()));
			geometry.put("coordinates", coordinates);
			feature.put("geometry", geometry);

			features.put(feature);
		}

		for (Event event : events) {

			JSONObject feature = JSONFactoryUtil.createJSONObject();
			feature.put("type", "Feature");

			JSONObject properties = JSONFactoryUtil.createJSONObject();
			properties.put("name", event.getTitle(Locale.FRANCE));
			properties.put("address", event.getPlaceAlias(Locale.FRANCE) + " " + event.getPlaceAddress(Locale.FRANCE)
					+ " " + event.getPlaceZipCode() + " " + event.getPlaceCity(Locale.FRANCE));
			properties.put("visual", event.getImageURL());
			// récupère l'url de détail du poi
			Group group = GroupLocalServiceUtil.fetchGroup(groupId);
			if (group == null) {
				group = GroupLocalServiceUtil.fetchFriendlyURLGroup(PortalUtil.getDefaultCompanyId(), "/strasbourg.eu");
			}
			if (group != null) {
				String url = "";
				String virtualHostName = group.getPublicLayoutSet().getVirtualHostname();
				if (virtualHostName.isEmpty()) {
					url = "/web" + group.getFriendlyURL() + "/";
				} else {
					url = "https://" + virtualHostName + "/";
				}
				url += "evenement/-/entity/id/" + event.getEventId();
				properties.put("url", url);
			}
			properties.put("sigId", event.getPlaceSIGId() + "_" + event.getEventId());

			// pour les favoris
			properties.put("type", FavoriteLocalServiceUtil.getFavoriteTypeByClass(event.getModelClassName()));
			properties.put("id", event.getPlaceId());

			// Prochaine date
			if (event.getFirstStartDate() != null) {
				String opened = "Prochaines dates";
				String schedule = "";
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				if (event.getFirstStartDate().equals(event.getLastEndDate())) {
					schedule = "Le " + sdf.format(event.getFirstStartDate());
				} else {
					if (event.getFirstStartDate().compareTo(new Date()) <= 0) {
						schedule = "Du" + sdf.format(event.getFirstStartDate()) + " au "
								+ sdf.format(event.getLastEndDate());
					} else {
						schedule = "Du" + sdf.format(event.getFirstStartDate()) + " au "
								+ sdf.format(event.getLastEndDate());
					}
				}
				properties.put("opened", opened);
				properties.put("schedules", schedule);
			}

			Place place = PlaceLocalServiceUtil.fetchPlace(event.getPlaceId());

			// Icône (on le prend dans la catégorie type agenda)
			AssetCategory category = null;
			List<AssetCategory> categories = event.getTypes();
			if (!categories.isEmpty()) {
				category = categories.get(0);
			} else {
				categories = place.getTypes();
				if (!categories.isEmpty()) {
					category = categories.get(0);
				}
			}
			String[] icons = null;
			if (category != null) {
				icons = category.getDescription(Locale.FRANCE).split(";");
			}
			String icon = "";
			// vérifi si le lieu dispose d'un horaire et s'il est fermé
			if (place != null) {
				if (place.hasScheduleTable() && !place.isOpenNow() && icons.length > 1) {
					icon = icons[1];
				} else {
					if (icons.length > 0) {
						icon = icons[0];

					}
				}
			}
			properties.put("icon", icon);

			feature.put("properties", properties);

			JSONObject geometry = JSONFactoryUtil.createJSONObject();
			geometry.put("type", "Point");
			JSONArray coordinates = JSONFactoryUtil.createJSONArray();
			// récupère le marker du lieu ou se déroule l'évènement
			if (place != null) {
				coordinates.put(Float.valueOf(place.getMercatorX()));
				coordinates.put(Float.valueOf(place.getMercatorY()));
			} else {
				// Si c'est un lieu manuel on récupère ses coordonnées
				String address = event.getPlaceAddress(Locale.FRANCE) + " " + event.getPlaceZipCode() + " "
						+ event.getPlaceCity(Locale.FRANCE);
				coordinates = getCoordinateForAddress(address);
			}
			if (coordinates != null) {
				geometry.put("coordinates", coordinates);
				feature.put("geometry", geometry);
			}

			features.put(feature);
		}
		geoJSON.put("features", features);

		return geoJSON;
	}

	/**
	 * Retourne les coordonnées d'une adresse en JSon
	 */
	private static JSONArray getCoordinateForAddress(String address) {
		JSONArray coordinates = null;
		try {
			String urlSearch = StrasbourgPropsUtil.getAdictBaseURL();
			String url = urlSearch + HtmlUtil.escapeURL(address);
			JSONObject addresses = JSONHelper.readJsonFromURL(url);
			JSONArray features = addresses.getJSONArray("features");
			if (features.length() > 0) {
				JSONObject geometry = features.getJSONObject(0).getJSONObject("geometry");
				coordinates = geometry.getJSONArray("coordinates");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return coordinates;
	}

}
