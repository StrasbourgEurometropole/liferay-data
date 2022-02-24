package eu.strasbourg.webservice.csmap.application;

import com.liferay.dynamic.data.mapping.model.DDMStructure;
import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.model.JournalFolder;
import com.liferay.journal.service.JournalArticleLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.service.CompanyLocalServiceUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import eu.strasbourg.service.csmap.constants.CodeCacheEnum;
import eu.strasbourg.service.csmap.exception.NoDefaultPictoException;
import eu.strasbourg.service.csmap.model.CsmapCache;
import eu.strasbourg.service.csmap.service.CsmapCacheLocalService;
import eu.strasbourg.service.csmap.service.PlaceCategoriesLocalService;
import eu.strasbourg.service.csmap.utils.ApiCsmapUtil;
import eu.strasbourg.service.place.model.CsmapCacheJson;
import eu.strasbourg.service.place.model.Historic;
import eu.strasbourg.service.place.service.CsmapCacheJsonLocalService;
import eu.strasbourg.service.place.service.HistoricLocalService;
import eu.strasbourg.service.place.service.PlaceLocalService;
import eu.strasbourg.utils.DateHelper;
import eu.strasbourg.utils.JournalArticleHelper;
import eu.strasbourg.webservice.csmap.constants.WSConstants;
import eu.strasbourg.webservice.csmap.utils.CSMapJSonHelper;
import eu.strasbourg.webservice.csmap.utils.WSCSMapUtil;
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
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;

import static com.liferay.portal.kernel.json.JSONFactoryUtil.createJSONObject;

/**
 * @author angelique.champougny
 * @author jeremy.zwickert
 * @author cédric.henry
 */
@Component(
        property = {
                JaxrsWhiteboardConstants.JAX_RS_APPLICATION_BASE + "=" + WSConstants.APP_GROUP_BASE + WSConstants.APP_PLACE_BASE,
                JaxrsWhiteboardConstants.JAX_RS_NAME + "=" + WSConstants.APP_PLACE_NAME,
                "auth.verifier.guest.allowed=true",
                "liferay.access.control.disable=true"
        },
        service = Application.class
)
public class PlaceApplication extends Application {

    public Set<Object> getSingletons() {
        return Collections.singleton(this);
    }

    private final Log log = LogFactoryUtil.getLog(this.getClass().getName());

    @GET
    @Produces("application/json")
    @Path("/get-places")
    public Response getPlaces() {
        return getPlaces("0");
    }

    @GET
    @Produces("application/json")
    @Path("/get-places/{last_update_time}")
    public Response getPlaces(
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
            List<CsmapCacheJson> ajouts = csmapCacheJsonLocalService.getByCreatedDateAndIsActive(lastUpdateTime);
            JSONArray jsonAjout = JSONFactoryUtil.createJSONArray();
            for (CsmapCacheJson cache : ajouts) {
                jsonAjout.put(JSONFactoryUtil.createJSONObject(cache.getJsonLieu()));
            }
            json.put(WSConstants.JSON_ADD, jsonAjout);

            // On récupère tous les lieux qui ont été modifiés
            List<CsmapCacheJson> modifications = csmapCacheJsonLocalService.getByCreatedDateAndModifiedDateAndIsActive(lastUpdateTime);
            JSONArray jsonModif = JSONFactoryUtil.createJSONArray();
            for (CsmapCacheJson cache : modifications) {
                jsonModif.put(JSONFactoryUtil.createJSONObject(cache.getJsonLieu()));
            }
            json.put(WSConstants.JSON_UPDATE, jsonModif);

            JSONArray jsonSuppr = JSONFactoryUtil.createJSONArray();
            // On récupère tous les lieux qui ont été dépubliés
            List<CsmapCacheJson> depubications = csmapCacheJsonLocalService.getByModifiedDateAndIsNotActive(lastUpdateTime);
            for (CsmapCacheJson cache : depubications) {
                jsonSuppr.put(cache.getSigId());
            }

            // On récupère tous les lieux qui ont été supprimés
            List<Historic> suppressions = historicLocalService.getBySuppressionDate(lastUpdateTime);
            for (Historic histo : suppressions) {
                jsonSuppr.put(histo.getSigId());
            }
            json.put(WSConstants.JSON_DELETE, jsonSuppr);

            if (jsonAjout.length() == 0 && jsonModif.length() == 0 && jsonSuppr.length() == 0)
                return WSResponseUtil.buildOkResponse(json, 201);
        } catch (JSONException e) {
            log.error(e);
            return WSResponseUtil.buildErrorResponse(500, e.getMessage());
        }

