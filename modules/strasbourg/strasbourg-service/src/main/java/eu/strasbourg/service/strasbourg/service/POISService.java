package eu.strasbourg.service.strasbourg.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.http.HttpServletRequest;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchContextFactory;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.WebKeys;

import eu.strasbourg.service.favorite.model.Favorite;
import eu.strasbourg.service.favorite.model.FavoriteType;
import eu.strasbourg.service.favorite.service.FavoriteLocalServiceUtil;
import eu.strasbourg.service.interest.model.Interest;
import eu.strasbourg.service.interest.service.InterestLocalServiceUtil;
import eu.strasbourg.service.place.model.Place;
import eu.strasbourg.service.place.service.PlaceLocalServiceUtil;
import eu.strasbourg.utils.SearchHelper;

public class POISService {

	public static JSONObject getPois(String idInterests, HttpServletRequest request) {
		JSONObject geoJson = null;

		// récupère les catégories des centres d'intérêts
		List<AssetCategory> categoriesCI = new ArrayList<AssetCategory>();
		for (String idInterest : idInterests.split(",")) {
			Interest interest = InterestLocalServiceUtil.fetchInterest(Long.parseLong(idInterest));
			categoriesCI.addAll(interest.getCategories());
		}

		// récupère les lieux des centres d'intérêt
		List<Long[]> categoriePlaceIds = new ArrayList<Long[]>();
		Long[] tabCategories = new Long[categoriesCI.size()];
		for (int i = 0; i < categoriesCI.size(); i++) {
			tabCategories[i] = categoriesCI.get(i).getCategoryId();
		}
		if (tabCategories.length > 0) {
			categoriePlaceIds.add(tabCategories);
		}

		List<Place> places = getPlaces(categoriePlaceIds, request);

		// récupère le fichier geoJson
		try {
			geoJson = getGeoJSON(places);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return geoJson;
	}

	private static List<Place> getPlaces(List<Long[]> prefilterCategoriesIds, HttpServletRequest request) {
		ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
		List<Place> places = new ArrayList<Place>();

		// Search context
		SearchContext searchContext = SearchContextFactory.getInstance(request);

		// ClassNames
		String[] classNames = new String[1];
		classNames[0] = Place.class.getName();

		// GroupId
		Group group = GroupLocalServiceUtil.fetchFriendlyURLGroup(themeDisplay.getCompanyId(), "/strasbourg.eu");
		long groupId = group.getGroupId();

		// GlobalGroupId
		long globalGroupId = themeDisplay.getCompanyGroupId();

		// GlobalScope
		boolean globalScope = true;

		// Catégories de la recherche utilisateur (non existantes pour ce
		// portlet)
		List<Long[]> categoriesRechercheIds = new ArrayList<Long[]>();

		// Locale
		Locale locale = themeDisplay.getLocale();

		// Recherche
		Hits hits = SearchHelper.getGlobalSearchHits(searchContext, classNames, groupId, globalGroupId, globalScope, "",
				false, "", null, null, categoriesRechercheIds, prefilterCategoriesIds, null, true, locale, -1, -1, "",
				false);

		// On renvoie la liste des lieux
		for (Document document : hits.getDocs()) {
			Place place = null;
			AssetEntry assetEntry = AssetEntryLocalServiceUtil.fetchEntry(Place.class.getName(),
					GetterUtil.getLong(document.get(Field.ENTRY_CLASS_PK)));
			place = PlaceLocalServiceUtil.fetchPlace(assetEntry.getClassPK());
			if (place != null) {
				places.add(place);
			}
		}
		return places;
	}

	public static JSONObject getFavoritesPois(String userId) {
		JSONObject geoJson = null;

		// récupère les lieux favoris de l'uilisateur
		List<Favorite> favorites = FavoriteLocalServiceUtil.getByPublikUser(userId);
		List<Place> places = new ArrayList<Place>();
		if (favorites != null) {
			Stream<Favorite> placeFavorites = favorites.stream()
					.filter(f -> f.getTypeId() == FavoriteType.PLACE.getId());
			if (placeFavorites != null) {
				for (Favorite favorite : placeFavorites.collect(Collectors.toList())) {
					Place place = PlaceLocalServiceUtil.fetchPlace(favorite.getPrimaryKey());
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
		JSONObject featureCollection = JSONFactoryUtil.createJSONObject();
//		featureCollection.put("type", "featureCollection");
		JSONArray featureList = JSONFactoryUtil.createJSONArray();
		for (Place place : places) {
			// {"geometry": {"type": "Point", "coordinates": [X, Y]}
			JSONObject point = JSONFactoryUtil.createJSONObject();
			point.put("type", "Point");
			// construct a JSONArray from a string
			JSONArray coord = JSONFactoryUtil
					.createJSONArray("[" + place.getMercatorX() + "," + place.getMercatorY() + "]");
			point.put("coordinates", coord);
			JSONObject feature = JSONFactoryUtil.createJSONObject();
			feature.put("geometry", point);
			featureList.put(feature);
			featureCollection.put("features", featureList);
		}

		return featureCollection;
	}
	
}
