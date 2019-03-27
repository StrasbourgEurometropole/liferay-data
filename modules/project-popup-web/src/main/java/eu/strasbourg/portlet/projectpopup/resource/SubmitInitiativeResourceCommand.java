package eu.strasbourg.portlet.projectpopup.resource;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetCategoryModel;
import com.liferay.document.library.kernel.model.DLFolder;
import com.liferay.document.library.kernel.model.DLFolderConstants;
import com.liferay.document.library.kernel.service.DLAppLocalServiceUtil;
import com.liferay.document.library.kernel.service.DLFolderLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.upload.UploadRequest;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.MimeTypesUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.SessionParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import eu.strasbourg.service.oidc.model.PublikUser;
import eu.strasbourg.service.oidc.service.PublikUserLocalServiceUtil;
import eu.strasbourg.service.project.model.Initiative;
import eu.strasbourg.service.project.service.InitiativeLocalServiceUtil;
import eu.strasbourg.utils.AssetVocabularyHelper;
import eu.strasbourg.utils.MailHelper;
import eu.strasbourg.utils.PublikApiClient;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import freemarker.template.Configuration;
import freemarker.template.Template;

import org.osgi.service.component.annotations.Component;

import javax.mail.internet.InternetAddress;
import javax.portlet.PortletException;
import javax.portlet.PortletRequest;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletRequest;

import static eu.strasbourg.portlet.projectpopup.ProjectPopupPortlet.CITY_NAME;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component(
    immediate = true,
    property = {
    	"javax.portlet.name=" + StrasbourgPortletKeys.PROJECT_POPUP_WEB,
    	"mvc.command.name=submitInitiative"
    },
    service = MVCResourceCommand.class
)
public class SubmitInitiativeResourceCommand implements MVCResourceCommand {
	
	// Id de recuperation des champs
	private static final String TITLE = "title";
	private static final String DESCRIPTION = "description";
	private static final String IN_THE_NAME_OF = "inTheNameOf";
	private static final String DISTRICT = "district";
	private static final String THEMATIC = "thematic";
	private static final String PROJECT = "project";
	private static final String PLACE = "place";
	private static final String PHOTO = "photo";
	private static final String VIDEO = "video";
	private static final String BIRTHDAY = "birthday";
    private static final String ADDRESS = "address";
    private static final String CITY = "city";
    private static final String POSTALCODE = "postalcode";
    private static final String PHONE = "phone";
    private static final String MOBILE = "mobile";
    private static final String SAVEINFO = "saveinfo";
    
    // Pattern de recuperation des dates
    private static final String PATTERN = "dd/MM/yyyy";
	
	// Champs
	private String title;
	private String description;
	private String inTheNameOf;
	private long districtId;
	private long thematicId;
	private long projectId;
	private String place;
	private String video;
	private Date birthday;
    private String address;
    private String city;
    private long postalcode;
    private String phone;
    private String mobile;

    // Gestion et contexte de la requete
    private String publikID;
    private PublikUser user;
    private DateFormat dateFormat;
    private String message;

