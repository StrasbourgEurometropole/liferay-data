package eu.strasbourg.webservice.csmap.application;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import eu.strasbourg.service.gtfs.model.Arret;
import eu.strasbourg.service.gtfs.model.CacheAlertJSON;
import eu.strasbourg.service.gtfs.model.Ligne;
import eu.strasbourg.service.gtfs.service.ArretLocalService;
import eu.strasbourg.service.gtfs.service.CacheAlertJSONLocalService;
import eu.strasbourg.service.gtfs.service.CacheHoursJSONLocalService;
import eu.strasbourg.service.gtfs.service.LigneLocalService;
import eu.strasbourg.utils.DateHelper;
import eu.strasbourg.webservice.csmap.constants.WSConstants;
import eu.strasbourg.webservice.csmap.utils.CSMapJSonHelper;
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
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Component(
        property = {
                JaxrsWhiteboardConstants.JAX_RS_APPLICATION_BASE + "=" + WSConstants.APP_GROUP_BASE + WSConstants.APP_TRANSPORT_BASE,
                JaxrsWhiteboardConstants.JAX_RS_NAME + "=" + WSConstants.APP_TRANSPORT_NAME,
                "auth.verifier.guest.allowed=true",
                "liferay.access.control.disable=true"
        },
        service = Application.class
)

public class TransportApplication extends Application {

        public Set<Object> getSingletons() {
                return Collections.singleton(this);
        }

        private final Log log = LogFactoryUtil.getLog(this.getClass().getName());

        @POST
        @Produces("application/json")
        @Path("/get-transports")
        public Response getTransports(
                @FormParam("ids_stops") String ids_stops,
                @FormParam("ids_lines") String ids_lines) {
                return getTransports("0",ids_stops,ids_lines);
        }

        @POST
        @Produces("application/json")
        @Path("/get-transports/{last_update_time}")
        public Response getTransports(
                @PathParam("last_update_time") String lastUpdateTimeString,
                @FormParam("ids_stops") String ids_stops,
                @FormParam("ids_lines") String ids_lines) {
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
                if (Validator.isNull(ids_stops)) {
                        ids_stops = "";
                }

                // On vérifie que les ids sont renseignés
                if (Validator.isNull(ids_lines)) {
                        ids_lines = "";
                }
                List<String> linesListUser = Arrays.asList(ids_lines.split(","));
                try {
                        JSONObject jsonStop = JSONFactoryUtil.createJSONObject();
                        JSONObject jsonLine = JSONFactoryUtil.createJSONObject();
                        List<Arret> arrets = _arretLocalService.getByStatus(WorkflowConstants.STATUS_APPROVED);
                        JSONArray jsonArretAjout = JSONFactoryUtil.createJSONArray();
                        JSONArray jsonArretModif = JSONFactoryUtil.createJSONArray();
                        for(Arret arret : arrets){
                                if(lastUpdateTime.before(arret.getCreateDate())){
                                        jsonArretAjout.put(CSMapJSonHelper.arretCSMapJSON(arret));
                                } else if(lastUpdateTime.before(arret.getModifiedDate())){
                                        jsonArretModif.put(CSMapJSonHelper.arretCSMapJSON(arret));
                                }
                        }
                        // Il peut exister plusieurs entrées de Ligne en BDD corresponsdant à la même ligne (différence de direction/route ou autres)
                        // Ces différences ne nous intéresse pas dans le cadre des informations envoyés à l'application
                        // Etant donné qu'on ne sait pas si au moment où on calcule le JSON si on va retomber sur la même entrée en BDD
                        // On récupère les lignes ordonnées par Date de modification et on gère les doublons de noms de ligne
                        // Ainsi on en traite chaque Ligne qu'une fois et pour l'update on regarde que par rapport à la dernière modifiée
                        List<Ligne> lignes = _ligneLocalService.getByStatusOrderedByModifiedDate(WorkflowConstants.STATUS_APPROVED);
                        JSONArray jsonLigneAjout = JSONFactoryUtil.createJSONArray();
                        JSONArray jsonLigneModif = JSONFactoryUtil.createJSONArray();
                        List<String> lineNumbers = new ArrayList<>();
                        for(Ligne ligne : lignes){
                                String lineName = ligne.getShortName();
                                if(!lineNumbers.contains(lineName)) {
                                        if(!linesListUser.contains(lineName)){
                                                jsonLigneAjout.put(CSMapJSonHelper.lineCSMapJSON(ligne));
                                                lineNumbers.add(lineName);
                                        } else {
                                                if(lastUpdateTime.before(ligne.getModifiedDate()))
                                                        jsonLigneModif.put(CSMapJSonHelper.lineCSMapJSON(ligne));
                                                lineNumbers.add(lineName);
                                        }
                                }
                        }

                        // Gestion des deletes
                        JSONArray stopsJSONDelete = JSONFactoryUtil.createJSONArray();
                        for (String idStop : ids_stops.split(",")) {
                                if(Validator.isNotNull(idStop)) {
                                        Arret arret = _arretLocalService.getByStopId(idStop);
                                        if (Validator.isNull(arret) || arret.getStatus() != WorkflowConstants.STATUS_APPROVED) {
                                                JSONObject stopJSONDelete = JSONFactoryUtil.createJSONObject();
                                                stopJSONDelete.put("stopId", idStop);
                                                stopsJSONDelete.put(stopJSONDelete);
                                        }
                                }
                        }

                        JSONArray linesJSONDelete = JSONFactoryUtil.createJSONArray();
                        for (String idLine : ids_lines.split(",")) {
                                if(Validator.isNotNull(idLine)) {
                                        List<Ligne> lines = _ligneLocalService.getByShortNameAndStatus(idLine,0);
                                        if (Validator.isNull(lines) || lines.isEmpty()) {
                                                JSONObject lineJSONDelete = JSONFactoryUtil.createJSONObject();
                                                lineJSONDelete.put("lineNumber", idLine);
                                                linesJSONDelete.put(lineJSONDelete);
                                        }
                                }
                        }

                        jsonStop.put(WSConstants.JSON_ADD,jsonArretAjout);
                        jsonStop.put(WSConstants.JSON_UPDATE,jsonArretModif);
                        jsonStop.put(WSConstants.JSON_DELETE,stopsJSONDelete);
                        jsonLine.put(WSConstants.JSON_ADD,jsonLigneAjout);
                        jsonLine.put(WSConstants.JSON_UPDATE,jsonLigneModif);
                        jsonLine.put(WSConstants.JSON_DELETE,linesJSONDelete);

                        json.put("stops", jsonStop);
                        json.put("lines", jsonLine);
                        if(jsonArretAjout.length() == 0 && jsonArretModif.length() == 0 && stopsJSONDelete.length() == 0
                           && jsonLigneAjout.length() == 0 && jsonLigneModif.length() == 0 && linesJSONDelete.length() == 0)
                                return WSResponseUtil.buildOkResponse(json, 201);
                } catch (Exception e) {
                        log.error(e);
                        return WSResponseUtil.buildErrorResponse(500, e.getMessage());
                }
                return WSResponseUtil.buildOkResponse(json);
        }

