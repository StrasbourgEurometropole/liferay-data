package eu.strasbourg.webservice.csmap.application;

import com.liferay.dynamic.data.mapping.model.DDMStructure;
import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.model.JournalFolder;
import com.liferay.journal.service.JournalArticleLocalServiceUtil;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import eu.strasbourg.service.csmap.model.Thematic;
import eu.strasbourg.service.csmap.service.ThematicLocalService;
import eu.strasbourg.service.notif.exception.NoSuchServiceNotifException;
import eu.strasbourg.service.notif.model.ServiceNotif;
import eu.strasbourg.service.notif.service.ServiceNotifLocalService;
import eu.strasbourg.utils.DateHelper;
import eu.strasbourg.utils.JournalArticleHelper;
import eu.strasbourg.webservice.csmap.constants.WSConstants;
import eu.strasbourg.webservice.csmap.service.WSSettings;
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

@Component(
        property = {
        JaxrsWhiteboardConstants.JAX_RS_APPLICATION_BASE + "=" + WSConstants.APP_GROUP_BASE + WSConstants.APP_SETTINGS_BASE,
        JaxrsWhiteboardConstants.JAX_RS_NAME + "=" + WSConstants.APP_SETTINGS_NAME,
        "auth.verifier.guest.allowed=true",
        "liferay.access.control.disable=true"
        },
        service = Application.class
)
public class SettingsApplication extends Application {

    public Set<Object> getSingletons() {
        return Collections.singleton(this);
    }

    private final Log log = LogFactoryUtil.getLog(this.getClass().getName());

    @GET
    @Produces("application/json")
    @Path("/get-report-url")
    public Response getReportUrl() {
        JSONObject json = JSONFactoryUtil.createJSONObject();

        try {
            Group csmapGroup = WSCSMapUtil.getGroupByKey(WSConstants.GROUP_KEY_CSMAP);
            long csmapGroupId = csmapGroup.getGroupId();
            JournalFolder reportUrlFolder = WSCSMapUtil.getJournalFolderByGroupAndName(csmapGroupId,WSConstants.FOLDER_PARAMETRAGE);
            long reportUrlFolderId = reportUrlFolder.getFolderId();

            List<JournalArticle> reportUrls = new ArrayList<>(JournalArticleLocalServiceUtil.getArticles(csmapGroupId, reportUrlFolderId));

            for (JournalArticle reportUrl : reportUrls) {
                DDMStructure structure = WSCSMapUtil.getStructureByGroupAndName(csmapGroupId,WSConstants.STRUCTURE_SIGNALEMENT);
                if(structure.getStructureKey().equals(reportUrl.getDDMStructureKey()) && reportUrl.getStatus() == WorkflowConstants.STATUS_APPROVED) {
                    JSONObject reportUrlJSON = JSONFactoryUtil.createJSONObject();
                    reportUrlJSON.put("fr_FR", JournalArticleHelper.getJournalArticleFieldValue(reportUrl, "reportUrl", Locale.FRANCE));
                    json.put(WSConstants.JSON_SETTINGS_REPORTURL, reportUrlJSON);
                }
            }

            if(json.length() == 0){
                return WSResponseUtil.buildOkResponse(json,201);
            }
        } catch (Exception e) {
            log.error(e);
            return WSResponseUtil.buildErrorResponse(500, e.getMessage());
        }
        return WSResponseUtil.buildOkResponse(json);

    }

