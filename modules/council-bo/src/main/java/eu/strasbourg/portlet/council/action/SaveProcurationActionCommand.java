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

    private List<OfficialTypeCouncil> availableOfficials;
    private int isPresential;
    private int procurationMode;
    private String otherProcurationMode;
    private long startDelib;
    private long procurationId;
    private long councilSessionId;

    @Override
    public boolean processAction(ActionRequest request, ActionResponse response) {
        try {
            // Récupération du contexte de la requêtes
            ServiceContext sc = ServiceContextFactory.getInstance(request);
            ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);

            // Validation
            boolean isValid = this.validate(request);
            if (!isValid) {
                // Si pas valide : on reste sur la page d'édition
                PortalUtil.copyRequestParameters(request, response);

                String portletName = (String) request.getAttribute(WebKeys.PORTLET_ID);
                PortletURL returnURL = PortletURLFactoryUtil.create(request, portletName, themeDisplay.getPlid(),
                        PortletRequest.RENDER_PHASE);

                response.setRenderParameter("returnURL", returnURL.toString());
                response.setRenderParameter("cmd", "manageProcurations");
                response.setRenderParameter("mvcPath", "/council-bo-manage-procurations.jsp");
                return false;
            }

            // Si édition ou création d'une nouvelle entrée
            Procuration procuration;
            this.procurationId = ParamUtil.getLong(request, "procurationId");
            this.isPresential = ParamUtil.getInteger(request, "isPresential");
            this.procurationMode = ParamUtil.getInteger(request, "procurationMode");
            this.otherProcurationMode = ParamUtil.getString(request, "otherProcurationMode");
            this.councilSessionId = ParamUtil.getLong(request, "councilSessionId");
//            Official official = ParamUtil.getO(request, "official");
            
            if (this.procurationId == 0) {
                procuration = this.procurationLocalService.createProcuration(sc);
            } else {
                procuration = this.procurationLocalService.getProcuration(procurationId);
            }

            // Champ : presential, procurationMode, otherProcurationMode (if procurationMode ) startDelib, councilSessionId
            procuration.setStartHour(new Date());
            procuration.setPresential(isPresential);
            procuration.setProcurationMode(procurationMode);
            procuration.setOtherProcurationMode(otherProcurationMode);
            procuration.setCouncilSessionId(councilSessionId);
            // TODO set isAfterVote à false ?? a test

            List<Deliberation> deliberations = DeliberationLocalServiceUtil.findByCouncilSessionId(councilSessionId);
            Optional<Deliberation> delibAffichageEnCours = deliberations.stream().filter(Deliberation::isAffichageEnCours).findFirst();
            Optional<Deliberation> delibVoteEnCours = deliberations.stream().filter(Deliberation::isVoteOuvert).findFirst();
            Optional<Deliberation> delibAdopteOrRejeteOrCommunique = deliberations.stream().filter((d -> d.isAdopte() || d.isRejete() || d.isCommunique())).findFirst();

            if (deliberations.stream().allMatch(Deliberation::isCree)) {
                procuration.setStartDelib(-1);
            } else if (delibAffichageEnCours.isPresent()) {
                    procuration.setStartDelib(delibAffichageEnCours.get().getDeliberationId());
            } else if (delibVoteEnCours.isPresent()) {
                // TODO erreur pas possible de creer une procuration lors d'un vote en cours
            } else {
                procuration.setStartDelib(delibAdopteOrRejeteOrCommunique.get().getDeliberationId());
                procuration.setIsAfterVote(true);
            }



            boolean isAbsent;
            long officialVotersId;
            Procuration newProcuration;

            // parcours des élus potentiels et recherche de procurations rempliess
            for (OfficialTypeCouncil availableOfficial : availableOfficials) {
                isAbsent = ParamUtil.getString(request, availableOfficial.getOfficialId() + "-isAbsent")
                        .equals("isAbsent");
                if (isAbsent) {
                    officialVotersId = ParamUtil.getLong(request, availableOfficial.getOfficialId() + "-officialVotersId");

                    newProcuration = this.procurationLocalService.createProcuration(sc);

                    newProcuration.setOfficialUnavailableId(availableOfficial.getOfficialId());
                    newProcuration.setOfficialVotersId(officialVotersId);
                    newProcuration.setIsAbsent(isAbsent);

                    this.procurationLocalService.updateProcuration(newProcuration, sc);
                }
            }
            // Fin Champs : procurations

            // Mise à jour de l'entrée en base
//            this.councilSessionLocalService.updateCouncilSession(councilSession, sc);

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

        ServiceContext sc = ServiceContextFactory.getInstance(request);

        // Procurations : test du nombre de procuration recevable max
        // recherche des élus ayant potentiellement une procuration
//        this.availableOfficials = this.officialTypeCouncilLocalService.findByTypeId(this.typeId)
//            .stream().filter(o -> o.getGroupId() == sc.getScopeGroupId()).collect(Collectors.toList());

        boolean isAbsent;
        long officialVotersId;
        Map<Long, Integer> officialProcurationCounts = new HashMap<>();

        // parcours des élus potentiels et recherche de procurations rempliess
//        for (OfficialTypeCouncil availableOfficial : availableOfficials) {
//            isAbsent = ParamUtil.getString(request, availableOfficial.getOfficialId() + "-isAbsent")
//                    .equals("isAbsent");
//            if (isAbsent) {
//                officialVotersId = ParamUtil.getLong(request, availableOfficial.getOfficialId() + "-officialVotersId");
//                if (officialVotersId > 0) {
//                    Official officialVoter = this.officialLocalService.fetchOfficial(officialVotersId);
//                    if (!officialVoter.getCouncilTypesIds().contains(""+this.typeId)) {
//                        SessionErrors.add(request, "official-voter-type-error");
//                        isValid = false;
//                    }else {
//                        if (officialProcurationCounts.containsKey(officialVotersId)) {
//                            officialProcurationCounts.put(
//                                    officialVotersId,
//                                    officialProcurationCounts.get(officialVotersId) + 1
//                            );
//                        } else {
//                            officialProcurationCounts.put(officialVotersId, 1);
//                        }
//                    }
//                }
//            }
//        }

        for (Map.Entry<Long, Integer> entry : officialProcurationCounts.entrySet()) {
            if (entry.getValue() > 2) {
                SessionErrors.add(request, "official-voters-limit-error");
                isValid = false;
            }
        }

        return isValid;
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
