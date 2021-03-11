package eu.strasbourg.portlet.help.context;

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
import java.util.ArrayList;
import java.util.List;


public class ViewHelpRequestsDisplayContext extends ViewListBaseDisplayContext<HelpRequest> {

    private List<HelpRequest> _helpRequests;

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