    @GET
    @Produces("application/json")
    @Path("/get-module-status")
    public Response getModuleStatus() {
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

        try {
            Group csmapGroup = WSCSMapUtil.getGroupByKey(WSConstants.GROUP_KEY_CSMAP);
            long csmapGroupId = csmapGroup.getGroupId();
            JournalFolder reportUrlFolder = WSCSMapUtil.getJournalFolderByGroupAndName(csmapGroupId,WSConstants.FOLDER_PARAMETRAGE);
            long reportUrlFolderId = reportUrlFolder.getFolderId();

            List<JournalArticle> reportUrls = new ArrayList<>(JournalArticleLocalServiceUtil.getArticles(csmapGroupId, reportUrlFolderId));

            for (JournalArticle reportUrl : reportUrls) {
                DDMStructure structure = WSCSMapUtil.getStructureByGroupAndName(csmapGroupId,WSConstants.STRUCTURE_STATUT_MODULES);
                if(structure.getStructureKey().equals(reportUrl.getDDMStructureKey()) && reportUrl.getStatus() == WorkflowConstants.STATUS_APPROVED) {
                    List<String> moduleStatus = new ArrayList<>(WSSettings.getModuleStatusFieldValue(reportUrl));
                    int i;
                    for(i = 0; i < moduleStatus.size(); i++){
                        JSONObject jsonModuleStatus = JSONFactoryUtil.createJSONObject();
                        String module = moduleStatus.get(i);
                        module = module.replaceAll("\\t", "");
                        jsonModuleStatus.put(WSConstants.JSON_SETTINGS_CODE, module);
                        i++;
                        String status = moduleStatus.get(i);
                        status = status.replaceAll("\\t", "");
                        if(status.isEmpty()){
                            status = "false";
                        }
                        jsonModuleStatus.put(WSConstants.JSON_SETTINGS_ISACTIVE, status);
                        jsonArray.put(jsonModuleStatus);
                    }
                }
            }

            if(jsonArray.length() == 0){
                return WSResponseUtil.buildOkResponse(jsonArray,201);
            }
        } catch (Exception e) {
            log.error(e);
            return WSResponseUtil.buildErrorResponse(500, e.getMessage());
        }
        return WSResponseUtil.buildOkResponse(jsonArray);
    }

    @GET
    @Produces("application/json")
    @Path("/get-thematics")
    public Response getThematics() {
        JSONArray json = JSONFactoryUtil.createJSONArray();

        try {
            List<Thematic> thematics = thematicLocalService.getThematics(-1,-1);

            if(thematics.isEmpty()){
                return WSResponseUtil.buildOkResponse(json,201);
            }

            for(Thematic thematic : thematics){
                JSONObject jsonThematic = JSONFactoryUtil.createJSONObject();
                jsonThematic.put("name", thematic.getName());
                jsonThematic.put("favorite", thematic.getFavorite());
                String topics = thematic.getTopics();
                if(Validator.isNotNull(thematic.getTopics()) && !topics.equals("")) {
                    JSONArray jsonTopics = JSONFactoryUtil.createJSONArray();
                    for (String topic : topics.split(",")) {
                        jsonTopics.put(topic);
                    }
                    jsonThematic.put("topics", jsonTopics);
                }
                json.put(jsonThematic);
            }
        } catch (Exception e) {
            log.error(e);
            return WSResponseUtil.buildErrorResponse(500, e.getMessage());
        }
        return WSResponseUtil.buildOkResponse(json);
    }

    @POST
    @Produces("application/json")
    @Path("/get-fcm-topics")
    public Response getFCMTopics(
            @FormParam("topics") String topics) {
        return getFCMTopics("0",topics);
    }

