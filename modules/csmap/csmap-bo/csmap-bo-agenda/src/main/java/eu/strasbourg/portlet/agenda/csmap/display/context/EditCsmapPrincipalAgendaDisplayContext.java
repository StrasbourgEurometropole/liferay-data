package eu.strasbourg.portlet.agenda.csmap.display.context;

import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;
import eu.strasbourg.service.agenda.model.Campaign;
import eu.strasbourg.service.agenda.model.Event;
import eu.strasbourg.service.agenda.service.CampaignLocalServiceUtil;
import eu.strasbourg.service.csmap.model.Agenda;
import eu.strasbourg.service.csmap.service.AgendaLocalServiceUtil;
import eu.strasbourg.utils.AssetVocabularyAccessor;
import eu.strasbourg.utils.AssetVocabularyHelper;
import eu.strasbourg.utils.constants.VocabularyNames;

import java.util.List;

public class EditCsmapPrincipalAgendaDisplayContext {
    private Agenda agendaPrincipal;
    private final AssetVocabularyAccessor _assetVocabularyAccessor;

    public EditCsmapPrincipalAgendaDisplayContext() {
        _assetVocabularyAccessor = new AssetVocabularyAccessor();
    }

    public Agenda getAgendaPrincipal() {
        agendaPrincipal = AgendaLocalServiceUtil.getAgendaPrincipal();
        if(Validator.isNull(agendaPrincipal)){
            agendaPrincipal = AgendaLocalServiceUtil.createAgenda();
        }
        return agendaPrincipal;
    }

    public List<Campaign> getCampaings(){
        return CampaignLocalServiceUtil.getCampaigns(-1,-1);
    }

    public String getClassName(){
        return Event.class.getName();
    }

    public String getAllCategoriesAgenda(){
        // Types
        String categories = this.agendaPrincipal.getTypesIds();

        // Thèmes
        if(!this.agendaPrincipal.getThemesIds().isEmpty()) {
            if(!categories.isEmpty()) {
                categories += "," + this.agendaPrincipal.getThemesIds();
            } else {
                categories = this.agendaPrincipal.getThemesIds();
            }
        }

        // Territoires
        if(!this.agendaPrincipal.getTerritoriesIds().isEmpty()) {
            if(!categories.isEmpty()) {
                categories += "," + this.agendaPrincipal.getTerritoriesIds();
            } else {
                categories = this.agendaPrincipal.getTerritoriesIds();
            }
        }

        return categories;
    }

    public String getThemeVocabularyId(){
        try {
            AssetVocabulary theme = AssetVocabularyHelper.getGlobalVocabulary(VocabularyNames.EVENT_THEME);
            if(Validator.isNotNull(theme))
                return String.valueOf(theme.getVocabularyId());
        } catch (PortalException e) {
            _log.error(e.getMessage());
        }
        return null;
    }

    public String getTypeVocabularyId(){
        try {
            AssetVocabulary type = AssetVocabularyHelper.getGlobalVocabulary(VocabularyNames.EVENT_TYPE);
            if(Validator.isNotNull(type))
                return String.valueOf(type.getVocabularyId());
        } catch (PortalException e) {
            _log.error(e.getMessage());
        }
        return null;
    }

    public String getTerritoryVocabularyId(){
        try {
            AssetVocabulary territory = AssetVocabularyHelper.getGlobalVocabulary(VocabularyNames.TERRITORY);
            if(Validator.isNotNull(territory))
                return String.valueOf(territory.getVocabularyId());
        } catch (PortalException e) {
            _log.error(e.getMessage());
        }
        return null;
    }

    private static final Log _log = LogFactoryUtil.getLog(EditCsmapPrincipalAgendaDisplayContext.class.getName());

}