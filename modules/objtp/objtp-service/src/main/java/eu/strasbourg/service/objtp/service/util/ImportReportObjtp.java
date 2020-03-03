package eu.strasbourg.service.objtp.service.util;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.template.*;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.PrefsPropsUtil;
import com.liferay.portal.kernel.util.PropsKeys;

import eu.strasbourg.utils.MailHelper;
import eu.strasbourg.utils.StrasbourgPropsUtil;


public class ImportReportObjtp {

	private String globalErrorCauseObjectCategory;
	private String globalErrorCauseFoundObject;
	private int errorObjectCategoryCount;
	private int errorFoundObjectCount;
	private int foundObjectNoImageCount;
	private List<ImportReportLineObjtp> reportLinesObjectCategory;
	private List<ImportReportLineObjtp> reportLinesFoundObject;
	private List<ImportReportLineObjtp> reportLinesNoImage;
	private int objectCategoryStatus;
	private int foundObjectStatus;
	private int totalObjectCategoryCount;
	private int totalFoundObjectCount;
	private Date endDate;
	
	public ImportReportObjtp() {
		this.reportLinesObjectCategory = new ArrayList<>();
		this.reportLinesFoundObject = new ArrayList<>();
		this.reportLinesNoImage = new ArrayList<>();
		this.setFoundObjectStatus(ImportReportStatusObjtp.SUCCESS);
		this.setObjectCategoryStatus(ImportReportStatusObjtp.SUCCESS);
	}
	

	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public int getObjectCategoryStatus() {
		return objectCategoryStatus;
	}
	public void setObjectCategoryStatus(int objectCategoryStatus) {
		this.objectCategoryStatus = objectCategoryStatus;
	}
	public int getFoundObjectStatus() {
		return foundObjectStatus;
	}
	public void setFoundObjectStatus(int foundObjectStatus) {
		this.foundObjectStatus = foundObjectStatus;
	}
	public String getGlobalErrorCauseObjectCategory() {
		return globalErrorCauseObjectCategory;
	}
	public void setGlobalErrorCauseObjectCategory(String globalErrorCauseObjectCategory) {
		this.globalErrorCauseObjectCategory = globalErrorCauseObjectCategory;
	}
	public String getGlobalErrorCauseFoundObject() {
		return globalErrorCauseFoundObject;
	}
	public void setGlobalErrorCauseFoundObject(String globalErrorCauseFoundObject) {
		this.globalErrorCauseFoundObject = globalErrorCauseFoundObject;
	}
	public int getErrorObjectCategoryCount() {
		return errorObjectCategoryCount;
	}
	public void setErrorObjectCategoryCount(int errorObjectCategoryCount) {
		this.errorObjectCategoryCount = errorObjectCategoryCount;
	}
	public int getErrorFoundObjectCount() {
		return errorFoundObjectCount;
	}
	public void setErrorFoundObjectCount(int errorFoundObjectCount) {
		this.errorFoundObjectCount = errorFoundObjectCount;
	}
	public int getFoundObjectNoImageCount() {
		return foundObjectNoImageCount;
	}
	public void setFoundObjectNoImageCount(int foundObjectNoImageCount) {
		this.foundObjectNoImageCount = foundObjectNoImageCount;
	}		
	public int getTotalObjectCategoryCount() {
		return totalObjectCategoryCount;
	}
	public void setTotalObjectCategoryCount(int totalObjectCategoryCount) {
		this.totalObjectCategoryCount = totalObjectCategoryCount;
	}
	public int getTotalFoundObjectCount() {
		return totalFoundObjectCount;
	}
	public void setTotalFoundObjectCount(int totalFoundObjectCount) {
		this.totalFoundObjectCount = totalFoundObjectCount;
	}
	public List<ImportReportLineObjtp> getReportLinesObjectCategory() {
		return reportLinesObjectCategory;
	}
	public void setReportLinesObjectCategory(List<ImportReportLineObjtp> reportLinesObjectCategory) {
		this.reportLinesObjectCategory = reportLinesObjectCategory;
	}
	public List<ImportReportLineObjtp> getReportLinesFoundObject() {
		return reportLinesFoundObject;
	}
	public void setReportLinesFoundObject(List<ImportReportLineObjtp> reportLinesFoundObject) {
		this.reportLinesFoundObject = reportLinesFoundObject;
	}
	public List<ImportReportLineObjtp> getReportLinesNoImage() {
		return reportLinesNoImage;
	}
	public void setReportLinesNoImage(List<ImportReportLineObjtp> reportLinesNoImage) {
		this.reportLinesNoImage = reportLinesNoImage;
	}


