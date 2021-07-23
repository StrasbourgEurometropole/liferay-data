package eu.strasbourg.portlet.council.resource;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.util.ParamUtil;
import eu.strasbourg.service.council.model.CouncilSession;
import eu.strasbourg.service.council.model.Deliberation;
import eu.strasbourg.service.council.model.Official;
import eu.strasbourg.service.council.model.Procuration;
import eu.strasbourg.service.council.service.*;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component(
        immediate = true,
        property = {
                "javax.portlet.name=" + StrasbourgPortletKeys.COUNCIL_BO,
                "mvc.command.name=closeProcuration"
        },
        service = MVCResourceCommand.class
)
public class CloseProcurationResourceCommand implements MVCResourceCommand {

    private final Log log = LogFactoryUtil.getLog(this.getClass().getName());

    private JSONObject message = JSONFactoryUtil.createJSONObject();
    private JSONObject error = JSONFactoryUtil.createJSONObject();

    /**
     * Params
     */
    private long councilSessionId;
    private long procurationId;
    private long officialId;
    private String action;

    @Override
    public boolean serveResource(ResourceRequest request, ResourceResponse response) {

        try {
            ServiceContext sc = ServiceContextFactory.getInstance(request);

            this.councilSessionId = ParamUtil.getLong(request, "councilSessionId");
            this.action = ParamUtil.getString(request, "action");

            if (this.action.equals("closeAll")) {

                List<Procuration> allProcurationsForCouncil = ProcurationLocalServiceUtil.findByCouncilSessionId(councilSessionId);
                List<Procuration> openedProcurationsForCouncil = allProcurationsForCouncil.stream().filter(p -> p.getEndHour() == null).collect(Collectors.toList());

                for (Procuration procuration : openedProcurationsForCouncil) {
                    boolean isRequestUnvalid = isUnvalid(response, sc, procuration);
                    if (isRequestUnvalid) {
                        return false;
                    }
                }

            } else {
                this.officialId = ParamUtil.getLong(request, "officialId");
                this.procurationId = ParamUtil.getLong(request, "procurationId");

                Procuration savedProcuration = ProcurationLocalServiceUtil.fetchProcuration(this.procurationId);

                boolean unvalid = isUnvalid(response, sc, savedProcuration);
                if (unvalid) {
                    return false;
                }
            }


        } catch (PortalException e) {
            log.error(e);
        }

        return true;
    }

    private boolean isUnvalid(ResourceResponse response, ServiceContext sc, Procuration savedProcuration) throws PortalException {

        if (savedProcuration.getStartDelib() == -1 && savedProcuration.isIsAfterVote()) {
            ProcurationLocalServiceUtil.removeProcuration(savedProcuration.getProcurationId());
        }

        List<Deliberation> deliberations = DeliberationLocalServiceUtil.findByCouncilSessionId(councilSessionId);
        Optional<Deliberation> delibAfficheOrAdopteOrRejeteOrCommunique = deliberations.stream().filter((d -> d.isAdopte() || d.isRejete() || d.isCommunique() || d.isAffichageEnCours())).findFirst();
        CouncilSession councilSession = CouncilSessionLocalServiceUtil.fetchCouncilSession(councilSessionId);

        if (delibAfficheOrAdopteOrRejeteOrCommunique.isPresent()) {
            savedProcuration.setEndDelib(councilSession.getLastDelibProcessed());
        }

        boolean isValid = validate(deliberations, savedProcuration);
        if (isValid) {
            // Update de l'entité
            savedProcuration.setEndHour(new Date());
            this.procurationLocalService.updateProcuration(savedProcuration, sc);
        } else {
            // On passe le JSON dans la reponse pour l'utiliser dans le JS
            // Recuperation de l'élément d'écriture de la réponse
            PrintWriter writer = null;

            try {
                writer = response.getWriter();
            } catch (IOException e) {
                log.error(e);
            }

            message.put("error", error);

            // On passe le JSON dans la reponse pour l'utiliser dans le JS
            writer.print(message.toString());

            return true;
        }
        return false;
    }

    /**
     * Validation de la requête du delete de l'entité
     */
    private boolean validate(List<Deliberation> deliberations, Procuration savedProcuration) {

        boolean isValid = true;
        long officialId = savedProcuration.getOfficialUnavailableId();
        Official absentOfficial = OfficialLocalServiceUtil.fetchOfficial(officialId);
        String absentOfficialName = absentOfficial.getFullName();

        // Vérification si un vote est en cours
        Optional<Deliberation> delibVoteEnCours = deliberations.stream().filter(Deliberation::isVoteOuvert).findFirst();
        if (delibVoteEnCours.isPresent()) {
            this.error.put("error", "Erreur : Impossible de supprimer la procuration pour " + absentOfficialName + ", un vote est en cours");
            return false;
        }
        // Vérification si la procuration est déjà fermée
        if (savedProcuration.getEndHour() != null) {
            this.error.put("error", "Erreur : La procuration pour " + absentOfficialName + "est d\u00E9j\u00E0 ferm\u00E0e");
            return false;
        }

        return isValid;
    }

    @Reference(unbind = "-")
    protected void setProcurationLocalService(ProcurationLocalService procurationLocalService) {
        this.procurationLocalService = procurationLocalService;
    }

    private ProcurationLocalService procurationLocalService;

}