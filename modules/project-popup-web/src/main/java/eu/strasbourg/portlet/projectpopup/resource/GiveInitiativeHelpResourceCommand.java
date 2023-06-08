package eu.strasbourg.portlet.projectpopup.resource;

import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.template.Template;
import com.liferay.portal.kernel.template.TemplateConstants;
import com.liferay.portal.kernel.template.TemplateManagerUtil;
import com.liferay.portal.kernel.template.TemplateResource;
import com.liferay.portal.kernel.template.URLTemplateResource;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.SessionParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import eu.strasbourg.service.oidc.model.PublikUser;
import eu.strasbourg.service.oidc.service.PublikUserLocalServiceUtil;
import eu.strasbourg.service.project.model.Initiative;
import eu.strasbourg.service.project.model.InitiativeHelp;
import eu.strasbourg.service.project.model.InitiativeHelpModel;
import eu.strasbourg.service.project.service.InitiativeHelpLocalServiceUtil;
import eu.strasbourg.service.project.service.InitiativeLocalServiceUtil;
import eu.strasbourg.utils.MailHelper;
import eu.strasbourg.utils.PublikApiClient;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import org.osgi.service.component.annotations.Component;

import javax.mail.internet.InternetAddress;
import javax.portlet.PortletException;
import javax.portlet.PortletRequest;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + StrasbourgPortletKeys.PROJECT_POPUP_WEB,
		"mvc.command.name=giveInitiativeHelp"
	},
	service = MVCResourceCommand.class
)
public class GiveInitiativeHelpResourceCommand implements MVCResourceCommand {
	
	// Constantes des ID de recuperation d'informations de la requete
	private static final String ENTRY_ID = "entryId";
	private static final String INITIATIVE_HELP_TYPE_IDS = "initiativeHelpTypeIds";
	private static final String INITIATIVE_HELP_MESSAGE = "initiativeHelpMessage";
	private static final String BIRTHDAY = "birthday";
    private static final String ADDRESS = "address";
    private static final String CITY = "city";
    private static final String POSTALCODE = "postalcode";
    private static final String PHONE = "phone";
    private static final String MOBILE = "mobile";
    private static final String DISPLAY_HELP = "displayHelp";
    private static final String SAVEINFO = "saveinfo";
    private static final String FORMATTED_DATE_PATTERN = "dd/MM/yyyy";
	
    @Override
	public boolean serveResource(ResourceRequest request, ResourceResponse response) throws PortletException {
        
        // Initialisations respectives de : resultat probant de la requete, sauvegarde ou non des informations Publik, message de retour
        boolean result = false;
        boolean saveInfo = false;

        // Initialisations respectives de : isUserAlredyHelp
        boolean isUserAlredyHelp = false;
        
        // Recuperation de l'utilsiteur Publik ayant lance la demande
        PublikUser user = null;
        String publikID = getPublikID(request);
        if (publikID != null && !publikID.isEmpty()) {
            user = PublikUserLocalServiceUtil.getByPublikUserId(publikID);
        }
        
        // Recuperation de l'initiative en question
        Initiative initiative = null;
        long entryID = ParamUtil.getLong(request, ENTRY_ID);
        try {
            AssetEntry assetEntry = AssetEntryLocalServiceUtil.getAssetEntry(entryID);
            initiative = InitiativeLocalServiceUtil.getInitiative(assetEntry.getClassPK());
        } catch (PortalException e1) {
            _log.error(e1);
        }
        
        // Verification de la validite des informations
        String message = validate(publikID, user,  initiative);
        if (message.equals("")) {
        	
        	// Mise a jour des informations du compte Publik si requete valide et demande par l'utilisateur
            saveInfo = ParamUtil.getBoolean(request, SAVEINFO);

            // Recuperation des informations du formulaire
            String initiativeHelpTypeIds = ParamUtil.getString(request, INITIATIVE_HELP_TYPE_IDS);
            String initiativeHelpMessage = ParamUtil.getString(request, INITIATIVE_HELP_MESSAGE);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat dateFormat = new SimpleDateFormat(FORMATTED_DATE_PATTERN);
            Date birthday = ParamUtil.getDate(request, BIRTHDAY, dateFormat);
            String dateNaiss = sdf.format(birthday);
            String address = HtmlUtil.stripHtml(ParamUtil.getString(request, ADDRESS));
            String city = HtmlUtil.stripHtml(ParamUtil.getString(request, CITY));
            long postalcode = ParamUtil.getLong(request, POSTALCODE);
            String phone = HtmlUtil.stripHtml(ParamUtil.getString(request, PHONE));
            String mobile = HtmlUtil.stripHtml(ParamUtil.getString(request, MOBILE));
            boolean displayHelp = ParamUtil.getBoolean(request, DISPLAY_HELP);

            if (saveInfo) {
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
            
            // Existance d'une aide de l'utilisateur pour cette initiative
            InitiativeHelp existantInitiativeHelp = null;
            if (initiative != null) {
                existantInitiativeHelp = InitiativeHelpLocalServiceUtil.getByPublikUserIdAndInitiativeId(
                        publikID,
                        initiative.getInitiativeId());
            }
            isUserAlredyHelp = existantInitiativeHelp != null;
            
            // Selon le resultat precedent, etrait ou ajout d'une aide
            if(isUserAlredyHelp)
            	result = removeInitiativeHelp(existantInitiativeHelp);
        	else {
        		result = sendInitiativeHelp(request, initiativeHelpTypeIds, initiativeHelpMessage, publikID, initiative, displayHelp);
        		
        		if(result)
        			//envoi du mail de confirmation au porteur et à l'administrateur
        			sendHelpMailConfirmation(request, publikID, initiative, initiativeHelpMessage);
        	}
        }
        
        // Retour des informations de la requete en JSON
        JSONObject jsonResponse = JSONFactoryUtil.createJSONObject();
        jsonResponse.put("result", result);
        jsonResponse.put("cmd", isUserAlredyHelp ? "remove-initiative-help" : "send-initiative-help");
        jsonResponse.put("message", message);
        jsonResponse.put("savedInfo", saveInfo);

        // Recuperation de l'élément d'écriture de la réponse
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
        } catch (IOException e) {
        	_log.error(e);
        }
        if (writer != null) {
            writer.print(jsonResponse.toString());
        }

        return result;
	}