	private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());
	
	@Override
	public boolean serveResource(ResourceRequest request, ResourceResponse response) throws PortletException {
        
        // Initialisations respectives de : resultat probant de la requete, sauvegarde ou non des informations Publik, message de retour, format de date
        boolean result = false;
        boolean savedInfo = false;
        this.message = "";
        this.dateFormat = new SimpleDateFormat(PATTERN);
        
        // Recuperation de l'utilsiteur Publik ayant lance la demande
        this.publikID = getPublikID(request);
        
        // Recuperation des informations du formulaire
        this.title = HtmlUtil.stripHtml(ParamUtil.getString(request, TITLE));
        this.description = HtmlUtil.stripHtml(ParamUtil.getString(request, DESCRIPTION));
        this.districtId = ParamUtil.getLong(request, DISTRICT);
        this.thematicId = ParamUtil.getLong(request, THEMATIC);
        this.projectId = ParamUtil.getLong(request, PROJECT);
        this.place = HtmlUtil.stripHtml(ParamUtil.getString(request, PLACE));
        this.video = HtmlUtil.stripHtml(ParamUtil.getString(request, VIDEO));
        this.birthday = ParamUtil.getDate(request, BIRTHDAY, this.dateFormat);
        this.address = HtmlUtil.stripHtml(ParamUtil.getString(request, ADDRESS));
        this.city = HtmlUtil.stripHtml(ParamUtil.getString(request, CITY));
        this.postalcode = ParamUtil.getLong(request, POSTALCODE);
        this.phone = HtmlUtil.stripHtml(ParamUtil.getString(request, PHONE));
        this.mobile = HtmlUtil.stripHtml(ParamUtil.getString(request, MOBILE));
        this.inTheNameOf = HtmlUtil.stripHtml(ParamUtil.getString(request, IN_THE_NAME_OF));
		
        // Verification de la validite des informations
        if (validate(request)) {
        
        	// Mise a jour des informations du compte Publik si requete valide et demande par l'utilisateur
        	savedInfo = ParamUtil.getBoolean(request, SAVEINFO);
            if (savedInfo) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String dateNaiss = sdf.format(ParamUtil.getDate(request, BIRTHDAY, dateFormat));
                PublikApiClient.setAllUserDetails(
                        this.publikID,
                        this.user.getLastName(),
                        this.address,
                        "" + this.postalcode,
                        this.city,
                        dateNaiss,
                        this.phone,
                        this.mobile
                );
            }
            
         	// Envoi de la demande
            result = saveInitiative(request);
            
            if(result)
            	sendInitiativeMailConfirmation(request);
        }
        
        // Retour des informations de la requete en JSON
        JSONObject jsonResponse = JSONFactoryUtil.createJSONObject();
        jsonResponse.put("result", result);
        jsonResponse.put("message", this.message);
        jsonResponse.put("savedInfo", savedInfo);

        // Recuperation de l'élément d'écriture de la réponse
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
            writer.print(jsonResponse.toString());
        } catch (IOException e) {
            _log.error("erreur dans l'ecriture du budget : ", e);
        }
        return result;
	}
	
	private boolean saveInitiative(ResourceRequest request) throws PortletException {
		ServiceContext sc;
        Initiative initiative;
        
        try {
            sc = ServiceContextFactory.getInstance(request);
            sc.setWorkflowAction(WorkflowConstants.ACTION_SAVE_DRAFT);
            List<Long> identifiants = new ArrayList<>();
            if (this.districtId == 0) {
                List<AssetCategory> districts = AssetVocabularyHelper.getAllDistrictsFromCity(CITY_NAME);
                assert districts != null;
                identifiants = districts.stream()
                        .map(AssetCategoryModel::getCategoryId)
                        .collect(Collectors.toList());
            } else {
                identifiants.add(districtId);
            }
            if (this.projectId != 0) {
                identifiants.add(projectId);
            }
            if (this.thematicId != 0) {
                identifiants.add(thematicId);
            }
            long[] ids = new long[identifiants.size()];
            for (int i = 0; i < identifiants.size(); i++) {
                ids[i] = identifiants.get(i);
            }
            sc.setAssetCategoryIds(ids);

            initiative = InitiativeLocalServiceUtil.createInitiative(sc);
            
            initiative.setTitle(this.title);
            initiative.setDescription(this.description);
            initiative.setInTheNameOf(this.inTheNameOf);
            initiative.setPlaceTextArea(this.place);
            initiative.setVideoUrl(this.video);
            initiative.setPublikId(this.publikID);
            initiative = uploadFile(initiative, request);
            
            initiative = InitiativeLocalServiceUtil.updateInitiative(initiative, sc);
            
        } catch (PortalException | IOException e) {
            _log.error(e);
            throw new PortletException(e);
        }
        _log.info("Initiative cree : " + initiative);
        return true;
    }
	
	/**
	 * Envoi du mail de confirmation de soumission d'une initiative
	 */
    private void sendInitiativeMailConfirmation(ResourceRequest request) {
    	
    	try {
	    	ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
	    	// récupération des images
			StringBuilder hostUrl = new StringBuilder("https://");
			hostUrl.append(request.getServerName());
			StringBuilder headerImage = new StringBuilder(hostUrl)
					.append("/o/plateforme-citoyenne-theme/images/logos/mail-img-header-pcs.png");
			StringBuilder btnImage = new StringBuilder(hostUrl)
					.append("/o/plateforme-citoyenne-theme/images/logos/mail-btn-knowmore.png");
	    	
			// préparation du template de mail
			Map<String, Object> context = new HashMap<>();
			context.put("link", themeDisplay.getURLPortal() + themeDisplay.getURLCurrent());
			context.put("headerImage", headerImage.toString());
			context.put("footerImage", btnImage.toString());
			context.put("Title", this.title);
			context.put("Message", this.description);
			
		  	Configuration configuration = new Configuration(Configuration.getVersion());
			configuration.setClassForTemplateLoading(this.getClass(), "/META-INF/resources/templates/");
			configuration.setTagSyntax(Configuration.ANGLE_BRACKET_TAG_SYNTAX);
			
			Template bodyTemplate = configuration.getTemplate("contact-mail-copy-body-fr_FR.ftl");
			StringWriter bodyWriter = new StringWriter();
			bodyTemplate.process(context, bodyWriter);
			
			String subject = LanguageUtil.get(PortalUtil.getHttpServletRequest(request), "modal.submit.initiative.mail.information");
			
			InternetAddress fromAddress = new InternetAddress("no-reply@no-reply.strasbourg.eu",
					themeDisplay.getScopeGroup().getName(request.getLocale()));
			
			InternetAddress[] toAddresses = new InternetAddress[0];
			InternetAddress address = new InternetAddress(this.user.getEmail());
			toAddresses = ArrayUtil.append(toAddresses, address);
			
			// envoi du mail aux utilisateurs
			MailHelper.sendMailWithHTML(fromAddress, toAddresses, subject,
					bodyWriter.toString());
		} catch (Exception e) {
			_log.error(e);
			e.printStackTrace();
		}
    }
	
	 /**
     * Recuperer l'image uploadée par l'utilisateur.
     *
     * @param initiative Entite concernee
     * @return l'inititive avec l'imageId
     * @throws IOException
     * @throws PortalException
     */
    private Initiative uploadFile(Initiative initiative, ResourceRequest request) throws IOException, PortalException {
    	
    	// Recuperation du contexte de la requete
        ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        ServiceContext sc = ServiceContextFactory.getInstance(request);
        UploadRequest uploadRequest = PortalUtil.getUploadPortletRequest(request);
        
        // Verification du nom du fichier
        if (validateFileName(request)) {
        	
            File photo = uploadRequest.getFile(PHOTO);
            
            // Verification de la bonne recuperation du contenu du fichier
            if (photo != null && photo.exists()) {
            	
                byte[] imageBytes = FileUtil.getBytes(photo);
                
                // Dossier a la racine
                DLFolder folderparent = DLFolderLocalServiceUtil.getFolder(themeDisplay.getScopeGroupId(),
                        													DLFolderConstants.DEFAULT_PARENT_FOLDER_ID,
                        													"Initiatives");
                // Dossier d'upload de l'entite
                DLFolder folder = DLFolderLocalServiceUtil.getFolder(themeDisplay.getScopeGroupId(),
                                									folderparent.getFolderId(),
                                									"Uploads");
                // Ajout du fichier
                FileEntry fileEntry = DLAppLocalServiceUtil.addFileEntry(
                        sc.getUserId(), folder.getRepositoryId(),
                        folder.getFolderId(), photo.getName(),
                        MimeTypesUtil.getContentType(photo),
                        photo.getName(), title,
                        "", imageBytes, sc);
                // Lien de l'image a l'entite
                initiative.setImageId(fileEntry.getFileEntryId());
                
                _log.info("Photo initiative uploade : [" + photo + "]");

            }
            return initiative;
            
        } else {
            throw new PortalException("le fichier n'est pas une image");
        }
    }
	
	/**
	 * Validation du nom du fichier photo
	 * @return Valide ou pas
	 */
	private boolean validateFileName(ResourceRequest request) throws PortalException {
        boolean result = true;
        UploadRequest uploadRequest = PortalUtil.getUploadPortletRequest(request);
        String fileName = uploadRequest.getFileName(PHOTO);
        if (fileName != null && !fileName.isEmpty()) {
            String type = fileName.substring(fileName.lastIndexOf("."));
            result = type.equals(".jpg") || type.equals(".jpeg") || type.equals(".png");
        }
        return result;
    }
	
	/**
	 * Validation des champs de la requete (excpet photo)
	 * @return Valide ou pas
	 */
	private boolean validate(PortletRequest request) {
        
        // utilisateur 
        if (this.publikID == null || this.publikID.isEmpty()) {
            this.message = "Utilisateur non reconnu";
            return false;
        } else {
        	this.user = PublikUserLocalServiceUtil.getByPublikUserId(this.publikID);
        	
        	if (this.user.isBanned()) {
        		this.message = "Vous ne pouvez soumettre une initiative";
        		return false;
        	} else if (this.user.getPactSignature() == null) {
        		this.message = "Vous devez signer le Pacte pour soumettre une initiative";
        		return false;
        	}
        }

        // title
        if (Validator.isNull(this.title)) {
        	this.message = "Titre non valide";
            return false;
        }

        // description
        if (Validator.isNull(this.description)) {
        	this.message = "Description non valide";
            return false;
        }

        /** desactivation de la verification de certains champs obligatoires
        // birthday
        if (Validator.isNull(this.birthday)) {
        	this.message = "Date de naissance non valide";
            return false;
        }

        // city
        if (Validator.isNull(this.city)) {
        	this.message = "Ville non valide";
            return false;
        }

        // address
        if (Validator.isNull(this.address)) {
        	this.message = "Adresse non valide";
        	return false;
        }

        // postalcode
        if (Validator.isNull(this.postalcode)) {
        	this.message = "Code postal non valide";
            return false;
        }**/

        return true;
    }

    /**
     * Recuperation du publik ID avec la session
     */
    private String getPublikID(PortletRequest request) {
        LiferayPortletRequest liferayPortletRequest = PortalUtil.getLiferayPortletRequest(request);
        HttpServletRequest originalRequest = liferayPortletRequest.getHttpServletRequest();
        return SessionParamUtil.getString(originalRequest, "publik_internal_id");
    }

}
