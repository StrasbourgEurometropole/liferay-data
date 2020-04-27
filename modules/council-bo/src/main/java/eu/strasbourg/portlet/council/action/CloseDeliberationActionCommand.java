package eu.strasbourg.portlet.council.action;

import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import eu.strasbourg.service.council.constants.StageDeliberation;
import eu.strasbourg.service.council.model.CouncilSession;
import eu.strasbourg.service.council.model.Deliberation;
import eu.strasbourg.service.council.model.Vote;
import eu.strasbourg.service.council.service.CouncilSessionLocalService;
import eu.strasbourg.service.council.service.DeliberationLocalService;
import eu.strasbourg.service.council.service.VoteLocalService;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;
import java.util.Date;
import java.util.List;

@Component(
        immediate = true,
        property = { "javax.portlet.name=" + StrasbourgPortletKeys.COUNCIL_BO,
                "mvc.command.name=closeDeliberation" },
        service = MVCActionCommand.class)
public class CloseDeliberationActionCommand extends BaseMVCActionCommand {

    final private static String POUR="pour";
    final private static String CONTRE="contre";

    private VoteLocalService voteLocalService;
    private DeliberationLocalService deliberationLocalService;
    private CouncilSessionLocalService councilSessionLocalService;

    @Reference(unbind = "-")
    protected void setVoteLocalService(
            VoteLocalService voteLocalService) {
        this.voteLocalService = voteLocalService;
    }

    @Reference(unbind = "-")
    protected void setDeliberationLocalService(
            DeliberationLocalService deliberationLocalService) {
        this.deliberationLocalService = deliberationLocalService;
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

        long deliberationId = ParamUtil.getLong(request, "deliberationId");
        Deliberation deliberation = deliberationLocalService.getDeliberation(deliberationId);

        //Récupération des votes associés
        List<Vote> votes  = voteLocalService.findByDeliberationId(deliberationId);

        int countPour=0;
        int countContre=0;

        for (Vote vote:votes) {
            if(vote.getResult().toLowerCase().equals(POUR)) {
                countPour++;
            } else if(vote.getResult().toLowerCase().equals(CONTRE)) {
                countContre++;
            }
        }

        int majoriteAbsolue = 0;

        // Calcule la majorité absolue
        if((countContre + countPour) % 2 ==0  ) {
            majoriteAbsolue = ((countContre + countPour)/2);
        }
        else {
            majoriteAbsolue = (countContre + countPour - 1)/2;
        }

        // Calcule le résultat
        if(countPour > majoriteAbsolue) {
            // Majorité absolue de Pour => ADOPTE
            deliberation.setStage(StageDeliberation.get(4).getName());
        } else if (countPour == countContre) {
            // Egalite, on va cherche le chef de session
            CouncilSession council = councilSessionLocalService.getCouncilSession(deliberation.getCouncilSessionId());
            Vote voteLeader = voteLocalService.findByDeliberationIdandOfficialId(deliberationId, council.getOfficialLeaderId());

            // S'il a voté
            if(voteLeader != null) {
                if(voteLeader.getResult().toLowerCase().equals(CONTRE)) {
                    // Si il a voté Contre => REJETE
                    deliberation.setStage(StageDeliberation.get(5).getName());
                } else {
                    // Sinon => POUR
                    deliberation.setStage(StageDeliberation.get(4).getName());
                }

            } else {
                // S'il n'a pas voté )> ADOPTE
                deliberation.setStage(StageDeliberation.get(4).getName());
            }

        } else {
            /// Pas de majorité pour le Pour => REJETE
            deliberation.setStage(StageDeliberation.get(5).getName());
        }
        deliberation.setStatusDate(new Date());

        // Update de l'entité
        deliberationLocalService.updateDeliberation(deliberation);

        // Post / Redirect / Get si tout est bon
        PortletURL renderURL = PortletURLFactoryUtil.create(request,
                portletName, themeDisplay.getPlid(), PortletRequest.RENDER_PHASE);
        renderURL.setParameter("tab", request.getParameter("tab"));
        response.sendRedirect(renderURL.toString());
    }

}
