package eu.strasbourg.service.oidc.utils;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.Validator;
import eu.strasbourg.service.favorite.model.Favorite;
import eu.strasbourg.service.favorite.service.FavoriteLocalServiceUtil;
import eu.strasbourg.service.interest.model.UserInterest;
import eu.strasbourg.service.interest.service.UserInterestLocalServiceUtil;
import eu.strasbourg.service.notification.model.UserNotificationChannel;
import eu.strasbourg.service.notification.model.UserNotificationStatus;
import eu.strasbourg.service.notification.model.UserNotificationType;
import eu.strasbourg.service.notification.service.UserNotificationChannelLocalServiceUtil;
import eu.strasbourg.service.notification.service.UserNotificationStatusLocalServiceUtil;
import eu.strasbourg.service.notification.service.UserNotificationTypeLocalServiceUtil;
import eu.strasbourg.service.oidc.model.AnonymisationHistoric;
import eu.strasbourg.service.oidc.model.PublikUser;
import eu.strasbourg.service.oidc.service.PublikUserLocalServiceUtil;
import eu.strasbourg.service.project.model.BudgetParticipatif;
import eu.strasbourg.service.project.model.BudgetPhase;
import eu.strasbourg.service.project.model.BudgetSupport;
import eu.strasbourg.service.project.service.BudgetParticipatifLocalServiceUtil;
import eu.strasbourg.service.project.service.BudgetPhaseLocalServiceUtil;
import eu.strasbourg.service.project.service.BudgetSupportLocalServiceUtil;
import eu.strasbourg.service.project.service.ProjectLocalServiceUtil;
import eu.strasbourg.utils.PublikApiClient;
import eu.strasbourg.utils.StrasbourgPropsUtil;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Classe permettant d'effectuer l'anonymisation des utilisateurs
 *
 * @author angélique Zunino Champougny
 */
public class OIDCAnonymiser {

    public final static Log log = LogFactoryUtil.getLog(OIDCAnonymiser.class);

    private ServiceContext sc;
    private AnonymisationHistoric anonymisationHistoric;

    /**
     * Constructeur de base
     * @param sc Contexte de la requete
     * @param anonymisationHistoric Entite de suivi de l'anonymisation
     */
    public OIDCAnonymiser (ServiceContext sc, AnonymisationHistoric anonymisationHistoric) {
        this.sc = sc;
        this.anonymisationHistoric = anonymisationHistoric;
    }

    /**
     * Opere l'anonymisation
     */
    public void doAnonymisation() {
        this.anonymisationHistoric.setStartDate(new Date());

        // Import des donnees du flux
        anonymisationData();

        this.anonymisationHistoric.setFinishDate(new Date());
    }

