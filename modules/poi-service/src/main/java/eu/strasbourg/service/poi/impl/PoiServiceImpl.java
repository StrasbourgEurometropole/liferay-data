package eu.strasbourg.service.poi.impl;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.osgi.service.component.annotations.Component;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetEntry;
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
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import eu.strasbourg.service.favorite.model.Favorite;
import eu.strasbourg.service.favorite.model.FavoriteType;
import eu.strasbourg.service.favorite.service.FavoriteLocalServiceUtil;
import eu.strasbourg.service.interest.model.Interest;
import eu.strasbourg.service.interest.service.InterestLocalServiceUtil;
import eu.strasbourg.service.place.model.Place;
import eu.strasbourg.service.place.model.PlaceSchedule;
import eu.strasbourg.service.place.service.PlaceLocalServiceUtil;
import eu.strasbourg.service.poi.PoiService;
import eu.strasbourg.utils.OccupationState;
import eu.strasbourg.utils.models.Pair;

/**
 * @author Benjamin Bini
 */
@Component(immediate = true, property = {}, service = PoiService.class)
public class PoiServiceImpl implements PoiService {

	public JSONObject getPois(String idInterests, long groupId) {
		JSONObject geoJson = null;

		long globalGroupId = -1;
		// récupère les catégories des centres d'intérêts
		List<AssetCategory> categoriesCI = new ArrayList<AssetCategory>();
		for (String idInterest : idInterests.split(",")) {
			Interest interest = InterestLocalServiceUtil.fetchInterest(Long.parseLong(idInterest));
			categoriesCI.addAll(interest.getCategories());
			if (globalGroupId == -1) {
				globalGroupId = interest.getGroupId();
			}
		}

		// récupère les lieux des centres d'intérêt
		Long[] tabCategories = new Long[categoriesCI.size()];
		for (int i = 0; i < categoriesCI.size(); i++) {
			tabCategories[i] = categoriesCI.get(i).getCategoryId();
		}
		long startTime = 0, endTime = 0, duration = 0;

		startTime = System.nanoTime();
		List<Place> places = getPlaces(tabCategories, globalGroupId);
		endTime = System.nanoTime();
		duration = (endTime - startTime) / 1_000_000;
		System.out.println("GetPlaces : " + duration);

		// récupère le fichier geoJson
		try {
			startTime = System.nanoTime();
			geoJson = getGeoJSON(places, groupId);
			endTime = System.nanoTime();
			duration = (endTime - startTime) / 1_000_000;
			System.out.println("getGeoJSON : " + duration + "ms");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return geoJson;
	}

	private List<Place> getPlaces(Long[] categoryIds, long globalGroupId) {
		List<AssetEntry> entries = new ArrayList<AssetEntry>();
		for (Long categoryId : categoryIds) { 
			entries.addAll(AssetEntryLocalServiceUtil.getAssetCategoryAssetEntries(categoryId));
		}
		List<Long> classPks = entries.stream().map(AssetEntry::getClassPK).collect(Collectors.toList());
		Criterion idCriterion = RestrictionsFactoryUtil.in("placeId", classPks);
		Criterion statusCriterion = RestrictionsFactoryUtil.eq("status", WorkflowConstants.STATUS_APPROVED);
		DynamicQuery placeQuery = PlaceLocalServiceUtil.dynamicQuery().add(idCriterion).add(statusCriterion);
		return PlaceLocalServiceUtil.dynamicQuery(placeQuery);
		/*
		 * List<Place> places = new ArrayList<Place>();
		 * 
		 * // Search context ThemeDisplay td =
		 * ServiceContextThreadLocal.getServiceContext().getThemeDisplay();
		 * SearchContext searchContext =
		 * SearchContextFactory.getInstance(td.getRequest());
		 * 
		 * // ClassNames String[] classNames = new String[1]; classNames[0] =
		 * Place.class.getName();
		 * 
		 * // GlobalScope boolean globalScope = true;
		 * 
		 * // Catégories de la recherche utilisateur (non existantes pour ce //
		 * portlet) List<Long[]> categoriesRechercheIds = new
		 * ArrayList<Long[]>();
		 * 
		 * // Locale Locale locale = Locale.FRANCE;
		 * 
		 * // Recherche Hits hits =
		 * SearchHelper.getGlobalSearchHits(searchContext, classNames, 0,
		 * globalGroupId, globalScope, "", false, "", null, null,
		 * categoriesRechercheIds, prefilterCategoriesIds, null, true, locale,
		 * -1, -1, "", false);
		 * 
		 * // On renvoie la liste des lieux for (Document document :
		 * hits.getDocs()) { Place place = null; AssetEntry assetEntry =
		 * AssetEntryLocalServiceUtil.fetchEntry(Place.class.getName(),
		 * GetterUtil.getLong(document.get(Field.ENTRY_CLASS_PK))); place =
		 * PlaceLocalServiceUtil.fetchPlace(assetEntry.getClassPK()); if (place
		 * != null) { places.add(place); } } return places;
		 */
	}

	public JSONObject getFavoritesPois(String userId, long groupId) {
		JSONObject geoJson = null;

		// récupère les lieux favoris de l'uilisateur
		List<Favorite> favorites = FavoriteLocalServiceUtil.getByPublikUser(userId);
		List<Place> places = new ArrayList<Place>();
		if (favorites != null) {
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

		// récupère le fichier geoJson
		try {
			geoJson = getGeoJSON(places, groupId);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return geoJson;
	}

	static private JSONObject getGeoJSON(List<Place> places, long groupId) throws JSONException {
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
			if (group != null) {
				String url = "";
				String virtualHostName = group.getPublicLayoutSet().getVirtualHostname();
				if (virtualHostName.isEmpty()) {
					url = "/web" + group.getFriendlyURL() + "/";
				} else {
					url = "https://" + virtualHostName;
				}
				url += "lieu/-/entity/sig/" + place.getSIGid();
				properties.put("url", url);
			}
			properties.put("sigId", place.getSIGid());

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
					} else if (place.isOpenNow()) {
						opened = "Ouvert";
						for (Pair<LocalTime, LocalTime> openingTime : currentSchedule.getOpeningTimes()) {
							if (schedule.length() > 0) {
								schedule += "<br>";
							}
							String startString = openingTime.getFirst().format(DateTimeFormatter.ofPattern("HH'h'mm"));
							String endString = openingTime.getSecond().format(DateTimeFormatter.ofPattern("HH'h'mm"));
							schedule += "De " + startString + " &agrave; " + endString;
						}
					} else {
						opened = "Ferm&eacute";
					}
					properties.put("opened", opened);
					properties.put("schedules", schedule);
				}
			}
			
			// Icône
			AssetCategory category = null;
			List<AssetCategory> categories = place.getTypes();
			if (!categories.isEmpty()) {
				category = categories.get(0);
			}
			properties.put("icon", category.getDescription(Locale.FRANCE));
			
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
		geoJSON.put("features", features);

		return geoJSON;
	}

}
