package eu.strasbourg.portlet.help.context;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import eu.strasbourg.service.help.model.HelpRequest;
import eu.strasbourg.service.help.service.HelpRequestLocalServiceUtil;
import eu.strasbourg.service.oidc.model.PublikUser;
import eu.strasbourg.service.oidc.service.PublikUserLocalServiceUtil;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import eu.strasbourg.utils.display.context.ViewListBaseDisplayContext;

import javax.portlet.*;
import java.util.*;

import com.liferay.portal.kernel.dao.orm.QueryUtil;



public class ViewHelpSeekersDisplayContext extends ViewListBaseDisplayContext<ViewHelpSeekersDisplayContext.HelpSeeker> {

    private List<HelpSeeker> _helpSeekers;

    public ViewHelpSeekersDisplayContext(RenderRequest request, RenderResponse response) {
        super(HelpSeeker.class, request, response);
    }

    public List<HelpSeeker> getHelpSeekers() throws PortalException {
        int countResults = 0;

        if (_helpSeekers == null) {

            // Recuperation de toutes les requetes d'aide
            List<HelpRequest> helpRequests = HelpRequestLocalServiceUtil.getHelpRequests(QueryUtil.ALL_POS, QueryUtil.ALL_POS);
            HashMap<String, HelpSeeker> helpSeekersMap =  new HashMap<>();

            for (HelpRequest request : helpRequests) {
                String helpSeekerId = request.getPublikId();
                // On a deja des requetes pour cet utilisateur
                if (helpSeekersMap.containsKey(helpSeekerId)) {
                    // Mise a jour nbre requetes
                    helpSeekersMap.get(helpSeekerId).incrementRequestsNumber();
                    // Mise a jour derniere requete en date si necessaire
                    helpSeekersMap.get(helpSeekerId).updateRequest(request);
                }
                // Premiere requete trouvee de cet utilisateur
                else {
                    // Ajout dans la liste
                    HelpSeeker helpSeeker = new HelpSeeker(PublikUserLocalServiceUtil.getByPublikUserId(helpSeekerId),
                                                            request);
                    helpSeekersMap.put( helpSeekerId, helpSeeker);
                }

            }
            // Tri par Keywords
            List<HelpSeeker> unfilteredHelpSeekers = new ArrayList<>(helpSeekersMap.values());

            // Ordonnancement (Date de derniere demande par defaut)
            List<HelpSeeker> unsortedHelpSeekers = getFilteredHelpSeekers(unfilteredHelpSeekers);
            Comparator<HelpSeeker> comp = this.getComparator();
            if (this.getOrderByType().equals("desc")) {
                _helpSeekers = ListUtil.sort(unsortedHelpSeekers, comp.reversed());
            }
            else {
                _helpSeekers = ListUtil.sort(unsortedHelpSeekers, comp);
            }
        }
        return _helpSeekers;
    }

    private List<HelpSeeker> getFilteredHelpSeekers(List<HelpSeeker> unfilteredSeekers) {
        List<HelpSeeker> filteredResults;
        if (!this.getKeywords().equals("")) {
            filteredResults = new ArrayList<>();
            List<PublikUser> users = PublikUserLocalServiceUtil.getPublikUsers(
                    -1,
                    -1,
                    this.getKeywords(),
                    this.getOrderByColSearchField(),
                    "desc".equals(this.getOrderByType()));
            for (HelpSeeker seeker : unfilteredSeekers) {
                for (PublikUser user : users) {
                    if (seeker.getPublikUser().equals(user)) {
                        filteredResults.add(seeker);
                        break;
                    }
                }
            }
        }
        else {
            filteredResults = unfilteredSeekers;
        }
        return filteredResults;
    }

