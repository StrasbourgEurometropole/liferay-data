package eu.strasbourg.portlet.edition.display.context;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.servlet.http.HttpServletRequest;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchContextFactory;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.SortFactoryUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;

import eu.strasbourg.service.edition.model.Edition;
import eu.strasbourg.service.edition.model.EditionGallery;
import eu.strasbourg.service.edition.service.EditionGalleryLocalServiceUtil;
import eu.strasbourg.service.edition.service.EditionLocalServiceUtil;

public class EditEditionDisplayContext {
	public EditEditionDisplayContext(RenderRequest request,
		RenderResponse response) {
		this._request = request;
		this._response = response;
		this._themeDisplay = (ThemeDisplay) request
			.getAttribute(WebKeys.THEME_DISPLAY);
	}

	public Edition getEdition() throws PortalException {
		long editionId = ParamUtil.getLong(_request, "editionId");
		if (_edition == null && editionId > 0) {
			_edition = EditionLocalServiceUtil.getEdition(editionId);
		}
		return _edition;
	}

	public Locale[] getAvailableLocales() {
		Set<Locale> availableLocalesSet = LanguageUtil.getAvailableLocales(_themeDisplay.getScopeGroupId());
		Locale[] availableLocales = availableLocalesSet
			.toArray(new Locale[availableLocalesSet.size()]);
		return availableLocales;
	}

	private Edition _edition;

	private final RenderRequest _request;
	private final RenderResponse _response;
	private final ThemeDisplay _themeDisplay;
	
	private List<EditionGallery> _galleries;
}
