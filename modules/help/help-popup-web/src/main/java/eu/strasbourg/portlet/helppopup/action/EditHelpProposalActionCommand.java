package eu.strasbourg.portlet.helppopup.action;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.service.AssetCategoryLocalServiceUtil;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
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
import eu.strasbourg.portlet.helppopup.constants.HelpPopUpPortletConstants;
import eu.strasbourg.service.help.model.HelpProposal;
import eu.strasbourg.service.help.service.HelpProposalLocalService;
import eu.strasbourg.utils.AssetVocabularyHelper;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.PortletRequest;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static eu.strasbourg.portlet.helppopup.HelpPopupPortlet.REDIRECT_URL_PARAM;

@Component(
        immediate = true,
        property = {
                "javax.portlet.name=" + StrasbourgPortletKeys.HELP_POPUP_WEB,
                "mvc.command.name=editHelpProposal"
        },
        service = MVCActionCommand.class
)
public class EditHelpProposalActionCommand implements MVCActionCommand {

    // Champs
    private String address;
    private String city;
    private long postalcode;
    private String phoneNumber;
    private String title;
    private String types;
    private String presentation;
    private long helperId;
    private String inTheNameOf;
    private String language;
    private long localisationId;
    private boolean deletePhoto;
    private long entryId;
    private boolean agreement1;
    private boolean agreement2;
    private boolean agreement3;

    // Gestion et contexte de la requete
    private String publikID;
    private String messageKey;

    @Override
	public boolean processAction(ActionRequest request, ActionResponse response) throws PortletException {

		boolean result = false;
		
		// Recuperation des identifiants assujetis a la requete
        this.entryId = ParamUtil.getLong(request, "entryId");
        this.publikID = getPublikID(request);
        
		// Recuperation de l'URL de redirection
        String redirectURL = ParamUtil.getString(request, REDIRECT_URL_PARAM);
        
        // Recuperation des informations du budget participatif du formulaire
        this.address = HtmlUtil.stripHtml(ParamUtil.getString(request, HelpPopUpPortletConstants.ADDRESS));
        this.city = HtmlUtil.stripHtml(ParamUtil.getString(request, HelpPopUpPortletConstants.CITY));
        this.postalcode = ParamUtil.getLong(request, HelpPopUpPortletConstants.POSTALCODE);
        this.phoneNumber = HtmlUtil.stripHtml(ParamUtil.getString(request, HelpPopUpPortletConstants.PHONE_NUMBER));
        this.title = HtmlUtil.stripHtml(ParamUtil.getString(request, HelpPopUpPortletConstants.TITLE));
        this.types = ParamUtil.getString(request, HelpPopUpPortletConstants.TYPES);
        this.presentation = HtmlUtil.stripHtml(ParamUtil.getString(request, HelpPopUpPortletConstants.PRESENTATION));
        this.helperId = ParamUtil.getLong(request, HelpPopUpPortletConstants.HELPER);
        this.inTheNameOf = HtmlUtil.stripHtml(ParamUtil.getString(request, HelpPopUpPortletConstants.IN_THE_NAME_OF));
        this.language = HtmlUtil.stripHtml(ParamUtil.getString(request, HelpPopUpPortletConstants.LANGUAGE));
        this.localisationId = ParamUtil.getLong(request, HelpPopUpPortletConstants.LOCALISATION);
        this.deletePhoto = ParamUtil.getString(request, HelpPopUpPortletConstants.DELETE_PHOTO).equals("true") ? true : false;
        this.agreement1 = ParamUtil.getString(request, HelpPopUpPortletConstants.AGREEMENT_1)
                .equals(HelpPopUpPortletConstants.AGREEMENT_1);
        this.agreement2 = ParamUtil.getString(request, HelpPopUpPortletConstants.AGREEMENT_2)
                .equals(HelpPopUpPortletConstants.AGREEMENT_2);
        this.agreement3 = ParamUtil.getString(request, HelpPopUpPortletConstants.AGREEMENT_3)
                .equals(HelpPopUpPortletConstants.AGREEMENT_3);

        // Verification de la validite des informations
        if (validate()) {
            // Envoi de la demande de modification du budget
            result = editHelpProposal(request);
        }else{
            SessionErrors.add(request, this.messageKey);
            return false;
        }
        
        try {
            response.sendRedirect(redirectURL);
        } catch (IOException e) {
            _log.error("erreur de redirection dans la command action editHelpProposal : ", e);
            throw new PortletException(e);
        }

        
		return result;
	}
	
