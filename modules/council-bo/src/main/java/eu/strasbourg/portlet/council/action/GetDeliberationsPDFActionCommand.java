package eu.strasbourg.portlet.council.action;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.search.*;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.*;
import eu.strasbourg.service.council.model.CouncilSession;
import eu.strasbourg.service.council.model.Deliberation;
import eu.strasbourg.service.council.model.Vote;
import eu.strasbourg.service.council.service.CouncilSessionLocalService;
import eu.strasbourg.service.council.service.CouncilSessionLocalServiceUtil;
import eu.strasbourg.service.council.service.DeliberationLocalServiceUtil;
import eu.strasbourg.service.council.service.VoteLocalServiceUtil;
import eu.strasbourg.utils.SearchHelper;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component(
        immediate = true,
        property = {
                "javax.portlet.name=" + StrasbourgPortletKeys.COUNCIL_BO,
                "mvc.command.name=getDeliberationsPDF"
        },
        service = MVCActionCommand.class
)
public class GetDeliberationsPDFActionCommand extends BaseMVCActionCommand {

    @Override
    protected void doProcessAction(ActionRequest request, ActionResponse response) throws Exception {
        ServiceContext sc = ServiceContextFactory.getInstance(request);
        ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        String portletName = (String) request.getAttribute(WebKeys.PORTLET_ID);

        long councilSessionId = ParamUtil.getLong(request, "councilSessionId");
        String titreConseil = "";
        CouncilSession council = CouncilSessionLocalServiceUtil.fetchCouncilSession(councilSessionId);
        if(Validator.isNotNull(council)){
            titreConseil = council.getTitle();
        }

        // on récpère toutes les délibérations du conseil de session qui sont adoptées ou rejetées avec des votes
        List<Deliberation> deliberations = DeliberationLocalServiceUtil.findByCouncilSessionId(councilSessionId)
                .stream().filter(d -> d.isRejete() ||d.isAdopte()).collect(Collectors.toList());
        for (Deliberation deliberation : deliberations) {
            // on vérifi s'il y a des votes
            List<Vote> votes = VoteLocalServiceUtil.findByDeliberationId(deliberation.getDeliberationId());
            if(Validator.isNotNull(votes) && !votes.isEmpty()){
                PrintPDF.printPDF(request, response, titreConseil, deliberation.getTitle(), votes);
            }

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