        return WSResponseUtil.buildOkResponse(json);
    }

    @GET
    @Produces("application/json")
    @Path("/get-hours/{sigid}")
    public Response getHours(
            @PathParam("sigid") String sigid) {

        JSONObject json = JSONFactoryUtil.createJSONObject();

        // On récupère le cache horaires du lieu
        CsmapCacheJson cache = csmapCacheJsonLocalService.fetchCsmapCacheJson(sigid);
        if (Validator.isNotNull(cache) && cache.getIsActive()) {
            try {
                if (cache.getJsonHoraire().equals("{}"))
                    return WSResponseUtil.buildOkResponse(json, 201);
                else
                    json = JSONFactoryUtil.createJSONObject(cache.getJsonHoraire());
            } catch (JSONException e) {
                log.error(e);
                return WSResponseUtil.buildErrorResponse(500, e.getMessage());
            }
        } else {
            if (!cache.getIsActive())
                return WSResponseUtil.buildErrorResponse(404, "Lieu introuvable");
            else if (Validator.isNotNull(placeLocalService.getPlaceBySIGId(sigid)))
                return WSResponseUtil.buildErrorResponse(500, "Cache horaire introuvable");
            else
                return WSResponseUtil.buildErrorResponse(404, "Lieu introuvable");
        }

        return WSResponseUtil.buildOkResponse(json);
    }

    @POST
    @Produces("application/json")
    @Path("/get-categories")
    public Response getCategories(
            @FormParam("ids_category") String idsCategory) {
        return getCategories("0", idsCategory);
    }

    @POST
    @Produces("application/json")
    @Path("/get-categories/{last_update_time}")
    public Response getCategories(
            @PathParam("last_update_time") String lastUpdateTimeString,
            @FormParam("ids_category") String idsCategory) {

        JSONObject json;
        CsmapCache cache = csmapCacheLocalService.fetchByCodeCache(CodeCacheEnum.CATEGORIES.getId());
        Date lastUpdateTime;

        try {
            long lastUpdateTimeLong = Long.parseLong(lastUpdateTimeString);
            // On reçoit -3600 depuis l'application, ce qui bypass notre cache
            if (lastUpdateTimeLong < 0) {
                lastUpdateTimeString = "0";
            }
            lastUpdateTime = DateHelper.getDateFromUnixTimestamp(lastUpdateTimeLong);
        } catch (Exception e) {
            return WSResponseUtil.lastUpdateTimeFormatError();
        }

        try {
            if (Validator.isNull(idsCategory)) {
                idsCategory = "";
            }

            if(Validator.isNotNull(cache)){
                if(lastUpdateTimeString.equals("0")){
                    json = createJSONObject(cache.getCacheJson());
                } else if(lastUpdateTime.before(cache.getModifiedDate())){
                    json = ApiCsmapUtil.getCategories(lastUpdateTimeString, idsCategory);
                } else {
                    json = csmapCacheLocalService.getJsonVide();
                }
            } else {
                json = ApiCsmapUtil.getCategories(lastUpdateTimeString, idsCategory);
            }

            if( json.getJSONArray("ADD").length() == 0 &&
                json.getJSONArray("UPDATE").length() == 0 &&
                json.getJSONArray("DELETE").length() == 0)
                return WSResponseUtil.buildOkResponse(json, 201);
        } catch (PortalException | NoDefaultPictoException e) {
            log.error(e);
            return WSResponseUtil.buildErrorResponse(500, e.getMessage());
        }

        return WSResponseUtil.buildOkResponse(json);
    }

    @POST
    @Produces("application/json")
    @Path("/get-simple-pois")
    public Response getSimplePOIs(
            @FormParam("ids_simple_poi") String ids_simple_poi) {
        return getSimplePOIs("0", ids_simple_poi);
    }

    @POST
    @Produces("application/json")
    @Path("/get-simple-pois/{last_update_time}")
    public Response getSimplePOIs(
            @PathParam("last_update_time") String lastUpdateTimeString,
            @FormParam("ids_simple_poi") String ids_simple_poi) {


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
        if (Validator.isNull(ids_simple_poi)) {
            ids_simple_poi = "";
        }

        try {
            Group group = CompanyLocalServiceUtil.getCompany(PortalUtil.getDefaultCompanyId()).getGroup();
            Group csmapGroup = WSCSMapUtil.getGroupByKey(WSConstants.GROUP_KEY_CSMAP);
            long csmapGroupId = csmapGroup.getGroupId();
            JournalFolder placesFolder = WSCSMapUtil.getJournalFolderByGroupAndName(csmapGroupId, WSConstants.FOLDER_POI_SIMPLE);
            long placesFolderId = placesFolder.getFolderId();
            DDMStructure structure = WSCSMapUtil.getStructureByGroupAndName(group.getGroupId(), WSConstants.STRUCTURE_POI_SIMPLE);

            // Recuperation des JournalArticle dans le dossier Numeros urgence
            List<JournalArticle> poiSimples = new ArrayList<>(JournalArticleLocalServiceUtil.getArticles(csmapGroupId, placesFolderId));
            // Recuperation des Numeros urgence a ADD et UPDATE
            List<JournalArticle> poiSimplesAdd = new ArrayList<>();
            List<JournalArticle> poiSimplesUpdate = new ArrayList<>();
            List<String> poiSimplesDelete = new ArrayList<>();

            // Verification des POI Simple si nouveau ou modifie
            for (JournalArticle poiSimple : poiSimples) {
                if (poiSimple.getDDMStructureKey().equals(structure.getStructureKey()) && poiSimple.getStatus() == WorkflowConstants.STATUS_APPROVED) {
                    if (lastUpdateTime.before(poiSimple.getCreateDate())) {
                        poiSimplesAdd.add(poiSimple);
                    } else if (lastUpdateTime.before(poiSimple.getModifiedDate())) {
                        poiSimplesUpdate.add(poiSimple);
                    }
                }
            }

            // Gestion des deletes
            JSONArray SimplePOIsJSONDelete = JSONFactoryUtil.createJSONArray();
            for (String idSimplePOI : ids_simple_poi.split(",")) {
                if (Validator.isNotNull(idSimplePOI)) {
                    JournalArticle journalArticle = JournalArticleHelper.getLatestArticleByResourcePrimKey(Long.parseLong(idSimplePOI));
                    if (Validator.isNull(journalArticle) || journalArticle.getStatus() != WorkflowConstants.STATUS_APPROVED) {
                        poiSimplesDelete.add(idSimplePOI);
                        SimplePOIsJSONDelete.put(idSimplePOI);
                    }
                }
            }

            if (poiSimplesAdd.isEmpty() && poiSimplesUpdate.isEmpty() && poiSimplesDelete.isEmpty()) {
                return WSResponseUtil.buildOkResponse(json, 201);
            }

            // Creation des differents JSON pour le resultat
            JSONArray jsonAjout = CSMapJSonHelper.SimplePOIsCSMapJSON(poiSimplesAdd, lastUpdateTime, true);
            JSONArray jsonModif = CSMapJSonHelper.SimplePOIsCSMapJSON(poiSimplesUpdate, lastUpdateTime, false);
            // Ajout de ADD dans le JSON final
            json.put(WSConstants.JSON_ADD, jsonAjout);
            // Ajout de UPDATE dans le JSON final
            json.put(WSConstants.JSON_UPDATE, jsonModif);
            // Ajout de DELETE dans le JSON final
            json.put(WSConstants.JSON_DELETE, SimplePOIsJSONDelete);

        } catch (Exception e) {
            log.error(e);
            return WSResponseUtil.buildErrorResponse(500, e.getMessage());
        }
        return WSResponseUtil.buildOkResponse(json);

    }

    @POST
    @Produces("application/json")
    @Path("/get-territoires")
    public Response getTerritoires(
            @FormParam("ids_territoires") String ids_territoires) {
        return getTerritoires("0", ids_territoires);
    }

    @POST
    @Produces("application/json")
    @Path("/get-territoires/{last_update_time}")
    public Response getTerritoires(
            @PathParam("last_update_time") String lastUpdateTimeString,
            @FormParam("ids_territoires") String ids_territoires) {


        JSONObject json;
        CsmapCache cache = csmapCacheLocalService.fetchByCodeCache(CodeCacheEnum.TERRITOIRE.getId());
        Date lastUpdateTime;
        try {
            long lastUpdateTimeLong = Long.parseLong(lastUpdateTimeString);
            // On reçoit -3600 depuis l'application, ce qui bypass notre cache
            if (lastUpdateTimeLong < 0) {
                lastUpdateTimeString = "0";
            }
            lastUpdateTime = DateHelper.getDateFromUnixTimestamp(lastUpdateTimeLong);
        } catch (Exception e) {
            return WSResponseUtil.lastUpdateTimeFormatError();
        }

        try {
            // On vérifie que les ids sont renseignés
            if (Validator.isNull(ids_territoires)) {
                ids_territoires = "";
            }

            if(Validator.isNotNull(cache)){
                if(lastUpdateTimeString.equals("0")){
                    json = createJSONObject(cache.getCacheJson());
                } else if(lastUpdateTime.before(cache.getModifiedDate())){
                    json = ApiCsmapUtil.getTerritoires(lastUpdateTimeString, ids_territoires);
                } else {
                    json = csmapCacheLocalService.getJsonVide();
                }
            } else {
                json = ApiCsmapUtil.getTerritoires(lastUpdateTimeString, ids_territoires);
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

    @Reference
    protected CsmapCacheLocalService csmapCacheLocalService;

    @Reference(unbind = "-")
    protected void setCsmapCacheLocalService(CsmapCacheLocalService csmapCacheLocalService) {
        this.csmapCacheLocalService = csmapCacheLocalService;
    }

    @Reference(unbind = "-")
    protected void setPlaceLocalService(PlaceLocalService placeLocalService) {
        this.placeLocalService = placeLocalService;
    }

    @Reference
    protected PlaceLocalService placeLocalService;

    @Reference(unbind = "-")
    protected void setCsmapCacheJsonLocalService(CsmapCacheJsonLocalService csmapCacheJsonLocalService) {
        this.csmapCacheJsonLocalService = csmapCacheJsonLocalService;
    }

    @Reference
    protected CsmapCacheJsonLocalService csmapCacheJsonLocalService;

    @Reference(unbind = "-")
    protected void setHistoricLocalService(HistoricLocalService historicLocalService) {
        this.historicLocalService = historicLocalService;
    }

    @Reference
    protected eu.strasbourg.service.place.service.HistoricLocalService historicLocalService;

    @Reference(unbind = "-")
    protected void setPlaceCategoriesLocalService(PlaceCategoriesLocalService placeCategoriesLocalService) {
        this.placeCategoriesLocalService = placeCategoriesLocalService;
    }

    @Reference
    protected PlaceCategoriesLocalService placeCategoriesLocalService;

}