package eu.strasbourg.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.LongStream;

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
import com.liferay.portal.kernel.service.ClassNameLocalServiceUtil;
import com.liferay.portal.kernel.service.CompanyLocalServiceUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;

/**
 * Classe Helper pour tout ce qui concerne les vocabulaires
 *
 */
public class AssetVocabularyHelper {
	/**
	 * Retourne la liste des vocabulaires rattachés à un type d'entité
	 */
	public static List<AssetVocabulary> getVocabulariesForAssetType(
		long groupId, long classNameId) {
		List<AssetVocabulary> vocabularies = AssetVocabularyLocalServiceUtil
			.getAssetVocabularies(-1, -1);
		List<AssetVocabulary> attachedVocabularies = new ArrayList<AssetVocabulary>();
		if (classNameId > 0) {
			for (AssetVocabulary vocabulary : vocabularies) {
				if (vocabulary.getGroupId() == groupId
					&& LongStream.of(vocabulary.getSelectedClassNameIds())
						.anyMatch(c -> c == classNameId)) {
					attachedVocabularies.add(vocabulary);
				}
			}
		}
		return attachedVocabularies;
	}

	/**
	 * Retourne la liste des vocabulaires obligatoires pour un type d'entité
	 */
	public static List<AssetVocabulary> getRequiredVocabulariesForAssetType(
		long groupId, long classNameId) {
		List<AssetVocabulary> vocabularies = AssetVocabularyLocalServiceUtil
			.getAssetVocabularies(-1, -1);
		List<AssetVocabulary> attachedVocabularies = new ArrayList<AssetVocabulary>();
		if (classNameId > 0) {
			for (AssetVocabulary vocabulary : vocabularies) {
				if (vocabulary.getGroupId() == groupId
					&& LongStream.of(vocabulary.getRequiredClassNameIds())
						.anyMatch(c -> c == classNameId)) {
					attachedVocabularies.add(vocabulary);
				}
			}
		}
		return attachedVocabularies;
	}

	/**
	 * Retourne le vocabulary rattaché à la classe donnée et qui possède le nom
	 * donné
	 */
	public static AssetVocabulary getEntityVocabulary(String className,
		String vocabularyName, long groupId) {
		long classNameId = ClassNameLocalServiceUtil.getClassName(className)
			.getClassNameId();
		List<AssetVocabulary> vocabularies = AssetVocabularyHelper
			.getVocabulariesForAssetType(groupId, classNameId);
		for (AssetVocabulary vocabulary : vocabularies) {
			if (StringHelper.compareIgnoringAccentuation(
				vocabulary.getName().toLowerCase(), vocabularyName)) {
				return vocabulary;
			}
		}
		return null;
	}

	/**
	 * Retourne le vocabulaire ayant le nom donné et faisant parti du groupe
	 * donné
	 */
	public static AssetVocabulary getVocabulary(String vocabularyName,
		long groupId) {
		List<AssetVocabulary> vocabularies = AssetVocabularyLocalServiceUtil
			.getAssetVocabularies(-1, -1);
		for (AssetVocabulary vocabulary : vocabularies) {
			if (StringHelper.compareIgnoringAccentuation(
				vocabulary.getName().toLowerCase(), vocabularyName)
				&& vocabulary.getGroupId() == groupId) {
				return vocabulary;
			}
		}
		return null;
	}

	/**
	 * Retourne le vocabulaire ayant le nom donné et faisant parti du groupe
	 * donné
	 * 
	 * @throws PortalException
	 */
	public static AssetVocabulary getGlobalVocabulary(String vocabularyName)
		throws PortalException {
		long companyId = PortalUtil.getDefaultCompanyId();
		long companyGroupId = CompanyLocalServiceUtil.getCompany(companyId)
			.getGroupId();
		List<AssetVocabulary> vocabularies = AssetVocabularyLocalServiceUtil
			.getAssetVocabularies(-1, -1);
		for (AssetVocabulary vocabulary : vocabularies) {
			if (StringHelper.compareIgnoringAccentuation(
				vocabulary.getName().toLowerCase(), vocabularyName)
				&& vocabulary.getGroupId() == companyGroupId) {
				return vocabulary;
			}
		}
		return null;
	}

	/**
	 * Retourne la liste des catégories rattachées à un AssetEntry
	 */
	public static List<AssetCategory> getAssetEntryCategories(
		AssetEntry entry) {
		long[] categoryIds = entry.getCategoryIds();
		List<AssetCategory> categories = new ArrayList<AssetCategory>();
		for (long categoryId : categoryIds) {
			AssetCategory category = AssetCategoryLocalServiceUtil
				.fetchAssetCategory(categoryId);
			if (category != null) {
				categories.add(category);
			}
		}
		return categories;
	}

