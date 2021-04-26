package eu.strasbourg.webservice.csmap.service;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.asset.kernel.service.AssetCategoryLocalServiceUtil;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.model.JournalFolder;
import com.liferay.journal.service.JournalArticleLocalServiceUtil;
import com.liferay.journal.service.JournalFolderLocalServiceUtil;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import eu.strasbourg.utils.AssetVocabularyHelper;
import eu.strasbourg.utils.JournalArticleHelper;
import eu.strasbourg.utils.constants.VocabularyNames;
import eu.strasbourg.webservice.csmap.constants.WSConstants;
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

    static public long getGroupId(String groupKey){

        Group csmapGroup = GroupLocalServiceUtil.getGroups(-1, -1).stream().filter(g -> g.getGroupKey().equals(groupKey)).findFirst().get();
        long groupId = 0;
        if (Validator.isNotNull(csmapGroup)) {
            groupId = csmapGroup.getGroupId();
        }
        return groupId;
    }

    static public long getEmergencyFolderId(String folderName, long groupId){
        // Folder Urgences
        JournalFolder emergenciesFolder = null;
        // Folder Aides urgence
        JournalFolder emergencyFolder = null;
        // Recuperation des folders
        List<JournalFolder> folders = JournalFolderLocalServiceUtil.getFolders(groupId);
        for (JournalFolder folder : folders) {
            // Recuperation du folder Urgences
            if (folder.getName().equals(WSConstants.FOLDER_EMERGENCIES)) {
                emergenciesFolder = folder;
            }
            else if (folder.getName().equals(folderName)) {
                emergencyFolder = folder;
            }
        }
        return emergencyFolder.getFolderId();
    }

    static public Map<String,List<JournalArticle>> getMapEmergencyNumbers(Date lastUpdateTime, String ids_emergency_number){

        Map<String,List<JournalArticle>> mapEmergencyNumbers = new HashMap<String,List<JournalArticle>>();

        long csmapGroupId = getGroupId(WSConstants.GROUP_KEY);
        long  emergencyNumbersFolderId = getEmergencyFolderId(WSConstants.FOLDER_EMERGENCY_NUMBERS,csmapGroupId);

        // Recuperation des JournalArticle dans le dossier Numeros urgence
        List<JournalArticle> emergencyNumbers = new ArrayList<JournalArticle>();
        // Recuperation des Numeros urgence a ADD et UPDATE
        List<JournalArticle> emergencyNumbersAdd = new ArrayList<JournalArticle>();
        List<JournalArticle> emergencyNumbersUpdate = new ArrayList<JournalArticle>();

        // Verification des Numeros urgence si nouveau ou modifie
        emergencyNumbers = JournalArticleLocalServiceUtil.getArticles(csmapGroupId, emergencyNumbersFolderId);
        for (JournalArticle emergencyNumber : emergencyNumbers) {
            if(emergencyNumber.getStatus() == WorkflowConstants.STATUS_APPROVED) {
                if (lastUpdateTime.before(emergencyNumber.getCreateDate())) {
                    emergencyNumbersAdd.add(emergencyNumber);
                }
                else if(!ids_emergency_number.contains(String.valueOf(emergencyNumber.getResourcePrimKey()))){
                    emergencyNumbersAdd.add(emergencyNumber);
                }
                else if (lastUpdateTime.before(emergencyNumber.getModifiedDate())) {
                    emergencyNumbersUpdate.add(emergencyNumber);
                }
            }
        }

        mapEmergencyNumbers.put(WSConstants.JSON_ADD,emergencyNumbersAdd);
        mapEmergencyNumbers.put(WSConstants.JSON_UPDATE,emergencyNumbersUpdate);
        return mapEmergencyNumbers;
    }

    static public Map<String,Map<AssetCategory, List<JournalArticle>>> getMapEmergencyHelps(Date lastUpdateTime, String ids_emergency_help_category){

        Map<String,Map<AssetCategory, List<JournalArticle>>> mapsEmergencyHelps = new HashMap<String,Map<AssetCategory, List<JournalArticle>>>();

        long csmapGroupId = getGroupId(WSConstants.GROUP_KEY);
        long  emergencyHelpsFolderId = getEmergencyFolderId(WSConstants.FOLDER_EMERGENCY_HELPS,csmapGroupId);

        Map<AssetCategory, List<JournalArticle>> emergencyHelpsMap = new HashMap<AssetCategory, List<JournalArticle>>();
        Map<AssetCategory, List<JournalArticle>> emergencyHelpsMapAdd = new HashMap<AssetCategory, List<JournalArticle>>();
        Map<AssetCategory, List<JournalArticle>> emergencyHelpsMapUpdate = new HashMap<AssetCategory, List<JournalArticle>>();



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
                    if(emergencyHelp.getStatus() == WorkflowConstants.STATUS_APPROVED) {
                        AssetEntry assetEntry = AssetEntryLocalServiceUtil.fetchEntry(JournalArticle.class.getName(), emergencyHelp.getResourcePrimKey());
                        if(assetEntry.getCategories().contains(category)) {
                            List<JournalArticle> newValue = emergencyHelpsMap.get(category);
                            newValue.add(emergencyHelp);
                            emergencyHelpsMap.replace(category,
                                    emergencyHelpsMap.get(category),
                                    newValue);
                            if (lastUpdateTime.before(category.getCreateDate())) {
                                if (!emergencyHelpsMapAdd.keySet().contains(category)) {
                                    List<JournalArticle> valueNull = new ArrayList<>();
                                    emergencyHelpsMapAdd.put(category, valueNull);
                                }
                            } else if (!ids_emergency_help_category.contains(String.valueOf(category.getCategoryId()))) {
                                if (!emergencyHelpsMapAdd.keySet().contains(category)) {
                                    List<JournalArticle> valueNull = new ArrayList<>();
                                    emergencyHelpsMapAdd.put(category, valueNull);
                                }
                            } else if (lastUpdateTime.before(category.getModifiedDate())) {
                                if (!emergencyHelpsMapAdd.keySet().contains(category)) {
                                    List<JournalArticle> valueNull = new ArrayList<>();
                                    emergencyHelpsMapUpdate.put(category, valueNull);
                                }
                            } else if (lastUpdateTime.before(emergencyHelp.getModifiedDate())) {
                                if (!emergencyHelpsMapAdd.keySet().contains(category)) {
                                    List<JournalArticle> valueNull = new ArrayList<>();
                                    emergencyHelpsMapUpdate.put(category, valueNull);
                                }
                            }
                        }
                    }
                }
                for(Map.Entry emergencyHelpEntry : emergencyHelpsMapAdd.entrySet()){
                    if(emergencyHelpsMap.keySet().contains(emergencyHelpEntry.getKey())){
                        List<JournalArticle> newValue = emergencyHelpsMap.get(category);
                        emergencyHelpsMapAdd.replace(category,
                                emergencyHelpsMapAdd.get(category),
                                newValue);
                    }
                }
                for(Map.Entry emergencyHelpEntry : emergencyHelpsMapUpdate.entrySet()){
                    if(emergencyHelpsMap.keySet().contains(emergencyHelpEntry.getKey())){
                        List<JournalArticle> newValue = emergencyHelpsMap.get(category);
                        emergencyHelpsMapUpdate.replace(category,
                                emergencyHelpsMapUpdate.get(category),
                                newValue);
                    }
                }
            }
        }
        mapsEmergencyHelps.put(WSConstants.JSON_ADD,emergencyHelpsMapAdd);
        mapsEmergencyHelps.put(WSConstants.JSON_UPDATE,emergencyHelpsMapUpdate);
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

    static public List<String> getJSONEmergencyHelpsDelete(String ids_emergency_help_category){
        // Preparation des donnees de la partie DELETE
        // On recupere tous les aides urgence qui ont ete supprimes
        List<String> emergencyHelpsJSONDelete = new ArrayList<>();
        for (String idEmergencyHelpCategory : ids_emergency_help_category.split(",")) {
            if(Validator.isNotNull(idEmergencyHelpCategory)) {
                Long idCategory = Long.parseLong(idEmergencyHelpCategory);
                if (Validator.isNull(AssetCategoryLocalServiceUtil.fetchAssetCategory(idCategory))) {
                    emergencyHelpsJSONDelete.add(idEmergencyHelpCategory);
                }
            }
        }
        return emergencyHelpsJSONDelete;
    }
}
