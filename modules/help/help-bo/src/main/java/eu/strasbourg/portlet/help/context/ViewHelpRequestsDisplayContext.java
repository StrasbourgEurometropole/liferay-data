package eu.strasbourg.portlet.help.context;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.language.LanguageUtil;
import eu.strasbourg.portlet.help.constants.HelpBOConstants;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import eu.strasbourg.service.help.model.HelpRequest;
import eu.strasbourg.service.help.service.HelpRequestLocalServiceUtil;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import eu.strasbourg.utils.display.context.ViewListBaseDisplayContext;

import javax.portlet.*;
import java.util.*;


public class ViewHelpRequestsDisplayContext extends ViewListBaseDisplayContext<HelpRequest> {

    private List<HelpRequest> _helpRequests;
    private HashMap<String, Integer> _studentImagesCount;

    public ViewHelpRequestsDisplayContext(RenderRequest request, RenderResponse response) {
        super(HelpRequest.class, request, response);
    }

    public List<HelpRequest> getHelpRequests() throws PortalException {
        if (this._helpRequests == null) {
            Hits hits = getHits(this._themeDisplay.getScopeGroupId());
            this._helpRequests = createObjectList(hits);
        }
        return this._helpRequests;
    }

    private List<HelpRequest> createObjectList(Hits hits) {
        // Création de la liste d'objet
        List<HelpRequest> results = new ArrayList<>();
        if (hits != null) {
            for (Document document : hits.getDocs()) {
                HelpRequest helpRequest = HelpRequestLocalServiceUtil.fetchHelpRequest(
                        GetterUtil.getLong(document.get(Field.ENTRY_CLASS_PK)));
                if (helpRequest != null) {
                    results.add(helpRequest);
                }
            }
        }
        return results;
    }

    public String getCurrentURL() {
        return PortalUtil.getCurrentURL(this._request);
    }

    public String getPublikUserEditURL(String publikUserId) throws WindowStateException, PortletModeException {
        PortletURL myPortletURL = PortletURLFactoryUtil.create(this._request, StrasbourgPortletKeys.OIDC_BO,
                this._themeDisplay.getLayout().getPlid(), PortletRequest.RENDER_PHASE);
        myPortletURL.setWindowState(WindowState.MAXIMIZED);
        myPortletURL.setPortletMode(PortletMode.VIEW);

        myPortletURL.setParameter(HelpBOConstants.PARAM_CMD, HelpBOConstants.PARAM_EDIT_PUBLIK_USER);
        myPortletURL.setParameter(HelpBOConstants.PARAM_PUBLIK_ID, publikUserId);
        myPortletURL.setParameter(HelpBOConstants.PARAM_RETURN_URL, PortalUtil.getCurrentURL(this._request));
        myPortletURL.setParameter(HelpBOConstants.PARAM_MVC_PATH, HelpBOConstants.URL_OIDC_EDIT_USER);

        return myPortletURL.toString();
    }

    @Override
    public String getOrderByColSearchField() {
        switch (this.getOrderByCol()) {
            case "title":
            case "alias":
                return "localized_title_fr_FR_sortable";
            case "modified-date":
                return "modified_sortable";
            case "publication-date":
                return "publishDate_sortable";
            case "status":
                return "status_sortable";
            case "create-date":
                return "createDate_sortable";
            default:
                return "createDate_sortable";
        }
    }

    public String getImagesCount(String publikId) {
        if (_studentImagesCount == null) {
            _studentImagesCount = new HashMap<>();
            // Recuperation de toutes les requetes d'aide
            List<HelpRequest> helpRequests = HelpRequestLocalServiceUtil.getHelpRequests(QueryUtil.ALL_POS, QueryUtil.ALL_POS);
            HashSet<Long> studentImagesIds = new HashSet<>();

            for (HelpRequest helpRequest : helpRequests) {
                String helpSeekerId = helpRequest.getPublikId();
                // On a deja des requetes pour cet utilisateur
                if (_studentImagesCount.containsKey(helpSeekerId)) {
                    int seeker_count = this._studentImagesCount.get(helpSeekerId);
                    // Mise a jour nombre de justificatifs si necessaire
                    long studentCardImageId = helpRequest.getStudentCardImageId();
                    if (studentCardImageId > 0 && !studentImagesIds.contains(studentCardImageId)) {
                        this._studentImagesCount.put(helpSeekerId, seeker_count + 1);
                        studentImagesIds.add(studentCardImageId);
                    }
                }
                // Premiere requete trouvee de cet utilisateur
                else {
                    // Ajout dans la liste
                    _studentImagesCount.put(helpSeekerId, 0);
                    long studentCardImageId = helpRequest.getStudentCardImageId();
                    if (studentCardImageId > 0) {
                        this._studentImagesCount.put(helpSeekerId, 1);
                        studentImagesIds.add(studentCardImageId);
                    }
                }

            }
        }
        return LanguageUtil.get(Locale.FRANCE,
            "eu.help.delete.student.card.images") +" ("+_studentImagesCount.get(publikId)+")"; }

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
}