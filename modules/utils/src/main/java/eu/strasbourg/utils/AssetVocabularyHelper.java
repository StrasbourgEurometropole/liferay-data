package eu.strasbourg.utils;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.asset.kernel.service.AssetCategoryLocalServiceUtil;
import com.liferay.asset.kernel.service.AssetCategoryPropertyLocalServiceUtil;
import com.liferay.asset.kernel.service.AssetVocabularyLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.service.ClassNameLocalServiceUtil;
import com.liferay.portal.kernel.service.CompanyLocalServiceUtil;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import eu.strasbourg.utils.constants.VocabularyNames;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

/**
 * Classe Helper pour tout ce qui concerne les vocabulaires
 *
 */
public class AssetVocabularyHelper {

	public static final String FRANCE="France";

	/**
	 * Retourne la liste des vocabulaires rattachés à un type d'entité
	 */
	public static List<AssetVocabulary> getVocabulariesForAssetType(long groupId, long classNameId) {
		List<AssetVocabulary> vocabularies = AssetVocabularyLocalServiceUtil.getAssetVocabularies(-1, -1);
		List<AssetVocabulary> attachedVocabularies = new ArrayList<AssetVocabulary>();
		if (classNameId > 0) {
			for (AssetVocabulary vocabulary : vocabularies) {
				if (vocabulary.getGroupId() == groupId
						&& LongStream.of(vocabulary.getSelectedClassNameIds()).anyMatch(c -> c == classNameId)) {
					attachedVocabularies.add(vocabulary);
				}
			}
		}
		return attachedVocabularies;
	}

	/**
	 * Retourne la liste des vocabulaires obligatoires pour un type d'entité
	 */
	public static List<AssetVocabulary> getRequiredVocabulariesForAssetType(long groupId, long classNameId) {
		List<AssetVocabulary> vocabularies = AssetVocabularyLocalServiceUtil.getAssetVocabularies(-1, -1);
		List<AssetVocabulary> attachedVocabularies = new ArrayList<AssetVocabulary>();
		if (classNameId > 0) {
			for (AssetVocabulary vocabulary : vocabularies) {
				if (vocabulary.getGroupId() == groupId
						&& LongStream.of(vocabulary.getRequiredClassNameIds()).anyMatch(c -> c == classNameId)) {
					attachedVocabularies.add(vocabulary);
				}
			}
		}
		return attachedVocabularies;
	}

	/**
	 * Retourne le vocabulary rattaché à la classe donnée et qui posséde le
	 * nom donné
	 */
	public static AssetVocabulary getEntityVocabulary(String className, String vocabularyName, long groupId) {
		long classNameId = ClassNameLocalServiceUtil.getClassName(className).getClassNameId();
		List<AssetVocabulary> vocabularies = AssetVocabularyHelper.getVocabulariesForAssetType(groupId, classNameId);
		for (AssetVocabulary vocabulary : vocabularies) {
			if (StringHelper.compareIgnoringAccentuation(vocabulary.getName().toLowerCase(), vocabularyName)) {
				return vocabulary;
			}
		}
		return null;
	}

	/**
	 * Retourne le vocabulaire ayant le nom donné et faisant parti du groupe
	 * donné
	 */
	public static AssetVocabulary getVocabulary(String vocabularyName, long groupId) {
		List<AssetVocabulary> vocabularies = AssetVocabularyLocalServiceUtil.getAssetVocabularies(-1, -1);
		for (AssetVocabulary vocabulary : vocabularies) {
			if (StringHelper.compareIgnoringAccentuation(vocabulary.getName().toLowerCase(), vocabularyName)
					&& vocabulary.getGroupId() == groupId) {
				return vocabulary;
			}
		}
		return null;
	}

	/**
	 * Retourne les quartiers d'une ville
	 * @param cityName Le nom de la ville
	 * @return La liste des catégories quartier disponibles pour la ville demandée
	 */
	public static List<AssetCategory> getAllDistrictsFromCity(String cityName) {

		List<AssetCategory> cityDistricts = new ArrayList<AssetCategory>();

		try {
			// Récupération du vocabulaire des teritoires
			AssetVocabulary territoryVocabulary = getGlobalVocabulary(VocabularyNames.TERRITORY);

			List<AssetCategory> cities = territoryVocabulary.getCategories();

			// Parcours des villes
			for (AssetCategory city : cities) {
				if (city.getTitle(Locale.FRENCH).equals(cityName)) {
					cityDistricts = getChild(city.getCategoryId());
				}
			}

		} catch (PortalException e) {
			return null;
		}

		return cityDistricts;
	}

