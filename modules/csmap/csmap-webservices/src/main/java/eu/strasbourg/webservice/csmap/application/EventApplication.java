package eu.strasbourg.webservice.csmap.application;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import eu.strasbourg.service.agenda.model.CacheJson;
import eu.strasbourg.service.agenda.model.Historic;
import eu.strasbourg.service.agenda.service.CacheJsonLocalService;
import eu.strasbourg.service.agenda.service.HistoricLocalService;
import eu.strasbourg.utils.DateHelper;
import eu.strasbourg.webservice.csmap.constants.WSConstants;
import eu.strasbourg.webservice.csmap.utils.WSResponseUtil;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.jaxrs.whiteboard.JaxrsWhiteboardConstants;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Component(
        property = {
                JaxrsWhiteboardConstants.JAX_RS_APPLICATION_BASE + "=" + WSConstants.APP_GROUP_BASE + WSConstants.APP_EVENT_BASE,
                JaxrsWhiteboardConstants.JAX_RS_NAME + "=" + WSConstants.APP_EVENT_NAME,
                "auth.verifier.guest.allowed=true",
                "liferay.access.control.disable=true"
        },
        service = Application.class
)
public class EventApplication extends Application {

    public Set<Object> getSingletons() {
        return Collections.singleton(this);
    }

    private final Log log = LogFactoryUtil.getLog(this.getClass().getName());

    @GET
    @Produces("application/json")
    @Path("/get-events")
    public Response getEvents() {
        return getEvents("0");
    }

    @GET
    @Produces("application/json")
    @Path("/get-events/{last_update_time}")
    public Response getEvents(
            @PathParam("last_update_time") String lastUpdateTimeString) {

        // On transforme la date string en date
        Date lastUpdateTime;
        try {
            long lastUpdateTimeLong = Long.parseLong(lastUpdateTimeString);
            lastUpdateTime = DateHelper.getDateFromUnixTimestamp(lastUpdateTimeLong);
        } catch (Exception e) {
            return WSResponseUtil.buildErrorResponse(400, "Format de date incorrect");
        }

        JSONObject json = JSONFactoryUtil.createJSONObject();

        try {
            // On récupère tous les lieux qui ont été ajoutés
            List<CacheJson> ajouts = cacheJsonLocalService.getByCreatedDateAndIsActive(lastUpdateTime);
            JSONArray jsonAjout = JSONFactoryUtil.createJSONArray();
            for (CacheJson cache: ajouts) {
                jsonAjout.put(JSONFactoryUtil.createJSONObject(cache.getJsonEvent()));
            }
            json.put(WSConstants.JSON_ADD, jsonAjout);

            // On récupère tous les lieux qui ont été modifiés
            List<CacheJson> modifications = cacheJsonLocalService.getByCreatedDateAndModifiedDateAndIsActive(lastUpdateTime);
            JSONArray jsonModif = JSONFactoryUtil.createJSONArray();
            for (CacheJson cache: modifications) {
                jsonModif.put(JSONFactoryUtil.createJSONObject(cache.getJsonEvent()));
            }
            json.put(WSConstants.JSON_UPDATE, jsonModif);

            JSONArray jsonSuppr = JSONFactoryUtil.createJSONArray();
            // On récupère tous les lieux qui ont été dépubliés
            List<CacheJson> depubications = cacheJsonLocalService.getByModifiedDateAndIsNotActive(lastUpdateTime);
            for (CacheJson cache: depubications) {
                jsonSuppr.put(cache.getEventId());
            }

            // On récupère tous les lieux qui ont été supprimés
            List<Historic> suppressions = historicLocalService.getBySuppressionDate(lastUpdateTime);
            for (Historic histo: suppressions) {
                jsonSuppr.put(histo.getEventId());
            }
            json.put(WSConstants.JSON_DELETE, jsonSuppr);

            if(jsonAjout.length() == 0 && jsonModif.length() == 0 && jsonSuppr.length() == 0)
                return WSResponseUtil.buildOkResponse(json, 201);
        } catch (JSONException e) {
            log.error(e);
            return WSResponseUtil.buildErrorResponse(500, e.getMessage());
        }

        return WSResponseUtil.buildOkResponse(json);
    }

    @Reference(unbind = "-")
    protected void setCacheJsonLocalService(CacheJsonLocalService cacheJsonLocalService) {
        this.cacheJsonLocalService = cacheJsonLocalService;
    }

    @Reference
    protected eu.strasbourg.service.agenda.service.CacheJsonLocalService cacheJsonLocalService;

    @Reference(unbind = "-")
    protected void setHistoricLocalService(HistoricLocalService historicLocalService) {
        this.historicLocalService = historicLocalService;
    }

    @Reference
    protected eu.strasbourg.service.agenda.service.HistoricLocalService historicLocalService;
}
