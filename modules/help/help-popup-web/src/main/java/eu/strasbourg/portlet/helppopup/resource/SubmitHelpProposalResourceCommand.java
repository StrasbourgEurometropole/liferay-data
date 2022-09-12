package eu.strasbourg.portlet.helppopup.resource;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.service.AssetCategoryLocalServiceUtil;
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
import eu.strasbourg.service.help.service.HelpProposalLocalService;
import eu.strasbourg.service.oidc.model.PublikUser;
import eu.strasbourg.service.oidc.service.PublikUserLocalServiceUtil;
import eu.strasbourg.utils.AssetVocabularyHelper;
import eu.strasbourg.utils.MailHelper;
import eu.strasbourg.utils.StrasbourgPropsUtil;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import eu.strasbourg.utils.constants.VocabularyNames;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component(
    immediate = true,
    property = {
    	"javax.portlet.name=" + StrasbourgPortletKeys.HELP_POPUP_WEB,
    	"mvc.command.name=submitHelpProposal"
    },
    service = MVCResourceCommand.class
)
public class SubmitHelpProposalResourceCommand implements MVCResourceCommand {

    // Champs
    private long helpProposalId;
    private String address;
    private String city;
    private String postalcode;
    private String phoneNumber;
    private String title;
    private String types;
	private String presentation;
    private long helperId;
	private String inTheNameOf;
    private String language;
	private long localisationId;
	private boolean agreement1;
    private boolean agreement2;
    private boolean agreement3;

    // Gestion et contexte de la requete
    private String publikID;
    private PublikUser user;
    private String message;

