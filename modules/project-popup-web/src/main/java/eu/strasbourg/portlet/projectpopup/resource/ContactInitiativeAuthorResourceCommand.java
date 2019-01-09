package eu.strasbourg.portlet.projectpopup.resource;

import java.io.IOException;
import java.io.PrintWriter;

import javax.portlet.PortletException;
import javax.portlet.PortletRequest;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;

import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.SessionParamUtil;
import com.liferay.portal.kernel.util.Validator;

import eu.strasbourg.service.oidc.model.PublikUser;
import eu.strasbourg.service.oidc.service.PublikUserLocalServiceUtil;
import eu.strasbourg.service.project.model.Initiative;
import eu.strasbourg.service.project.service.InitiativeLocalServiceUtil;
import eu.strasbourg.utils.MailHelper;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

@Component(
	    immediate = true,
	    property = {
	    	"javax.portlet.name=" + StrasbourgPortletKeys.PROJECT_POPUP_WEB,
	    	"mvc.command.name=ContactInitiativeAuthor"
	    },
	    service = MVCResourceCommand.class
	)
public class ContactInitiativeAuthorResourceCommand implements MVCResourceCommand {
	
	// Gestion et contexte de la requete
    private String publikID;
    private String message;
    private PublikUser user;
    private String subject;
    private String mailMessage;
    
	private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());
	
	@Override
	public boolean serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws PortletException {
		
		boolean result = false;
		this.message = "";
		
		try {
			// Recuperation de l'utilsiteur Publik ayant lance la demande
	        this.publikID = getPublikID(resourceRequest);
			
	        // Recuperation des informations du formulaire
	        this.subject = HtmlUtil.stripHtml(ParamUtil.getString(resourceRequest, "subject"));
	        this.mailMessage = HtmlUtil.stripHtml(ParamUtil.getString(resourceRequest, "message"));
	        String lastname = HtmlUtil.stripHtml(ParamUtil.getString(resourceRequest, "lastname")); //Non utilisé pour le moment
	        String firstname = HtmlUtil.stripHtml(ParamUtil.getString(resourceRequest, "firstname")); //Non utilisé pour le moment
	        String mobile = HtmlUtil.stripHtml(ParamUtil.getString(resourceRequest, "mobile")); //Non utilisé pour le moment
	        String email = HtmlUtil.stripHtml(ParamUtil.getString(resourceRequest, "email"));
	        
	        if(validate(resourceRequest)) {
	        	
	        	AssetEntry assetEntry = AssetEntryLocalServiceUtil.getAssetEntry(ParamUtil.getLong(resourceRequest, "entryId"));
	        	Initiative ini = InitiativeLocalServiceUtil.getInitiative(assetEntry.getClassPK());
	        	String authorMail = ini.getAuthor().getEmail();
	        	
	        	// envoi du mail aux utilisateurs
	        	result = MailHelper.sendMailWithPlainText(email, authorMail, this.subject, this.mailMessage);
	        }
        } catch (Exception e) {
            _log.error(e);
            this.message = "Message de l'erreur : Erreur technique";
            result = false;
        }
		
		// Retour des informations de la requete en JSON
        JSONObject jsonResponse = JSONFactoryUtil.createJSONObject();
        jsonResponse.put("message", this.message);
        jsonResponse.put("result", result);
		
        // Recuperation de l'élément d'écriture de la réponse
        PrintWriter writer = null;
        
        try {
			writer = resourceResponse.getWriter();
		} catch (IOException e) {
			_log.error(e);
		}
        writer.print(jsonResponse.toString());
        
		return result;
	}
	
	/**
	 * Validation des champs de la requete
	 * @return Valide ou pas
	 */
	private boolean validate(PortletRequest request) {
        
        // utilisateur 
        if (this.publikID == null || this.publikID.isEmpty()) {
            this.message = "Utilisateur non reconnu";
            return false;
        } else {
        	this.user = PublikUserLocalServiceUtil.getByPublikUserId(this.publikID);
        	
        	if (this.user.isBanned()) {
        		this.message = "Vous ne pouvez envoyer un message à l'hautheur de l'initiative";
        		return false;
        	} else if (this.user.getPactSignature() == null) {
        		this.message = "Vous devez signer le Pacte pour envoyer un message à l'hautheur de l'initiative";
        		return false;
        	}
        }

        // sujet du mail
        if (Validator.isNull(this.subject)) {
        	this.message = "Sujet non valide";
            return false;
        }

        // message du mail
        if (Validator.isNull(this.mailMessage)) {
        	this.message = "Message non valide";
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
        return SessionParamUtil.getString(originalRequest, "publik_internal_id");
    }
}
