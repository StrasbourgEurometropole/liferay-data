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
		
		// Recuperation du contexte de la requete
		LiferayPortletRequest liferayPortletRequest = PortalUtil.getLiferayPortletRequest(request);
        HttpServletRequest originalRequest = liferayPortletRequest.getHttpServletRequest();
        
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
                SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd");
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
            result = sendBudgetSupport(request);
            
        }
        
        // Récupération du json des entités
        JSONObject jsonResponse = JSONFactoryUtil.createJSONObject();
        jsonResponse.put("result", result);
        jsonResponse.put("message", this.message);
        jsonResponse.put("savedInfo", saveInfo);

        // Recuperation de l'élément d'écriture de la réponse
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
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
            budgetSupport = BudgetSupportLocalServiceUtil.updateBudgetSupport(budgetSupport);

        } catch (PortalException e) {
            _log.error(e);
            throw new PortletException(e);
        }
        _log.info("pétition créé : " + budgetSupport);
        return true;
    }
	
	/**
	 * Verification des information du formulaire issu de la requete et validation
	 * du contexte fonctionnel de la requete (ex: vote possible, entite perime, etc)
	 * @return Si la requete est tangible
	 */
	private boolean validate(ResourceRequest request) {
        boolean isValid = true;
        
        // utilisateur 
        if (this.publikID == null || this.publikID.isEmpty()) {
        	isValid = false;
            this.message = "Utilisateur non enregistr&eacute;/identifi&eacute;";
        } else {
        	this.user = PublikUserLocalServiceUtil.getByPublikUserId(this.publikID);
        	
        	if (this.user.isBanned()) {
        		this.message = "Vous ne pouvez soutenir ce projet";
        		isValid = false;
        	} else if (this.user.getPactSignature() == null) {
        		this.message = "Vous devez signer le Pacte pour soutenir ce projet";
        		isValid = false;
        	}
        }
        
        // budget participatif
        try {
			this.budgetParticipatif = BudgetParticipatifLocalServiceUtil.getBudgetParticipatif(this.entryID);
			
			if (this.budgetParticipatif != null) {
				if (!this.budgetParticipatif.isVotable()) {
					isValid = false;
					this.message = "Ce budget participatif n'est pas en p&eacute;riode de vote";
				}
			} else {
				this.message = "Erreur lors de la r&eacute;cuperation du budget participatif";
				isValid = false;
			}
		} catch (PortalException e1) {
			_log.error(e1);
			this.message = "Erreur lors de la r&eacute;cuperation du budget participatif";
			isValid = false;
		}
        
        // birthday
        if (Validator.isNull(this.birthday)) {
            isValid = false;
            this.message = "error";
        }

        // city
        if (Validator.isNull(this.city)) {
            isValid = false;
            this.message = "error";
        }

        // address
        if (Validator.isNull(this.address)) {
            isValid = false;
            this.message = "error";
        }

        // postalcode
        if (Validator.isNull(this.postalcode)) {
            isValid = false;
            this.message = "error";
        }
        
        return isValid;
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
