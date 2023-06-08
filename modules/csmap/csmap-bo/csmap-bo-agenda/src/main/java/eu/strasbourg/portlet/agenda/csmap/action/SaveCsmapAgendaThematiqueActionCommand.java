package eu.strasbourg.portlet.agenda.csmap.action;

import com.liferay.asset.kernel.model.AssetVocabulary;
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
import eu.strasbourg.service.csmap.constants.CodeCacheEnum;
import eu.strasbourg.service.csmap.model.Agenda;
import eu.strasbourg.service.csmap.service.AgendaLocalService;
import eu.strasbourg.service.csmap.service.CsmapCacheLocalService;
import eu.strasbourg.utils.AssetVocabularyHelper;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import eu.strasbourg.utils.constants.VocabularyNames;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

@Component(
        immediate = true,
        property = {"javax.portlet.name=" + StrasbourgPortletKeys.CSMAP_BO_AGENDA,
                "mvc.command.name=saveAgendaThematique"},
        service = MVCActionCommand.class)
public class SaveCsmapAgendaThematiqueActionCommand implements MVCActionCommand {

    @Override
    public boolean processAction(ActionRequest request, ActionResponse response) {
        ServiceContext sc;
        try {
            sc = ServiceContextFactory.getInstance(request);
            ThemeDisplay td = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
            sc.setScopeGroupId(td.getCompanyGroupId());

            long agendaId = ParamUtil.getLong(request, "agendaId");

            // Validation
            boolean isValid = validate(request);
            if (!isValid) {
                // Si pas valide : on reste sur la page d'édition
                PortalUtil.copyRequestParameters(request, response);

                String portletName = (String) request
                        .getAttribute(WebKeys.PORTLET_ID);
                PortletURL returnURL = PortletURLFactoryUtil.create(request,
                        portletName, td.getPlid(),
                        PortletRequest.RENDER_PHASE);
                response.setRenderParameter("tab", "");
                response.setRenderParameter("returnURL", returnURL.toString());
                response.setRenderParameter("mvcPath", "/csmap-bo-agenda-edit-thematique.jsp");
                return false;
            }

            Agenda agenda;
            if (agendaId == 0) {
                agenda = _agendaLocalService.createAgenda();
            } else {
                agenda = _agendaLocalService.getAgenda(agendaId);
            }

            // ----------------------------------------------------------------
            // -------------------- INFORMATIONS GENERALES --------------------
            // ----------------------------------------------------------------

            String title = ParamUtil.getString(request, "title");
            agenda.setTitle(title);

            String editorialTitle = ParamUtil.getString(request, "editorialTitle");
            agenda.setEditorialTitle(editorialTitle);

            String subtitle = ParamUtil.getString(request, "subtitle");
            agenda.setSubtitle(subtitle);

            Long imageId = ParamUtil.getLong(request, "imageId");
            agenda.setImageId(imageId);

            String link = ParamUtil.getString(request, "link");
            agenda.setLink(link);

            String labelLink = ParamUtil.getString(request, "labelLink");
            agenda.setLabelLink(labelLink);

            Date publicationStartDate = ParamUtil.getDate(request,
                    "publicationStartDate" , dateFormat, null);
            if(Validator.isNotNull(publicationStartDate)) {
                LocalDateTime startPublication = new Timestamp(publicationStartDate.getTime())
                        .toLocalDateTime().withHour(0).withMinute(0).withSecond(0).withNano(0);
                agenda.setPublicationStartDate(Timestamp.valueOf(startPublication));
            }else
                agenda.setPublicationStartDate(null);

            Date publicationEndDate = ParamUtil.getDate(request,
                    "publicationEndDate" , dateFormat, null);
            if(Validator.isNotNull(publicationEndDate)) {
                LocalDateTime endPublication = new Timestamp(publicationEndDate.getTime())
                        .toLocalDateTime().withHour(23).withMinute(59).withSecond(59).withNano(999999999);
                agenda.setPublicationEndDate(Timestamp.valueOf(endPublication));
            }else
                agenda.setPublicationEndDate(null);

            StringBuilder campaigns = new StringBuilder();
            long[] campaignsIds = ParamUtil.getLongValues(request, "campaigns");
            for (long campaignsId : campaignsIds) {
                if (campaigns.toString().equals("")) {
                    campaigns = new StringBuilder(String.valueOf(campaignsId));
                } else {
                    campaigns.append(",").append(campaignsId);
                }
            }
            agenda.setCampaignsIds(campaigns.toString());

            StringBuilder agendaThemes = new StringBuilder();
            long[] agendaThemesIds = ParamUtil.getLongValues(request, "Vocabulary_" + getThemeVocabularyId());
            for (long agendaThemesId : agendaThemesIds) {
                if (agendaThemes.toString().equals("")) {
                    agendaThemes = new StringBuilder(String.valueOf(agendaThemesId));
                } else {
                    agendaThemes.append(",").append(agendaThemesId);
                }
            }
            agenda.setThemesIds(agendaThemes.toString());

            StringBuilder agendaTypes = new StringBuilder();
            long[] agendaTypesIds = ParamUtil.getLongValues(request, "Vocabulary_" + getTypeVocabularyId());
            for (long agendaTypesId : agendaTypesIds) {
                if (agendaTypes.toString().equals("")) {
                    agendaTypes = new StringBuilder(String.valueOf(agendaTypesId));
                } else {
                    agendaTypes.append(",").append(agendaTypesId);
                }
            }
            agenda.setTypesIds(agendaTypes.toString());

            StringBuilder agendaTerritories = new StringBuilder();
            long[] agendaTerritoriesIds = ParamUtil.getLongValues(request, "Vocabulary_" + getTerritoryVocabularyId());
            for (long agendaTerritoriesId : agendaTerritoriesIds) {
                if (agendaTerritories.toString().equals("")) {
                    agendaTerritories = new StringBuilder(String.valueOf(agendaTerritoriesId));
                } else {
                    agendaTerritories.append(",").append(agendaTerritoriesId);
                }
            }
            agenda.setTerritoriesIds(agendaTerritories.toString());

            String tags = ParamUtil.getString(request,
                    "tags");
            agenda.setTags(tags);

            agenda.setIsPrincipal(false);

            // Gestion de l'activation
            Boolean isActive = ParamUtil.getBoolean(request, "isActive");
            agenda.setIsActive(isActive);
            if(isActive){
                // désactive l'agenda activé
                Agenda agendaActif = _agendaLocalService.getAgendaThematiqueActif();
                if(Validator.isNotNull(agendaActif) && agendaActif.getAgendaId() != agenda.getAgendaId()){
                    agendaActif.setIsActive(false);
                    _agendaLocalService.updateAgenda(agendaActif);
                }else {
                    agenda.setIsActive(true);
                }
            }
            _agendaLocalService.updateAgenda(agenda);

            // Régénération du cache des agendas pour CSMap
            _csmapCacheLocalService.generateCsmapCache(CodeCacheEnum.AGENDA.getId());

        } catch (PortalException e) {
            _log.error(e.getMessage(), e);
        }

        return true;
    }

