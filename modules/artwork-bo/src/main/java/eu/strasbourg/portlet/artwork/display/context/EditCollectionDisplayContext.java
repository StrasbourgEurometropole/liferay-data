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

import eu.strasbourg.service.artwork.model.ArtworkCollection;
import eu.strasbourg.service.artwork.service.ArtworkCollectionLocalServiceUtil;

public class EditCollectionDisplayContext {
	public EditCollectionDisplayContext(RenderRequest request, RenderResponse response) {
		this._request = request;
		this._themeDisplay = (ThemeDisplay) request
			.getAttribute(WebKeys.THEME_DISPLAY);
	}
	
	public ArtworkCollection getCollection() throws PortalException {
		long collectionId = ParamUtil.getLong(_request, "collectionId");
		if (_collection == null &&  collectionId > 0) {
			_collection = ArtworkCollectionLocalServiceUtil.getArtworkCollection(collectionId);
		}
		
		return _collection;
	}

	public Locale[] getAvailableLocales() {
		Set<Locale> availableLocalesSet = LanguageUtil.getAvailableLocales(_themeDisplay.getScopeGroupId());
		Locale[] availableLocales = availableLocalesSet
			.toArray(new Locale[availableLocalesSet.size()]);
		return availableLocales;
	}
	
	private ArtworkCollection _collection;
	
	private final RenderRequest _request;
	private final ThemeDisplay _themeDisplay;
}
