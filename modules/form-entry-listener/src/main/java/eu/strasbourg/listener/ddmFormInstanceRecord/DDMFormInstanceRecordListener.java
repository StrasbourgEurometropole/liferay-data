package eu.strasbourg.listener.ddmFormInstanceRecord;

import com.liferay.dynamic.data.mapping.model.*;
import com.liferay.dynamic.data.mapping.service.DDMContentLocalService;
import com.liferay.dynamic.data.mapping.storage.DDMFormFieldValue;
import com.liferay.mail.kernel.service.MailService;
import com.liferay.portal.kernel.template.*;
import com.liferay.portal.kernel.util.*;
import eu.strasbourg.utils.MailHelper;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.ModelListener;
import com.liferay.portal.kernel.model.BaseModelListener;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;


import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.mail.internet.InternetAddress;
import java.io.StringWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * @author louis.politanski
 */
@Component(
		immediate = true,
		service = ModelListener.class
)
public class DDMFormInstanceRecordListener extends BaseModelListener<DDMFormInstanceRecord> {

	// Nom de champ formulaire mail declenchant une notification automatique
	private static final String emailNametag = "__USER_EMAIL__";
	private static final String[] includedFieldTypes = new String[] { "text", "select", "radio", "date",
			"checkbox_multiple", "numeric"};
	private static final Set<String> includedFieldTypesSet = new HashSet<>(Arrays.asList(includedFieldTypes));
	private static final String[] optionFieldTypes = new String[] { "select", "radio", "checkbox_multiple"};
	private static final Set<String> optionFieldTypesSet = new HashSet<>(Arrays.asList(optionFieldTypes));
	private class CustomFieldData {
		private String fieldType;
		private String fieldLabel;
		private List<String> valuesIDs = new ArrayList<>();
		private String valueLabel;

		public CustomFieldData() {}

		public String getFieldType() {
			return fieldType;
		}

		public void setFieldType(String fieldType) {
			this.fieldType = fieldType;
		}

		public String getFieldLabel() {
			return fieldLabel;
		}

		public void setFieldLabel(String fieldLabel) {
			this.fieldLabel = fieldLabel;
		}
		public List<String> getValuesIDs() {
			return valuesIDs;
		}

		public void addValueID(String valueID) {
			if (optionFieldTypesSet.contains(this.fieldType)) {
				switch (this.fieldType)
				{
					case "select":
						String value =  valueID.replace("\"","");
						value = value.replace("[", "");
						value = value.replace("]", "");
						valuesIDs.add(value);
						break;
					case "checkbox_multiple":
						String[] values = valueID.split(",", 0);
						for (String str : values) {
							str = str.replace("\"","");
							str = str.replace("[", "");
							str = str.replace("]", "");
							valuesIDs.add(str);
						}
						break;
					default:
						valuesIDs.add(valueID);
				}
			}
			else {
				setValueLabel(valueID);
			}
		}

		public String getValueLabel() {
			return valueLabel;
		}

		public void setValueLabel(String valueLabel) {
			this.valueLabel = valueLabel;
		}
	}

	@Override
	public void onAfterUpdate(DDMFormInstanceRecord form) {

		// Check statut de remplissage du formulaire : 0 = rempli et valide
		try {
			if (form.getStatus() != 0)
			{ return; }
		} catch (PortalException e) {
			e.printStackTrace();
		}


		try {
			Locale locale = form.getDDMFormValues().getDefaultLocale();

			// Contient les valeurs et le type du champ, avec l'identifiant du champ comme cle de la Map
			Map<String, List<DDMFormFieldValue>> formfieldvaluesMap = form.getDDMFormValues().getDDMFormFieldValuesMap();

			// Verification de la presence du nametag d'email dans les identifiants de champ
			if (!formfieldvaluesMap.containsKey(emailNametag)) {
				_log.debug("No email nametag");
				return;
			}
			else { _log.debug("Email nametag valid"); }

			// Contient les donnees associees a un champ
			Map<String, CustomFieldData> fieldDataMap = new HashMap<>();
			// Les ids des champs ordonnes selon leur position dans le formulaire
			List<String> orderedNamesList = new ArrayList<>();

			// Recuperation des id des valeurs (exclusion si type non traite)
			for (String formFieldKey : formfieldvaluesMap.keySet()) {
				List<DDMFormFieldValue> formFieldValuesList = formfieldvaluesMap.get(formFieldKey);
				// On selectionne uniquement le type de champ souhaite pour le recapitulatif
				if (formFieldValuesList.size() == 1 &&
						includedFieldTypesSet.contains(formFieldValuesList.get(0).getType()))  {
					DDMFormFieldValue fieldValue = formFieldValuesList.get(0);
					CustomFieldData newFieldData = new CustomFieldData();
					fieldDataMap.put(fieldValue.getName(), newFieldData);
					// Ajout du type de champ
					newFieldData.setFieldType(fieldValue.getType());
					// Ajout de l'identifiant de(s) valeur(s) renseignee(s)
					newFieldData.addValueID(fieldValue.getValue().getString(locale));
				}
			}

			String userEmail = fieldDataMap.get(emailNametag).getValueLabel();
			if (!Validator.isEmailAddress(userEmail)) {
				_log.error("Invalid email address : "+userEmail);
				return;
			}

			_log.error("Valeurs ok");

			// Recuperation des labels des champs du formulaire et des labels des valeurs reneignees
			List<DDMFormField> formFieldsList = form.getDDMFormValues().getDDMForm().getDDMFormFields();
			for (DDMFormField formField : formFieldsList) {
				// On recupere uniquement les champs dont on a recupere les valeurs precedemment
				String fieldName = formField.getName();
				if (fieldDataMap.containsKey(fieldName)) {
					// On ajoute l'id du champ a la liste determinant l'ordre
					orderedNamesList.add(fieldName);
					// On ajoute le libelle du champ
					CustomFieldData currentFieldData = fieldDataMap.get(fieldName);
					currentFieldData.setFieldLabel(formField.getLabel().getString(locale));
					// Pour les champs a options dont la valeur est formattee, on recupere le libelle de celle-ci
					if (optionFieldTypesSet.contains(formField.getType())) {
						DDMFormFieldOptions fieldOptions = formField.getDDMFormFieldOptions();
						String valueLabel = "";
						boolean firstConcat = true;
						for (String valueId : currentFieldData.getValuesIDs()) {
							String optionlLabel = fieldOptions.getOptionLabels(valueId).getString(locale);
							if (firstConcat) {
								valueLabel = optionlLabel;
								firstConcat = false;
							}
							else {
								valueLabel += ", " + optionlLabel;
							}
						}
						currentFieldData.setValueLabel(valueLabel);
					}
				}
			}

			// Envoi de l'email de notification
			Map<String, Object> context = new HashMap<>();
			DDMFormInstance formInstance = form.getFormInstance();
			String formName = formInstance.getName(formInstance.getDescriptionCurrentLanguageId());
			context.put("formName", formName);

			String mailFields = "";
			for (String name: orderedNamesList) {
				CustomFieldData entryData = fieldDataMap.get(name);

				mailFields += "<span class=\"label\">"+entryData.getFieldLabel()+"</span><hr><strong>"+entryData.getValueLabel()+"</strong><br><br>";
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
				_log.debug("Mail sent to:"+userEmail);
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