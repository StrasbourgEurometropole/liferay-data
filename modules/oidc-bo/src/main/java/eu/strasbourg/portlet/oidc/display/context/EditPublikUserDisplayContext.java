package eu.strasbourg.portlet.oidc.display.context;

import java.util.Locale;
import java.util.Set;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import eu.strasbourg.service.oidc.model.PublikUser;
import eu.strasbourg.service.oidc.service.PublikUserLocalServiceUtil;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

public class EditPublikUserDisplayContext {
	
	private PublikUser _publikUser;
	
	private final RenderRequest _request;
	private final ThemeDisplay _themeDisplay;
	
	public EditPublikUserDisplayContext(RenderRequest request,
			RenderResponse response) {
		this._request = request;
		this._themeDisplay = (ThemeDisplay) request
			.getAttribute(WebKeys.THEME_DISPLAY);
	}
	
	public PublikUser getPublikUser() {
		long publikUserLiferayId = ParamUtil.getLong(_request, "publikUserLiferayId");
		if (_publikUser == null && publikUserLiferayId > 0) {
			_publikUser = PublikUserLocalServiceUtil.fetchPublikUser(publikUserLiferayId);
		}
		return _publikUser;
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
