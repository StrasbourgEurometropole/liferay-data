package eu.strasbourg.utils.api;

import java.util.List;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.model.AssetVocabulary;

import aQute.bnd.annotation.ProviderType;

/**
 * Classe Helper pour tout ce qui concerne les vocabulaires
 *
 */
@ProviderType
public interface AssetVocabularyHelperService {
	public List<AssetVocabulary> getVocabulariesForAssetType(long groupId, long classNameId);

	/**
	 * Retourne la liste des catégories rattachées à un AssetEntry
	 */
	public List<AssetCategory> getAssetEntryCategories(
		AssetEntry entry);

	/**
	 * Retourne la liste des catégories d'un vocabulaire spécifique rattachées à
	 * un AssetEntry
	 * 
	 */
	public List<AssetCategory> getAssetEntryCategoriesByVocabulary(
		AssetEntry entry, String vocabularyName);

	/**
	 * Retourne la valeur d'une propriété d'une catégorie
	 * Retourne une chaîne vide si la propriété n'existe pas
	 */
	public String getCategoryProperty(long categoryId, String key);

	/**
	 * Retourne le vocabulaire ayant le nom donné et faisant parti du groupe
	 * donné
	 */
	public AssetVocabulary getVocabulary(String vocabularyName, long groupId);
}
