package eu.strasbourg.webservice.csmap.application;

import com.liferay.asset.kernel.service.AssetCategoryLocalServiceUtil;
import com.liferay.journal.model.JournalArticle;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import eu.strasbourg.service.favorite.service.FavoriteLocalService;
import eu.strasbourg.service.gtfs.model.Arret;
import eu.strasbourg.service.gtfs.model.Ligne;
import eu.strasbourg.service.gtfs.service.ArretLocalServiceUtil;
import eu.strasbourg.service.gtfs.service.ArretServiceUtil;
import eu.strasbourg.service.gtfs.service.LigneLocalServiceUtil;
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
import java.util.stream.Collectors;

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
                try {
                        JSONObject jsonStop = JSONFactoryUtil.createJSONObject();
                        JSONObject jsonLine = JSONFactoryUtil.createJSONObject();
                        List<Arret> arrets = ArretLocalServiceUtil.getArrets(-1,-1);
                        JSONArray jsonArretAjout = JSONFactoryUtil.createJSONArray();
                        JSONArray jsonArretModif = JSONFactoryUtil.createJSONArray();
                        for(Arret arret : arrets){
                                if(lastUpdateTime.before(arret.getCreateDate())){
                                        jsonArretAjout.put(CSMapJSonHelper.arretCSMapJSON(arret));
                                } else if(lastUpdateTime.before(arret.getModifiedDate())){
                                        jsonArretModif.put(CSMapJSonHelper.arretCSMapJSON(arret));
                                }
                        }
                        List<Ligne> lignes = LigneLocalServiceUtil.getLignes(-1,-1);
                        JSONArray jsonLigneAjout = JSONFactoryUtil.createJSONArray();
                        JSONArray jsonLigneModif = JSONFactoryUtil.createJSONArray();
                        for(Ligne ligne : lignes){
                                if(lastUpdateTime.before(ligne.getCreateDate())){
                                        jsonLigneAjout.put(CSMapJSonHelper.lineCSMapJSON(ligne));
                                } else if(lastUpdateTime.before(ligne.getModifiedDate())){
                                        jsonLigneModif.put(CSMapJSonHelper.lineCSMapJSON(ligne));
                                }
                        }

                        // Gestion des deletes
                        JSONArray stopsJSONDelete = JSONFactoryUtil.createJSONArray();
                        for (String idStop : ids_stops.split(",")) {
                                if(Validator.isNotNull(idStop)) {
                                        Arret arret = ArretLocalServiceUtil.fetchArret(Long.parseLong(idStop));
                                        if (Validator.isNull(arret)) {
                                                JSONObject stopJSONDelete = JSONFactoryUtil.createJSONObject();
                                                stopJSONDelete.put("stopCode", idStop);
                                                stopsJSONDelete.put(stopJSONDelete);
                                        }
                                }
                        }

                        JSONArray linesJSONDelete = JSONFactoryUtil.createJSONArray();
                        for (String idLine : ids_lines.split(",")) {
                                if(Validator.isNotNull(idLine)) {
                                        Ligne line = LigneLocalServiceUtil.fetchLigne(Long.parseLong(idLine));
                                        if (Validator.isNull(line)) {
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
                } catch (Exception e) {
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
                List<Arret> arrets = ArretLocalServiceUtil.getArrets(-1,-1).stream().filter(a -> a.getCode().equals(stopCode)).collect(Collectors.toList());
                if(Validator.isNull(arrets) || arrets.isEmpty()){
                        return WSResponseUtil.buildErrorResponse(500, "Not valid stopCode");
                }
                try{
                        JSONArray arretsRealTime = ArretServiceUtil.getArretRealTime(stopCode);
                        JSONArray schedulesJSON = JSONFactoryUtil.createJSONArray();
                        for(int i = 0; i < arretsRealTime.length(); i++){
                                JSONObject arretRealTime = arretsRealTime.getJSONObject(i);
                                JSONObject monitoredVehicleJourney = arretRealTime.getJSONObject("MonitoredVehicleJourney");
                                JSONObject monitoredCall = monitoredVehicleJourney.getJSONObject("MonitoredCall");
                                JSONObject scheduleJSON = JSONFactoryUtil.createJSONObject();
                                scheduleJSON.put("lineNumber", monitoredVehicleJourney.getString("LineRef"));
                                scheduleJSON.put("destinationName", monitoredVehicleJourney.getString("DestinationName"));
                                scheduleJSON.put("departureTime", monitoredCall.getString("ExpectedDepartureTime"));
                                schedulesJSON.put(scheduleJSON);
                        }
                        json.put("schedules", schedulesJSON);
                } catch(Exception e){
                        return WSResponseUtil.buildErrorResponse(500, e.getMessage());
                }
                return WSResponseUtil.buildOkResponse(json);
        }
}
