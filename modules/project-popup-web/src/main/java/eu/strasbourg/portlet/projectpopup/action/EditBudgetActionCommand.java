package eu.strasbourg.portlet.projectpopup.action;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetCategoryModel;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
import com.liferay.asset.kernel.service.AssetVocabularyLocalServiceUtil;
import com.liferay.document.library.kernel.model.DLFolder;
import com.liferay.document.library.kernel.model.DLFolderConstants;
import com.liferay.document.library.kernel.service.DLAppLocalServiceUtil;
import com.liferay.document.library.kernel.service.DLAppServiceUtil;
import com.liferay.document.library.kernel.service.DLFolderLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.module.configuration.ConfigurationException;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.upload.UploadRequest;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.MimeTypesUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.SessionParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import eu.strasbourg.portlet.projectpopup.configuration.ProjectPopupConfiguration;
import eu.strasbourg.service.oidc.model.PublikUser;
import eu.strasbourg.service.oidc.service.PublikUserLocalServiceUtil;
import eu.strasbourg.service.project.model.BudgetParticipatif;
import eu.strasbourg.service.project.model.BudgetPhase;
import eu.strasbourg.service.project.service.BudgetParticipatifLocalServiceUtil;
import eu.strasbourg.service.project.service.BudgetPhaseLocalServiceUtil;
import eu.strasbourg.utils.AssetVocabularyHelper;
import eu.strasbourg.utils.FileEntryHelper;
import eu.strasbourg.utils.StringHelper;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import eu.strasbourg.utils.constants.VocabularyNames;
import org.osgi.service.component.annotations.Component;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.PortletRequest;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static eu.strasbourg.portlet.projectpopup.ProjectPopupPortlet.CITY_NAME;
import static eu.strasbourg.portlet.projectpopup.ProjectPopupPortlet.REDIRECT_URL_PARAM;

/**
 * @author romain.vergnais
 */
@Component(
        immediate = true,
        property = {
                "javax.portlet.name=" + StrasbourgPortletKeys.PROJECT_POPUP_WEB,
                "mvc.command.name=editBudget"
        },
        service = MVCActionCommand.class
)
public class EditBudgetActionCommand implements MVCActionCommand {
	
    private static final String BUDGETTITLE = "title";
    private static final String BUDGETSUMMARY = "summary";
    private static final String BUDGETDESCRIPTION = "budgetdescription";
    private static final String LIEU = "budgetlieux";
    private static final String PROJECT = "project";
    private static final String QUARTIER = "quartier";
    private static final String THEME = "theme";
    private static final String PHOTO = "budgetPhoto";
    private static final String DELETE_PHOTO = "deletePhoto";
    private static final String VIDEO = "budgetVideo";


