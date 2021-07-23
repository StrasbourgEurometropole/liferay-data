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
public class SaveProcurationResourceCommand implements MVCResourceCommand {

    private final Log log = LogFactoryUtil.getLog(this.getClass().getName());

    /**
     * Params
     */
    private long councilSessionId;
    private long beneficiaryId;
    private int isPresential;
    private int procurationMode;
    private String otherProcurationMode;
    private boolean isAbsent = true;
    private long officialId;

    private JSONObject message = JSONFactoryUtil.createJSONObject();
    private JSONObject error = JSONFactoryUtil.createJSONObject();
    private JSONObject warn = JSONFactoryUtil.createJSONObject();

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
                this.error.put("error", "Erreur : Impossible de cr\u00E9er la procuration, un vote est en cours");
                return false;
            } else {
                procuration.setStartDelib(delibAdopteOrRejeteOrCommunique.get().getDeliberationId());
                procuration.setIsAfterVote(true);
            }

            // On passe le JSON dans la reponse pour l'utiliser dans le JS
            // Recuperation de l'élément d'écriture de la réponse
            PrintWriter writer = null;

            try {
                writer = response.getWriter();
            } catch (IOException e) {
                log.error(e);
            }
            // Validation
            boolean isValid = this.validate();
            if (!isValid) {
                message.put("error", error);
                // On passe le JSON dans la reponse pour l'utiliser dans le JS
                writer.print(message.toString());
                return false;
            }

            message.put("warn", warn);
            message.put("procurationId", procuration.getProcurationId());

            // On passe le JSON dans la reponse pour l'utiliser dans le JS
            writer.print(message.toString());

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
    private boolean validate() {
        boolean isValid = true;

        Official absentOfficial = OfficialLocalServiceUtil.fetchOfficial(officialId);
        String absentOfficialName = absentOfficial.getFullName();
        Official beneficiary = OfficialLocalServiceUtil.fetchOfficial(beneficiaryId);
        String beneficiaryName = beneficiary.getFullName();

        // Champs obligatoires
        if (procurationMode == 0) {
            this.error.put("error", "Erreur : Il faut choisir un mode de procuration pour l'\u00E9lu " + absentOfficialName);
            return false;
        }

        if (beneficiaryId == 0) {
            this.error.put("error", "Erreur : il faut choisir un b\u00E9n\u00E9ficiaire pour l'\u00E9lu " + absentOfficialName);
            return false;
        }

        List<Procuration> listProcurationsForBeneficiary = ProcurationLocalServiceUtil.findByCouncilSessionIdAndOfficialVotersId(councilSessionId, beneficiaryId);
        List<Procuration> openedProcurations = listProcurationsForBeneficiary.stream().filter(p -> p.getEndHour() == null).collect(Collectors.toList());
        int nbProcurations = openedProcurations.size();

        // Vérification qu'un élu n'a pas plus de 2 procurations à son nom
        if (nbProcurations >= 2) {
            this.error.put("error", "Erreur : Le b\u00E9n\u00E9ficiare " + beneficiaryName + " a d\u00E9j\u00E0 deux procurations à son nom");
            return false;
        }

        // Vérification qu'il n'y a pas déjà de procuration ouverte pour cet élu
        boolean officialHasOngoingProcuration = ProcurationLocalServiceUtil.isOfficialAbsent(councilSessionId, officialId);
        if (officialHasOngoingProcuration) {
            this.error.put("error", "Erreur : Impossible de cr\u00E9er la procuration, l'\u00E9lu " + absentOfficialName + " a d\u00E9j\u00E0 une procuration en cours");
            return false;
        }

        // Check si le bénéficiare est absent
        boolean hasOngoingProcuration = ProcurationLocalServiceUtil.isOfficialAbsent(councilSessionId, beneficiaryId);

        // Si le bénéficiare a une procuration qui n'est pas fermée (en cours) alors il est absent
        if (hasOngoingProcuration) {
            this.warn.put("warn", "Warning : Le b\u00E9n\u00E9ficiaire " + beneficiaryName + " de la procuration est absent");
        }

        // Check du statut de l'officiel qu'on modifie
        if (nbProcurations != 0) {
            this.warn.put("warn", "Warning : L'\u00E9lu " + absentOfficialName + " est b\u00E9n\u00E9ficiare d'une ou plusieurs procurations");
        }

        // Vérification de la longueur du champ
        if (this.otherProcurationMode != null && this.otherProcurationMode.length() > 20) {
            this.error.put("error", "Erreur : Le mode de procuration saisi pour l'\u00E9lu " + absentOfficialName + " est trop long");
            return false;
        }

        // Vérification conseil du jour ou à venir
        GregorianCalendar gregorianCalendar = CouncilSessionLocalServiceUtil.calculDateForFindCouncil();

        Date date = gregorianCalendar.getTime();
        List<CouncilSession> councilSessions = CouncilSessionLocalServiceUtil.getFutureCouncilSessions(date);
        boolean isCouncilValid = councilSessions.stream().anyMatch(c -> c.getCouncilSessionId() == councilSessionId);
        if (!isCouncilValid) {
            this.error.put("error", "Erreur : Le conseil n'est pas valide, ce doit \u00EAtre un conseil du jour ou \u00E0 venir");
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