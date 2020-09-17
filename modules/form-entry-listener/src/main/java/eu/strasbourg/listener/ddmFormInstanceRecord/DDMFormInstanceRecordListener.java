package eu.strasbourg.listener.ddmFormInstanceRecord;

import com.liferay.dynamic.data.mapping.model.DDMContent;
import com.liferay.dynamic.data.mapping.model.DDMFormInstance;
import com.liferay.dynamic.data.mapping.service.DDMContentLocalService;
import com.liferay.mail.kernel.model.MailMessage;
import com.liferay.mail.kernel.service.MailService;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.template.*;
import com.liferay.portal.kernel.util.*;
import eu.strasbourg.utils.MailHelper;
import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.ModelListener;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.dynamic.data.mapping.model.DDMFormInstanceRecord;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;


import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.mail.Address;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.io.StringWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

/**
 * @author louis.politanski
 */
@Component(
		immediate = true,
		service = ModelListener.class
)
public class DDMFormInstanceRecordListener extends BaseModelListener<DDMFormInstanceRecord> {

	// Nom de champ formulaire mail declenchant une notification automatique
	private final String emailNametag = "__USER_EMAIL__";

	@Override
	public void onAfterCreate(DDMFormInstanceRecord form) throws ModelListenerException {
		try {
			// Recuperation du nom et de la valeur des champs du formulaire
			long formContentId = form.getStorageId();
			String formData = _ddmContentLocalService.getContent(formContentId).getData();

			Map<String, Map<String, String>> formEntriesM = new HashMap<>();
			JSONObject formContentJ = JSONFactoryUtil.createJSONObject(formData);
			if (formContentJ.has("fieldValues")) {
				JSONArray formFields = formContentJ.getJSONArray("fieldValues");
				for (int i = 0; i < formFields.length(); i++) {
					JSONObject fieldJ = formFields.getJSONObject(i);
					String fieldName = fieldJ.getString("name");
					JSONObject fieldValue = fieldJ.getJSONObject("value");
					Iterator<String> localeKey = fieldValue.keys();
					if (localeKey.hasNext())
					{
						String fieldValueS = fieldValue.getString(localeKey.next());
						Map<String, String> formEntry = new HashMap<>();
						formEntry.put("value", fieldValueS);
						formEntriesM.put(fieldName, formEntry);
					}
				}
			}
			else {
				_log.warn("Form data fieldValues is not present.");
				return;
			}

			// Verification de la presence du nametag d'email et validation
			if (!formEntriesM.containsKey(emailNametag)) {
				_log.debug("No email nametag");
				return;
			}
			String userEmail = formEntriesM.get(emailNametag).get("value");
			if (!Validator.isEmailAddress(userEmail)) {
				_log.error("Invalid email address.");
				return;
			}

			// Recuperation des labels et du type des champs du formulaire
			DDMFormInstance formInstance = form.getFormInstance();
			String formStructureS = formInstance.getStructure().getDefinition();
			JSONObject formStructureJ = JSONFactoryUtil.createJSONObject(formStructureS);
			if (formStructureJ.has("fields")) {
				JSONArray formFields = formStructureJ.getJSONArray("fields");
				for (int i = 0; i < formFields.length(); i++) {
					JSONObject fieldJ = formFields.getJSONObject(i);
					String fieldName = fieldJ.getString("name");
					if (formEntriesM.containsKey(fieldName)) {
						String fieldType = fieldJ.getString("type");
						formEntriesM.get(fieldName).put("type", fieldType);
						JSONObject labelJ = fieldJ.getJSONObject("label");
						Iterator<String> localeKey = labelJ.keys();
						if (localeKey.hasNext())
						{
							String labelS = labelJ.getString(localeKey.next());
							formEntriesM.get(fieldName).put("label", labelS);
						}
					}
				}
			}
			else {
				_log.warn("Form structure fields is not present.");
				return;
			}

			// Traitement des entrees du formulaire en fonction de leur type
			Iterator<String> formEntriesKeys = formEntriesM.keySet().iterator();
			while (formEntriesKeys.hasNext()) {
				String key = formEntriesKeys.next();
				Map<String, String> entryData = formEntriesM.get(key);
				switch (entryData.get("type"))
				{
					case "grid":
						formEntriesKeys.remove();
						break;
					case "select":
						String value =  entryData.get("value").replace("\"","");
						value = value.replace("[", "");
						value = value.replace("]", "");
						entryData.put("value", value);
						break;
					case "checkbox_multiple":
						value =  entryData.get("value").replace("\""," ");
						value = value.replace("[", "");
						value = value.replace("]", "");
						entryData.put("value", value);
						break;
				}
			}

			// Envoi de l'email de notification
			Map<String, Object> context = new HashMap<>();
			String formName = formInstance.getName(formInstance.getDescriptionCurrentLanguageId());
			context.put("formName", formName);

			String mailFields = "";
			formEntriesKeys = formEntriesM.keySet().iterator();
			while (formEntriesKeys.hasNext()) {
				String key = formEntriesKeys.next();
				Map<String, String> entryData = formEntriesM.get(key);
				mailFields += "<p>"+entryData.get("label")+":<br><b>"+entryData.get("value")+"</b></p>";
			}
			context.put("content", mailFields);

			LocalDateTime dateTime = LocalDateTime.now();
			String date = dateTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
			String time = dateTime.format(DateTimeFormatter.ofPattern("hh:mm"));
			context.put("date", date);
			context.put("time", time);

			TemplateResource templateResourceSubject;
			TemplateResource templateResourceBody;
			Template subjectTemplate;
			Template bodyTemplate;
			String mailSubject;
			String mailBody;
			StringWriter out;

			boolean success = false;
			try {

				// Chargement du template contenant le sujet du mail
				templateResourceSubject = new URLTemplateResource("0",
						Objects.requireNonNull(this.getClass().getClassLoader()
								.getResource("/templates/contact-mail-subject.ftl")));
				subjectTemplate = TemplateManagerUtil.getTemplate(
						TemplateConstants.LANG_TYPE_FTL, templateResourceSubject, false);

				// Traitement du template sujet
				out = new StringWriter();
				subjectTemplate.putAll(context);
				subjectTemplate.processTemplate(out);
				mailSubject = out.toString();

				//Chargement du template contenant le corps du mail
				templateResourceBody = new URLTemplateResource("0",
						Objects.requireNonNull(this.getClass().getClassLoader()
								.getResource("/templates/contact-mail-body.ftl")));
				bodyTemplate = TemplateManagerUtil.getTemplate(
						TemplateConstants.LANG_TYPE_FTL, templateResourceBody, false);

				// Traitement du template corps
				out = new StringWriter();
				bodyTemplate.putAll(context);
				bodyTemplate.processTemplate(out);
				mailBody = out.toString();

				InternetAddress fromAddress = new InternetAddress("no-reply@no-reply.strasbourg.eu",
						"Strasbourg.eu");

				InternetAddress[] toAddresses = {new InternetAddress(userEmail)};

				success = MailHelper.sendMailWithHTML(fromAddress, toAddresses, mailSubject, mailBody);
			} catch (Exception e) {
				_log.error(e);
			}
			if (success) {
				_log.error("Mail sent to:"+userEmail);
			}
			else {
				_log.error("Error sending mail to:"+userEmail);
			}
		}
		catch (PortalException e) {
			e.printStackTrace();
		}
		return;
	}

	@Reference
	private DDMContentLocalService _ddmContentLocalService;
	@Reference
	private MailService _mailService;

	private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());

}