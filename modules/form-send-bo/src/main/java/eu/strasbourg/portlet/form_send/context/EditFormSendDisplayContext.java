package eu.strasbourg.portlet.form_send.context;

import com.liferay.dynamic.data.mapping.model.DDMContent;
import com.liferay.dynamic.data.mapping.model.DDMFormInstance;
import com.liferay.dynamic.data.mapping.model.DDMFormInstanceRecord;
import com.liferay.dynamic.data.mapping.model.DDMStructure;
import com.liferay.dynamic.data.mapping.service.DDMContentLocalServiceUtil;
import com.liferay.dynamic.data.mapping.service.DDMFormInstanceRecordLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import eu.strasbourg.service.formSendRecordField.model.FormSendRecordField;
import eu.strasbourg.service.formSendRecordField.service.FormSendRecordFieldLocalServiceUtil;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import java.util.*;

public class EditFormSendDisplayContext{

    private final RenderRequest _request;
    private final ThemeDisplay _themeDisplay;

    private DDMFormInstanceRecord _record;
    private List<String[]> _recordFields;
    private Map<String, String> _texteAreaFields;

    public EditFormSendDisplayContext(RenderRequest request, RenderResponse response) {
        this._request = request;
        this._themeDisplay = (ThemeDisplay) request
                .getAttribute(WebKeys.THEME_DISPLAY);
    }

    // récupère les valeurs d'un formulaire envoyé (nom du champ, valeur du champ, formSendRecordFieldId, réponse de la ville)
    public List<String[]> getRecordFields(Locale locale) {
        long recordId = ParamUtil.getLong(_request, "recordId");
        if (_recordFields == null && recordId > 0) {
            _recordFields = new ArrayList<String[]>();

            // récupère le formulaire envoyé
            if(Validator.isNotNull(getRecord())){
                // récupère les infos du contenu du formulaire envoyé
                DDMContent content = DDMContentLocalServiceUtil.fetchDDMContent(_record.getStorageId());

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
                                // on ne garde que les types text qui sont renseignés
                                if(Validator.isNotNull(getTexteFields().get(json.getString("name")))
                                        && Validator.isNotNull(json.getJSONObject("value").getString(locale.toString()))) {

                                    // récupère la réponse de la ville
                                    FormSendRecordField formSendRecordField = this.getFormSendRecordField(content.getContentId(), json.getString("instanceId"));
                                    String[] field = {getTexteFields().get(json.getString("name")),
                                            json.getJSONObject("value").getString(locale.toString()).replaceAll("(\r\n|\n)", "<br />"),
                                            ""+formSendRecordField.getFormSendRecordFieldId(), formSendRecordField.getResponse()};
                                    _recordFields.add(field);
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }

            }
        }
        return _recordFields;
    }

    // récupère le formulaire envoyé
    public DDMFormInstanceRecord getRecord(){
        if(_record == null){
            long recordId = ParamUtil.getLong(_request, "recordId");
            DDMFormInstanceRecord record = null;
            if (recordId > 0)
                record = DDMFormInstanceRecordLocalServiceUtil.fetchFormInstanceRecord(recordId);

            _record = record;
        }
        return _record;
    }

    // récupère les champ texte qui devront être affiché (nom, valeur)
    private Map<String, String> getTexteFields() {
        if(this._texteAreaFields == null) {
            Map<String, String> texteAreaFields = new HashMap<String, String>();
            // récupère le formulaire envoyé
            if(Validator.isNotNull(getRecord())){
                DDMFormInstance formInstance = null;
                try {
                    //récupère le formulaire
                    formInstance = _record.getFormInstance();
                    if(Validator.isNotNull(formInstance)){
                        // récupère la structure du formulaire
                        try {
                            DDMStructure structure = formInstance.getStructure();
                            JSONArray jsonArray = JSONFactoryUtil.createJSONArray();
                            if (Validator.isNotNull(structure))
                                jsonArray = JSONFactoryUtil.createJSONObject(structure.getDefinition()).getJSONArray("fields");

                            for (Object json : jsonArray) {
                                // récupère le type de champs
                                JSONObject jsonField = JSONFactoryUtil.createJSONObject(json.toString());
                                String type = jsonField.getString("type");

                                // ne garde que les textearea
                                if (Validator.isNotNull(type) && type.equals("text")) {
                                    texteAreaFields.put(jsonField.getString("name"), jsonField.getJSONObject("label").getString(Locale.FRANCE.toString()));
                                }
                            }
                        } catch (PortalException e) {
                            e.printStackTrace();
                        }
                    }
                } catch (PortalException e) {
                    e.printStackTrace();
                }
            }

            this._texteAreaFields = texteAreaFields;
        }
        return this._texteAreaFields;
    }

    // récupère le formSendRecordField (qui contient les réponses et le lien avec les signalements en lien avec la réponse à une question du formulaire)
    private FormSendRecordField getFormSendRecordField(long contentId, String instanceId) {
        FormSendRecordField formSendRecordField = null;
        List<FormSendRecordField> formSendRecordFieldList = FormSendRecordFieldLocalServiceUtil.getByContentAndInstanceId(contentId, instanceId);
        if(formSendRecordFieldList.size() > 0){
            formSendRecordField = formSendRecordFieldList.get(0);
        }

        if(Validator.isNull(formSendRecordField)){
            // si le formSendRecordField n'existe pas on le créer
            try {
                ServiceContext sc = ServiceContextFactory.getInstance(this._request);
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
}
