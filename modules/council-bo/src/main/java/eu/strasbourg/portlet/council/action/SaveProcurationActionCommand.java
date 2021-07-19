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
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import eu.strasbourg.service.council.model.*;
import eu.strasbourg.service.council.service.*;
import eu.strasbourg.service.council.service.persistence.ProcurationUtil;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;
import java.text.SimpleDateFormat;
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
public class SaveProcurationActionCommand implements MVCActionCommand {

    private final Log log = LogFactoryUtil.getLog(this.getClass().getName());

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
        try {
            // Récupération du contexte de la requêtes
            ServiceContext sc = ServiceContextFactory.getInstance(request);
            ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
            this.action = ParamUtil.getString(request, "actionHidden");

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
                this.councilSessionId = ParamUtil.getLong(request, "councilSessionId");


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


                // TODO renvoi sur la page et pas sur la view des conseils


                // Validation
                boolean isValid = this.validate(request);
                if (!isValid) {
                    // Si pas valide : on reste sur la page d'édition
                    return unValid(request, response, themeDisplay);
                }

                // Mise à jour de l'entrée en base
                this.procurationLocalService.updateProcuration(procuration, sc);

            } else {

            }



        } catch (PortalException e) {
            log.error(e);
        }
        return true;
    }

    /**
     * Validation de la requête
     */
    private boolean validate(ActionRequest request) throws PortalException {
        boolean isValid = true;

        List<Procuration> listProcurationsForBeneficiary = ProcurationLocalServiceUtil.findByCouncilSessionIdAndOfficialVotersId(councilSessionId, beneficiaryId);
        int nbProcurations = listProcurationsForBeneficiary.size();

        // Vérification qu'un élu n'a pas plus de 2 procurations à son nom
        if (nbProcurations >= 2) {
            SessionErrors.add(request, "official-voters-limit-error");
            isValid = false;
        }

        // Check si le bénéficiare est absent
        List<Procuration> procurations = ProcurationLocalServiceUtil.findByCouncilSessionIdAndOfficialUnavailableId(councilSessionId, beneficiaryId);
        boolean hasOngoingProcuration = procurations.stream().anyMatch(p -> p.getEndHour() == null && p.getStartHour() != null);

        // Si le bénéficiare a une procuration qui n'est pas fermée (en cours) alors il est absent
        if (hasOngoingProcuration) {
            // TODO  warn
            SessionErrors.add(request, "beneficiary-absent-error");
        }

        // Check du statut de l'officiel qu'on modifie
        if (nbProcurations != 0) {
            // TODO  warn
            SessionErrors.add(request, "official-has-procurations-error");
        }

        // Vérification de la longueur du champ
         if (this.otherProcurationMode != null && this.otherProcurationMode.length() > 20) {
             SessionErrors.add(request, "other-procuration-mode-too-long-error");
             isValid = false;
         }

        // Vérification conseil du jour ou à venir
        GregorianCalendar gregorianCalendar = CouncilSessionLocalServiceUtil.calculDateForFindCouncil();

        Date date = gregorianCalendar.getTime();
        List<CouncilSession> councilSessions = CouncilSessionLocalServiceUtil.getFutureCouncilSessions(date);
        boolean isCouncilValid = councilSessions.stream().anyMatch(c -> c.getCouncilSessionId() == councilSessionId);
        if (!isCouncilValid) {
            isValid = false;
            SessionErrors.add(request, "not-valid-council-error");
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
