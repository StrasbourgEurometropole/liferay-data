package eu.strasbourg.webservice.csmap.service;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.asset.kernel.service.AssetCategoryLocalServiceUtil;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
import com.liferay.dynamic.data.mapping.model.DDMStructure;
import com.liferay.dynamic.data.mapping.service.DDMStructureLocalServiceUtil;
import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.model.JournalFolder;
import com.liferay.journal.service.JournalArticleLocalServiceUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import eu.strasbourg.utils.AssetVocabularyHelper;
import eu.strasbourg.utils.JournalArticleHelper;
import eu.strasbourg.utils.constants.VocabularyNames;
import eu.strasbourg.webservice.csmap.constants.WSConstants;
import eu.strasbourg.webservice.csmap.utils.WSCSMapUtil;
import org.osgi.service.component.annotations.Component;

import java.util.*;

/**
 * Service s'occuppant de verifier si les urgences de l'utilisateur sont à jour.
 */
@Component(
        immediate = true,
        service = WSEmergencies.class
)
public class WSEmergencies {

    static public Map<String,List<JournalArticle>> getMapEmergencyNumbers(Date lastUpdateTime, String ids_emergency_number) throws Exception {
        Map<String, List<JournalArticle>> mapEmergencyNumbers = new HashMap<>();
        try {

            Group csmapGroup = WSCSMapUtil.getGroupByKey(WSConstants.GROUP_KEY_CSMAP);
            long csmapGroupId = csmapGroup.getGroupId();
            JournalFolder emergencyNumbersFolder = WSCSMapUtil.getJournalFolderByGroupAndName(csmapGroupId, WSConstants.FOLDER_EMERGENCY_NUMBERS);
            long emergencyNumbersFolderId = emergencyNumbersFolder.getFolderId();

            // Recuperation des JournalArticle dans le dossier Numeros urgence
            List<JournalArticle> emergencyNumbers = new ArrayList<>(JournalArticleLocalServiceUtil.getArticles(csmapGroupId, emergencyNumbersFolderId));
            // Recuperation des Numeros urgence a ADD et UPDATE
            List<JournalArticle> emergencyNumbersAdd = new ArrayList<>();
            List<JournalArticle> emergencyNumbersUpdate = new ArrayList<>();

            DDMStructure structure = WSCSMapUtil.getStructureByGroupAndName(csmapGroupId,WSConstants.STRUCTURE_EMERGENCY_NUMBER);

            // Verification des Numeros urgence si nouveau ou modifie
            for (JournalArticle emergencyNumber : emergencyNumbers) {
                if (structure.getStructureKey().equals(emergencyNumber.getDDMStructureKey()) && emergencyNumber.getStatus() == WorkflowConstants.STATUS_APPROVED) {
                    if (lastUpdateTime.before(emergencyNumber.getCreateDate())) {
                        emergencyNumbersAdd.add(emergencyNumber);
                    } else if (!ids_emergency_number.contains(String.valueOf(emergencyNumber.getResourcePrimKey()))) {
                        emergencyNumbersAdd.add(emergencyNumber);
                    } else if (lastUpdateTime.before(emergencyNumber.getModifiedDate())) {
                        emergencyNumbersUpdate.add(emergencyNumber);
                    }
                }
            }

            mapEmergencyNumbers.put(WSConstants.JSON_ADD, emergencyNumbersAdd);
            mapEmergencyNumbers.put(WSConstants.JSON_UPDATE, emergencyNumbersUpdate);
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
        return mapEmergencyNumbers;
    }

    static public Map<String,Map<AssetCategory, List<JournalArticle>>> getMapEmergencyHelps(Date lastUpdateTime, String ids_emergency_help_category) throws Exception {
        Map<String,Map<AssetCategory, List<JournalArticle>>> mapsEmergencyHelps = new HashMap<>();
        try{
        Group csmapGroup = WSCSMapUtil.getGroupByKey(WSConstants.GROUP_KEY_CSMAP);
        long csmapGroupId = csmapGroup.getGroupId();
        JournalFolder emergencyHelpsFolder = WSCSMapUtil.getJournalFolderByGroupAndName(csmapGroupId,WSConstants.FOLDER_EMERGENCY_HELPS);
        long emergencyHelpsFolderId = emergencyHelpsFolder.getFolderId();

        Map<AssetCategory, List<JournalArticle>> emergencyHelpsMap = new HashMap<>();
        Map<AssetCategory, List<JournalArticle>> emergencyHelpsMapAdd = new HashMap<>();
        Map<AssetCategory, List<JournalArticle>> emergencyHelpsMapUpdate = new HashMap<>();
        Map<AssetCategory, List<JournalArticle>> emergencyHelpsMapToDelete = new HashMap<>();

        DDMStructure structure = WSCSMapUtil.getStructureByGroupAndName(csmapGroupId,WSConstants.STRUCTURE_EMERGENCY_HELP);

        // On recupere les categories des urgences
        AssetVocabulary emergenciesVocabulary = AssetVocabularyHelper.getVocabulary(VocabularyNames.CSMAP_URGENCES, csmapGroupId);
        // On recupere les aides urgence
        List<JournalArticle> emergencyHelps = JournalArticleLocalServiceUtil.getArticles(csmapGroupId, emergencyHelpsFolderId);
        if (Validator.isNotNull(emergenciesVocabulary)) {
            for (AssetCategory category : emergenciesVocabulary.getCategories()) {
                List<JournalArticle> value = new ArrayList<>();
                emergencyHelpsMap.put(category, value);


                // Verification des Numeros urgence si nouveau ou modifie
                for (JournalArticle emergencyHelp : emergencyHelps) {
                    if(structure.getStructureKey().equals(emergencyHelp.getDDMStructureKey()) && emergencyHelp.getStatus() == WorkflowConstants.STATUS_APPROVED) {
                        AssetEntry assetEntry = AssetEntryLocalServiceUtil.fetchEntry(JournalArticle.class.getName(), emergencyHelp.getResourcePrimKey());
                        if(assetEntry.getCategories().contains(category)) {
                            List<JournalArticle> newValue = emergencyHelpsMap.get(category);
                            newValue.add(emergencyHelp);
                            emergencyHelpsMap.replace(category,
                                    emergencyHelpsMap.get(category),
                                    newValue);
                            if (lastUpdateTime.before(category.getCreateDate())) {
                                if (!emergencyHelpsMapAdd.containsKey(category)) {
                                    List<JournalArticle> valueNull = new ArrayList<>();
                                    emergencyHelpsMapAdd.put(category, valueNull);
                                }
                            } else if (!ids_emergency_help_category.contains(String.valueOf(category.getCategoryId()))) {
                                if (!emergencyHelpsMapAdd.containsKey(category)) {
                                    List<JournalArticle> valueNull = new ArrayList<>();
                                    emergencyHelpsMapAdd.put(category, valueNull);
                                }
                            } else if (lastUpdateTime.before(category.getModifiedDate())) {
                                if (!emergencyHelpsMapAdd.containsKey(category)) {
                                    List<JournalArticle> valueNull = new ArrayList<>();
                                    emergencyHelpsMapUpdate.put(category, valueNull);
                                }
                            } else if (lastUpdateTime.before(emergencyHelp.getModifiedDate())) {
                                if (!emergencyHelpsMapAdd.containsKey(category)) {
                                    List<JournalArticle> valueNull = new ArrayList<>();
                                    emergencyHelpsMapUpdate.put(category, valueNull);
                                }
                            }
                        }
                    }
                }
                for(Map.Entry emergencyHelpEntry : emergencyHelpsMapAdd.entrySet()){
                    if(emergencyHelpsMap.containsKey(emergencyHelpEntry.getKey())){
                        List<JournalArticle> newValue = emergencyHelpsMap.get(category);
                        emergencyHelpsMapAdd.replace(category,
                                emergencyHelpsMapAdd.get(category),
                                newValue);
                    }
                }
                for(Map.Entry emergencyHelpEntry : emergencyHelpsMapUpdate.entrySet()){
                    if(emergencyHelpsMap.containsKey(emergencyHelpEntry.getKey())){
                        List<JournalArticle> newValue = emergencyHelpsMap.get(category);
                        emergencyHelpsMapUpdate.replace(category,
                                emergencyHelpsMapUpdate.get(category),
                                newValue);
                    }
                }
                // Dans le cas d'une catégorie qui n'aurait aucun Article "Aide d'urgence" associé
                // On considère que c'est une catégorie à supprimer puisqu'on ne veut pas afficher une catégorie vide
                if(emergencyHelpsMap.get(category) != null && emergencyHelpsMap.get(category).isEmpty()) {
                    emergencyHelpsMapToDelete.put(category, new ArrayList<>());
                }
            }
        }
        mapsEmergencyHelps.put(WSConstants.JSON_ADD,emergencyHelpsMapAdd);
        mapsEmergencyHelps.put(WSConstants.JSON_UPDATE,emergencyHelpsMapUpdate);
            mapsEmergencyHelps.put(WSConstants.JSON_DELETE,emergencyHelpsMapToDelete);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return mapsEmergencyHelps;
    }

    static public List<String> getJSONEmergencyNumbersDelete(String ids_emergency_number){
        // Preparation des donnees de la partie DELETE
        // On recupere tous les numeros urgence qui ont ete supprimes
        List<String> emergencyNumbersJSONDelete = new ArrayList<>();
            for (String idEmergencyNumber : ids_emergency_number.split(",")) {
                if(Validator.isNotNull(idEmergencyNumber)) {
                    JournalArticle journalArticle = JournalArticleHelper.getLatestArticleByResourcePrimKey(Long.parseLong(idEmergencyNumber));
                    if (Validator.isNull(journalArticle) || journalArticle.getStatus() != WorkflowConstants.STATUS_APPROVED) {
                        emergencyNumbersJSONDelete.add(idEmergencyNumber);
                    }
                }
            }
        return emergencyNumbersJSONDelete;
    }

    /**
     * Récupération de la liste des Ids catégories d'aides d'urgence à considérer comme à supprimer chez l'utilisateur appelant
     * @param ids_emergency_help_category Ids de catégorie aide d'urgence présent chez l'utilisateurs
     * @param emergencyHelpCategoriesEmpty Ids des caégories aide d'urgence ne possédant pas de contenu web associé (donc à considéré comme à supprimer)
     * @return List des Ids catégories aide d'urgence à mettre dans le JSON de delete
     */
    static public List<Long> getJSONEmergencyHelpsDelete(String ids_emergency_help_category, List<Long> emergencyHelpCategoriesEmpty){

        List<Long> emergencyHelpsJSONDelete = new ArrayList<>();
        for (String idEmergencyHelpCategory : ids_emergency_help_category.split(",")) {
            if(Validator.isNotNull(idEmergencyHelpCategory)) {
                long idCategory = Long.parseLong(idEmergencyHelpCategory);
                // Soit la catégorie n'existe plus car supprimée, soit c'est une catégorie qui n'a pas de contenu web associé
                if (Validator.isNull(AssetCategoryLocalServiceUtil.fetchAssetCategory(idCategory))
                    || emergencyHelpCategoriesEmpty.contains(idCategory)) {
                    emergencyHelpsJSONDelete.add(idCategory);
                }
            }
        }
        return emergencyHelpsJSONDelete;
    }
}
