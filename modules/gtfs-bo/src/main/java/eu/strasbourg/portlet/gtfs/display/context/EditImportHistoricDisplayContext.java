package eu.strasbourg.portlet.gtfs.display.context;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.service.WorkflowDefinitionLinkLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;


import java.util.Locale;
import java.util.Set;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import eu.strasbourg.service.gtfs.model.ImportHistoric;
import eu.strasbourg.service.gtfs.service.ImportHistoricLocalServiceUtil;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

public class EditImportHistoricDisplayContext {

	private ImportHistoric _importHistoric;
	private final RenderRequest _request;
	private final ThemeDisplay _themeDisplay;
	
	public EditImportHistoricDisplayContext(RenderRequest request,RenderResponse response) {
		this._request = request;
		this._themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
	}
	
	public ImportHistoric getImportHistoric() {
		long importHistoric = ParamUtil.getLong(_request, "importHistoricId");
		if (_importHistoric == null && importHistoric > 0) {
			_importHistoric = ImportHistoricLocalServiceUtil.fetchImportHistoric(importHistoric);
		}
		return _importHistoric;
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
			ImportHistoric.class.getName());
	}
		
	/**
	 * Wrapper autour du permission checker pour les permissions de module
	 */
	public boolean hasPermission(String actionId) throws PortalException {
		return _themeDisplay.getPermissionChecker().hasPermission(
		this._themeDisplay.getScopeGroupId(),
			StrasbourgPortletKeys.GTFS_BO, StrasbourgPortletKeys.GTFS_BO,
			actionId);
	}
	
}
