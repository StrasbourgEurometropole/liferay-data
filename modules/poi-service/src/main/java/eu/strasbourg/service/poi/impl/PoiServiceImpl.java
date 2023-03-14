package eu.strasbourg.service.poi.impl;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.asset.kernel.service.AssetCategoryLocalServiceUtil;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
import com.liferay.asset.kernel.service.AssetVocabularyLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.service.ClassNameLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import eu.strasbourg.service.agenda.model.Event;
import eu.strasbourg.service.agenda.service.EventLocalService;
import eu.strasbourg.service.agenda.service.EventLocalServiceUtil;
import eu.strasbourg.service.favorite.model.Favorite;
import eu.strasbourg.service.favorite.model.FavoriteType;
import eu.strasbourg.service.favorite.service.FavoriteLocalServiceUtil;
import eu.strasbourg.service.gtfs.model.Arret;
import eu.strasbourg.service.gtfs.service.ArretLocalService;
import eu.strasbourg.service.gtfs.service.ArretLocalServiceUtil;
import eu.strasbourg.service.interest.model.Interest;
import eu.strasbourg.service.interest.service.InterestLocalServiceUtil;
import eu.strasbourg.service.place.model.Place;
import eu.strasbourg.service.place.service.PlaceLocalService;
import eu.strasbourg.service.place.service.PlaceLocalServiceUtil;
import eu.strasbourg.service.poi.PoiService;
import eu.strasbourg.utils.SearchHelper;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Benjamin Bini
 */
@Component(immediate = true, property = {}, service = PoiService.class)
public class PoiServiceImpl implements PoiService {

	//AngelTODO à réintégrer un fois que la gestion du territoire et des coordonnées de tous les events physiques sans exception sera faite
	/*public int getPoisCategoryCount(long idCategory, String prefilters, String tags, long groupId, String classNames,
									boolean dateField, String fromDate, String toDate, String localeId, long globalGroupId) {

		return (int) getCount(-1, idCategory, prefilters, tags, groupId,
				classNames, dateField, fromDate, toDate, localeId, globalGroupId);
	}

	public int getPoisInterestCount(long idInterest, long groupId, String classNames, String localeId, long globalGroupId) {

		return (int) getCount(idInterest, -1, "", "", groupId,
				classNames, true, "", "", localeId, globalGroupId);
	}

	public int getFavoritesPoisCount(String userId, long groupId, String classNames) {
		int count = 0;

		// récupère les favoris de l'uilisateur
		List<Favorite> favorites = FavoriteLocalServiceUtil.getByPublikUser(userId);
		if (classNames.equals("all")) {
			count += favorites.stream().filter(f -> f.getTypeId() == FavoriteType.PLACE.getId() || f.getTypeId() == FavoriteType.ARRET.getId())
					.collect(Collectors.toList()).size();
			List<Favorite> eventsfavorites = favorites.stream().filter(f -> f.getTypeId() == FavoriteType.EVENT.getId())
					.collect(Collectors.toList());
			for (Favorite favorite : eventsfavorites) {
				Event event = EventLocalServiceUtil.fetchEvent(favorite.getEntityId());
				if (event != null && event.getNextOpenDate().isEqual(LocalDate.now())) {
					count++;
					;
				}
			}
		} else {
			if (classNames.contains(Place.class.getName()))
				count += favorites.stream().filter(f -> f.getTypeId() == FavoriteType.PLACE.getId())
						.collect(Collectors.toList()).size();
			if (classNames.contains(Arret.class.getName()))
				count += favorites.stream().filter(f -> f.getTypeId() == FavoriteType.ARRET.getId())
						.collect(Collectors.toList()).size();
			if (classNames.contains(Event.class.getName())) {
				List<Favorite> eventsfavorites = favorites.stream()
						.filter(f -> f.getTypeId() == FavoriteType.EVENT.getId()).collect(Collectors.toList());
				for (Favorite favorite : eventsfavorites) {
					Event event = EventLocalServiceUtil.fetchEvent(favorite.getEntityId());
					if (event != null && event.getNextOpenDate().isEqual(LocalDate.now())) {
						count++;
						;
					}
				}
			}
		}
		return count;
	}*/

