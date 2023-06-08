package eu.strasbourg.portlet.form_send.context;

import com.liferay.dynamic.data.mapping.model.DDMContent;
import com.liferay.dynamic.data.mapping.model.DDMFormInstanceRecord;
import com.liferay.dynamic.data.mapping.service.DDMContentLocalServiceUtil;
import com.liferay.dynamic.data.mapping.service.DDMFormInstanceRecordLocalServiceUtil;
import com.liferay.portal.kernel.dao.search.EmptyOnClickRowChecker;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import eu.strasbourg.service.formSendRecordField.model.FormSendRecordField;
import eu.strasbourg.service.formSendRecordField.model.FormSendRecordFieldSignalement;
import eu.strasbourg.service.formSendRecordField.service.FormSendRecordFieldLocalServiceUtil;
import eu.strasbourg.service.formSendRecordField.service.FormSendRecordFieldSignalementLocalServiceUtil;
import eu.strasbourg.utils.display.context.ViewListBaseDisplayContext;

import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class ViewReportingDisplayContext extends ViewListBaseDisplayContext<FormSendRecordFieldSignalement>{

    private List<FormSendRecordFieldSignalement> _allSignalements;

    public ViewReportingDisplayContext(RenderRequest request, RenderResponse response) {
        super(FormSendRecordFieldSignalement.class, request, response);
        this._themeDisplay = (ThemeDisplay) request
                .getAttribute(WebKeys.THEME_DISPLAY);
    }

    public List<FormSendRecordFieldSignalement> getAllSignalements() {
        if (this._allSignalements == null) {
            List<FormSendRecordFieldSignalement> signalementList = new ArrayList<FormSendRecordFieldSignalement>();
            long formInstanceId = ParamUtil.getLong(_request,"formInstanceId");
            //récupère tous les formulaires envoyés du formulaire
            List<DDMFormInstanceRecord> recordlist = DDMFormInstanceRecordLocalServiceUtil.getDDMFormInstanceRecords(-1,-1);
            recordlist = recordlist.stream().filter(r -> r.getFormInstanceId() == formInstanceId).collect(Collectors.toList());
            if(Validator.isNotNull(recordlist)){
                for (DDMFormInstanceRecord record : recordlist) {
                    //récupère les formSendRecordFields du formulaire
                    List<FormSendRecordField> formSendRecordFieldList = FormSendRecordFieldLocalServiceUtil.getByContentId(record.getStorageId());

                    // récupère les signalements du formSendRecordField
                    for (FormSendRecordField formSendRecordField : formSendRecordFieldList) {
                        List<FormSendRecordFieldSignalement> formSendRecordFieldSignalementList = FormSendRecordFieldSignalementLocalServiceUtil.findByFormSendRecordFieldId(formSendRecordField.getFormSendRecordFieldId());

                        for (FormSendRecordFieldSignalement formSendRecordFieldSignalement : formSendRecordFieldSignalementList) {
                            signalementList.add(formSendRecordFieldSignalement);
                        }
                    }
                }
                //effectue le tri
                signalementList.sort((s1, s2) -> s1.getCreateDate().compareTo(s2.getCreateDate()));
                if("desc".equals(this.getOrderByType()))
                    Collections.reverse(signalementList);

                this._allSignalements = signalementList;
            }
        }
        return this._allSignalements;
    }

    public List<FormSendRecordFieldSignalement> getSignalements() {

        //TODO pour la pagination
//        if (this._signalements == null) {
//            List<FormSendRecordFieldSignalement> signalementList = new ArrayList<FormSendRecordFieldSignalement>();
//            long recordSetId = ParamUtil.getLong(_request,"recordSetId");
//            //récupère tous les formulaires envoyés du formulaire
//            List<DDLRecord> recordlist = DDLRecordLocalServiceUtil.getDDLRecords(-1,-1);
//            recordlist = recordlist.stream().filter(r -> r.getRecordSetId() == recordSetId).collect(Collectors.toList());
//            if(Validator.isNotNull(recordlist)){
//                // récupère les signalements des formulaires envoyés
//                for (DDLRecord record : recordlist) {
//                    //récupère les formSendRecordFields du formulaire
//                    List<FormSendRecordField> formSendRecordFieldList = FormSendRecordFieldLocalServiceUtil.getByContentId(record.getDDMStorageId());
//
//                    // récupère les signalements du formSendRecordField
//                    for (FormSendRecordField formSendRecordField : formSendRecordFieldList) {
//                        List<FormSendRecordFieldSignalement> formSendRecordFieldSignalementList = FormSendRecordFieldSignalementLocalServiceUtil.findByFormSendRecordFieldId(formSendRecordField.getFormSendRecordFieldId());
//
//                        for (FormSendRecordFieldSignalement formSendRecordFieldSignalement : formSendRecordFieldSignalementList) {
//                            signalementList.add(formSendRecordFieldSignalement);
//                        }
//                    }
//                }
//                //effectue le tri
//                signalementList.sort((s1, s2) -> s1.getCreateDate().compareTo(s2.getCreateDate()));
//                if("desc".equals(this.getOrderByType()))
//                    Collections.reverse(signalementList);
//
//                this._signalements = signalementList.subList(
//                        this._searchContainer.getStart()>this._allSignalements.size()?this._allSignalements.size():this._searchContainer.getStart(),
//                        this._searchContainer.getEnd()>this._allSignalements.size()?this._allSignalements.size():this._searchContainer.getEnd());
//            }
//        }
//        return this._signalements;

        return this._allSignalements;
    }

    /**
     * Retourne le searchContainer
     */
    public SearchContainer<FormSendRecordFieldSignalement> getSearchContainer() {
        if (this._searchContainer == null && Validator.isNotNull(this.getAllSignalements())) {
            PortletURL iteratorURL = this._response.createRenderURL();
            iteratorURL.setParameter("tab", ParamUtil.getString(this._request, "tab"));
            iteratorURL.setParameter("orderByCol", this.getOrderByCol());
            iteratorURL.setParameter("orderByType", this.getOrderByType());
            iteratorURL.setParameter("keywords", this.getKeywords());

            this._searchContainer = new SearchContainer<FormSendRecordFieldSignalement>(this._request,
                    iteratorURL, null, "no-entries-were-found");

            this._searchContainer.setEmptyResultsMessageCssClass(
                    "taglib-empty-result-message-header-has-plus-btn");
            this._searchContainer
                    .setRowChecker(new EmptyOnClickRowChecker(this._response));
            this._searchContainer.setOrderByColParam("orderByCol");
            this._searchContainer.setOrderByTypeParam("orderByType");
            this._searchContainer.setResults(this._allSignalements);
            this._searchContainer.setTotal(this._allSignalements.size());
        }
        return this._searchContainer;
    }

    // Récupération de la réponse lié au signalement
    public String getResponse(long formSendRecordFieldId){
        String response = "";
        //récupère le formSendRecordField correspondant
        FormSendRecordField formSendRecordField = FormSendRecordFieldLocalServiceUtil.fetchFormSendRecordField(formSendRecordFieldId);
        if(Validator.isNotNull(formSendRecordField)){
            //Récupère le contenu du formulaire
            DDMContent content = DDMContentLocalServiceUtil.fetchDDMContent(formSendRecordField.getContentId());
            if(Validator.isNotNull(content)) {
                String jsonString = content.getData();
                if (Validator.isNotNull(jsonString)) {
                    try {
                        // récupère les infos de tous les champs du formualaire
                        JSONArray jsonArray = JSONFactoryUtil.createJSONObject(jsonString).getJSONArray("fieldValues");
                        for (Object jsonObject : jsonArray) {
                            // on ne récupère que le champs concerné
                            JSONObject json = JSONFactoryUtil.createJSONObject(jsonObject.toString());
                            String instanceId = json.getString("instanceId");
                            if(instanceId.equals(formSendRecordField.getInstanceId()))
                                response = json.getJSONObject("value").getString(Locale.FRANCE.toString()).replaceAll("(\r\n|\n)", "<br />");
                        }
                    } catch (JSONException e) {
                        _log.error(e.getMessage(), e);
                    }
                }
            }
        }
        return response;
    }

    private final Log _log = LogFactoryUtil.getLog(this.getClass());
}
