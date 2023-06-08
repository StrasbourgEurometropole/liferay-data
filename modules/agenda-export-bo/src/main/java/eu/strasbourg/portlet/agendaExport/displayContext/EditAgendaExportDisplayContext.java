package eu.strasbourg.portlet.agendaExport.displayContext;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.asset.kernel.model.AssetVocabularyModel;
import com.liferay.asset.kernel.service.AssetCategoryServiceUtil;
import com.liferay.asset.kernel.service.AssetVocabularyServiceUtil;
import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.model.DLFileEntryModel;
import com.liferay.document.library.kernel.model.DLFolder;
import com.liferay.document.library.kernel.service.DLFileEntryLocalServiceUtil;
import com.liferay.document.library.kernel.service.DLFolderLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.service.UserServiceUtil;
import com.liferay.portal.kernel.service.WorkflowDefinitionLinkLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.ResourceBundleUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import eu.strasbourg.service.agenda.model.AgendaExport;
import eu.strasbourg.service.agenda.model.AgendaExportPeriod;
import eu.strasbourg.service.agenda.service.AgendaExportLocalServiceUtil;
import eu.strasbourg.service.agenda.service.AgendaExportPeriodLocalServiceUtil;
import eu.strasbourg.utils.AssetVocabularyAccessor;
import eu.strasbourg.utils.AssetVocabularyHelper;
import eu.strasbourg.utils.StrasbourgPropsUtil;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import org.apache.commons.lang3.ArrayUtils;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import java.util.*;

public class EditAgendaExportDisplayContext {

    private static final String DOCUMENT_LIBRARY_FOLDER = "Template Export Agenda";

    private ResourceBundle bundle = ResourceBundleUtil.getBundle("content.Language",
            this.getClass().getClassLoader());

	private final RenderRequest _request;
    private final ThemeDisplay _themeDisplay;
    private final AssetVocabularyAccessor _assetVocabularyAccessor;

    //View variables
    private AgendaExport _agendaExport;
    private Boolean _toExport = false;

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

    public void setToExport(String export) {

        if(export.equals("true")) {
            _toExport = true;
        } else {
            _toExport = false;
        }
    }

    /**
     * Retire l'élément mvcPath s'il est défini
     * Correction temporaire, il faudrait trouver pourquoi Liferay rajoute le mvcPath à l'url de la resourceCommand
     * @param resourceURL
     * @return
     */
    public String cleanResourceURL(String resourceURL) {

        if(resourceURL.contains("mvcPath")) {

            String[] splitedResourceURL = resourceURL.split("&");
            int indexToRemove = -1;
            for(int i = 0; i < splitedResourceURL.length; i++) {
                if(splitedResourceURL[i].contains("mvcPath")) {
                    indexToRemove = i;
                }
            }

            if(indexToRemove != -1) {
                splitedResourceURL = ArrayUtils.remove(splitedResourceURL, indexToRemove);
            }

            resourceURL = String.join("&", splitedResourceURL);
        }

        return resourceURL;
    }

    public Boolean getToExport() {
        return _toExport;
    }

    public List<AgendaExportPeriod> getOrCreateAgendaExportPeriods() throws PortalException {

        List<AgendaExportPeriod> periods = new ArrayList<>();
        if(_agendaExport == null || _agendaExport.getAgendaExportPeriods().isEmpty()) {
            periods.add(AgendaExportPeriodLocalServiceUtil.createAgendaExportPeriod());
        } else {
            periods = _agendaExport.getAgendaExportPeriods();
        }

        return periods;
    }

    /**
     * Retourne la liste des catégories sauvegardées en fonction du vocabulaire
     * @param vocabularyId
     * @return
     * @throws JSONException
     */
    public String getSavedCategoriesByVocabulary(String vocabularyId) throws JSONException {
        AgendaExport agendaExport = this.getAgendaExport();
        if(agendaExport == null) { return ""; }
        String categoriesString = agendaExport.getEventCategories();
        if(categoriesString == null) { return ""; }
        JSONObject vocabulaires = JSONFactoryUtil.createJSONObject(categoriesString);
        if(!vocabulaires.has(vocabularyId)) { return ""; }

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
        languages.add(LanguageUtil.get(bundle, "eu.agenda.export.language.fr"));
        languages.add(LanguageUtil.get(bundle, "eu.agenda.export.language.en"));
        languages.add(LanguageUtil.get(bundle, "eu.agenda.export.language.de"));
        return languages;
    }

