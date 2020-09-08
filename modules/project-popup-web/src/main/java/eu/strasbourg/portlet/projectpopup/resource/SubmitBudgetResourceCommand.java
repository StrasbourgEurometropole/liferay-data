package eu.strasbourg.portlet.projectpopup.resource;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetCategoryModel;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.document.library.kernel.model.DLFolder;
import com.liferay.document.library.kernel.model.DLFolderConstants;
import com.liferay.document.library.kernel.service.DLAppLocalServiceUtil;
import com.liferay.document.library.kernel.service.DLAppServiceUtil;
import com.liferay.document.library.kernel.service.DLFolderLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.template.*;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.upload.UploadRequest;
import com.liferay.portal.kernel.util.*;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import eu.strasbourg.portlet.projectpopup.configuration.ProjectPopupConfiguration;
import eu.strasbourg.service.oidc.model.PublikUser;
import eu.strasbourg.service.oidc.service.PublikUserLocalServiceUtil;
import eu.strasbourg.service.project.model.BudgetParticipatif;
import eu.strasbourg.service.project.model.BudgetPhase;
import eu.strasbourg.service.project.service.BudgetParticipatifLocalServiceUtil;
import eu.strasbourg.service.project.service.BudgetPhaseLocalServiceUtil;
import eu.strasbourg.utils.AssetVocabularyHelper;
import eu.strasbourg.utils.FileEntryHelper;
import eu.strasbourg.utils.MailHelper;
import eu.strasbourg.utils.PublikApiClient;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
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
import java.util.*;
import java.util.stream.Collectors;

import static eu.strasbourg.portlet.projectpopup.ProjectPopupPortlet.CITY_NAME;
import static eu.strasbourg.portlet.projectpopup.utils.ProjectPopupUtils.getPublikID;

