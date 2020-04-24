package eu.strasbourg.portlet.council.action;

import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import eu.strasbourg.service.council.model.CouncilSession;
import eu.strasbourg.service.council.model.Deliberation;
import eu.strasbourg.service.council.model.Official;
import eu.strasbourg.service.council.model.Procuration;
import eu.strasbourg.service.council.service.CouncilSessionLocalService;
import eu.strasbourg.service.council.service.DeliberationLocalService;
import eu.strasbourg.service.council.service.OfficialLocalService;
import eu.strasbourg.service.council.service.ProcurationLocalService;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;
import java.util.List;

@Component(
        immediate = true,
        property = { "javax.portlet.name=" + StrasbourgPortletKeys.COUNCIL_BO,
                "mvc.command.name=openDeliberation" },
        service = MVCActionCommand.class)
public class OpenDeliberationActionCommand extends BaseMVCActionCommand {

    private DeliberationLocalService deliberationLocalService;
    private ProcurationLocalService procurationLocalService;
    private OfficialLocalService officialLocalService;
    private CouncilSessionLocalService councilSessionLocalService;

    public static final String MUNICIPAL = "municipal";
    public static final String EUROMETROPOLITAN = "eurometropolitan";

    @Reference(unbind = "-")
    protected void setDeliberationLocalService(
            DeliberationLocalService deliberationLocalService) {
        this.deliberationLocalService = deliberationLocalService;
    }

    @Reference(unbind = "-")
    protected void setProcurationLocalService(
            ProcurationLocalService procurationLocalService) {
        this.procurationLocalService = procurationLocalService;
    }

    @Reference(unbind = "-")
    protected void setOfficialLocalService(
            OfficialLocalService officialLocalService) {
        this.officialLocalService = officialLocalService;
    }

    @Reference(unbind = "-")
    protected void setCouncilSessionLocalService(
            CouncilSessionLocalService councilSessionLocalService) {
        this.councilSessionLocalService = councilSessionLocalService;
    }

    @Override
    protected void doProcessAction(ActionRequest request,
                                   ActionResponse response) throws Exception {
        ServiceContext sc = ServiceContextFactory.getInstance(request);
        ThemeDisplay themeDisplay = (ThemeDisplay) request
                .getAttribute(WebKeys.THEME_DISPLAY);
        String portletName = (String) request.getAttribute(WebKeys.PORTLET_ID);

        String stage = ParamUtil.getString(request, "stage");
        long deliberationId = ParamUtil.getLong(request, "deliberationId");
        Deliberation deliberation = deliberationLocalService.getDeliberation(deliberationId);
        CouncilSession councilSession = councilSessionLocalService.fetchCouncilSession(deliberation.getCouncilSessionId());

        List<Procuration> procurations = procurationLocalService.findByCouncilSessionId(deliberation.getCouncilSessionId());

        String type = councilSession.isEurometropolitan() ? EUROMETROPOLITAN:MUNICIPAL;

        List<Official> officials = officialLocalService.findByGroupIdAndIsActiveAndType(themeDisplay.getScopeGroupId(), true, type);


        int countOfficialActive = officials.size();
        int countAbsentWithoutProc = Math.toIntExact(procurations.stream().filter(x -> x.isIsAbsent() && x.getOfficialVotersId() == 0).count());
        int countOfficialVoting = countOfficialActive - countAbsentWithoutProc;

        deliberation.setCountOfficialsActive(countOfficialActive);
        deliberation.setCountOfficialsVoting(countOfficialVoting);


        // Calcule la valeur du quorum
        int quorum = (int)Math.floor(((double) countOfficialActive / 3) + 1);

        // Vérifie la présence du quorum
        if(countOfficialVoting >= quorum) {
            // Passe au statut "Vote ouvert"
            deliberation.setStage(stage);
        } else {
            // Pas de quorum, on avertit
            String[] args = new String[]{String.valueOf(countOfficialVoting), String.valueOf(countOfficialActive), String.valueOf(quorum)};
            SessionErrors.add(request, "quorum-error", args);
        }
        // Update de l'entité (même si pas de quorum on enregistre les chiffres pour que les gens puissent vérifier
        deliberationLocalService.updateDeliberation(deliberation);

        // Post / Redirect / Get si tout est bon
        PortletURL renderURL = PortletURLFactoryUtil.create(request,
                portletName, themeDisplay.getPlid(), PortletRequest.RENDER_PHASE);
        renderURL.setParameter("tab", request.getParameter("tab"));
        response.sendRedirect(renderURL.toString());

    }
}
