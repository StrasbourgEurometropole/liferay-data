package eu.strasbourg.portlet.place.display.context;

import java.util.Locale;
import java.util.Set;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.service.WorkflowDefinitionLinkLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import eu.strasbourg.service.place.model.Place;
import eu.strasbourg.service.place.service.PlaceLocalServiceUtil;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

public class EditPlaceDisplayContext {
	public EditPlaceDisplayContext(RenderRequest request,
			RenderResponse response) {
		this._request = request;
		this._themeDisplay = (ThemeDisplay) request
				.getAttribute(WebKeys.THEME_DISPLAY);
	}

	public Place getPlace() {
		long placeId = ParamUtil.getLong(_request, "placeId");
		if (_place == null && placeId > 0) {
			_place = PlaceLocalServiceUtil.fetchPlace(placeId);
		}
		return _place;
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
				Place.class.getName());
	}

	/**
	 * Wrapper autour du permission checker pour les permissions de module
	 */
	public boolean hasPermission(String actionId) throws PortalException {
		return _themeDisplay.getPermissionChecker().hasPermission(
				this._themeDisplay.getScopeGroupId(),
				StrasbourgPortletKeys.PLACE_BO, StrasbourgPortletKeys.PLACE_BO,
				actionId);
	}

	private Place _place;

	private final RenderRequest _request;
	private final ThemeDisplay _themeDisplay;

}