    /**
     * Define the language filter list
     * @return
     */
    public List<String> getFormatExportList() {
        List<String> languages = new ArrayList<>();

        languages.add(LanguageUtil.get(bundle, "eu.agenda.export.format.word"));
        languages.add(LanguageUtil.get(bundle, "eu.agenda.export.format.json"));
        return languages;
    }

    /**
     * List that define available level of aggregations
     * @return
     */
    public Map<String, String> getAggregationLevel() {
        Map<String, String> orders = new LinkedHashMap<>();
        orders.put("0", LanguageUtil.get(bundle, "eu.agenda.export.order.none"));
        orders.put("1", LanguageUtil.get(bundle, "eu.agenda.export.order.group"));
        orders.put("2", LanguageUtil.get(bundle, "eu.agenda.export.order.subGroup"));
        return orders;
    }

    /**
     * Define aggregation types
     * Currently DAY / MONTH / VOCABULARY / CATEGORY
     * @return
     */
    public Map<String, String> getAggregationTypes() {
        Map<String, String> types = new LinkedHashMap<>();
        types.put("", LanguageUtil.get(bundle, "eu.agenda.export.aggregation.value.none"));
        types.put("DAY", LanguageUtil.get(bundle, "eu.agenda.export.aggregation.value.day"));
        types.put("MONTH", LanguageUtil.get(bundle, "eu.agenda.export.aggregation.value.month"));
        types.put("VOCABULARY", LanguageUtil.get(bundle, "eu.agenda.export.aggregation.value.vocabulary"));
        types.put("CATEGORY", LanguageUtil.get(bundle, "eu.agenda.export.aggregation.value.category"));
        return types;
    }

    public String getAggregationDepth() throws JSONException {

        if(_agendaExport == null) { return "0"; }
        String aggregationsString = _agendaExport.getAggregations();
        if(aggregationsString == null) { return "0"; }

        JSONObject aggregations = JSONFactoryUtil.createJSONObject(aggregationsString);
        Object level = aggregations.get("level");

        if(level != null) {
            return level.toString();
        }

        return "0";
    }

    public String getAggregationSavedValue(String section, String type) throws JSONException {

        if(_agendaExport == null) { return ""; }
        String aggregationsString = _agendaExport.getAggregations();
        if(aggregationsString == null) { return ""; }

        JSONObject aggregations = JSONFactoryUtil.createJSONObject(aggregationsString);
        JSONObject sectionObject = null;

        if(section.toUpperCase().equals("FIRST") || section.toUpperCase().equals("SECOND")) {
            sectionObject = aggregations.getJSONObject(section);
        } else {
            return "";
        }

        //TODO ENUM !
        if(
            type.toUpperCase().equals("TYPE") ||
            type.toUpperCase().equals("VOCABULARY") ||
            type.toUpperCase().equals("CATEGORY") ||
            type.toUpperCase().equals("CATEGORYFILTER")
        ) {
            Object value = sectionObject.get(type.toLowerCase());
            if(value != null) {
                return value.toString();
            }
        }

        return null;
    }

    public String getAggregationCategoryFilter(String section) throws JSONException {

        String result = this.getAggregationSavedValue(section, "CATEGORYFILTER");

        if(result == null) {
            return "true";
        }

        return result;
    }

    public String getAggregationCategoryName(String section) throws JSONException {

        String id = getAggregationSavedValue(section, "CATEGORY");
        if(id == null || id.equals("")) {
            return "";
        }

        AssetCategory category = null;

        try {
            Long categoryId = Long.parseLong(id);
            category = AssetCategoryServiceUtil.getCategory(categoryId);
        } catch (Exception e) {
            _log.error(e.getMessage() + " : " + id, e);
        }

        if(category == null) {
            return "";
        }

        return category.getName();
    }

    public Map<Long, String> getSavedEventCategories(String section) throws JSONException {

        Map<Long, String> categories = new LinkedHashMap<>();
        Locale locale = _themeDisplay.getLocale();
        String id = getAggregationSavedValue(section, "VOCABULARY");
        if(id == null || id.equals("")) {
            return categories;
        }

        AssetVocabulary vocabulary = null;

        try {
            Long vocabularyId = Long.parseLong(id);
            vocabulary = AssetVocabularyServiceUtil.getVocabulary(vocabularyId);
        } catch (Exception e) {
            _log.error(e.getMessage() + " : " + id, e);
        }

        if(vocabulary == null) {
            return categories;
        }

        //load parent categories
        List<AssetCategory> parentCategories = AssetVocabularyHelper.getParentCategory(vocabulary.getCategories());
        parentCategories.sort(
            Comparator.comparing(
                AssetCategory::getName,
                String.CASE_INSENSITIVE_ORDER
            )
        );
        for(AssetCategory category : parentCategories) {
            categories.put(category.getCategoryId(), category.getTitle(locale));
        }
        return categories;
    }

