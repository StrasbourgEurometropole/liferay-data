package eu.strasbourg.portlet.help.action;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import eu.strasbourg.service.help.model.HelpProposal;
import eu.strasbourg.service.help.service.HelpProposalLocalService;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.*;


@Component(
        immediate = true,
        property = {
                "javax.portlet.name=" + StrasbourgPortletKeys.HELP_BO,
                "mvc.command.name=redirectToHelpProposal"
        },
        service = MVCActionCommand.class
)
public class RedirectToHelpProposalActionCommand implements MVCActionCommand {

    @Override
    public boolean processAction(ActionRequest actionRequest,
                                 ActionResponse actionResponse) {

        // Recuperation de l'id de la proposition d'aide a laquelle on veut acceder
        long helpProposalId = ParamUtil.getLong(actionRequest, "helpProposalId");
        HelpProposal helpProposal = _helpProposalLocalService.fetchHelpProposal(helpProposalId);

        if (helpProposal != null) {
            // On redirige l'utilisateur
            ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest
                    .getAttribute(WebKeys.THEME_DISPLAY);
            String portletName = (String) actionRequest
                    .getAttribute(WebKeys.PORTLET_ID);
            PortletURL returnURL = PortletURLFactoryUtil.create(actionRequest,
                    portletName, themeDisplay.getPlid(),
                    PortletRequest.RENDER_PHASE);
            returnURL.setParameter("tab", actionRequest.getParameter("tab"));

            actionResponse.setRenderParameter("returnURL", returnURL.toString());
            actionResponse.setRenderParameter("mvcPath",
                    "/help-bo-edit-help-proposal.jsp");
            actionResponse.setRenderParameter("helpProposalId", String.valueOf(helpProposalId));

            return true;
        }

        return false;
    }

    @Reference(unbind = "-")
    protected void setHelpProposalLocalService(HelpProposalLocalService helpProposalLocalService) {
        _helpProposalLocalService = helpProposalLocalService;
    }

    private HelpProposalLocalService _helpProposalLocalService;

    private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());

}