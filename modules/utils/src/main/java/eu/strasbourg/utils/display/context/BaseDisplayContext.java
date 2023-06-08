package eu.strasbourg.utils.display.context;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;

public class BaseDisplayContext {
	protected RenderRequest _request = null;
	protected RenderResponse _response = null;
	protected ThemeDisplay _themeDisplay = null;

	public BaseDisplayContext(RenderRequest request, RenderResponse response) {
		this._request = request;
		this._response = response;
		this._themeDisplay = (ThemeDisplay) _request
			.getAttribute(WebKeys.THEME_DISPLAY);
	}
}
