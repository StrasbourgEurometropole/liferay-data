package eu.strasbourg.webservice.csmap.application;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;
import eu.strasbourg.service.agenda.service.CacheJsonLocalService;
import eu.strasbourg.service.agenda.service.CampaignLocalService;
import eu.strasbourg.service.agenda.service.HistoricLocalService;
import eu.strasbourg.service.csmap.constants.CodeCacheEnum;
import eu.strasbourg.service.csmap.model.CsmapCache;
import eu.strasbourg.service.csmap.service.AgendaLocalService;
import eu.strasbourg.service.csmap.service.CsmapCacheLocalService;
import eu.strasbourg.service.csmap.utils.ApiCsmapUtil;
import eu.strasbourg.utils.AssetVocabularyHelper;
import eu.strasbourg.utils.DateHelper;
import eu.strasbourg.utils.constants.VocabularyNames;
import eu.strasbourg.webservice.csmap.constants.WSConstants;
import eu.strasbourg.webservice.csmap.utils.CSMapJSonHelper;
import eu.strasbourg.webservice.csmap.utils.WSResponseUtil;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.jaxrs.whiteboard.JaxrsWhiteboardConstants;

import javax.ws.rs.*;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;
import java.util.*;

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


        JSONObject json = createJSONObject();

        // On transforme la date string en date
        Date lastUpdateTime;
        try {
            long lastUpdateTimeLong = Long.parseLong(lastUpdateTimeString);
            lastUpdateTime = DateHelper.getDateFromUnixTimestamp(lastUpdateTimeLong);
        } catch (Exception e) {
            return WSResponseUtil.lastUpdateTimeFormatError();
        }

        // On vérifie que les ids sont renseignés
        if (Validator.isNull(ids_themes)) {
            ids_themes = "";
        }

        try {
            // On récupère les catégories du vocabulaire des thèmes agenda
            AssetVocabulary eventThemeVocabulary = AssetVocabularyHelper
                    .getGlobalVocabulary(VocabularyNames.EVENT_THEME);
            List<AssetCategory> categories = new ArrayList<>();
            if (Validator.isNotNull(eventThemeVocabulary))
                categories = eventThemeVocabulary.getCategories();

            // On récupère toutes les catégories qui ont été ajoutées ou modifiées
            JSONArray jsonAjout = JSONFactoryUtil.createJSONArray();
            JSONArray jsonModif = JSONFactoryUtil.createJSONArray();

            for (AssetCategory categ : categories) {
                if (lastUpdateTime.before(categ.getCreateDate()))
                    jsonAjout.put(CSMapJSonHelper.eventThemesCSMapJSON(categ));
                else if (lastUpdateTime.before(categ.getModifiedDate()))
                    jsonModif.put(CSMapJSonHelper.eventThemesCSMapJSON(categ));
            }

            json.put(WSConstants.JSON_ADD, jsonAjout);
            json.put(WSConstants.JSON_UPDATE, jsonModif);

            // On récupère toutes les catégories qui ont été supprimées
            JSONArray jsonSuppr = JSONFactoryUtil.createJSONArray();

            if (Validator.isNotNull(ids_themes)) {
                if (Validator.isNotNull(eventThemeVocabulary))
                    for (String idCategory : ids_themes.split(",")) {
                        AssetCategory category = AssetVocabularyHelper.getCategoryByExternalId(eventThemeVocabulary, idCategory);
                        if (Validator.isNull(category)) {
                            jsonSuppr.put(idCategory);
                        }
                    }
            }
            json.put(WSConstants.JSON_DELETE, jsonSuppr);

            if (jsonAjout.length() == 0 && jsonModif.length() == 0 && jsonSuppr.length() == 0)
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


        JSONObject json = createJSONObject();

        // On transforme la date string en date
        Date lastUpdateTime;
        try {
            long lastUpdateTimeLong = Long.parseLong(lastUpdateTimeString);
            lastUpdateTime = DateHelper.getDateFromUnixTimestamp(lastUpdateTimeLong);
        } catch (Exception e) {
            return WSResponseUtil.lastUpdateTimeFormatError();
        }

        // On vérifie que les ids sont renseignés
        if (Validator.isNull(ids_types)) {
            ids_types = "";
        }

        try {
            // On récupère les catégories du vocabulaire des types agenda
            AssetVocabulary eventTypeVocabulary = AssetVocabularyHelper
                    .getGlobalVocabulary(VocabularyNames.EVENT_TYPE);
            List<AssetCategory> categories = new ArrayList<>();
            if (Validator.isNotNull(eventTypeVocabulary))
                categories = eventTypeVocabulary.getCategories();

            // On récupère toutes les catégories qui ont été ajoutées ou modifiées
            JSONArray jsonAjout = JSONFactoryUtil.createJSONArray();
            JSONArray jsonModif = JSONFactoryUtil.createJSONArray();

            for (AssetCategory categ : categories) {
                if (lastUpdateTime.before(categ.getCreateDate()))
                    jsonAjout.put(CSMapJSonHelper.eventTypesCSMapJSON(categ));
                else if (lastUpdateTime.before(categ.getModifiedDate()))
                    jsonModif.put(CSMapJSonHelper.eventTypesCSMapJSON(categ));
            }

            json.put(WSConstants.JSON_ADD, jsonAjout);
            json.put(WSConstants.JSON_UPDATE, jsonModif);

            // On récupère toutes les catégories qui ont été supprimées
            JSONArray jsonSuppr = JSONFactoryUtil.createJSONArray();

            if (Validator.isNotNull(ids_types)) {
                if (Validator.isNotNull(eventTypeVocabulary))
                    for (String idCategory : ids_types.split(",")) {
                        AssetCategory category = AssetVocabularyHelper.getCategoryByExternalId(eventTypeVocabulary, idCategory);
                        if (Validator.isNull(category)) {
                            jsonSuppr.put(idCategory);
                        }
                    }
            }
            json.put(WSConstants.JSON_DELETE, jsonSuppr);

            if (jsonAjout.length() == 0 && jsonModif.length() == 0 && jsonSuppr.length() == 0)
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
        JSONObject json;
        try {
            CsmapCache cache = csmapCacheLocalService.fetchByCodeCache(CodeCacheEnum.AGENDA.getId());
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
    protected CacheJsonLocalService cacheJsonLocalService;

    @Reference(unbind = "-")
    protected void setCacheJsonLocalService(CacheJsonLocalService cacheJsonLocalService) {
        this.cacheJsonLocalService = cacheJsonLocalService;
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
