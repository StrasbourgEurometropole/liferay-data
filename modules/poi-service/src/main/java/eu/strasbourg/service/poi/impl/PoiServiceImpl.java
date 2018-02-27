package eu.strasbourg.service.poi.impl;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.osgi.service.component.annotations.Component;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;

import eu.strasbourg.service.favorite.model.Favorite;
import eu.strasbourg.service.favorite.model.FavoriteType;
import eu.strasbourg.service.favorite.service.FavoriteLocalServiceUtil;
import eu.strasbourg.service.interest.model.Interest;
import eu.strasbourg.service.interest.service.InterestLocalServiceUtil;
import eu.strasbourg.service.place.model.Place;
import eu.strasbourg.service.place.model.PlaceSchedule;
import eu.strasbourg.service.place.service.PlaceLocalServiceUtil;
import eu.strasbourg.service.poi.PoiService;
import eu.strasbourg.utils.models.Pair;

/**
 * @author Benjamin Bini
 */
@Component(immediate = true, property = {}, service = PoiService.class)
public class PoiServiceImpl implements PoiService {

	public JSONObject getPois(String idInterests) {
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

		List<Place> places = getPlaces(tabCategories, globalGroupId);

		// récupère le fichier geoJson
		try {
			geoJson = getGeoJSON(places);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return geoJson;
	}

	private List<Place> getPlaces(Long[] categoryIds, long globalGroupId) {
		List<Place> places = new ArrayList<Place>();
		for (Long categoryId : categoryIds) {
			List<AssetEntry> entries = AssetEntryLocalServiceUtil.getAssetCategoryAssetEntries(categoryId);
			for (AssetEntry entry : entries) {
				if (entry == null) {
					continue;
				}
				Place place = PlaceLocalServiceUtil.fetchPlaceByUuidAndGroupId(entry.getClassUuid(), globalGroupId);
				if (place == null) {
					continue;
				}
				places.add(place);
			}
		}
		return places;
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

	public JSONObject getFavoritesPois(String userId) {
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
			geoJson = getGeoJSON(places);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return geoJson;
	}

	static private JSONObject getGeoJSON(List<Place> places) throws JSONException {
		JSONObject geoJSON = JSONFactoryUtil.createJSONObject();
		geoJSON.put("type", "FeatureCollection");

		JSONArray features = JSONFactoryUtil.createJSONArray();
		for (Place place : places) {

			JSONObject feature = JSONFactoryUtil.createJSONObject();
			feature.put("type", "Feature");

			JSONObject properties = JSONFactoryUtil.createJSONObject();
			properties.put("name", place.getAlias(Locale.FRANCE));
			properties.put("address", place.getAddressStreet());
			properties.put("visual", place.getImageURL());
			properties.put("sigId", place.getSIGid());
			if (!place.getPeriods().isEmpty()) {
				GregorianCalendar now = new GregorianCalendar();
				properties.put("isClosed", place.isClosed(now));
				// récupère les horaires en cours
				List<PlaceSchedule> currentSchedules = place.getPlaceSchedule(now, Locale.FRENCH);
				properties.put("currentSchedule", "");
				properties.put("nextSchedules", "");
			}
			if (false) { // (place.isEnabled()) {

				properties.put("icon", "TODO");
				/*
				 * OccupationState occupation = place.getRealTime();
				 * 
				 * int type = 2; if (place.isSwimmingPool()){ type = 1; } if
				 * (place.isMairie()){ type = 3; } properties.put("typePlace",
				 * type); properties.put("realTime", occupation);
				 */
			} else {
				// récupère la catégorie du lieu
				AssetCategory category = null;
				List<AssetCategory> categories = place.getTypes();
				if (!categories.isEmpty()) {
					category = categories.get(0);
				}
				properties.put("icon", category.getDescription(Locale.FRANCE));
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
