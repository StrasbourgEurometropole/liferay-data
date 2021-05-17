package eu.strasbourg.webservice.csmap.application;

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
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import eu.strasbourg.utils.DateHelper;
import eu.strasbourg.utils.JournalArticleHelper;
import eu.strasbourg.webservice.csmap.constants.WSConstants;
import eu.strasbourg.webservice.csmap.service.WSSettings;
import eu.strasbourg.webservice.csmap.utils.CSMapJSonHelper;
import eu.strasbourg.webservice.csmap.utils.WSCSMapUtil;
import eu.strasbourg.webservice.csmap.utils.WSResponseUtil;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.jaxrs.whiteboard.JaxrsWhiteboardConstants;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
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
}
