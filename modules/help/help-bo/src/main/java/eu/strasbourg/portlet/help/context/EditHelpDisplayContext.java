package eu.strasbourg.portlet.help.context;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.service.WorkflowDefinitionLinkLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import eu.strasbourg.service.help.model.HelpProposal;
import eu.strasbourg.service.help.service.HelpProposalLocalServiceUtil;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

public class EditHelpDisplayContext {
	
	private HelpProposal _helpProposal;
	private final RenderRequest _request;
	private final ThemeDisplay _themeDisplay;
	
	public EditHelpDisplayContext(RenderRequest request,
								  RenderResponse response) {
		this._request = request;
		this._themeDisplay = (ThemeDisplay) request
			.getAttribute(WebKeys.THEME_DISPLAY);
	}

	public HelpProposal getHelpProposal() {
		long helpProposalId = ParamUtil.getLong(_request, "helpProposalId");
		if (_helpProposal == null && helpProposalId > 0) {
			_helpProposal = HelpProposalLocalServiceUtil.fetchHelpProposal(helpProposalId);
		}
		return _helpProposal;
	}

	/**
	 * @return True si le framework workflow est actif pour ce type d'entité
	 */
	public boolean isWorkflowEnabled() {
		return WorkflowDefinitionLinkLocalServiceUtil.hasWorkflowDefinitionLink(
			_themeDisplay.getCompanyId(), _themeDisplay.getCompanyGroupId(),
				HelpProposal.class.getName());
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
