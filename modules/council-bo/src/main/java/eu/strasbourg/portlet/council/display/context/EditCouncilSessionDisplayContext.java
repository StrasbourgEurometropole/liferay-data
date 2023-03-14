package eu.strasbourg.portlet.council.display.context;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.WorkflowDefinitionLinkLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import eu.strasbourg.portlet.council.utils.UserRoleType;
import eu.strasbourg.service.council.constants.ProcurationModeEnum;
import eu.strasbourg.service.council.constants.ProcurationPresentialEnum;
import eu.strasbourg.service.council.model.*;
import eu.strasbourg.service.council.service.CouncilSessionLocalServiceUtil;
import eu.strasbourg.service.council.service.DeliberationLocalServiceUtil;
import eu.strasbourg.service.council.service.OfficialLocalServiceUtil;
import eu.strasbourg.service.council.service.TypeLocalServiceUtil;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

import javax.portlet.RenderRequest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class EditCouncilSessionDisplayContext {

    private CouncilSession councilSession;
    private final RenderRequest request;
    private final ThemeDisplay themeDisplay;

    public EditCouncilSessionDisplayContext(RenderRequest request) {
        this.request = request;
        this.themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
    }

    public CouncilSession getCouncilSession() {
        long councilSessionId = ParamUtil.getLong(this.request, "councilSessionId");
        if (this.councilSession == null && councilSessionId > 0) {
            this.councilSession = CouncilSessionLocalServiceUtil.fetchCouncilSession(councilSessionId);
        }
        return councilSession;
    }

    /**
     * Renvoie les types de conseil enfonction des droit de l'utilisateur
     */
    @SuppressWarnings("unused")
    public List<Type> getAuthorizedTypes() {
        List<Type> authorizedTypes = new ArrayList<>();
        List<Type> alltypes = TypeLocalServiceUtil.getTypes(-1, -1);
        List<Long> authorizedId = UserRoleType.typeIdsForUser(themeDisplay);

        for (Type type: alltypes) {
            if(authorizedId.contains(type.getTypeId())) {
                authorizedTypes.add(type);
            }
        }

        return authorizedTypes;
    }

    /**
     * Renvoie tous les élus actifs
     */
    @SuppressWarnings("unused")
    public List<Official> getAllActiveOfficials() {
        return OfficialLocalServiceUtil.findByGroupIdAndIsActive(this.themeDisplay.getSiteGroupId(), true);
    }

    /**
     * Recherche s'il existe une procuration dans la session courrante pour l'élu donné
     * @return la procuration ou null
     */
    @SuppressWarnings("unused")
    public Procuration findAssociatedProcuration(long officialId) {
        List<Procuration> procurations = new ArrayList<>();

        if(this.getCouncilSession() != null)
            procurations = this.getCouncilSession().getProcurations();

        return procurations.stream()
                .filter(procuration -> procuration.getOfficialUnavailableId() == officialId)
                .findFirst()
                .orElse(null);
    }

    /**
     * Recupere toutes les procurations pour ce conseil dans l'ordre alphabétique des noms des élus
     * @return liste des procurations
     */
    @SuppressWarnings("unused")
    public List<Procuration> getProcurationsHistoric() {
        List<Procuration> procurations = new ArrayList<>();

        if (this.getCouncilSession() != null)
            procurations = this.getCouncilSession().getProcurations();

        // Il s'agit d'un conseil pré-évolution multiconseil si les startHour des deliberations sont null
        boolean isOldCouncil = procurations.stream().allMatch(p -> p.getStartHour() == null);
        if (isOldCouncil) {
            List<Procuration> sortedByName = procurations.stream()
                .sorted(Comparator.comparing(p -> OfficialLocalServiceUtil.fetchOfficial(p.getOfficialUnavailableId()).getFullName()))
                .collect(Collectors.toList());
            return sortedByName;

        } else {
            List<Procuration> sortedByNameAndStartHour = procurations.stream()
                .sorted(Comparator.comparing(ProcurationModel::getStartHour))
                .sorted(Comparator.comparing(p -> OfficialLocalServiceUtil.fetchOfficial(p.getOfficialUnavailableId()).getFullName()))
                .collect(Collectors.toList());
            return sortedByNameAndStartHour;
        }
    }

    /**
     * Recherche l'élu correspondant à l'id
     * @return official
     */
    @SuppressWarnings("unused")
    public Official getOfficial(long officialId) {
        return OfficialLocalServiceUtil.fetchOfficial(officialId);
    }

    /**
     * Recherche le type de presentiel
     * @return official
     */
    @SuppressWarnings("unused")
    public String getProcurationPresential(int presential) {
        return ProcurationPresentialEnum.get(presential).getName();
    }

    /**
     * Recherche le type de presentiel
     * @return official
     */
    @SuppressWarnings("unused")
    public String getProcurationMode(int procurationMode) {
        return ProcurationModeEnum.get(procurationMode).getName();
    }

    /**
     * Recherche l'order de la delib
     * @return String
     */
    @SuppressWarnings("unused")
    public String getStartDelibOrder(long startDelib){
        String getStartDelibOrder = "";
        Deliberation startDeliberation = null;
        try {
            startDeliberation = DeliberationLocalServiceUtil.getDeliberation(startDelib);
            if(Validator.isNotNull(startDeliberation)){
                getStartDelibOrder = startDelib == -1?"": String.valueOf(startDeliberation.getOrder());
            }
        } catch (PortalException e) {
            _log.error(e.getMessage() + " : " + startDelib);
        }
        return getStartDelibOrder;
    }

    /**
     * Recherche l'order de la delib
     * @return String
     */
    @SuppressWarnings("unused")
    public String getEndDelibOrder(long endDelib){
        String getEndDelibOrder = "";
        Deliberation endDeliberation = null;
        try {
            endDeliberation = DeliberationLocalServiceUtil.getDeliberation(endDelib);
            if(Validator.isNotNull(endDeliberation)){
                getEndDelibOrder = endDelib == -1?"": String.valueOf(endDeliberation.getOrder());
            }
        } catch (PortalException e) {
            _log.error(e.getMessage() + " : " + endDelib);
        }
        return getEndDelibOrder;
    }

    /**
     * Recherche l'heure de debut de la procuration
     * @return String
     */
    @SuppressWarnings("unused")
    public String getStartHour(Procuration procuration) {
        Date startHour = procuration.getStartHour();
        if (startHour != null) {
            SimpleDateFormat hour = new SimpleDateFormat("HH", Locale.FRANCE);
            SimpleDateFormat minute = new SimpleDateFormat("mm", Locale.FRANCE);

            String startMinuteFormatted = minute.format(startHour).equals("00") ? "" : minute.format(startHour);
            String startHourFormatted = hour.format(startHour);

            String startTime = startHourFormatted +"h"+ startMinuteFormatted;

            return startTime;
        } else {
            return "";
        }
    }

    /**
     * Recherche l'heure de la fin de la procuration
     * @return String
     */
    @SuppressWarnings("unused")
    public String getEndHour(Procuration procuration) {

        Date endHour = procuration.getEndHour();
        if (endHour != null) {
            SimpleDateFormat hour = new SimpleDateFormat("HH", Locale.FRANCE);
            SimpleDateFormat minute = new SimpleDateFormat("mm", Locale.FRANCE);

            String endTime = null;
            if(Validator.isNotNull(endHour)){
                String endMinute = minute.format(endHour).equals("00") ? "" : minute.format(endHour);
                endTime = hour.format(endHour) +"h"+ endMinute;
            }
            return endTime;
        } else {
            return "";
        }
    }

    /**
     * Verifie s'il y a encore une ou des procurations ouvertes
     * @return Boolean
     */
    @SuppressWarnings("unused")
    public Boolean isStillOpen(){
        Boolean isStillOpen = false;
        List<Procuration> procurations;
        if(this.getCouncilSession() != null) {
            procurations = this.getCouncilSession().getProcurations();
            for(Procuration procuration : procurations){
                if(Validator.isNull(procuration.getEndHour()) || procuration.getEndDelib()==-1){
                    isStillOpen = true;
                } else {
                    try{
                        if(Validator.isNull(DeliberationLocalServiceUtil.getDeliberation(procuration.getEndDelib()))){
                            isStillOpen = true;
                        }
                    } catch(Exception e) {}
                }
            }
        }

        return isStillOpen;
    }

    @SuppressWarnings("unused")
    public long getGroupId() {
        return this.themeDisplay.getScopeGroupId();
    }

    /**
     * @return True si le framework workflow est actif pour ce type d'entité
     */
    @SuppressWarnings("unused")
    public boolean isWorkflowEnabled() {
        return WorkflowDefinitionLinkLocalServiceUtil.hasWorkflowDefinitionLink(
                this.themeDisplay.getCompanyId(), this.themeDisplay.getCompanyGroupId(),
                CouncilSession.class.getName());
    }

    /**
     * Wrapper autour du permission checker pour les permissions de module
     */
    @SuppressWarnings("unused")
    public boolean hasPermission(String actionId) {
        return this.themeDisplay.getPermissionChecker().hasPermission(
                this.themeDisplay.getScopeGroupId(), StrasbourgPortletKeys.COUNCIL_BO,
                StrasbourgPortletKeys.COUNCIL_BO, actionId);
    }

    private final Log _log = LogFactoryUtil.getLog(this.getClass());

}
