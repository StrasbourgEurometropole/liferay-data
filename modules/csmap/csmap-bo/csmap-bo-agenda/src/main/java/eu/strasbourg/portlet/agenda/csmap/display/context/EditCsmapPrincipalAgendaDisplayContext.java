package eu.strasbourg.portlet.agenda.csmap.display.context;

import com.liferay.asset.kernel.model.AssetTag;
import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.asset.kernel.service.AssetTagLocalServiceUtil;
import com.liferay.portal.kernel.util.Validator;
import eu.strasbourg.service.agenda.model.Campaign;
import eu.strasbourg.service.agenda.service.CampaignLocalServiceUtil;
import eu.strasbourg.service.csmap.model.Agenda;
import eu.strasbourg.service.csmap.service.AgendaLocalServiceUtil;
import eu.strasbourg.utils.AssetVocabularyAccessor;

import java.util.LinkedList;
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

    public Boolean agendaPrincipalVerifId(int id) {
        if(Validator.isNotNull(agendaPrincipal.getCampaignsIds()) && agendaPrincipal.getCampaignsIds().contains(String.valueOf(id))){
            return true;
        } else if(Validator.isNotNull(agendaPrincipal.getThemesIds()) && agendaPrincipal.getThemesIds().contains(String.valueOf(id))){
            return true;
        } else if(Validator.isNotNull(agendaPrincipal.getTypesIds()) && agendaPrincipal.getTypesIds().contains(String.valueOf(id))){
            return true;
        } else if(Validator.isNotNull(agendaPrincipal.getTags()) && agendaPrincipal.getTags().contains(String.valueOf(id))){
            return true;
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