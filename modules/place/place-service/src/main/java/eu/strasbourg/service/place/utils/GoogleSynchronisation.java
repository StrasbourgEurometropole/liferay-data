package eu.strasbourg.service.place.utils;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import eu.strasbourg.service.place.model.GoogleMyBusinessHistoric;
import eu.strasbourg.service.place.model.Place;
import eu.strasbourg.service.place.model.PlaceSchedule;
import eu.strasbourg.service.place.service.PlaceLocalServiceUtil;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
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
            for (Place place : places) {
                // on récupère les horaires du lieu
                Map<String, List<PlaceSchedule>> schedules = place.getFollowingWeekSchedules(new Date(), Locale.FRANCE);
                if (schedules != null) {

                    nbPlacesSynchronisated++;
                    this.googleMyBusinessHistoric.addNewOperation("lieu " + place.getAliasCurrentValue() + " synchronis&eacute;");

                } else {
                    this.googleMyBusinessHistoric.addNewOperation("lieu " + place.getAliasCurrentValue() + " sans horaires");
                }
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

    public GoogleMyBusinessHistoric getGoogleMyBusinessHistoric() {
        return googleMyBusinessHistoric;
    }

    public void setGoogleMyBusinessHistoric(GoogleMyBusinessHistoric googleMyBusinessHistoric) {
        this.googleMyBusinessHistoric = googleMyBusinessHistoric;
    }
}
