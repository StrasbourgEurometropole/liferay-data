package eu.strasbourg.portlet.oidc.display.context;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.service.WorkflowDefinitionLinkLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import eu.strasbourg.service.oidc.model.AnonymisationHistoric;
import eu.strasbourg.service.oidc.service.AnonymisationHistoricLocalServiceUtil;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import java.util.Locale;
import java.util.Set;

public class EditAnonymisationHistoricsDisplayContext {

	private AnonymisationHistoric _anonymisationHistoric;

	private final RenderRequest _request;
	private final ThemeDisplay _themeDisplay;

	public EditAnonymisationHistoricsDisplayContext(RenderRequest request,
                                                    RenderResponse response) {
		this._request = request;
		this._themeDisplay = (ThemeDisplay) request
			.getAttribute(WebKeys.THEME_DISPLAY);
	}
	
	public AnonymisationHistoric getAnonymisationHistoric() {
		long anonymisationHistoricId = ParamUtil.getLong(_request, "anonymisationHistoricId");
		if (_anonymisationHistoric == null && anonymisationHistoricId > 0) {
			_anonymisationHistoric = AnonymisationHistoricLocalServiceUtil.fetchAnonymisationHistoric(anonymisationHistoricId);
		}
		return _anonymisationHistoric;
	}

	/**
	 * @return True si le framework workflow est actif pour ce type d'entité
	 */
	public boolean isWorkflowEnabled() {
		return WorkflowDefinitionLinkLocalServiceUtil.hasWorkflowDefinitionLink(
				_themeDisplay.getCompanyId(), _themeDisplay.getCompanyGroupId(),
				AnonymisationHistoric.class.getName());
	}
	
	public Locale[] getAvailableLocales() {
		Set<Locale> availableLocalesSet = LanguageUtil.getSupportedLocales();
		Locale[] availableLocales = availableLocalesSet
			.toArray(new Locale[availableLocalesSet.size()]);
		return availableLocales;
	}
	
	/**
	 * Wrapper autour du permission checker pour les permissions de module
	 */
	public boolean hasPermission(String actionId) throws PortalException {
		return _themeDisplay.getPermissionChecker().hasPermission(
		this._themeDisplay.getCompanyGroupId(),
			StrasbourgPortletKeys.OIDC_BO, StrasbourgPortletKeys.OIDC_BO,
			actionId);
	}

}
