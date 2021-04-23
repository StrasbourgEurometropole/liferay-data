package eu.strasbourg.webservice.csmap.application;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.asset.kernel.service.*;
import com.liferay.asset.kernel.service.persistence.AssetEntryQuery;
import com.liferay.document.library.kernel.model.DLFolderConstants;
import com.liferay.document.library.kernel.service.DLAppServiceUtil;
import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.service.JournalArticleLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.util.Validator;
import eu.strasbourg.utils.DateHelper;
import eu.strasbourg.utils.JournalArticleHelper;
import eu.strasbourg.utils.constants.VocabularyNames;
import eu.strasbourg.webservice.csmap.constants.WSConstants;
import eu.strasbourg.webservice.csmap.utils.WSResponseUtil;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.jaxrs.whiteboard.JaxrsWhiteboardConstants;

import javax.ws.rs.*;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;
import java.util.*;
import java.util.stream.Stream;

@Component(
        property = {
                JaxrsWhiteboardConstants.JAX_RS_APPLICATION_BASE + "=" + WSConstants.APP_GROUP_BASE + WSConstants.APP_VARIOUS_DATA_BASE,
                JaxrsWhiteboardConstants.JAX_RS_NAME + "=" + WSConstants.APP_VARIOUS_DATA_NAME,
                "auth.verifier.guest.allowed=true",
                "liferay.access.control.disable=true"
        },
        service = Application.class
)
public class VariousDataApplication extends Application{

    public Set<Object> getSingletons() {
        return Collections.singleton(this);
    }

    private final Log log = LogFactoryUtil.getLog(this.getClass().getName());


