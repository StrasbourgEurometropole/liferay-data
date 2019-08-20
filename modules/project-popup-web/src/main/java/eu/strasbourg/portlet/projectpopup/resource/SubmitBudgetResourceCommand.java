package eu.strasbourg.portlet.projectpopup.resource;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetCategoryModel;
import com.liferay.asset.kernel.model.AssetEntry;
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
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import eu.strasbourg.service.oidc.model.PublikUser;
import eu.strasbourg.service.oidc.service.PublikUserLocalServiceUtil;
import eu.strasbourg.service.project.model.BudgetParticipatif;
import eu.strasbourg.service.project.model.BudgetPhase;
import eu.strasbourg.service.project.service.BudgetParticipatifLocalServiceUtil;
import eu.strasbourg.service.project.service.BudgetPhaseLocalServiceUtil;
import eu.strasbourg.utils.AssetVocabularyHelper;
import eu.strasbourg.utils.MailHelper;
import eu.strasbourg.utils.PublikApiClient;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import freemarker.template.Configuration;
import freemarker.template.Template;

import org.osgi.service.component.annotations.Component;

import javax.mail.internet.InternetAddress;
import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
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

import static eu.strasbourg.portlet.projectpopup.ProjectPopupPortlet.CITY_NAME;
import static eu.strasbourg.portlet.projectpopup.utils.ProjectPopupUtils.getPublikID;

/**
 * @author alexandre.quere
 */
@Component(
        immediate = true,
        property = {
                "javax.portlet.name=" + StrasbourgPortletKeys.PROJECT_POPUP_WEB,
                "mvc.command.name=submitBudget"
        },
        service = MVCResourceCommand.class
)
public class SubmitBudgetResourceCommand implements MVCResourceCommand {

    private static final String BIRTHDAY = "birthday";
    private static final String ADDRESS = "address";
    private static final String CITY = "city";
    private static final String POSTALCODE = "postalcode";
    private static final String PHONE = "phone";
    private static final String MOBILE = "mobile";
    private static final String BUDGETTITLE = "title";
    private static final String BUDGETDESCRIPTION = "description";
    private static final String LIEU = "budgetLieux";
    private static final String PROJECT = "project";
    private static final String QUARTIER = "quartier";
    private static final String THEME = "theme";
    private static final String PHOTO = "budgetPhoto";
    private static final String VIDEO = "video";
    private static final String SAVEINFO = "saveinfo";
    private static final String PATTERN = "dd/MM/yyyy";

    private String publikID;
    private PublikUser user;
    private DateFormat dateFormat;
    private Date birthday;
    private String address;
    private String city;
    private long postalcode;
    private String phone;
    private String mobile;
    private String video;
    private String title;
    private String description;
    private String lieu;
    private long projectId;
    private long quartierId;
    private long themeId;
    private String message;

