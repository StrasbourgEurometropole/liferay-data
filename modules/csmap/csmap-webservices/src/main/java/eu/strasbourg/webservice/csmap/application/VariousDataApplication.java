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
import eu.strasbourg.webservice.csmap.service.WSEmergencies;
import eu.strasbourg.webservice.csmap.utils.CSMapJSonHelper;
import eu.strasbourg.webservice.csmap.utils.WSResponseUtil;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.jaxrs.whiteboard.JaxrsWhiteboardConstants;

import javax.ws.rs.*;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;
import java.util.*;
import java.util.stream.Collectors;

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
            return WSResponseUtil.lastUpdateTimeFormatError();
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

    @POST
    @Produces("application/json")
    @Path("/get-emergencies")
    public Response getEmergencies(
            @FormParam("ids_emergency_number") String ids_emergency_number,
            @FormParam("ids_emergency_help_category") String ids_emergency_help_category) {
        return getEmergencies("0", ids_emergency_number,ids_emergency_help_category);
    }

    @POST
    @Produces("application/json")
    @Path("/get-emergencies/{last_update_time}")
    public Response getEmergencies(
            @PathParam("last_update_time") String lastUpdateTimeString,
            @FormParam("ids_emergency_number") String ids_emergency_number,
            @FormParam("ids_emergency_help_category") String ids_emergency_help_category){


        JSONObject json = JSONFactoryUtil.createJSONObject();

        // On transforme la date string en date
        Date lastUpdateTime;
        try {
            long lastUpdateTimeLong = Long.parseLong(lastUpdateTimeString);
            lastUpdateTime = DateHelper.getDateFromUnixTimestamp(lastUpdateTimeLong);
        }catch (Exception e) {
            return WSResponseUtil.lastUpdateTimeFormatError();
        }


        // On vérifie que les ids sont renseignés
        if (Validator.isNull(ids_emergency_number)) {
            ids_emergency_number = "";
        }

        // On vérifie que les ids sont renseignés
        if (Validator.isNull(ids_emergency_help_category)) {
            ids_emergency_help_category = "";
        }

        try {
            // Gestion des numeros urgence
            Map<String,List<JournalArticle>> mapsEmergencyNumbers = new HashMap<>(WSEmergencies.getMapEmergencyNumbers(lastUpdateTime,ids_emergency_number));
            List<JournalArticle> emergencyNumbersAdd = new ArrayList<>(mapsEmergencyNumbers.get(WSConstants.JSON_ADD));
            List<JournalArticle> emergencyNumbersUpdate = new ArrayList<>(mapsEmergencyNumbers.get(WSConstants.JSON_UPDATE));

            // Gestion des aides urgence
            Map<String,Map<AssetCategory, List<JournalArticle>>> mapsEmergencyHelps = new HashMap<>(WSEmergencies.getMapEmergencyHelps(lastUpdateTime,ids_emergency_help_category));
            Map<AssetCategory, List<JournalArticle>> emergencyHelpsMapAdd = new HashMap<>(mapsEmergencyHelps.get(WSConstants.JSON_ADD));
            Map<AssetCategory, List<JournalArticle>> emergencyHelpsMapUpdate = new HashMap<>(mapsEmergencyHelps.get(WSConstants.JSON_UPDATE));

            // Gestion des deletes
            JSONArray emergencyNumbersJSONDelete = JSONFactoryUtil.createJSONArray();
            List<String> idEmergencyNumbers = new ArrayList<>(WSEmergencies.getJSONEmergencyNumbersDelete(ids_emergency_number));
            if(Validator.isNotNull(idEmergencyNumbers)) {
                for (String idEmergencyNumber : idEmergencyNumbers) {
                    if (Validator.isNotNull(idEmergencyNumber)) {
                        JournalArticle journalArticle = JournalArticleHelper.getLatestArticleByResourcePrimKey(Long.parseLong(idEmergencyNumber));
                        if (Validator.isNull(journalArticle) || journalArticle.getStatus() != WorkflowConstants.STATUS_APPROVED) {
                            JSONObject emergencyNumberJSONDelete = JSONFactoryUtil.createJSONObject();
                            emergencyNumberJSONDelete.put(WSConstants.JSON_WC_ID, idEmergencyNumber);
                            emergencyNumbersJSONDelete.put(emergencyNumberJSONDelete);
                        }
                    }
                }
            }

            JSONArray emergencyHelpsJSONDelete = JSONFactoryUtil.createJSONArray();
            List<Long> idEmergencyHelpCategorys = new ArrayList<>(WSEmergencies.getJSONEmergencyHelpsDelete(ids_emergency_help_category));
            for (long idEmergencyHelpCategory : idEmergencyHelpCategorys) {
                if(Validator.isNotNull(idEmergencyHelpCategory)) {
                    if (Validator.isNull(AssetCategoryLocalServiceUtil.fetchAssetCategory(idEmergencyHelpCategory))) {
                        JSONObject emergencyHelpJSONDelete = JSONFactoryUtil.createJSONObject();
                        emergencyHelpJSONDelete.put(WSConstants.JSON_WC_ID,idEmergencyHelpCategory);
                        emergencyHelpsJSONDelete.put(emergencyHelpJSONDelete);
                    }
                }
            }
            JSONObject emergencyJSONDelete = JSONFactoryUtil.createJSONObject();
            emergencyJSONDelete.put(WSConstants.JSON_WC_EMERGENCY_NUMBERS,emergencyNumbersJSONDelete );
            emergencyJSONDelete.put(WSConstants.JSON_WC_EMERGENCY_HELPS,emergencyHelpsJSONDelete );

            if(emergencyNumbersAdd.isEmpty() && emergencyNumbersUpdate.isEmpty() &&
                emergencyHelpsMapAdd.isEmpty() && emergencyHelpsMapUpdate.isEmpty() &&
                idEmergencyNumbers.isEmpty() && idEmergencyHelpCategorys.isEmpty()){
                    return WSResponseUtil.buildOkResponse(json,201);
            }

            // Creation des differents JSON pour le resultat
            JSONArray jsonAjout = JSONFactoryUtil.createJSONArray();
            JSONArray jsonModif = JSONFactoryUtil.createJSONArray();
            JSONArray jsonSuppr = JSONFactoryUtil.createJSONArray();

             // Ajout dans la partie ADD
            jsonAjout.put(CSMapJSonHelper.emergencyCSMapJSON(emergencyNumbersAdd, emergencyHelpsMapAdd));
            // Ajout dans la partie UPDATE
            jsonModif.put(CSMapJSonHelper.emergencyCSMapJSON(emergencyNumbersUpdate, emergencyHelpsMapUpdate));
            // Ajout dans la partie DELETE
            jsonSuppr.put(emergencyJSONDelete);
            // Ajout de ADD dans le JSON final
            json.put(WSConstants.JSON_ADD, jsonAjout);
            // Ajout de UPDATE dans le JSON final
            json.put(WSConstants.JSON_UPDATE, jsonModif);
            // Ajout de DELETE dans le JSON final
            json.put(WSConstants.JSON_DELETE, jsonSuppr);

        }catch (PortalException e) {
            log.error(e);
            return WSResponseUtil.buildErrorResponse(500, e.getMessage());
        }catch (Exception e) {
            log.error(e);
            return WSResponseUtil.buildErrorResponse(500, e.getMessage());
        }
        return WSResponseUtil.buildOkResponse(json);

    }

    @POST
    @Produces("application/json")
    @Path("/get-social-networks")
    public Response getSocialNetworks() {
        return getSocialNetworks("0", "");
    }

    @POST
    @Produces("application/json")
    @Path("/get-social-networks/{last_update_time}")
    public Response getSocialNetworks(
            @PathParam("last_update_time") String lastUpdateTimeString,
            @FormParam("ids_social_network") String ids_social_network) {


        JSONObject json = JSONFactoryUtil.createJSONObject();

        // On transforme la date string en date
        Date lastUpdateTime;
        try {
            long lastUpdateTimeLong = Long.parseLong(lastUpdateTimeString);
            lastUpdateTime = DateHelper.getDateFromUnixTimestamp(lastUpdateTimeLong);
        } catch (Exception e) {
            return WSResponseUtil.lastUpdateTimeFormatError();
        }

        // On vérifie que les ids sont renseignés
        if (Validator.isNull(ids_social_network)) {
            ids_social_network = "";
        }

        return WSResponseUtil.buildOkResponse(json);
    }

    @GET
    @Produces("application/json")
    @Path("/get-general-conditions")
    public Response getGeneralConditions() {
        return getGeneralConditions("0");
    }

    @GET
    @Produces("application/json")
    @Path("/get-general-conditions/{last_update_time}")
    public Response getGeneralConditions(
            @PathParam("last_update_time") String lastUpdateTimeString){


        JSONObject json = JSONFactoryUtil.createJSONObject();

        // On transforme la date string en date
        Date lastUpdateTime;
        try {
            long lastUpdateTimeLong = Long.parseLong(lastUpdateTimeString);
            lastUpdateTime = DateHelper.getDateFromUnixTimestamp(lastUpdateTimeLong);
        }catch (Exception e) {
            return WSResponseUtil.lastUpdateTimeFormatError();
        }

        try {
            long csmapGroupId = WSEmergencies.getGroupId(WSConstants.GROUP_KEY);
            long  generalConditionsFolderId = WSEmergencies.getEmergencyFolderId(WSConstants.FOLDER_GENERAL_CONDITIONS,csmapGroupId);

            // Recuperation des JournalArticle dans le dossier Numeros urgence
            List<JournalArticle> generalConditions = new ArrayList<>(JournalArticleLocalServiceUtil.getArticles(csmapGroupId, generalConditionsFolderId));
            // Recuperation des Numeros urgence a ADD et UPDATE
            List<JournalArticle> generalConditionsAdd = new ArrayList<>();
            List<JournalArticle> generalConditionsUpdate = new ArrayList<>();

            // Verification des Numeros urgence si nouveau ou modifie
            for (JournalArticle generalCondition : generalConditions) {
                if(generalCondition.getStatus() == WorkflowConstants.STATUS_APPROVED) {
                    if (lastUpdateTime.before(generalCondition.getCreateDate())) {
                        generalConditionsAdd.add(generalCondition);
                    }
                    else if (lastUpdateTime.before(generalCondition.getModifiedDate())) {
                        generalConditionsUpdate.add(generalCondition);
                    }
                }
            }

            if(generalConditionsAdd.isEmpty() && generalConditionsUpdate.isEmpty()){
                return WSResponseUtil.buildOkResponse(json,201);
            }

            // Creation des differents JSON pour le resultat
            JSONArray jsonAjout = JSONFactoryUtil.createJSONArray();
            JSONArray jsonModif = JSONFactoryUtil.createJSONArray();

            // Ajout dans la partie ADD
            jsonAjout.put(CSMapJSonHelper.generalConditionsCSMapJSON(generalConditionsAdd));
            // Ajout dans la partie UPDATE
            jsonModif.put(CSMapJSonHelper.generalConditionsCSMapJSON(generalConditionsUpdate));
            // Ajout dans la partie DELETE
            // Ajout de ADD dans le JSON final
            json.put(WSConstants.JSON_ADD, jsonAjout);
            // Ajout de UPDATE dans le JSON final
            json.put(WSConstants.JSON_UPDATE, jsonModif);
            // Ajout de DELETE dans le JSON final

        }catch (PortalException e) {
            log.error(e);
            return WSResponseUtil.buildErrorResponse(500, e.getMessage());
        }catch (Exception e) {
            log.error(e);
            return WSResponseUtil.buildErrorResponse(500, e.getMessage());
        }
        return WSResponseUtil.buildOkResponse(json);

    }
}