    /**
     * Recupere l'OIDC et anonymise les donnees dans les tables tempons de ce service
     */
    private void anonymisationData() {
            Timestamp startTimestamp = new Timestamp(System.currentTimeMillis());
            this.anonymisationHistoric.addNewOperation("############### Anonymisation utilisateurs ###############");

            // Récupère tous les publikUser
            List<PublikUser> publikUsers = PublikUserLocalServiceUtil.getAllPublikUsers();

            JSONArray publikUserIds = JSONFactoryUtil.createJSONArray();
            for (PublikUser publikUser : publikUsers) {
                if(Validator.isNotNull(publikUser.getPublikId())){
                    publikUserIds.put(publikUser.getPublikId());
                }
            }

            JSONObject usersDeleted = PublikApiClient.getUsersDeleted(publikUserIds);

            String resultat = "";
            String message = "";
            int nbAnonymisation = 0;
            // vérifi si il y a eu une erreur à la récupération des utilisateurs supprimés
            if(Validator.isNotNull(usersDeleted.get("errors"))){
                resultat = "ERREUR";
                message = usersDeleted.getString("errors");
            }else{
                // on vérifi le nombre de résultats
                JSONArray publikUsersToAnonymized = usersDeleted.getJSONArray("unknown_uuids");
                if(publikUsersToAnonymized.length() > (publikUsers.size() * 0.5 / 100)){
                    resultat = "ERREUR";
                    message = "L'API retourne plus de 0.5% d'utilisateurs &agrave; supprimer";
                }else{
                    if(publikUsersToAnonymized.length() == 0){
                        this.anonymisationHistoric.addNewOperation("Aucun compte utilisateur supprim&eacute;");
                    }else{
                        // récupération de l'utilisateur anonyme
                        long anonymUserId = Long.parseLong(StrasbourgPropsUtil.getAnonymUserId());
                        if (Validator.isNull(anonymUserId)) {
                            resultat = "ERREUR";
                            message += "Id utilisateur anonyme non renseign&eacute;";
                        }else {
                            PublikUser anonymUser = PublikUserLocalServiceUtil.fetchPublikUser(anonymUserId);
                            if (Validator.isNull(anonymUser)) {
                                resultat = "ERREUR";
                                message += "Utilisateur anonyme introuvable";
                            } else {
                                // récupère la première phase active qui est en période de vote s'il y en a
                                Date today = new Date();
                                BudgetPhase phaseActive = BudgetPhaseLocalServiceUtil.getBudgetPhases(-1,-1).stream()
                                        .filter(p -> p.getIsActive())
                                        .filter(p -> !p.getBeginVoteDate().after(today))
                                        .filter(p -> !p.getEndVoteDate().before(today))
                                        .findFirst().get();
                                if(phaseActive != null)
                                    this.anonymisationHistoric.addNewOperation("Nous sommes en p&eacute;riode de vote pour la phase " +
                                            phaseActive.getTitle() +
                                            ". Les utilisateurs ayant vot&eeacutes lors de celle-ci ne seront pas anonymis&eacute;s.");
                                // on anonymise
                                this.anonymisationHistoric.addNewOperation("Nombre de compte utilisateur supprim&eacute;s : " + publikUsersToAnonymized.length());
                                for (int i = 0; i < publikUsersToAnonymized.length(); i++) {
                                    String publikIUserId = publikUsersToAnonymized.get(i).toString();
                                    PublikUser publikUser = PublikUserLocalServiceUtil.getByPublikUserId(publikIUserId);
                                    if (publikUser != null) {
                                        //anonymise les données placit
                                        Boolean anonymisedUser = true;
                                        if(phaseActive != null){
                                            // on vérifi si l'utilisateur a voté sur cette phase
                                            List<BudgetParticipatif> budgetsSupported = BudgetParticipatifLocalServiceUtil.getBudgetSupportedByPublikUserInPhase(publikIUserId, phaseActive.getBudgetPhaseId());
                                            if(budgetsSupported.size() > 0)
                                                anonymisedUser = false;
                                        }
                                        if(anonymisedUser) {
                                            PublikUserLocalServiceUtil.anonymisedUserPlacit(anonymUser, publikUser);

                                            // supprime les liens pour les tables favorite, userInterest et UserNotification...
                                            // Suppression des favoris de l'utilisateur
                                            List<Favorite> favorites = FavoriteLocalServiceUtil.
                                                    getByPublikUser(publikUser.getPublikId());
                                            if (!favorites.isEmpty()) {
                                                for (Favorite favorite : favorites) {
                                                    // Suppression
                                                    FavoriteLocalServiceUtil.deleteFavorite(favorite);
                                                }
                                            }

                                            // Suppression des centres d'intérêts de l'utilisateur
                                            List<UserInterest> userInterests = UserInterestLocalServiceUtil.
                                                    getByPublikUserId(publikUser.getPublikId());
                                            if (!userInterests.isEmpty()) {
                                                for (UserInterest userInterest : userInterests) {
                                                    // Suppression
                                                    UserInterestLocalServiceUtil.deleteUserInterest(userInterest);
                                                }
                                            }

                                            // Suppression des statuts de notifications de l'utilisateur
                                            List<UserNotificationStatus> userNotificationStatus = UserNotificationStatusLocalServiceUtil.
                                                    getByPublikUserId(publikUser.getPublikId());
                                            if (!userNotificationStatus.isEmpty()) {
                                                for (UserNotificationStatus userNotifStatus : userNotificationStatus) {
                                                    // Suppression
                                                    UserNotificationStatusLocalServiceUtil.deleteUserNotificationStatus(userNotifStatus);
                                                }
                                            }

                                            // Suppression des types de notifications de l'utilisateur
                                            List<UserNotificationType> userNotificationTypes = UserNotificationTypeLocalServiceUtil.
                                                    getByPublikUserId(publikUser.getPublikId());
                                            if (!userNotificationTypes.isEmpty()) {
                                                for (UserNotificationType userNotificationType : userNotificationTypes) {
                                                    // Suppression
                                                    UserNotificationTypeLocalServiceUtil.deleteUserNotificationType(userNotificationType);
                                                }
                                            }

                                            // Suppression des chaines de notifications de l'utilisateur
                                            List<UserNotificationChannel> userNotificationChannels = UserNotificationChannelLocalServiceUtil.
                                                    getByPublikUserId(publikUser.getPublikId());
                                            if (!userNotificationChannels.isEmpty()) {
                                                for (UserNotificationChannel userNotificationChannel : userNotificationChannels) {
                                                    // Suppression
                                                    UserNotificationChannelLocalServiceUtil.deleteUserNotificationChannel(userNotificationChannel);
                                                }
                                            }

                                            // suppression du publikUser
                                            PublikUserLocalServiceUtil.deletePublikUser(publikUser);

                                            nbAnonymisation++;
                                            this.anonymisationHistoric.addNewOperation("publikUser " + publikIUserId +
                                                    " (" + publikUser.getFirstName() + " " + publikUser.getLastName() + ") anonymis&eacute;");
                                        }else{
                                            this.anonymisationHistoric.addNewOperation("L'utilisateur "+ publikIUserId +
                                                    " (" + publikUser.getFirstName() + " " + publikUser.getLastName() +
                                                    ") ne peut pas &ecirc;tre anonymis&eacute; car il a vot&eacute; lors de cette phase.");
                                        }
                                    } else {
                                        this.anonymisationHistoric.addNewOperation("publikUser " + publikIUserId + " introuvable");
                                    }
                                }
                            }
                        }
                    }
                }
            }

            if(resultat.equals("ERREUR")) {
                this.anonymisationHistoric.addNewOperation("############### Anonymisation termin&eacute;e ###############");
                this.anonymisationHistoric.setErrorDescription("Probleme survenu lors de la r&eacute;cup&eacute;ration des donn&eacute;es");
                this.anonymisationHistoric.setErrorStackTrace(message);
                this.anonymisationHistoric.setResult(0);
                log.error(message);
            }else {
                this.anonymisationHistoric.addNewOperation("############### Anonymisation termin&eacute;e ###############");
                this.anonymisationHistoric.addNewOperation("Nombre d'utilisateur anonymis&eacute;s : " + nbAnonymisation);

                Timestamp endTimestamp = new Timestamp(System.currentTimeMillis());
                long processTime = (endTimestamp.getTime() - startTimestamp.getTime()) / 1000;
                this.anonymisationHistoric.addNewOperation("Anonymisation effectu&eacute;e en  " + processTime + " secondes.");

                // Succes de l'anonymisation
                this.anonymisationHistoric.setResult(1);
            }
    }

    public AnonymisationHistoric getAnonymisationHistoric() {
        return anonymisationHistoric;
    }

    public void setAnonymisationHistoric(AnonymisationHistoric anonymisationHistoric) {
        this.anonymisationHistoric = anonymisationHistoric;
    }

}