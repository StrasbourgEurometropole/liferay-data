package eu.strasbourg.webservice.csmap.application;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.asset.kernel.service.AssetCategoryPropertyLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;
import eu.strasbourg.service.agenda.model.CacheJson;
import eu.strasbourg.service.agenda.model.Historic;
import eu.strasbourg.service.agenda.service.CacheJsonLocalService;
import eu.strasbourg.service.agenda.service.HistoricLocalService;
import eu.strasbourg.utils.AssetVocabularyHelper;
import eu.strasbourg.utils.DateHelper;
import eu.strasbourg.utils.FileEntryHelper;
import eu.strasbourg.utils.constants.VocabularyNames;
import eu.strasbourg.webservice.csmap.constants.WSConstants;
import eu.strasbourg.webservice.csmap.exception.place.NoDefaultPictoException;
import eu.strasbourg.webservice.csmap.utils.CSMapJSonHelper;
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

    @POST
    @Produces("application/json")
    @Path("/get-themes")
    public Response getThemes(
            @FormParam("ids_themes") String ids_themes) {
        return getThemes("0", ids_themes);
    }

    @POST
    @Produces("application/json")
    @Path("/get-themes/{last_update_time}")
    public Response getThemes(
            @PathParam("last_update_time") String lastUpdateTimeString,
            @FormParam("ids_themes") String ids_themes) {


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
        if (Validator.isNull(ids_themes)) {
            ids_themes = "";
        }

        try {
            // On récupère les catégories du vocabulaire des lieux
            AssetVocabulary placeTypeVocabulary = AssetVocabularyHelper
                    .getGlobalVocabulary(VocabularyNames.EVENT_THEME);
            List<AssetCategory> categories = new ArrayList<>();
            if (Validator.isNotNull(placeTypeVocabulary))
                categories = placeTypeVocabulary.getCategories();

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
                if (Validator.isNotNull(placeTypeVocabulary))
                    for (String idCategory : ids_themes.split(",")) {
                        AssetCategory category = AssetVocabularyHelper.getCategoryByExternalId(placeTypeVocabulary, idCategory);
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
            @FormParam("ids_types") String ids_types) {
        return getTypes("0", ids_types);
    }

    @POST
    @Produces("application/json")
    @Path("/get-types/{last_update_time}")
    public Response getTypes(
            @PathParam("last_update_time") String lastUpdateTimeString,
            @FormParam("ids_types") String ids_types) {


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
        if (Validator.isNull(ids_types)) {
            ids_types = "";
        }

        try {
            // On récupère les catégories du vocabulaire des lieux
            AssetVocabulary placeTypeVocabulary = AssetVocabularyHelper
                    .getGlobalVocabulary(VocabularyNames.EVENT_TYPE);
            List<AssetCategory> categories = new ArrayList<>();
            if (Validator.isNotNull(placeTypeVocabulary))
                categories = placeTypeVocabulary.getCategories();

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
                if (Validator.isNotNull(placeTypeVocabulary))
                    for (String idCategory : ids_types.split(",")) {
                        AssetCategory category = AssetVocabularyHelper.getCategoryByExternalId(placeTypeVocabulary, idCategory);
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