	public static boolean isAllDistrictOfCity(List<AssetCategory> listDistrictSizeToCompare, String city){
		List<AssetCategory> districts = getAllDistrictsFromCity(city);
		Boolean isAllDistrict = true;
		if (Validator.isNotNull(districts)){
			for (AssetCategory district : districts) {
				if (!listDistrictSizeToCompare.contains(district)){
					isAllDistrict = false;
				}
			}
		}
		return isAllDistrict;
	}

	public static boolean isAllFrenchCity(int listCitySizeToCompare){
		AssetVocabulary territoryVocabulary = null;
		try {
			territoryVocabulary = getGlobalVocabulary(VocabularyNames.TERRITORY);
		} catch (PortalException ignored) {
		}
		assert territoryVocabulary != null;
		List<AssetCategory> territories = territoryVocabulary.getCategories();
		int index = 0;
		if (territories!=null&&!territories.isEmpty()){
			for (AssetCategory territory :territories) {
				try {
					if (territory.getAncestors().size()==1 && territory.getAncestors().get(0).getName().equals(FRANCE)){
						index++;
					}
				} catch (PortalException ignored){
				}
			}
		}
		return index == listCitySizeToCompare;
	}

	/**
	 * Retourne le vocabulaire ayant le nom donné et faisant parti du groupe
	 * donné
	 *
	 * @throws PortalException
	 */
	public static AssetVocabulary getGlobalVocabulary(String vocabularyName) throws PortalException {
		long companyId = PortalUtil.getDefaultCompanyId();
		long companyGroupId = CompanyLocalServiceUtil.getCompany(companyId).getGroupId();
		List<AssetVocabulary> vocabularies = AssetVocabularyLocalServiceUtil.getAssetVocabularies(-1, -1);
		for (AssetVocabulary vocabulary : vocabularies) {
			if (StringHelper.compareIgnoringAccentuation(vocabulary.getName().toLowerCase(), vocabularyName)
					&& vocabulary.getGroupId() == companyGroupId) {
				return vocabulary;
			}
		}
		return null;
	}

	/**
	 * Retourne la liste des catégories rattachées à un AssetEntry
	 */
	public static List<AssetCategory> getAssetEntryCategories(AssetEntry entry) {
		long[] categoryIds = entry.getCategoryIds();
		List<AssetCategory> categories = new ArrayList<AssetCategory>();
		for (long categoryId : categoryIds) {
			AssetCategory category = AssetCategoryLocalServiceUtil.fetchAssetCategory(categoryId);
			if (category != null) {
				categories.add(category);
			}
		}
		return categories;
	}

	/**
	 * Retourne la liste des catégories d'un vocabulaire spécifique rattachées
	 * à un AssetEntry
	 *
	 */
	static public List<AssetCategory> getAssetEntryCategoriesByVocabulary(AssetEntry entry, String vocabularyName) {
		List<AssetCategory> results = new ArrayList<AssetCategory>();
		List<AssetCategory> categories = AssetVocabularyHelper.getAssetEntryCategories(entry);
		for (AssetCategory category : categories) {
			AssetVocabulary vocabulary = AssetVocabularyLocalServiceUtil
					.fetchAssetVocabulary(category.getVocabularyId());
			if (vocabulary != null
					&& StringHelper.compareIgnoringAccentuation(vocabulary.getName().toLowerCase(), vocabularyName)) {
				results.add(category);
			}
		}
		return results;
	}

	/**
	 * Retourne la valeur d'une propriété d'une catégorie Retourne une chaîne
	 * vide si la propriété n'existe pas
	 */
	static public String getCategoryProperty(long categoryId, String key) {
		try {
			return AssetCategoryPropertyLocalServiceUtil.getCategoryProperty(categoryId, key).getValue();
		} catch (Exception e) {
			return "";
		}
	}

