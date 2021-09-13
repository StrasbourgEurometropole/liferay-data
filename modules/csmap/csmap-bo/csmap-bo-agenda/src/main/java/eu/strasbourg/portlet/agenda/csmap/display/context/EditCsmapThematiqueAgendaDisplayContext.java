package eu.strasbourg.portlet.agenda.csmap.display.context;

import com.liferay.asset.kernel.model.AssetTag;
import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.asset.kernel.service.AssetTagLocalServiceUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import eu.strasbourg.service.agenda.model.Campaign;
import eu.strasbourg.service.agenda.service.CampaignLocalServiceUtil;
import eu.strasbourg.service.csmap.model.Agenda;
import eu.strasbourg.service.csmap.service.AgendaLocalServiceUtil;
import eu.strasbourg.utils.AssetVocabularyAccessor;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import java.util.LinkedList;
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

    public List<AssetVocabulary> getAgendaThemes() {
        List<AssetVocabulary> vocabularies = new LinkedList<>();
        vocabularies.add(_assetVocabularyAccessor.getEventThemes());
        return vocabularies;
    }

    public List<AssetVocabulary> getAgendaTypes() {
        List<AssetVocabulary> vocabularies = new LinkedList<>();
        vocabularies.add(_assetVocabularyAccessor.getEventTypes());
        return vocabularies;
    }

    public Boolean agendaThematiqueVerifId(int id) {
        if(Validator.isNotNull(agendaThematique)) {
            if (Validator.isNotNull(agendaThematique.getCampaignsIds()) && agendaThematique.getCampaignsIds().contains(String.valueOf(id))) {
                return true;
            } else if (Validator.isNotNull(agendaThematique.getThemesIds()) && agendaThematique.getThemesIds().contains(String.valueOf(id))) {
                return true;
            } else if (Validator.isNotNull(agendaThematique.getTypesIds()) && agendaThematique.getTypesIds().contains(String.valueOf(id))) {
                return true;
            } else if (Validator.isNotNull(agendaThematique.getTags()) && agendaThematique.getTags().contains(String.valueOf(id))) {
                return true;
            }
        }
        return false;
    }

    public List<AssetTag> getAssetTags(){
        return AssetTagLocalServiceUtil.getAssetTags(-1,-1);
    }

    public List<Campaign> getCampaings(){
        return CampaignLocalServiceUtil.getCampaigns(-1,-1);
    }
}