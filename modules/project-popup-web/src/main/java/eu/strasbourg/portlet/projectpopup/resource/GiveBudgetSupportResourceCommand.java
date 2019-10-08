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
import eu.strasbourg.service.project.model.BudgetParticipatif;
import eu.strasbourg.service.project.model.BudgetSupport;
import eu.strasbourg.service.project.service.BudgetParticipatifLocalServiceUtil;
import eu.strasbourg.service.project.service.BudgetSupportLocalServiceUtil;

import eu.strasbourg.utils.PublikApiClient;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

@Component(
	immediate = true,
    property = {
            "javax.portlet.name=" + StrasbourgPortletKeys.PROJECT_POPUP_WEB,
            "mvc.command.name=giveBudgetSupport"
    },
    service = MVCResourceCommand.class
)
public class GiveBudgetSupportResourceCommand implements MVCResourceCommand {
	
	// Constantes des ID de recuperation d'informations de la requete
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
    private int nbUserSupports;
    private int nbUserEntrySupports;
    private int nbEntrySupports;
    private long entryID;
    private BudgetParticipatif budgetParticipatif;
    private DateFormat dateFormat;
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
        this.entryID = ParamUtil.getLong(request, "entryId");
        
        // Recuperation des informations du formulaire
        this.dateFormat = new SimpleDateFormat(FORMATTED_DATE_PATTERN);
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
                String dateNaiss = sdf.format(this.birthday);
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
            result = sendBudgetSupport(request);
            
            // Recuperation du nombre de vote de l'utilisateur pour l'entite courante
            this.nbUserEntrySupports = this.budgetParticipatif.getNbSupportOfUser(this.publikID);
            this.nbEntrySupports = (int) this.budgetParticipatif.getNbSupports();
            this.nbUserSupports++;
        }
        
        // Retour des informations de la requete en JSON
        JSONObject jsonResponse = JSONFactoryUtil.createJSONObject();
        jsonResponse.put("result", result);
        jsonResponse.put("message", this.message);
        jsonResponse.put("savedInfo", saveInfo);
        
        JSONObject updatedSupportsInfo = JSONFactoryUtil.createJSONObject();
        
        updatedSupportsInfo.put("nbUserSupports", this.nbUserSupports);
        updatedSupportsInfo.put("nbUserEntrySupports", this.nbUserEntrySupports);
        updatedSupportsInfo.put("nbEntrySupports", this.nbEntrySupports);
        updatedSupportsInfo.put("nbSupportForActivePhase", this.budgetParticipatif.getPhase().getNumberOfVote());
        
        jsonResponse.put("updatedSupportsInfo", updatedSupportsInfo);

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
	 * Envoi de la demande de soutien
	 * @return Si la demande s'est bien passee
	 */
	private boolean sendBudgetSupport(ResourceRequest request) throws PortletException {
        ServiceContext sc;
        BudgetSupport budgetSupport;
        
        try {
            sc = ServiceContextFactory.getInstance(request);
            sc.setWorkflowAction(WorkflowConstants.ACTION_SAVE_DRAFT);

            budgetSupport = BudgetSupportLocalServiceUtil.createBudgetSupport(sc);

            budgetSupport.setCitoyenAddress(this.address);
            budgetSupport.setCitoyenBirthday(this.birthday);
            budgetSupport.setCitoyenCity(this.city);
            budgetSupport.setCitoyenFirstname(this.user.getFirstName());
            budgetSupport.setCitoyenLastName(this.user.getLastName());
            budgetSupport.setCitoyenPostalCode(this.postalcode);
            budgetSupport.setCitoyenPhone(this.phone);
            if (!this.mobile.isEmpty())
            	budgetSupport.setCitoyenMobilePhone(this.mobile);
            budgetSupport.setCitoyenMail(this.user.getEmail());
            budgetSupport.setPublikUserId(this.publikID);
            budgetSupport.setBudgetParticipatifId(this.budgetParticipatif.getBudgetParticipatifId());
            budgetSupport = BudgetSupportLocalServiceUtil.updateBudgetSupport(budgetSupport);
        } catch (PortalException e) {
            _log.error(e);
            throw new PortletException(e);
        }
        _log.info("Soutien cree : " + budgetSupport);
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
            this.message = "Utilisateur non reconnu";
            return false;
        } else {
        	this.user = PublikUserLocalServiceUtil.getByPublikUserId(this.publikID);
        	
        	if (this.user.isBanned()) {
        		this.message = "Vous ne pouvez soutenir ce projet";
        		return false;
        	} else if (this.user.getPactSignature() == null) {
        		this.message = "Vous devez signer le Pacte pour soutenir ce projet";
        		return false;
        	}
        }
        
        // budget participatif
        try {
            AssetEntry assetEntry = AssetEntryLocalServiceUtil.getAssetEntry(this.entryID);
			this.budgetParticipatif = BudgetParticipatifLocalServiceUtil.getBudgetParticipatif(assetEntry.getClassPK());
			
			if (this.budgetParticipatif != null) {
				if (!this.budgetParticipatif.isVotable()) {
					this.message = "Ce budget participatif n'est pas en phase de vote";
					return false;
				}
			} else {
				this.message = "Erreur lors de la recherche du budget participatif";
				return false;
			}
		} catch (PortalException e1) {
			_log.error(e1);
			this.message = "Erreur lors de la recherche du budget participatif";
			return false;
		}
        
        // nombre de votes de l'utilisateur
        this.nbUserSupports = BudgetParticipatifLocalServiceUtil.countBudgetSupportedByPublikUserInPhase(
        		this.publikID,
        		this.budgetParticipatif.getPhase().getBudgetPhaseId());
        if (this.nbUserSupports >= this.budgetParticipatif.getPhase().getNumberOfVote()) {
        	this.message = "Vous ne pouvez plus voter pour cette phase";
			return false;
        }
        
        return true; //désactivation du controle des champs  de l'usager suite à la désactivation de la popup au premier vote
        /**
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
        **/
        
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