	/**
	 * Retourne la liste des catéogories enfants à une catégorie donnée. Si la
	 * catégorie est "live", on ajoute également les versions staging
	 */
	public static List<AssetCategory> getChild(long categoryId) {
		return AssetCategoryLocalServiceUtil.getChildCategories(categoryId);
	}

	/**
	 * Retourne la catégorie passée en paramètre avec ses parents
	 */
	public static List<AssetCategory> getCategoryWithAncestors(long categoryId) {
		List<AssetCategory> categories = new ArrayList<AssetCategory>();
		try {
			categories.add(AssetCategoryLocalServiceUtil.getCategory(categoryId));
		} catch (PortalException e) {
			return new ArrayList<AssetCategory>();
		}
		return getCategoriesWithAncestors(categories);
	}

	/**
	 * Retourne la catégorie passée en paramètre avec ses parents
	 */
	public static List<AssetCategory> getCategoryWithAncestors(AssetCategory category) {
		List<AssetCategory> categories = new ArrayList<AssetCategory>();
		categories.add(category);
		return getCategoriesWithAncestors(categories);
	}

	/**
	 * Retourne les catégories passées en paramètre avec leurs parents
	 */
	public static List<AssetCategory> getCategoriesWithAncestors(List<AssetCategory> categories) {
		List<AssetCategory> allCategories = new ArrayList<AssetCategory>();
		for (AssetCategory category : categories) {
			List<AssetCategory> ancestors;
			try {
				ancestors = category.getAncestors();
			} catch (PortalException e) {
				ancestors = new ArrayList<AssetCategory>();
			}
			allCategories.add(category);
			allCategories.addAll(ancestors);
		}
		return allCategories;
	}

	/**
	 * Retourne la catégorie passée en paramètre avec ses enfants
	 */
	public static List<AssetCategory> getCategoryWithChild(long categoryId) {
		List<AssetCategory> categories = new ArrayList<AssetCategory>();
		try {
			categories.add(AssetCategoryLocalServiceUtil.getCategory(categoryId));
		} catch (PortalException e) {
			return new ArrayList<AssetCategory>();
		}
		return getCategoriesWithChild(categories);
	}

	/**
	 * Retourne la catégorie passée en paramètre avec ses enfants
	 */
	public static List<AssetCategory> getCategoriesWithChild(AssetCategory category) {
		List<AssetCategory> categories = new ArrayList<AssetCategory>();
		categories.add(category);
		return getCategoriesWithChild(categories);
	}

	/**
	 * Retourne les catégorie passée en paramètre avec leurs enfants
	 */
	public static List<AssetCategory> getCategoriesWithChild(List<AssetCategory> categories) {
		List<AssetCategory> allCategories = new ArrayList<AssetCategory>();
		for (AssetCategory category : categories) {
			allCategories.add(category);
			allCategories.addAll(getChild(category.getCategoryId()));
		}
		return allCategories;
	}

	/**
	 * Retourne l'ensemble des catégories passées en paramètre et leur parents.
	 * Si la catégorie est "live", on ajoute également les versions staging
	 */
	public static List<AssetCategory> getFullHierarchyCategories(List<AssetCategory> categories) {
		List<AssetCategory> allCategories = new ArrayList<AssetCategory>();
		for (AssetCategory category : categories) {
			List<AssetCategory> ancestors;
			try {
				ancestors = category.getAncestors();
			} catch (PortalException e) {
				ancestors = new ArrayList<AssetCategory>();
			}
			allCategories.add(category);
			allCategories.addAll(ancestors);

			// Ajout des catégories staging
			Group group = GroupLocalServiceUtil.fetchGroup(category.getGroupId());
			if (group != null && group.getStagingGroup() != null) {
				AssetCategory stagingCategory = AssetCategoryLocalServiceUtil
						.fetchAssetCategoryByUuidAndGroupId(category.getUuid(), group.getStagingGroup().getGroupId());
				if (stagingCategory != null) {
					allCategories.add(stagingCategory);
				}
				for (AssetCategory ancestor : ancestors) {
					AssetCategory stagingAncestor = AssetCategoryLocalServiceUtil.fetchAssetCategoryByUuidAndGroupId(
							ancestor.getUuid(), group.getStagingGroup().getGroupId());
					if (stagingAncestor != null) {
						allCategories.add(stagingAncestor);
					}
				}
			}
		}
		return allCategories;
	}