    @Override
	public boolean processAction(ActionRequest request, ActionResponse response) throws PortletException {

		boolean result = false;
		
		// Recuperation des identifiants assujetis a la requete
        long entryId = ParamUtil.getLong(request, "entryId");
        PublikUser user = null;
        String publikID = getPublikID(request);
        if (publikID != null && !publikID.isEmpty()) {
            user = PublikUserLocalServiceUtil.getByPublikUserId(publikID);
        }
        
		// Recuperation de l'URL de redirection
        String redirectURL = ParamUtil.getString(request, REDIRECT_URL_PARAM);
        
        // Recuperation des informations du budget participatif du formulaire
        String lieu = HtmlUtil.stripHtml(ParamUtil.getString(request, LIEU));
        String video = HtmlUtil.stripHtml(ParamUtil.getString(request, VIDEO));
        String title = HtmlUtil.stripHtml(ParamUtil.getString(request, BUDGETTITLE));
        String summary = HtmlUtil.stripHtml(ParamUtil.getString(request, BUDGETSUMMARY));
        String description = ParamUtil.getString(request, BUDGETDESCRIPTION);
        long projectId = ParamUtil.getLong(request, PROJECT);
        long quartierId = ParamUtil.getLong(request, QUARTIER);
        long themeId = ParamUtil.getLong(request, THEME);
        boolean deletePhoto = ParamUtil.getString(request, DELETE_PHOTO).equals("true");

        // Récupération des info d'upload
        String nbFiles = null;
        String typesFiles = null;
        String sizeFile = null;
        UploadRequest uploadRequest = PortalUtil.getUploadPortletRequest(request);
        String fileName = uploadRequest.getFileName("budgetPhoto");
        String[] fileNames = uploadRequest.getFileNames("budgetFile");
        File[] files = uploadRequest.getFiles("budgetFile");
        String[] oldFileIds = ParamUtil.getStringValues(request, "budgetFileId");
        try {
            ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
            ProjectPopupConfiguration configuration = themeDisplay.getPortletDisplay()
                    .getPortletInstanceConfiguration(ProjectPopupConfiguration.class);
            nbFiles = configuration.nbFiles();
            typesFiles = configuration.typesFiles();
            sizeFile = configuration.sizeFile();
        } catch (ConfigurationException e) {
            _log.error(e.getMessage(), e);
        }

        // Verification de la validite des informations
        String messageKey = validate(request, publikID, user, title, summary, description, oldFileIds, nbFiles, fileNames,
                typesFiles, files, sizeFile);
        if (messageKey.equals("")) {
            // Envoi de la demande de modification du budget
            try {
                AssetEntry assetEntry = AssetEntryLocalServiceUtil.getAssetEntry(entryId);
                BudgetParticipatif bp = BudgetParticipatifLocalServiceUtil.getBudgetParticipatif(assetEntry.getClassPK());
                ServiceContext sc = ServiceContextFactory.getInstance(request);

                List<AssetCategory> categories = bp.getCategories();

                //On recupère les catégories du bp, sans la thematique, le projet ou les territoires
                for (AssetCategory assetCategory : bp.getCategories()) {
                    AssetVocabulary voca = AssetVocabularyLocalServiceUtil.getVocabulary(assetCategory.getVocabularyId());
                    if(StringHelper.compareIgnoringAccentuation(voca.getName().toLowerCase(), VocabularyNames.TERRITORY) ||
                            StringHelper.compareIgnoringAccentuation(voca.getName().toLowerCase(), VocabularyNames.PROJECT) ||
                            StringHelper.compareIgnoringAccentuation(voca.getName().toLowerCase(), VocabularyNames.THEMATIC)) {
                        categories.remove(assetCategory);
                    }
                }

                List<Long> idCategories = categories.stream().map(AssetCategoryModel::getCategoryId).collect(Collectors.toList());

                if (quartierId == 0) {
                    List<AssetCategory> districts = AssetVocabularyHelper.getAllDistrictsFromCity(CITY_NAME);

                    idCategories.addAll(districts.stream()
                            .map(AssetCategoryModel::getCategoryId)
                            .collect(Collectors.toList()));
                } else
                    idCategories.add(quartierId);
                if (projectId != 0)
                    idCategories.add(projectId);
                if (themeId != 0)
                    idCategories.add(themeId);

                sc.setAssetCategoryIds(idCategories.stream().mapToLong(w -> w).toArray());
                bp.setTitle(title);
                bp.setSummary(summary);
                bp.setDescription(description);
                bp.setVideoUrl(video);
                bp.setPlaceTextArea(lieu);

                if(deletePhoto && (fileName == null || fileName.isEmpty()))
                    bp.setImageId(0);
                else
                    uploadFile(bp, request, title);

                // on ajoute les nouveaux documents
                String newFilesIds = String.join(",", oldFileIds);
                if(files.length > 0) {
                    String bpFilesIds = uploadDocuments(bp, request, oldFileIds, nbFiles, fileNames,
                            typesFiles, files, sizeFile, title);
                    if(newFilesIds.length() > 0 && bpFilesIds.length() > 0)
                        newFilesIds += ",";
                    newFilesIds += bpFilesIds;
                }
                bp.setFilesIds(newFilesIds);

                //Mise à jour du BP
                BudgetParticipatifLocalServiceUtil.updateBudgetParticipatif(bp, sc);

            } catch (PortalException | IOException e) {
                _log.error(e);
                throw new PortletException(e);
            }
        }else{
            SessionErrors.add(request, messageKey);
            return false;
        }
        
        try {
            response.sendRedirect(redirectURL);
        } catch (IOException e) {
            _log.error("erreur de redirection dans la command action editBudget : ", e);
            throw new PortletException(e);
        }

        
		return true;
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
    private void uploadFile(BudgetParticipatif budgetParticipatif, ActionRequest request, String title) throws PortalException, IOException{
    	
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
        } else {
            throw new PortalException("le fichier n'est pas une image");
        }
    }

