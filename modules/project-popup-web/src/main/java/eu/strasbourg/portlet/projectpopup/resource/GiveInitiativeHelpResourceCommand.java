package eu.strasbourg.portlet.projectpopup.resource;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.portlet.PortletException;
import javax.portlet.PortletRequest;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;

import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.SessionParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import eu.strasbourg.service.oidc.model.PublikUser;
import eu.strasbourg.service.oidc.service.PublikUserLocalServiceUtil;
import eu.strasbourg.service.project.model.Initiative;
import eu.strasbourg.service.project.model.InitiativeHelp;
import eu.strasbourg.service.project.service.InitiativeHelpLocalServiceUtil;
import eu.strasbourg.service.project.service.InitiativeLocalServiceUtil;
import eu.strasbourg.utils.PublikApiClient;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

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
    private static final String SAVEINFO = "saveinfo";
    private static final String FORMATTED_DATE_PATTERN = "dd/MM/yyyy";
    
    // Variables tempons
    private String publikID;
    private PublikUser user;
    private boolean isUserAlredyHelp;
    private long entryID;
    private DateFormat dateFormat;
    private Initiative initiative;
    private InitiativeHelp existantInitiativeHelp;
    private String initiativeHelpTypeIds;
    private String initiativeHelpMessage;
    private Date birthday;
    private String address;
    private String city;
    private long postalcode;
    private String phone;
    private String mobile;
    private String message;
	
    @Override
	public boolean serveResource(ResourceRequest request, ResourceResponse response) throws PortletException {
        
        // Initialisations respectives de : resultat probant de la requete, sauvegarde ou non des informations Publik, message de retour
        boolean result = false;
        boolean saveInfo = false;
        
        // Recuperation de l'utilsiteur Publik ayant lance la demande
        this.publikID = getPublikID(request);
        
        // Recuperation du budget participatif en question
        this.entryID = ParamUtil.getLong(request, ENTRY_ID);
        
        // Recuperation des informations du formulaire
        this.dateFormat = new SimpleDateFormat(FORMATTED_DATE_PATTERN);
        this.initiativeHelpTypeIds = ParamUtil.getString(request, INITIATIVE_HELP_TYPE_IDS);
        this.initiativeHelpMessage = ParamUtil.getString(request, INITIATIVE_HELP_MESSAGE);
        this.birthday = ParamUtil.getDate(request, BIRTHDAY, dateFormat);
        this.address = HtmlUtil.stripHtml(ParamUtil.getString(request, ADDRESS));
        this.city = HtmlUtil.stripHtml(ParamUtil.getString(request, CITY));
        this.postalcode = ParamUtil.getLong(request, POSTALCODE);
        this.phone = HtmlUtil.stripHtml(ParamUtil.getString(request, PHONE));
        this.mobile = HtmlUtil.stripHtml(ParamUtil.getString(request, MOBILE));
        
        // Verification de la validite des informations
        if (validate(request)) {
        	
        	// Mise a jour des informations du compte Publik si requete valide et demande par l'utilisateur
            saveInfo = ParamUtil.getBoolean(request, SAVEINFO);
            if (saveInfo) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String dateNaiss = sdf.format(ParamUtil.getDate(request, "birthday", dateFormat));
                PublikApiClient.setAllUserDetails(
                		this.publikID, 
                		this.user.getLastName(), 
                		this.address, 
                		"" + this.postalcode,
                		this.city,
                		dateNaiss, 
                		this.phone,
                		this.mobile
                );
            }
            
            // Existance d'une aide de l'utilisateur pour cette initiative
            this.existantInitiativeHelp = InitiativeHelpLocalServiceUtil.getByPublikUserIdAndInitiativeId(
					this.publikID, 
					this.initiative.getInitiativeId());
            this.isUserAlredyHelp = this.existantInitiativeHelp != null;
            
            // Selon le resultat precedent, etrait ou ajout d'une aide
            result = this.isUserAlredyHelp ? removeInitiativeHelp(request) : sendInitiativeHelp(request);

        }
        
        // Retour des informations de la requete en JSON
        JSONObject jsonResponse = JSONFactoryUtil.createJSONObject();
        jsonResponse.put("result", result);
        jsonResponse.put("cmd", this.isUserAlredyHelp ? "remove-initiative-help" : "send-initiative-help");
        jsonResponse.put("message", this.message);
        jsonResponse.put("savedInfo", saveInfo);

        // Recuperation de l'élément d'écriture de la réponse
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
        } catch (IOException e) {
        	_log.error(e);
        }
        writer.print(jsonResponse.toString());

        return result;
	}
	
	/**
	 * Envoi de la demande d'aide
	 * @return Si la demande s'est bien passee
	 */
	private boolean sendInitiativeHelp(ResourceRequest request) throws PortletException {
        ServiceContext sc;
        InitiativeHelp initiativeHelp;
        
        try {
            sc = ServiceContextFactory.getInstance(request);
            sc.setWorkflowAction(WorkflowConstants.ACTION_SAVE_DRAFT);

            initiativeHelp = InitiativeHelpLocalServiceUtil.createInitiativeHelp(sc);

            initiativeHelp.setHelpTypes(this.initiativeHelpTypeIds);
            initiativeHelp.setMessage(this.initiativeHelpMessage);
            initiativeHelp.setPublikUserId(this.publikID);
            initiativeHelp.setInitiativeId(this.initiative.getInitiativeId());

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
	private boolean removeInitiativeHelp(ResourceRequest request) throws PortletException {
        InitiativeHelp initiativeHelp;
        
        initiativeHelp = InitiativeHelpLocalServiceUtil.removeInitiativeHelp(existantInitiativeHelp.getInitiativeId());
        
        _log.info("Aide d'initiative supprime : " + initiativeHelp);
        return true;
        
	}
	
	/**
	 * Verification des information du formulaire issu de la requete et validation
	 * du contexte fonctionnel de la requete (ex: vote possible, entite perime, etc)
	 * @return Si la requete est tangible
	 */
	private boolean validate(ResourceRequest request) {
        
        // utilisateur 
        if (this.publikID == null || this.publikID.isEmpty()) {
            this.message = "Utilisateur non recconu";
            return false;
        } else {
        	this.user = PublikUserLocalServiceUtil.getByPublikUserId(this.publikID);
        	
        	if (this.user.isBanned()) {
        		this.message = "Vous ne pouvez soutenir ce projet";
        		return false;
        	} else if (this.user.getPactSignature() == null) {
        		this.message = "Vous devez signer le Pacte pour proposer votre aide";
        		return false;
        	}
        }
        
        // Initiative
        try {
            AssetEntry assetEntry = AssetEntryLocalServiceUtil.getAssetEntry(this.entryID);
			this.initiative = InitiativeLocalServiceUtil.getInitiative(assetEntry.getClassPK());
			
			if (this.initiative == null) {
				this.message = "Erreur lors de la recherche de l'initiative";
				return false;
			}
		} catch (PortalException e1) {
			_log.error(e1);
			this.message = "Erreur lors de la recherche de l'initiative";
			return false;
		}
        
        // birthday
        if (Validator.isNull(this.birthday)) {
            this.message = "Date de naissance non valide";
            return false;
        }

        // city
        if (Validator.isNull(this.city)) {
            this.message = "Ville non valide";
            return false;
        }

        // address
        if (Validator.isNull(this.address)) {
            this.message = "Adresse non valide";
            return false;
        }

        // postalcode
        if (Validator.isNull(this.postalcode)) {
            this.message = "Code postal non valide";
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
	
	/**
     * Le log
     */
    private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());
	
}