	public JSONObject getPois(String idInterestsString, String idCategoriesString, String vocabulariesEmptyIds,
							  String prefiltersString, String tagsString, long groupId, String classNames,
							  boolean dateField, String fromDate, String toDate, String localeId, long globalGroupId) {
		JSONObject geoJson = null;

		// Recherche
		List<Place> places = new ArrayList<Place>();
		if (classNames.equals("all") || classNames.contains(Place.class.getName())) {
			boolean vocabularies=isVocabularies(Place.class.getName(),groupId,globalGroupId,vocabulariesEmptyIds);
			if(!vocabularies)
				_log.debug("Pas de lieu à afficher car il y a des vocabulaires les concernant qui n'ont aucune catégorie cochée ");
			else{
				// récupère les lieux des catégories et centres d'intérêt
				long classNameId = ClassNameLocalServiceUtil.getClassName(Place.class.getName()).getClassNameId();
				List<Long[]> categories = getCategories(idInterestsString, idCategoriesString, classNameId);
				List<Long[]> prefilters = getprefilters(prefiltersString, classNameId);
				Document [] documents = getHit(categories, prefilters, tagsString, groupId, Place.class.getName(),
						false, fromDate, toDate, localeId, globalGroupId).getDocs();
				if (documents != null) {
					// récuperer les identifiants de places
					List<Long> idsplaceEntries= getClassPkFromAssetEntryByHits(documents);
					places=_placeLocalService.findByIds(idsplaceEntries);
				}
			}
		}
		List<Event> events = new ArrayList<Event>();
		if (classNames.equals("all") || classNames.contains(Event.class.getName())) {
			boolean vocabularies=isVocabularies(Event.class.getName(),groupId,globalGroupId,vocabulariesEmptyIds);

			if(!vocabularies)
				_log.debug("Pas d'événement à afficher car il y a des vocabulaires les concernant qui n'ont aucune catégorie cochée ");
			else {
				// récupère les évènements des catégories et centres d'intérêt
				long classNameId = ClassNameLocalServiceUtil.getClassName(Event.class.getName()).getClassNameId();
				List<Long[]> categories = getCategories(idInterestsString, idCategoriesString, classNameId);
				List<Long[]> prefilters = getprefilters(prefiltersString, classNameId);
				Document [] documents = getHit(categories, prefilters, tagsString, groupId, Event.class.getName(),
						dateField, fromDate, toDate, localeId, globalGroupId).getDocs();

				if (documents != null) {
					List<Long> idsEventEntries= getClassPkFromAssetEntryByHits(documents);
					events=_eventLocalService.findByids(idsEventEntries);
				}
			}
		}

		// récupère les arrêts
		List<Arret> arrets = new ArrayList<Arret>();
		if (classNames.equals("all") || classNames.contains(Arret.class.getName())) {
			boolean vocabularies=isVocabularies(Arret.class.getName(),groupId,globalGroupId,vocabulariesEmptyIds);
			if(!vocabularies)
				_log.debug("Pas d'arrêt à afficher car il y a des vocabulaires les concernant qui n'ont aucune catégorie cochée ");
			else {
				// récupère les arrets des catégories et centres d'intérêt
				long classNameId = ClassNameLocalServiceUtil.getClassName(Arret.class.getName()).getClassNameId();
				List<Long[]> categories = getCategories(idInterestsString, idCategoriesString, classNameId);
				List<Long[]> prefilters = getprefilters(prefiltersString, classNameId);
				Document [] documents = getHit(categories, prefilters, tagsString, groupId, Arret.class.getName(),
						false, fromDate, toDate, localeId, globalGroupId).getDocs();

				if (documents != null) {
					List<Long> idsArretEntries= getClassPkFromAssetEntryByHits(documents);
					arrets=_arretLocalService.findByIds(idsArretEntries);
				}
			}
		}
		// récupère le fichier geoJson
		try {
			long startTime = System.nanoTime();
			geoJson = getGeoJSON(places, events, arrets, groupId, LocaleUtil.fromLanguageId(localeId));
			long endTime = System.nanoTime();
			long duration = (endTime - startTime) / 1_000_000;
			_log.debug("getGeoJSON : " + duration + "ms (" + geoJson.getJSONArray("features").length() + " items)");
		} catch (JSONException e) {
			_log.error(e.getMessage() + " : places -> " + places + ", events -> " + events +
					", arrets -> " + arrets + ", groupId " + groupId + ", localeId -> " + localeId);
		}
		return geoJson;
	}