    /**
	 * Envoi du mail de confirmation de l'aide au porteur de l'initiative et à l'administrateur du site
	 */
    private void sendHelpMailConfirmation(ResourceRequest request, String userId, Initiative initiative, String initiativeHelpMessage) {
    	
    	try {
	    	JSONObject jsonUser = PublikApiClient.getUserDetails(userId);
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
            context.put("Title", initiative.getTitle());
            context.put("Message", initiativeHelpMessage);

            StringWriter out = new StringWriter();

            //Chargement du template contenant le corps du mail
            TemplateResource templateResourceBody = new URLTemplateResource("0",
                    Objects.requireNonNull(this.getClass().getClassLoader()
                            .getResource("/templates/contact-mail-copy-body-fr_FR.ftl")));
            Template bodyTemplate = TemplateManagerUtil.getTemplate(
                    TemplateConstants.LANG_TYPE_FTL, templateResourceBody, false);

            // Traitement du template corps
            bodyTemplate.putAll(context);
            bodyTemplate.processTemplate(out);
            String mailBody = out.toString();
			
			String subject = jsonUser.getString("first_name") + " " + jsonUser.getString("last_name") 
			+ " " + LanguageUtil.get(PortalUtil.getHttpServletRequest(request), "modal.give.initiative.help.mail.confirmation.subject");
			
			InternetAddress fromAddress = new InternetAddress("no-reply@no-reply.strasbourg.eu",
					themeDisplay.getScopeGroup().getName(request.getLocale()));
			
			InternetAddress[] toAddresses = new InternetAddress[0];
			InternetAddress address = new InternetAddress(initiative.getAuthor().getEmail());
			toAddresses = ArrayUtil.append(toAddresses, address);
			
			// envoi du mail aux utilisateurs
			MailHelper.sendMailWithHTML(fromAddress, toAddresses, subject, mailBody);
		} catch (Exception e) {
            _log.error(e.getMessage(), e);
		}
    }
	
	/**
	 * Envoi de la demande d'aide
	 * @return Si la demande s'est bien passee
	 */
	private boolean sendInitiativeHelp(ResourceRequest request, String initiativeHelpTypeIds, String initiativeHelpMessage, String publikID, Initiative initiative, boolean displayHelp) throws PortletException {
        ServiceContext sc;
        InitiativeHelp initiativeHelp;
        
        try {
            sc = ServiceContextFactory.getInstance(request);
            sc.setWorkflowAction(WorkflowConstants.ACTION_SAVE_DRAFT);

            initiativeHelp = InitiativeHelpLocalServiceUtil.createInitiativeHelp(sc);

            initiativeHelp.setHelpTypes(initiativeHelpTypeIds);
            initiativeHelp.setMessage(initiativeHelpMessage);
            initiativeHelp.setPublikUserId(publikID);
            initiativeHelp.setInitiativeId(initiative != null ? initiative.getInitiativeId() : 0);
            initiativeHelp.setHelpDisplay(displayHelp);

            initiativeHelp = InitiativeHelpLocalServiceUtil.updateInitiativeHelp(initiativeHelp);

        } catch (PortalException e) {
            _log.error(e);
            throw new PortletException(e);
        }
        
        _log.info("Aide d'initiative cree : " + initiativeHelp);
        return true;
    }
	
	/**
	 * Envoi de la demande de retrait de l'aide
	 * @return Si la demande s'est bien passee
	 */
	private boolean removeInitiativeHelp(InitiativeHelpModel existantInitiativeHelp) {
        InitiativeHelp initiativeHelp = InitiativeHelpLocalServiceUtil.removeInitiativeHelp(existantInitiativeHelp.getInitiativeHelpId());
        
        _log.info("Aide d'initiative supprime : " + initiativeHelp);
        return true;
        
	}
	
	/**
	 * Verification des information du formulaire issu de la requete et validation
	 * du contexte fonctionnel de la requete (ex: vote possible, entite perime, etc)
	 * @return le message d'erreur s'il y a une erreur
	 */
	private String validate(String publikID, PublikUser user, Initiative initiative) {
        
        // utilisateur 
        if (publikID == null || publikID.isEmpty()) {
            return "Utilisateur non recconu";
        } else {
        	if (user.isBanned()) {
                return "Vous ne pouvez soutenir ce projet";
        	} else if (user.getPactSignature() == null) {
                return "Vous devez signer le Pacte pour proposer votre aide";
        	}
        }
        
        // Initiative
        if (initiative == null) {
            return  "Erreur lors de la recherche de l'initiative";
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
	
	/**
     * Le log
     */
    private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());
	
}