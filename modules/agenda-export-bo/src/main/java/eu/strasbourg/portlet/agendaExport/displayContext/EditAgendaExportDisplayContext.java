package eu.strasbourg.portlet.agendaExport.displayContext;

import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.service.WorkflowDefinitionLinkLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import eu.strasbourg.service.agenda.model.AgendaExport;
import eu.strasbourg.service.agenda.model.AgendaExportPeriod;
import eu.strasbourg.service.agenda.service.AgendaExportLocalServiceUtil;
import eu.strasbourg.utils.AssetVocabularyAccessor;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

public class EditAgendaExportDisplayContext {
	
	private final RenderRequest _request;
    private final ThemeDisplay _themeDisplay;
    private final AssetVocabularyAccessor _assetVocabularyAccessor;
    
    private AgendaExport _agendaExport;

    public EditAgendaExportDisplayContext(RenderRequest request, RenderResponse response) {
        this._request = request;
        this._themeDisplay = (ThemeDisplay) request
                .getAttribute(WebKeys.THEME_DISPLAY);
        _assetVocabularyAccessor = new AssetVocabularyAccessor();
    }

    /**
     * Retourne l'instance AgendaExport
     * @return
     */
    public AgendaExport getAgendaExport() {
        long agendaExportId = ParamUtil.getLong(_request, "agendaExportId");
        if (_agendaExport == null && agendaExportId > 0) {
        	_agendaExport = AgendaExportLocalServiceUtil.fetchAgendaExport(agendaExportId);
        }
        return _agendaExport;
    }

    /**
     * Retourne la liste des catégories sauvegardées en fonction du vocabulaire
     * @param vocabularyId
     * @return
     * @throws JSONException
     */
    public String getSavedCategoriesByVocabulary(String vocabularyId) throws JSONException {
        AgendaExport agendaExport = this.getAgendaExport();
        JSONObject vocabulaires = JSONFactoryUtil.createJSONObject(
            agendaExport.getEventCategories()
        );

        if(!vocabulaires.has(vocabularyId)) {
            return "";
        }

        return vocabulaires.get(vocabularyId).toString();
    }

    public String getDefaultPeriodIndexes() {
        if (this.getAgendaExport() != null) {
            List<AgendaExportPeriod> periods = this.getAgendaExport().getAgendaExportPeriods();
            String indexes = "0";
            for (int i = 1; i <= periods.size(); i++) {
                indexes +=  "," + i;
            }
            return indexes;
        }
        return "";
    }

    public String getDefaultIndexes(int length) {
        String indexes = "";
        for (int i = 1; i <= length; i++) {
            if (Validator.isNotNull(indexes)) {
                indexes += ",";
            }
            indexes += i;
        }
        return indexes;
    }

    public Locale[] getAvailableLocales() {
        Set<Locale> availableLocalesSet = LanguageUtil.getSupportedLocales();
        return availableLocalesSet
                .toArray(new Locale[availableLocalesSet.size()]);
    }

    /**
     * Define the language filter list
     * @return
     */
    public List<String> getLanguageList() {
        List<String> languages = new ArrayList<>();
        languages.add("Français");
        languages.add("Anglais");
        languages.add("Allemand");
        return languages;
    }

    /**
     * Define the list of vocaularies that can be used as filter
     * @return
     */
    public List<AssetVocabulary> getEventVocabularies() {
        List<AssetVocabulary> vocabularies = new ArrayList<>();
        vocabularies.add(_assetVocabularyAccessor.getEventThemes());
        vocabularies.add(_assetVocabularyAccessor.getTerritories());
        vocabularies.add(_assetVocabularyAccessor.getEventPublics());
        vocabularies.add(_assetVocabularyAccessor.getEventTypes());
        vocabularies.add(_assetVocabularyAccessor.getPlaceTypes());
        return vocabularies;
    }

    /**
     * @return True si le framework workflow est actif pour ce type d'entité
     */
    public boolean isWorkflowEnabled() {
        boolean result = WorkflowDefinitionLinkLocalServiceUtil.hasWorkflowDefinitionLink(
                _themeDisplay.getCompanyId(), _themeDisplay.getCompanyGroupId(),
                AgendaExport.class.getName());
        return result;
    }

    public boolean hasPermission(String actionId) {
        return _themeDisplay.getPermissionChecker().hasPermission(
                this._themeDisplay.getCompanyId(),
                StrasbourgPortletKeys.AGENDA_EXPORT_BO,
                StrasbourgPortletKeys.AGENDA_EXPORT_BO,
                actionId);
    }
}
