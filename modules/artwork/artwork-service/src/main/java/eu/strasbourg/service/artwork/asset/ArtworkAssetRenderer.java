package eu.strasbourg.service.artwork.asset;

import java.util.Locale;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.liferay.asset.kernel.model.BaseAssetRenderer;

import eu.strasbourg.service.artwork.model.Artwork;

public class ArtworkAssetRenderer extends BaseAssetRenderer<Artwork> {
	
	public static final String TYPE = "artwork";
	private Artwork _entry;
	
	public ArtworkAssetRenderer(Artwork entry) {
		_entry = entry;
	}
	
	@Override
	public Artwork getAssetObject() {
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
		return Artwork.class.getName();
	}

	@Override
	public long getClassPK() {
		return _entry.getArtworkId();
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
	public boolean include(HttpServletRequest request,
		HttpServletResponse response, String template) throws Exception {
		return false;
	}

}
