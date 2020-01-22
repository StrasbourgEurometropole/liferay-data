package eu.strasbourg.portlet.form_send.context;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.dynamic.data.lists.model.DDLRecord;
import com.liferay.dynamic.data.lists.model.DDLRecordSet;
import com.liferay.dynamic.data.lists.service.DDLRecordSetLocalServiceUtil;
import com.liferay.dynamic.data.mapping.model.DDMContent;
import com.liferay.dynamic.data.mapping.model.DDMStructure;
import com.liferay.dynamic.data.mapping.service.DDMContentLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.Criterion;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.module.configuration.ConfigurationException;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.*;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import eu.strasbourg.portlet.form_send.configuration.FormSendConfiguration;
import eu.strasbourg.portlet.form_send.formulaire.Champ;
import eu.strasbourg.portlet.form_send.formulaire.Formulaire;
import eu.strasbourg.portlet.form_send.formulaire.Option;
import eu.strasbourg.service.formSendRecordField.model.FormSendRecordField;
import eu.strasbourg.service.formSendRecordField.model.FormSendRecordFieldSignalement;
import eu.strasbourg.service.formSendRecordField.service.FormSendRecordFieldLocalServiceUtil;
import eu.strasbourg.service.formSendRecordField.service.FormSendRecordFieldSignalementLocalServiceUtil;
import eu.strasbourg.service.oidc.model.PublikUser;
import eu.strasbourg.service.oidc.service.PublikUserLocalServiceUtil;
import eu.strasbourg.utils.AssetVocabularyAccessor;
import eu.strasbourg.utils.DateHelper;
import eu.strasbourg.utils.Pager;

