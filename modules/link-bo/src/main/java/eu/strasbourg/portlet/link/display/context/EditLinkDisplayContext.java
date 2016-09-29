package eu.strasbourg.portlet.link.display.context;

import java.util.Locale;
import java.util.Set;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;

import eu.strasbourg.service.link.model.Link;
import eu.strasbourg.service.link.service.LinkLocalServiceUtil;

public class EditLinkDisplayContext {
	public EditLinkDisplayContext(RenderRequest request,
		RenderResponse response) {
		this._request = request;
		this._response = response;
		this._themeDisplay = (ThemeDisplay) request
			.getAttribute(WebKeys.THEME_DISPLAY);
	}

	public Link getLink() throws PortalException {
		long linkId = ParamUtil.getLong(_request, "linkId");
		if (_link == null && linkId > 0) {
			_link = LinkLocalServiceUtil.getLink(linkId);
		}
		return _link;
	}
	

	public Locale[] getAvailableLocales() {
		Set<Locale> availableLocalesSet = LanguageUtil.getAvailableLocales(_themeDisplay.getScopeGroupId());
		Locale[] availableLocales = availableLocalesSet
			.toArray(new Locale[availableLocalesSet.size()]);
		return availableLocales;
	}

	private Link _link;

	private final RenderRequest _request;
	private final RenderResponse _response;
	private final ThemeDisplay _themeDisplay;
	
}
