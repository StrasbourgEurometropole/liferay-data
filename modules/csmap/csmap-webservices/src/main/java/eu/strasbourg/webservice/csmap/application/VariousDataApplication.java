package eu.strasbourg.webservice.csmap.application;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.model.AssetTag;
import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.asset.kernel.service.*;
import com.liferay.asset.kernel.service.persistence.AssetEntryQuery;
import com.liferay.document.library.kernel.model.DLFolder;
import com.liferay.document.library.kernel.model.DLFolderConstants;
import com.liferay.document.library.kernel.service.DLFolderLocalServiceUtil;
import com.liferay.dynamic.data.mapping.model.DDMStructure;
import com.liferay.dynamic.data.mapping.service.DDMStructureLocalServiceUtil;
import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.model.JournalFolder;
import com.liferay.journal.service.JournalArticleLocalServiceUtil;
import com.liferay.journal.service.JournalFolderLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.io.WriterOutputStream;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import eu.strasbourg.utils.DateHelper;
import eu.strasbourg.utils.JournalArticleHelper;
import eu.strasbourg.utils.AssetVocabularyHelper;
import eu.strasbourg.utils.DateHelper;
import eu.strasbourg.utils.JournalArticleHelper;
import eu.strasbourg.utils.constants.VocabularyNames;
import eu.strasbourg.webservice.csmap.constants.WSConstants;
import eu.strasbourg.webservice.csmap.utils.CSMapJSonHelper;
import eu.strasbourg.webservice.csmap.utils.WSResponseUtil;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.jaxrs.whiteboard.JaxrsWhiteboardConstants;

import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;
import java.util.*;

/**
 * @author angelique.champougny
 */
@Component(
        property = {
                JaxrsWhiteboardConstants.JAX_RS_APPLICATION_BASE + "=" + WSConstants.APP_GROUP_BASE + WSConstants.APP_VARIOUS_DATA_BASE,
                JaxrsWhiteboardConstants.JAX_RS_NAME + "=" + WSConstants.APP_VARIOUS_DATA_NAME,
                "auth.verifier.guest.allowed=true",
                "liferay.access.control.disable=true"
        },
        service = Application.class
)
public class VariousDataApplication extends Application {

    public Set<Object> getSingletons() {
        return Collections.singleton(this);
    }

    private final Log log = LogFactoryUtil.getLog(this.getClass().getName());

    @PUT
    @Produces("application/json")
    @Path("/get-news/{last_update_time}")
    public Response getNews(
            @PathParam("last_update_time") String lastUpdateTimeString,
            String params) {

        // On vérifie que lastUpdateTimeString est renseigné
        if (Validator.isNull(lastUpdateTimeString))
            return WSResponseUtil.buildErrorResponse(400,
                    "Il manque le paramètre last_update_time");

        // On transforme la date string en date
        Date lastUpdateTime;
        try {
            long lastUpdateTimeLong = Long.parseLong(lastUpdateTimeString);
            lastUpdateTime = DateHelper.getDateFromUnixTimestamp(lastUpdateTimeLong);
        }catch (Exception e) {
            return WSResponseUtil.buildErrorResponse(400, "Format de date incorrect");
        }

        // On vérifie que les ids sont renseignés
        if (Validator.isNull(params) || !params.contains("ids_news="))
            return WSResponseUtil.buildErrorResponse(400, "Il manque le paramètre ids_news");

        JSONObject json = JSONFactoryUtil.createJSONObject();

        // On récupère les contenu web de structure Brève ayant le tag csmap
        List<JournalArticle> breves = new ArrayList<>();
        // récupération du tag
        Group group = GroupLocalServiceUtil.getGroups(-1, -1).stream().filter(g -> g.getGroupKey().equals("Strasbourg.eu")).findFirst().get();

        // On récupère toutes les brèves qui ont été ajoutées ou modifiées
        JSONArray jsonAjout = JSONFactoryUtil.createJSONArray();
        JSONArray jsonModif = JSONFactoryUtil.createJSONArray();
        AssetTag tag = AssetTagLocalServiceUtil.getGroupTags(group.getGroupId()).stream().filter(t -> t.getName().equals("csmap")).findFirst().orElse(null);

        if(tag != null) {
            // récupération de l'assetEntry
            List<AssetEntry> entries = AssetEntryLocalServiceUtil.getAssetTagAssetEntries(tag.getTagId());

            // récupération de la structure
            DDMStructure structure = DDMStructureLocalServiceUtil.getStructures(group.getGroupId()).stream().filter(s -> s.getName(Locale.FRANCE).equals("Breve")).findFirst().orElse(null);
            for (AssetEntry entry : entries) {
                // récupération de la dernière version du journalArticle
                JournalArticle journalArticle = JournalArticleHelper.getLatestArticleByResourcePrimKey(entry.getClassPK());
                if (structure.getStructureKey().equals(journalArticle.getDDMStructureKey()) && journalArticle.getStatus() == WorkflowConstants.STATUS_APPROVED)
                    breves.add(journalArticle);
            }

            for (JournalArticle breve : breves) {
                JSONObject jsonWC = CSMapJSonHelper.getBreveCSMapJSON(breve);

                if (lastUpdateTime.before(breve.getCreateDate()))
                    jsonAjout.put(jsonWC);
                else if (lastUpdateTime.before(breve.getModifiedDate()))
                    jsonModif.put(jsonWC);

            }
        }

        json.put(WSConstants.JSON_ADD, jsonAjout);
        json.put(WSConstants.JSON_UPDATE, jsonModif);

        // On récupère toutes les news qui ont été supprimées/dépubliées
        JSONArray jsonSuppr = JSONFactoryUtil.createJSONArray();
        String[] paramsArray = params.split("ids_news=");
        if(paramsArray.length > 1 ) {
            for (String idNews : paramsArray[1].split(",")) {
                JournalArticle journalArticle = JournalArticleLocalServiceUtil.fetchLatestArticle(Long.parseLong(idNews));
                if (journalArticle == null)
                    jsonSuppr.put(idNews);
                else if(journalArticle.getStatus() != WorkflowConstants.STATUS_APPROVED)
                    jsonSuppr.put(idNews);
            }
        }
        json.put(WSConstants.JSON_DELETE, jsonSuppr);

        return WSResponseUtil.buildOkResponse(json);
    }

