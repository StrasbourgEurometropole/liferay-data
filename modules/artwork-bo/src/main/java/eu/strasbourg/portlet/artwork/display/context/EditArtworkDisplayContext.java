package eu.strasbourg.portlet.artwork.display.context;

import java.util.Locale;
import java.util.Set;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;

import eu.strasbourg.service.artwork.model.Artwork;
import eu.strasbourg.service.artwork.service.ArtworkLocalServiceUtil;

public class EditArtworkDisplayContext {
	public EditArtworkDisplayContext(RenderRequest request,
		RenderResponse response) {
		this._request = request;
		this._themeDisplay = (ThemeDisplay) request
			.getAttribute(WebKeys.THEME_DISPLAY);
	}

	public Artwork getArtwork() throws PortalException {
		long artworkId = ParamUtil.getLong(_request, "artworkId");
		if (_artwork == null && artworkId > 0) {
			_artwork = ArtworkLocalServiceUtil.getArtwork(artworkId);
		}
		return _artwork;
	}
	

	public Locale[] getAvailableLocales() {
		Set<Locale> availableLocalesSet = LanguageUtil.getAvailableLocales(_themeDisplay.getScopeGroupId());
		Locale[] availableLocales = availableLocalesSet
			.toArray(new Locale[availableLocalesSet.size()]);
		return availableLocales;
	}

	private Artwork _artwork;

	private final RenderRequest _request;
	private final ThemeDisplay _themeDisplay;
	
}
