package eu.strasbourg.portlet.projectpopup.action;

import static eu.strasbourg.portlet.projectpopup.ProjectPopupPortlet.REDIRECT_URL_PARAM;
import static eu.strasbourg.portlet.projectpopup.ProjectPopupPortlet.CITY_NAME;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.PortletRequest;
import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetCategoryModel;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
import com.liferay.asset.kernel.service.AssetVocabularyLocalServiceUtil;
import com.liferay.document.library.kernel.model.DLFolder;
import com.liferay.document.library.kernel.model.DLFolderConstants;
import com.liferay.document.library.kernel.service.DLAppLocalServiceUtil;
import com.liferay.document.library.kernel.service.DLFolderLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
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

import eu.strasbourg.service.oidc.model.PublikUser;
import eu.strasbourg.service.oidc.service.PublikUserLocalServiceUtil;
import eu.strasbourg.service.project.model.BudgetParticipatif;
import eu.strasbourg.service.project.model.BudgetPhase;
import eu.strasbourg.service.project.service.BudgetParticipatifLocalServiceUtil;
import eu.strasbourg.service.project.service.BudgetPhaseLocalServiceUtil;
import eu.strasbourg.utils.AssetVocabularyHelper;
import eu.strasbourg.utils.StringHelper;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import eu.strasbourg.utils.constants.VocabularyNames;

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
    private static final String BUDGETDESCRIPTION = "description";
    private static final String LIEU = "budgetlieux";
    private static final String PROJECT = "project";
    private static final String QUARTIER = "quartier";
    private static final String THEME = "theme";
    private static final String PHOTO = "budgetPhoto";
    private static final String DELETE_PHOTO = "deletePhoto";
    private static final String VIDEO = "budgetVideo";

    private String publikID;
    private PublikUser user;
    private String video;
    private String title;
    private String description;
    private String lieu;
    private boolean deletePhoto;
    private long projectId;
    private long quartierId;
    private long themeId;
    private long entryId;
    private String message;


	@Override
	public boolean processAction(ActionRequest request, ActionResponse response) throws PortletException {

		boolean result = false;
		
		// Recuperation des identifiants assujetis a la requete
        this.entryId = ParamUtil.getLong(request, "entryId");
        this.publikID = getPublikID(request);
        
		// Recuperation de l'URL de redirection
        String redirectURL = ParamUtil.getString(request, REDIRECT_URL_PARAM);
        
        // Recuperation des informations du budget participatif du formulaire
        this.lieu = HtmlUtil.stripHtml(ParamUtil.getString(request, LIEU));
        this.video = HtmlUtil.stripHtml(ParamUtil.getString(request, VIDEO));
        this.title = HtmlUtil.stripHtml(ParamUtil.getString(request, BUDGETTITLE));
        this.description = HtmlUtil.stripHtml(ParamUtil.getString(request, BUDGETDESCRIPTION));
        this.projectId = ParamUtil.getLong(request, PROJECT);
        this.quartierId = ParamUtil.getLong(request, QUARTIER);
        this.themeId = ParamUtil.getLong(request, THEME);
        this.deletePhoto = ParamUtil.getString(request, DELETE_PHOTO).equals("true") ? true : false;
        
        // Verification de la validite des informations
        if (validate(request)) {
            // Envoi de la demande de modification du budget
            result = editBudget(request);
        }
        
        try {
            response.sendRedirect(redirectURL);
        } catch (IOException e) {
            _log.error("erreur de redirection dans la command action editBudget : ", e);
            throw new PortletException(e);
        }

        
		return result;
	}
	
	private boolean editBudget(ActionRequest request) throws PortletException {
        
        try {
        	AssetEntry assetEntry = AssetEntryLocalServiceUtil.getAssetEntry(this.entryId);
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
            
            List<Long> idCategories = categories.stream().map(c->c.getCategoryId()).collect(Collectors.toList());
            
            if (this.quartierId == 0) {
                List<AssetCategory> districts = AssetVocabularyHelper.getAllDistrictsFromCity(CITY_NAME);
                
                idCategories.addAll(districts.stream()
                        .map(AssetCategoryModel::getCategoryId)
                        .collect(Collectors.toList()));
            } else 
                idCategories.add(quartierId);
            if (this.projectId != 0) 
                idCategories.add(projectId);
            if (this.themeId != 0) 
                idCategories.add(themeId);
            
            sc.setAssetCategoryIds(idCategories.stream().mapToLong(w -> w).toArray());
            bp.setTitle(this.title);
            bp.setDescription(this.description);
            bp.setVideoUrl(this.video);
            bp.setPlaceTextArea(this.lieu);
            
            UploadRequest uploadRequest = PortalUtil.getUploadPortletRequest(request);
            String fileName = uploadRequest.getFileName("budgetPhoto");
            if(this.deletePhoto && (fileName == null || fileName.isEmpty()))
            	bp.setImageId(0);
            else
            	uploadFile(bp, request);
            
            //Mise à jour du BP
            BudgetParticipatifLocalServiceUtil.updateBudgetParticipatif(bp, sc);
            
        } catch (PortalException e) {
            _log.error(e);
            throw new PortletException(e);
        } catch (IOException e) {
        	_log.error(e);
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
    private void uploadFile(BudgetParticipatif budgetParticipatif, ActionRequest request) throws PortalException, IOException{
    	
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
    
    private boolean validateFileName(ActionRequest request) {
        boolean result = true;
        UploadRequest uploadRequest = PortalUtil.getUploadPortletRequest(request);
        String fileName = uploadRequest.getFileName("budgetPhoto");
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
	private boolean validate(ActionRequest request) {
        
		ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        
        // utilisateur 
        if (this.publikID == null || this.publikID.isEmpty()) {
            this.message = "Utilisateur non reconnu";
            return false;
        } else {
        	this.user = PublikUserLocalServiceUtil.getByPublikUserId(this.publikID);
        	
        	if (this.user.isBanned()) {
        		this.message = "Vous ne pouvez modifier ce projet";
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

        if (!validateFileName(request)) {
			this.message = "Nom du fichier de l'image non valide";
		    return false;
		}

        return true;
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