	private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());

    private ResourceBundle bundle = ResourceBundleUtil.getBundle("content.Language",
            this.getClass().getClassLoader());
	
	@Override
	public boolean serveResource(ResourceRequest request, ResourceResponse response) throws PortletException {
        
        // Initialisations respectives de : resultat probant de la requete, sauvegarde ou non des informations Publik, message de retour, format de date
        boolean result = false;
        this.message = "";

        // Recuperation de l'utilsiteur Publik ayant lance la demande
        this.publikID = getPublikID(request);
        
        // Recuperation des informations du formulaire
        this.address = HtmlUtil.stripHtml(ParamUtil.getString(request, HelpPopUpPortletConstants.ADDRESS));
        this.city = HtmlUtil.stripHtml(ParamUtil.getString(request, HelpPopUpPortletConstants.CITY));
        this.postalcode = HtmlUtil.stripHtml(ParamUtil.getString(request, HelpPopUpPortletConstants.POSTALCODE));
        this.phoneNumber = HtmlUtil.stripHtml(ParamUtil.getString(request, HelpPopUpPortletConstants.PHONE_NUMBER));
        this.title = HtmlUtil.stripHtml(ParamUtil.getString(request, HelpPopUpPortletConstants.TITLE));
        this.types = ParamUtil.getString(request, HelpPopUpPortletConstants.TYPES);
        this.presentation = HtmlUtil.stripHtml(ParamUtil.getString(request, HelpPopUpPortletConstants.PRESENTATION));
        this.helperId = ParamUtil.getLong(request, HelpPopUpPortletConstants.HELPER);
        this.inTheNameOf = HtmlUtil.stripHtml(ParamUtil.getString(request, HelpPopUpPortletConstants.IN_THE_NAME_OF));
        this.language = HtmlUtil.stripHtml(ParamUtil.getString(request, HelpPopUpPortletConstants.LANGUAGE));
        this.localisationId = ParamUtil.getLong(request, HelpPopUpPortletConstants.LOCALISATION);
        this.agreement1 = ParamUtil.getString(request, HelpPopUpPortletConstants.AGREEMENT_1)
                .equals(HelpPopUpPortletConstants.AGREEMENT_1);
        this.agreement2 = ParamUtil.getString(request, HelpPopUpPortletConstants.AGREEMENT_2)
                .equals(HelpPopUpPortletConstants.AGREEMENT_2);
        this.agreement3 = ParamUtil.getString(request, HelpPopUpPortletConstants.AGREEMENT_3)
                .equals(HelpPopUpPortletConstants.AGREEMENT_3);

        // Verification de la validite des informations
        if (validate()) {
         	// Envoi de la demande
            result = saveHelpProposal(request);
            
            if(result)
            	sendHelpProposalMailConfirmation(request);
        }
        
        // Retour des informations de la requete en JSON
        JSONObject jsonResponse = JSONFactoryUtil.createJSONObject();
        jsonResponse.put("result", result);
        jsonResponse.put("message", this.message);

        // Recuperation de l'élément d'écriture de la réponse
        PrintWriter writer;
        try {
            writer = response.getWriter();
            writer.print(jsonResponse.toString());
        } catch (IOException e) {
            _log.error("erreur dans l'enregistrement de la proposition d'aide : ", e);
        }
        return result;
	}
	
	private boolean saveHelpProposal(ResourceRequest request) throws PortletException {
		ServiceContext sc;
        HelpProposal helpProposal;
        
        try {
            sc = ServiceContextFactory.getInstance(request);
            List<Long> identifiants = new ArrayList<>();
            String[] typeIds = this.types.split("-");
            for (String typeId : typeIds) {
                if (!typeId.equals("-")) {
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

            helpProposal = _helpProposalLocalService.createHelpProposal(sc);
            this.helpProposalId = helpProposal.getHelpProposalId();

            helpProposal.setModifiedByUserDate(new Date());
            helpProposal.setAddress(this.address);
            helpProposal.setCity(this.city);
            helpProposal.setPostalCode(Long.parseLong(this.postalcode));
            helpProposal.setPhoneNumber(this.phoneNumber);
            helpProposal.setTitle(this.title, Locale.FRANCE);
            helpProposal.setDescription(this.presentation, Locale.FRANCE);
            helpProposal.setInTheNameOf(this.inTheNameOf);
            helpProposal.setSpokenLanguages(this.language, Locale.FRANCE);
            helpProposal.setPublikId(this.publikID);
            helpProposal = uploadFile(helpProposal, request);
            helpProposal.setAgreementSigned1(this.agreement1);
            helpProposal.setAgreementSigned2(this.agreement2);
            helpProposal.setAgreementSigned3(this.agreement3);

            _helpProposalLocalService.updateHelpProposal(helpProposal, sc);
            
        } catch (PortalException | IOException e) {
            _log.error(e);
            this.message = e.getMessage();
            return false;
        }
        _log.info("Proposition d'aide cree : " + helpProposal);
        this.message = ""+helpProposal.getHelpProposalId();
        return true;
    }
	
	/**
	 * Envoi du mail de confirmation de soumission d'une initiative
	 */
    private void sendHelpProposalMailConfirmation(ResourceRequest request) {
    	
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
            // Retourne l'URL de la page d'accueil
            context.put("domaine", themeDisplay.getScopeGroup().getDisplayURL(themeDisplay));
            context.put("detailURL", "/detail-aide/-/entity/id/" + this.helpProposalId);
            context.put("helpProposalID", this.helpProposalId);

            StringWriter out = new StringWriter();

            //Chargement du template contenant le corps du mail
            TemplateResource templateResourceBody = new URLTemplateResource("0",
                    Objects.requireNonNull(this.getClass().getClassLoader()
                            .getResource("META-INF/resources/templates/contact-mail-help-proposal-copy-body-fr_FR.ftl")));
            Template bodyTemplate = TemplateManagerUtil.getTemplate(
                    TemplateConstants.LANG_TYPE_FTL, templateResourceBody, false);

            // Traitement du template corps
            bodyTemplate.putAll(context);
            bodyTemplate.processTemplate(out);
            String mailBody = out.toString();
			
			String subject = LanguageUtil.get(PortalUtil.getHttpServletRequest(request), "modal.submit.help-proposal.mail.information") + this.title;
			
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
            MailHelper.sendMailWithBCCWithHTML(fromAddress, toAddresses, bccAddress, subject, mailBody);
		} catch (Exception e) {
            _log.error(e.getMessage(), e);
		}
    }
	
	 /**
     * Recuperer l'image uploadée par l'utilisateur.
     *
     * @param helpProposal Entite concernee
     * @return l'aide avec l'imageId
     */
    private HelpProposal uploadFile(HelpProposal helpProposal, ResourceRequest request) throws IOException, PortalException {
    	
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
                                									"Uploads");
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
            return helpProposal;
            
        } else {
            throw new PortalException(LanguageUtil.get(bundle, HelpPopUpPortletConstants.ERROR_EXTENSION));
        }
    }
	
	/**
	 * Validation du nom du fichier photo
	 * @return Valide ou pas
	 */
	private boolean validateFileName(ResourceRequest request) {
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
            this.message = LanguageUtil.get(bundle, HelpPopUpPortletConstants.ERROR_USER_NO_FOUND);
            return false;
        } else {
        	this.user = PublikUserLocalServiceUtil.getByPublikUserId(this.publikID);
        }

        // consentements
        if (!this.agreement1 || !this.agreement2) {
            this.message = LanguageUtil.get(bundle, HelpPopUpPortletConstants.ERROR_AGREEMENTS);
            return false;
        }

        // title
        if (Validator.isNull(this.title)) {
            this.message =  LanguageUtil.get(bundle, HelpPopUpPortletConstants.ERROR_TITLE);
            return false;
        }

        // présentation
        if (Validator.isNull(this.presentation)) {
            this.message = LanguageUtil.get(bundle, HelpPopUpPortletConstants.ERROR_PRESENTATION);
            return false;
        }

        // address
        if (Validator.isNull(this.address)) {
            this.message = LanguageUtil.get(bundle, HelpPopUpPortletConstants.ERROR_ADDRESS);
            return false;
        }

        // city
        if (Validator.isNull(this.city)) {
            this.message = LanguageUtil.get(bundle, HelpPopUpPortletConstants.ERROR_CITY);
            return false;
        }

        // postalcode
        if (Validator.isNull(this.postalcode)) {
            this.message = LanguageUtil.get(bundle, HelpPopUpPortletConstants.ERROR_POSTAL_CODE);
            return false;
        }
        Pattern p = Pattern.compile(LanguageUtil.get(bundle, HelpPopUpPortletConstants.REGEX_POSTAL_CODE));
        Matcher m = p.matcher(this.postalcode);
        if (!m.matches()) {
            this.message = LanguageUtil.get(bundle, HelpPopUpPortletConstants.ERROR_POSTAL_CODE);
            return false;
        }

        // Téléphone
        if (Validator.isNull(this.phoneNumber)) {
            this.message = LanguageUtil.get(bundle, HelpPopUpPortletConstants.ERROR_PHONE_NUMBER);
            return false;
        }

        // Types d'aide
        if (Validator.isNull(this.types)) {
            this.message = LanguageUtil.get(bundle, HelpPopUpPortletConstants.ERROR_HELP_TYPE);
            return false;
        }

        // type d'aidant
        if (Validator.isNull(this.helperId)) {
            this.message = LanguageUtil.get(bundle, HelpPopUpPortletConstants.ERROR_HELPER_TYPE);
            return false;
        }

        // Déposé au nom de
        if (Validator.isNull(this.inTheNameOf)) {
            this.message = LanguageUtil.get(bundle, HelpPopUpPortletConstants.ERROR_IN_THE_NAME_OF);
            return false;
        }

        // Localisation
        if (Validator.isNull(this.localisationId)) {
            this.message = LanguageUtil.get(bundle, HelpPopUpPortletConstants.ERROR_TERRITORY);
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
    protected void setHelpProposalLocalService(HelpProposalLocalService helpProposalLocalService) {
        _helpProposalLocalService = helpProposalLocalService;
    }

    private HelpProposalLocalService _helpProposalLocalService;

}