    /**
     * Recuperer les documents uploadée par l'utilisateur.
     *
     * @param budgetParticipatif le budget participatif correspondant.
     * @param request            la request
     * @return les documentIds
     * @throws IOException
     * @throws PortalException
     */
    private String uploadDocuments(BudgetParticipatif budgetParticipatif, ActionRequest request, String[] oldFileIds,
                                   String nbFiles, String[] fileNames, String typesFiles, File[] files, String sizeFile,
                                   String title) throws PortalException, IOException{

        // Recuperation du contexte de la requete
        ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        ServiceContext sc = ServiceContextFactory.getInstance(request);
        String filesIds = "";

        // Verification du nombre de fichiers
        if (validateNbFiles(fileNames, oldFileIds, nbFiles)) {

            // Verification de l'extention des fichier
            if (validateFileExtensions(fileNames, typesFiles)) {

                // Vérification de la taille des fichiers
                if(validateFileSizes(files, sizeFile)){

                    // Vérification que le(s) fichier(s) est/sont clean
                    String message = antiVirusVerif(files);
                    if (message.equals("")){
                        int numFile = 0;
                        for (File file : files) {

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
                                            folderUpload.getFolderId(), budgetParticipatif.getPhase().getTitle(),
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
                                String name = fileNames[numFile];

                                // Ajout du fichier
                                FileEntry fileEntry;
                                try {
                                    fileEntry = DLAppLocalServiceUtil.addFileEntry(
                                            sc.getUserId(), folder.getRepositoryId(),
                                            folder.getFolderId(), name,
                                            MimeTypesUtil.getContentType(file),
                                            name, title,
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
                    }else{
                        throw new PortalException("Fichier(s) suspect(s) d&eacute;tect&eacute;(s)");
                    }
                }else{
                    throw new PortalException("Fichier(s) trop volumineux (maximum autoris&eacute; : "
                            + ParamUtil.getLong(request, "sizeFile") + "Mo)");
                }
            } else {
                throw new PortalException("Extension(s) de fichier(s) non valide(s)");
            }
        } else {
            throw new PortalException("Trop de fichiers");
        }

        return filesIds;
    }
    
    private boolean validateFileName(ActionRequest request) {
        boolean result = true;
        UploadRequest uploadRequest = PortalUtil.getUploadPortletRequest(request);
        String fileName = uploadRequest.getFileName("budgetPhoto");
        if (fileName != null && !fileName.isEmpty()) {
            String type = fileName.substring(fileName.lastIndexOf(".")).toLowerCase();
            result = type.equals(".jpg") || type.equals(".jpeg") || type.equals(".png");
        }
        return result;
    }

    private boolean validateNbFiles(String[] fileNames, String[] oldFileIds, String nbFiles) {
        boolean result = true;
        if ((fileNames.length + oldFileIds.length) > 0) {
            if ((fileNames.length + oldFileIds.length) > Long.parseLong(nbFiles)) {
                result = false;
            }
        }
        return result;
    }

    private boolean validateFileExtensions(String[] fileNames, String typesFiles) {
        boolean result = true;
        for (String fileName : fileNames) {
            if (fileName != null && !fileName.isEmpty()) {
                String type = fileName.substring(fileName.lastIndexOf(".") + 1);
                if (!Arrays.asList(typesFiles.split(",")).contains(type.toLowerCase())) {
                    result = false;
                    break;
                }
            }
        }
        return result;
    }

    private boolean validateFileSizes(File[] files, String sizeFile) {
        boolean result = true;
        for (File file : files) {
            if (file != null) {
                long fileSize = file.length() / (1024 * 1024);
                if(fileSize > Long.parseLong(sizeFile)){
                    result = false;
                    break;
                }
            }
        }
        return result;
    }

    private String antiVirusVerif(File[] files) {
        for (File file : files) {
            if (file != null) {
                String error = FileEntryHelper.scanFile(file);
                if (Validator.isNotNull(error)) {
                    return error;
                }
            }
        }
        return "";
    }
	
	/**
	 * Validation des champs de la requete (excpet photo)
	 * @return Valide ou pas
	 */
	private String validate(ActionRequest request, String publikID, PublikUser user, String title, String summary,
                            String description, String[] oldFileIds, String nbFiles, String[] fileNames, String typesFiles, File[] files,
                            String sizeFile) {
        
		ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        
        // utilisateur 
        if (publikID == null || publikID.isEmpty()) {
            return "user";
        } else {
        	if (user.isBanned()) {
                return "banned";
        	} else if (user.getPactSignature() == null) {
                return "pact";
        	}
        }
        
        // Phase
        BudgetPhase activePhase = BudgetPhaseLocalServiceUtil.getActivePhase(themeDisplay.getScopeGroupId());
        if (activePhase != null) {
        	if (!activePhase.isInDepositPeriod()) {
                return "phase";
        	}
        } else {
            return "phase";
        }
        
        // title
        if (Validator.isNull(title)) {
            return "title";
        }
        
        // Resume
        if (Validator.isNull(summary)) {
            return "summary";
        }

        // description
        if (Validator.isNull(HtmlUtil.stripHtml(description))) {
            return "description";
        }

        if (!validateFileName(request)) {
            return "image";
		}

        if (!validateNbFiles(fileNames, oldFileIds, nbFiles)) {
            return "too-much";
        }else{
            if (!validateFileExtensions(fileNames, typesFiles)) {
                return "extension";
            }else{
                if (!validateFileSizes(files, sizeFile)) {
                    return "big";
                }else {
                    String message = antiVirusVerif(files);
                    if (!message.equals(""))
                        return message;
                }
            }
        }

        return "";
    }
	
	/**
     * Récupération du publik ID avec la session
     */
    private String getPublikID(PortletRequest request) {
        LiferayPortletRequest liferayPortletRequest = PortalUtil.getLiferayPortletRequest(request);
        HttpServletRequest originalRequest = liferayPortletRequest.getHttpServletRequest();
        return SessionParamUtil.getString(originalRequest, "publik_internal_id");
    }
	
	/**
     * le log
     */
    private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());

}
