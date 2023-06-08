package eu.strasbourg.portlet.form_send.action;

import com.liferay.dynamic.data.mapping.model.DDMContent;
import com.liferay.dynamic.data.mapping.model.DDMFormInstanceRecord;
import com.liferay.dynamic.data.mapping.service.DDMContentLocalServiceUtil;
import com.liferay.dynamic.data.mapping.service.DDMFormInstanceRecordLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import eu.strasbourg.service.formSendRecordField.model.FormSendRecordField;
import eu.strasbourg.service.formSendRecordField.service.FormSendRecordFieldLocalService;
import eu.strasbourg.service.formSendRecordField.service.FormSendRecordFieldLocalServiceUtil;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import java.util.Date;
import java.util.List;

@Component(
        immediate = true,
        property = {
        "javax.portlet.name=" + StrasbourgPortletKeys.FORM_SEND_BO,
        "mvc.command.name=saveFormSend"
        },
        service = MVCActionCommand.class
        )
public class SaveFormSendActionCommand implements MVCActionCommand{

    private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());
    private FormSendRecordFieldLocalService _formSendRecordFieldLocalService;

    @Override
    public boolean processAction(ActionRequest actionRequest, ActionResponse actionResponse) {
        ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest
                .getAttribute(WebKeys.THEME_DISPLAY);
        try {
            long recordId = ParamUtil.getLong(actionRequest,"recordId");
            if (Validator.isNotNull(recordId)) {
                // récupération du formulaire envoyé
                DDMFormInstanceRecord record = DDMFormInstanceRecordLocalServiceUtil.fetchFormInstanceRecord(recordId);
                if(Validator.isNotNull(record)){
                    // récupère les infos du contenu du formulaire envoyé
                    DDMContent content = DDMContentLocalServiceUtil.fetchDDMContent(record.getStorageId());
                    if(Validator.isNotNull(content)) {
                        // récupère le contenu du formulaire envoyé
                        String jsonString = content.getData();
                        if (Validator.isNotNull(jsonString)) {
                            // récupère les infos de tous les champs du formualaire
                            JSONArray jsonArray = JSONFactoryUtil.createJSONObject(jsonString).getJSONArray("fieldValues");
                            for (Object jsonObject : jsonArray) {
                                // récupère le formSendRecordField
                                JSONObject json = JSONFactoryUtil.createJSONObject(jsonObject.toString());
                                List<FormSendRecordField> formSendRecordFieldList = FormSendRecordFieldLocalServiceUtil.getByContentAndInstanceId(content.getContentId(), json.getString("instanceId"));
                                if(formSendRecordFieldList.size() > 0){
                                    FormSendRecordField formSendRecordField = formSendRecordFieldList.get(0);
                                    if (Validator.isNotNull(formSendRecordField)){
                                        // récupère la réponse si elle existe
                                        String reponse = ParamUtil.getString(actionRequest,"rep-ville_" + formSendRecordField.getFormSendRecordFieldId());
                                        // vérifi que la réponse est nouvelle
                                        if(!reponse.equals(formSendRecordField.getResponse())){
                                            formSendRecordField.setModifiedDate(new Date());
                                            formSendRecordField.setResponseUserId(themeDisplay.getUserId());
                                            formSendRecordField.setResponse(reponse);

                                            _formSendRecordFieldLocalService.updateFormSendRecordField(formSendRecordField);
                                        }
                                    }
                                }

                            }
                        }
                    }
                }
            }

        } catch (PortalException e) {
            _log.error(e);
        }
        return true;
    }

    @Reference(unbind = "-")
    protected void setFormSendRecordFieldLocalService(FormSendRecordFieldLocalService formSendRecordFieldLocalService){
        _formSendRecordFieldLocalService = formSendRecordFieldLocalService;
    }
}