    /**
     * Renvoit la liste des templates disponibles
     * @return
     */
    public Map<Long, String> getTemplateList() {

        Map<Long, String> files = new LinkedHashMap<>();
        Long groupId = _themeDisplay.getCompanyGroupId();
        DLFolder folder;
        List<DLFileEntry> fileEntries = new ArrayList<>();

        try {
            folder = DLFolderLocalServiceUtil.getFolder(groupId, 0, DOCUMENT_LIBRARY_FOLDER);
            fileEntries = new ArrayList<>(DLFileEntryLocalServiceUtil.getFileEntries(groupId, folder.getFolderId()));
            fileEntries.sort(Comparator.comparing(DLFileEntryModel::getFileName, String::compareToIgnoreCase));

        } catch (PortalException e) {
            _log.error(e.getMessage() + " : groupId -> " + groupId + " doc-lib-folder -> " + DOCUMENT_LIBRARY_FOLDER);
        }

        //Default value
        if(fileEntries.isEmpty() || !this.isTemplateInList(fileEntries)) {
            files.put(
                new Long(0), LanguageUtil.get(bundle, "eu.agenda.export.none")
            );
            if(_agendaExport != null) {
                _agendaExport.setTemplateId(0);
            }
        }

        //Fill map
        for(DLFileEntry file : fileEntries) {
            if (!file.isInTrash()) {
                files.put(file.getFileEntryId(), file.getFileName().replace(".docx", ""));
            }
        }

        return files;
    }

    /**
     * Vérifie que le template sauvegardé dans _exportAgenda est bien présent dans la liste
     * @return boolean
     */
    private boolean isTemplateInList(List<DLFileEntry> fileEntries) {

        if(fileEntries == null || _agendaExport == null) {
            return false;
        }

        for(DLFileEntry fileEntry : fileEntries) {
            if(!fileEntry.isInTrash() && _agendaExport.getTemplateId() == fileEntry.getFileEntryId()) {
                return true;
            }
        }

        return false;
    }

    /**
     * Define the list of vocabularies that can be used as filter
     * @return
     */
    public List<AssetVocabulary> getEventVocabularies() {
        List<AssetVocabulary> vocabularies = new LinkedList<>();
        vocabularies.add(_assetVocabularyAccessor.getEventThemes());
        vocabularies.add(_assetVocabularyAccessor.getTerritories());
        vocabularies.add(_assetVocabularyAccessor.getEventPublics());
        vocabularies.add(_assetVocabularyAccessor.getEventTypes());
        vocabularies.add(_assetVocabularyAccessor.getPlaceTypes());
        vocabularies.sort(Comparator.comparing(AssetVocabularyModel::getName));
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

    public boolean canEditAdminContent(Long agendaExportId) throws PortalException {

        if(agendaExportId == null) {
            return true;
        }

        AgendaExport agendaExport = AgendaExportLocalServiceUtil.getAgendaExport(agendaExportId);
        boolean createdByAdmin = false;
        if(agendaExport != null) {
            User user = UserServiceUtil.getUserById(agendaExport.getUserId());
            if(user != null) {
                createdByAdmin = isAdministrator(user);
            }
        }

        //Si le user qui a créé l'entité est un admin, on doit vérifier que l'utilisateur courant a les droits de modifier cette entité
        //lui aussi est un admin
        if(createdByAdmin) {
            return _themeDisplay.getPermissionChecker().isOmniadmin();
        }

        return true;
    }

    public boolean isAdministrator(User user){
        boolean isAdministrator = false;
        Role adminRole = RoleLocalServiceUtil.fetchRole(_themeDisplay.getCompanyId(), "Administrator");
        isAdministrator = user.getRoles().contains(adminRole);
        return isAdministrator;
    }

    private static final Log _log = LogFactoryUtil.getLog(EditAgendaExportDisplayContext.class.getName());

}
