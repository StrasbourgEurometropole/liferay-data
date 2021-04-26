package eu.strasbourg.webservice.csmap.application;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.model.AssetTag;
import com.liferay.asset.kernel.service.AssetCategoryLocalServiceUtil;
import com.liferay.asset.kernel.service.AssetEntryLocalService;
import com.liferay.asset.kernel.service.AssetTagLocalService;
import com.liferay.dynamic.data.mapping.model.DDMStructure;
import com.liferay.dynamic.data.mapping.service.DDMStructureLocalService;
import com.liferay.journal.model.JournalArticle;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import eu.strasbourg.utils.DateHelper;
import eu.strasbourg.utils.JournalArticleHelper;
import eu.strasbourg.webservice.csmap.constants.WSConstants;
import eu.strasbourg.webservice.csmap.service.WSEmergencies;
import eu.strasbourg.webservice.csmap.utils.CSMapJSonHelper;
import eu.strasbourg.webservice.csmap.utils.WSResponseUtil;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.jaxrs.whiteboard.JaxrsWhiteboardConstants;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

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
            String params) {
        return getNews("0", params);
    }

    @POST
    @Produces("application/json")
    @Path("/get-news/{last_update_time}")
    public Response getNews(
            @PathParam("last_update_time") String lastUpdateTimeString,
            String params) {

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
        List<JournalArticle> breves = new ArrayList<>();

        // récupération du group
        Group group = groupLocalService.getGroups(-1, -1).stream().filter(g -> g.getGroupKey().equals("Strasbourg.eu")).findFirst().orElse(null);
        if (group == null)
            return WSResponseUtil.buildErrorResponse(500, "Group Strasbourg.eu introuvable");

        // récupération du tag
        AssetTag tag = assetTagLocalService.getGroupTags(group.getGroupId()).stream().filter(t -> t.getName().equals("csmap")).findFirst().orElse(null);
        if (tag == null)
            return WSResponseUtil.buildErrorResponse(500, "Tag csmap introuvable");

        // récupération de la structure
        DDMStructure structure = ddmStructureLocalService.getStructures(group.getGroupId()).stream().filter(s -> s.getName(Locale.FRANCE).equals("Breve")).findFirst().orElse(null);
        if(structure == null)
            return WSResponseUtil.buildErrorResponse(500, "Structure Breve introuvable");

        // On récupère toutes les brèves qui ont été ajoutées ou modifiées
        JSONArray jsonAjout = JSONFactoryUtil.createJSONArray();
        JSONArray jsonModif = JSONFactoryUtil.createJSONArray();

        // récupération des brèves
        List<AssetEntry> entries = assetEntryLocalService.getAssetTagAssetEntries(tag.getTagId());
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

        json.put(WSConstants.JSON_ADD, jsonAjout);
        json.put(WSConstants.JSON_UPDATE, jsonModif);

        // On récupère toutes les news qui ont été supprimées/dépubliées
        JSONArray jsonSuppr = JSONFactoryUtil.createJSONArray();
        String[] paramsArray = params.split("ids_news=");
        if(paramsArray.length > 1 ) {
            for (String idNews : paramsArray[1].split(",")) {
                JournalArticle journalArticle = JournalArticleHelper.getLatestArticleByResourcePrimKey(Long.parseLong(idNews));
                if (journalArticle == null)
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
    public Response getEmergencies() {
        return getEmergencies("0", "ids_emergency_number=","ids_emergency_help_category=");
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
            return WSResponseUtil.buildErrorResponse(400, "Format de date incorrect");
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
            Map<String,List<JournalArticle>> mapsEmergencyNumbers = new HashMap<String, List<JournalArticle>>(WSEmergencies.getMapEmergencyNumbers(lastUpdateTime,ids_emergency_number));
            List<JournalArticle> emergencyNumbersAdd = new ArrayList<JournalArticle>(mapsEmergencyNumbers.get(WSConstants.JSON_ADD));
            List<JournalArticle> emergencyNumbersUpdate = new ArrayList<JournalArticle>(mapsEmergencyNumbers.get(WSConstants.JSON_UPDATE));

            // Gestion des aides urgence
            Map<String,Map<AssetCategory, List<JournalArticle>>> mapsEmergencyHelps = new HashMap<String,Map<AssetCategory, List<JournalArticle>>>(WSEmergencies.getMapEmergencyHelps(lastUpdateTime,ids_emergency_help_category));
            Map<AssetCategory, List<JournalArticle>> emergencyHelpsMapAdd = new HashMap<AssetCategory, List<JournalArticle>>(mapsEmergencyHelps.get(WSConstants.JSON_ADD));
            Map<AssetCategory, List<JournalArticle>> emergencyHelpsMapUpdate = new HashMap<AssetCategory, List<JournalArticle>>(mapsEmergencyHelps.get(WSConstants.JSON_UPDATE));

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
            List<String> idEmergencyHelpCategorys = new ArrayList<>(WSEmergencies.getJSONEmergencyHelpsDelete(ids_emergency_help_category));
            for (String idEmergencyHelpCategory : idEmergencyHelpCategorys) {
                if(Validator.isNotNull(idEmergencyHelpCategory)) {
                    Long idCategory = Long.parseLong(idEmergencyHelpCategory);
                    if (Validator.isNull(AssetCategoryLocalServiceUtil.fetchAssetCategory(idCategory))) {
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
                WSResponseUtil.buildOkResponse(json,201);
            }

            // Creation des differents JSON pour le resultat
            JSONArray jsonAjout = JSONFactoryUtil.createJSONArray();
            JSONArray jsonModif = JSONFactoryUtil.createJSONArray();
            JSONArray jsonSuppr = JSONFactoryUtil.createJSONArray();

            // Ajout dans la partie ADD
            jsonAjout.put(CSMapJSonHelper.emergencyCSMapJSON(emergencyNumbersAdd, emergencyHelpsMapAdd, true));
            // Ajout dans la partie UPDATE
            jsonModif.put(CSMapJSonHelper.emergencyCSMapJSON(emergencyNumbersUpdate, emergencyHelpsMapUpdate, true));
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
        }
        return WSResponseUtil.buildOkResponse(json);

    }


    @Reference(unbind = "-")
    protected void setAssetEntryLocalService(AssetEntryLocalService assetEntryLocalService) {
        this.assetEntryLocalService = assetEntryLocalService;
    }

    @Reference
    protected AssetEntryLocalService assetEntryLocalService;

    @Reference(unbind = "-")
    protected void setAssetTagLocalService(AssetTagLocalService assetTagLocalService) {
        this.assetTagLocalService = assetTagLocalService;
    }

    @Reference
    protected AssetTagLocalService assetTagLocalService;

    @Reference(unbind = "-")
    protected void setDDMStructureLocalService(DDMStructureLocalService ddmStructureLocalService) {
        this.ddmStructureLocalService = ddmStructureLocalService;
    }

    @Reference
    protected DDMStructureLocalService ddmStructureLocalService;

    @Reference(unbind = "-")
    protected void setGroupLocalService(GroupLocalService groupLocalService) {
        this.groupLocalService = groupLocalService;
    }

    @Reference
    protected GroupLocalService groupLocalService;
}