	public JSONObject getFavoritesPois(String userId, long groupId, String classNames, String localeId) {
		JSONObject geoJSON = JSONFactoryUtil.createJSONObject();
		geoJSON.put("type", "FeatureCollection");
		Locale locale = LocaleUtil.fromLanguageId(localeId);

		// récupère les favoris de l'uilisateur
		List<Favorite> favorites = FavoriteLocalServiceUtil.getByPublikUser(userId);
		if (favorites != null) {
			JSONArray features = JSONFactoryUtil.createJSONArray();
			if (classNames.equals("all") || classNames.contains(Place.class.getName())) {
				Stream<Favorite> placeFavorites = favorites.stream()
						.filter(f -> f.getTypeId() == FavoriteType.PLACE.getId());
				if (placeFavorites != null) {
					for (Favorite favorite : placeFavorites.collect(Collectors.toList())) {
						Place place = PlaceLocalServiceUtil.fetchPlace(favorite.getEntityId());
						if (place != null) {
							features.put(place.getGeoJSON(groupId,locale));
						}
					}
				}
			}
			if (classNames.equals("all") || classNames.contains(Arret.class.getName())) {
				Stream<Favorite> arretFavorites = favorites.stream()
						.filter(f -> f.getTypeId() == FavoriteType.ARRET.getId());
				if (arretFavorites != null) {
					for (Favorite favorite : arretFavorites.collect(Collectors.toList())) {
						Arret arret = ArretLocalServiceUtil.fetchArret(favorite.getEntityId());
						if (arret != null) {
							features.put(arret.getGeoJSON(groupId,locale));
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
						if (event != null && event.getNextOpenDate().isEqual(LocalDate.now())) {
							// on ne garde que les évènements du jour
							features.put(event.getGeoJSON(groupId,locale));
						}
					}
				}
			}
			geoJSON.put("features", features);
		}
		return geoJSON;
	}






	//TODO à réintégrer un fois que la gestion du territoire et des coordonnées de tous les events physiques sans exception sera faite
	/*private long getCount(long idInterest, long idCategory, String prefiltersString, String tagsString, long groupId,
						  String classNames, boolean dateField, String startDate, String endDate, String localeId, long globalGroupId) {

		SearchContext searchContext = new SearchContext();
		searchContext.setCompanyId(PortalUtil.getDefaultCompanyId());

		LocalDate fromDate = LocalDate.now();
		LocalDate toDate = LocalDate.now();
		if(dateField) {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/M/yyyy");
			fromDate = LocalDate.parse(startDate, dtf);
			toDate = LocalDate.parse(endDate, dtf);
		}

		// récupère la catégorie ainsi que les catégories enfants de la
		// catégorie
		List<Long[]> categories = new ArrayList<>();
		if (idCategory != -1) {
			List<Long> filterCategories = new ArrayList<>();
			filterCategories.add(idCategory);
			// récupère les catégories enfants
			List<AssetCategory> childsCategories = AssetCategoryLocalServiceUtil
					.getChildCategories(idCategory);
			if (!childsCategories.isEmpty()) {
				filterCategories.addAll(childsCategories.stream().map(c -> c.getCategoryId()).collect(Collectors.toList()));
			}
			Long[] categoriesIdsForVocabulary = new Long[filterCategories.size()];
			filterCategories.toArray(categoriesIdsForVocabulary);
			categories.add(categoriesIdsForVocabulary);
		}

		// récupère les catégories ainsi que les catégories enfants du centre
		// d'intérêts
		if (idInterest != -1) {
			List<AssetCategory> filterCategories = new ArrayList<>();
			Interest interest = InterestLocalServiceUtil.fetchInterest(idInterest);
			List<AssetCategory> categoriesInterest = interest.getCategories();
			filterCategories.addAll(categoriesInterest);
			for (AssetCategory assetCategory : categoriesInterest) {
				// récupère les catégories enfants
				List<AssetCategory> chilsCategories = AssetCategoryLocalServiceUtil
						.getChildCategories(assetCategory.getCategoryId());
				if (!chilsCategories.isEmpty()) {
					filterCategories.addAll(chilsCategories);
				}
			}
			Long[] interestsIdsForVocabulary = new Long[filterCategories.size()];
			filterCategories.stream().map(c -> c.getCategoryId()).collect(Collectors.toList()).toArray(interestsIdsForVocabulary);
			categories.add(interestsIdsForVocabulary);
		}

		// préfiltres
		List<Long[]> prefilters = new ArrayList<>();
		if(Validator.isNotNull(prefiltersString)) {
			for (String prefilterCategoriesIdsGroupByVocabulary : prefiltersString.split(";")) {
				Long[] prefilterCategoriesIdsForVocabulary = ArrayUtil
						.toLongArray(StringUtil.split(prefilterCategoriesIdsGroupByVocabulary, ",", 0));
				prefilters.add(prefilterCategoriesIdsForVocabulary);
			}
		}

		// tags
		String[] tags = null;
		if(Validator.isNotNull(tagsString))
			tags = StringUtil.split(tagsString);

		// Locale
		Locale locale = LocaleUtil.fromLanguageId(localeId);


		// Recherche
		long count = SearchHelper.getGlobalSearchCount(searchContext, classNames.split(","), groupId, globalGroupId,
				true, null, dateField, "dates_Number_sortable", fromDate, toDate, categories,
				prefilters, tags, false, locale);

		return count;
	}*/

	private List<Long[]> getCategories(String idInterestsString, String idCategoriesString, long classNameId) {

		// récupère les catégories ainsi que les catégories enfants des
		// catégories des vocabulaires
		// les catégories sont regoupées par vocabulaire
		List<Long[]> categories = new ArrayList<>();
		if (Validator.isNotNull(idCategoriesString)) {
			List<Long> filterCategories = new ArrayList<>();
			long oldLinkedVocabularyId = -1;
			for (String categoryId : idCategoriesString.split(",")) {
				if(Validator.isNotNull(categoryId)) {
					AssetCategory category = AssetCategoryLocalServiceUtil.fetchCategory(Long.parseLong(categoryId));
					if(Validator.isNotNull(category)) {
						AssetVocabulary vocabulaire = AssetVocabularyLocalServiceUtil.fetchAssetVocabulary(category.getVocabularyId());
						//on vérifie que le vocabulaire de la catégorie est liée au type d'entité
						boolean isLinked = false;
						long[] classNameIds = vocabulaire.getSelectedClassNameIds();
						for(long id : classNameIds) {
							if (id == classNameId || id == 0){
								isLinked = true;
								break;
							}
						}
						if(isLinked) {
							if(oldLinkedVocabularyId != vocabulaire.getVocabularyId()){
								if(oldLinkedVocabularyId != -1) {
									Long[] categoriesIdsForVocabulary = new Long[filterCategories.size()];
									filterCategories.toArray(categoriesIdsForVocabulary);
									categories.add(categoriesIdsForVocabulary);
								}
								oldLinkedVocabularyId = vocabulaire.getVocabularyId();
								filterCategories = new ArrayList<>();
							}
						}
						filterCategories.add(Long.valueOf(categoryId));
						// récupère les catégories enfants
						List<AssetCategory> childsCategories = AssetCategoryLocalServiceUtil
								.getChildCategories(Long.parseLong(categoryId));
						if (!childsCategories.isEmpty()) {
							filterCategories.addAll(childsCategories.stream().map(c -> c.getCategoryId()).collect(Collectors.toList()));
						}
					}
				}
			}
			if(!filterCategories.isEmpty()) {
				Long[] categoriesIdsForVocabulary = new Long[filterCategories.size()];
				filterCategories.toArray(categoriesIdsForVocabulary);
				categories.add(categoriesIdsForVocabulary);
			}
		}

		// récupère les catégories ainsi que les catégories enfants des centres
		// d'intérêts
		if (Validator.isNotNull(idInterestsString)) {
			List<AssetCategory> filterCategories = new ArrayList<>();
			for (String interestsId : idInterestsString.split(",")) {
				Interest interest = InterestLocalServiceUtil.fetchInterest(Long.parseLong(interestsId));
				List<AssetCategory> categoriesInterest = interest.getCategories();
				filterCategories.addAll(categoriesInterest);
				for (AssetCategory assetCategory : categoriesInterest) {
					// récupère les catégories enfants
					List<AssetCategory> childsCategories = AssetCategoryLocalServiceUtil
							.getChildCategories(assetCategory.getCategoryId());
					if (!childsCategories.isEmpty()) {
						filterCategories.addAll(childsCategories);
					}
				}
			}
			Long[] interestsIdsForVocabulary = new Long[filterCategories.size()];
			filterCategories.stream().map(c -> c.getCategoryId()).collect(Collectors.toList()).toArray(interestsIdsForVocabulary);
			categories.add(interestsIdsForVocabulary);
		}

		return categories;
	}

	private List<Long[]> getprefilters(String idPrefiltersString, long classNameId) {

		// récupère les catégories ainsi que les catégories enfants des
		// préfiltres des vocabulaires
		// les catégories sont regoupées par vocabulaire
		List<Long[]> prefilters = new ArrayList<>();
		if (Validator.isNotNull(idPrefiltersString)) {
			List<Long> prefilterCategories = new ArrayList<>();
			long oldLinkedVocabularyId = -1;
			for (String prefilterCategoryId : idPrefiltersString.split(",")) {
				if(Validator.isNotNull(prefilterCategoryId)) {
					AssetCategory category = AssetCategoryLocalServiceUtil.fetchCategory(Long.parseLong(prefilterCategoryId));
					if(Validator.isNotNull(category)) {
						AssetVocabulary vocabulaire = AssetVocabularyLocalServiceUtil.fetchAssetVocabulary(category.getVocabularyId());
						//on vérifie que le vocabulaire de la catégorie est liée au type d'entité
						boolean isLinked = false;
						long[] classNameIds = vocabulaire.getSelectedClassNameIds();
						for(long id : classNameIds) {
							if (id == classNameId || id == 0){
								isLinked = true;
								break;
							}
						}
						if(isLinked) {
							if(oldLinkedVocabularyId != vocabulaire.getVocabularyId()){
								if(oldLinkedVocabularyId != -1) {
									Long[] prefiltercategoriesIdsForVocabulary = new Long[prefilterCategories.size()];
									prefilterCategories.toArray(prefiltercategoriesIdsForVocabulary);
									prefilters.add(prefiltercategoriesIdsForVocabulary);
								}
								oldLinkedVocabularyId = vocabulaire.getVocabularyId();
								prefilterCategories = new ArrayList<>();
							}
							prefilterCategories.add(Long.valueOf(prefilterCategoryId));
							// récupère les catégories enfants
							List<AssetCategory> childsCategories = AssetCategoryLocalServiceUtil
									.getChildCategories(Long.parseLong(prefilterCategoryId));
							if (!childsCategories.isEmpty()) {
								prefilterCategories.addAll(childsCategories.stream().map(c -> c.getCategoryId()).collect(Collectors.toList()));
							}
						}
					}
				}
			}
			if(!prefilterCategories.isEmpty()) {
				Long[] categoriesIdsForVocabulary = new Long[prefilterCategories.size()];
				prefilterCategories.toArray(categoriesIdsForVocabulary);
				prefilters.add(categoriesIdsForVocabulary);
			}
		}

		return prefilters;
	}

	private Hits getHit(List<Long[]> categories, List<Long[]> prefilters, String tagsString, long groupId,
						String classNames, boolean dateField, String startDate, String endDate, String localeId, long globalGroupId) {

		SearchContext searchContext = new SearchContext();
		searchContext.setCompanyId(PortalUtil.getDefaultCompanyId());

		LocalDate fromDate = LocalDate.now();
		LocalDate toDate = LocalDate.now();
		if(dateField && Validator.isNotNull(startDate) && Validator.isNotNull(endDate)) {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("d/M/yyyy");
			fromDate = LocalDate.parse(startDate, dtf);
			toDate = LocalDate.parse(endDate, dtf);
		}

		// tags
		String[] tags = null;
		if(Validator.isNotNull(tagsString))
			tags = StringUtil.split(tagsString);

		// Locale
		Locale locale = LocaleUtil.fromLanguageId(localeId);


		// Recherche
		long startTime = System.nanoTime();
		Hits hits = SearchHelper.getGlobalSearchHits(searchContext, classNames.split(","), groupId, globalGroupId, true,
				null, dateField, "dates_Number_sortable", fromDate, toDate, categories, prefilters,
				tags, false, locale, -1, -1, "", false);
		long endTime = System.nanoTime();
		long duration = (endTime - startTime) / 1_000_000;
		_log.debug("(" + classNames + ") GetPOIs : " + duration + "ms (" + hits.getLength() + " items)");

		return hits;
	}

	static private JSONObject getGeoJSON(List<Place> places, List<Event> events, List<Arret> arrets, long groupId, Locale locale) throws JSONException {
		JSONObject geoJSON = JSONFactoryUtil.createJSONObject();
		geoJSON.put("type", "FeatureCollection");

		JSONArray features = JSONFactoryUtil.createJSONArray();
		for (Place place : places) {
			features.put(place.getGeoJSON(groupId,locale));
		}

		for (Event event : events) {
			features.put(event.getGeoJSON(groupId, locale));
		}

		for (Arret arret : arrets) {
			features.put(arret.getGeoJSON(groupId, locale));
		}
		geoJSON.put("features", features);

		return geoJSON;
	}

	/**
	 * interface des plce
	 */
	private PlaceLocalService _placeLocalService;

	@Reference(unbind = "-")
	protected void setPlaceLocalService(PlaceLocalService placeLocalService) {
		_placeLocalService = placeLocalService;
	}

	/**
	 * interface des participations
	 */
	private ArretLocalService _arretLocalService;

	@Reference(unbind = "-")
	protected void setArretLocalService(ArretLocalService arretLocalService) {
		_arretLocalService = arretLocalService;
	}

	/**
	 * interface des participations
	 */
	private EventLocalService _eventLocalService;

	@Reference(unbind = "-")
	protected void setEventLocalService(EventLocalService eventLocalService) {
		_eventLocalService = eventLocalService;
	}

	private static final Log _log = LogFactoryUtil.getLog(PoiServiceImpl.class.getName());

	/**
	 * Renvoit la liste des ClassPK from assetEntry
	 * @param documents
	 * @return
	 */
	private static List<Long> getClassPkFromAssetEntryByHits(Document []documents )
	{
		List<Long> assetEntries = new ArrayList<>();
		DynamicQuery assetEntrieDynamicQuery = AssetEntryLocalServiceUtil.dynamicQuery();
		//recupére la liste de classeNames from elastic search
		List<String> classNameEntries=Arrays.asList(documents).stream()
				.map(document -> GetterUtil.getString(document.get(Field.ENTRY_CLASS_NAME)))
				.collect(Collectors.toList());
		//recupére la liste de classPK from elastic search
		List<Long> classPkEntries= Arrays.asList(documents).stream()
				.map(document -> GetterUtil.getLong(document.get(Field.ENTRY_CLASS_PK)))
				.collect(Collectors.toList());
		// chercher classNameId from className
		List<Long> classNameIds=new ArrayList<>();
		classNameEntries.forEach(className->classNameIds.add(ClassNameLocalServiceUtil.getClassNameId(className)));
		// Si rien pour les clauses IN on renvoit une liste vide (sinon ça plante)
		if(classNameIds.isEmpty() || classPkEntries.isEmpty()) {
			return assetEntries;
		} else {
			assetEntrieDynamicQuery.add(PropertyFactoryUtil.forName("classNameId").in(classNameIds));
			assetEntrieDynamicQuery.add(PropertyFactoryUtil.forName("classPK").in(classPkEntries));
			assetEntrieDynamicQuery.setProjection(ProjectionFactoryUtil.property("classPK"));
			assetEntries = AssetEntryLocalServiceUtil.dynamicQuery(assetEntrieDynamicQuery);

			return assetEntries.stream().filter(assetEntry -> assetEntry!=null)
					.collect(Collectors.toList());
        }
	}
	private static boolean isVocabularies(String clazzName,long groupId,long globalGroupId, String vocabulariesEmptyIds){

		List <AssetVocabulary> vocabularies= AssetVocabularyLocalServiceUtil.getGroupsVocabularies(new long[]{groupId, globalGroupId}, clazzName);
		long countVocabularie = vocabularies.stream().filter(v -> vocabulariesEmptyIds.contains(""+v.getVocabularyId()))
						.map(AssetVocabulary::getVocabularyId).count();
		boolean result= countVocabularie==0?true:false;
		return result;
	}

}