    @POST
    @Produces("application/json")
    @Path("/get-fcm-topics/{last_update_time}")
    public Response getFCMTopics(
            @PathParam("last_update_time") String lastUpdateTimeString,
            @FormParam("topics") String topics) {

        // On transforme la date string en date
        Date lastUpdateTime;
        try {
            long lastUpdateTimeLong = Long.parseLong(lastUpdateTimeString);
            lastUpdateTime = DateHelper.getDateFromUnixTimestamp(lastUpdateTimeLong);
        } catch (Exception e) {
            return WSResponseUtil.buildErrorResponse(400, "Format de date incorrect");
        }

        // On vérifie que les ids sont renseignés
        if (Validator.isNull(topics)) {
            topics = "";
        }

        JSONObject json = JSONFactoryUtil.createJSONObject();

        // On récupère toutes les catégories qui ont été ajoutées ou modifiées
        JSONArray jsonAjout = JSONFactoryUtil.createJSONArray();
        JSONArray jsonModif = JSONFactoryUtil.createJSONArray();

        List<ServiceNotif> services = serviceNotifLocalService.getServiceNotifs(-1,-1);

        for (ServiceNotif service : services) {
            if (lastUpdateTime.before(service.getCreateDate()))
                jsonAjout.put(CSMapJSonHelper.serviceCSMapJSON(service));
            else if (lastUpdateTime.before(service.getModifiedDate()))
                jsonModif.put(CSMapJSonHelper.serviceCSMapJSON(service));
        }

        // AJout du topic ALL
        JSONObject jsonAll = JSONFactoryUtil.createJSONObject();
        jsonAll.put("topic",WSConstants.TOPIC_ALERTE);
        jsonAll.put("name","Alertes");
        jsonAll.put("mandatory",true);
        jsonModif.put(jsonAll);

        json.put(WSConstants.JSON_ADD, jsonAjout);
        json.put(WSConstants.JSON_UPDATE, jsonModif);

        // On récupère toutes les catégories qui ont été supprimées
        JSONArray jsonSuppr = JSONFactoryUtil.createJSONArray();

        if (Validator.isNotNull(topics)) {
                for (String topic : topics.split(",")) {
                    try {
                        if(!topic.equals(WSConstants.TOPIC_ALERTE)) {
                            serviceNotifLocalService.getByTopic(topic);
                        }
                    } catch (NoSuchServiceNotifException e) {
                        jsonSuppr.put(topic);
                    }
                }
        }
        json.put(WSConstants.JSON_DELETE, jsonSuppr);

        if (jsonAjout.length() == 0 && jsonModif.length() == 0 && jsonSuppr.length() == 0)
            return WSResponseUtil.buildOkResponse(json, 201);

        return WSResponseUtil.buildOkResponse(json);
    }

    @GET
    @Produces("application/json")
    @Path("/get-last-recommended-version")
    public Response getLastRecommendedVersion() {
        JSONObject json = JSONFactoryUtil.createJSONObject();

        try {
            Group csmapGroup = WSCSMapUtil.getGroupByKey(WSConstants.GROUP_KEY_CSMAP);
            long csmapGroupId = csmapGroup.getGroupId();
            JournalFolder settingFolder = WSCSMapUtil.getJournalFolderByGroupAndName(csmapGroupId,WSConstants.FOLDER_PARAMETRAGE);
            long settingFolderId = settingFolder.getFolderId();

            List<JournalArticle> settingWebContents = new ArrayList<>(JournalArticleLocalServiceUtil.getArticles(csmapGroupId, settingFolderId));

            for (JournalArticle webContent : settingWebContents) {
                DDMStructure structure = WSCSMapUtil.getStructureByGroupAndName(csmapGroupId,WSConstants.STRUCTURE_LAST_VERSION);
                if(structure.getStructureKey().equals(webContent.getDDMStructureKey()) && webContent.getStatus() == WorkflowConstants.STATUS_APPROVED) {
                    JSONObject webContentJson = JSONFactoryUtil.createJSONObject();
                    webContentJson.put("fr_FR", JournalArticleHelper.getJournalArticleFieldValue(webContent, "lastVersion", Locale.FRANCE));
                    json.put(WSConstants.JSON_SETTINGS_LAST_VERSION, webContentJson);
                    break;
                }
            }

            if(json.length() == 0){
                return WSResponseUtil.buildOkResponse(json,201);
            }
        } catch (Exception e) {
            log.error(e);
            return WSResponseUtil.buildErrorResponse(500, e.getMessage());
        }
        return WSResponseUtil.buildOkResponse(json);

    }

    @Reference
    protected ThematicLocalService thematicLocalService;

    @Reference(unbind = "-")
    protected void setThematicLocalService(ThematicLocalService thematicLocalService) {
        this.thematicLocalService = thematicLocalService;
    }

    @Reference
    protected ServiceNotifLocalService serviceNotifLocalService;

    @Reference(unbind = "-")
    protected void setServiceNotifLocalService(ServiceNotifLocalService serviceNotifLocalService) {
        this.serviceNotifLocalService = serviceNotifLocalService;
    }
}
