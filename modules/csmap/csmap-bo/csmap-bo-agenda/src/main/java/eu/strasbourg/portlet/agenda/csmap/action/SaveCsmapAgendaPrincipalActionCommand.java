package eu.strasbourg.portlet.agenda.csmap.action;

import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import eu.strasbourg.service.csmap.constants.CodeCacheEnum;
import eu.strasbourg.service.csmap.model.Agenda;
import eu.strasbourg.service.csmap.service.AgendaLocalService;
import eu.strasbourg.service.csmap.service.CsmapCacheLocalService;
import eu.strasbourg.utils.AssetVocabularyHelper;
import eu.strasbourg.utils.PortletHelper;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import eu.strasbourg.utils.constants.VocabularyNames;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

@Component(
        immediate = true,
        property = {"javax.portlet.name=" + StrasbourgPortletKeys.CSMAP_BO_AGENDA,
                "mvc.command.name=saveAgendaPrincipal"},
        service = MVCActionCommand.class)
public class SaveCsmapAgendaPrincipalActionCommand extends BaseMVCActionCommand {

    @Override
    protected void doProcessAction(ActionRequest request, ActionResponse response) {
        long agendaPrincipalId = ParamUtil.getLong(request, "agendaId");
        Agenda agenda;
        if (agendaPrincipalId != 0) {
            try {
                agenda = _agendaLocalService.getAgenda(agendaPrincipalId);
            } catch (Exception e) {
                agenda = _agendaLocalService.createAgenda(agendaPrincipalId);
            }
        } else {
            agenda = _agendaLocalService.createAgenda();
        }

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

        agenda.setIsPrincipal(true);
        agenda.setIsActive(true);
        agenda.setImageId((long) 0);

        _agendaLocalService.updateAgenda(agenda);

        // Régénération du cache des agendas pour CSMap
        _csmapCacheLocalService.generateCsmapCache(CodeCacheEnum.AGENDA.getId());
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

    private static final Log _log = LogFactoryUtil.getLog(SaveCsmapAgendaPrincipalActionCommand.class.getName());
}