	private boolean editHelpProposal(ActionRequest request) throws PortletException {
        
        try {
        	AssetEntry assetEntry = AssetEntryLocalServiceUtil.getAssetEntry(this.entryId);
        	HelpProposal helpProposal = _helpProposalLocalService.getHelpProposal(assetEntry.getClassPK());
        	ServiceContext sc = ServiceContextFactory.getInstance(request);

            List<Long> identifiants = new ArrayList<>();
            String[] typeIds = this.types.split("-");
            for (String typeId : typeIds) {
                if (typeId != "-") {
                    identifiants.add(Long.parseLong(typeId));
                }
            }
            identifiants.add(helperId);
            identifiants.add(localisationId);
            // Mise de la ville de strasbourg si c'est un quartier
            AssetCategory parent = AssetCategoryLocalServiceUtil.fetchAssetCategory(localisationId).getParentCategory();
            if(parent.getName().equals("Strasbourg")) {
                identifiants.add(parent.getCategoryId());
            }
            // Ajout active
            AssetCategory active = AssetVocabularyHelper.getCategory("Active", sc.getScopeGroupId());
            if (active != null)
                identifiants.add(active.getCategoryId());
            // Ajout non lue
            AssetCategory nonLu = AssetVocabularyHelper.getCategory("Non Lue", sc.getScopeGroupId());
            if (nonLu != null)
                identifiants.add(nonLu.getCategoryId());

            long[] ids = new long[identifiants.size()];
            for (int i = 0; i < identifiants.size(); i++) {
                ids[i] = identifiants.get(i);
            }

            sc.setAssetCategoryIds(ids);

            helpProposal.setAddress(this.address);
            helpProposal.setCity(this.city);
            helpProposal.setPostalCode(this.postalcode);
            helpProposal.setPhoneNumber(this.phoneNumber);
            helpProposal.setTitle(this.title, Locale.FRANCE);
            helpProposal.setDescription(this.presentation, Locale.FRANCE);
            helpProposal.setInTheNameOf(this.inTheNameOf);
            helpProposal.setSpokenLanguages(this.language, Locale.FRANCE);
            helpProposal.setModifiedByUserDate(new Date());
            helpProposal.setAgreementSigned1(this.agreement1);
            helpProposal.setAgreementSigned2(this.agreement2);
            helpProposal.setAgreementSigned3(this.agreement3);
            
            UploadRequest uploadRequest = PortalUtil.getUploadPortletRequest(request);
            String fileName = uploadRequest.getFileName(HelpPopUpPortletConstants.PHOTO);
            if(this.deletePhoto && (fileName == null || fileName.isEmpty()))
                helpProposal.setImageId(0);
            else
            	uploadFile(helpProposal, request);

            //Mise à jour du BP
            _helpProposalLocalService.updateHelpProposal(helpProposal, sc);
            
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
     * @param helpProposal la proposaition d'aide correspondante.
     * @param request            la request
     * @throws IOException
     * @throws PortalException
     */
    private Boolean uploadFile(HelpProposal helpProposal, ActionRequest request) throws PortalException, IOException{
    	
    	// Recuperation du contexte de la requete
        ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        ServiceContext sc = ServiceContextFactory.getInstance(request);
        UploadRequest uploadRequest = PortalUtil.getUploadPortletRequest(request);
        
        // Verification du nom du fichier
        if (validateFileName(request)) {
        	
            File photo = uploadRequest.getFile(HelpPopUpPortletConstants.PHOTO);
            
            // Verification de la bonne recuperation du contenu du fichier
            if (photo != null && photo.exists()) {
                byte[] imageBytes = FileUtil.getBytes(photo);
                
                // Dossier a la racine
                DLFolder folderparent = DLFolderLocalServiceUtil.getFolder(themeDisplay.getScopeGroupId(),
                        													DLFolderConstants.DEFAULT_PARENT_FOLDER_ID,
                        													"Proposition d'aide");
                // Dossier d'upload de l'entite
                DLFolder folder = DLFolderLocalServiceUtil.getFolder(themeDisplay.getScopeGroupId(),
                                									folderparent.getFolderId(),
                                									"uploads");
                // Ajout du fichier
                FileEntry fileEntry = DLAppLocalServiceUtil.addFileEntry(
                        sc.getUserId(), folder.getRepositoryId(),
                        folder.getFolderId(), photo.getName(),
                        MimeTypesUtil.getContentType(photo),
                        photo.getName(), title,
                        "", imageBytes, sc);
                // Lien de l'image a l'entite
                helpProposal.setImageId(fileEntry.getFileEntryId());
                
                _log.info("Photo proposition d'aide uploade : [" + photo + "]");

            }
        } else {
            this.messageKey = HelpPopUpPortletConstants.ERROR_EXTENSION;
            return false;
        }

        return true;
    }

   private boolean validateFileName(ActionRequest request) {
        boolean result = true;
        UploadRequest uploadRequest = PortalUtil.getUploadPortletRequest(request);
        String fileName = uploadRequest.getFileName(HelpPopUpPortletConstants.PHOTO);
        if (fileName != null && !fileName.isEmpty()) {
            String type = fileName.substring(fileName.lastIndexOf(".")).toLowerCase();
            result = type.equals(".jpg") || type.equals(".jpeg") || type.equals(".png");
        }
        return result;
    }
	
	/**
	 * Validation des champs de la requete (excpet photo)
	 * @return Valide ou pas
	 */
	private boolean validate() {

        // address
        if (Validator.isNull(this.address)) {
            this.messageKey = HelpPopUpPortletConstants.ERROR_ADDRESS;
            return false;
        }

        // city
        if (Validator.isNull(this.city)) {
            this.messageKey = HelpPopUpPortletConstants.ERROR_CITY;
            return false;
        }

        // postalcode
        if (Validator.isNull(this.postalcode)) {
            this.messageKey = HelpPopUpPortletConstants.ERROR_POSTAL_CODE;
            return false;
        }
        Pattern p = Pattern.compile(HelpPopUpPortletConstants.REGEX_POSTAL_CODE);
        Matcher m = p.matcher(String.valueOf(this.postalcode));
        if (!m.matches()) {
            this.messageKey = HelpPopUpPortletConstants.ERROR_POSTAL_CODE;
            return false;
        }

        // Téléphone
        if (Validator.isNull(this.phoneNumber)) {
            this.messageKey = HelpPopUpPortletConstants.ERROR_PHONE_NUMBER;
            return false;
        }

        // utilisateur
        if (this.publikID == null || this.publikID.isEmpty()) {
            this.messageKey = HelpPopUpPortletConstants.ERROR_USER_NO_FOUND;
            return false;
        }

        // title
        if (Validator.isNull(this.title)) {
            this.messageKey = HelpPopUpPortletConstants.ERROR_TITLE;
            return false;
        }

        // Types d'aide
        if (Validator.isNull(this.types)) {
            this.messageKey = HelpPopUpPortletConstants.ERROR_HELP_TYPE;
            return false;
        }

        // Description
        if (Validator.isNull(this.presentation)) {
            this.messageKey = HelpPopUpPortletConstants.ERROR_PRESENTATION;
            return false;
        }

        // type d'aidant
        if (Validator.isNull(this.helperId)) {
            this.messageKey = HelpPopUpPortletConstants.ERROR_HELPER_TYPE;
            return false;
        }

        // Déposé au nom de
        if (Validator.isNull(this.inTheNameOf)) {
            this.messageKey = HelpPopUpPortletConstants.ERROR_IN_THE_NAME_OF;
            return false;
        }

        // Localisation
        if (Validator.isNull(this.localisationId)) {
            this.messageKey = HelpPopUpPortletConstants.ERROR_TERRITORY;
            return false;
        }

        // consentements
        if (!this.agreement1 || !this.agreement2) {
            this.messageKey = HelpPopUpPortletConstants.ERROR_AGREEMENTS;
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

    @Reference(unbind = "-")
    protected void setHelpProposalLocalService(HelpProposalLocalService helpProposalLocalService) {
        _helpProposalLocalService = helpProposalLocalService;
    }

    private HelpProposalLocalService _helpProposalLocalService;

}
