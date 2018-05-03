package eu.strasbourg.service.objtp.service.util;

import java.io.IOException;
import java.net.MalformedURLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import org.osgi.service.component.annotations.Reference;

import com.liferay.document.library.kernel.model.DLFolderConstants;
import com.liferay.document.library.kernel.service.DLAppServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.service.CompanyLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.ResourceBundleUtil;
import com.liferay.portal.kernel.util.Validator;

import eu.strasbourg.service.objtp.model.FoundObject;
import eu.strasbourg.service.objtp.model.ObjectCategory;
import eu.strasbourg.service.objtp.service.FoundObjectLocalService;
import eu.strasbourg.service.objtp.service.FoundObjectLocalServiceUtil;
import eu.strasbourg.service.objtp.service.ObjectCategoryLocalService;
import eu.strasbourg.service.objtp.service.ObjectCategoryLocalServiceUtil;
import eu.strasbourg.utils.FileEntryHelper;
import eu.strasbourg.utils.JSONHelper;
import eu.strasbourg.utils.StrasbourgPropsUtil;

public class ObjtpImporter {

	private ResourceBundle bundle = ResourceBundleUtil
			.getBundle("content.ImportErrors", this.getClass().getClassLoader());
	private final Log _log = LogFactoryUtil.getLog(this.getClass());
	
	@Transactional(isolation = Isolation.DEFAULT, rollbackFor = {PortalException.class, SystemException.class,IOException.class,JSONException.class, ParseException.class})	
	public void doImport() throws JSONException, IOException,PortalException, ParseException {
		_log.info("Start importing objtp");
	
		ImportReportObjtp report = new ImportReportObjtp();
		
		this.doObjectCategoriesImport(report);
		
		if(!report.getReportLinesObjectCategory().isEmpty() && report.getObjectCategoryStatus() != ImportReportStatusObjtp.FAILURE) {
			report.setObjectCategoryStatus(ImportReportStatusObjtp.SUCCESS_WITH_ERRORS);
		}
		
		this.doFoundObjectsImport(report);
		
		if(!report.getReportLinesFoundObject().isEmpty() && report.getFoundObjectStatus() != ImportReportStatusObjtp.FAILURE) {
			report.setFoundObjectStatus(ImportReportStatusObjtp.SUCCESS_WITH_ERRORS);
		}
		report.setEndDate(new Date());
		report.sendMail();
		_log.info("Finish importing objtp");
	}


	/**
	 * Lance l'import des catégories d'objet
	 * @throws MalformedURLException 
	 * @throws IOException 
	 * @throws JSONException 
	 */
	@Transactional(isolation = Isolation.DEFAULT, rollbackFor = {PortalException.class, SystemException.class,IOException.class,JSONException.class})
	public ImportReportObjtp doObjectCategoriesImport(ImportReportObjtp report) throws JSONException, IOException {
		_log.info("Start importing object categories");
		
		JSONObject json = null;
		try {
			// On récupère le JSON contenant les catégories d'objet trouvé depuis un appel à l'API
			String url = StrasbourgPropsUtil.getObjtpURL() + "liste_categories";
			json = JSONHelper.readJsonFromURL(url);
		} catch (IOException e) { 
			// Erreur de lecture URL
			_log.error(e);
			report.globalErrorObjectCategory(LanguageUtil.get(bundle, "category-object-no-url"));
			return report;
		} catch (JSONException e) { 
			// Erreur de parse du JSON
			_log.error(e);
			report.globalErrorObjectCategory(LanguageUtil.get(bundle, "category-object-json-fail"));
			return report;
		}
		
	    // Récupère les résultats
	    JSONArray objectCategories = json.getJSONArray("result");
	    
	    if(objectCategories == null || objectCategories.length() ==0) {
	    	report.globalErrorObjectCategory(LanguageUtil.get(bundle, "no-category-object"));
	    	return report;
	    }  
	    
	    // Récupère le nombre total de catégories d'objet à insérer
	    report.setTotalObjectCategoryCount(objectCategories.length());
	    // On vide d'abord la base   
	    List<ObjectCategory> allCategories = ObjectCategoryLocalServiceUtil.getObjectCategories(-1, -1);
	 	for (ObjectCategory category : allCategories) {
	 		ObjectCategoryLocalServiceUtil.deleteObjectCategory(category);
		}
	    
	    for (int i = 0; i < objectCategories.length(); i++) {
	    	_log.info("Import catégorie d'objet : " + (i + 1) + "/" + objectCategories.length());
	    	JSONObject objectCategory = objectCategories.getJSONObject(i);
	    	ImportReportLineObjtp reportLine = this.importObjectCategory(objectCategory);
	    	
	    	if(reportLine.getStatus() == ImportReportStatusObjtp.FAILURE) {
	    		report.incrementErrorObjectCategoryCount();
	    		report.getReportLinesObjectCategory().add(reportLine);
	    	}
	    }		
	    _log.info("Finish importing object categories");
		return report;
	}
	

