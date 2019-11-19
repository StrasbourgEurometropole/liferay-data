package eu.strasbourg.portlet.form_send.context;

import com.liferay.dynamic.data.lists.model.DDLRecord;
import com.liferay.dynamic.data.lists.model.DDLRecordSet;
import com.liferay.dynamic.data.lists.service.DDLRecordLocalServiceUtil;
import com.liferay.dynamic.data.lists.service.DDLRecordSetLocalServiceUtil;
import com.liferay.dynamic.data.mapping.model.DDMContent;
import com.liferay.dynamic.data.mapping.model.DDMStructure;
import com.liferay.dynamic.data.mapping.service.DDMContentLocalServiceUtil;
import com.liferay.portal.kernel.dao.search.EmptyOnClickRowChecker;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import eu.strasbourg.utils.display.context.ViewListBaseDisplayContext;

import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import java.util.*;
import java.util.stream.Collectors;

public class ViewFormSendDisplayContext extends ViewListBaseDisplayContext<DDLRecord>{

    private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());

    private final RenderRequest _request;
    private RenderResponse _response;
    private List<DDLRecord> _allFormSends;
    private List<DDLRecord> _formSends;
    private Map<String, String[]> _texteAreaFields;

    public ViewFormSendDisplayContext(RenderRequest request, RenderResponse response) {
        super(DDLRecord.class, request, response);
        this._request = request;
        this._response = response;
    }

    // Récupère les formulaires envoyés du formulaire choisi
    public List<DDLRecord> getAllFormSends() {
        if (this._allFormSends == null) {
            List<DDLRecord> recordList = DDLRecordLocalServiceUtil.getDDLRecords(-1,-1);

            // ne garde que les formulaires envoyé du formulaire choisi
            long recordSetId = ParamUtil.getLong(_request,"recordSetId");
            recordList = recordList.stream().filter(r -> r.getRecordSetId() == recordSetId).collect(Collectors.toList());

            //effectue le tri
            recordList.sort((r1, r2) -> r1.getModifiedDate().compareTo(r2.getModifiedDate()));
            if("desc".equals(this.getOrderByType()))
                Collections.reverse(recordList);

            this._allFormSends = recordList;
        }
        return this._allFormSends;
    }

    public List<DDLRecord> getFormSends() {

        //TODO pour la pagination
//        if (this._formSends == null) {
//            List<DDLRecord> recordList = DDLRecordLocalServiceUtil.getDDLRecords(-1,-1);
//
//            // ne garde que les formulaires envoyé du formulaire choisi
//            long recordSetId = ParamUtil.getLong(_request,"recordSetId");
//            recordList = recordList.stream().filter(r -> r.getRecordSetId() == recordSetId).collect(Collectors.toList());
//
//            //effectue le tri
//            recordList.sort((r1, r2) -> r1.getModifiedDate().compareTo(r2.getModifiedDate()));
//            if("desc".equals(this.getOrderByType()))
//                Collections.reverse(recordList);
//
//            this._formSends = recordList.subList(
//                    this._searchContainer.getStart()>this._allFormSends.size()?this._allFormSends.size():this._searchContainer.getStart(),
//                    this._searchContainer.getEnd()>this._allFormSends.size()?this._allFormSends.size():this._searchContainer.getEnd());
//        }
//        return this._formSends;

        return this.getAllFormSends();
    }

    // récupère les valeurs d'un formulaire envoyé (nom du champ, valeur du champ)
    public List<String[]> getRecordFields(long recordDDMStorageId, Locale locale) {
        List<String[]> recordFields = new ArrayList<String[]>();
        // récupère tous les champs qui devront être affichés
        Map<String, String[]> texteFields = getTexteFields();

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
                        JSONObject json = JSONFactoryUtil.createJSONObject(jsonObject.toString());
                        // on ne garde que les type text
                        if(Validator.isNotNull(texteFields.get(json.getString("name")))) {
                            // remplace la réponse "" par la réponse réelle
                            texteFields.get(json.getString("name"))[1] = json.getJSONObject("value").getString(locale.toString()).replaceAll("(\r\n|\n)", "<br />");
                        }
                    }

                    // transform la map en list
                    if(Validator.isNotNull(texteFields)) {
                        recordFields = new ArrayList<String[]>(texteFields.values());
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        return recordFields;
    }

    /**
     * Retourne le searchContainer
     */
    public SearchContainer<DDLRecord> getSearchContainer() {
        if (this._searchContainer == null && Validator.isNotNull(this.getAllFormSends())) {
            PortletURL iteratorURL = this._response.createRenderURL();
            iteratorURL.setParameter("tab", ParamUtil.getString(this._request, "tab"));
            iteratorURL.setParameter("orderByCol", this.getOrderByCol());
            iteratorURL.setParameter("orderByType", this.getOrderByType());
            iteratorURL.setParameter("keywords", this.getKeywords());

            this._searchContainer = new SearchContainer<DDLRecord>(this._request,
                    iteratorURL, null, "no-entries-were-found");

            this._searchContainer.setEmptyResultsMessageCssClass(
                    "taglib-empty-result-message-header-has-plus-btn");
            this._searchContainer
                    .setRowChecker(new EmptyOnClickRowChecker(this._response));
            this._searchContainer.setOrderByColParam("orderByCol");
            this._searchContainer.setOrderByTypeParam("orderByType");
            this._searchContainer.setResults(this._allFormSends);
            this._searchContainer.setTotal(this._allFormSends.size());
        }
        return this._searchContainer;
    }

    // récupère les champ text qui devront être affiché (nom, {valeur,reponse:""})
    private Map<String, String[]> getTexteFields() {

        if(this._texteAreaFields == null) {
            Map<String, String[]> texteAreaFields = new LinkedHashMap<String, String[]>();
            //récupère le formulaire
            long recordSetId = ParamUtil.getLong(_request, "recordSetId");
            DDLRecordSet recordSet = DDLRecordSetLocalServiceUtil.fetchDDLRecordSet(recordSetId);
            if (Validator.isNotNull(recordSet)) {
                // récupère la structure du formulaire
                try {
                    DDMStructure structure = recordSet.getDDMStructure();
                    JSONArray jsonArray = JSONFactoryUtil.createJSONArray();
                    if (Validator.isNotNull(structure))
                        jsonArray = JSONFactoryUtil.createJSONObject(structure.getDefinition()).getJSONArray("fields");

                    for (Object json : jsonArray) {
                        // récupère le type de champs
                        JSONObject jsonField = JSONFactoryUtil.createJSONObject(json.toString());
                        String type = jsonField.getString("type");

                        // ne garde que les text
                        if (Validator.isNotNull(type) && type.equals("text")) {
                            texteAreaFields.put(jsonField.getString("name"),
                                    new String[]{jsonField.getJSONObject("label").getString(Locale.FRANCE.toString()), ""});
                        }
                    }
                } catch (PortalException e) {
                    e.printStackTrace();
                }
            }

            this._texteAreaFields = texteAreaFields;
        }else{
            // initialise toutes les réponses à ""
            for (String[] value : _texteAreaFields.values()){
                value[1] = "";
            }
        }
        return this._texteAreaFields;
    }
}