    /**
     * Validation des champs obligatoires
     */
    private boolean validate(ActionRequest request) {
        boolean isValid = true;

        // Titre
        if (Validator.isNull(ParamUtil.getString(request, "title"))) {
            SessionErrors.add(request, "title-error");
            isValid = false;
        }
        // Titre editorial
        if (Validator.isNull(ParamUtil.getString(request, "editorialTitle"))) {
            SessionErrors.add(request, "editorial-title-error");
            isValid = false;
        }

        Date publicationStartDate = ParamUtil.getDate(request,
                "publicationStartDate" , dateFormat, null);
        Date publicationEndDate = ParamUtil.getDate(request,
                "publicationEndDate" , dateFormat, null);

        if(Validator.isNotNull(publicationEndDate) && Validator.isNotNull(publicationStartDate)
        && publicationEndDate.compareTo(publicationStartDate) < 0 ) {
            SessionErrors.add(request, "publication-date-error");
            isValid = false;
        }
        return isValid;
    }

    private AgendaLocalService _agendaLocalService;
    private CsmapCacheLocalService _csmapCacheLocalService;

    @Reference(unbind = "-")
    protected void setAgendaExportLocalService(AgendaLocalService agendaLocalService) {
        _agendaLocalService = agendaLocalService;
    }

    @Reference(unbind = "-")
    protected void setCsmapCacheLocalService(CsmapCacheLocalService csmapCacheLocalService) {
        _csmapCacheLocalService = csmapCacheLocalService;
    }

    private String getThemeVocabularyId(){
        try {
            AssetVocabulary theme = AssetVocabularyHelper.getGlobalVocabulary(VocabularyNames.EVENT_THEME);
            if(Validator.isNotNull(theme))
                return String.valueOf(theme.getVocabularyId());
        } catch (PortalException e) {
            _log.error(e.getMessage() + " : " + VocabularyNames.EVENT_THEME);
        }
        return null;
    }

    private String getTypeVocabularyId(){
        try {
            AssetVocabulary type = AssetVocabularyHelper.getGlobalVocabulary(VocabularyNames.EVENT_TYPE);
            if(Validator.isNotNull(type))
                return String.valueOf(type.getVocabularyId());
        } catch (PortalException e) {
            _log.error(e.getMessage() + " : " + VocabularyNames.EVENT_TYPE);
        }
        return null;
    }

    private String getTerritoryVocabularyId(){
        try {
            AssetVocabulary type = AssetVocabularyHelper.getGlobalVocabulary(VocabularyNames.TERRITORY);
            if(Validator.isNotNull(type))
                return String.valueOf(type.getVocabularyId());
        } catch (PortalException e) {
            _log.error(e.getMessage() + " : " + VocabularyNames.TERRITORY);
        }
        return null;
    }

    private static final Log _log = LogFactoryUtil.getLog(SaveCsmapAgendaThematiqueActionCommand.class.getName());
    private DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
}

