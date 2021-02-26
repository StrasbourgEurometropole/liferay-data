package eu.strasbourg.portlet.help.context;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import eu.strasbourg.service.help.model.HelpProposal;
import eu.strasbourg.service.help.model.HelpRequest;
import eu.strasbourg.service.help.service.HelpProposalLocalService;
import eu.strasbourg.service.help.service.HelpProposalLocalServiceUtil;
import eu.strasbourg.service.help.service.HelpRequestLocalServiceUtil;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import eu.strasbourg.utils.display.context.ViewListBaseDisplayContext;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import java.util.List;

public class ViewProposalHelpRequestsDisplayContext extends ViewListBaseDisplayContext<HelpRequest> {

    private final RenderRequest _request;
    private HelpProposal _helpProposal;
    private List <HelpRequest> _helpRequests;
    private final ThemeDisplay _themeDisplay;

    public ViewProposalHelpRequestsDisplayContext(RenderRequest request, RenderResponse response) {
        super(HelpRequest.class, request, response);
        this._request = request;
        this._themeDisplay = (ThemeDisplay) request
                .getAttribute(WebKeys.THEME_DISPLAY);
    }

    public HelpProposal getHelpProposal() throws PortalException {
        long helpProposalId = ParamUtil.getLong(_request, "helpProposalId");
        if (_helpProposal == null && helpProposalId > 0) {
            _helpProposal = HelpProposalLocalServiceUtil.getHelpProposal(helpProposalId);
        }
        return _helpProposal;
    }

    public List<HelpRequest> getHelpRequests() throws PortalException {
        if (this._helpRequests == null) {
            this._helpRequests = HelpRequestLocalServiceUtil.getByHelpProposalId(getHelpProposal().getHelpProposalId());
        }
        return this._helpRequests;
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

}