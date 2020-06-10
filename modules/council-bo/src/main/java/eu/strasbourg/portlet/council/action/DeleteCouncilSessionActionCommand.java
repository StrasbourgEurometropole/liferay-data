package eu.strasbourg.portlet.council.action;

import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import eu.strasbourg.service.council.service.CouncilSessionLocalService;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;

@Component(
        immediate = true,
        property = {
                "javax.portlet.name=" + StrasbourgPortletKeys.COUNCIL_BO,
                "mvc.command.name=deleteCouncilSession"
        },
        service = MVCActionCommand.class
)
public class DeleteCouncilSessionActionCommand extends BaseMVCActionCommand {

    @Override
    protected void doProcessAction(ActionRequest request, ActionResponse response) throws Exception {
        // Récupération du contexte de la requête
        ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        String portletName = (String) request.getAttribute(WebKeys.PORTLET_ID);

        long councilSessionId = ParamUtil.getLong(request, "councilSessionId");

        // On vérifi qu'il n'y a pas de délib lié à ce conseil
        if(!this.councilSessionLocalService.hasDelib(councilSessionId)){
            // Suppression de l'entité
            this.councilSessionLocalService.removeCouncilSession(councilSessionId);
        }else{
            SessionErrors.add(request, "council-has-delib-error");
        }

        // Post / Redirect / Get si tout est bon
        PortletURL renderURL = PortletURLFactoryUtil.create(request,
                portletName, themeDisplay.getPlid(), PortletRequest.RENDER_PHASE);
        renderURL.setParameter("tab", request.getParameter("tab"));
        response.sendRedirect(renderURL.toString());
    }

    @Reference(unbind = "-")
    protected void setCouncilSessionLocalService(CouncilSessionLocalService councilSessionLocalService) {
        this.councilSessionLocalService = councilSessionLocalService;
    }

    private CouncilSessionLocalService councilSessionLocalService;

}