	@Transactional(isolation = Isolation.DEFAULT, rollbackFor = {PortalException.class, SystemException.class,IOException.class,JSONException.class})
	public ImportReportLineObjtp importObjectCategory(JSONObject objectCategoryJSON) {
		
		ImportReportLineObjtp reportLine = new ImportReportLineObjtp();
		
		// Récupération des différents champs
		String categoryName = objectCategoryJSON.getString("nom_categorie");
		if (Validator.isNull(categoryName)) {
			reportLine.error(LanguageUtil.get(bundle, "category-object-name-field-missing"));
		}	
		String codeCategory = objectCategoryJSON.getString("code_categorie");
		if (Validator.isNull(codeCategory)) {
			reportLine.error(LanguageUtil.get(bundle, "category-object-code-field-missing"));
			reportLine.setNumber("XXX");
		}
		else {
			reportLine.setNumber(codeCategory);
		}
		
		if(reportLine.getStatus() == ImportReportStatusObjtp.FAILURE) {
			return reportLine;
		}
		
		ObjectCategory objectCategory = ObjectCategoryLocalServiceUtil.createObjectCategory(codeCategory);
		
		objectCategory.setName(categoryName);
		
		ObjectCategoryLocalServiceUtil.updateObjectCategory(objectCategory);
	
		return reportLine;
	}
	
	
	
	/**
	 * Lance l'import des objets trouvés
	 * @throws MalformedURLException 
	 * @throws IOException 
	 * @throws PortalException 
	 * @throws ParseException 
	 */
	@Transactional(isolation = Isolation.DEFAULT, rollbackFor = {PortalException.class, SystemException.class,IOException.class,JSONException.class})
	public ImportReportObjtp doFoundObjectsImport(ImportReportObjtp report) throws PortalException, IOException, ParseException {
		_log.info("Start importing found objects");
		
		
		
		// On récupère le JSON contenant les objets trouvés depuis un appel à l'API
		JSONObject json = null;
		try {
			String url = StrasbourgPropsUtil.getObjtpURL() + "recherche_objets";
			json = JSONHelper.readJsonFromURL(url);
		} catch (IOException e) { 
			// Erreur de lecture URL
			_log.error(e);
			report.globalErrorFoundObject(LanguageUtil.get(bundle, "found-object-no-url"));
			return report;
		} catch (JSONException e) { 
			// Erreur de parse du JSON
			_log.error(e);
			report.globalErrorFoundObject(LanguageUtil.get(bundle, "found-object-json-fail"));
			return report;
		}
		
	    // Récupère les résultats
	    JSONArray foundObjects = json.getJSONArray("result");
	    
	    if(foundObjects == null || foundObjects.length() ==0) {
	    	report.globalErrorFoundObject(LanguageUtil.get(bundle, "no-found-object"));
			return report;
	    }
	    
	    // Récupère le nombre total d'objet à insérer
	    report.setTotalFoundObjectCount(foundObjects.length());
	    
	    // On vide d'abord la base
	    List<FoundObject> allobjects = FoundObjectLocalServiceUtil.getFoundObjects(-1, -1);
	 	for (FoundObject object : allobjects) {
	 		FoundObjectLocalServiceUtil.deleteFoundObject(object);
		}
	    
	 	
	    for (int i = 0; i < foundObjects.length() ; i++) {
	    	_log.info("Import objet trouvé : " + (i + 1) + "/" + foundObjects.length());
	    	JSONObject foundObject = foundObjects.getJSONObject(i);
	    	ImportReportLineObjtp reportLine = this.importObject(foundObject);
	    	
	    	if(reportLine.getStatus() == ImportReportStatusObjtp.FAILURE) {
	    		report.incrementErrorFoundObjectCount();
	    		report.getReportLinesFoundObject().add(reportLine);
	    	}
	    	if(reportLine.getStatus() == ImportReportStatusObjtp.SUCCESS_WITH_ERRORS) {
	    		report.incrementFoundObjectNoImageCount();
	    		report.getReportLinesNoImage().add(reportLine);
	    	}
	    }
	    
	    _log.info("Finish importing found objects");
		
		
		return report;
	}
	
