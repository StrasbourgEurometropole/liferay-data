package eu.strasbourg.portlet.help.context;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;
import eu.strasbourg.service.help.model.HelpRequest;
import eu.strasbourg.service.help.service.HelpRequestLocalServiceUtil;
import eu.strasbourg.service.oidc.model.PublikUser;
import eu.strasbourg.service.oidc.service.PublikUserLocalServiceUtil;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import eu.strasbourg.utils.display.context.ViewListBaseDisplayContext;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ViewSeekerHelpRequestsDisplayContext extends ViewListBaseDisplayContext<HelpRequest> {

    private final RenderRequest _request;
    private final ThemeDisplay _themeDisplay;
    private List<HelpRequest> _helpRequests;
    private PublikUser _helpSeeker;

    public ViewSeekerHelpRequestsDisplayContext(RenderRequest request, RenderResponse response) {
        super(HelpRequest.class, request, response);
        this._request = request;
        this._themeDisplay = (ThemeDisplay) request
                .getAttribute(WebKeys.THEME_DISPLAY);
    }

    public PublikUser getHelpSeeker() {
        if (_helpSeeker == null) {
            this.getHelpRequests();
        }
        return _helpSeeker;
    }

    public List<HelpRequest> getHelpRequests() {
        if (_helpRequests == null) {
            String helpSeekerId = ParamUtil.getString(_request, "helpSeekerId");
            _helpSeeker = PublikUserLocalServiceUtil.getByPublikUserId(helpSeekerId);
            if (helpSeekerId.equals("")) {
                _helpRequests = new ArrayList<>();
            }
            else {
                List<HelpRequest> unsortedHelpRequests = HelpRequestLocalServiceUtil.getByPublikId(helpSeekerId);
                // Tri par requete la plus recente
                Comparator<HelpRequest> byDate = new Comparator<HelpRequest>() {
                    @Override
                    public int compare(HelpRequest c1, HelpRequest c2) {
                        return Long.valueOf(c1.getCreateDate().getTime()).compareTo(c2.getCreateDate().getTime());
                    }
                };
                this._helpRequests = ListUtil.sort(unsortedHelpRequests, byDate.reversed());
            }
        }
        return _helpRequests;
    }

    public String getCurrentUrl() {
        return PortalUtil.getCurrentURL(this._request);
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
