package eu.strasbourg.portlet.projectpopup.resource;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.service.DLFileEntryLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import eu.strasbourg.service.project.model.BudgetParticipatif;
import eu.strasbourg.service.project.service.BudgetParticipatifLocalServiceUtil;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import org.osgi.service.component.annotations.Component;

import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * @author romain.vergnais
 */
@Component(
        immediate = true,
        property = {
                "javax.portlet.name=" + StrasbourgPortletKeys.PROJECT_POPUP_WEB,
                "mvc.command.name=getBudget"
        },
        service = MVCResourceCommand.class
)
public class GetBudgetResourceCommand implements MVCResourceCommand {


	@Override
	public boolean serveResource(ResourceRequest request, ResourceResponse response) throws PortletException {
		boolean success = true;
		
		// Recuperation de l'id de l'entité
		long entryID = ParamUtil.getLong(request, "entryId");

		// Retour des informations de la requete en JSON
		JSONObject jsonResponse = JSONFactoryUtil.createJSONObject();

        try {
        	// Recuperation du budget participatif à modifier
        	AssetEntry assetEntry = AssetEntryLocalServiceUtil.getAssetEntry(entryID);
        	if(assetEntry != null) {
				BudgetParticipatif bp = BudgetParticipatifLocalServiceUtil.getBudgetParticipatif(assetEntry.getClassPK());

				if(bp != null) {
					jsonResponse.put("title", bp.getTitle());
					jsonResponse.put("description", bp.getDescription());
					jsonResponse.put("summary", bp.getSummary());

					//Récupération de la liste des quartiers
					List<AssetCategory> districts = bp.getDistrictCategories();
					//Si le bp est pour toute la ville (plus de 1 quartier), selectionne 'la ville entière'
					int idstrictId = districts.size() == 1 ? (int) districts.get(0).getCategoryId() : 0;

					jsonResponse.put("quartier", idstrictId);
					jsonResponse.put("placeText", bp.getPlaceTextArea());

					if (bp.getProjectCategory() != null)
						jsonResponse.put("projectId", bp.getProjectCategory() != null ? bp.getProjectCategory().getCategoryId() : 0);

					if (bp.getThematicCategory() != null)
						jsonResponse.put("themeId", bp.getThematicCategory() != null ? bp.getThematicCategory().getCategoryId() : 0);

					jsonResponse.put("hasImage", bp.getImageId() != 0 ? true : false);
					jsonResponse.put("videoURL", bp.getVideoUrl());

					//récupération des noms de fichiers
					JSONArray jsonFiles = JSONFactoryUtil.createJSONArray();

					String[] fileIds = bp.getFilesIds().split(",");
					for (String fileId : fileIds) {
						if(!fileId.equals("")) {
							DLFileEntry file = DLFileEntryLocalServiceUtil.fetchDLFileEntry(Long.parseLong(fileId));
							JSONObject jsonFile = JSONFactoryUtil.createJSONObject();
							jsonFile.put("name", file.getFileName());
							jsonFile.put("id", file.getFileEntryId());
							jsonFiles.put(jsonFile);
						}
					}

					jsonResponse.put("documents", jsonFiles);
				}
			}

			// Recuperation de l'élément d'écriture de la réponse
		    PrintWriter writer = null;
		    try {
		        writer = response.getWriter();
		    } catch (IOException e) {
		    	_log.error(e);
		    }
		    writer.print(jsonResponse.toString());
		    
        } catch (PortalException e1) {
			_log.info(e1.getMessage(), e1);
			 success = false;
		}

        return success;
	}
	
	/**
     * Le log
     */
    private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());
}