    private Comparator<HelpSeeker> getComparator() {
        Comparator<HelpSeeker> comparator;
        switch (this.getOrderByCol()) {
            case "last-name":
                comparator = (c1, c2) -> String.valueOf(c1.getPublikUser().getLastName())
                        .compareTo(c2.getPublikUser().getLastName());
                break;
            case "first-name":
                comparator = (c1, c2) -> String.valueOf(c1.getPublikUser().getFirstName())
                        .compareTo(c2.getPublikUser().getFirstName());
                break;
            case "email":
                comparator = (c1, c2) -> String.valueOf(c1.getPublikUser().getEmail())
                        .compareTo(c2.getPublikUser().getEmail());
                break;
            case "nb-requests":
                comparator = (c1, c2) -> Integer.valueOf(c1.getRequestsNumber())
                        .compareTo(c2.getRequestsNumber());
                break;
            default:
                comparator = (c1, c2) -> Long.valueOf(c1.getLastRequest().getCreateDate().getTime())
                        .compareTo(c2.getLastRequest().getCreateDate().getTime());
        }
        return comparator;
    }

    /**
     * Retourne la liste des utilisateurs correspondant à la recherche lancée en ignorant la pagination
     */
    /*
    public List<PublikUser> getAllHelpSeekers() throws PortalException {
        if (helpSeekers == null) {
            helpSeekers = new ArrayList<>();
            helpRequestNumbers = new HashMap<>();

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


        }
        return helpSeekers;
    }
    */



    /**
     * Retourne la liste des PK de tous les demandeurs
     * @return liste de PK (ex: "1,5,7,8")
     */

    public String getAllHelpSeekerIds() throws PortalException {
        StringBuilder helpSeekerIds = new StringBuilder();
        for (HelpSeeker helpSeeker : this.getHelpSeekers()) {
            if (helpSeekerIds.length() > 0) {
                helpSeekerIds.append(",");
            }
            helpSeekerIds.append(helpSeeker.getPublikUser().getPublikId());
        }
        return helpSeekerIds.toString();
    }


    public String getCurrentURL() {
        return PortalUtil.getCurrentURL(this._request);
    }
    public String getPublikUserEditURL(String publikUserId) throws WindowStateException, PortletModeException {
        PortletURL myPortletURL = PortletURLFactoryUtil.create(this._request, StrasbourgPortletKeys.OIDC_BO,
                this._themeDisplay.getLayout().getPlid(), PortletRequest.RENDER_PHASE);
        myPortletURL.setWindowState(WindowState.MAXIMIZED);
        myPortletURL.setPortletMode(PortletMode.VIEW);

        myPortletURL.setParameter("cmd", "editHelpSeeker");
        myPortletURL.setParameter("publikUserLiferayId", publikUserId);
        myPortletURL.setParameter("returnURL", PortalUtil.getCurrentURL(this._request));
        myPortletURL.setParameter("mvcPath", "/oidc-bo-edit-publikuser.jsp");

        return myPortletURL.toString();
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

    public boolean hasPermissionOIDC(String actionId) throws PortalException {
        return _themeDisplay.getPermissionChecker().hasPermission(
                this._themeDisplay.getScopeGroupId(),
                StrasbourgPortletKeys.OIDC_BO, StrasbourgPortletKeys.OIDC_BO,
                actionId);
    }

    private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());

    // Classe nested pour aggreger les donnees d'un user
    public class HelpSeeker {
        private PublikUser _user = null;
        private HelpRequest _lastRequest = null;
        private int _requestsNumber = 1;

        public HelpSeeker(PublikUser user, HelpRequest request) {
            _user = user;
            _lastRequest = request;
        }

        public void updateRequest(HelpRequest request) {
            if (request.getCreateDate().getTime() <
                    _lastRequest.getCreateDate().getTime()) {
                _lastRequest = request;
            }
        }

        public void incrementRequestsNumber() {
            _requestsNumber++;
        }

        public PublikUser getPublikUser() {
            return _user;
        }

        public HelpRequest getLastRequest() {
            return _lastRequest;
        }

        public int getRequestsNumber() {
            return _requestsNumber;
        }
    }

    public Class<HelpSeeker> getHelpSeekerClass() {
        return HelpSeeker.class;
    }
}