import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.stream.Collectors;

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

        //trie par date antéchronologique
        this.records = this.records.stream().sorted((r1, r2) -> r2.getCreateDate()
                .compareTo(r1.getCreateDate()))
                .collect(Collectors.toList());

        return this.records;
    }

    // récupère les réponses à chaques questions d'un formulaire envoyé (liste de instanceId/name/valeur)
    public List<String[]> getRecordFields(long recordDDMStorageId, Locale locale) {
        List<String[]> recordFields = new ArrayList<String[]>();
        // récupère les infos du contenu du formulaire envoyé
        DDMContent content = DDMContentLocalServiceUtil.fetchDDMContent(recordDDMStorageId);
        if(Validator.isNotNull(content)){

            // récupère le contenu du formulaire envoyé
            String jsonString = content.getData();
            if(Validator.isNotNull(jsonString)){
                try {
                    // récupère les infos de tous les champs du formualaire
                    JSONArray jsonArray = JSONFactoryUtil.createJSONObject(jsonString).getJSONArray("fieldValues");
                    for (Object jsonObject : jsonArray) {

                        // récupère les infos du champs
                        // instanceId
                        // name -> nom du champs
                        // value -> saisie utilisateur (n'est pas renseigné pour les paragraphes)
                        JSONObject json = JSONFactoryUtil.createJSONObject(jsonObject.toString());
                        JSONObject jsonField = JSONFactoryUtil.createJSONObject();
                        String[] field = {json.getString("instanceId"),json.getString("name"),""};
                        if(!json.isNull("value"))
                            field[2] = json.getJSONObject("value").getString(locale.toString()).replaceAll("(\r\n|\n)", "<br />");
                        recordFields.add(field);
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

    // récupère le tip d'un champ
    public String getTip(String name, Locale locale) {
        String tip = "";
        if(Validator.isNotNull(this.getForm())) {
            Champ champ = this.formulaire.getField(name);
            if (Validator.isNotNull(champ))
                tip = champ.getTip(locale);
        }

        return tip;
    }

    // récupère le formSendRecordField (qui contient les réponses et le lien avec les signalements en lien avec la réponse à une question du formulaire)
    public FormSendRecordField getFormSendRecordField(long contentId, String instanceId) {
        FormSendRecordField formSendRecordField = null;
        List<FormSendRecordField> formSendRecordFieldList = FormSendRecordFieldLocalServiceUtil.getByContentAndInstanceId(contentId, instanceId);
        if(formSendRecordFieldList.size() > 0){
            formSendRecordField = formSendRecordFieldList.get(0);
        }

        if(Validator.isNull(formSendRecordField)){
            // si le formSendRecordField n'existe pas on le créer
            try {
                ServiceContext sc = ServiceContextFactory.getInstance(this.request);
                formSendRecordField = FormSendRecordFieldLocalServiceUtil.createFormSendRecordField(sc);
                formSendRecordField.setContentId(contentId);
                formSendRecordField.setInstanceId(instanceId);
                FormSendRecordFieldLocalServiceUtil.updateFormSendRecordField(formSendRecordField);
            } catch (PortalException e) {
                e.printStackTrace();
            }
        }

        return formSendRecordField;
    }

    //récupère l'utilisateur
    public User getUser(long userId){
        User user = null;
        try {
            user =  UserLocalServiceUtil.getUser(userId);
        } catch (PortalException e) {
            e.printStackTrace();
        }
        return user;
    }

    // Recuperation des informations de l'utilisateur
    public PublikUser getPublikUser() {

        PublikUser publikUser = null;

        LiferayPortletRequest liferayPortletRequest = PortalUtil.getLiferayPortletRequest(request);
        HttpServletRequest originalRequest = liferayPortletRequest.getHttpServletRequest();
        String publikUserId = SessionParamUtil.getString(originalRequest, "publik_internal_id");
        if(Validator.isNotNull(publikUserId))
            publikUser = PublikUserLocalServiceUtil.getByPublikUserId(publikUserId);

        return publikUser;
    }

    // Vérifie si l'utilisateur est banni
    public Boolean isUserBanned(){
        PublikUser publikUser = this.getPublikUser();
        if (Validator.isNotNull(publikUser))
            return publikUser.isBanned();
        else
            return false;
    }

    // Vérifi si l'utilisateur a signé le pact
    public Boolean hasUserSigned(){
        PublikUser publikUser = this.getPublikUser();
        if (Validator.isNotNull(publikUser))
            return Validator.isNotNull(publikUser.getPactSignature()) ? true : false;
        else
            return false;
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

    /**
     * Retourne la date de création de l'entrée du formulaire
     */
    public String getShortDate(Date date, Locale locale) {
        return DateHelper.displayShortDate(date, locale);
    }

    // récupère le nombre de réponses de la ville pour le formulaire
    public int getNbReponsesVille() {
        Criterion idCriterion = RestrictionsFactoryUtil.ne("response", "");
        DynamicQuery formSendRecordFieldQuery = FormSendRecordFieldLocalServiceUtil.dynamicQuery().add(idCriterion);
        List<FormSendRecordField> listFormSendRecordField = FormSendRecordFieldLocalServiceUtil.dynamicQuery(formSendRecordFieldQuery);
        return listFormSendRecordField.size();
    }

    // Récupération des catégories
    public List<AssetCategory> getCategories(){
        AssetVocabularyAccessor assetVocabularyAccessor = new AssetVocabularyAccessor();
        long groupId = themeDisplay.getLayout().getGroupId();
        return assetVocabularyAccessor.getCategoriesSignalement(groupId).getCategories();
    }

    // Vérifi si la réponse doit être affichée
    public Boolean isToShow(long formSendRecordFieldId){
        Boolean isToShow = true;
        //Récupère les signalements s'il y en a
        List<FormSendRecordFieldSignalement> signalements = FormSendRecordFieldSignalementLocalServiceUtil.findByFormSendRecordFieldId(formSendRecordFieldId);
        //Ne garde que ceux qui n'ont pas de signalements approuvé
        if(signalements.size() > 0 && signalements.stream().anyMatch(s -> s.getStatus() == WorkflowConstants.STATUS_APPROVED)){
            isToShow = false;
        }
        return isToShow;
    }

    public String getTexteModeration(){
        String message = "";
        if(Validator.isNotNull(this.configuration) && Validator.isNotNull(configuration.message())) {
            message = configuration.message();
        }else{
            message = "La r&eacute;ponse a &eacute;t&eacute; mod&eacute;r&eacute;e.";
        }

        return message;
    }
}