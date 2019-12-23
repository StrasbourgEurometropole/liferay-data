package eu.strasbourg.service.place.utils;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.ObjectValuePair;
import com.liferay.portal.kernel.util.Validator;
import eu.strasbourg.service.place.model.GoogleMyBusinessHistoric;
import eu.strasbourg.service.place.model.Place;
import eu.strasbourg.service.place.model.PlaceSchedule;
import eu.strasbourg.service.place.model.SubPlace;
import eu.strasbourg.service.place.service.PlaceLocalServiceUtil;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;

import com.google.api.services.mybusiness.v4.MyBusiness;
import com.google.api.services.mybusiness.v4.model.ListAccountsResponse;

import java.io.InputStream;
import java.io.InputStreamReader;

import java.util.Collections;
import java.util.List;

public class GoogleSynchronisation {

    public final static Log log = LogFactoryUtil.getLog(GoogleSynchronisation.class);

    private ServiceContext sc;
    private GoogleMyBusinessHistoric googleMyBusinessHistoric;

    private static final String APPLICATION_NAME =
            "Google My Business API Java Quickstart";
    private static final java.io.File DATA_STORE_DIR =
            new java.io.File(System.getProperty("user.home"),
                    ".store/mybusiness_sample");
    private static FileDataStoreFactory dataStoreFactory;
    private static HttpTransport httpTransport;
    private static final JsonFactory JSON_FACTORY =
            JacksonFactory.getDefaultInstance();
    private static MyBusiness mybusiness;

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
     * Opere l'anonymisation
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
        // vérifi si il y a eu une erreur à la récupération des utilisateurs supprimés
//        if(Validator.isNotNull(usersDeleted.get("errors"))){
//            resultat = "ERREUR";
//            message = usersDeleted.getString("errors");
//        }else{
            // on récupère les lieux actifs
            List<Place> places = PlaceLocalServiceUtil.getPlaces(-1,-1).stream()
                    .filter(p -> p.isApproved()).collect(Collectors.toList());
            if(places.size() == 0){
                this.googleMyBusinessHistoric.addNewOperation("Aucun lieux &agrave; synchroniser");
            }else{
                this.googleMyBusinessHistoric.addNewOperation("Nombre de lieux &agrave; synchroniser : " + places.size());
                for (Place place : places) {
                    // on récupère les horaires du lieu
                    Map<String, List<PlaceSchedule>> schedules = place.getFollowingWeekSchedules(new Date(), Locale.FRANCE);
                    if (schedules != null) {

                        try {
                            httpTransport = GoogleNetHttpTransport.newTrustedTransport();
                            dataStoreFactory = new FileDataStoreFactory(DATA_STORE_DIR);

                            // Calls the authorize() function to get a credential.
                            Credential credential = authorize();

                            // Calls MyBusiness.Builder to create a new instance named 'mybusiness'.
                            mybusiness = new MyBusiness.Builder(httpTransport, JSON_FACTORY, credential)
                                    .setApplicationName(APPLICATION_NAME).build();

                            // Uses the 'mybusiness' instance to send an API call.
                            MyBusiness.Accounts.List accountsList = mybusiness.accounts().list();
                            ListAccountsResponse response = accountsList.execute();
                            List accounts = response.getAccounts();
                        } catch (GeneralSecurityException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        nbPlacesSynchronisated++;
                        this.googleMyBusinessHistoric.addNewOperation("lieu " + place.getAliasCurrentValue() + " synchronis&eacute;");

                    } else {
                        this.googleMyBusinessHistoric.addNewOperation("lieu " + place.getAliasCurrentValue() + " sans horaires");
                    }
                }
            }
//        }

        this.googleMyBusinessHistoric.addNewOperation("############### Synchronisation termin&eacute;e ###############");
        if(resultat.equals("ERREUR")) {
            this.googleMyBusinessHistoric.setErrorDescription("Probleme survenu lors de l'envoi des donn&eacute;es");
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

    /**
     * Demonstrates the authentication flow to use
     * with the Google My Business API Java client library.
     * @return AuthorizationCodeInstalledApp
     */
    private static Credential authorize() throws Exception {
        // Creates an InputStream to hold the client ID and secret.
        InputStream secrets = GoogleSynchronisation.class.getResourceAsStream("/client_secrets.json");

        // Prompts the user if no credential is found.
        if (secrets == null) {
            System.out.println(
                    "Enter Client ID and Secret from Google API Console "
                            + "into place/place-service/src/main/resources/client_secrets.json");
            System.exit(1);
        }

        // Uses the InputStream to create an instance of GoogleClientSecrets.
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY,
                new InputStreamReader(secrets));
        if (clientSecrets.getDetails().getClientId().startsWith("Enter")
                || clientSecrets.getDetails().getClientSecret().startsWith("Enter ")) {
            System.out.println(
                    "Enter Client ID and Secret from Google API Console "
                            + "into place/place-service/src/main/resources/client_secrets.json");
            System.exit(1);
        }

        // Sets up the authorization code flow.
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                httpTransport, JSON_FACTORY, clientSecrets,
                Collections.singleton("https://www.googleapis.com/auth/business.manage"))
                .setDataStoreFactory(dataStoreFactory).build();
        // Returns the credential.
        return new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver()).authorize("user");
    }

    public GoogleMyBusinessHistoric getGoogleMyBusinessHistoric() {
        return googleMyBusinessHistoric;
    }

    public void setGoogleMyBusinessHistoric(GoogleMyBusinessHistoric googleMyBusinessHistoric) {
        this.googleMyBusinessHistoric = googleMyBusinessHistoric;
    }
}
