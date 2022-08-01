package eu.strasbourg.webservice.csmap.application;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;
import eu.strasbourg.service.agenda.service.CampaignLocalService;
import eu.strasbourg.service.agenda.service.CsmapCacheJsonLocalService;
import eu.strasbourg.service.agenda.service.HistoricLocalService;
import eu.strasbourg.service.csmap.constants.CodeCacheEnum;
import eu.strasbourg.service.csmap.model.CsmapCache;
import eu.strasbourg.service.csmap.service.AgendaLocalService;
import eu.strasbourg.service.csmap.service.CsmapCacheLocalService;
import eu.strasbourg.service.csmap.utils.ApiCsmapUtil;
import eu.strasbourg.utils.DateHelper;
import eu.strasbourg.webservice.csmap.constants.WSConstants;
import eu.strasbourg.webservice.csmap.utils.WSResponseUtil;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.jaxrs.whiteboard.JaxrsWhiteboardConstants;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;
import java.util.Collections;
import java.util.Date;
import java.util.Set;

import static com.liferay.portal.kernel.json.JSONFactoryUtil.createJSONObject;

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
        JSONObject json;
        CsmapCache cache = csmapCacheLocalService.fetchByCodeCache(CodeCacheEnum.EVENT.getId());
        Date lastUpdateTime;

        try {
            long lastUpdateTimeLong = Long.parseLong(lastUpdateTimeString);
            // On reçoit des timestamp négatif ou très bas à cause de la gestion des Fuseaux horaires depuis l'application, ce qui bypass notre cache
            // On va gérer jusqu'au fuseaux -12h => (12*3600) => 43200
            if (lastUpdateTimeLong <= 43200) {
                lastUpdateTimeString = "0";
            }
            lastUpdateTime = DateHelper.getDateFromUnixTimestamp(lastUpdateTimeLong);
        } catch (Exception e) {
            return WSResponseUtil.lastUpdateTimeFormatError();
        }

        try {
            if(Validator.isNotNull(cache)){
                if(lastUpdateTimeString.equals("0")){
                    json = createJSONObject(cache.getCacheJson());
                } else if(lastUpdateTime.before(cache.getModifiedDate())){
                    json = ApiCsmapUtil.getEvents(lastUpdateTimeString);
                } else {
                    json = csmapCacheLocalService.getJsonVide();
                }
            } else {
                json = ApiCsmapUtil.getEvents(lastUpdateTimeString);
            }

            if( json.getJSONArray("ADD").length() == 0 &&
                json.getJSONArray("UPDATE").length() == 0 &&
                json.getJSONArray("DELETE").length() == 0)
                return WSResponseUtil.buildOkResponse(json, 201);
        } catch (JSONException e) {
            log.error(e);
            return WSResponseUtil.buildErrorResponse(500, e.getMessage());
        }

        return WSResponseUtil.buildOkResponse(json);
    }

    @POST
    @Produces("application/json")
    @Path("/get-themes")
    public Response getThemes(
            @FormParam("ids_theme") String ids_themes) {
        return getThemes("0", ids_themes);
    }

    @POST
    @Produces("application/json")
    @Path("/get-themes/{last_update_time}")
    public Response getThemes(
            @PathParam("last_update_time") String lastUpdateTimeString,
            @FormParam("ids_theme") String ids_themes) {
        JSONObject json;
        CsmapCache cache = csmapCacheLocalService.fetchByCodeCache(CodeCacheEnum.THEME.getId());
        Date lastUpdateTime;
        try {
            long lastUpdateTimeLong = Long.parseLong(lastUpdateTimeString);
            // On reçoit des timestamp négatif ou très bas à cause de la gestion des Fuseaux horaires depuis l'application, ce qui bypass notre cache
            // On va gérer jusqu'au fuseaux -12h => (12*3600) => 43200
            if (lastUpdateTimeLong <= 43200) {
                lastUpdateTimeString = "0";
            }
            lastUpdateTime = DateHelper.getDateFromUnixTimestamp(lastUpdateTimeLong);
        } catch (Exception e) {
            return WSResponseUtil.lastUpdateTimeFormatError();
        }

        try {
            // On vérifie que les ids sont renseignés
            if (Validator.isNull(ids_themes)) {
                ids_themes = "";
            }

            if(Validator.isNotNull(cache)){
                if(lastUpdateTimeString.equals("0")){
                    json = createJSONObject(cache.getCacheJson());
                } else if(lastUpdateTime.before(cache.getModifiedDate())){
                    json = ApiCsmapUtil.getThemes(lastUpdateTimeString, ids_themes);
                } else {
                    json = csmapCacheLocalService.getJsonVide();
                }
            } else {
                json = ApiCsmapUtil.getThemes(lastUpdateTimeString, ids_themes);
            }

            if( json.getJSONArray("ADD").length() == 0 &&
                    json.getJSONArray("UPDATE").length() == 0 &&
                    json.getJSONArray("DELETE").length() == 0)
                return WSResponseUtil.buildOkResponse(json, 201);
        } catch (PortalException e) {
            log.error(e);
            return WSResponseUtil.buildErrorResponse(500, e.getMessage());
        }
        return WSResponseUtil.buildOkResponse(json);
    }

    @POST
    @Produces("application/json")
    @Path("/get-types")
    public Response getTypes(
            @FormParam("ids_type") String ids_types) {
        return getTypes("0", ids_types);
    }

    @POST
    @Produces("application/json")
    @Path("/get-types/{last_update_time}")
    public Response getTypes(
            @PathParam("last_update_time") String lastUpdateTimeString,
            @FormParam("ids_type") String ids_types) {


        JSONObject json;
        CsmapCache cache = csmapCacheLocalService.fetchByCodeCache(CodeCacheEnum.TYPE.getId());
        Date lastUpdateTime;

        try {
            long lastUpdateTimeLong = Long.parseLong(lastUpdateTimeString);
            // On reçoit des timestamp négatif ou très bas à cause de la gestion des Fuseaux horaires depuis l'application, ce qui bypass notre cache
            // On va gérer jusqu'au fuseaux -12h => (12*3600) => 43200
            if (lastUpdateTimeLong <= 43200) {
                lastUpdateTimeString = "0";
            }
            lastUpdateTime = DateHelper.getDateFromUnixTimestamp(lastUpdateTimeLong);
        } catch (Exception e) {
            return WSResponseUtil.lastUpdateTimeFormatError();
        }

        try {
            // On vérifie que les ids sont renseignés
            if (Validator.isNull(ids_types)) {
                ids_types = "";
            }

            if(Validator.isNotNull(cache)){
                if(lastUpdateTimeString.equals("0")){
                    json = createJSONObject(cache.getCacheJson());
                } else if(lastUpdateTime.before(cache.getModifiedDate())){
                    json = ApiCsmapUtil.getTypes(lastUpdateTimeString, ids_types);
                } else {
                    json = csmapCacheLocalService.getJsonVide();
                }
            } else {
                json = ApiCsmapUtil.getTypes(lastUpdateTimeString, ids_types);
            }

            if( json.getJSONArray("ADD").length() == 0 &&
                    json.getJSONArray("UPDATE").length() == 0 &&
                    json.getJSONArray("DELETE").length() == 0)
                return WSResponseUtil.buildOkResponse(json, 201);
        } catch (PortalException e) {
            log.error(e);
            return WSResponseUtil.buildErrorResponse(500, e.getMessage());
        }
        return WSResponseUtil.buildOkResponse(json);
    }

    @GET
    @Produces("application/json")
    @Path("/get-agendas")
    public Response getAgendas() {
        return getAgendas("0");
    }

    @GET
    @Produces("application/json")
    @Path("/get-agendas/{last_update_time}")
    public Response getAgendas(
            @PathParam("last_update_time") String lastUpdateTimeString) {
        JSONObject json;
        CsmapCache cache = csmapCacheLocalService.fetchByCodeCache(CodeCacheEnum.AGENDA.getId());
        Date lastUpdateTime;

        try {
            long lastUpdateTimeLong = Long.parseLong(lastUpdateTimeString);
            // On reçoit des timestamp négatif ou très bas à cause de la gestion des Fuseaux horaires depuis l'application, ce qui bypass notre cache
            // On va gérer jusqu'au fuseaux -12h => (12*3600) => 43200
            if (lastUpdateTimeLong <= 43200) {
                lastUpdateTimeString = "0";
            }
            lastUpdateTime = DateHelper.getDateFromUnixTimestamp(lastUpdateTimeLong);
        } catch (Exception e) {
            return WSResponseUtil.lastUpdateTimeFormatError();
        }

        if(!lastUpdateTimeString.equals("0")){
            if(lastUpdateTime.after(cache.getModifiedDate())){
                return WSResponseUtil.buildOkResponse(JSONFactoryUtil.createJSONObject(), 201);
            }
        }

        try {
            if(Validator.isNotNull(cache)){
                json = JSONFactoryUtil.createJSONObject(cache.getCacheJson());
            } else {
                json = ApiCsmapUtil.getAgenda();
            }
        } catch (Exception e) {
            log.error(e);
            return WSResponseUtil.buildErrorResponse(500, e.getMessage());
        }
        return WSResponseUtil.buildOkResponse(json);
    }

    @Reference
    protected HistoricLocalService historicLocalService;

    @Reference(unbind = "-")
    protected void setHistoricLocalService(HistoricLocalService historicLocalService) {
        this.historicLocalService = historicLocalService;
    }

    @Reference
    protected CsmapCacheLocalService csmapCacheLocalService;

    @Reference(unbind = "-")
    protected void setCsmapCacheLocalService(CsmapCacheLocalService csmapCacheLocalService) {
        this.csmapCacheLocalService = csmapCacheLocalService;
    }

    @Reference
    protected AgendaLocalService agendaLocalService;

    @Reference(unbind = "-")
    protected void setAgendaLocalService(AgendaLocalService agendaLocalService) {
        this.agendaLocalService = agendaLocalService;
    }

    @Reference
    protected CampaignLocalService campaignLocalService;

    @Reference(unbind = "-")
    protected void setCampaignLocalService(CampaignLocalService campaignLocalService) {
        this.campaignLocalService = campaignLocalService;
    }
}
