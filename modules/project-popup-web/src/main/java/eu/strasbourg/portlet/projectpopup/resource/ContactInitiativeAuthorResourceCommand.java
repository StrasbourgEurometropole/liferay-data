package eu.strasbourg.portlet.projectpopup.resource;

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
import org.osgi.service.component.annotations.Component;

import javax.portlet.PortletRequest;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.PrintWriter;

@Component(
	    immediate = true,
	    property = {
	    	"javax.portlet.name=" + StrasbourgPortletKeys.PROJECT_POPUP_WEB,
	    	"mvc.command.name=ContactInitiativeAuthor"
	    },
	    service = MVCResourceCommand.class
	)
public class ContactInitiativeAuthorResourceCommand implements MVCResourceCommand {
    
	private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());
	
	@Override
	public boolean serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) {
		
		boolean result = false;

		// Recuperation de l'utilsiteur Publik ayant lance la demande
		PublikUser user = null;
		String publikID = getPublikID(resourceRequest);
		if (publikID != null && !publikID.isEmpty()) {
			user = PublikUserLocalServiceUtil.getByPublikUserId(publikID);
		}

		// Recuperation des informations du formulaire
		String subject = HtmlUtil.stripHtml(ParamUtil.getString(resourceRequest, "subject"));
		String mailMessage = HtmlUtil.stripHtml(ParamUtil.getString(resourceRequest, "message"));
		String email = HtmlUtil.stripHtml(ParamUtil.getString(resourceRequest, "email"));

		String message = validate(publikID, user, subject, mailMessage);
		if (message.equals("")) {
			try {
				AssetEntry assetEntry = AssetEntryLocalServiceUtil.getAssetEntry(ParamUtil.getLong(resourceRequest, "entryId"));
				Initiative ini = InitiativeLocalServiceUtil.getInitiative(assetEntry.getClassPK());
				String authorMail = ini.getAuthor().getEmail();

				// envoi du mail aux utilisateurs
				result = MailHelper.sendMailWithPlainText(email, authorMail, subject, mailMessage);
			} catch (Exception e) {
				_log.error(e);
				message = "Message de l'erreur : Erreur technique";
			}

			if(message.equals("")) {
				result = true;
			}
		}
		
		// Retour des informations de la requete en JSON
        JSONObject jsonResponse = JSONFactoryUtil.createJSONObject();
        jsonResponse.put("message", message);
        jsonResponse.put("result", result);
		
        // Recuperation de l'élément d'écriture de la réponse
        PrintWriter writer = null;
        
        try {
			writer = resourceResponse.getWriter();
		} catch (IOException e) {
			_log.error(e);
		}
		if (writer != null) {
			writer.print(jsonResponse.toString());
		}

		return result;
	}
	
	/**
	 * Validation des champs de la requete
	 * @return Valide ou pas
	 */
	private String validate(String publikID, PublikUser user, String subject, String mailMessage) {
        
        // utilisateur 
        if (publikID == null || publikID.isEmpty()) {
			return "Utilisateur non reconnu";
        } else {
        	
        	if (user.isBanned()) {
				return "Vous ne pouvez envoyer un message à l'hautheur de l'initiative";
        	} else if (user.getPactSignature() == null) {
				return "Vous devez signer le Pacte pour envoyer un message à l'hautheur de l'initiative";
        	}
        }

        // sujet du mail
        if (Validator.isNull(subject)) {
			return "Sujet non valide";
        }

        // message du mail
        if (Validator.isNull(mailMessage)) {
			return "Message non valide";
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
