package eu.strasbourg.portlet.projectpopup.resource;

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
import com.liferay.portal.kernel.util.Validator;
import eu.strasbourg.service.oidc.model.PublikUser;
import eu.strasbourg.service.oidc.service.PublikUserLocalServiceUtil;
import eu.strasbourg.service.project.model.BudgetParticipatif;
import eu.strasbourg.service.project.model.BudgetSupport;
import eu.strasbourg.service.project.service.BudgetParticipatifLocalServiceUtil;
import eu.strasbourg.service.project.service.BudgetSupportLocalServiceUtil;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import org.osgi.service.component.annotations.Component;

import javax.portlet.PortletRequest;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@Component(
	immediate = true,
    property = {
            "javax.portlet.name=" + StrasbourgPortletKeys.PROJECT_POPUP_WEB,
            "mvc.command.name=removeBudgetSupport"
    },
    service = MVCResourceCommand.class
)
public class RemoveBudgetSupportResourceCommand implements MVCResourceCommand {

	@Override
	public boolean serveResource(ResourceRequest request, ResourceResponse response) {
		
		boolean result = false;

        // Initialisations respectives de : nombre de votes pour l'entite courante, le nombre de votes de l'utilisateur
        // pour l'entite courante, le nombre de votes de l'utilisateur, le nombre de votes pour la phase active
        int nbUserSupports = 0;
        int nbUserEntrySupports = 0;
        int nbEntrySupports = 0;
        long nbSupportForActivePhase = 0;
		
		// Recuperation de l'utilsiteur Publik ayant lance la demande
        PublikUser user = null;
        String publikID = getPublikID(request);
        if (publikID != null && !publikID.isEmpty()) {
            user = PublikUserLocalServiceUtil.getByPublikUserId(publikID);
        }
        
        // Recuperation du budget participatif en question
        BudgetParticipatif budgetParticipatif = null;
        BudgetSupport budgetSupport = null;
        long entryID = ParamUtil.getLong(request, "entryId");
        try {
            AssetEntry assetEntry = AssetEntryLocalServiceUtil.getAssetEntry(entryID);
            budgetParticipatif = BudgetParticipatifLocalServiceUtil.getBudgetParticipatif(assetEntry.getClassPK());

            // Recuperation du budget support lié
            List<BudgetSupport> budgetSupports = BudgetSupportLocalServiceUtil.getBudgetSupportByBudgetParticipatifIdAndPublikUserId(
                    budgetParticipatif.getBudgetParticipatifId(),
                    publikID);

            if (!budgetSupports.isEmpty()) {
                budgetSupport = budgetSupports.get(0);
            }
        } catch (PortalException e1) {
            _log.error(e1);
        }
        
        // Verification de la validite des informations
        String message = validate(publikID, user, budgetParticipatif, budgetSupport);
        if (message.equals("")) {

            if (budgetSupport != null) {
                result = removeBudgetSupport(budgetSupport);
            }

            // Recuperation du nombre de vote de l'utilisateur pour l'entite courante
            if (budgetParticipatif != null) {
                nbUserEntrySupports = budgetParticipatif.getNbSupportOfUser(publikID);
                nbEntrySupports = (int) budgetParticipatif.getNbSupports();
                nbUserSupports =  BudgetParticipatifLocalServiceUtil.countBudgetSupportedByPublikUserInPhase(
                        publikID,
                        budgetParticipatif.getPhase().getBudgetPhaseId()
                );
                nbSupportForActivePhase = budgetParticipatif.getPhase().getNumberOfVote();
            }
        }
        
        // Récupération du json des entités
        JSONObject jsonResponse = JSONFactoryUtil.createJSONObject();
        jsonResponse.put("result", result);
        jsonResponse.put("message", message);
        
        JSONObject updatedSupportsInfo = JSONFactoryUtil.createJSONObject();
        
        updatedSupportsInfo.put("nbUserSupports", nbUserSupports);
        updatedSupportsInfo.put("nbUserEntrySupports", nbUserEntrySupports);
        updatedSupportsInfo.put("nbEntrySupports", nbEntrySupports);
        updatedSupportsInfo.put("nbSupportForActivePhase", nbSupportForActivePhase);
        
        jsonResponse.put("updatedSupportsInfo", updatedSupportsInfo);

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
	 * Envoi de la demande de supression de soutien
	 * @return Si la demande s'est bien passee
	 */
	private boolean removeBudgetSupport(BudgetSupport budgetSupport) {
        BudgetSupportLocalServiceUtil.removeBudgetSupport(budgetSupport.getBudgetSupportId());

        _log.info("Soutien retire : " + budgetSupport);
        return true;
    }
	
	/**
	 * Verification des information du formulaire issu de la requete et validation
	 * du contexte fonctionnel de la requete (ex: vote possible, entite perime, etc)
	 * @return Si la requete est tangible
	 */
	private String validate(String publikID, PublikUser user, BudgetParticipatif budgetParticipatif, BudgetSupport budgetSupport) {
		
		// Utilisateur
        if (publikID == null || publikID.isEmpty()) {
            return "Utilisateur non recconu";
        } else {
        	if (user.isBanned()) {
                return "Vous ne pouvez revenir sur votre vote";
        	} else if (user.getPactSignature() == null) {
                return "Vous devez signer le Pacte pour revenir sur votre vote";
        	}
        }

        // Budget participatif
        if (budgetParticipatif != null) {
            if (!budgetParticipatif.isVotable()) {
                return "Ce budget participatif n'est pas en phase de vote";
            }
        } else {
            return "Erreur lors de la recherche du budget participatif";
        }

        // Soutien
        if (Validator.isNull(budgetSupport)) {
            return "Erreur lors de la recherche du vote";
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