    @PUT
    @Produces("application/json")
    @Path("/get-emergencies/{last_update_time}")
    public Response getEmergencies(
            @PathParam("last_update_time") String lastUpdateTimeString,
            String param){


        JSONObject json = JSONFactoryUtil.createJSONObject();
        try {
            // On vérifie que lastUpdateTimeString est renseigné
            if (Validator.isNull(lastUpdateTimeString)) {
                return WSResponseUtil.buildErrorResponse(400,
                        "Il manque le paramètre last_update_time");
            }

            // On transforme la date string en date
            Date lastUpdateTime;
            try {
                long lastUpdateTimeLong = Long.parseLong(lastUpdateTimeString);
                lastUpdateTime = DateHelper.getDateFromUnixTimestamp(lastUpdateTimeLong);
            }catch (Exception e) {
                return WSResponseUtil.buildErrorResponse(400, "Format de date incorrect");
            }

            // On vérifie que les ids sont renseignés
            if (Validator.isNull(param)) {
                return WSResponseUtil.buildErrorResponse(400, "Il manque les paramètres ids_emergency_number et ids_emergency_help_category");
            }

            // On vérifie que les ids sont renseignés
            if (!param.contains("ids_emergency_number") && !param.contains("ids_emergency_help_category")) {
                return WSResponseUtil.buildErrorResponse(400, "Il manque l'un des paramètres ids_emergency_number ou ids_emergency_help_category");
            }

            String ids_emergency_number = "";
            String ids_emergency_help_category = "";
            // On vérifie que les ids sont renseignés
            String[] params = param.split("&");
            for( String paramArray : params) {
                if (paramArray.contains("ids_emergency_number")) {
                    String[] paramsArrayNumber = paramArray.split("ids_emergency_number=");
                    if (paramsArrayNumber.length > 1) {
                        ids_emergency_number = paramsArrayNumber[1];
                    }
                } else if (paramArray.contains("ids_emergency_help_category")) {
                    String[] paramsArrayHelp = paramArray.split("ids_emergency_help_category=");
                    if (paramsArrayHelp.length > 1) {
                        ids_emergency_help_category = paramsArrayHelp[1];
                    }
                }
            }

            // Creation des differents JSON pour le resultat
            JSONArray jsonAjout = JSONFactoryUtil.createJSONArray();
            JSONArray jsonModif = JSONFactoryUtil.createJSONArray();
            JSONObject jsonSuppr = JSONFactoryUtil.createJSONObject();

            JSONObject emergencyNumbersJSONDelete = JSONFactoryUtil.createJSONObject();
            JSONObject emergencyHelpsJSONDelete = JSONFactoryUtil.createJSONObject();

            Group csmapGroup = GroupLocalServiceUtil.getGroups(-1, -1).stream().filter(g -> g.getGroupKey().equals(WSConstants.GROUP_KEY)).findFirst().get();
            long csmapGroupId = 0;
            if (Validator.isNotNull(csmapGroup)) {
                csmapGroupId = csmapGroup.getGroupId();
            }

            // Folder Urgences
            JournalFolder emergenciesFolder = null;
            // Folder Numéros urgence
            JournalFolder emergencyNumbersFolder = null;
            // Folder Aides urgence
            JournalFolder emergencyHelpsFolder = null;
            // Recuperation des folders
            List<JournalFolder> folders = JournalFolderLocalServiceUtil.getFolders(csmapGroupId);
            for (JournalFolder folder : folders) {
                // Recuperation du folder Urgences
                if (folder.getName().equals(WSConstants.FOLDER_EMERGENCIES)) {
                    emergenciesFolder = folder;
                }
                else if (folder.getName().equals(WSConstants.FOLDER_EMERGENCY_NUMBERS)) {
                    emergencyNumbersFolder = folder;
                }
                else if (folder.getName().equals(WSConstants.FOLDER_EMERGENCY_HELPS)){
                    emergencyHelpsFolder = folder;
                }
            }

            // Recuperation des JournalArticle dans le dossier Numeros urgence
            List<JournalArticle> emergencyNumbers = new ArrayList<JournalArticle>();
            // Recuperation des Numeros urgence a ADD et UPDATE
            List<JournalArticle> emergencyNumbersAdd = new ArrayList<JournalArticle>();
            List<JournalArticle> emergencyNumbersUpdate = new ArrayList<JournalArticle>();

            // Verification des Numeros urgence si nouveau ou modifie
            emergencyNumbers = JournalArticleLocalServiceUtil.getArticles(csmapGroupId, emergencyNumbersFolder.getFolderId());
            for (JournalArticle emergencyNumber : emergencyNumbers) {
                if(emergencyNumber.getStatus() == WorkflowConstants.STATUS_APPROVED) {
                    if (lastUpdateTime.before(emergencyNumber.getCreateDate()))
                        emergencyNumbersAdd.add(emergencyNumber);
                    else if (lastUpdateTime.before(emergencyNumber.getModifiedDate()))
                        emergencyNumbersAdd.add(emergencyNumber);
                }
            }

            // On recupere les categories des urgences
            AssetVocabulary emergenciesVocabulary = AssetVocabularyHelper.getVocabulary(VocabularyNames.CSMAP_URGENCES, csmapGroupId);

            // Verification des categories urgences si nouveau ou modifie
            Map<AssetCategory, List<JournalArticle>> emergencyHelpsMapAdd = new HashMap<AssetCategory, List<JournalArticle>>();
            Map<AssetCategory, List<JournalArticle>> emergencyHelpsMapUpdate = new HashMap<AssetCategory, List<JournalArticle>>();
            if (Validator.isNotNull(emergenciesVocabulary)) {
                for (AssetCategory category : emergenciesVocabulary.getCategories()) {
                    // Si la category a été modifie ou cree pas besoin de verifier les JournalArticles car doit tous les ajouter au JSON
                    if (lastUpdateTime.before(category.getCreateDate())) {
                        emergencyHelpsMapAdd = this.updateEmergencyMap(emergencyHelpsMapAdd,category);
                    } else if (lastUpdateTime.before(category.getModifiedDate())) {
                        emergencyHelpsMapUpdate = this.updateEmergencyMap(emergencyHelpsMapUpdate,category);
                    }
                    // Si la category a pas ete modifie ou cree  besoin de verifier les JournalArticles car doit ajouter au JSON les changements
                    else {
                        List<AssetEntry> emergencyHelpsAsset = this.getAssetEntriesForCategory(category);
                        for (AssetEntry emergencyHelpAsset : emergencyHelpsAsset) {
                            JournalArticle emergencyHelp = JournalArticleLocalServiceUtil.getArticle(emergencyHelpAsset.getClassPK());
                            if (lastUpdateTime.before(emergencyHelp.getCreateDate())) {
                                emergencyHelpsMapAdd = this.updateEmergencyMap(emergencyHelpsMapAdd,category, emergencyHelp);
                            } else if (lastUpdateTime.before(emergencyHelp.getModifiedDate())) {
                                emergencyHelpsMapUpdate = this.updateEmergencyMap(emergencyHelpsMapUpdate,category, emergencyHelp);
                            }
                        }
                    }
                }
            }

            // Preparation des donnees de la partie DELETE
            // On recupere tous les numeros urgence qui ont ete supprimes
            if(Validator.isNotNull(emergencyNumbers)) {
                for (String idEmergencyNumber : ids_emergency_number.split(",")) {
                    JournalArticle journalArticle = JournalArticleLocalServiceUtil.fetchLatestArticle(Long.parseLong(idEmergencyNumber));
                    if(Validator.isNull(journalArticle) || journalArticle.getStatus()!= WorkflowConstants.STATUS_APPROVED){
                        emergencyNumbersJSONDelete.put(WSConstants.JSON_WC_ID, idEmergencyNumber);
                    }
                }
            }

            // Preparation des donnees de la partie DELETE
            // On recupere tous les aides urgence qui ont ete supprimes
            for (String idEmergencyHelpCategory : ids_emergency_help_category.split(",")) {
                Long idCategory = Long.parseLong(idEmergencyHelpCategory);
                if(Validator.isNull(AssetCategoryLocalServiceUtil.fetchAssetCategory(idCategory))){
                    emergencyHelpsJSONDelete.put(WSConstants.JSON_WC_ID, idEmergencyHelpCategory);
                }
            }

        // Ajout dans la partie ADD
            jsonAjout.put(CSMapJSonHelper.emergencyCSMapJSON(emergencyNumbersAdd, emergencyHelpsMapAdd, true));
            // Ajout dans la partie UPDATE
            jsonModif.put(CSMapJSonHelper.emergencyCSMapJSON(emergencyNumbersUpdate, emergencyHelpsMapUpdate, true));
            // Ajout dans la partie DELETE
            jsonSuppr.put(WSConstants.JSON_WC_EMERGENCY_NUMBERS, emergencyNumbersJSONDelete);
            jsonSuppr.put(WSConstants.JSON_WC_EMERGENCY_HELPS, emergencyHelpsJSONDelete);
            // Ajout de ADD dans le JSON final
            json.put(WSConstants.JSON_ADD, jsonAjout);
            // Ajout de UPDATE dans le JSON final
            json.put(WSConstants.JSON_UPDATE, jsonModif);
            // Ajout de DELETE dans le JSON final
            json.put(WSConstants.JSON_DELETE, jsonSuppr);

        }catch (PortalException e) {
            log.error(e);
            return WSResponseUtil.buildErrorResponse(500, e.getMessage());
        }
        return WSResponseUtil.buildOkResponse(json);

    }

