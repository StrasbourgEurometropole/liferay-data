package eu.strasbourg.portlet.form_send.context;

import com.liferay.dynamic.data.lists.model.DDLRecord;
import com.liferay.dynamic.data.lists.model.DDLRecordSet;
import com.liferay.dynamic.data.lists.service.DDLRecordSetLocalServiceUtil;
import com.liferay.dynamic.data.mapping.model.DDMContent;
import com.liferay.dynamic.data.mapping.model.DDMStructure;
import com.liferay.dynamic.data.mapping.service.DDMContentLocalServiceUtil;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.module.configuration.ConfigurationException;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import eu.strasbourg.portlet.form_send.configuration.FormSendConfiguration;
import eu.strasbourg.portlet.form_send.formulaire.Champ;
import eu.strasbourg.portlet.form_send.formulaire.Formulaire;
import eu.strasbourg.portlet.form_send.formulaire.Option;
import eu.strasbourg.utils.Pager;

import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

public class FormSendDisplayContext {

    private ThemeDisplay themeDisplay;
    private FormSendConfiguration configuration;
    private RenderRequest request;
    private RenderResponse response;
    private DDLRecordSet recordSet;
    private Formulaire formulaire;
    private List<DDLRecord> records;
    private SearchContainer<DDLRecord> searchContainer;
    private Map<String, String> newLibs;

    public FormSendDisplayContext(HttpServletRequest request, HttpServletResponse response) {
        this.themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        try {
            this.configuration = themeDisplay.getPortletDisplay()
                    .getPortletInstanceConfiguration(FormSendConfiguration.class);
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }
    }

    public FormSendDisplayContext(RenderRequest request, RenderResponse response) {
        this.themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        try {
            this.configuration = themeDisplay.getPortletDisplay()
                    .getPortletInstanceConfiguration(FormSendConfiguration.class);
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }
        this.request = request;
        this.response = response;
    }

    // récupère le titre
    public String getTitle() {
        String title = "";
        if(Validator.isNotNull(this.configuration)) {
            title = configuration.title();
        }

        return title;
    }

    // récupère le formulaire choisi dans la config
    public DDLRecordSet getRecordSet() {
        if(Validator.isNull(this.recordSet)) {
            String recordSetId = configuration.recordSetId();
            if(Validator.isNotNull(recordSetId))
                this.recordSet = DDLRecordSetLocalServiceUtil.fetchDDLRecordSet(Long.parseLong(recordSetId));
        }

        return this.recordSet;
    }

    // récupère la structure du formulaire choisi
    public Formulaire getForm() {
        if(Validator.isNull(this.formulaire) && Validator.isNotNull(this.getRecordSet())) {
            try {
                DDMStructure structure = this.recordSet.getDDMStructure();
                JSONArray jsonArray = JSONFactoryUtil.createJSONArray();
                if (Validator.isNotNull(structure))
                    jsonArray = JSONFactoryUtil.createJSONObject(structure.getDefinition()).getJSONArray("fields");

                this.formulaire = new Formulaire(this.recordSet.getRecordSetId(), this.recordSet.getNameMap(), jsonArray);
            } catch (PortalException e) {
                e.printStackTrace();
            }
        }

        return this.formulaire;
    }

    // récupère tous les formulaires envoyés au formulaire choisi
    public List<DDLRecord> getRecords() {
        if(Validator.isNull(this.records) && Validator.isNotNull(this.getRecordSet())) {
            this.records = this.recordSet.getRecords();
        }

        return this.records;
    }

