package eu.strasbourg.portlet.council.action;

import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import eu.strasbourg.service.council.constants.DeliberationDataConstants;
import eu.strasbourg.service.council.constants.StageDeliberation;
import eu.strasbourg.service.council.model.Deliberation;
import eu.strasbourg.service.council.model.DeliberationModel;
import eu.strasbourg.service.council.model.Procuration;
import eu.strasbourg.service.council.model.ProcurationModel;
import eu.strasbourg.service.council.service.CouncilSessionLocalService;
import eu.strasbourg.service.council.service.DeliberationLocalService;
import eu.strasbourg.service.council.service.ProcurationLocalService;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component(
        immediate = true,
        property = {"javax.portlet.name=" + StrasbourgPortletKeys.COUNCIL_BO,
                "mvc.command.name=deleteDeliberation"},
        service = MVCActionCommand.class)
public class DeleteDeliberationActionCommand extends BaseMVCActionCommand {

    private DeliberationLocalService deliberationLocalService;

    @Reference(unbind = "-")
    protected void setDeliberationLocalService(
            DeliberationLocalService deliberationLocalService) {
        this.deliberationLocalService = deliberationLocalService;
    }

    private CouncilSessionLocalService councilSessionLocalService;

    @Reference(unbind = "-")
    protected void setCouncilSessionLocalService(
            CouncilSessionLocalService councilSessionLocalService) {
        this.councilSessionLocalService = councilSessionLocalService;
    }

    private ProcurationLocalService procurationLocalService;

    @Reference(unbind = "-")
    protected void setProcurationLocalService(
            ProcurationLocalService procurationLocalService) {
        this.procurationLocalService = procurationLocalService;
    }

    @Override
    protected void doProcessAction(ActionRequest request,
                                   ActionResponse response) throws Exception {
        ServiceContext sc = ServiceContextFactory.getInstance(request);
        ThemeDisplay themeDisplay = (ThemeDisplay) request
                .getAttribute(WebKeys.THEME_DISPLAY);
        String portletName = (String) request.getAttribute(WebKeys.PORTLET_ID);

        long deliberationId = ParamUtil.getLong(request, "deliberationId");

        // Partie procuration
        Deliberation deliberation = deliberationLocalService.getDeliberation(deliberationId);
        // Récupération des procurations du conseil de la deliberation
        List<Procuration> procurations = councilSessionLocalService.getCouncilSession(deliberation.getCouncilSessionId()).getProcurations();
        // Récupère les delibs qui ne sont pas en CREE
        List<Deliberation> notCreated = deliberationLocalService.findByCouncilSessionId(deliberation.getCouncilSessionId()).stream()
                .filter(d -> !d.getStage().equals(StageDeliberation.CREE.getName()))
                .collect(Collectors.toList());
        for (Procuration procuration : procurations) {
            boolean updateProc = false;
            // Verifie si la procuration a pour startDelib la delib qui va être supprimé si oui on set à -1
            if (procuration.getStartDelib() == deliberationId) {
                procuration.setStartDelib(-1);
                // Si la procuration n'a pas de delib de fin alors on set le isAfterVote a true
                if (Validator.isNull(procuration.getEndHour())) {
                    procuration.setIsAfterVote(true);
                }
                updateProc = true;
            }
            // Verifie si la liste des delibs contient seulement la delib en cours de suppression
            if (notCreated.size() == 1 && notCreated.contains(deliberation)) {
                // Si oui mettre -1 en endDelib car pas d'autre deliberation possible
                if (procuration.getEndDelib() == deliberationId) {
                    procuration.setEndDelib(-1);
                    updateProc = true;
                }
            } else {
                // Si non recuperer les delib qui sont comprisent entre start et end Hour de la proc et utiliser la derniere pour set endDelib
                if (Validator.isNotNull(procuration.getEndHour())) {
                    List<Deliberation> voteds = notCreated.stream()
                            .filter(d -> Validator.isNotNull(d.getEndVoteDate()))
                            .filter(d -> d.getEndVoteDate().after(procuration.getStartHour()) && d.getEndVoteDate().before(procuration.getEndHour()))
                            .sorted(Comparator.comparing(DeliberationModel::getEndVoteDate))
                            .collect(Collectors.toList());
                    if (voteds.isEmpty()) {
                        // Si pas de delib dans la liste set à -1
                        procuration.setEndDelib(-1);
                        updateProc = true;
                    } else {
                        Deliberation voted = voteds.get(voteds.size() - 1);
                        procuration.setEndDelib(voted.getDeliberationId());
                        updateProc = true;
                    }
                }
            }
            if (updateProc)
                procurationLocalService.updateProcuration(procuration);
        }

        // Suppression de l'entité
        deliberationLocalService.removeDeliberation(deliberationId);

        // Post / Redirect / Get si tout est bon
        PortletURL renderURL = PortletURLFactoryUtil.create(request,
                portletName, themeDisplay.getPlid(), PortletRequest.RENDER_PHASE);
        renderURL.setParameter("tab", request.getParameter("tab"));
        response.sendRedirect(renderURL.toString());
    }
}