	/**
	 * Renvoie l'ensemble des catégories passées en paramètres et leur parents
	 * sous forme d'array d'ids. Si la catégorie est "live", on ajoute également
	 * les versions staging
	 */
	public static long[] getFullHierarchyCategoriesIds(List<AssetCategory> categories) {
		List<AssetCategory> allCategories = getFullHierarchyCategories(categories);
		return ListUtil.toLongArray(allCategories, AssetCategory.CATEGORY_ID_ACCESSOR);
	}

	/**
	 * Retourne la 1ère catégorie trouvée avec le nom donné et faisant parti du groupe donné
	 */
	public static AssetCategory getCategory(String categoryName, long groupId) {
		List<AssetCategory> categories = AssetCategoryLocalServiceUtil.getAssetCategories(-1, -1);
		for (AssetCategory category : categories) {

			if (StringHelper.compareIgnoringAccentuation(category.getName().toLowerCase(), categoryName)
					&& category.getGroupId() == groupId) {
				return category;
			}
		}
		return null;
	}

	/**
	 * Retourne la category ayant la propriété "externalId" ou "SIGId".
	 *  Retourne null si aucune catégorie ne correspond à ces critères.
	 */
	public static AssetCategory getCategoryByExternalId(String externalId) {
		List<AssetCategory> categories = AssetCategoryLocalServiceUtil.getAssetCategories(-1, -1);
		for (AssetCategory category : categories) {
			String SIGIdProperty = AssetVocabularyHelper.getCategoryProperty(category.getCategoryId(), "SIG");
			if (SIGIdProperty.equals(externalId)) {
				return category;
			}
			String externalIdProperty = AssetVocabularyHelper.getCategoryProperty(category.getCategoryId(),
					"externalId");
			if (externalIdProperty.equals(externalId)) {
				return category;
			}
		}
		return null;
	}

	/**
	 * Retourne la category ayant le vocabulaire "vocabulary" et une propriété
	 * "externalId" ou "SIGId" ayant pour valeur "externalId". Retourne null si
	 * aucune catégorie ne correspond à ces critéres.
	 */
	public static AssetCategory getCategoryByExternalId(AssetVocabulary vocabulary, String externalId) {
		List<AssetCategory> categories = vocabulary.getCategories();
		for (AssetCategory category : categories) {

			String SIGIdProperty = AssetVocabularyHelper.getCategoryProperty(category.getCategoryId(), "SIG");
			if (SIGIdProperty.equals(externalId)) {
				return category;
			}
			String externalIdProperty = AssetVocabularyHelper.getCategoryProperty(category.getCategoryId(),
					"externalId");
			if (externalIdProperty.equals(externalId)) {
				return category;
			}
		}
		return null;
	}

	/**
	 * Ajoute une catégorie à un AssetEntry
	 * @return
	 */
	public static void addCategoryToAssetEntry(AssetCategory category, AssetEntry entry) {
		AssetCategoryLocalServiceUtil.addAssetEntryAssetCategory(
				entry.getEntryId(), category.getCategoryId());
	}

	/**
	 * Retire une catégorie à un AssetEntry
	 * @return
	 */
	public static void removeCategoryToAssetEntry(AssetCategory category, AssetEntry entry) {
		AssetCategoryLocalServiceUtil.deleteAssetEntryAssetCategory(
				entry.getEntryId(), category.getCategoryId());
	}

	/**
	 * Retourne les categorys ayant le vocabulaire "vocabulary" et une propriété
	 * "externalId" ou "SIGId" ayant pour valeur "externalId". Retourne une liste vide si
	 * aucune catégorie ne correspond à ces critères.
	 */
	public static List<AssetCategory> getCategoriesByExternalsId(AssetVocabulary vocabulary, List<String> externalsId) {
		List<AssetCategory> categories = vocabulary.getCategories();
		List<AssetCategory> categoriesResult = new ArrayList<AssetCategory>();

		for (String externalId : externalsId) {
			for (AssetCategory category : categories) {

				String SIGIdProperty = AssetVocabularyHelper.getCategoryProperty(category.getCategoryId(), "SIG");
				if (SIGIdProperty.equals(externalId)) {
					categoriesResult.add(category);
					break;
				}
				String externalIdProperty = AssetVocabularyHelper.getCategoryProperty(category.getCategoryId(),
						"externalId");
				if (externalIdProperty.equals(externalId)) {
					categoriesResult.add(category);
					break;
				}
			}
		}
		return categoriesResult;
	}


