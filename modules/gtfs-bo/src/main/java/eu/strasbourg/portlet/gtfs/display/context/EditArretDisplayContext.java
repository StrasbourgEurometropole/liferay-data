package eu.strasbourg.portlet.gtfs.display.context;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.service.WorkflowDefinitionLinkLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import eu.strasbourg.service.gtfs.model.Arret;
import eu.strasbourg.service.gtfs.service.ArretLocalServiceUtil;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import java.util.Locale;
import java.util.Set;

public class EditArretDisplayContext {
	public EditArretDisplayContext(RenderRequest request,
								   RenderResponse response) {
		this._request = request;
		this._themeDisplay = (ThemeDisplay) request
				.getAttribute(WebKeys.THEME_DISPLAY);
	}

	public Arret getArret() {
		long arretId = ParamUtil.getLong(_request, "arretId");
		if (_arret == null && arretId > 0) {
			_arret = ArretLocalServiceUtil.fetchArret(arretId);
		}
		return _arret;
	}

	public String getDefaultIndexes(int length) {
		String indexes = "";
		for (int i = 1; i <= length; i++) {
			if (Validator.isNotNull(indexes)) {
				indexes += ",";
			}
			indexes += i;
		}
		return indexes;
	}

	public Locale[] getAvailableLocales() {
		Set<Locale> availableLocalesSet = LanguageUtil.getSupportedLocales();
		Locale[] availableLocales = availableLocalesSet
				.toArray(new Locale[availableLocalesSet.size()]);
		return availableLocales;
	}

	public boolean isWorkflowEnabled() {
		return WorkflowDefinitionLinkLocalServiceUtil.hasWorkflowDefinitionLink(
				_themeDisplay.getCompanyId(), _themeDisplay.getCompanyGroupId(),
				Arret.class.getName());
	}

	/**
	 * Wrapper autour du permission checker pour les permissions de module
	 */
	public boolean hasPermission(String actionId) throws PortalException {
		return _themeDisplay.getPermissionChecker().hasPermission(
				this._themeDisplay.getCompanyGroupId(),
				StrasbourgPortletKeys.GTFS_BO, StrasbourgPortletKeys.GTFS_BO,
				actionId);
	}

	private Arret _arret;

	private final RenderRequest _request;
	private final ThemeDisplay _themeDisplay;

}
