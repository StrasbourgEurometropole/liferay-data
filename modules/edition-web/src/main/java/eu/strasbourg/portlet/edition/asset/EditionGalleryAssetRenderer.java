package eu.strasbourg.portlet.edition.asset;
import java.util.Locale;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import javax.servlet.http.HttpServletRequest;

import com.liferay.asset.kernel.model.BaseJSPAssetRenderer;

import eu.strasbourg.service.edition.model.EditionGallery;

public class EditionGalleryAssetRenderer extends BaseJSPAssetRenderer<EditionGallery> {
	
	public static final String TYPE = "editionGallery";
	private EditionGallery _entry;
	
	public EditionGalleryAssetRenderer(EditionGallery entry) {
		_entry = entry;
	}
	
	@Override
	public EditionGallery getAssetObject() {
		return _entry;
	}

	@Override
	public long getGroupId() {
		return _entry.getGroupId();
	}

	@Override
	public long getUserId() {
		return _entry.getUserId();
	}

	@Override
	public String getUserName() {
		return _entry.getUserName();
	}

	@Override
	public String getUuid() {
		return _entry.getUuid();
	}

	@Override
	public String getClassName() {
		return EditionGallery.class.getName();
	}

	@Override
	public long getClassPK() {
		return _entry.getGalleryId();
	}

	@Override
	public String getSummary(PortletRequest portletRequest,
		PortletResponse portletResponse) {
		return _entry.getDescription(portletRequest.getLocale());
	}

	@Override
	public String getTitle(Locale locale) {
		return _entry.getTitle(locale);
	}
	
	@Override
	public String getJspPath(HttpServletRequest request, String template) {
		if (template.equals(TEMPLATE_ABSTRACT) ||
			template.equals(TEMPLATE_FULL_CONTENT)) {

			return "/edition-gallery/asset/" + template + ".jsp";
		}
		else {
			return null;
		}
	}


}
