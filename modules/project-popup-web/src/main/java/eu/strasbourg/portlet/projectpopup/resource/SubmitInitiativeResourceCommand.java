package eu.strasbourg.portlet.projectpopup.resource;

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
import com.liferay.portal.kernel.util.SessionParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import eu.strasbourg.service.oidc.model.PublikUser;
import eu.strasbourg.service.oidc.service.PublikUserLocalServiceUtil;
import eu.strasbourg.service.project.model.Initiative;
import eu.strasbourg.service.project.service.InitiativeLocalServiceUtil;
import eu.strasbourg.utils.MailHelper;
import eu.strasbourg.utils.PublikApiClient;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import org.apache.commons.lang3.ArrayUtils;
import org.osgi.service.component.annotations.Component;

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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

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
	private static final String DISTRICT = "quartier";
	private static final String THEMATIC = "theme";
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

	private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());
	
	@Override
	public boolean serveResource(ResourceRequest request, ResourceResponse response) throws PortletException {
        // Recuperation du contexte de la requete
        ServiceContext sc = null;
        try {
            sc = ServiceContextFactory.getInstance(request);
        } catch (PortalException e) {
            _log.error(e);
        }
        DateFormat dateFormat = new SimpleDateFormat(PATTERN);

        // Initialisations respectives de : resultat probant de la requete, sauvegarde ou non des informations Publik, message de retour, format de date
        boolean result = false;
        boolean savedInfo = false;

        // Recuperation de l'utilsiteur Publik ayant lance la demande
        PublikUser user = null;
        String publikID = getPublikID(request);
        if (publikID != null && !publikID.isEmpty()) {
            user = PublikUserLocalServiceUtil.getByPublikUserId(publikID);
        }

        // Recuperation des informations du formulaire
        String title = HtmlUtil.stripHtml(ParamUtil.getString(request, TITLE));
        String description = HtmlUtil.stripHtml(ParamUtil.getString(request, DESCRIPTION));
        long[] districtId = ParamUtil.getLongValues(request, DISTRICT);
        long thematicId = ParamUtil.getLong(request, THEMATIC);
        long projectId = ParamUtil.getLong(request, PROJECT);
        String place = HtmlUtil.stripHtml(ParamUtil.getString(request, PLACE));
        String video = HtmlUtil.stripHtml(ParamUtil.getString(request, VIDEO));
        String address = HtmlUtil.stripHtml(ParamUtil.getString(request, ADDRESS));
        String city = HtmlUtil.stripHtml(ParamUtil.getString(request, CITY));
        long postalcode = ParamUtil.getLong(request, POSTALCODE);
        String phone = HtmlUtil.stripHtml(ParamUtil.getString(request, PHONE));
        String mobile = HtmlUtil.stripHtml(ParamUtil.getString(request, MOBILE));
        String inTheNameOf = HtmlUtil.stripHtml(ParamUtil.getString(request, IN_THE_NAME_OF));
		
        // Verification de la validite des informations
        String message = validate(publikID, user, title, description);
        if (message.equals("")) {

        	// Mise a jour des informations du compte Publik si requete valide et demande par l'utilisateur
        	savedInfo = ParamUtil.getBoolean(request, SAVEINFO);
            if (savedInfo) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String dateNaiss = sdf.format(ParamUtil.getDate(request, BIRTHDAY, dateFormat));
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
            if (districtId.length >= 0) {
                identifiants.addAll(java.util.Arrays.asList(ArrayUtils.toObject(districtId)));
            }
            if (projectId != 0) {
                identifiants.add(projectId);
            }
            if (thematicId != 0) {
                identifiants.add(thematicId);
            }
            long[] ids = new long[identifiants.size()];
            for (int i = 0; i < identifiants.size(); i++) {
                ids[i] = identifiants.get(i);
            }

            if (sc != null) {
                sc.setWorkflowAction(WorkflowConstants.ACTION_SAVE_DRAFT);
                sc.setAssetCategoryIds(ids);
            }

            Initiative initiative = null;
            try {
                initiative = InitiativeLocalServiceUtil.createInitiative(sc);

                initiative.setTitle(title);
                initiative.setDescription(description);
                initiative.setInTheNameOf(inTheNameOf);
                initiative.setPlaceTextArea(place);
                initiative.setVideoUrl(video);
                initiative.setPublikId(publikID);
                initiative = uploadFile(initiative, request);

                initiative = InitiativeLocalServiceUtil.updateInitiative(initiative, sc);

            } catch (PortalException | IOException e) {
                _log.error(e);
                throw new PortletException(e);
            }
            _log.info("Initiative cree : " + initiative);

            if(message.equals("")) {
                result = true;
                sendInitiativeMailConfirmation(request, title, description, user);
            }
        }
        
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
	 * Envoi du mail de confirmation de soumission d'une initiative
	 */
    private void sendInitiativeMailConfirmation(ResourceRequest request, String title,
                                                String description, PublikUser user) {
    	
    	try {
	    	ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
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
                            .getResource("META-INF/resources/templates/contact-mail-initiative-copy-body-fr_FR.ftl")));
            Template bodyTemplate = TemplateManagerUtil.getTemplate(
                    TemplateConstants.LANG_TYPE_FTL, templateResourceBody, false);

            // Traitement du template corps
            bodyTemplate.putAll(context);
            bodyTemplate.processTemplate(out);
            String mailBody = out.toString();
			
			String subject = LanguageUtil.get(PortalUtil.getHttpServletRequest(request), "modal.submit.initiative.mail.information");
			
			InternetAddress fromAddress = new InternetAddress("no-reply@no-reply.strasbourg.eu",
					themeDisplay.getScopeGroup().getName(request.getLocale()));
			
			InternetAddress[] toAddresses = new InternetAddress[0];
			InternetAddress address = new InternetAddress(user.getEmail());
			toAddresses = ArrayUtil.append(toAddresses, address);

            // Copie carbone invisible
            InternetAddress bccAddress = new InternetAddress("participer@strasbourg.eu");
			
			// envoi du mail aux utilisateurs
			MailHelper.sendMailWithBCCWithHTML(fromAddress, toAddresses, bccAddress, subject, mailBody);
		} catch (Exception e) {
            _log.error(e.getMessage(), e);
		}
    }
	
	 /**
     * Recuperer l'image uploadée par l'utilisateur.
     *
     * @param initiative Entite concernee
     * @return l'inititive avec l'imageId
      * @throws PortalException Fichier sans image
      * @throws IOException Pb récupération de la photo
     */
    private Initiative uploadFile(Initiative initiative, ResourceRequest request) throws PortalException, IOException {
    	
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
                        													"Photo ATQ");
                // Dossier d'upload de l'entite
                DLFolder folder = DLFolderLocalServiceUtil.getFolder(themeDisplay.getScopeGroupId(),
                                									folderparent.getFolderId(),
                                									"Uploads");
                // Ajout du fichier
                FileEntry fileEntry = DLAppLocalServiceUtil.addFileEntry(
                        sc.getUserId(), folder.getRepositoryId(),
                        folder.getFolderId(), photo.getName(),
                        MimeTypesUtil.getContentType(photo),
                        photo.getName(), initiative.getTitle(),
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
	private boolean validateFileName(ResourceRequest request) {
        boolean result = true;
        UploadRequest uploadRequest = PortalUtil.getUploadPortletRequest(request);
        String fileName = uploadRequest.getFileName(PHOTO);
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
	private String validate(String publikID, PublikUser user, String title, String description) {
        
        // utilisateur 
        if (publikID == null || publikID.isEmpty()) {
            return "Utilisateur non reconnu";
        } else {
        	if (user.isBanned()) {
                return "Vous ne pouvez soumettre une initiative";
        	} else if (user.getPactSignature() == null) {
                return "Vous devez signer le Pacte pour soumettre une initiative";
        	}
        }

        // title
        if (Validator.isNull(title)) {
            return "Titre non valide";
        }

        // description
        if (Validator.isNull(description)) {
            return "Description non valide";
        }

        return "";
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
