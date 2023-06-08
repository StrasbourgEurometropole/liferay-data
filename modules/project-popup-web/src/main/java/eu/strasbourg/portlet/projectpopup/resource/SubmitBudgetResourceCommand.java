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
import com.liferay.portal.kernel.template.Template;
import com.liferay.portal.kernel.template.TemplateConstants;
import com.liferay.portal.kernel.template.TemplateManagerUtil;
import com.liferay.portal.kernel.template.TemplateResource;
import com.liferay.portal.kernel.template.URLTemplateResource;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.upload.UploadRequest;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.MimeTypesUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.ResourceBundleUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
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
import eu.strasbourg.utils.StrasbourgPropsUtil;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import org.osgi.service.component.annotations.Component;

import javax.mail.internet.InternetAddress;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.ResourceBundle;
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
    private static final String VIDEO = "video";
    private static final String SAVEINFO = "saveinfo";
    private static final String PATTERN = "dd/MM/yyyy";

    /** Clef de language */
    private static final String ERROR_FILE_TO_LARGE = "project.popup.web.error.file.to.large";
    private static final String ERROR_UNABLE_TO_SCAN_FILE = "project.popup.web.error.unable.to.scan.file.for.viruses";
    private static final String ERROR_VIRUS_DETECTED = "project.popup.web.error.a.virus.was.detected.in.the.file";
    private static final String ERROR_DURING_SAVING_PROJECT = "project.popup.web.error.while.project.saving";

    /** le log */
    private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());

    /** Fichier local de langue */
    private ResourceBundle languageBundle = ResourceBundleUtil.getBundle("content.Language",
            this.getClass().getClassLoader());

    @Override
    public boolean serveResource(ResourceRequest request, ResourceResponse response) {
        // Recuperation du contexte de la requete
        ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        ServiceContext sc = null;
        ProjectPopupConfiguration configuration = null;
        try {
            sc = ServiceContextFactory.getInstance(request);
            configuration = themeDisplay.getPortletDisplay()
                    .getPortletInstanceConfiguration(ProjectPopupConfiguration.class);
        } catch (PortalException e) {
            _log.error(e);
        }
        DateFormat dateFormat = new SimpleDateFormat(PATTERN);
        
        // Initialisations respectives de : resultat probant de la requete, sauvegarde ou non des informations Publik, message de retour
        boolean result = false;
        boolean savedInfo = false;
        
        // Recuperation de l'utilsiteur Publik ayant lance la demande
        PublikUser user = null;
        String publikID = getPublikID(request);
        if (publikID != null && !publikID.isEmpty()) {
            user = PublikUserLocalServiceUtil.getByPublikUserId(publikID);
        }

        // Recuperation la phase active
        BudgetPhase activePhase = BudgetPhaseLocalServiceUtil.getActivePhase(themeDisplay.getScopeGroupId());
        
        // Recuperation des informations du formulaire
        String title = HtmlUtil.stripHtml(ParamUtil.getString(request, BUDGETTITLE));
        String summary = HtmlUtil.stripHtml(ParamUtil.getString(request, BUDGETSUMMARY));
        String squiredescription = ParamUtil.getString(request, SQUIREDESCRIPTION);
        String address = HtmlUtil.stripHtml(ParamUtil.getString(request, ADDRESS));
        long postalcode = ParamUtil.getLong(request, POSTALCODE);
        String city = HtmlUtil.stripHtml(ParamUtil.getString(request, CITY));
        String mobile = HtmlUtil.stripHtml(ParamUtil.getString(request, MOBILE));
        Date birthday = ParamUtil.getDate(request, BIRTHDAY, dateFormat);
        String video = HtmlUtil.stripHtml(ParamUtil.getString(request, VIDEO));
        String lieu = HtmlUtil.stripHtml(ParamUtil.getString(request, LIEU));
        String phone = HtmlUtil.stripHtml(ParamUtil.getString(request, PHONE));
        long projectId = ParamUtil.getLong(request, PROJECT);
        long quartierId = ParamUtil.getLong(request, QUARTIER);
        long themeId = ParamUtil.getLong(request, THEME);
        UploadRequest uploadRequest = PortalUtil.getUploadPortletRequest(request);
        String photoFileName = uploadRequest.getFileName("budgetPhoto");
        File photoFile = uploadRequest.getFile("budgetPhoto");
        File[] documentFiles = uploadRequest.getFiles("budgetFile");
        String[] documentsFileNames = uploadRequest.getFileNames("budgetFile");
        
        // Verification de la validite des informations
        String message = validate(request, configuration, publikID, user,  activePhase, title, summary,
                squiredescription, city, address, postalcode, quartierId, photoFileName, photoFile, documentFiles,
                documentsFileNames);
        if (message.equals("")) {
        
        	// Mise a jour des informations du compte Publik si requete valide et demande par l'utilisateur
        	savedInfo = ParamUtil.getBoolean(request, SAVEINFO);
            if (savedInfo) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String dateNaiss = sdf.format(birthday);
                PublikApiClient.setAllUserDetails(
                        publikID,
                        user != null ? user.getLastName() : null,
                        address,
                        "" + postalcode,
                        city,
                        dateNaiss,
                        phone,
                        mobile
                );
            }

            List<Long> identifiants = new ArrayList<>();
            if (quartierId == 0) {
                List<AssetCategory> districts = AssetVocabularyHelper.getAllDistrictsFromCity(CITY_NAME);
                assert districts != null;
                identifiants = districts.stream()
                        .map(AssetCategoryModel::getCategoryId)
                        .collect(Collectors.toList());
            } else {
                identifiants.add(quartierId);
            }
            if (projectId != 0) {
                identifiants.add(projectId);
            }
            if (themeId != 0) {
                identifiants.add(themeId);
            }

            long[] ids = new long[identifiants.size()];
            for (int i = 0; i < identifiants.size(); i++) {
                ids[i] = identifiants.get(i);
            }

            if (sc != null) {
                sc.setWorkflowAction(WorkflowConstants.ACTION_PUBLISH);
                sc.setAssetCategoryIds(ids);
            }

            // Création du budget participatif
            BudgetParticipatif budgetParticipatif = null;
            try {
                budgetParticipatif = BudgetParticipatifLocalServiceUtil.createBudgetParticipatif(sc);
                budgetParticipatif.setTitle(title);
                budgetParticipatif.setSummary(summary);
                budgetParticipatif.setDescription(squiredescription);
                budgetParticipatif.setCitoyenFirstname(user != null ? user.getFirstName() : null);
                budgetParticipatif.setCitoyenLastname(user != null ? user.getLastName() : null);
                budgetParticipatif.setCitoyenAdresse(address);
                budgetParticipatif.setCitoyenPostalCode(postalcode);
                budgetParticipatif.setCitoyenCity(city);
                budgetParticipatif.setCitoyenEmail(user != null ? user.getEmail() : null);
                budgetParticipatif.setCitoyenMobile(mobile);
                budgetParticipatif.setCitoyenBirthday(birthday);
                if (!video.isEmpty())
                    budgetParticipatif.setVideoUrl(video);
                budgetParticipatif.setPlaceTextArea(lieu);
                budgetParticipatif.setCitoyenPhone(phone);
                budgetParticipatif.setPublikId(publikID);
                budgetParticipatif = uploadFile(photoFile, themeDisplay, sc, budgetParticipatif);

                // Récpère la phase active (si elle existe)
                long groupId;
                if (sc != null) {
                    groupId = sc.getThemeDisplay().getLayout().getGroupId();
                    BudgetPhase budgetPhaseActive = BudgetPhaseLocalServiceUtil.getActivePhase(groupId);
                    if (budgetPhaseActive != null) {
                        budgetParticipatif.setBudgetPhaseId(budgetPhaseActive.getBudgetPhaseId());
                        AssetCategory phaseCat = budgetPhaseActive.getPhaseCategory();

                        //Recuperation des categories id déjà passés dans le service context
                        ids = sc.getAssetCategoryIds();

                        //On ajoute la catégorie "Phase du budget participatif" de la phase active au BP dans la liste existante
                        List<Long> idsLong = Arrays.stream(ids).boxed().collect(Collectors.toList());
                        idsLong.add(phaseCat.getCategoryId());

                        //Affecte la categorie "Phase du budget participatif" de la phase active au BP
                        //La categorie est ajoutee dans le service context car le BP n'est pas encore cree
                        sc.setAssetCategoryIds(idsLong.stream().mapToLong(w -> w).toArray());
                    }
                }

                budgetParticipatif = uploadDocuments(documentFiles, themeDisplay, sc, budgetParticipatif, documentsFileNames);
                budgetParticipatif = BudgetParticipatifLocalServiceUtil.updateBudgetParticipatif(budgetParticipatif, sc);
                AssetEntry assetEntry = budgetParticipatif.getAssetEntry();
                if (assetEntry == null)
                    throw new PortalException("aucune assetCategory pour le budget"
                            + budgetParticipatif.getBudgetParticipatifId());
            } catch (PortalException | IOException e) {
                _log.error(e);
                message = LanguageUtil.get(languageBundle, ERROR_DURING_SAVING_PROJECT);
            }
            _log.info("budget cree : " + budgetParticipatif);
            
            if(message.equals("")) {
                result = true;
                sendBPMailConfirmation(request, themeDisplay, title, squiredescription, user);
            }
        }else if(message.equals("error"))
            message = "";
        
        // Retour des informations de la requete en JSON
        JSONObject jsonResponse = JSONFactoryUtil.createJSONObject();
        jsonResponse.put("result", result);
        jsonResponse.put("message", message);
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
    
    /**
	 * Envoi du mail de confirmation de soumission du budget participatif
	 */
    private void sendBPMailConfirmation(ResourceRequest request, ThemeDisplay themeDisplay, String title,
                                        String description, PublikUser user) {
    	
    	try {
	    	// récupération des images
			StringBuilder hostUrl = new StringBuilder("https://");
			hostUrl.append(request.getServerName());
			String headerImage = hostUrl +
                    "/o/plateforme-citoyenne-theme/images/logos/mail-img-header-pcs.png";
			String btnImage = hostUrl +
					"/o/plateforme-citoyenne-theme/images/logos/mail-btn-knowmore.png";
	    	
			// préparation du template de mail
			Map<String, Object> context = new HashMap<>();
			context.put("link", themeDisplay.getURLPortal() + themeDisplay.getURLCurrent());
			context.put("headerImage", headerImage);
			context.put("footerImage", btnImage);
			context.put("Title", title);
			context.put("Message", description);

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
			InternetAddress address = new InternetAddress(user.getEmail());
			toAddresses = ArrayUtil.append(toAddresses, address);
			
			// envoi du mail aux utilisateurs
			MailHelper.sendMailWithHTML(fromAddress, toAddresses, subject, mailBody);
		} catch (Exception e) {
            _log.error(e.getMessage(), e);
		}
    }

    /**
     * Recuperer l'image uploadée par l'utilisateur.
     *
     * @param budgetParticipatif le budget participatif correspondant.
     * @return le budgetParticipatif avec l'imageId
     */
    private BudgetParticipatif uploadFile(File photoFile, ThemeDisplay themeDisplay, ServiceContext sc,
                      BudgetParticipatif budgetParticipatif) throws IOException, PortalException {

        // Verification de la bonne recuperation du contenu du fichier
        if (photoFile != null && photoFile.exists()) {
            byte[] imageBytes = FileUtil.getBytes(photoFile);

            // Dossier a la racine
            DLFolder folderparent = DLFolderLocalServiceUtil.getFolder(themeDisplay.getScopeGroupId(),
                                                                        DLFolderConstants.DEFAULT_PARENT_FOLDER_ID,
                                                                        "budget participatif");
            // Dossier d'upload de l'entite
            DLFolder folder = DLFolderLocalServiceUtil.getFolder(themeDisplay.getScopeGroupId(),
                                                                folderparent.getFolderId(),
                                                                "uploads");
            // TODO Remove this after debug
            FileEntryHelper.logFileInfo(photoFile);

            // Ajout du fichier
            FileEntry fileEntry = DLAppLocalServiceUtil.addFileEntry(
                    sc.getUserId(), folder.getRepositoryId(),
                    folder.getFolderId(), photoFile.getName(),
                    MimeTypesUtil.getContentType(photoFile),
                    photoFile.getName(), budgetParticipatif.getTitle(),
                    "", imageBytes, sc);

            // Lien de l'image a l'entite
            budgetParticipatif.setImageId(fileEntry.getFileEntryId());

            _log.info("Photo budget participatif uploade : [" + photoFile + "]");

        }
        return budgetParticipatif;
    }

    /**
     * Recuperer les fichiers uploadés par l'utilisateur.
     *
     * @param budgetParticipatif le budget participatif correspondant.
     * @return le budgetParticipatif avec les fichiers
     */
    private BudgetParticipatif uploadDocuments(File[] documentFiles, ThemeDisplay themeDisplay,
                       ServiceContext sc, BudgetParticipatif budgetParticipatif, String[] documentsFileNames
    ) throws IOException, PortalException {

        String filesIds = "";

        int numFile = 0;
        for (File file : documentFiles) {

            // Verification de la bonne recuperation du contenu du fichier
            if (file != null && file.exists()) {
                byte[] imageBytes = FileUtil.getBytes(file);

                // Dossier a la racine
                DLFolder folderParent = DLFolderLocalServiceUtil.getFolder(themeDisplay.getScopeGroupId(),
                        DLFolderConstants.DEFAULT_PARENT_FOLDER_ID,
                        "budget participatif");
                // Dossier d'upload de l'entite
                DLFolder folderUpload = DLFolderLocalServiceUtil.getFolder(themeDisplay.getScopeGroupId(),
                        folderParent.getFolderId(),
                        "uploads");

                // Dossier nom de la phase
                long repositoryId = DLFolderConstants.getDataRepositoryId(themeDisplay.getScopeGroupId(), DLFolderConstants.DEFAULT_PARENT_FOLDER_ID);
                Folder folderPhase;
                try {
                    folderPhase = DLAppServiceUtil.getFolder(repositoryId,
                            folderUpload.getFolderId(),
                            budgetParticipatif.getPhase().getTitle());
                }catch(Exception e) {
                    folderPhase = DLAppLocalServiceUtil.addFolder(
                            sc.getUserId(), repositoryId,
                            folderUpload.getFolderId(),
                            budgetParticipatif.getPhase().getTitle(),
                            "", sc);
                }

                // Dossier d'upload de l'entite (nom du projet)
                Folder folder;
                try {
                    folder = DLAppServiceUtil.getFolder(repositoryId,
                            folderPhase.getFolderId(),
                            budgetParticipatif.getTitle());
                }catch(Exception e) {
                    folder = DLAppLocalServiceUtil.addFolder(
                            sc.getUserId(), repositoryId,
                            folderPhase.getFolderId(), budgetParticipatif.getTitle(),
                            "", sc);
                }

                //récupère le nom du fichier envoyé
                String name = documentsFileNames[numFile];

                // Ajout du fichier
                FileEntry fileEntry;
                try {
                    // TODO Remove this after debug
                    FileEntryHelper.logFileInfo(file);

                    fileEntry = DLAppLocalServiceUtil.addFileEntry(
                            sc.getUserId(), folder.getRepositoryId(),
                            folder.getFolderId(), name,
                            MimeTypesUtil.getContentType(file),
                            name, budgetParticipatif.getTitle(),
                            "", imageBytes, sc);
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

    private boolean validateFileName(String fileName) {
        boolean result = true;

        if (fileName != null && !fileName.isEmpty()) {
            String type = fileName.substring(fileName.lastIndexOf(".")).toLowerCase();
            result = type.equals(".jpg") || type.equals(".jpeg") || type.equals(".png");
        }
        return result;
    }

    private boolean validateNbFiles(ProjectPopupConfiguration configuration, File[] files)  {
        boolean result = true;
        if (files.length > 0) {
            long nbFileMax = !configuration.nbFiles().equals("") ? Integer.parseInt(configuration.nbFiles()) : 3;
            if (files.length > nbFileMax) {
                result = false;
            }
        }
        return result;
    }

    private boolean validateFileExtensions(ProjectPopupConfiguration configuration, String[] fileNames) {
        boolean result = true;
        String[] typesFiles = configuration.typesFiles().split(",");
        for (String fileName : fileNames) {
            if (fileName != null && !fileName.isEmpty()) {
                String type = fileName.substring(fileName.lastIndexOf(".") + 1);
                if (!Arrays.asList(typesFiles).contains(type.toLowerCase())) {
                    result = false;
                    break;
                }
            }
        }
        return result;
    }

    private boolean validateFileSizes(ProjectPopupConfiguration configuration, File[] documentFiles)  {
        boolean result = true;
        long fileSizeMax = !configuration.sizeFile().equals("") ? Integer.parseInt(configuration.sizeFile()) : 3;
        for (File file : documentFiles) {
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

    private String antiVirusVerif(File[] files) {
        String result = "";
        if (StrasbourgPropsUtil.getParticiperAntivirusActivation()) {
            for (File file : files) {
                if (file != null) {
                    String error = FileEntryHelper.scanFile(file);
                    if (Validator.isNotNull(error)) {
                        switch (error) {
                            case "a-virus-was-detected-in-the-file":
                                result =  LanguageUtil.get(languageBundle, ERROR_VIRUS_DETECTED);
                                break;
                            case "unable-to-scan-file-for-viruses":
                            default:
                                result = LanguageUtil.get(languageBundle, ERROR_UNABLE_TO_SCAN_FILE);
                                break;
                        }
                        break;
                    }
                }
            }
        }
        return result;
    }

    private String validate(ResourceRequest request, ProjectPopupConfiguration configuration, String publikID,
                            PublikUser user, BudgetPhase activePhase, String title, String summary, String description,
                            String city, String address, long postalcode, long quartierId, String photoFileName,
                            File photoFile, File[] documentFiles, String[] documentsFileNames) {
        // utilisateur
        if (publikID == null || publikID.isEmpty()) {
            return "Utilisateur non reconnu";
        } else {
        	if (user.isBanned()) {
                return "Vous ne pouvez soutenir ce projet";
        	} else if (user.getPactSignature() == null) {
                return "Vous devez signer le Pacte pour soumettre un projet";
        	}
        }
        
        // Phase
        if (activePhase != null) {
        	if (!activePhase.isInDepositPeriod()) {
                return "Nous ne sommes pas en phase de depot";
        	}
        } else {
            return "Nous ne sommes pas en phase de depot";
        }
        
        // title
        if (Validator.isNull(title)) {
            return "Titre non valide";
        }
        
        // resume
        if (Validator.isNull(summary)) {
            return "Resume non valide";
        }

        // description
        if (Validator.isNull(HtmlUtil.stripHtml(description))) {
            return "Description non valide";
        }

        // quartier
        if (Validator.isNull(quartierId)) {
            return "Quartier non valide";
        }

        // city
        if (Validator.isNull(city)) {
            return "Ville non valide";
        }

        // address
        if (Validator.isNull(address)) {
            return "Adresse non valide";
        }

        // postalcode
        if (Validator.isNull(postalcode)) {
            return "Code postal non valide";
        }

        // Photo
        if (!validateFileName(photoFileName)) {
            return "Nom du fichier de l'image non valide";
        }
        String message = antiVirusVerif(new File[]{photoFile});
        if (!message.equals("")) {
            return message;
        }

        // Documents
        if (!validateNbFiles(configuration, documentFiles)) {
            return"Trop de fichiers";
        } else {
            if (!validateFileExtensions(configuration, documentsFileNames)) {
                return "Extension(s) de fichier(s) non valide(s)";
            } else {
                if (!validateFileSizes(configuration, documentFiles)) {
                    return LanguageUtil.get(languageBundle, ERROR_FILE_TO_LARGE)
                            + ParamUtil.getLong(request, "sizeFile") + "Mo)";
                } else {
                    message = antiVirusVerif(documentFiles);
                    if (!message.equals("")) {
                        return message;
                    }
                }
            }
        }

        return "";
    }

}