    // récupère les valeurs d'un formulaire envoyé (liste de nom/valeur)
    public List<Map<String, String>> getRecordFields(long recordDDMStorageId, Locale locale) {
        List<Map<String, String>> recordFields = new ArrayList<Map<String, String>>();
        DDMContent content = DDMContentLocalServiceUtil.fetchDDMContent(recordDDMStorageId);
        if(Validator.isNotNull(content)){
            String jsonString = content.getData();
            if(Validator.isNotNull(jsonString)){
                try {
                    JSONArray jsonArray = JSONFactoryUtil.createJSONObject(jsonString).getJSONArray("fieldValues");
                    for (Object jsonObject : jsonArray) {
                        JSONObject json = JSONFactoryUtil.createJSONObject(jsonObject.toString());
                        Map<String, String> mapField = new HashMap<String, String>();
                        String value = "";
                        if(!json.isNull("value"))
                            value = json.getJSONObject("value").getString(locale.toString()).replaceAll("(\r\n|\n)", "<br />");
                        else{
                            // si c'est un format de texte, on récupère le text
                            if(Validator.isNotNull(this.getForm())){
                                if(Validator.isNotNull(this.formulaire.getField(json.getString("name"))) &&
                                        Validator.isNotNull(this.formulaire.getField(json.getString("name")).getType()) &&
                                        this.formulaire.getField(json.getString("name")).getType().equals("paragraph")){
                                    value = this.formulaire.getField(json.getString("name")).getText();
                                }
                            }
                        }
                        mapField.put(json.getString("name"), value);
                        recordFields.add(mapField);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        return recordFields;
    }

    // récupère les champ qui devront être affiché (choisi dans la config)
    public List<String> getFieldsToShow() {
        List<String> fieldsToShow = new ArrayList<String>();
        String fieldsSelectedString = configuration.fieldsSelected();
        if(Validator.isNotNull(fieldsSelectedString)) {
            String[] fieldsSelected = fieldsSelectedString.split(",");
            for (String fieldSelected : fieldsSelected) {
                fieldsToShow.add(fieldSelected.split("_")[1]);
            }
        }

        return fieldsToShow;
    }

    // récupère les nouveaux libellé des champs
    public Map<String, String> getNewLibelle() {
        if(this.newLibs == null){
            this.newLibs = new HashMap<String, String>();
            String[] newLibsString = configuration.newLibs().split(",");
            for (String newLib : newLibsString) {
                String[] newLibString = newLib.split("--");
                this.newLibs.put(newLibString[0], newLibString.length > 1 ? newLibString[1] : "");
            }
        }

        return this.newLibs;
    }

    // récupère le label d'un champ (récupération du nouveau libellé s'il existe
    public String getLabel(String name, Locale locale) {
        // on vérifie que le champ n'a pas un nouveau libellé
        if(Validator.isNotNull(this.getNewLibelle())){
            String libelle = this.newLibs.get("newLib_" + configuration.recordSetId() + "_" + name);
            if(Validator.isNotNull(libelle))
                return libelle;
        }
        String label = "";
        if(Validator.isNotNull(this.getForm())) {
            Champ champ = this.formulaire.getField(name);
            if (Validator.isNotNull(champ))
                label = champ.getLabel(locale);
        }

        return label.substring(0, 1).toUpperCase() + label.substring(1);
    }

    // vérifie si le select est multiple ou pas
    public Boolean isMultiple(String name) {
        Boolean isMultiple = false;
        if(Validator.isNotNull(this.getForm())) {
            Champ champ = this.formulaire.getField(name);
            if (Validator.isNotNull(champ)) {
                isMultiple = champ.isMultiple();
            }
        }

        return isMultiple;
    }

    // récupère le type du champ à afficher
    public String getFieldType(String name) {
        String fieldType = "";
        if(Validator.isNotNull(this.getForm())) {
            Champ champ = this.formulaire.getField(name);
            if (Validator.isNotNull(champ)) {
                fieldType = champ.getType();
            }
        }

        return fieldType;
    }

    // récupère les options d'1 selecteur ou d'1 checkbox
    public List<Option> getOptions(String name) {
        List<Option> options = new ArrayList<Option>();
        if(Validator.isNotNull(this.getForm())) {
            Champ champ = this.formulaire.getField(name);
            if (Validator.isNotNull(champ))
                options = champ.getOptions();
        }

        return options;
    }

    /**
     * Retourne le nombre d'entrées à afficher par page
     */
    public int getDelta() {

        int delta = 5;
        if(Validator.isNotNull(this.configuration) && Validator.isNotNull(this.configuration.nbEntries())) {
            delta =  Integer.parseInt(configuration.nbEntries());
        }
        return delta;
    }

    /**
     * Retourne le searchContainer
     */
    public SearchContainer<DDLRecord> getSearchContainer() {
        if (searchContainer == null && Validator.isNotNull(this.getRecords())) {
            Map<String, String[]> parameterMap = request.getParameterMap();
            PortletURL iteratorURL = this.response.createRenderURL();
            iteratorURL.setParameters(parameterMap);
            searchContainer = new SearchContainer<DDLRecord>(request, iteratorURL, null, "no-entries-were-found");
            searchContainer.setDelta(this.getDelta());
            searchContainer.setTotal(this.records.size());
            searchContainer.setResults(this.records);
        }
        return searchContainer;
    }

    /**
     * Retourne les résultats entre les indexes start (inclu) et end (non inclu)
     */
    public List<DDLRecord> getPaginatedResults() {
        return ListUtil.subList(this.getRecords(), this.getSearchContainer().getStart(), this.getSearchContainer().getEnd());
    }

    /**
     * Retourne le pager de la page
     */
    public Pager getPager() {
        return new Pager(this.getSearchContainer().getTotal(), (int) this.getDelta(),
                this.getSearchContainer().getCur());
    }

    /**
     * Retourne l'URL sur laquelle aller pour accéder à la Xième page
     */
    public String getURLForPage(long pageIndex) {
        PortletURL url = this.getSearchContainer().getIteratorURL();
        url.setParameter("cur", String.valueOf(pageIndex));
        String valueToReturn = url.toString();
        url.setParameter("cur", String.valueOf(this.getSearchContainer().getCur()));
        return valueToReturn;
    }
}