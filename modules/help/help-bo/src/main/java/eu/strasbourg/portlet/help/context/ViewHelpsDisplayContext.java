package eu.strasbourg.portlet.help.context;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.util.GetterUtil;
import eu.strasbourg.service.help.model.HelpProposal;
import eu.strasbourg.service.help.model.HelpRequest;
import eu.strasbourg.service.help.service.HelpProposalLocalServiceUtil;
import eu.strasbourg.service.help.service.HelpRequestLocalServiceUtil;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import eu.strasbourg.utils.display.context.ViewListBaseDisplayContext;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ViewHelpsDisplayContext extends ViewListBaseDisplayContext<HelpProposal> {

	private List <HelpProposal> _helpProposals;
	private HashMap<Long, Integer> _helpRequestsByProposal;

	public ViewHelpsDisplayContext(RenderRequest request, RenderResponse response) {
		super(HelpProposal.class, request, response);
	}

	public List<HelpProposal> getHelpProposals() throws PortalException {
		if (this._helpProposals == null) {
			Hits hits = getHits(this._themeDisplay.getScopeGroupId());
			this._helpProposals = createObjectList(hits);
		}
		return this._helpProposals;
	}

	public HashMap<Long, Integer> getHelpRequestsByProposal() throws PortalException {
		if (this._helpProposals == null) {
			getHelpProposals();
		}
		if (_helpRequestsByProposal == null) {
			_helpRequestsByProposal = new HashMap<>();
			for (HelpProposal helpProposal : this._helpProposals) {
				long proposalId = helpProposal.getHelpProposalId();
				List<HelpRequest> requests = HelpRequestLocalServiceUtil.getByHelpProposalId(proposalId);
				_helpRequestsByProposal.put(Long.valueOf(proposalId), Integer.valueOf(requests.size()));
			}
		}
		return _helpRequestsByProposal;
	}

	private List<HelpProposal> createObjectList(Hits hits) {
		// Création de la liste d'objet
		List<HelpProposal> results = new ArrayList<>();
		if (hits != null) {
			for (Document document : hits.getDocs()) {
				HelpProposal helpProposal = HelpProposalLocalServiceUtil.fetchHelpProposal(
					GetterUtil.getLong(document.get(Field.ENTRY_CLASS_PK)));
				if (helpProposal != null) {
					results.add(helpProposal);
				}
			}
		}
		return results;
	}

	/**
	 * Wrapper autour du permission checker pour les permissions de module
	 */
	public boolean hasPermission(String actionId) throws PortalException {
		return _themeDisplay.getPermissionChecker().hasPermission(
			this._themeDisplay.getScopeGroupId(),
			StrasbourgPortletKeys.HELP_BO, StrasbourgPortletKeys.HELP_BO,
			actionId);
	}

}