    @PUT
    @Produces("application/json")
    @Path("/get-emergencies/{last_update_time}")
    public Response getEmergencies(
            @PathParam("last_update_time") String lastUpdateTimeString,
            String ids_emergency_number,
            String ids_emergency_help_category){

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
        if (Validator.isNull(ids_emergency_number)) {
            return WSResponseUtil.buildErrorResponse(400, "Il manque le paramètre ids_emergency_number");
        }

        // On vérifie que les ids sont renseignés
        if (Validator.isNull(ids_emergency_help_category)) {
            return WSResponseUtil.buildErrorResponse(400, "Il manque le paramètre ids_emergency_help_category");
        }

        // Creation des differents JSON pour le resultat
        JSONObject json = JSONFactoryUtil.createJSONObject();

        JSONArray jsonAjout = JSONFactoryUtil.createJSONArray();
        JSONArray jsonModif = JSONFactoryUtil.createJSONArray();
        JSONObject jsonSuppr = JSONFactoryUtil.createJSONObject();

        JSONObject emergencyNumbersJSONDelete = JSONFactoryUtil.createJSONObject();
        JSONObject emergencyHelpsJSONDelete = JSONFactoryUtil.createJSONObject();

        try {

            Group csmapGroup = GroupLocalServiceUtil.getGroups(-1, -1).stream().filter(g -> g.getGroupKey().equals("CSMAP")).findFirst().get();
            long scmapgroupId = 0;
            if (Validator.isNotNull(csmapGroup)) {
                scmapgroupId = csmapGroup.getGroupId();
            }

            // Recuperation des folders
            long repositoryId = DLFolderConstants.getDataRepositoryId(scmapgroupId, DLFolderConstants.DEFAULT_PARENT_FOLDER_ID);
            // Folder Urgences
            Folder emergenciesFolder = DLAppServiceUtil.getFolder(repositoryId, 0, "Urgences");
            // Folder Numéros urgence
            Folder emergencyNumbersFolder = DLAppServiceUtil.getFolder(emergenciesFolder.getFolderId(), 0, "Numéro urgence");
            // Folder Aides urgence
            Folder emergencyHelpsFolder = DLAppServiceUtil.getFolder(emergenciesFolder.getFolderId(), 0, "Aides urgence");

            // Recuperation des JournalArticle dans le dossier Numeros urgence
            List<JournalArticle> emergencyNumbers = new ArrayList<JournalArticle>();
            // Recuperation des Numeros urgence a ADD et UPDATE
            List<JournalArticle> emergencyNumbersAdd = new ArrayList<JournalArticle>();
            List<JournalArticle> emergencyNumbersUpdate = new ArrayList<JournalArticle>();

            // Verification des Numeros urgence si nouveau ou modifie
            emergencyNumbers = JournalArticleLocalServiceUtil.getArticles(scmapgroupId, emergencyNumbersFolder.getFolderId());
            for (JournalArticle emergencyNumber : emergencyNumbers) {
                if (lastUpdateTime.before(emergencyNumber.getCreateDate()))
                    emergencyNumbersAdd.add(emergencyNumber);
                else if (lastUpdateTime.before(emergencyNumber.getModifiedDate()))
                    emergencyNumbersAdd.add(emergencyNumber);
            }

            // On recupere les categories des urgences
            AssetVocabulary emergenciesVocabulary = null;
            List<AssetVocabulary> vocabularies = AssetVocabularyLocalServiceUtil.getAssetVocabularies(-1, -1);
            for (AssetVocabulary vocabulary : vocabularies) {
                if (vocabulary.getName().equals(VocabularyNames.CSMAP_URGENCES)) {
                    emergenciesVocabulary = vocabulary;
                }
            }

            // Verification des categories urgences si nouveau ou modifie
            Map<AssetCategory, List<JournalArticle>> emergencyHelpsMapAdd = new HashMap<AssetCategory, List<JournalArticle>>();
            Map<AssetCategory, List<JournalArticle>> emergencyHelpsMapUpdate = new HashMap<AssetCategory, List<JournalArticle>>();
            if (Validator.isNotNull(emergenciesVocabulary)) {
                for (AssetCategory category : emergenciesVocabulary.getCategories()) {
                    // Si la category a été modifie ou cree pas besoin de verifier les JournalArticles car doit tous les ajouter au JSON
                    if (lastUpdateTime.before(category.getCreateDate())) {
                        emergencyHelpsMapAdd = this.updateEmergencyMap(category);
                    } else if (lastUpdateTime.before(category.getModifiedDate())) {
                        emergencyHelpsMapUpdate = this.updateEmergencyMap(category);
                    }
                    // Si la category a pas ete modifie ou cree  besoin de verifier les JournalArticles car doit ajouter au JSON les changements
                    else {
                        List<AssetEntry> emergencyHelpsAsset = this.getAssetEntriesForCategory(category);
                        for (AssetEntry emergencyHelpAsset : emergencyHelpsAsset) {
                            JournalArticle emergencyHelp = JournalArticleLocalServiceUtil.getArticle(emergencyHelpAsset.getClassPK());
                            if (lastUpdateTime.before(emergencyHelp.getCreateDate())) {
                                emergencyHelpsMapAdd = this.updateEmergencyMap(category, emergencyHelp);
                            } else if (lastUpdateTime.before(emergencyHelp.getModifiedDate())) {
                                emergencyHelpsMapUpdate = this.updateEmergencyMap(category, emergencyHelp);
                            }
                        }
                    }
                }
            }

            // Preparation des donnees de la partie DELETE
            // On recupere tous les numeros urgence qui ont ete supprimes
            if(Validator.isNotNull(emergencyNumbers)) {
                for (String idEmergencyNumber : ids_emergency_number.split(",")) {
                    if (!this.listJournalArticleContainId(emergencyNumbers, idEmergencyNumber))
                        emergencyNumbersJSONDelete.put("id", idEmergencyNumber);
                }
            }

            // Preparation des donnees de la partie DELETE
            // On recupere tous les aides urgence qui ont ete supprimes
            for (String idEmergencyHelpCategory : ids_emergency_help_category.split(",")) {
                Long idCategory = Long.parseLong(idEmergencyHelpCategory);
                if(Validator.isNull(AssetCategoryLocalServiceUtil.getAssetCategory(idCategory))){
                    emergencyHelpsJSONDelete.put("id", idEmergencyHelpCategory);
                }
            }

            // Ajout dans la partie ADD
            jsonAjout.put(this.emergencyCSMapJSON(emergencyNumbersAdd, emergencyHelpsMapAdd, true));
            // Ajout dans la partie UPDATE
            jsonModif.put(this.emergencyCSMapJSON(emergencyNumbersUpdate, emergencyHelpsMapUpdate, true));
            // Ajout dans la partie DELETE
            jsonSuppr.put("emergencyNumbers", emergencyNumbersJSONDelete);
            jsonSuppr.put("emergencyHelps", emergencyHelpsJSONDelete);
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

    // Fonction permettant la creation des parties dans le JSON
    private static JSONObject emergencyCSMapJSON(List<JournalArticle> emergencyNumbers, Map<AssetCategory, List<JournalArticle>> emergencyHelpsMap, boolean maj) throws PortalException {
        JSONObject jsonEmergency = JSONFactoryUtil.createJSONObject();
        JSONObject emergencyNumbersJSON = JSONFactoryUtil.createJSONObject();
        JSONObject emergencyHelpsJSON = JSONFactoryUtil.createJSONObject();
        for(JournalArticle emergencyNumber : emergencyNumbers){
            emergencyNumbersJSON.put("id", emergencyNumber.getResourcePrimKey());
            // CategoryTitle en fonction des differentes langues
            JSONObject titleJSON = JSONFactoryUtil.createJSONObject();
            titleJSON.put("fr_FR", JournalArticleHelper.getJournalArticleFieldValue(emergencyNumber, "Titre", Locale.FRANCE));
            if (Validator.isNotNull(JournalArticleHelper.getJournalArticleFieldValue(emergencyNumber, "Titre", Locale.US))) {
                titleJSON.put("en_US", JournalArticleHelper.getJournalArticleFieldValue(emergencyNumber, "Titre", Locale.US));
            }
            if (Validator.isNotNull(JournalArticleHelper.getJournalArticleFieldValue(emergencyNumber, "Titre", Locale.GERMANY))) {
                titleJSON.put("de_DE", JournalArticleHelper.getJournalArticleFieldValue(emergencyNumber, "Titre", Locale.GERMANY));
            }
            emergencyHelpsJSON.put("title", titleJSON);

            emergencyNumbersJSON.put("order", JournalArticleHelper.getJournalArticleFieldValue(emergencyNumber,"Ordre",Locale.FRANCE));
            emergencyNumbersJSON.put("number", JournalArticleHelper.getJournalArticleFieldValue(emergencyNumber,"Numéro",Locale.FRANCE));
            JSONObject colorJSON = JSONFactoryUtil.createJSONObject();
            colorJSON.put("fontColor", JournalArticleHelper.getJournalArticleFieldValue(emergencyNumber,"CouleurDeLaPolice",Locale.FRANCE));
            colorJSON.put("backgroundColor", JournalArticleHelper.getJournalArticleFieldValue(emergencyNumber,"CouleurDuFond",Locale.FRANCE));
            emergencyNumbersJSON.put("color", colorJSON);
        }
        for(Map.Entry emergencyHelpEntry : emergencyHelpsMap.entrySet()){
            // Recuperation des valeurs du MapEntry
            AssetCategory category = (AssetCategory) emergencyHelpEntry.getKey();
            List<JournalArticle> emergencyHelps = (List<JournalArticle>) emergencyHelpEntry.getValue();

            emergencyHelpsJSON.put("categoryId", category.getCategoryId());
            emergencyHelpsJSON.put("categoryOrder", AssetCategoryPropertyLocalServiceUtil.getCategoryProperty(category.getCategoryId(), "order").getValue());
            // CategoryTitle en fonction des differentes langues
            JSONObject categoryTitleJSON = JSONFactoryUtil.createJSONObject();
            categoryTitleJSON.put("fr_FR", category.getTitle(Locale.FRANCE));
            if (Validator.isNotNull(category.getTitle(Locale.US))) {
                categoryTitleJSON.put("en_US", category.getTitle(Locale.US));
            }
            if (Validator.isNotNull(category.getTitle(Locale.GERMANY))) {
                categoryTitleJSON.put("de_DE", category.getTitle(Locale.GERMANY));
            }
            emergencyHelpsJSON.put("categoryTitle", categoryTitleJSON);
            JSONObject categoryContentJSON = JSONFactoryUtil.createJSONObject();
            for(JournalArticle emergencyHelp : emergencyHelps ) {
                categoryContentJSON.put("title", JournalArticleHelper.getJournalArticleFieldValue(emergencyHelp, "Titre", Locale.FRANCE));
                categoryContentJSON.put("number", JournalArticleHelper.getJournalArticleFieldValue(emergencyHelp, "Numéro", Locale.FRANCE));
                categoryContentJSON.put("order", JournalArticleHelper.getJournalArticleFieldValue(emergencyHelp, "Ordre", Locale.FRANCE));
            }
            emergencyHelpsJSON.put("categoryContent", categoryContentJSON);
        }
        jsonEmergency.put("emergencyNumbers", emergencyNumbersJSON);
        jsonEmergency.put("emergencyHelps", emergencyHelpsJSON);
        return jsonEmergency;
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

    private static Map<AssetCategory,List<JournalArticle>> updateEmergencyMap(AssetCategory category) throws PortalException {
        Map<AssetCategory,List<JournalArticle>> emergencyHelpsMap = new HashMap<>();
        List<AssetEntry> emergencyHelpsAsset = getAssetEntriesForCategory(category);
        for (AssetEntry emergencyHelpAsset : emergencyHelpsAsset) {
            if (emergencyHelpsMap.containsKey(category)) {
                List<JournalArticle> newValue = emergencyHelpsMap.get(category);
                newValue.add(JournalArticleLocalServiceUtil.getArticle(emergencyHelpAsset.getClassPK()));
                emergencyHelpsMap.replace(category,
                        emergencyHelpsMap.get(category),
                        newValue);
            }
            else{
                List<JournalArticle> newValue = new ArrayList<>();
                newValue.add(JournalArticleLocalServiceUtil.getArticle(emergencyHelpAsset.getClassPK()));
                emergencyHelpsMap.put(category,newValue);
            }
        }
        return emergencyHelpsMap;
    }

    private static Map<AssetCategory,List<JournalArticle>> updateEmergencyMap(AssetCategory category, JournalArticle journalArticle) throws PortalException {
        Map<AssetCategory,List<JournalArticle>> emergencyHelpsMap = new HashMap<>();
        if (emergencyHelpsMap.containsKey(category)) {
            List<JournalArticle> newValue = emergencyHelpsMap.get(category);
            newValue.add(journalArticle);
            emergencyHelpsMap.replace(category,
                    emergencyHelpsMap.get(category),
                    newValue);
        }
        else{
            List<JournalArticle> newValue = new ArrayList<>();
            newValue.add(journalArticle);
            emergencyHelpsMap.put(category,newValue);
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
