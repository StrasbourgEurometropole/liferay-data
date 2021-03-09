package eu.strasbourg.portlet.helppopup.resource;

import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
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
import com.liferay.portal.kernel.util.SessionParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import eu.strasbourg.portlet.helppopup.constants.HelpPopUpPortletConstants;
import eu.strasbourg.service.help.model.HelpProposal;
import eu.strasbourg.service.help.model.HelpRequest;
import eu.strasbourg.service.help.service.HelpProposalLocalServiceUtil;
import eu.strasbourg.service.help.service.HelpRequestLocalService;
import eu.strasbourg.service.oidc.model.PublikUser;
import eu.strasbourg.service.oidc.service.PublikUserLocalServiceUtil;
import eu.strasbourg.utils.MailHelper;
import eu.strasbourg.utils.StrasbourgPropsUtil;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.mail.internet.InternetAddress;
import javax.portlet.PortletException;
import javax.portlet.PortletRequest;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.ResourceBundle;

@Component(
    immediate = true,
    property = {
    	"javax.portlet.name=" + StrasbourgPortletKeys.HELP_POPUP_WEB,
    	"mvc.command.name=submitHelpRequest"
    },
    service = MVCResourceCommand.class
)
public class SubmitHelpResourceCommand implements MVCResourceCommand {

    // Champs
    private long entryID;
    private HelpProposal helpProposal;
    private long helpRequestId;
    private String phoneNumber;
    private String message;
    private long studentCardImageId;
    private boolean previousImageEdited;
    private boolean agreement1;
    private boolean agreement2;
    private boolean agreement3;

    // Gestion et contexte de la requete
    private String publikID;
    private PublikUser user;
    private String messageResult;

