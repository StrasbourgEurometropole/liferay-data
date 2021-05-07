package eu.strasbourg.webservice.csmap.application;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.model.AssetTagModel;
import com.liferay.asset.kernel.service.AssetCategoryLocalServiceUtil;
import com.liferay.asset.kernel.service.AssetEntryLocalService;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
import com.liferay.dynamic.data.mapping.model.DDMStructure;
import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.model.JournalFolder;
import com.liferay.journal.service.JournalArticleLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import eu.strasbourg.utils.DateHelper;
import eu.strasbourg.utils.JournalArticleHelper;
import eu.strasbourg.webservice.csmap.constants.WSConstants;
import eu.strasbourg.webservice.csmap.service.WSEmergencies;
import eu.strasbourg.webservice.csmap.utils.CSMapJSonHelper;
import eu.strasbourg.webservice.csmap.utils.WSCSMapUtil;
import eu.strasbourg.webservice.csmap.utils.WSResponseUtil;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.jaxrs.whiteboard.JaxrsWhiteboardConstants;

import javax.ws.rs.*;
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

    @POST
    @Produces("application/json")
    @Path("/get-news")
    public Response getNews(
            @FormParam("ids_news") String idsNews) {
        return getNews("0", idsNews);
    }

    @POST
    @Produces("application/json")
    @Path("/get-news/{last_update_time}")
    public Response getNews(
            @PathParam("last_update_time") String lastUpdateTimeString,
            @FormParam("ids_news") String idsNews) {

        // On transforme la date string en date
        Date lastUpdateTime;
        try {
            long lastUpdateTimeLong = Long.parseLong(lastUpdateTimeString);
            lastUpdateTime = DateHelper.getDateFromUnixTimestamp(lastUpdateTimeLong);
        }catch (Exception e) {
            return WSResponseUtil.buildErrorResponse(400, "Format de date incorrect");
        }

        JSONObject json = JSONFactoryUtil.createJSONObject();

        // On récupère les contenu web de structure Brève ayant le tag csmap
        AssetTagModel tag;
        DDMStructure structure;
        try {
            // récupération du group
            Group group = WSCSMapUtil.getGroupByName(WSConstants.GROUP_KEY_STRAS);

            // récupération du tag
            tag = WSCSMapUtil.getTagByGroupAndName(group.getGroupId(), WSConstants.TAG_CSMAP);

            // récupération de la structure
            structure = WSCSMapUtil.getStructureByGroupAndName(group.getGroupId(), WSConstants.STRUCTURE_BREVE);
        }catch (Exception e){
            return WSResponseUtil.buildErrorResponse(500, e.getMessage());
        }

        // On récupère toutes les brèves qui ont été ajoutées ou modifiées
        JSONArray jsonAjout = JSONFactoryUtil.createJSONArray();
        JSONArray jsonModif = JSONFactoryUtil.createJSONArray();

        // récupération des brèves
        List<AssetEntry> entries = assetEntryLocalService.getAssetTagAssetEntries(tag.getTagId());
        for (AssetEntry entry : entries) {
            // récupération de la dernière version du journalArticle
            JournalArticle journalArticle = JournalArticleHelper.getLatestArticleByResourcePrimKey(entry.getClassPK());
            if (structure.getStructureKey().equals(journalArticle.getDDMStructureKey()) && journalArticle.getStatus() == WorkflowConstants.STATUS_APPROVED) {
                JSONObject jsonWC = CSMapJSonHelper.getBreveCSMapJSON(journalArticle);

                if (lastUpdateTime.before(journalArticle.getCreateDate()))
                    jsonAjout.put(jsonWC);
                else if (lastUpdateTime.before(journalArticle.getModifiedDate()))
                    jsonModif.put(jsonWC);
            }
        }

        json.put(WSConstants.JSON_ADD, jsonAjout);
        json.put(WSConstants.JSON_UPDATE, jsonModif);

        // On récupère toutes les news qui ont été supprimées/dépubliées
        JSONArray jsonSuppr = JSONFactoryUtil.createJSONArray();
        if(Validator.isNotNull(idsNews)) {
            for (String idNews : idsNews.split(",")) {
                JournalArticle journalArticle = JournalArticleHelper.getLatestArticleByResourcePrimKey(Long.parseLong(idNews));
                AssetEntry assetEntry = AssetEntryLocalServiceUtil.fetchEntry(JournalArticle.class.getName(), journalArticle.getResourcePrimKey());
                if (journalArticle == null || !assetEntry.getTags().contains(tag))
                    jsonSuppr.put(idNews);
                else if(journalArticle.getStatus() != WorkflowConstants.STATUS_APPROVED)
                    jsonSuppr.put(idNews);
            }
        }
        json.put(WSConstants.JSON_DELETE, jsonSuppr);

        if(jsonAjout.length() == 0 && jsonModif.length() == 0 && jsonSuppr.length() == 0)
            return WSResponseUtil.buildOkResponse(json, 201);

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
    public Response getSocialNetworks(
            @FormParam("ids_social_network") String idsSocialNetwork) {
        return getSocialNetworks("0", idsSocialNetwork);
    }

    @POST
    @Produces("application/json")
    @Path("/get-social-networks/{last_update_time}")
    public Response getSocialNetworks(
            @PathParam("last_update_time") String lastUpdateTimeString,
            @FormParam("ids_social_network") String idsSocialNetwork) {


        JSONObject json = JSONFactoryUtil.createJSONObject();

        // On transforme la date string en date
        Date lastUpdateTime;
        try {
            long lastUpdateTimeLong = Long.parseLong(lastUpdateTimeString);
            lastUpdateTime = DateHelper.getDateFromUnixTimestamp(lastUpdateTimeLong);
        } catch (Exception e) {
            return WSResponseUtil.lastUpdateTimeFormatError();
        }


        Group group;
        DDMStructure structure;
        JournalFolder folder;
        try {
            // récupération du group CSMAP
            group = WSCSMapUtil.getGroupByName(WSConstants.GROUP_KEY);

            // récupération du dossier
            folder = WSCSMapUtil.getJournalFolderByGroupAndName(group.getGroupId(), WSConstants.FOLDER_SOCIAL_NETWORK);

            // récupération de la structure
            structure = WSCSMapUtil.getStructureByGroupAndName(group.getGroupId(), WSConstants.STRUCTURE_SOCIAL_NETWORK);
        }catch (Exception e){
            return WSResponseUtil.buildErrorResponse(500, e.getMessage());
        }

        // On récupère tous les réseaux sociaux qui ont été ajoutés ou modifiés
        JSONArray jsonAjout = JSONFactoryUtil.createJSONArray();
        JSONArray jsonModif = JSONFactoryUtil.createJSONArray();

        // On récupère les contenu web de structure social network du dossier Réseau sociaux
        List<JournalArticle> journalArticles = JournalArticleLocalServiceUtil.getArticles(group.getGroupId(), folder.getFolderId());
        for (JournalArticle journalArticle : journalArticles) {
            if(journalArticle.getDDMStructureKey().equals(structure.getStructureKey()) && journalArticle.getStatus() == WorkflowConstants.STATUS_APPROVED) {
                JSONObject jsonWC = CSMapJSonHelper.getSocialNetworkCSMapJSON(journalArticle);

                if (lastUpdateTime.before(journalArticle.getCreateDate()))
                    jsonAjout.put(jsonWC);
                else if (lastUpdateTime.before(journalArticle.getModifiedDate()))
                    jsonModif.put(jsonWC);
            }
        }

        json.put(WSConstants.JSON_ADD, jsonAjout);
        json.put(WSConstants.JSON_UPDATE, jsonModif);

        // On récupère tous les réseaux sociaux qui ont été supprimées/dépubliées
        JSONArray jsonSuppr = JSONFactoryUtil.createJSONArray();
        if(Validator.isNotNull(idsSocialNetwork)) {
            for (String idSocialNetwork : idsSocialNetwork.split(",")) {
                JournalArticle journalArticle = JournalArticleHelper.getLatestArticleByResourcePrimKey(Long.parseLong(idSocialNetwork));
                if (journalArticle == null)
                    jsonSuppr.put(idSocialNetwork);
                else if(journalArticle.getStatus() != WorkflowConstants.STATUS_APPROVED)
                    jsonSuppr.put(idSocialNetwork);
            }
        }
        json.put(WSConstants.JSON_DELETE, jsonSuppr);

        if(jsonAjout.length() == 0 && jsonModif.length() == 0 && jsonSuppr.length() == 0)
            return WSResponseUtil.buildOkResponse(json, 201);

        return WSResponseUtil.buildOkResponse(json);
    }

    @Reference(unbind = "-")
    protected void setAssetEntryLocalService(AssetEntryLocalService assetEntryLocalService) {
        this.assetEntryLocalService = assetEntryLocalService;
    }

    @Reference
    protected AssetEntryLocalService assetEntryLocalService;

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
            Group csmapGroup = WSCSMapUtil.getGroupByName(WSConstants.GROUP_KEY);
            long csmapGroupId = csmapGroup.getGroupId();
            JournalFolder generalConditionsFolder = WSCSMapUtil.getJournalFolderByGroupAndName(csmapGroupId,WSConstants.FOLDER_DIVERS);
            long generalConditionsFolderId = generalConditionsFolder.getFolderId();

            // Recuperation des JournalArticle dans le dossier Numeros urgence
            List<JournalArticle> generalConditions = new ArrayList<>(JournalArticleLocalServiceUtil.getArticles(csmapGroupId, generalConditionsFolderId));
            // Recuperation des Numeros urgence a ADD et UPDATE
            List<JournalArticle> generalConditionsAdd = new ArrayList<>();
            List<JournalArticle> generalConditionsUpdate = new ArrayList<>();

            // Verification des Numeros urgence si nouveau ou modifie
            for (JournalArticle generalCondition : generalConditions) {
                if(generalCondition.getTitle(Locale.FRANCE).equals(WSConstants.GENERAL_CONDITIONS) && generalCondition.getStatus() == WorkflowConstants.STATUS_APPROVED) {
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

            // Ajout de ADD dans le JSON final
            json.put(WSConstants.JSON_ADD, CSMapJSonHelper.generalConditionsCSMapJSON(generalConditionsAdd));
            // Ajout de UPDATE dans le JSON final
            json.put(WSConstants.JSON_UPDATE, CSMapJSonHelper.generalConditionsCSMapJSON(generalConditionsUpdate));
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
