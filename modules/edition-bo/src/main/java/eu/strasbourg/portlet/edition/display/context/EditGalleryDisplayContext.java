package eu.strasbourg.portlet.edition.display.context;

import java.util.Locale;
import java.util.Set;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;

import eu.strasbourg.service.edition.model.EditionGallery;
import eu.strasbourg.service.edition.service.EditionGalleryLocalServiceUtil;

public class EditGalleryDisplayContext {
	public EditGalleryDisplayContext(RenderRequest request, RenderResponse response) {
		this._request = request;
		this._response = response;
		this._themeDisplay = (ThemeDisplay) request
			.getAttribute(WebKeys.THEME_DISPLAY);
	}
	
	public EditionGallery getEditionGallery() throws PortalException {
		long galleryId = ParamUtil.getLong(_request, "galleryId");
		if (_editionGallery == null &&  galleryId > 0) {
			_editionGallery = EditionGalleryLocalServiceUtil.getEditionGallery(galleryId);
		}
		
		return _editionGallery;
	}

	public Locale[] getAvailableLocales() {
		Set<Locale> availableLocalesSet = LanguageUtil.getAvailableLocales(_themeDisplay.getSiteGroupIdOrLiveGroupId());
		Locale[] availableLocales = availableLocalesSet
			.toArray(new Locale[availableLocalesSet.size()]);
		return availableLocales;
	}
	
	private EditionGallery _editionGallery;
	
	private final RenderRequest _request;
	private final RenderResponse _response;
	private final ThemeDisplay _themeDisplay;
}
