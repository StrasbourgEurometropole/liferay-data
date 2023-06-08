package eu.strasbourg.portlet.agenda.csmap.display.context;

import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import eu.strasbourg.service.agenda.model.Campaign;
import eu.strasbourg.service.agenda.model.Event;
import eu.strasbourg.service.agenda.service.CampaignLocalServiceUtil;
import eu.strasbourg.service.csmap.model.Agenda;
import eu.strasbourg.service.csmap.service.AgendaLocalServiceUtil;
import eu.strasbourg.utils.AssetVocabularyAccessor;
import eu.strasbourg.utils.AssetVocabularyHelper;
import eu.strasbourg.utils.constants.VocabularyNames;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import java.util.List;

public class EditCsmapThematiqueAgendaDisplayContext {
    private Agenda agendaThematique;
    private final AssetVocabularyAccessor _assetVocabularyAccessor;
    private final RenderRequest _request;

    public EditCsmapThematiqueAgendaDisplayContext(RenderRequest request,
                                      RenderResponse response) {
        _assetVocabularyAccessor = new AssetVocabularyAccessor();
        this._request = request;
    }

    public Agenda getAgendaThematique() {
        long agendaId = ParamUtil.getLong(_request, "agendaId");
        if (agendaThematique == null && agendaId > 0) {
            agendaThematique = AgendaLocalServiceUtil.fetchAgenda(agendaId);
        }
        return agendaThematique;
    }

    public List<Campaign> getCampaings(){
        return CampaignLocalServiceUtil.getCampaigns(-1,-1);
    }

    public String getClassName(){
        return Event.class.getName();
    }

    public String getAllCategoriesAgenda(){
        String categories = "";
        if (this.agendaThematique != null) {
            // Types
            categories= this.agendaThematique.getTypesIds();

            // Thèmes
            if(!this.agendaThematique.getThemesIds().isEmpty()) {
                if(!categories.isEmpty()) {
                    categories += "," + this.agendaThematique.getThemesIds();
                } else {
                    categories = this.agendaThematique.getThemesIds();
                }
            }

            // Territoires
            if(!this.agendaThematique.getTerritoriesIds().isEmpty()) {
                if(!categories.isEmpty()) {
                    categories += "," + this.agendaThematique.getTerritoriesIds();
                } else {
                    categories = this.agendaThematique.getTerritoriesIds();
                }
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

    private static final Log _log = LogFactoryUtil.getLog(EditCsmapThematiqueAgendaDisplayContext.class.getName());
}