        @GET
        @Produces("application/json")
        @Path("/get-hours/{stopCode}")
        public Response getHours(
                @PathParam("stopCode") String stopCode){
               JSONObject json = JSONFactoryUtil.createJSONObject();
                if(Validator.isNull(stopCode)){
                        return WSResponseUtil.buildErrorResponse(500, "No stopCode");
                }
                List<Arret> arrets = _arretLocalService.getByStopCode(stopCode);
                if(Validator.isNull(arrets) || arrets.isEmpty()){
                        return WSResponseUtil.buildErrorResponse(500, "Not valid stopCode");
                }
                try{
                        json = JSONFactoryUtil.createJSONObject(cacheHoursJsonLocalService.getJsonHour(stopCode, WSConstants.TIMEOUT));
                } catch(Exception e){
                        log.error(e);
                        return WSResponseUtil.buildErrorResponse(500, e.getMessage());
                }
                if(json.length() == 0)
                        return WSResponseUtil.buildOkResponse(json, 201);
                return WSResponseUtil.buildOkResponse(json);
        }

        @GET
        @Produces("application/json")
        @Path("/get-alerts")
        public Response getAlerts() {
                JSONObject json;
                try {
                        // On récupère le cache du json des alertes
                        CacheAlertJSON cache = cacheAlertJsonLocalService.getCacheAlertJSONs(-1, -1).get(0);
                        json = JSONFactoryUtil.createJSONObject(cache.getJsonAlert());
                        if(json.has("error")){
                                return WSResponseUtil.buildErrorResponse(500, json.getString("error"));
                        }
                } catch (JSONException e) {
                        log.error(e);
                        return WSResponseUtil.buildErrorResponse(500, e.getMessage());
                }
                return WSResponseUtil.buildOkResponse(json);
        }

        @Reference(unbind = "-")
        protected void setLigneLocalService(LigneLocalService ligneLocalService) {
                _ligneLocalService = ligneLocalService;
        }

        @Reference
        protected LigneLocalService _ligneLocalService;

        @Reference(unbind = "-")
        protected void setArretLocalService(ArretLocalService arretLocalService) {
                _arretLocalService = arretLocalService;
        }

        @Reference
        protected ArretLocalService _arretLocalService;

        @Reference
        protected CacheAlertJSONLocalService cacheAlertJsonLocalService;

        @Reference(unbind = "-")
        protected void setCacheAlertJsonLocalService(CacheAlertJSONLocalService cacheAlertJsonLocalService) {
                this.cacheAlertJsonLocalService = cacheAlertJsonLocalService;
        }

        @Reference
        protected CacheHoursJSONLocalService cacheHoursJsonLocalService;

        @Reference(unbind = "-")
        protected void setCacheHoursJsonLocalService(CacheHoursJSONLocalService cacheHoursJsonLocalService) {
                this.cacheHoursJsonLocalService = cacheHoursJsonLocalService;
        }
}
