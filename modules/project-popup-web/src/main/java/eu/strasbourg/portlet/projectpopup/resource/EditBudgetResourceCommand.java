package eu.strasbourg.portlet.projectpopup.resource;

import java.io.IOException;
import java.io.PrintWriter;

import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;

import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.util.ParamUtil;

import eu.strasbourg.service.project.model.BudgetParticipatif;
import eu.strasbourg.service.project.service.BudgetParticipatifLocalServiceUtil;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

/**
 * @author romain.vergnais
 */
@Component(
        immediate = true,
        property = {
                "javax.portlet.name=" + StrasbourgPortletKeys.PROJECT_POPUP_WEB,
                "mvc.command.name=editBudget"
        },
        service = MVCResourceCommand.class
)
public class EditBudgetResourceCommand implements MVCResourceCommand {

	private long entryID;
	
	@Override
	public boolean serveResource(ResourceRequest request, ResourceResponse response) throws PortletException {
		boolean success = true;
		
		// Recuperation du budget participatif en question
        this.entryID = ParamUtil.getLong(request, "entryId");
        
        try {
        	AssetEntry assetEntry = AssetEntryLocalServiceUtil.getAssetEntry(this.entryID);
			BudgetParticipatif bp = BudgetParticipatifLocalServiceUtil.getBudgetParticipatif(assetEntry.getClassPK());
        
		    // Retour des informations de la requete en JSON
		    JSONObject jsonResponse = JSONFactoryUtil.createJSONObject();
		    jsonResponse.put("title", bp.getTitle());
		    
		    
			// Recuperation de l'élément d'écriture de la réponse
		    PrintWriter writer = null;
		    try {
		        writer = response.getWriter();
		    } catch (IOException e) {
		    	_log.error(e);
		    }
		    writer.print(jsonResponse.toString());
		    
        } catch (PortalException e1) {
			_log.error(e1);
			e1.printStackTrace();
			 success = false;
		}

        return success;
	}
	
	/**
     * Le log
     */
    private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());
}