    /**
     * le log
     */
    private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());

    @Override
    public boolean serveResource(ResourceRequest request, ResourceResponse response) throws PortletException {
    	
        this.dateFormat = new SimpleDateFormat(PATTERN);
        
        // Initialisations respectives de : resultat probant de la requete, sauvegarde ou non des informations Publik, message de retour
        boolean result = false;
        boolean savedInfo = false;
        this.message = "";
        
        // Recuperation de l'utilsiteur Publik ayant lance la demande
        this.publikID = getPublikID(request);
        
        // Recuperation des informations du formulaire
        this.address = HtmlUtil.stripHtml(ParamUtil.getString(request, ADDRESS));
        this.city = HtmlUtil.stripHtml(ParamUtil.getString(request, CITY));
        this.postalcode = ParamUtil.getLong(request, POSTALCODE);
        this.phone = HtmlUtil.stripHtml(ParamUtil.getString(request, PHONE));
        this.mobile = HtmlUtil.stripHtml(ParamUtil.getString(request, MOBILE));
        this.birthday = ParamUtil.getDate(request, BIRTHDAY, dateFormat);
        this.lieu = HtmlUtil.stripHtml(ParamUtil.getString(request, LIEU));
        this.video = HtmlUtil.stripHtml(ParamUtil.getString(request, VIDEO));
        this.title = HtmlUtil.stripHtml(ParamUtil.getString(request, BUDGETTITLE));
        this.description = HtmlUtil.stripHtml(ParamUtil.getString(request, BUDGETDESCRIPTION));
        this.projectId = ParamUtil.getLong(request, PROJECT);
        this.quartierId = ParamUtil.getLong(request, QUARTIER);
        this.themeId = ParamUtil.getLong(request, THEME);
        
        // Verification de la validite des informations
        if (validate(request)) {
        
        	// Mise a jour des informations du compte Publik si requete valide et demande par l'utilisateur
        	savedInfo = ParamUtil.getBoolean(request, SAVEINFO);
            if (savedInfo) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String dateNaiss = sdf.format(this.birthday);
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
            result = sendBudget(request);
            
            if(result)
            	sendBPMailConfirmation(request);
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

    private boolean sendBudget(ResourceRequest request) throws PortletException {
        ServiceContext sc;
        BudgetParticipatif budgetParticipatif;
        
        try {
            sc = ServiceContextFactory.getInstance(request);
            sc.setWorkflowAction(WorkflowConstants.ACTION_SAVE_DRAFT);
            List<Long> identifiants = new ArrayList<>();
            if (this.quartierId == 0) {
                List<AssetCategory> districts = AssetVocabularyHelper.getAllDistrictsFromCity(CITY_NAME);
                assert districts != null;
                identifiants = districts.stream()
                        .map(AssetCategoryModel::getCategoryId)
                        .collect(Collectors.toList());
            } else {
                identifiants.add(quartierId);
            }
            if (this.projectId != 0) {
                identifiants.add(projectId);
            }
            if (this.themeId != 0) {
                identifiants.add(themeId);
            }
            long[] ids = new long[identifiants.size()];
            for (int i = 0; i < identifiants.size(); i++) {
                ids[i] = identifiants.get(i);
            }
            sc.setAssetCategoryIds(ids);

            budgetParticipatif = BudgetParticipatifLocalServiceUtil.createBudgetParticipatif(sc);
            budgetParticipatif.setTitle(this.title);
            budgetParticipatif.setDescription(this.description);
            budgetParticipatif.setCitoyenFirstname(this.user.getFirstName());
            budgetParticipatif.setCitoyenLastname(this.user.getLastName());
            budgetParticipatif.setCitoyenAdresse(this.address);
            budgetParticipatif.setCitoyenPostalCode(this.postalcode);
            budgetParticipatif.setCitoyenCity(this.city);
            budgetParticipatif.setCitoyenEmail(this.user.getEmail());
            budgetParticipatif.setCitoyenMobile(this.mobile);
            budgetParticipatif.setCitoyenBirthday(this.birthday);
            if (!this.video.isEmpty())
                budgetParticipatif.setVideoUrl(this.video);
            budgetParticipatif.setPlaceTextArea(this.lieu);
            budgetParticipatif.setCitoyenPhone(this.phone);
            budgetParticipatif.setPublikId(this.publikID);
            budgetParticipatif = uploadFile(budgetParticipatif, request);
            budgetParticipatif = BudgetParticipatifLocalServiceUtil.updateBudgetParticipatif(budgetParticipatif, sc);
            AssetEntry assetEntry = budgetParticipatif.getAssetEntry();
            if (assetEntry == null)
                throw new PortalException("aucune assetCategory pour le budget"
                        + budgetParticipatif.getBudgetParticipatifId());
        } catch (PortalException | IOException e) {
            _log.error(e);
            throw new PortletException(e);
        }
        _log.info("budget cree : " + budgetParticipatif);
        return true;
    }
    
    
    /**
	 * Envoi du mail de confirmation de soumission du budget participatif
	 */
    private void sendBPMailConfirmation(ResourceRequest request) {
    	
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
			
			String subject = LanguageUtil.get(PortalUtil.getHttpServletRequest(request), "modal.submitBudget.mail.information");
			
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
     * @param budgetParticipatif le budget participatif correspondant.
     * @param request            la request
     * @return le budgetParticipatif avec l'imageId
     * @throws IOException
     * @throws PortalException
     */
    private BudgetParticipatif uploadFile(BudgetParticipatif budgetParticipatif, ResourceRequest request) throws IOException, PortalException {
    	
    	// Recuperation du contexte de la requete
        ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        ServiceContext sc = ServiceContextFactory.getInstance(request);
        UploadRequest uploadRequest = PortalUtil.getUploadPortletRequest(request);
        
        // Verification du nom du fichier
        if (validateFileName(request)) {
        	
            File budgetPhoto = uploadRequest.getFile(PHOTO);
            
            // Verification de la bonne recuperation du contenu du fichier
            if (budgetPhoto != null && budgetPhoto.exists()) {
                byte[] imageBytes = FileUtil.getBytes(budgetPhoto);
                
                // Dossier a la racine
                DLFolder folderparent = DLFolderLocalServiceUtil.getFolder(themeDisplay.getScopeGroupId(),
                        													DLFolderConstants.DEFAULT_PARENT_FOLDER_ID,
                        													"budget participatif");
                // Dossier d'upload de l'entite
                DLFolder folder = DLFolderLocalServiceUtil.getFolder(themeDisplay.getScopeGroupId(),
                                									folderparent.getFolderId(),
                                									"uploads");
                // Ajout du fichier
                FileEntry fileEntry = DLAppLocalServiceUtil.addFileEntry(
                        sc.getUserId(), folder.getRepositoryId(),
                        folder.getFolderId(), budgetPhoto.getName(),
                        MimeTypesUtil.getContentType(budgetPhoto),
                        budgetPhoto.getName(), title,
                        "", imageBytes, sc);
                // Lien de l'image a l'entite
                budgetParticipatif.setImageId(fileEntry.getFileEntryId());
                
                _log.info("Photo budget participatif uploade : [" + budgetPhoto + "]");

            }
            return budgetParticipatif;
        } else {
            throw new PortalException("le fichier n'est pas une image");
        }
    }

    private boolean validateFileName(ResourceRequest request) throws PortalException {
        boolean result = true;
        UploadRequest uploadRequest = PortalUtil.getUploadPortletRequest(request);
        String fileName = uploadRequest.getFileName("budgetPhoto");
        if (fileName != null && !fileName.isEmpty()) {
            String type = fileName.substring(fileName.lastIndexOf("."));
            result = type.equals(".jpg") || type.equals(".jpeg") || type.equals(".png");
        }
        return result;
    }

    private boolean validate(ResourceRequest request) {
    	
    	ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        
        // utilisateur 
        if (this.publikID == null || this.publikID.isEmpty()) {
            this.message = "Utilisateur non reconnu";
            return false;
        } else {
        	this.user = PublikUserLocalServiceUtil.getByPublikUserId(this.publikID);
        	
        	if (this.user.isBanned()) {
        		this.message = "Vous ne pouvez soutenir ce projet";
        		return false;
        	} else if (this.user.getPactSignature() == null) {
        		this.message = "Vous devez signer le Pacte pour soumettre un projet";
        		return false;
        	}
        }
        
        // Phase
        BudgetPhase activePhase = BudgetPhaseLocalServiceUtil.getActivePhase(themeDisplay.getScopeGroupId());
        if (activePhase != null) {
        	if (!activePhase.isInDepositPeriod()) {
        		this.message = "Nous ne sommes pas en phase de depot";
                return false;
        	}
        } else {
        	this.message = "Nous ne sommes pas en phase de depot";
            return false;
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
        }

        try {
			if (!validateFileName(request)) {
				this.message = "Nom du fichier de l'image non valide";
			    return false;
			}
		} catch (PortalException e) {
			_log.error(e);
			this.message = "Erreur lors de la lecture de du fichier de l'image";
		    return false;
		}

        return true;
    }

}
