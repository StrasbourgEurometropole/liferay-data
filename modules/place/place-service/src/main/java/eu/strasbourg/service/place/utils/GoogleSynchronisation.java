package eu.strasbourg.service.place.utils;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Ticket;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.TicketLocalServiceUtil;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.Validator;
import eu.strasbourg.service.place.model.GoogleMyBusinessHistoric;
import eu.strasbourg.service.place.model.Place;
import eu.strasbourg.service.place.model.PlaceSchedule;
import eu.strasbourg.service.place.service.PlaceLocalServiceUtil;
import eu.strasbourg.utils.PasserelleHelper;
import eu.strasbourg.utils.StrasbourgPropsUtil;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

public class GoogleSynchronisation {

    public final static Log log = LogFactoryUtil.getLog(GoogleSynchronisation.class);

    private ServiceContext sc;
    private GoogleMyBusinessHistoric googleMyBusinessHistoric;

    /**
     * Constructeur de base
     * @param sc Contexte de la requete
     * @param googleMyBusinessHistoric Entite de suivi de la synchronisation
     */
    public GoogleSynchronisation (ServiceContext sc, GoogleMyBusinessHistoric googleMyBusinessHistoric) {
        this.sc = sc;
        this.googleMyBusinessHistoric = googleMyBusinessHistoric;
    }

    /**
     * Opere la synchronisation
     */
    public void doSynchronisation() {
        this.googleMyBusinessHistoric.setStartDate(new Date());

        // Import des donnees du flux
        synchronisationData();

        this.googleMyBusinessHistoric.setFinishDate(new Date());
    }

    /**
     * Envoi les données à google
     */
    private void synchronisationData() {
        Timestamp startTimestamp = new Timestamp(System.currentTimeMillis());
        this.googleMyBusinessHistoric.addNewOperation("############### Synchronisation horaires lieux ###############");
        String resultat = "";
        String message = "";
        int nbPlacesSynchronisated = 0;

        // on récupère les lieux actifs
        List<Place> places = PlaceLocalServiceUtil.getPlaces(-1,-1).stream()
                .filter(p -> p.isApproved()).collect(Collectors.toList());
        if(places.size() == 0){
            this.googleMyBusinessHistoric.addNewOperation("Aucun lieux &agrave; synchroniser");
        }else{
            this.googleMyBusinessHistoric.addNewOperation("Nombre de lieux &agrave; synchroniser : " + places.size());
            // récupère l'accès token
            this.googleMyBusinessHistoric.addNewOperation("R&eacute;cup&egrave;re l'access_token");
            JSONObject json = null;
            try {
                json = getJSONAccesToken();
                String error = json.getString("error");
                if (Validator.isNotNull(error)) {
                    message = json.getString("error_description");
                    resultat = "ERREUR";
                }else {
                    String accessToken = json.getString("access_token");
                    this.googleMyBusinessHistoric.addNewOperation("Access_token : " + accessToken);
                    for (Place place : places) {
                        // on récupère les horaires du lieu
                        Map<String, List<PlaceSchedule>> schedules = place.getFollowingWeekSchedules(new Date(), Locale.FRANCE);
                        if (schedules != null) {
                            // Synchronise à google map
                            nbPlacesSynchronisated++;
                            this.googleMyBusinessHistoric.addNewOperation("lieu " + place.getAliasCurrentValue() + " synchronis&eacute;");

                        } else {
                            this.googleMyBusinessHistoric.addNewOperation("lieu " + place.getAliasCurrentValue() + " sans horaires");
                        }
                    }
                }
            } catch (Exception e) {
                message = e.getMessage();
                resultat = "ERREUR";
            }
        }


        this.googleMyBusinessHistoric.addNewOperation("############### Synchronisation termin&eacute;e ###############");
        if(resultat.equals("ERREUR")) {
            this.googleMyBusinessHistoric.setErrorDescription("Probleme survenu lors de la synchronisation");
            this.googleMyBusinessHistoric.setErrorStackTrace(message);
            this.googleMyBusinessHistoric.setResult(0);
            log.error(message);
        }else {
            this.googleMyBusinessHistoric.addNewOperation("Nombre de lieux synchronis&eacute;s : " + nbPlacesSynchronisated);
            Timestamp endTimestamp = new Timestamp(System.currentTimeMillis());
            long processTime = (endTimestamp.getTime() - startTimestamp.getTime()) / 1000;
            this.googleMyBusinessHistoric.addNewOperation("Synchronisation effectu&eacute;e en  " + processTime + " secondes.");

            // Succes de l'anonymisation
            this.googleMyBusinessHistoric.setResult(1);
        }
    }

    public JSONObject getJSONAccesToken() throws Exception{
        JSONObject jsonResponse = JSONFactoryUtil.createJSONObject();
        //récupère le refreshToken (className = "" , classPK = 0, type = 98)
        Ticket ticket = TicketLocalServiceUtil.getTickets(-1, -1).stream()
                .filter(t -> t.getClassName().equals("") && t.getClassPK() == 0 && t.getType() == 98).findFirst().get();
        if(ticket != null) {
            StringBuilder postData = new StringBuilder();
            Map<String, Object> params = new LinkedHashMap<String, Object>();
            params.put("refresh_token", ticket.getExtraInfo());
            params.put("client_id", StrasbourgPropsUtil.getGMBClientId());
            params.put("client_secret", StrasbourgPropsUtil.getGMBSecretCode());
            params.put("grant_type", "refresh_token");

            for (Map.Entry<String, Object> param : params.entrySet()) {
                if (postData.length() != 0)
                    postData.append('&');
                postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
                postData.append('=');
                postData.append(URLEncoder
                        .encode(String.valueOf(param.getValue()), "UTF-8"));
            }

            String url = StrasbourgPropsUtil.getGMBAccessTokenURL() + "?" + postData;
            URL u = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) u.openConnection();
            conn.setConnectTimeout(StrasbourgPropsUtil.getWebServiceDefaultTimeout());
            conn.setReadTimeout(StrasbourgPropsUtil.getWebServiceDefaultTimeout());
            conn.setDoOutput(true);
            conn.setFixedLengthStreamingMode(0);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", ContentTypes.APPLICATION_JSON);
            conn.setRequestProperty("charset", "utf-8");
            jsonResponse = PasserelleHelper.readJson(conn);
        }
        return jsonResponse;
    }

    public GoogleMyBusinessHistoric getGoogleMyBusinessHistoric() {
        return googleMyBusinessHistoric;
    }

    public void setGoogleMyBusinessHistoric(GoogleMyBusinessHistoric googleMyBusinessHistoric) {
        this.googleMyBusinessHistoric = googleMyBusinessHistoric;
    }
}
