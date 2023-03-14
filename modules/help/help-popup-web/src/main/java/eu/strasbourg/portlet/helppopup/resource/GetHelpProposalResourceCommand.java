package eu.strasbourg.portlet.helppopup.resource;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import eu.strasbourg.service.help.model.HelpProposal;
import eu.strasbourg.service.help.service.HelpProposalLocalService;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import eu.strasbourg.utils.constants.VocabularyNames;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

/**
 * @author romain.vergnais
 */
@Component(
        immediate = true,
        property = {
                "javax.portlet.name=" + StrasbourgPortletKeys.HELP_POPUP_WEB,
                "mvc.command.name=getHelpProposal"
        },
        service = MVCResourceCommand.class
)
public class GetHelpProposalResourceCommand implements MVCResourceCommand {

	private long entryID;
	
	@Override
	public boolean serveResource(ResourceRequest request, ResourceResponse response) {
		boolean success = true;
		ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
		
		// Recuperation de l'id de l'entité
        this.entryID = ParamUtil.getLong(request, "entryId");

		// Retour des informations de la requete en JSON
		JSONObject jsonResponse = JSONFactoryUtil.createJSONObject();

        try {
        	// Recuperation de la proposition d'aide à modifier
        	AssetEntry assetEntry = AssetEntryLocalServiceUtil.getAssetEntry(this.entryID);
        	if(assetEntry != null) {
				HelpProposal helpProposal = _helpProposalLocalService.getHelpProposal(assetEntry.getClassPK());

				if(helpProposal != null) {
					jsonResponse.put("title", helpProposal.getTitle(Locale.FRANCE));

					//Récupération de la liste des types
					List<AssetCategory> types = helpProposal.getHelpProposalTypeCategories();
					long[] idsTypes = types.stream()
							.mapToLong(AssetCategory::getCategoryId).toArray();
					jsonResponse.put("types", idsTypes);

					jsonResponse.put("description", helpProposal.getDescription(Locale.FRANCE));

					jsonResponse.put("helperId", helpProposal.getHelpProposalHelperCategory().getCategoryId());

					jsonResponse.put("inTheNameOf", helpProposal.getInTheNameOf());

					jsonResponse.put("languages", helpProposal.getSpokenLanguages(Locale.FRANCE));

					//Récupération de la liste des types
					List<AssetCategory> localisations = helpProposal.getTerritoryCategories();
					if(localisations.size()>1){
						// On est dans le cas du choix d'un quartier de Strasbourg auquel on a ajouté Strasbourg
						// il faut donc n'envoyer que le quartier
						localisations = localisations.stream().filter(l -> !l.getName().equals("Strasbourg")).collect(Collectors.toList());
					}

					long[] idslocalisations = localisations.stream()
							.mapToLong(AssetCategory::getCategoryId).toArray();
					jsonResponse.put("localisationId", idslocalisations[0]);

					jsonResponse.put("hasImage", helpProposal.getImageId() != 0 ? true : false);

					jsonResponse.put("agreement1", helpProposal.isAgreementSigned1());

					jsonResponse.put("agreement2", helpProposal.isAgreementSigned2());

					jsonResponse.put("agreement3", helpProposal.isAgreementSigned3());
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

	@Reference(unbind = "-")
	protected void setHelpProposalLocalService(HelpProposalLocalService helpProposalLocalService) {
		_helpProposalLocalService = helpProposalLocalService;
	}

	private HelpProposalLocalService _helpProposalLocalService;
}
