package eu.strasbourg.webservice.csmap.service;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.Validator;
import eu.strasbourg.portlet.mediatheque.borrower.BorrowerResponse;
import eu.strasbourg.portlet.mediatheque.borrower.BorrowerWebService;
import eu.strasbourg.portlet.mediatheque.borrower.Media;
import eu.strasbourg.portlet.mediatheque.constants.CodeErreurMediathequeConstants;
import eu.strasbourg.portlet.resid.dossier.Adresse;
import eu.strasbourg.portlet.resid.dossier.Contractant;
import eu.strasbourg.portlet.resid.dossier.Dossier;
import eu.strasbourg.portlet.resid.dossier.DossiersResponse;
import eu.strasbourg.portlet.resid.dossier.DossiersWebService;
import eu.strasbourg.portlet.resid.dossier.Forfait;
import eu.strasbourg.portlet.resid.dossier.Vehicule;
import eu.strasbourg.portlet.resid.dossier.Zone;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class WSAccountData {

    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    // TODO Différence entre errorTechnical et errorDescription ? A revoir là ou c'est utilisé

    /**
     * Appelle le WS Mediatheque et traite le retour
     */
    public static JSONObject getMediatheque(String publikUserId) {

        JSONObject response = JSONFactoryUtil.createJSONObject();

        BorrowerResponse borrower = BorrowerWebService.getResponse(publikUserId);

        String codeErreur = borrower.getCode_erreur();
        String erreurMessage = borrower.getErr();
        if (Validator.isNull(codeErreur) && !Validator.isNull(erreurMessage.isEmpty())) {
            response.put("responseCode", 500);
            response.put("errorDescription", "Une erreur technique est survenue");
            // TODO get l'url, la mettre dans le json et return le json
            return response;
        }

        // Cas ou le code erreur est inconnu
        List<String> allCodeErreur = CodeErreurMediathequeConstants.allCodesErreurList;
        if (!allCodeErreur.contains(codeErreur)) {
            response.put("responseCode", 500);
            response.put("errorDescription", "Une erreur technique est survenue");
            // TODO get l'url, la mettre dans le json et return le json
            return response;
        }

        // Cas du borrower inconnu
        if (Validator.isNull(borrower)) {
            response.put("responseCode", 500);
            response.put("errorDescription", "Une erreur technique est survenue");
            // TODO get l'url, la mettre dans le json et return le json
            return response;
        } else {

            // Gestion des message selon les codeErreur reçus
            if (!Validator.isNull(borrower.getCode_erreur()) || (!Validator.isNull(borrower.getErr()) && !borrower.getErr().equals("0"))) {
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
                        response.put("message", "Le num\u00e9ro de carte n'existe pas");
                        break;
                    case CodeErreurMediathequeConstants.ASSOCIATION_SUPPRIMEE:
                        response.put("message", "Une association a \u00e9t\u00e9 supprim\u00e9e");
                        break;
                    default:
                        response.put("errorTechnical", erreurMessage);
                        break;
                }
            }

            // Gestion numéro de carte
            String cardNumber = borrower.getCardNumber();
            response.put("cardNumber", cardNumber);

            // Gestion date d'expiration
            LocalDate expirationDate = borrower.getExpireDate();
            if (expirationDate.isAfter(LocalDate.now())) {
                response.put("messageExpirationDate", "Votre abonnement Pass'relle est arriv\u00e9 \u00e0 \u00e9ch\u00e9ance. Pensez \u00e0 vous r\u00e9abonner !");
            }
            if (expirationDate.isBefore(LocalDate.now().plusMonths(1))) {
                response.put("messageExpirationDate", "Votre abonnement Pass'relle arrive \u00e0 \u00e9ch\u00e9ance dans moins d'un mois, pensez \u00e0 vous r\u00e9abonner !");
            }
            response.put("expirationDate", expirationDate);

            // Gestion des emprunts
            List<Media> borrowings = borrower.getBorrowings();
            JSONArray borrowingsJsonArray = JSONFactoryUtil.createJSONArray();
            if (!borrowings.isEmpty()) {
                for (Media media : borrowings) {
                    JSONObject borrowedMedia = JSONFactoryUtil.createJSONObject();
                    borrowedMedia.put("title", media.getName());
                    borrowedMedia.put("type", media.getType());
                    borrowedMedia.put("returnDate", media.getReturnDate());

                    borrowingsJsonArray.put(borrowedMedia);
                }
                response.put("borrowings", borrowingsJsonArray);
            }

            // Gestion des réservations
            List<Media> reservations = borrower.getReservations();
            JSONArray reservationsJsonArray = JSONFactoryUtil.createJSONArray();
            if (!reservations.isEmpty()) {
                for (Media media : reservations) {
                    JSONObject reservedMedia = JSONFactoryUtil.createJSONObject();
                    reservedMedia.put("title", media.getName());
                    reservedMedia.put("type", media.getType());

                    reservationsJsonArray.put(reservedMedia);
                }
                response.put("reservations", reservationsJsonArray);
            }

            // TODO url
        }
        response.put("responseCode", 200);
        return response;
    }

    /**
     * Appelle le WS Resid et traite le retour
     */
    public static JSONObject getResid(String publicUserId) {

        JSONObject response = JSONFactoryUtil.createJSONObject();
        DossiersResponse dossiersResponse = DossiersWebService.getResponse(publicUserId);

        if (Validator.isNull(dossiersResponse)) {
            response.put("responseCode", 500);
            response.put("errorDescription", "Une erreur technique est survenue");
        }

        List<Dossier> dossiers = dossiersResponse.getDossiers();
        if (!dossiers.isEmpty()) {
            if (dossiersResponse.getCodeRetour() != 0) {
                response.put("errorDescription", "Une erreur technique est survenue");
            }
        }

        JSONArray files = JSONFactoryUtil.createJSONArray();
        for (Dossier dossier : dossiers) {

            // Gestion du dossier
            JSONObject file = JSONFactoryUtil.createJSONObject();
            file.put("number", dossier.getNumeroDossier());
            LocalDate dateFinValiditeDossier = dossier.getDateFinValiditeDossier();
            file.put("endValidityDate", dateFinValiditeDossier);
            if (LocalDate.now().isAfter(dateFinValiditeDossier)) {
                file.put("messageEndValidityDate", "Votre titre n'est plus valide");
            }
            List<Forfait> forfaits = dossier.getForfaits();
            if (forfaits.isEmpty()) {
                file.put("messageNoForfait", "Vous n'avez aucun forfait en cours ou à venir");
            }

            // Gestion du contractant principal du dossier
            JSONObject principalUser = JSONFactoryUtil.createJSONObject();
            Contractant contractantPrincipal = dossier.getContractantPrincipal();
            principalUser.put("name", contractantPrincipal.getNom());
            JSONObject address = JSONFactoryUtil.createJSONObject();
            Adresse contractantPrincipalAdresse = contractantPrincipal.getAdresse();
            address.put("firstLine", contractantPrincipalAdresse.getNumero() + contractantPrincipalAdresse.getExtension() + contractantPrincipalAdresse.getVoie());
            address.put("secondLine", contractantPrincipalAdresse.getComplementVoie());
            address.put("thirdLine", contractantPrincipalAdresse.getComplementVoie2());
            address.put("fourthLine", contractantPrincipalAdresse.getCodePostal() + contractantPrincipalAdresse.getVille());
            principalUser.put("address", address);
            file.put("principalUser", principalUser);

            // Gestion de la zone
            Zone zone = dossier.getZone();
            file.put("zoneName", zone.getIntitule());
            file.put("userType", zone.getTypeUsager());

            // Gestion du véhicule principal
            Vehicule vehiculePrincipal = dossier.getVehiculePrincipal();
            JSONObject vehiculPrincipalJson = JSONFactoryUtil.createJSONObject();
            vehiculPrincipalJson.put("registration", vehiculePrincipal.getImmatriculation());
            if (vehiculePrincipal.hasCarteGriseProvisoire()) {
                vehiculPrincipalJson.put("expireDate", vehiculePrincipal.getDateFinValidite());
            }
            file.put("mainVehicule", vehiculPrincipalJson);

            // Gestion du véhicule principal temporaire
            Vehicule vehiculePrincipalTemporaire = dossier.getVehiculePrincipalTemporaire();
            JSONObject vehiculPrincipalJsonTemporaire = JSONFactoryUtil.createJSONObject();
            vehiculPrincipalJsonTemporaire.put("registration", vehiculePrincipalTemporaire.getImmatriculation());
            if (vehiculePrincipalTemporaire.hasCarteGriseProvisoire()) {
                vehiculPrincipalJsonTemporaire.put("expireDate", vehiculePrincipalTemporaire.getDateFinValidite());
            }
            file.put("mainTemporaryVehicule", vehiculPrincipalJsonTemporaire);

            // Gestion des périodes des forfaits
            if (!forfaits.isEmpty()) {
                List<String> periodesForfaits = getListPeriodesForfaits(forfaits);
                String perdiodes = periodesForfaits.stream().collect(Collectors.joining(","));
                file.put("packagesPeriod", perdiodes);
            }

            // Gestion du véhicule secondaire
            Vehicule vehiculeSecondaire = dossier.getVehiculePrincipalTemporaire();
            JSONObject vehiculeSecondaireJson = JSONFactoryUtil.createJSONObject();
            vehiculPrincipalJsonTemporaire.put("registration", vehiculeSecondaire.getImmatriculation());
            file.put("secondaryVehicule", vehiculeSecondaireJson);

            files.put(file);
        }
        response.put("files", files);
        // TODO url

        if (response.get("errorTechnical") != null || response.get("message") != null) {
            // TODO juste l'url dans response
        }

        response.put("responseCode", 200);
        return response;
    }

    /**
     * Renvoie la liste des périodes des forfait pour un dossier
     */
    private static List<String> getListPeriodesForfaits(List<Forfait> forfaits) {

        List<String> periodesForfaits = new ArrayList<>();
        for (int i = 0; i < forfaits.size(); i++) {

            LocalDate dateDebut;
            LocalDate dateFin;
            Forfait forfait = forfaits.get(i);
            if (i == 0) {
                dateDebut = forfait.getDateDebut();
                dateFin = forfait.getDateFin();
                String perdiode = formatPeriode(dateDebut, dateFin);
                periodesForfaits.add(perdiode);
            } else {
                Forfait pastForfait = forfaits.get(i - 1);
                if (pastForfait.getDateFin().plusDays(1) == forfait.getDateDebut()) {
                    periodesForfaits.remove(periodesForfaits.size() -1);
                    dateFin = forfait.getDateDebut();
                    String perdiode = formatPeriode(pastForfait.getDateDebut(), dateFin);
                    periodesForfaits.add(perdiode);
                } else {
                    dateDebut = forfait.getDateDebut();
                    dateFin = forfait.getDateFin();
                    String perdiode = formatPeriode(dateDebut, dateFin);
                    periodesForfaits.add(perdiode);
                }
            }
            i++;
        }
        return periodesForfaits;
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
