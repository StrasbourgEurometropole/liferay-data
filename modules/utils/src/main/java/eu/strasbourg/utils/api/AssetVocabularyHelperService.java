package eu.strasbourg.utils.api;

import java.util.List;
import java.util.Locale;

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
    List<AssetVocabulary> getVocabulariesForAssetType(long groupId, long classNameId);

    /**
     * Retourne la liste des catégories rattachées à un AssetEntry
     */
    List<AssetCategory> getAssetEntryCategories(
            AssetEntry entry);

    /**
     * Retourne la liste des catégories d'un vocabulaire spécifique rattachées à
     * un AssetEntry
     *
     */
    List<AssetCategory> getAssetEntryCategoriesByVocabulary(
            AssetEntry entry, String vocabularyName);

    /**
     * Retourne la valeur d'une propriété d'une catégorie
     * Retourne une chaîne vide si la propriété n'existe pas
     */
    String getCategoryProperty(long categoryId, String key);

    /**
     * Retourne le vocabulaire ayant le nom donné et faisant parti du groupe
     * donné
     */
    AssetVocabulary getVocabulary(String vocabularyName, long groupId);

    /**
     * méthode permettant de savoir si le nombre entré en parametre est égale au nombre de quartier total.
     *
     * @param listDistrictSizeToCompare le nombre de quartier présent dans l'asset.
     * @return le boolean.
     */
    boolean isAllDistrict(int listDistrictSizeToCompare);

    /**
     * Retourne les sous-sous-catégories 'Territoire' correspondant aux quartiers de la participation
     * @return : null si vide, sinon la liste des catégories
     */
    String getDistrictTitle(Locale locale, List<AssetCategory> assetCategories);
}