	private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());

    private ResourceBundle bundle = ResourceBundleUtil.getBundle("content.Language",
            this.getClass().getClassLoader());
	
	@Override
	public boolean serveResource(ResourceRequest request, ResourceResponse response) throws PortletException {
        
        // Initialisations respectives de : resultat probant de la requete, sauvegarde ou non des informations Publik, message de retour, format de date
        boolean result = false;
        this.messageResult = "";

        // Recuperation de l'utilsiteur Publik ayant lance la demande
        this.publikID = getPublikID(request);

        // Recuperation de la proposition d'aide correspondante
        this.entryID = ParamUtil.getLong(request, HelpPopUpPortletConstants.ENTRY_ID);
        
        // Recuperation des informations du formulaire
        this.phoneNumber = HtmlUtil.stripHtml(ParamUtil.getString(request, HelpPopUpPortletConstants.PHONE_NUMBER));
        this.message = HtmlUtil.stripHtml(ParamUtil.getString(request, HelpPopUpPortletConstants.MESSAGE));

        // Recuperation donnees justificatifs
        this.studentCardImageId = ParamUtil.getLong(request, HelpPopUpPortletConstants.STUDENT_CARD_IMAGE_ID);
        this.previousImageEdited = ParamUtil.getString(request, HelpPopUpPortletConstants.PREVIOUS_IMAGE_EDITED).equals("true") ? true : false;

        // Signature des consentements
        this.agreement1 = ParamUtil.getString(request, HelpPopUpPortletConstants.AGREEMENT_1)
                .equals(HelpPopUpPortletConstants.AGREEMENT_1);
        this.agreement2 = ParamUtil.getString(request, HelpPopUpPortletConstants.AGREEMENT_2)
                .equals(HelpPopUpPortletConstants.AGREEMENT_2);
        this.agreement3 = ParamUtil.getString(request, HelpPopUpPortletConstants.AGREEMENT_3)
                .equals(HelpPopUpPortletConstants.AGREEMENT_3);
		
        // Verification de la validite des informations
        if (validate()) {
            
         	// Envoi de la demande
            result = saveHelpRequest(request);
            
            if(result) {
                sendHelpRequestMail(request);
                sendHelpRequestMailConfirmation(request);
            }
        }
        
        // Retour des informations de la requete en JSON
        JSONObject jsonResponse = JSONFactoryUtil.createJSONObject();
        jsonResponse.put("result", result);
        jsonResponse.put("message", this.messageResult);

        // Recuperation de l'élément d'écriture de la réponse
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
            writer.print(jsonResponse.toString());
        } catch (IOException e) {
            _log.error("erreur dans l'enregistrement de la demande d'aide : ", e);
        }
        return result;
	}
	
	private boolean saveHelpRequest(ResourceRequest request) throws PortletException {
		ServiceContext sc;
        HelpRequest helpRequest;
        
        try {
            sc = ServiceContextFactory.getInstance(request);
            helpRequest = _helpRequestLocalService.createHelpRequest(sc);
            this.helpRequestId = helpRequest.getHelpRequestId();

            helpRequest.setPhoneNumber(this.phoneNumber);
            helpRequest.setMessage(this.message);
            helpRequest.setHelpProposalId(this.helpProposal.getHelpProposalId());
            helpRequest.setPublikId(this.publikID);
            helpRequest = uploadFile(helpRequest, request);
            helpRequest.setAgreementSigned1(this.agreement1);
            helpRequest.setAgreementSigned2(this.agreement2);
            helpRequest.setAgreementSigned3(this.agreement3);

           _helpRequestLocalService.updateHelpRequest(helpRequest);
            
        } catch (PortalException | IOException e) {
            _log.error(e);
            this.messageResult = e.getMessage();
            return false;
        }
        _log.info("Demande d'aide cree : " + helpRequest);
        return true;
    }

    /**
     * Envoi du mail de confirmation de demande d'aide
     */
    private void sendHelpRequestMail(ResourceRequest request) {

        try {
            ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
            // récupération des images
            StringBuilder hostUrl = new StringBuilder("https://");
            hostUrl.append(request.getServerName());
//			StringBuilder headerImage = new StringBuilder(hostUrl)
//					.append("/o/plateforme-citoyenne-theme/images/logos/mail-img-header-pcs.png");
//			StringBuilder btnImage = new StringBuilder(hostUrl)
//					.append("/o/plateforme-citoyenne-theme/images/logos/mail-btn-knowmore.png");

            // préparation du template de mail
            Map<String, Object> context = new HashMap<>();
            context.put("link", themeDisplay.getURLPortal() + themeDisplay.getURLCurrent());
//			context.put("headerImage", headerImage.toString());
//			context.put("footerImage", btnImage.toString());
            context.put("Phone", this.phoneNumber);
            context.put("Message", this.message);

            StringWriter out = new StringWriter();

            //Chargement du template contenant le corps du mail
            TemplateResource templateResourceBody = new URLTemplateResource("0",
                    Objects.requireNonNull(this.getClass().getClassLoader()
                            .getResource("META-INF/resources/templates/contact-mail-help-request-body-fr_FR.ftl")));
            Template bodyTemplate = TemplateManagerUtil.getTemplate(
                    TemplateConstants.LANG_TYPE_FTL, templateResourceBody, false);

            // Traitement du template corps
            bodyTemplate.putAll(context);
            bodyTemplate.processTemplate(out);
            String mailBody = out.toString();

            String subject = LanguageUtil.get(PortalUtil.getHttpServletRequest(request), "modal.submit.help-request.mail.information");

            InternetAddress fromAddress = new InternetAddress("no-reply@no-reply.strasbourg.eu",
                    themeDisplay.getScopeGroup().getName(request.getLocale()));

            InternetAddress[] toAddresses = new InternetAddress[0];
            PublikUser helper =  PublikUserLocalServiceUtil.getByPublikUserId(this.helpProposal.getPublikId());
            if(helper != null) {
                InternetAddress address = new InternetAddress(helper.getEmail());
                toAddresses = ArrayUtil.append(toAddresses, address);
            }

            // Copie carbone invisible
            InternetAddress bccAddress = null;
            String bccProperties = StrasbourgPropsUtil.getEntraideUserSubmitBCCMail();
            if (Validator.isNotNull(bccProperties))
                bccAddress = new InternetAddress(bccProperties);

            // envoi du mail aux utilisateurs
            MailHelper.sendMailWithBCCWithHTML(fromAddress, toAddresses, bccAddress, subject, mailBody);
        } catch (Exception e) {
            _log.error(e);
            e.printStackTrace();
        }
    }

    /**
     * Envoi du mail de confirmation de demande d'aide
     */
    private void sendHelpRequestMailConfirmation(ResourceRequest request) {

        try {
            ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
            // récupération des images
            StringBuilder hostUrl = new StringBuilder("https://");
            hostUrl.append(request.getServerName());
//			StringBuilder headerImage = new StringBuilder(hostUrl)
//					.append("/o/plateforme-citoyenne-theme/images/logos/mail-img-header-pcs.png");
//			StringBuilder btnImage = new StringBuilder(hostUrl)
//					.append("/o/plateforme-citoyenne-theme/images/logos/mail-btn-knowmore.png");

            // préparation du template de mail
            Map<String, Object> context = new HashMap<>();
            context.put("link", themeDisplay.getURLPortal() + themeDisplay.getURLCurrent());
//			context.put("headerImage", headerImage.toString());
//			context.put("footerImage", btnImage.toString());
            context.put("Phone", this.phoneNumber);
            context.put("Message", this.message);

            StringWriter out = new StringWriter();

            //Chargement du template contenant le corps du mail
            TemplateResource templateResourceBody = new URLTemplateResource("0",
                    Objects.requireNonNull(this.getClass().getClassLoader()
                            .getResource("META-INF/resources/templates/contact-mail-help-request-confirmation-body-fr_FR.ftl")));
            Template bodyTemplate = TemplateManagerUtil.getTemplate(
                    TemplateConstants.LANG_TYPE_FTL, templateResourceBody, false);

            // Traitement du template corps
            bodyTemplate.putAll(context);
            bodyTemplate.processTemplate(out);
            String mailBody = out.toString();

            String subject = LanguageUtil.get(PortalUtil.getHttpServletRequest(request), "modal.submit.help-request.mail.confirmation");

            InternetAddress fromAddress = new InternetAddress("no-reply@no-reply.strasbourg.eu",
                    themeDisplay.getScopeGroup().getName(request.getLocale()));

            InternetAddress[] toAddresses = new InternetAddress[0];
            InternetAddress address = new InternetAddress(this.user.getEmail());
            toAddresses = ArrayUtil.append(toAddresses, address);

            // Copie carbone invisible
            InternetAddress bccAddress = null;
            String bccProperties = StrasbourgPropsUtil.getEntraideUserSubmitBCCMail();
            if (Validator.isNotNull(bccProperties))
                bccAddress = new InternetAddress(bccProperties);

            // envoi du mail aux utilisateurs
            MailHelper.sendMailWithBCCWithHTML(fromAddress, toAddresses, bccAddress,  subject, mailBody);
        } catch (Exception e) {
            _log.error(e);
            e.printStackTrace();
        }
    }
	
	 /**
     * Recuperer l'image uploadée par l'utilisateur.
     *
     * @param helpRequest Entite concernee
     * @return l'la demande d'aide avec l'imageId
     */
    private HelpRequest uploadFile(HelpRequest helpRequest, ResourceRequest request) throws IOException, PortalException {
    	
    	// Recuperation du contexte de la requete
        ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        ServiceContext sc = ServiceContextFactory.getInstance(request);
        UploadRequest uploadRequest = PortalUtil.getUploadPortletRequest(request);
        boolean validFilename = validateFileName(request);

        // Verification du nom du fichier
        if ((studentCardImageId < 1 && validFilename) ||
                (this.previousImageEdited && this.studentCardImageId > 1 && validFilename)) {
            File photo = uploadRequest.getFile(HelpPopUpPortletConstants.PHOTO);

            // Verification de la bonne recuperation du contenu du fichier
            if (photo != null && photo.exists()) {

                byte[] imageBytes = FileUtil.getBytes(photo);

                // Dossier a la racine
                DLFolder folderparent = DLFolderLocalServiceUtil.getFolder(themeDisplay.getScopeGroupId(),
                        DLFolderConstants.DEFAULT_PARENT_FOLDER_ID,
                        "Demande d'aide");
                // Dossier d'upload de l'entite
                DLFolder folder = DLFolderLocalServiceUtil.getFolder(themeDisplay.getScopeGroupId(),
                        folderparent.getFolderId(),
                        "Uploads");
                // Ajout du fichier
                FileEntry fileEntry = DLAppLocalServiceUtil.addFileEntry(
                        sc.getUserId(), folder.getRepositoryId(),
                        folder.getFolderId(), photo.getName(),
                        MimeTypesUtil.getContentType(photo),
                        photo.getName(), this.helpProposal.getTitle(Locale.FRANCE),
                        "", imageBytes, sc);
                // Lien de l'image a l'entite
                helpRequest.setStudentCardImageId(fileEntry.getFileEntryId());

                _log.info("Photo initiative uploade : [" + photo + "]");

            }
            return helpRequest;
        }
        else if (!this.previousImageEdited && this.studentCardImageId > 1) {
            helpRequest.setStudentCardImageId(this.studentCardImageId);
            return helpRequest;
        }
        else {
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
        
        // utilisateur 
        if (this.publikID == null || this.publikID.isEmpty()) {
            this.messageResult = LanguageUtil.get(bundle, HelpPopUpPortletConstants.ERROR_USER_NO_FOUND);
            return false;
        } else {
        	this.user = PublikUserLocalServiceUtil.getByPublikUserId(this.publikID);
        }

        // HelpProposal
        try {
            AssetEntry assetEntry = AssetEntryLocalServiceUtil.getAssetEntry(this.entryID);
            this.helpProposal = HelpProposalLocalServiceUtil.getHelpProposal(assetEntry.getClassPK());

            if (this.helpProposal == null) {
                this.messageResult = LanguageUtil.get(bundle, HelpPopUpPortletConstants.ERROR_DURING_HELP_PROPOSAL_RESEARCH);
                return false;
            }
        } catch (PortalException e1) {
            _log.error(e1);
            this.messageResult = LanguageUtil.get(bundle, HelpPopUpPortletConstants.ERROR_DURING_HELP_PROPOSAL_RESEARCH);
            return false;
        }

        // consentements
        if (!this.agreement1 || !this.agreement2 || !this.agreement3) {
            this.message = LanguageUtil.get(bundle, HelpPopUpPortletConstants.ERROR_AGREEMENTS);
            return false;
        }

        // Téléphone
        if (Validator.isNull(this.phoneNumber)) {
            this.messageResult = LanguageUtil.get(bundle, HelpPopUpPortletConstants.ERROR_PHONE_NUMBER);
            return false;
        }

        // Message
        if (Validator.isNull(this.message)) {
        	this.messageResult = LanguageUtil.get(bundle, HelpPopUpPortletConstants.ERROR_MESSAGE);
            return false;
        }

        return true;
    }

    /**
     * Recuperation du publik ID avec la session
     */
    private String getPublikID(PortletRequest request) {
        LiferayPortletRequest liferayPortletRequest = PortalUtil.getLiferayPortletRequest(request);
        HttpServletRequest originalRequest = liferayPortletRequest.getHttpServletRequest();
        return SessionParamUtil.getString(originalRequest, HelpPopUpPortletConstants.PUBLIK_INTERNAL_ID);
    }

    @Reference(unbind = "-")
    protected void setHelpRequestLocalService(HelpRequestLocalService helpRequestLocalService) {
        _helpRequestLocalService = helpRequestLocalService;
    }

    private HelpRequestLocalService _helpRequestLocalService;
}