	/*
	 * Incrémente le nombre d'erreurs liées aux catégories d'objets
	 */
	public void incrementErrorObjectCategoryCount() {
		this.setErrorObjectCategoryCount(
			this.getErrorObjectCategoryCount() + 1);
	}
	
	/*
	 * Incrémente le nombre d'erreurs liées aux objets trouvés
	 */
	public void incrementErrorFoundObjectCount() {
		this.setErrorFoundObjectCount(
			this.getErrorFoundObjectCount() + 1);
	}
	
	/*
	 * Incrémente le nombre d'objets trouvés sans image
	 */
	public void incrementFoundObjectNoImageCount() {
		this.setFoundObjectNoImageCount(
			this.getFoundObjectNoImageCount() + 1);
	}
	

	/*
	 * Action entreprise lors d'une erreur arrêtant l'import des catégories d'objet
	 */
	public void globalErrorObjectCategory(String cause) {
		this.setObjectCategoryStatus(ImportReportStatusObjtp.FAILURE);
		this.setGlobalErrorCauseObjectCategory(cause);
	}
	
	/*
	 * Action entreprise lors d'une erreur arrêtant l'import des objets trouvés
	 */
	public void globalErrorFoundObject(String cause) {
		this.setFoundObjectStatus(ImportReportStatusObjtp.FAILURE);
		this.setGlobalErrorCauseFoundObject(cause);
	}
	
	public void sendMail() {
		String environment = StrasbourgPropsUtil.getEnvironment();

		String mailAddresses = StrasbourgPropsUtil.getObjtpImportMails();

		StringWriter out = new StringWriter();
		String subject;
		String body;

		try {
			// Chargement du template contenant le sujet du mail
			TemplateResource templateResourceSubject = new URLTemplateResource(
					"0",
					this.getClass().getClassLoader().getResource("/templates/import-notification-mail-subject.ftl.ftl"));
			Template subjectTemplate = TemplateManagerUtil.getTemplate(
					TemplateConstants.LANG_TYPE_FTL, templateResourceSubject, false);

			// Traitement du template sujet
			subjectTemplate.put("environment", environment);
			subjectTemplate.processTemplate(out);
			subject = out.toString();

			//Chargement du template contenant le corps du mail
			TemplateResource templateResourceBody = new URLTemplateResource(
					"0",
					this.getClass().getClassLoader().getResource("/templates/import-notification-mail-body.ftl"));
			Template bodyTemplate = TemplateManagerUtil.getTemplate(
					TemplateConstants.LANG_TYPE_FTL, templateResourceSubject, false);

			// Traitement du template corps
			bodyTemplate.put("report", this);
			bodyTemplate.processTemplate(out);
			body = out.toString();

			String adminEmailFromAddress = PrefsPropsUtil.getString(
				PortalUtil.getDefaultCompanyId(),
				PropsKeys.ADMIN_EMAIL_FROM_ADDRESS);

			// Envoi du mail
			MailHelper.sendMailWithPlainText(adminEmailFromAddress, mailAddresses, subject, body);
		} catch (Exception e) {
			_log.error(e);
		}
	}
	
	private final Log _log = LogFactoryUtil.getLog(this.getClass());
}
