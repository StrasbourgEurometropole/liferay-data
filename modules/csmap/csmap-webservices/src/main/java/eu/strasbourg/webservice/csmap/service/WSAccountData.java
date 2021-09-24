package eu.strasbourg.webservice.csmap.service;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.Validator;
import eu.strasbourg.portlet.familySpace.Family;
import eu.strasbourg.portlet.familySpace.FamilySpaceResponse;
import eu.strasbourg.portlet.familySpace.FamilySpaceWebService;
import eu.strasbourg.portlet.familySpace.Person;
import eu.strasbourg.portlet.mediatheque.borrower.BorrowerResponse;
import eu.strasbourg.portlet.mediatheque.borrower.BorrowerWebService;
import eu.strasbourg.portlet.mediatheque.borrower.Media;
import eu.strasbourg.portlet.mediatheque.constants.CodeErreurMediathequeConstants;
import eu.strasbourg.portlet.mediatheque.mapping.MediathequeMapping;
import eu.strasbourg.portlet.resid.dossier.Adresse;
import eu.strasbourg.portlet.resid.dossier.Contractant;
import eu.strasbourg.portlet.resid.dossier.Dossier;
import eu.strasbourg.portlet.resid.dossier.DossiersResponse;
import eu.strasbourg.portlet.resid.dossier.DossiersWebService;
import eu.strasbourg.portlet.resid.dossier.Forfait;
import eu.strasbourg.portlet.resid.dossier.Vehicule;
import eu.strasbourg.portlet.resid.dossier.Zone;
import eu.strasbourg.utils.StrasbourgPropsUtil;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class WSAccountData {

    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    /**
     * Appelle le WS Mediatheque et traite le retour
     */
    public static JSONObject getMediatheque(String publikUserId) {

        JSONObject response = JSONFactoryUtil.createJSONObject();
        response.put("url", StrasbourgPropsUtil.getMediathequeURL());

        BorrowerResponse borrower = BorrowerWebService.getResponse(publikUserId);

        // Pas de réponse
        if (Validator.isNull(borrower)) {
            response.put("responseCode", 500);
            response.put("errorDescription", "Une erreur technique est survenue");
            response.put("errorTechnical", "Une erreur est survenue, veuillez contacter le webmestre.");
            return response;
        }

        response.put("responseCode", 200);
        String codeErreur = borrower.getCode_erreur();
        String messageErreur = borrower.getErreur();
        // Erreurs retournées par le webService
        if (Validator.isNotNull(codeErreur)) {
            switch (codeErreur) {
                case CodeErreurMediathequeConstants.AUCUNE_ASSOCIATION:
                    response.put("message", "Aucune association trouv\u00e9e");
                    break;
                case CodeErreurMediathequeConstants.DELAI_DEPASSE:
                    response.put("message", "Le compte n'a pas \u00e9t\u00e9 activ\u00e9 dans le temps imparti");
                    break;
                case CodeErreurMediathequeConstants.ASSOCIATION_A_VALIDER:
                    response.put("message", "Le lien d'activation n'a pas \u00e9t\u00e9 effectu\u00e9");
                    break;
                case CodeErreurMediathequeConstants.AUCUN_EMAIL:
                    response.put("message", "L'email n'est pas renseign\u00e9");
                    break;
                case CodeErreurMediathequeConstants.AUCUNE_CARTE:
                    response.put("message", messageErreur);
                    break;
                case CodeErreurMediathequeConstants.ASSOCIATION_SUPPRIMEE:
                    response.put("message", messageErreur);
                    break;
                default:
                    response.put("errorTechnical", messageErreur);
                    break;
            }
            return response;
        }

        // Le webService retourne un message d'erreur sans code (erreur technique de leur côté)
        String messageErreurTechnique = borrower.getErr();
        if (Validator.isNotNull(messageErreurTechnique) && !messageErreurTechnique.equals("0")) {
            response.put("errorDescription", messageErreurTechnique);
            response.put("errorTechnical", "Une erreur est survenue, veuillez contacter le webmestre.");
            return response;
        }

        // tout va bien :D
        // Gestion numéro de carte
        String cardNumber = borrower.getCardNumber();
        response.put("cardNumber", cardNumber);

        // Gestion date d'expiration
        LocalDate expirationDate = borrower.getExpireDate();
        if(Validator.isNotNull(expirationDate)) {
            response.put("expirationDate", expirationDate.format(formatter));
            LocalDate today = LocalDate.now();
            if (today.isAfter(expirationDate)) {
                response.put("messageExpirationDate", "Votre abonnement Pass'relle est arriv\u00e9 \u00e0 \u00e9ch\u00e9ance. Pensez \u00e0 vous r\u00e9abonner !");
            }
            if (today.plusMonths(1).isAfter(expirationDate)) {
                response.put("messageExpirationDate", "Votre abonnement Pass'relle arrive \u00e0 \u00e9ch\u00e9ance dans moins d'un mois, pensez \u00e0 vous r\u00e9abonner !");
            }
        }

        // Gestion des emprunts
        List<Media> borrowings = borrower.getBorrowings();
        if(!borrowings.isEmpty()) {
            JSONArray borrowingsJsonArray = JSONFactoryUtil.createJSONArray();
            if (!borrowings.isEmpty()) {
                for (Media media : borrowings) {
                    JSONObject borrowedMedia = JSONFactoryUtil.createJSONObject();
                    borrowedMedia.put("title", media.getName());
                    if(Validator.isNotNull(media.getType())) {
                        MediathequeMapping typeDoc = MediathequeMapping.get(media.getType());
                        if (typeDoc != null)
                            borrowedMedia.put("type", typeDoc.getName());
                    }
                    if(Validator.isNotNull(media.getReturnDate()))
                        borrowedMedia.put("returnDate", media.getReturnDate().format(formatter));

                    borrowingsJsonArray.put(borrowedMedia);
                }
                response.put("borrowings", borrowingsJsonArray);
            }
        }

        // Gestion des réservations
        List<Media> reservations = borrower.getReservations();
        if (!reservations.isEmpty()) {
            JSONArray reservationsJsonArray = JSONFactoryUtil.createJSONArray();
            if (!reservations.isEmpty()) {
                for (Media media : reservations) {
                    JSONObject reservedMedia = JSONFactoryUtil.createJSONObject();
                    reservedMedia.put("title", media.getName());
                    if(Validator.isNotNull(media.getType())){
                        MediathequeMapping typeDoc = MediathequeMapping.get(media.getType());
                        if (typeDoc != null)
                            reservedMedia.put("type", typeDoc.getName());
                    }

                    reservationsJsonArray.put(reservedMedia);
                }
                response.put("reservations", reservationsJsonArray);
            }
        }

        return response;
    }

    /**
     * Appelle le WS Resid et traite le retour
     */
    public static JSONObject getResid(String publicUserId) {

        JSONObject response = JSONFactoryUtil.createJSONObject();
        response.put("url", StrasbourgPropsUtil.getResidantURL());

        DossiersResponse dossierResponse = DossiersWebService.getResponse(publicUserId);

        // pas de réponse
        if (Validator.isNull(dossierResponse)) {
            response.put("responseCode", 500);
            response.put("errorDescription", "Une erreur technique est survenue");
            response.put("errorTechnical", "Le service est momentan\u00E9ment indisponible, veuillez r\u00E9essayer ult\u00E9rieurement.");
            return response;
        }

        response.put("responseCode", 200);

        // Erreur renvoyé par le webservice
        String messageErreur = dossierResponse.getErreurDescription();
        if (dossierResponse.getCodeRetour() != 0) {
            response.put("message", messageErreur);
            return response;
        }

        List<Dossier> dossiers = dossierResponse.getDossiers();
        // Pas de dossier
        if (dossiers.isEmpty()) {
            response.put("errorTechnical", "Pas de dossier");
            return response;
        }

        // tout va bien :D
        if(!dossiers.isEmpty()) {
            JSONArray files = JSONFactoryUtil.createJSONArray();
            for (Dossier dossier : dossiers) {

                // Gestion du dossier
                JSONObject file = JSONFactoryUtil.createJSONObject();

                // Numéro du dossier
                file.put("number", dossier.getNumeroDossier());

                // Date de fin de validité
                LocalDate dateFinValiditeDossier = dossier.getDateFinValiditeDossier();
                if(Validator.isNotNull(dateFinValiditeDossier)) {
                    file.put("endValidityDate", dateFinValiditeDossier.format(formatter));
                    LocalDate today = LocalDate.now();
                    if(today.isAfter(dateFinValiditeDossier))
                        file.put("messageEndValidityDate", "Votre titre n'est plus valide");
                }

                // Forfaits
                List<Forfait> forfaits = dossier.getForfaits();
                if (forfaits.isEmpty() && dossier.getZone().getTypeUsager().getCode().equals("RESID")) {
                    file.put("messageNoForfait", "Vous n'avez aucun forfait en cours ou à venir");
                }

                // Contractant principal
                JSONObject principalUser = JSONFactoryUtil.createJSONObject();
                Contractant contractantPrincipal = dossier.getContractantPrincipal();
                // nom
                principalUser.put("name", contractantPrincipal.getNom());
                // pénom ?
                // adresse
                JSONObject address = JSONFactoryUtil.createJSONObject();
                Adresse contractantPrincipalAdresse = contractantPrincipal.getAdresse();
                String firstLine = (Validator.isNotNull(contractantPrincipalAdresse.getNumero()) ? contractantPrincipalAdresse.getNumero()+" " : "")
                        + (Validator.isNotNull(contractantPrincipalAdresse.getExtension()) ? contractantPrincipalAdresse.getExtension()+" " : "")
                        + (Validator.isNotNull(contractantPrincipalAdresse.getVoie()) ? contractantPrincipalAdresse.getVoie() : "");
                if(Validator.isNotNull(firstLine.trim()))
                    address.put("firstLine", firstLine);
                if(Validator.isNotNull(contractantPrincipalAdresse.getComplementVoie()))
                    address.put("secondLine", contractantPrincipalAdresse.getComplementVoie());
                if(Validator.isNotNull(contractantPrincipalAdresse.getComplementVoie2()))
                    address.put("thirdLine", contractantPrincipalAdresse.getComplementVoie2());
                address.put("fourthLine", contractantPrincipalAdresse.getCodePostal() + " " + contractantPrincipalAdresse.getVille());
                principalUser.put("address", address);
                file.put("principalUser", principalUser);

                // Zone
                Zone zone = dossier.getZone();
                file.put("zoneName", zone.getIntitule());

                // Type utilisateur
                file.put("userType", zone.getTypeUsager().getCode());

                // Véhicule principal
                Vehicule vehiculePrincipal = dossier.getVehiculePrincipal();
                if(Validator.isNotNull(vehiculePrincipal)) {
                    JSONObject vehiculPrincipalJson = JSONFactoryUtil.createJSONObject();
                    vehiculPrincipalJson.put("registration", vehiculePrincipal.getImmatriculation());
                    if (vehiculePrincipal.hasCarteGriseProvisoire() && Validator.isNotNull(vehiculePrincipal.getDateFinValidite())) {
                        vehiculPrincipalJson.put("expireDate", vehiculePrincipal.getDateFinValidite().format(formatter));
                    }
                    file.put("mainVehicule", vehiculPrincipalJson);
                }

                // Véhicule principal temporaire
                Vehicule vehiculePrincipalTemporaire = dossier.getVehiculePrincipalTemporaire();
                if(Validator.isNotNull(vehiculePrincipalTemporaire)) {
                    JSONObject vehiculePrincipalJsonTemporaire = JSONFactoryUtil.createJSONObject();
                    vehiculePrincipalJsonTemporaire.put("registration", vehiculePrincipalTemporaire.getImmatriculation());
                    if (Validator.isNotNull(vehiculePrincipalTemporaire.getDateFinValidite())){
                        vehiculePrincipalJsonTemporaire.put("expireDate", vehiculePrincipalTemporaire.getDateFinValidite().format(formatter));
                    }
                    file.put("mainTemporaryVehicule", vehiculePrincipalJsonTemporaire);
                }

                // Périodes des forfaits
                if (!forfaits.isEmpty()) {
                    JSONArray periodJson = JSONFactoryUtil.createJSONArray();
                    LocalDate startDate = null, endDate = null;
                    for (Forfait forfait : forfaits) {
                        if(Validator.isNotNull(startDate)){
                            startDate = forfait.getDateDebut();
                            endDate = forfait.getDateFin();
                        }else{
                            if(endDate.plusDays(1) == forfait.getDateDebut())
                                endDate = forfait.getDateFin();
                            else{
                                String period = formatPeriode(startDate, endDate);
                                periodJson.put(period);
                                startDate = forfait.getDateDebut();
                                endDate = forfait.getDateFin();
                            }
                        }
                    }
                    String period = formatPeriode(startDate, endDate);
                    periodJson.put(period);
                    file.put("packagesPeriod", periodJson);
                }

                // Véhicule secondaire
                Vehicule vehiculeSecondaire = dossier.getVehiculePrincipalTemporaire();
                if(Validator.isNotNull(vehiculeSecondaire)) {
                    JSONObject vehiculeSecondaireJson = JSONFactoryUtil.createJSONObject();
                    vehiculeSecondaireJson.put("registration", vehiculeSecondaire.getImmatriculation());
                    file.put("secondaryVehicule", vehiculeSecondaireJson);
                }

                files.put(file);
            }
            response.put("files", files);
        }

        return response;
    }

    /**
     * Appelle le WS Famille et renvoie les familles
     */
    public static JSONObject getFamily(String publicUserId) {

        JSONObject response = JSONFactoryUtil.createJSONObject();
        response.put("url", StrasbourgPropsUtil.getFamilySpaceURL());

        FamilySpaceResponse familySpaceResponse = FamilySpaceWebService.getResponse(publicUserId);

        // Pas de réponse
        if (Validator.isNull(familySpaceResponse)) {
            response.put("responseCode", 500);
            response.put("errorDescription", "Une erreur technique est survenue");
            response.put("errorTechnical", "Le service est momentan\u00E9ment indisponible, veuillez r\u00E9essayer ult\u00E9rieurement.");
            return response;
        }

        response.put("responseCode", 200);

        // Erreur renvoyé par le webservice
        if (familySpaceResponse.getCodeRetour() == 1) {
            response.put("message", familySpaceResponse.getErreurDescription());
            return response;
        }

        // Pas de comptes liés
        if (familySpaceResponse.getCount() == 0) {
            response.put("errorTechnical", "Pas de comptes liés");
            return response;
        }

        // tout va bien :D
        List<Family> families = familySpaceResponse.getFamilies();
        JSONArray familiesJson = JSONFactoryUtil.createJSONArray();
        for (Family family : families) {
            JSONObject familyJson = JSONFactoryUtil.createJSONObject();
            if(Validator.isNotNull(family.getIdFamily()))
                familyJson.put("familyId", family.getIdFamily());

            List<Person> persons = family.getPersons();
            if (persons.isEmpty()) {
                familyJson.put("messageNoKids", "Aucun enfant n'est actuellement inscrit \u00e0 la cantine scolaire");
            } else {
                JSONArray kidsJson = JSONFactoryUtil.createJSONArray();
                for (Person person : persons) {
                    JSONObject kidJson = JSONFactoryUtil.createJSONObject();
                    kidJson.put("firstname", person.getFirstName());
                    kidJson.put("lastname", person.getLastName());

                    if (person.getHasLunchBooked()) {
                        LocalDate firstBookingDate = person.getFirstBookingDate();
                        LocalDate lastBookingDate = person.getLastBookingDate();
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append("R\u00e9serv\u00e9(s) \u00e0 partir du ")
                                .append(firstBookingDate.format(formatter))
                                .append(" jusqu'au ")
                                .append(lastBookingDate.format(formatter));
                        kidJson.put("lunchBookedPeriod", stringBuilder.toString());

                        if (LocalDate.now().plusDays(13).isAfter(lastBookingDate)) {
                            stringBuilder = new StringBuilder();
                            stringBuilder.append("Le dernier jour r\u00e9serv\u00e9 est le ")
                                    .append(lastBookingDate.format(formatter))
                                    .append(", pensez \u00e0 r\u00e9server les repas de votre enfant le mercredi pr\u00e9c\u00e9dent le jour \u00e0 r\u00e9server.");
                            kidJson.put("messageWarningLunchBooked", stringBuilder.toString());
                        }
                    }
                    kidsJson.put(kidJson);
                }
                familyJson.put("kids", kidsJson);
            }
            familiesJson.put(familyJson);
        }
        response.put("familes", familiesJson);

        return response;
    }

    /**
     * Formatte les dates de début et fin pour en faire un string
     */
    public static String formatPeriode(LocalDate dateDebut, LocalDate dateFin) {
        StringBuilder stringBuilder = new StringBuilder();

        String dateDebutString = dateDebut.format(formatter);
        String dateFinString = dateFin.format(formatter);
        stringBuilder.append("Du ").append(dateDebutString).append(" au ").append(dateFinString);

        return stringBuilder.toString();
    }
}
