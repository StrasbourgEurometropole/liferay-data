package eu.strasbourg.portlet.video.display.context;

import java.util.Locale;
import java.util.Set;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.service.WorkflowDefinitionLinkLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;

import eu.strasbourg.service.video.model.VideoGallery;
import eu.strasbourg.service.video.service.VideoGalleryLocalServiceUtil;

public class EditGalleryDisplayContext {
	public EditGalleryDisplayContext(RenderRequest request, RenderResponse response) {
		this._request = request;
		this._themeDisplay = (ThemeDisplay) request
			.getAttribute(WebKeys.THEME_DISPLAY);
	}
	
	public VideoGallery getVideoGallery() throws PortalException {
		long galleryId = ParamUtil.getLong(_request, "galleryId");
		if (_videoGallery == null &&  galleryId > 0) {
			_videoGallery = VideoGalleryLocalServiceUtil.getVideoGallery(galleryId);
		}
		
		return _videoGallery;
	}

	public Locale[] getAvailableLocales() {
		Set<Locale> availableLocalesSet = LanguageUtil.getAvailableLocales(_themeDisplay.getScopeGroupId());
		Locale[] availableLocales = availableLocalesSet
			.toArray(new Locale[availableLocalesSet.size()]);
		return availableLocales;
	}
	
	/**
	 * @return True si le framework workflow est actif pour ce type d'entité
	 */
	public boolean isWorkflowEnabled() {
		return WorkflowDefinitionLinkLocalServiceUtil.hasWorkflowDefinitionLink(
			_themeDisplay.getCompanyId(), _themeDisplay.getScopeGroupId(),
			VideoGallery.class.getName());
	}
	
	private VideoGallery _videoGallery;
	
	private final RenderRequest _request;
	private final ThemeDisplay _themeDisplay;
}
