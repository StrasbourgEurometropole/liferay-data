package eu.strasbourg.portlet.council.resource;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.ParamUtil;
import eu.strasbourg.service.council.model.CouncilSession;
import eu.strasbourg.service.council.model.Deliberation;
import eu.strasbourg.service.council.model.Procuration;
import eu.strasbourg.service.council.service.*;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.ActionRequest;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component(
        immediate = true,
        property = {
                "javax.portlet.name=" + StrasbourgPortletKeys.COUNCIL_BO,
                "mvc.command.name=saveProcurationDynamic"
        },
        service = MVCResourceCommand.class
)
public class SaveProcurationResourceCommand implements MVCResourceCommand{

    private final Log log = LogFactoryUtil.getLog(this.getClass().getName());

    /** Service */
    private CouncilSessionLocalService councilSessionLocalService;

    /** Params */
    private long councilSessionId;
    private long beneficiaryId;
    private int isPresential;
    private int procurationMode;
    private String otherProcurationMode;
    private boolean isAbsent = true;
    private long officialId;

    @Override
    public boolean serveResource(ResourceRequest request, ResourceResponse response) {

        try {
            ServiceContext sc = ServiceContextFactory.getInstance(request);
            this.beneficiaryId = ParamUtil.getLong(request, "beneficiaryId");
            this.councilSessionId = ParamUtil.getLong(request, "councilSessionId");

            // Récupération de la session à traiter
            this.officialId = ParamUtil.getLong(request, "officialId");
            this.isPresential = ParamUtil.getInteger(request, "presential");
            this.procurationMode = ParamUtil.getInteger(request, "procurationMode");
            if (this.procurationMode == 4) {
                this.otherProcurationMode = ParamUtil.getString(request, "otherProcurationMode");
            }

            // Set des champs de la procuration
            Procuration procuration = this.procurationLocalService.createProcuration(sc);
            procuration.setStartHour(new Date());
            procuration.setPresential(isPresential);
            procuration.setProcurationMode(procurationMode);
            procuration.setOtherProcurationMode(otherProcurationMode);
            procuration.setCouncilSessionId(councilSessionId);
            procuration.setOfficialUnavailableId(officialId);
            procuration.setOfficialVotersId(beneficiaryId);
            procuration.setIsAbsent(isAbsent);

            List<Deliberation> deliberations = DeliberationLocalServiceUtil.findByCouncilSessionId(councilSessionId);
            Optional<Deliberation> delibAffichageEnCours = deliberations.stream().filter(Deliberation::isAffichageEnCours).findFirst();
            Optional<Deliberation> delibVoteEnCours = deliberations.stream().filter(Deliberation::isVoteOuvert).findFirst();
            Optional<Deliberation> delibAdopteOrRejeteOrCommunique = deliberations.stream().filter((d -> d.isAdopte() || d.isRejete() || d.isCommunique())).findFirst();

            if (deliberations.stream().allMatch(Deliberation::isCree) || deliberations.isEmpty()) {
                procuration.setStartDelib(-1);
            } else if (delibAffichageEnCours.isPresent()) {
                procuration.setStartDelib(delibAffichageEnCours.get().getDeliberationId());
            } else if (delibVoteEnCours.isPresent()) {
                SessionErrors.add(request, "ongoing-vote-error");
                return false;
            } else {
                procuration.setStartDelib(delibAdopteOrRejeteOrCommunique.get().getDeliberationId());
                procuration.setIsAfterVote(true);
            }

            // Validation
            boolean isValid = this.validate(request);
            if (!isValid) {
                // Si pas valide : on reste sur la page d'édition
                return false;
            }

            // Mise à jour de l'entrée en base
            this.procurationLocalService.updateProcuration(procuration, sc);

        } catch (PortalException e) {
            log.error(e);
        }

        return true;
    }

    /**
     * Validation de la requête du save de l'entité
     */
    private boolean validate(ResourceRequest request) {
        boolean isValid = true;

        List<Procuration> listProcurationsForBeneficiary = ProcurationLocalServiceUtil.findByCouncilSessionIdAndOfficialVotersId(councilSessionId, beneficiaryId);
        List<Procuration> openedProcurations = listProcurationsForBeneficiary.stream().filter(p -> p.getEndHour() == null).collect(Collectors.toList());
        int nbProcurations = listProcurationsForBeneficiary.size();

        // Vérification qu'un élu n'a pas plus de 2 procurations à son nom
        if (nbProcurations >= 2) {
            SessionErrors.add(request, "official-voters-limit-error");
            return false;
        }

        // Vérification qu'il n'y a pas déjà de procuration ouverte pour cet élu
        boolean officialHasOngoingProcuration = ProcurationLocalServiceUtil.isOfficialAbsent(councilSessionId, officialId);
        if (officialHasOngoingProcuration) {
            SessionErrors.add(request, "ongoing-procuration-error");
            return false;
        }

        // Check si le bénéficiare est absent
        boolean hasOngoingProcuration = ProcurationLocalServiceUtil.isOfficialAbsent(councilSessionId, beneficiaryId);

        // Si le bénéficiare a une procuration qui n'est pas fermée (en cours) alors il est absent
        if (hasOngoingProcuration) {
            // TODO  warn
            SessionErrors.add(request, "beneficiary-absent-error");
        }

        // Check du statut de l'officiel qu'on modifie
        if (nbProcurations != 0) {
            // TODO  warn
            SessionErrors.add(request, "official-has-procurations-warn");
        }

        // Vérification de la longueur du champ
        if (this.otherProcurationMode != null && this.otherProcurationMode.length() > 20) {
            SessionErrors.add(request, "other-procuration-mode-too-long-error");
            return false;
        }

        // Vérification conseil du jour ou à venir
        GregorianCalendar gregorianCalendar = CouncilSessionLocalServiceUtil.calculDateForFindCouncil();

        Date date = gregorianCalendar.getTime();
        List<CouncilSession> councilSessions = CouncilSessionLocalServiceUtil.getFutureCouncilSessions(date);
        boolean isCouncilValid = councilSessions.stream().anyMatch(c -> c.getCouncilSessionId() == councilSessionId);
        if (!isCouncilValid) {
            SessionErrors.add(request, "not-valid-council-error");
            return false;
        }

        return isValid;
    }

    /**
     * Récupération des paramètre de la requête
     */
    private void loadParameters(ResourceRequest request) {
        this.councilSessionId = ParamUtil.getLong(request, "councilSessionId");
    }

    @Reference(unbind = "-")
    protected void setCouncilSessionLocalService(CouncilSessionLocalService councilSessionLocalService) {
        this.councilSessionLocalService = councilSessionLocalService;
    }

    @Reference(unbind = "-")
    protected void setProcurationLocalService(ProcurationLocalService procurationLocalService) {
        this.procurationLocalService = procurationLocalService;
    }

    private ProcurationLocalService procurationLocalService;

}