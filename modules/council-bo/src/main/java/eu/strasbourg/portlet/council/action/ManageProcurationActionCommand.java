package eu.strasbourg.portlet.council.action;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;
import eu.strasbourg.service.council.model.*;
import eu.strasbourg.service.council.service.*;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;
import java.util.*;
import java.util.stream.Collectors;

@Component(
        immediate = true,
        property = {
                "javax.portlet.name=" + StrasbourgPortletKeys.COUNCIL_BO,
                "mvc.command.name=saveProcuration"
        },
        service = MVCActionCommand.class
)
public class ManageProcurationActionCommand implements MVCActionCommand {

    private final Log log = LogFactoryUtil.getLog(this.getClass().getName());

    private long procurationId;
    private int isPresential;
    private int procurationMode;
    private String otherProcurationMode;
    private long councilSessionId;
    private boolean isAbsent = true;
    private long officialId;
    private long beneficiaryId;
    private String action;

    @Override
    public boolean processAction(ActionRequest request, ActionResponse response) {

        // Récupération du contexte de la requêtes
        ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);

        try {
            ServiceContext sc = ServiceContextFactory.getInstance(request);
            this.action = ParamUtil.getString(request, "actionHidden");
            this.procurationId = ParamUtil.getLong(request, "procurationIdHidden");
            this.councilSessionId = ParamUtil.getLong(request, "councilSessionId");


            // Soit on veut enregistrer une procuration, soit la supprimer
            if (this.action.equals("save")) {

                // Si édition ou création d'une nouvelle entrée
                this.officialId = ParamUtil.getLong(request, "officalIdHidden");
                this.beneficiaryId = ParamUtil.getLong(request, this.officialId + "-officialVotersId");
                this.isPresential = ParamUtil.getInteger(request, this.officialId + "-presentialSelect");
                this.procurationMode = ParamUtil.getInteger(request, this.officialId + "-modeSelect");
                if (this.procurationMode == 4) {
                    this.otherProcurationMode = ParamUtil.getString(request, this.officialId + "-autre");
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
                    return unValid(request, response, themeDisplay);
                } else {
                    procuration.setStartDelib(delibAdopteOrRejeteOrCommunique.get().getDeliberationId());
                    procuration.setIsAfterVote(true);
                }

                // Validation
                boolean isValid = this.validateSave(request);
                if (!isValid) {
                    // Si pas valide : on reste sur la page d'édition
                    return unValid(request, response, themeDisplay);
                }

                // Mise à jour de l'entrée en base
                this.procurationLocalService.updateProcuration(procuration, sc);

            } else if (this.action.equals("closeAll")) {

                List<Procuration> allProcurationsForCouncil = ProcurationLocalServiceUtil.findByCouncilSessionId(councilSessionId);
                List<Procuration> openedProcurationsForCouncil = allProcurationsForCouncil.stream().filter(p -> p.getEndHour() == null).collect(Collectors.toList());

                for (Procuration procuration : openedProcurationsForCouncil) {
                    boolean isRequestUnvalid = isUnvalid(request, sc, procuration);
                    if (isRequestUnvalid) {
                        return unValid(request, response, themeDisplay);
                    }
                }

            } else {

                Procuration savedProcuration = ProcurationLocalServiceUtil.fetchProcuration(this.procurationId);
                boolean isUnvalidRequest = isUnvalid(request, sc, savedProcuration);
                if (isUnvalidRequest) {
                    return unValid(request, response, themeDisplay);
                }
            }
        } catch (PortalException e) {
            log.error(e);
        }
        return valid(request, response, themeDisplay);
    }

    private boolean isUnvalid(ActionRequest request, ServiceContext sc, Procuration savedProcuration) throws PortalException {

        if (savedProcuration.getStartDelib() == -1 && savedProcuration.isIsAfterVote()) {
            ProcurationLocalServiceUtil.removeProcuration(savedProcuration.getProcurationId());
        }

        List<Deliberation> deliberations = DeliberationLocalServiceUtil.findByCouncilSessionId(councilSessionId);
        Optional<Deliberation> delibAfficheOrAdopteOrRejeteOrCommunique = deliberations.stream().filter((d -> d.isAdopte() || d.isRejete() || d.isCommunique() || d.isAffichageEnCours())).findFirst();
        CouncilSession councilSession = CouncilSessionLocalServiceUtil.fetchCouncilSession(councilSessionId);

        if (delibAfficheOrAdopteOrRejeteOrCommunique.isPresent()) {
            savedProcuration.setEndDelib(councilSession.getLastDelibProcessed());
        }

        boolean isValid = validateClose(request, deliberations, savedProcuration);
        if (!isValid) {
            // Si pas valide : on reste sur la page d'édition
            return true;
        }

        // Update de l'entité
        savedProcuration.setEndHour(new Date());
        this.procurationLocalService.updateProcuration(savedProcuration, sc);
        return false;
    }

    /**
     * Validation de la requête du save de l'entité
     */
    private boolean validateSave(ActionRequest request) {
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
     * Validation de la requête du delete de l'entité
     */
    private boolean validateClose(ActionRequest request, List<Deliberation> deliberations, Procuration savedProcuration) {

        boolean isValid = true;

        // Vérification si un vote est en cours
        Optional<Deliberation> delibVoteEnCours = deliberations.stream().filter(Deliberation::isVoteOuvert).findFirst();
        if (delibVoteEnCours.isPresent()) {
            SessionErrors.add(request, "ongoing-vote-delete-error");
            return false;
        }
        // Vérification si la procuration est déjà fermée
        if (savedProcuration.getEndHour() != null) {
            SessionErrors.add(request, "already-closed-procuration-error");
        }

        return isValid;
    }

    /**
     * Gestion du retour orsqu'une erreur se produit
     */
    private boolean unValid(ActionRequest request, ActionResponse response, ThemeDisplay themeDisplay) {

        PortalUtil.copyRequestParameters(request, response);

        String portletName = (String) request.getAttribute(WebKeys.PORTLET_ID);
        PortletURL returnURL = PortletURLFactoryUtil.create(request, portletName, themeDisplay.getPlid(),
                PortletRequest.RENDER_PHASE);

        response.setRenderParameter("returnURL", returnURL.toString());
        response.setRenderParameter("cmd", "manageProcurations");
        response.setRenderParameter("mvcPath", "/council-bo-manage-procurations.jsp");
        return false;
    }

    /**
     * Gestion du retour sans erreur
     */
    private boolean valid(ActionRequest request, ActionResponse response, ThemeDisplay themeDisplay) {

        PortalUtil.copyRequestParameters(request, response);

        String portletName = (String) request.getAttribute(WebKeys.PORTLET_ID);
        PortletURL returnURL = PortletURLFactoryUtil.create(request, portletName, themeDisplay.getPlid(),
                PortletRequest.RENDER_PHASE);

        response.setRenderParameter("returnURL", returnURL.toString());
        response.setRenderParameter("cmd", "manageProcurations");
        response.setRenderParameter("mvcPath", "/council-bo-manage-procurations.jsp");
        return true;
    }

    @Reference(unbind = "-")
    protected void setCouncilSessionLocalService(CouncilSessionLocalService councilSessionLocalService) {
        this.councilSessionLocalService = councilSessionLocalService;
    }

    @Reference(unbind = "-")
    protected void setOfficialLocalService(OfficialLocalService officialLocalService) {
        this.officialLocalService = officialLocalService;
    }

    @Reference(unbind = "-")
    protected void setOfficialTypeCouncilLocalService(OfficialTypeCouncilLocalService officialTypeCouncilLocalService) {
        this.officialTypeCouncilLocalService = officialTypeCouncilLocalService;
    }

    @Reference(unbind = "-")
    protected void setProcurationLocalService(ProcurationLocalService procurationLocalService) {
        this.procurationLocalService = procurationLocalService;
    }

    private CouncilSessionLocalService councilSessionLocalService;
    private OfficialLocalService officialLocalService;
    private OfficialTypeCouncilLocalService officialTypeCouncilLocalService;
    private ProcurationLocalService procurationLocalService;
}
