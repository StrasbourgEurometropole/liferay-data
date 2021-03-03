package eu.strasbourg.portlet.help.context;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import eu.strasbourg.service.help.model.HelpRequest;
import eu.strasbourg.service.help.service.HelpRequestLocalService;
import eu.strasbourg.service.help.service.HelpRequestLocalServiceUtil;
import eu.strasbourg.service.oidc.model.PublikUser;
import eu.strasbourg.service.oidc.service.PublikUserLocalServiceUtil;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import eu.strasbourg.utils.display.context.ViewListBaseDisplayContext;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.sound.sampled.Port;
import java.util.*;

import com.liferay.portal.kernel.dao.orm.QueryUtil;



public class ViewHelpSeekersDisplayContext extends ViewListBaseDisplayContext<PublikUser> {

    private List<PublikUser> helpSeekers;
    private HashMap<String, Integer> helpRequestNumbers;
    private HashMap<String, HelpRequest> lastRequestbySeekers;

    public ViewHelpSeekersDisplayContext(RenderRequest request, RenderResponse response) {
        super(PublikUser.class, request, response);
    }

    public List<PublikUser> getHelpSeekers() throws PortalException {
        int countResults = 0;

        if (helpSeekers == null) {
            /*
            helpSeekers = new ArrayList<>();
            helpRequestNumbers = new HashMap<>();
            List<PublikUser> userHits = PublikUserLocalServiceUtil.getPublikUsers(
                    this.getSearchContainer().getStart(),
                    this.getSearchContainer().getEnd(),
                    this.getKeywords(),
                    this.getOrderByColSearchField(),
                    "desc".equals(this.getOrderByType()));
            // On trie pour garder uniquement les utilisateurs ayant au moins une aide
            for (PublikUser user : userHits) {
                List<HelpRequest> requestsByUser = HelpRequestLocalServiceUtil.getByPublikId(user.getPublikId());
                if ( requestsByUser.size() > 0) {
                    helpSeekers.add(user);
                    long publikId = user.getPublikUserLiferayId();
                    helpRequestNumbers.put(publikId, requestsByUser.size());
                }
            }

            countResults = getAllHelpSeekers().size();
            this.getSearchContainer().setTotal(countResults);
             */

            // Recuperation de toutes les requetes d'aide
            List<HelpRequest> helpRequests = HelpRequestLocalServiceUtil.getHelpRequests(QueryUtil.ALL_POS, QueryUtil.ALL_POS);
            List<PublikUser> helpSeekersTemp = new ArrayList<>();
            helpRequestNumbers = new HashMap<>();
            lastRequestbySeekers = new HashMap<>();

            for (HelpRequest request : helpRequests) {
                // Ajout du Publik User (si non deja present) dans la liste
                String helpSeekerId = request.getPublikId();

                // On a deja des requetes pour cet utilisateur
                if (helpRequestNumbers.containsKey(helpSeekerId)) {
                    // Mise a jour nbre requetes
                    helpRequestNumbers.put(helpSeekerId, helpRequestNumbers.get(helpSeekerId) + 1);
                    // Mise a jour derniere requete en date si necessaire
                    if (request.getCreateDate().getTime() >
                            lastRequestbySeekers.get(helpSeekerId).getCreateDate().getTime() ) {
                        lastRequestbySeekers.put(helpSeekerId, request);
                    }
                }
                // Premiere requete trouvee de cet utilisateur
                else {
                    // Ajout dans la liste
                    helpSeekersTemp.add(PublikUserLocalServiceUtil.getByPublikUserId(helpSeekerId));
                    // Mise a jour nbre requetes
                    helpRequestNumbers.put(helpSeekerId, 1);
                    // Derniere requete en date de cet user
                    lastRequestbySeekers.put(helpSeekerId, request);
                }
            }
            // TODO Tri par date de derniere demande


            helpSeekers = helpSeekersTemp;
        }
        return helpSeekers;
    }


    /**
     * Retourne la liste des utilisateurs correspondant à la recherche lancée en ignorant la pagination
     */
    public List<PublikUser> getAllHelpSeekers() throws PortalException {
        if (helpSeekers == null) {
            helpSeekers = new ArrayList<>();
            helpRequestNumbers = new HashMap<>();
            /*
            List<PublikUser> userHits = PublikUserLocalServiceUtil.getPublikUsers(
                    -1,
                    -1,
                    this.getKeywords(),
                    this.getOrderByColSearchField(),
                    "desc".equals(this.getOrderByType()));
            // On trie pour garder uniquement les utilisateurs ayant au moins une aide

            for (PublikUser user : userHits) {
                List<HelpRequest> requestsByUser = HelpRequestLocalServiceUtil.getByPublikId(user.getPublikId());
                if ( requestsByUser != null) {
                    helpSeekers.add(user);
                    long publikId = user.getPublikUserLiferayId();
                    helpRequestNumbers.put(publikId, requestsByUser.size());
                }
            }

             */
        }
        return helpSeekers;
    }


    public HashMap<String, Integer> getHelpRequestsNumbers() throws PortalException {
        if (helpRequestNumbers == null) {
            this.getHelpSeekers();
        }
        return helpRequestNumbers;
    }

    public HashMap<String, HelpRequest> getLastRequestbySeekers() throws PortalException {
        if (lastRequestbySeekers == null) {
            this.getHelpSeekers();
        }
        return lastRequestbySeekers;
    }

    /**
     * Retourne la liste des PK de tous les demandeurs
     * @return liste de PK (ex: "1,5,7,8")
     */
    public String getAllHelpSeekerIds() throws PortalException {
        StringBuilder helpSeekerIds = new StringBuilder();
        for (PublikUser helpSeeker : this.getHelpSeekers()) {
            if (helpSeekerIds.length() > 0) {
                helpSeekerIds.append(",");
            }
            helpSeekerIds.append(helpSeeker.getPublikId());
        }
        return helpSeekerIds.toString();
    }

    /**
     * Renvoie le nom de la colonne sur laquelle on fait le tri pour demandeurs (PublikUser)
     */
    public String getOrderByColSearchField() {
        switch (this.getOrderByCol()) {
            case "first-name":
                return "firstName";
            case "email":
                return "email";
            case "banish-date":
                return "banishDate";
            default:
                return "lastName";
        }
    }

    /**
     * Wrapper autour du permission checker pour les permissions de module
     */
    public boolean hasPermission(String actionId) throws PortalException {
        return _themeDisplay.getPermissionChecker().hasPermission(
                this._themeDisplay.getScopeGroupId(),
                StrasbourgPortletKeys.HELP_BO, StrasbourgPortletKeys.HELP_BO,
                actionId);
    }

    private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());

}