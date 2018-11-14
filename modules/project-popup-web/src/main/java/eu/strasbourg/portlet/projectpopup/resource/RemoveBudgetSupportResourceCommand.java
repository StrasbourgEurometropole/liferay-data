package eu.strasbourg.portlet.projectpopup.resource;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

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
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.SessionParamUtil;

import eu.strasbourg.service.oidc.model.PublikUser;
import eu.strasbourg.service.oidc.service.PublikUserLocalServiceUtil;
import eu.strasbourg.service.project.model.BudgetParticipatif;
import eu.strasbourg.service.project.model.BudgetSupport;
import eu.strasbourg.service.project.service.BudgetParticipatifLocalServiceUtil;
import eu.strasbourg.service.project.service.BudgetSupportLocalServiceUtil;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

@Component(
	immediate = true,
    property = {
            "javax.portlet.name=" + StrasbourgPortletKeys.PROJECT_POPUP_WEB,
            "mvc.command.name=removeBudgetSupport"
    },
    service = MVCResourceCommand.class
)
public class RemoveBudgetSupportResourceCommand implements MVCResourceCommand {
	
	// Variables tempons
    private String publikID;
    private PublikUser user;
    private int nbUserSupports;
    private int nbUserEntrySupports;
    private int nbEntrySupports;
    private long entryID;
    private BudgetParticipatif budgetParticipatif;
    private BudgetSupport budgetSupport;
    private String message;

	@Override
	public boolean serveResource(ResourceRequest request, ResourceResponse response) throws PortletException {
		
		boolean result = false;
		
		// Recuperation de l'utilsiteur Publik ayant lance la demande
        this.publikID = getPublikID(request);
        
        // Recuperation du budget participatif en question
        this.entryID = ParamUtil.getLong(request, "entryId");
        
        // Verification de la validite des informations        
        if (validate(request)) {
        	
            result = removeBudgetSupport(request);
            
            // Recuperation du nombre de vote de l'utilisateur pour l'entite courante
            this.nbUserEntrySupports = this.budgetParticipatif.getNbSupportOfUser(this.publikID);
            this.nbEntrySupports = (int) this.budgetParticipatif.getNbSupports();
            this.nbUserSupports =  BudgetParticipatifLocalServiceUtil.countBudgetSupportedByPublikUserInPhase(
				                		this.publikID,
				                		this.budgetParticipatif.getPhase().getBudgetPhaseId()
				                	);
        }
        
        // Récupération du json des entités
        JSONObject jsonResponse = JSONFactoryUtil.createJSONObject();
        jsonResponse.put("result", result);
        jsonResponse.put("message", this.message);
        
        JSONObject updatedSupportsInfo = JSONFactoryUtil.createJSONObject();
        
        updatedSupportsInfo.put("nbUserSupports", this.nbUserSupports);
        updatedSupportsInfo.put("nbUserEntrySupports", this.nbUserEntrySupports);
        updatedSupportsInfo.put("nbEntrySupports", this.nbEntrySupports);
        
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
	 * Envoi de la demande de supression de soutien
	 * @return Si la demande s'est bien passee
	 */
	private boolean removeBudgetSupport(ResourceRequest request) throws PortletException {
        BudgetSupportLocalServiceUtil.removeBudgetSupport(this.budgetSupport.getBudgetSupportId());

        _log.info("Soutien retire : " + this.budgetSupport);
        return true;
    }
	
	/**
	 * Verification des information du formulaire issu de la requete et validation
	 * du contexte fonctionnel de la requete (ex: vote possible, entite perime, etc)
	 * @return Si la requete est tangible
	 */
	private boolean validate(ResourceRequest request) {
		
		// Utilisateur
        if (this.publikID == null || this.publikID.isEmpty()) {
            this.message = "Utilisateur non recconu";
            return false;
        } else {
        	this.user = PublikUserLocalServiceUtil.getByPublikUserId(this.publikID);
        	
        	if (this.user.isBanned()) {
        		this.message = "Vous ne pouvez revenir sur votre vote";
        		return false;
        	} else if (this.user.getPactSignature() == null) {
        		this.message = "Vous devez signer le Pacte pour revenir sur votre vote";
        		return false;
        	}
        }
        
        // Budget participatif
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
        
        // Soutien
        List<BudgetSupport> budgetSupports = BudgetSupportLocalServiceUtil.getBudgetSupportByBudgetParticipatifIdAndPublikUserId(
				this.budgetParticipatif.getBudgetParticipatifId(), 
				this.publikID);
        
        if (budgetSupports.size() == 0) {
        	this.message = "Erreur lors de la recherche du vote";
			return false;
        } else {
        	this.budgetSupport = budgetSupports.get(0);
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
