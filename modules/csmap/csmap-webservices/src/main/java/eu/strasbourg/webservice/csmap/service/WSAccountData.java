package eu.strasbourg.webservice.csmap.service;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.Validator;
import eu.strasbourg.portlet.mediatheque.borrower.BorrowerResponse;
import eu.strasbourg.portlet.mediatheque.borrower.BorrowerWebService;
import eu.strasbourg.portlet.mediatheque.borrower.Media;
import eu.strasbourg.portlet.mediatheque.constants.CodeErreurMediathequeConstants;

import java.time.LocalDate;
import java.util.List;

public class WSAccountData {

    public static JSONObject sendRequest(String publikUserId) {

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
        return response;
    }
}
