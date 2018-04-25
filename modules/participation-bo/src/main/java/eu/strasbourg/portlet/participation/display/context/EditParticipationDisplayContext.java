package eu.strasbourg.portlet.participation.display.context;

import java.util.Locale;
import java.util.Set;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.service.WorkflowDefinitionLinkLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;

import eu.strasbourg.service.project.model.Participation;
import eu.strasbourg.service.project.service.ParticipationLocalServiceUtil;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

public class EditParticipationDisplayContext {
	
	private Participation _participation;
	
	private final RenderRequest _request;
	private final ThemeDisplay _themeDisplay;
	
	public EditParticipationDisplayContext(RenderRequest request,
		RenderResponse response) {
		this._request = request;
		this._themeDisplay = (ThemeDisplay) request
			.getAttribute(WebKeys.THEME_DISPLAY);
	}

	public Participation getParticipation() {
		long participationId = ParamUtil.getLong(_request, "participationId");
		if (_participation == null && participationId > 0) {
			_participation = ParticipationLocalServiceUtil.fetchParticipation(participationId);
		}
		return _participation;
	}

	public Locale[] getAvailableLocales() {
		Set<Locale> availableLocalesSet = LanguageUtil.getSupportedLocales();
		Locale[] availableLocales = availableLocalesSet
			.toArray(new Locale[availableLocalesSet.size()]);
		return availableLocales;
	}

	/**
	 * @return True si le framework workflow est actif pour ce type d'entité
	 */
	public boolean isWorkflowEnabled() {
		return WorkflowDefinitionLinkLocalServiceUtil.hasWorkflowDefinitionLink(
			_themeDisplay.getCompanyId(), _themeDisplay.getCompanyGroupId(),
			Participation.class.getName());
	}
		
	/**
	 * Wrapper autour du permission checker pour les permissions de module
	 */
	public boolean hasPermission(String actionId) throws PortalException {
		return _themeDisplay.getPermissionChecker().hasPermission(
		this._themeDisplay.getCompanyGroupId(),
			StrasbourgPortletKeys.PARTICIPATION_BO, StrasbourgPortletKeys.PARTICIPATION_BO,
			actionId);
	}

}