	@Transactional(isolation = Isolation.DEFAULT, rollbackFor = {PortalException.class, SystemException.class,IOException.class,JSONException.class})
	public ImportReportLineObjtp importObject(JSONObject objectJSON) throws IOException, PortalException, ParseException {
		
		ImportReportLineObjtp reportLine = new ImportReportLineObjtp();
		
		// Récupération des différents champs
		String objectNumero = objectJSON.getString("numero_objet");
		if (Validator.isNull(objectNumero)) {
			reportLine.error(LanguageUtil.get(bundle, "found-object-numero-field-missing"));
			reportLine.setNumber("XXX");
		}
		else {
			reportLine.setNumber(objectNumero);
		}
		String depotDate = objectJSON.getString("date_depot");
		if (Validator.isNull(depotDate)) {
			reportLine.error(LanguageUtil.get(bundle, "found-object-date-field-missing"));
		}	
		String codeCategory = objectJSON.getString("code_categorie");
		if (Validator.isNull(codeCategory)) {
			reportLine.error(LanguageUtil.get(bundle, "found-object-code-field-missing"));
		}
		else {
			ObjectCategory category = ObjectCategoryLocalServiceUtil.fetchObjectCategory(codeCategory);
			if(category == null) {
				reportLine.error(LanguageUtil.format(bundle,"found-object-no-existing-code",codeCategory));
			}
		}		
		
		FoundObject objectDuplicate = FoundObjectLocalServiceUtil.fetchFoundObject(objectNumero);
		
		if(objectDuplicate != null) {
			reportLine.error(LanguageUtil.get(bundle, "found-object-already-existing"));
		}
		
		if(reportLine.getStatus() == ImportReportStatusObjtp.FAILURE) {
			return reportLine;
		}
		
		
		// Crée le nouvel objet trouvé
		FoundObject object = FoundObjectLocalServiceUtil.createFoundObject(objectNumero);		
		object.setCategoryCode(codeCategory);
		
		SimpleDateFormat recievingFormat = new SimpleDateFormat("yyyymmdd");
		SimpleDateFormat outFormat = new SimpleDateFormat("dd/mm/yyyy");
		
		Date dateDepot = recievingFormat.parse(depotDate);
		
		object.setDate(dateDepot);
		
		// On récupère l'image associée à l'objet trouvé
		String url = StrasbourgPropsUtil.getObjtpURL() + "image_objet?numero_objet="+ object.getNumber();
		JSONObject json = null;
		
		try {
			json = JSONHelper.readJsonFromURL(url);
		} catch (IOException e) { 
			// Erreur de lecture URL
			_log.error(e);
			reportLine.error(LanguageUtil.get(bundle, "image-no-url"));
			reportLine.setStatus(ImportReportStatusObjtp.SUCCESS_WITH_ERRORS);
		} catch (JSONException e) { 
			// Erreur de parse du JSON
			_log.error(e);
			reportLine.error(LanguageUtil.get(bundle, "image-json-fail"));
			reportLine.setStatus(ImportReportStatusObjtp.SUCCESS_WITH_ERRORS);
		}
		
		JSONArray imageArray = json.getJSONArray("result");
		
		if(imageArray == null || imageArray.length() ==0){
			reportLine.error(LanguageUtil.get(bundle, "no-image-for-object"));
			reportLine.setStatus(ImportReportStatusObjtp.SUCCESS_WITH_ERRORS);
		}
		else {
			JSONObject image = imageArray.getJSONObject(0);
			String imageBase64 = image.getString("image");
			
			// On convertit l'image base64 en série de Bytes
			byte[] imageBytes = javax.xml.bind.DatatypeConverter.parseBase64Binary(imageBase64);
			
			// Récupère les différents ID nécessaires aux manipulations de dossiers
			Company defaultCompany = CompanyLocalServiceUtil.getCompanyByWebId("liferay.com");
			long companyId = defaultCompany.getCompanyId();
			long globalGroupId = defaultCompany.getGroup().getGroupId();
		    long repositoryId = DLFolderConstants.getDataRepositoryId(globalGroupId, DLFolderConstants.DEFAULT_PARENT_FOLDER_ID);
		  
		    // serviceContext nécessaire à la création du dossier et de l'enregistrement de l'image dans le dossier
		    ServiceContext serviceContext = new ServiceContext();
		    serviceContext.setAddGroupPermissions(true);
		    serviceContext.setAddGuestPermissions(true);
		    
			// on récupère le dossier "Objets trouves" présent dans Global
			Folder objtpFolder = DLAppServiceUtil.getFolder(repositoryId,0,"Objets trouves");
			
			// S'il n'existe pas, on le crée
			if(objtpFolder == null) {
				objtpFolder = DLAppServiceUtil.addFolder(
			             repositoryId
			             ,DLFolderConstants.DEFAULT_PARENT_FOLDER_ID
			             , "Objets trouves"
			            , "Objets trouves"
			            , serviceContext);;
			}
			
			FileEntry existingObjectImage =  DLAppServiceUtil.getFileEntry(globalGroupId, objtpFolder.getFolderId(), object.getNumber());
			
			if(existingObjectImage != null) {
				DLAppServiceUtil.deleteFileEntryByTitle(repositoryId, objtpFolder.getFolderId(), object.getNumber());
			}
			
			// on ajoute l'image au dossier
			FileEntry objectImage = DLAppServiceUtil.addFileEntry(
	                repositoryId,
	                objtpFolder.getFolderId(),
	                object.getNumber(),
	                "image/jpeg",
	                object.getNumber(),
	                null,
	                null,
	                imageBytes,
	                serviceContext);
			String imageUrl = FileEntryHelper.getFileEntryURL(objectImage.getFileEntryId());
			object.setImageUrl(imageUrl);
		}
			
		
		FoundObjectLocalServiceUtil.updateFoundObject(object);
	
		return reportLine;
	}	
}