	/**
	 * Retourne la liste des catégories d'un vocabulaire spécifique rattachées à
	 * un AssetEntry
	 * 
	 */
	static public List<AssetCategory> getAssetEntryCategoriesByVocabulary(
		AssetEntry entry, String vocabularyName) {
		List<AssetCategory> results = new ArrayList<AssetCategory>();
		List<AssetCategory> categories = AssetVocabularyHelper
			.getAssetEntryCategories(entry);
		for (AssetCategory category : categories) {
			AssetVocabulary vocabulary = AssetVocabularyLocalServiceUtil
				.fetchAssetVocabulary(category.getVocabularyId());
			if (vocabulary != null && StringHelper.compareIgnoringAccentuation(
				vocabulary.getName().toLowerCase(), vocabularyName)) {
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
			return AssetCategoryPropertyLocalServiceUtil
				.getCategoryProperty(categoryId, key).getValue();
		} catch (Exception e) {
			return "";
		}
	}

	/**
	 * Retourne la liste des catéogories enfants à une catégorie donnée
	 */
	public static List<AssetCategory> getChild(long categoryId) {
		return AssetCategoryLocalServiceUtil.getChildCategories(categoryId);
	}

	/**
	 * Retourne l'ensemble des catégories pasées en paramètre et leur parents
	 */
	public static List<AssetCategory> getFullHierarchyCategories(
		List<AssetCategory> categories) throws PortalException {
		List<AssetCategory> allCategories = new ArrayList<AssetCategory>();
		for (AssetCategory category : categories) {
			List<AssetCategory> ancestors = category.getAncestors();
			allCategories.add(category);
			allCategories.addAll(ancestors);
		}
		return allCategories;
	}

	/**
	 * Renvoie l'ensemble des catégories parentes, les catégorie passée en
	 * paramètre et leurs enfants sous forme d'array d'ids
	 */
	public static long[] getFullHierarchyCategoriesIds(
		List<AssetCategory> categories) throws PortalException {
		List<AssetCategory> allCategories = getFullHierarchyCategories(
			categories);
		return ListUtil.toLongArray(allCategories,
			AssetCategory.CATEGORY_ID_ACCESSOR);
	}

	/**
	 * Retourne la catégorie avec le nom donné et faisant parti du groupe donné
	 */
	public static AssetCategory getCategory(String categoryName, long groupId) {
		List<AssetCategory> categories = AssetCategoryLocalServiceUtil
			.getAssetCategories(-1, -1);
		for (AssetCategory category : categories) {
			if (StringHelper.compareIgnoringAccentuation(
				category.getName().toLowerCase(), categoryName)
				&& category.getGroupId() == groupId) {
				return category;
			}
		}
		return null;
	}

	/**
	 * Retourne la category ayant le vocabulaire "vocabulary" et une propriété
	 * "externalId" ou "SIGId" ayant pour valeur "externalId". Retourne null si
	 * aucune catégorie ne correspond à ces critères.
	 */
	public static AssetCategory getCategoryByExternalId(
		AssetVocabulary vocabulary, String externalId) {
		List<AssetCategory> categories = vocabulary.getCategories();
		for (AssetCategory category : categories) {

			String SIGIdProperty = AssetVocabularyHelper
				.getCategoryProperty(category.getCategoryId(), "SIG");
			if (SIGIdProperty.equals(externalId)) {
				return category;
			}
			String externalIdProperty = AssetVocabularyHelper
				.getCategoryProperty(category.getCategoryId(), "externalId");
			if (externalIdProperty.equals(externalId)) {
				return category;
			}
		}
		return null;
	}

	/**
	 * Retourne la version JSON d'une liste de catégories
	 */
	static public JSONArray toJSON(AssetVocabulary vocabulary) {
		List<AssetCategory> categories = vocabulary.getCategories();
		JSONArray jsonCategories = JSONFactoryUtil.createJSONArray();
		for (AssetCategory category : categories) {
			JSONObject jsonCategory = AssetVocabularyHelper
				.categotyToJSON(category);
			if (jsonCategory.length() > 0) {
				jsonCategories.put(jsonCategory);
			}
		}
		return jsonCategories;
	}

	/**
	 * Retourne l'ID externe d'une catégorie : propriété "externalId" ou "SIG"
	 */
	static public String getExternalId(AssetCategory category) {
		String externalId = "";
		if (category != null) {
			externalId = AssetVocabularyHelper
				.getCategoryProperty(category.getCategoryId(), "externalId");
			if (Validator.isNull(externalId)) {
				externalId = AssetVocabularyHelper
					.getCategoryProperty(category.getCategoryId(), "SIG");
			}
		}
		return externalId;
	}

	/**
	 * Retourne la version JSON d'une catégorie
	 */
	static public JSONObject categotyToJSON(AssetCategory category) {
		JSONObject jsonCategory = JSONFactoryUtil.createJSONObject();
		if (category != null) {
			// On exporte la propriété "externalId" de la catégorie, si elle
			// n'existe pas, la propriété "SIG", sinon, on renvoie un objet vide
			String externalId = AssetVocabularyHelper.getExternalId(category);
			if (Validator.isNull(externalId)) {
				return jsonCategory;
			}
			jsonCategory.put("id", externalId);
			jsonCategory.put("name",
				JSONHelper.getJSONFromI18nMap(category.getTitleMap()));
			try {
				int level = category.getAncestors().size();
				if (level > 0) {
					jsonCategory.put("level", level);
					String parentExternalId = AssetVocabularyHelper
						.getExternalId(category.getParentCategory());
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
	 * Retourne un JSONArray de String des externalIds d'une liste de catégories
	 */
	static public JSONArray getExternalIdsJSONArray(
		List<AssetCategory> categories) {
		JSONArray json = JSONFactoryUtil.createJSONArray();
		for (AssetCategory category : categories) {
			String categoryExternalId = AssetVocabularyHelper
				.getExternalId(category);
			if (Validator.isNotNull(categoryExternalId)) {
				json.put(categoryExternalId);
			}
		}
		return json;
	}

	private static Log _log = LogFactoryUtil.getLog("AssetVocabularyHelper");
}