	/**
	 * Vérifie si la catégorie est une piscine
	 *
	 * @throws PortalException
	 */
	public static Boolean isSwimmingPool(AssetCategory category) throws PortalException {
		if (category.getName().equals("Piscines")) {
			return true;
		}
		// vérification des parents
		for (AssetCategory ancestor : category.getAncestors()) {
			if (ancestor.getName().equals("Piscines")) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Vérifie si la catégorie est un parking
	 *
	 * @throws PortalException
	 */
	public static Boolean isParking(AssetCategory category) throws PortalException {
		if (category.getName().equals("Parkings")) {
			return true;
		}
		// vérification des parents
		for (AssetCategory ancestor : category.getAncestors()) {
			if (ancestor.getName().equals("Parkings")) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Vérifie si la catégorie est une mairie
	 *
	 * @throws PortalException
	 */
	public static Boolean isMairie(AssetCategory category) throws PortalException {
		// TODO mairie de quartier et centre administratif uniquement ou mairie de l'eurométropole également ?
		if (category.getName().contains("Mairies")) {
			return true;
		}
		// vérification des parents
		for (AssetCategory ancestor : category.getAncestors()) {
			if (ancestor.getName().contains("Mairies")) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Vérifie si la catégorie est une patinoire
	 *
	 * @throws PortalException
	 */
	public static Boolean isPatinoire(AssetCategory category) throws PortalException {
		if (category.getName().contains("Patinoire")) {
			return true;
		}
		// vérification des parents
		for (AssetCategory ancestor : category.getAncestors()) {
			if (ancestor.getName().contains("Patinoire")) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Retourne la version JSON d'une liste de catégories
	 */
	static public JSONArray toJSON(List<AssetCategory> categories) {
		JSONArray jsonCategories = JSONFactoryUtil.createJSONArray();
		for (AssetCategory category : categories) {
			JSONObject jsonCategory = AssetVocabularyHelper.categoryToJSON(category);
			if (jsonCategory.length() > 0) {
				jsonCategories.put(jsonCategory);
			}
		}
		return jsonCategories;
	}

	/**
	 * Retourne la version JSON d'une liste d'un vocabulaire
	 */
	static public JSONArray toJSON(AssetVocabulary vocabulary) {
		return toJSON(vocabulary.getCategories());
	}

	/**
	 * Retourne l'ID externe d'une catégorie : propriété "externalId" ou "SIG"
	 */
	static public String getExternalId(AssetCategory category) {
		String externalId = "";
		if (category != null) {
			externalId = AssetVocabularyHelper.getCategoryProperty(category.getCategoryId(), "externalId");
			if (Validator.isNull(externalId)) {
				externalId = AssetVocabularyHelper.getCategoryProperty(category.getCategoryId(), "SIG");
			}
		}
		return externalId;
	}

	/**
	 * Retourne la version JSON d'une catégorie, ignore les catégories n'ayant pas d'externalId
	 */
	static public JSONObject categoryToJSON(AssetCategory category) {
		JSONObject jsonCategory = JSONFactoryUtil.createJSONObject();
		if (category != null) {
			// On exporte la propriété "externalId" de la catégorie, si elle
			// n'existe pas, la propriété "SIG", sinon, on renvoie un objet vide
			String externalId = AssetVocabularyHelper.getExternalId(category);
			if (Validator.isNull(externalId)) {
				return jsonCategory;
			}
			jsonCategory.put("id", externalId);
			jsonCategory.put("name", JSONHelper.getJSONFromI18nMap(category.getTitleMap()));
			try {
				int level = category.getAncestors().size();
				if (level > 0) {
					jsonCategory.put("level", level);
					String parentExternalId = AssetVocabularyHelper.getExternalId(category.getParentCategory());
					if (Validator.isNotNull(parentExternalId)) {
						jsonCategory.put("parentId", parentExternalId);
					}
				}
			} catch (PortalException e) {
				_log.error(e);
			}
		}
		return jsonCategory;
	}

	/**
	 * Retourne un JSONArray des externalIds d'une liste de catégories
	 */
	static public JSONArray getExternalIdsJSONArray(List<AssetCategory> categories) {
		JSONArray json = JSONFactoryUtil.createJSONArray();
		for (AssetCategory category : categories) {
			String categoryExternalId = AssetVocabularyHelper.getExternalId(category);
			if (Validator.isNotNull(categoryExternalId)) {
				json.put(categoryExternalId);
			}
		}
		return json;
	}

	public static String getTerritoryTitle(Locale locale, List<AssetCategory> assetTerritoryCategories) {
		StringBuilder result = new StringBuilder();

		if (assetTerritoryCategories == null || assetTerritoryCategories.isEmpty()) {
			result.append("aucun territoire");
		} else {
			result.append(assetTerritoryCategories.stream()
					.map(assetCategory -> assetCategory.getTitle(locale))
					.collect(Collectors.joining(" - ")));
		}
		return result.toString();
	}

	/**
	 * Retourne les sous-sous-catégories 'Territoire' correspondant aux quartiers de
	 * l'entité
	 *
	 * @return : null si vide, sinon la liste des catégories
	 */
	public static List<AssetCategory> getDistrictCategories(List<AssetCategory> territories) {
		List<AssetCategory> districts = new ArrayList<>();
		for (AssetCategory territory : territories) {
			try {
				if (territory.getAncestors().size() == 2) {
					districts.add(territory);
				}
			} catch (PortalException e) {
				continue;
			}
		}
		return districts;
	}

	/**
	 * Retourne les sous-catégories 'Territoire' correspondant aux villes de
	 * l'entité
	 *
	 * @return : null si vide, sinon la liste des catégories
	 */
	public static List<AssetCategory> getCityCategories(List<AssetCategory> territories) {
		List<AssetCategory> cities = new ArrayList<AssetCategory>();
		for (AssetCategory territory : territories) {
			try {
				if (territory.getAncestors().size() == 1) {
					cities.add(territory);
				}
			} catch (PortalException e) {
				continue;
			}
		}
		return cities;
	}

	public static String getDistrictTitleForCity(Locale locale, List<AssetCategory> assetDistrictCategories, List<AssetCategory> assetCityCategories, String city) {
		StringBuilder result = new StringBuilder();
		boolean isAllDistricts = AssetVocabularyHelper.isAllDistrictOfCity(assetDistrictCategories, city);
		boolean isAllCities= AssetVocabularyHelper.isAllFrenchCity(assetCityCategories.size());

		if ((assetCityCategories == null || assetCityCategories.isEmpty()) && (assetDistrictCategories == null || assetDistrictCategories.isEmpty())) {
			result.append("Aucune commune");
		} else if (isAllCities) {
			result.append("Toutes les communes de l\u2019Eurom\u00e9tropole");
		} else {
			if (!assetDistrictCategories.isEmpty()) {
				long globalGroupId =0;
				try {
					Company defaultCompany = CompanyLocalServiceUtil.getCompanyByWebId("liferay.com");
					globalGroupId = defaultCompany.getGroup().getGroupId();
					if(globalGroupId != 0) {
						AssetCategory strasbourg = getCategory("Strasbourg", globalGroupId);
						assetCityCategories.remove(strasbourg);
						assetCityCategories.add(strasbourg);
					}
				} catch (PortalException e) {
					_log.error("Le group Global n'a pas été trouvé");
				}
			}

			result.append(assetCityCategories.stream()
					.map(assetCategory -> assetCategory.getTitle(locale))
					.collect(Collectors.joining(" - ")));

			if(!assetDistrictCategories.isEmpty()){
				if(!isAllDistricts) {
					result.append(" (");
					result.append(assetDistrictCategories.stream()
							.map(assetCategory -> assetCategory.getTitle(locale))
							.collect(Collectors.joining(" - ")));
					result.append(")");
				}else{
					result.append(" (Tous les quartiers)");
				}
			}
		}

		return result.toString();
	}

	public static String getDistrictTitle(Locale locale, List<AssetCategory> assetDistrictCategories, List<AssetCategory> assetCityCategories) {
		return getDistrictTitleForCity(locale, assetDistrictCategories, assetCityCategories, "Strasbourg");
	}

	/**
	 * méthode permettant de récupérer les titres des thématiques
	 * @param locale la locale
	 * @param assetCategories les thematiques
	 * @return les titres
	 */
	public static String getThematicTitle(Locale locale,List<AssetCategory> assetCategories){
		StringBuilder result = new StringBuilder();
		if (assetCategories != null && !assetCategories.isEmpty()) {
			result.append(assetCategories.stream()
					.map(assetCategory -> assetCategory.getTitle(locale))
					.collect(Collectors.joining(" - ")));
		}
		return result.toString();
	}

	/**
	 * Retourne la liste des catégories du vocabulaire passé en paramètre, sans
	 * les catégories enfants triées par la valeur de la propriété "order" de
	 * chaque catégorie
	 */
	public static List<AssetCategory> getSortedCategories(String vocabulary, long groupId) {
		List<AssetCategory> categories = getVocabulary(vocabulary, groupId).getCategories();

		// trie des catégories par la propriété order si elle existe
		Map<String, AssetCategory> order_category = new HashMap<String, AssetCategory>();
		List<AssetCategory> categoriesWithoutOrder = new ArrayList<AssetCategory>();
		for (AssetCategory assetCategory : categories) {
			if (assetCategory != null) {
				String orderString = AssetVocabularyHelper.getCategoryProperty(assetCategory.getCategoryId(), "order");
				if (orderString.equals("")) {
					categoriesWithoutOrder.add(assetCategory);
				} else {
					order_category.put(orderString, assetCategory);
				}
			}
		}

		List<AssetCategory> sortedCategories = new ArrayList<AssetCategory>();
		for (AssetCategory assetCategory : order_category.values()) {
			sortedCategories.add(assetCategory);
		}
		sortedCategories.addAll(categoriesWithoutOrder);

		return sortedCategories;
	}

	/**
	 * Renvoit la liste des categories ayant au moins 1 catégorie enfant
	 * @param categories
	 * @return
	 */
	public static List<AssetCategory> getParentCategory(List<AssetCategory> categories) {
		List<AssetCategory> parentCategories = new ArrayList<>();
		for(AssetCategory category : categories) {
			if(getCategoriesWithChild(category).size() > 1) {
				parentCategories.add(category);
			}
		}

		return parentCategories;
	}

	/**
	 * Ajout une nouvelle categorie au vocabulaire du site donné (marche en locale FR_fr)
	 * @param categoryName Nom de la categorie à ajouter
	 * @param vocabularyName Nom du vocabulaire où ajouter la categorie
	 * @param sc Contexte de la requête
	 * @return La catégorie ajouté
	 */
	public static AssetCategory addCategoryToVocabulary(String categoryName, String vocabularyName, ServiceContext sc)
			throws PortalException {
		AssetVocabulary assetVocabulary = AssetVocabularyHelper.getVocabulary(vocabularyName, sc.getScopeGroupId());
		return AssetCategoryLocalServiceUtil.addCategory( sc.getUserId(), sc.getScopeGroupId(), categoryName,
				assetVocabulary.getVocabularyId(), sc);
	}

	/**
	 * Renommer une categorie du vocabulaire du site donné
	 * @param assetCategory Categorie à renommer
	 * @param newName Nouveau nom de la catégorie
	 * @return La catégorie renommée si trouvée, sinon null
	 */
	public static AssetCategory renameCategory(AssetCategory assetCategory, String newName) {
		if (assetCategory != null) {
			assetCategory.setTitle(newName);
			assetCategory.setName(newName);
			assetCategory = AssetCategoryLocalServiceUtil.updateAssetCategory(assetCategory);
		}
		return assetCategory;
	}

	/**
	 * Supprimer une categorie du vocabulaire du site donné
	 * @param categoryName Nom de la categorie à ajouter
	 * @param groupId Id du site
	 * @return La catégorie supprimée si trouvée, sinon null
	 */
	public static AssetCategory removeCategory(String categoryName, long groupId) {
		AssetCategory assetCategory = AssetVocabularyHelper.getCategory(categoryName, groupId);
		if (assetCategory != null) {
			assetCategory = AssetCategoryLocalServiceUtil.deleteAssetCategory(assetCategory);
		}
		return assetCategory;
	}

	private static Log _log = LogFactoryUtil.getLog("AssetVocabularyHelper");
}