/**
 * @author Alexandre Quere
 * @author Romain Vernier
 * @author Anglélique Zunino Champougny
 * @author Cédric Henry
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

    /** Id paramètres de requête */
    private static final String BIRTHDAY = "birthday";
    private static final String ADDRESS = "address";
    private static final String CITY = "city";
    private static final String POSTALCODE = "postalcode";
    private static final String PHONE = "phone";
    private static final String MOBILE = "mobile";
    private static final String BUDGETTITLE = "title";
    private static final String BUDGETSUMMARY = "summary";
    private static final String SQUIREDESCRIPTION = "squiredescription";
    private static final String LIEU = "budgetLieux";
    private static final String PROJECT = "project";
    private static final String QUARTIER = "quartier";
    private static final String THEME = "theme";
    private static final String PHOTO = "budgetPhoto";
    private static final String VIDEO = "video";
    private static final String SAVEINFO = "saveinfo";
    private static final String PATTERN = "dd/MM/yyyy";

    /** Clef de language */
    private static final String ERROR_FILE_TO_LARGE = "project.popup.web.error.file.to.large";
    private static final String ERROR_UNABLE_TO_SCAN_FILE = "project.popup.web.error.unable.to.scan.file.for.viruses";
    private static final String ERROR_VIRUS_DETECTED = "project.popup.web.error.a.virus.was.detected.in.the.file";
    private static final String ERROR_DURING_FILE_SCAN = "project.popup.web.error.during.file.scan";

    /** Tampon contexte de requête */
    private ThemeDisplay themeDisplay;
    private ServiceContext sc;
    private ProjectPopupConfiguration configuration;

    /** Tampon paramètres de requête */
    private String publikID;
    private PublikUser user;
    private Date birthday;
    private String address;
    private String city;
    private long postalcode;
    private String phone;
    private String mobile;
    private String video;
    private String title;
    private String summary;
    private String squiredescription;
    private String lieu;
    private long projectId;
    private long quartierId;
    private long themeId;
    private String message;
    private File budgetPhoto;
    private String fileName;
    private String[] fileNames;
    private File[] files;

    /**
     * le log
     */
    private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());

    /** Fichier local de langue */
    private ResourceBundle languageBundle = ResourceBundleUtil.getBundle("content.Language",
            this.getClass().getClassLoader());

    @Override
    public boolean serveResource(ResourceRequest request, ResourceResponse response) throws PortletException {

        // Recuperation du contexte de la requete
        this.themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        try {
            this.sc = ServiceContextFactory.getInstance(request);
            this.configuration = this.themeDisplay.getPortletDisplay()
                    .getPortletInstanceConfiguration(ProjectPopupConfiguration.class);
        } catch (PortalException e) {
            _log.error(e);
        }
        UploadRequest uploadRequest = PortalUtil.getUploadPortletRequest(request);
        DateFormat dateFormat = new SimpleDateFormat(PATTERN);
        
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
        this.summary = HtmlUtil.stripHtml(ParamUtil.getString(request, BUDGETSUMMARY));
        this.squiredescription = ParamUtil.getString(request, SQUIREDESCRIPTION);
        this.projectId = ParamUtil.getLong(request, PROJECT);
        this.quartierId = ParamUtil.getLong(request, QUARTIER);
        this.themeId = ParamUtil.getLong(request, THEME);
        this.budgetPhoto = uploadRequest.getFile(PHOTO);
        this.fileName = uploadRequest.getFileName("budgetPhoto");
        this.files = uploadRequest.getFiles("budgetFile");
        this.fileNames = uploadRequest.getFileNames("budgetFile");
        
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
            result = sendBudget();
            
            if(result)
            	sendBPMailConfirmation(request);
        }
        
        // Retour des informations de la requete en JSON
        JSONObject jsonResponse = JSONFactoryUtil.createJSONObject();
        jsonResponse.put("result", result);
        jsonResponse.put("message", this.message);
        jsonResponse.put("savedInfo", savedInfo);

        // Recuperation de l'élément d'écriture de la réponse
        PrintWriter writer;
        try {
            writer = response.getWriter();
            writer.print(jsonResponse.toString());
        } catch (IOException e) {
            _log.error("erreur dans l'ecriture du budget : ", e);
        }
        return result;
    }

    private boolean sendBudget() throws PortletException {
        BudgetParticipatif budgetParticipatif;
        
        try {
            this.sc.setWorkflowAction(WorkflowConstants.ACTION_PUBLISH);
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
            budgetParticipatif.setSummary(this.summary);
            budgetParticipatif.setDescription(this.squiredescription);
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
            budgetParticipatif = uploadFile(budgetParticipatif);

            // Récpère la phase active (si elle existe)
            long groupId = this.sc.getThemeDisplay().getLayout().getGroupId();
            BudgetPhase budgetPhaseActive = BudgetPhaseLocalServiceUtil.getActivePhase(groupId);
            if (budgetPhaseActive != null) {
                budgetParticipatif.setBudgetPhaseId(budgetPhaseActive.getBudgetPhaseId());
                AssetCategory phaseCat = budgetPhaseActive.getPhaseCategory();

                //Recuperation des categories id déjà passés dans le service context
                ids = this.sc.getAssetCategoryIds();

                //On ajoute la catégorie "Phase du budget participatif" de la phase active au BP dans la liste existante
                List<Long> idsLong = Arrays.stream(ids).boxed().collect(Collectors.toList());
                idsLong.add(phaseCat.getCategoryId());

                //Affecte la categorie "Phase du budget participatif" de la phase active au BP
                //La categorie est ajoutee dans le service context car le BP n'est pas encore cree
                this.sc.setAssetCategoryIds(idsLong.stream().mapToLong(w -> w).toArray());
            }

            budgetParticipatif = uploadDocuments(budgetParticipatif);
            budgetParticipatif = BudgetParticipatifLocalServiceUtil.updateBudgetParticipatif(budgetParticipatif, this.sc);
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
	    	// récupération des images
			StringBuilder hostUrl = new StringBuilder("https://");
			hostUrl.append(request.getServerName());
			StringBuilder headerImage = new StringBuilder(hostUrl)
					.append("/o/plateforme-citoyenne-theme/images/logos/mail-img-header-pcs.png");
			StringBuilder btnImage = new StringBuilder(hostUrl)
					.append("/o/plateforme-citoyenne-theme/images/logos/mail-btn-knowmore.png");
	    	
			// préparation du template de mail
			Map<String, Object> context = new HashMap<>();
			context.put("link", this.themeDisplay.getURLPortal() + this.themeDisplay.getURLCurrent());
			context.put("headerImage", headerImage.toString());
			context.put("footerImage", btnImage.toString());
			context.put("Title", this.title);
			context.put("Message", this.squiredescription);

            StringWriter out = new StringWriter();

            //Chargement du template contenant le corps du mail
            TemplateResource templateResourceBody = new URLTemplateResource("0",
                    Objects.requireNonNull(this.getClass().getClassLoader()
                            .getResource("META-INF/resources/templates/contact-mail-copy-body-fr_FR.ftl")));
            Template bodyTemplate = TemplateManagerUtil.getTemplate(
                    TemplateConstants.LANG_TYPE_FTL, templateResourceBody, false);

            // Traitement du template corps
            bodyTemplate.putAll(context);
            bodyTemplate.processTemplate(out);
            String mailBody = out.toString();
			
			String subject = LanguageUtil.get(PortalUtil.getHttpServletRequest(request), "modal.submitbudget.mail.information");
			
			InternetAddress fromAddress = new InternetAddress("no-reply@no-reply.strasbourg.eu",
					themeDisplay.getScopeGroup().getName(request.getLocale()));
			
			InternetAddress[] toAddresses = new InternetAddress[0];
			InternetAddress address = new InternetAddress(this.user.getEmail());
			toAddresses = ArrayUtil.append(toAddresses, address);
			
			// envoi du mail aux utilisateurs
			MailHelper.sendMailWithHTML(fromAddress, toAddresses, subject, mailBody);
		} catch (Exception e) {
			_log.error(e);
			e.printStackTrace();
		}
    }

    /**
     * Recuperer l'image uploadée par l'utilisateur.
     *
     * @param budgetParticipatif le budget participatif correspondant.
     * @return le budgetParticipatif avec l'imageId
     */
    private BudgetParticipatif uploadFile(BudgetParticipatif budgetParticipatif) throws IOException, PortalException {

        // Verification de la bonne recuperation du contenu du fichier
        if (this.budgetPhoto != null && this.budgetPhoto.exists()) {
            byte[] imageBytes = FileUtil.getBytes(this.budgetPhoto);

            // Dossier a la racine
            DLFolder folderparent = DLFolderLocalServiceUtil.getFolder(this.themeDisplay.getScopeGroupId(),
                                                                        DLFolderConstants.DEFAULT_PARENT_FOLDER_ID,
                                                                        "budget participatif");
            // Dossier d'upload de l'entite
            DLFolder folder = DLFolderLocalServiceUtil.getFolder(this.themeDisplay.getScopeGroupId(),
                                                                folderparent.getFolderId(),
                                                                "uploads");
            // Ajout du fichier
            FileEntry fileEntry = DLAppLocalServiceUtil.addFileEntry(
                    this.sc.getUserId(), folder.getRepositoryId(),
                    folder.getFolderId(), this.budgetPhoto.getName(),
                    MimeTypesUtil.getContentType(this.budgetPhoto),
                    this.budgetPhoto.getName(), this.title,
                    "", imageBytes, this.sc);
            // Lien de l'image a l'entite
            budgetParticipatif.setImageId(fileEntry.getFileEntryId());

            _log.info("Photo budget participatif uploade : [" + this.budgetPhoto + "]");

        }
        return budgetParticipatif;
    }

    /**
     * Recuperer les fichiers uploadés par l'utilisateur.
     *
     * @param budgetParticipatif le budget participatif correspondant.
     * @return le budgetParticipatif avec les fichiers
     */
    private BudgetParticipatif uploadDocuments(BudgetParticipatif budgetParticipatif) throws IOException, PortalException {

        String filesIds = "";

        int numFile = 0;
        for (File file : this.files) {

            // Verification de la bonne recuperation du contenu du fichier
            if (file != null && file.exists()) {
                byte[] imageBytes = FileUtil.getBytes(file);

                // Dossier a la racine
                DLFolder folderParent = DLFolderLocalServiceUtil.getFolder(this.themeDisplay.getScopeGroupId(),
                        DLFolderConstants.DEFAULT_PARENT_FOLDER_ID,
                        "budget participatif");
                // Dossier d'upload de l'entite
                DLFolder folderUpload = DLFolderLocalServiceUtil.getFolder(this.themeDisplay.getScopeGroupId(),
                        folderParent.getFolderId(),
                        "uploads");

                // Dossier nom de la phase
                long repositoryId = DLFolderConstants.getDataRepositoryId(this.themeDisplay.getScopeGroupId(), DLFolderConstants.DEFAULT_PARENT_FOLDER_ID);
                Folder folderPhase;
                try {
                    folderPhase = DLAppServiceUtil.getFolder(repositoryId,
                            folderUpload.getFolderId(),
                            budgetParticipatif.getPhase().getTitle());
                }catch(Exception e) {
                    folderPhase = DLAppLocalServiceUtil.addFolder(
                            this.sc.getUserId(), repositoryId,
                            folderUpload.getFolderId(),
                            budgetParticipatif.getPhase().getTitle(),
                            "", this.sc);
                }

                // Dossier d'upload de l'entite (nom du projet)
                Folder folder;
                try {
                    folder = DLAppServiceUtil.getFolder(repositoryId,
                            folderPhase.getFolderId(),
                            budgetParticipatif.getTitle());
                }catch(Exception e) {
                    folder = DLAppLocalServiceUtil.addFolder(
                            this.sc.getUserId(), repositoryId,
                            folderPhase.getFolderId(), budgetParticipatif.getTitle(),
                            "", this.sc);
                }

                //récupère le nom du fichier envoyé
                String name = this.fileNames[numFile];

                // Ajout du fichier
                FileEntry fileEntry;
                try {
                    fileEntry = DLAppLocalServiceUtil.addFileEntry(
                            this.sc.getUserId(), folder.getRepositoryId(),
                            folder.getFolderId(), name,
                            MimeTypesUtil.getContentType(file),
                            name, title,
                            "", imageBytes, this.sc);
                }catch(Exception e) {
                    fileEntry = DLAppLocalServiceUtil.getFileEntry(
                            themeDisplay.getScopeGroupId(), folder.getFolderId(), name);
                }
                // Lien de l'image a l'entite
                if(Validator.isNotNull(filesIds)){
                    filesIds += ",";
                }
                filesIds += fileEntry.getFileEntryId();

                _log.info("Document budget participatif uploade : [" + file + "]");

            }
            numFile++;
        }
        budgetParticipatif.setFilesIds(filesIds);
        return budgetParticipatif;
    }

    private boolean validateFileName() {
        boolean result = true;

        if (this.fileName != null && !this.fileName.isEmpty()) {
            String type = this.fileName.substring(this.fileName.lastIndexOf(".")).toLowerCase();
            result = type.equals(".jpg") || type.equals(".jpeg") || type.equals(".png");
        }
        return result;
    }

    private boolean validateNbFiles()  {
        boolean result = true;
        if (this.files.length > 0) {
            long nbFileMax = !this.configuration.nbFiles().equals("") ? Integer.parseInt(this.configuration.nbFiles()) : 3;
            if (this.files.length > nbFileMax) {
                result = false;
            }
        }
        return result;
    }

    private boolean validateFileExtensions() {
        boolean result = true;
        String[] typesFiles = this.configuration.typesFiles().split(",");
        for (String fileName : this.fileNames) {
            if (fileName != null && !fileName.isEmpty()) {
                String type = fileName.substring(fileName.lastIndexOf(".") + 1);
                if (!Arrays.stream(typesFiles).anyMatch(type.toLowerCase()::equals)) {
                    result = false;
                    break;
                }
            }
        }
        return result;
    }

    private boolean validateFileSizes()  {
        boolean result = true;
        long fileSizeMax = !this.configuration.sizeFile().equals("") ? Integer.parseInt(this.configuration.sizeFile()) : 3;
        for (File file : this.files) {
            if (file != null) {
                long fileSize = file.length() / (1024 * 1024);
                if(fileSize > fileSizeMax){
                    result = false;
                    break;
                }
            }
        }
        return result;
    }

    private boolean antiVirusVerif() {
        boolean result = true;
        for (File file : this.files) {
            if (file != null) {
                String error = FileEntryHelper.scanFile(file);
                if (Validator.isNotNull(error)) {
                    switch (error){
                        case "unable-to-scan-file-for-viruses":
                            this.message = LanguageUtil.get(languageBundle, ERROR_UNABLE_TO_SCAN_FILE);
                            return true;
                            //break;
                        case "a-virus-was-detected-in-the-file":
                            this.message = LanguageUtil.get(languageBundle, ERROR_VIRUS_DETECTED);
                            result = false;
                            break;
                        default:
                            this.message = LanguageUtil.get(languageBundle, ERROR_DURING_FILE_SCAN);
                            result = false;
                            break;
                    }
                    break;
                }
            }
        }
        return result;
    }

    private boolean validate(ResourceRequest request) {
        
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
        BudgetPhase activePhase = BudgetPhaseLocalServiceUtil.getActivePhase(this.themeDisplay.getScopeGroupId());
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
        
        // resume
        if (Validator.isNull(this.summary)) {
        	this.message = "Resume non valide";
            return false;
        }

        // description
        if (Validator.isNull(HtmlUtil.stripHtml(this.squiredescription))) {
        	this.message = "Description non valide";
            return false;
        }

        // quartier
        if (Validator.isNull(this.quartierId)) {
            this.message = "Quartier non valide";
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

        // Photo
        if (!validateFileName()) {
            this.message = "Nom du fichier de l'image non valide";
            return false;
        }

        // Documents
        if (!validateNbFiles()) {
            this.message = "Trop de fichiers";
            return false;
        } else {
            if (!validateFileExtensions()) {
                this.message = "Extension(s) de fichier(s) non valide(s)";
                return false;
            } else {
                if (!validateFileSizes()) {
                    this.message = LanguageUtil.get(languageBundle, ERROR_FILE_TO_LARGE)
                            + ParamUtil.getLong(request, "sizeFile") + "Mo)";
                    return false;
                } else if (!antiVirusVerif()) {
                    return false;
                }
            }
        }

        return true;
    }

}