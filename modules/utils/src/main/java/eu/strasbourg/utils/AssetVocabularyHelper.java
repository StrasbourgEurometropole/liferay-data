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
import com.liferay.portal.kernel.util.ListUtil;

/**
 * Classe Helper pour tout ce qui concerne les vocabulaires
 *
 */
public class AssetVocabularyHelper {
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
	
	public static List<AssetCategory> getFullHierarchyCategories(List<AssetCategory> categories) throws PortalException {
		List<AssetCategory> allCategories = new ArrayList<AssetCategory>();
		for (AssetCategory category : categories) {
			List<AssetCategory> ancestors = category.getAncestors();
			List<AssetCategory> child = AssetCategoryLocalServiceUtil.getChildCategories(category.getCategoryId());
			allCategories.add(category);
			allCategories.addAll(ancestors);
			allCategories.addAll(child);
		}
		return allCategories;
	}
	
	public static long[] getFullHierarchyCategoriesIds(List<AssetCategory> categories) throws PortalException {
		List<AssetCategory> allCategories = getFullHierarchyCategories(categories);
		return ListUtil.toLongArray(
			allCategories, AssetCategory.CATEGORY_ID_ACCESSOR);
	}

	/**
	 * Retourne la version JSON d'une liste de catégories
	 */
	static public JSONArray toJSON(AssetVocabulary vocabulary) {
		List<AssetCategory> categories = vocabulary.getCategories();
		JSONArray jsonCategories = JSONFactoryUtil.createJSONArray();
		for (AssetCategory category : categories) {
			jsonCategories.put(AssetVocabularyHelper.categotyToJSON(category));
		}
		return jsonCategories;
	}

	/**
	 * Retourne la version JSON d'une catégorie
	 */
	static public JSONObject categotyToJSON(AssetCategory category) {
		JSONObject jsonCategory = JSONFactoryUtil.createJSONObject();
		if (category != null) {
			jsonCategory.put("id", category.getCategoryId());
			jsonCategory.put("name",
				JSONHelper.getJSONFromI18nMap(category.getTitleMap()));
		}
		return jsonCategory;
	}
}