    // Fonction verifiant si pour une List de JournalArticle il y a un Article qui possede l'id passe en parametre
    private static boolean listJournalArticleContainId(List<JournalArticle> journalArticleList, String id) throws PortalException {
        boolean result = false;

        for (JournalArticle journalArticle : journalArticleList){
            if(journalArticle.getResourcePrimKey()== Long.parseLong(id)){
                result = true;
            }
        }

        return result;
    }

    private static Map<AssetCategory,List<JournalArticle>> updateEmergencyMap(Map<AssetCategory,List<JournalArticle>> emergencyHelpsMap, AssetCategory category) throws PortalException {
        List<AssetEntry> emergencyHelpsAsset = AssetEntryLocalServiceUtil.getAssetCategoryAssetEntries(category.getCategoryId());
        for (AssetEntry emergencyHelpAsset : emergencyHelpsAsset) {
            JournalArticle journalArticle = JournalArticleLocalServiceUtil.getLatestArticle(emergencyHelpAsset.getClassPK());
            if(journalArticle.getStatus()==WorkflowConstants.STATUS_APPROVED) {
                if (emergencyHelpsMap.containsKey(category)) {
                    List<JournalArticle> newValue = emergencyHelpsMap.get(category);
                    newValue.add(JournalArticleLocalServiceUtil.getArticle(emergencyHelpAsset.getClassPK()));
                    emergencyHelpsMap.replace(category,
                            emergencyHelpsMap.get(category),
                            newValue);
                } else {
                    List<JournalArticle> newValue = new ArrayList<>();
                    newValue.add(JournalArticleLocalServiceUtil.getArticle(emergencyHelpAsset.getClassPK()));
                    emergencyHelpsMap.put(category, newValue);
                }
            }
        }
        return emergencyHelpsMap;
    }

    private static Map<AssetCategory,List<JournalArticle>> updateEmergencyMap(Map<AssetCategory,List<JournalArticle>> emergencyHelpsMap, AssetCategory category, JournalArticle journalArticle) throws PortalException {
        if(journalArticle.getStatus()==WorkflowConstants.STATUS_APPROVED) {
            if (emergencyHelpsMap.containsKey(category)) {
                List<JournalArticle> newValue = emergencyHelpsMap.get(category);
                newValue.add(journalArticle);
                emergencyHelpsMap.replace(category,
                        emergencyHelpsMap.get(category),
                        newValue);
            } else {
                List<JournalArticle> newValue = new ArrayList<>();
                newValue.add(journalArticle);
                emergencyHelpsMap.put(category, newValue);
            }
        }
        return emergencyHelpsMap;
    }

    private static List<AssetEntry> getAssetEntriesForCategory(AssetCategory category) throws PortalException {
        AssetEntryQuery aq = new AssetEntryQuery();
        long[] categories = new long[1];
        categories[0] = category.getCategoryId();
        aq.setAllCategoryIds(categories);
        List<AssetEntry> emergencyHelpsAsset = AssetEntryServiceUtil.getEntries(aq);
        return emergencyHelpsAsset;
    }

}
