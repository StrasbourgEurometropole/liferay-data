package eu.strasbourg.portlet.artwork.asset;

import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.portlet.PortletBag;
import com.liferay.portal.kernel.portlet.PortletBagPool;
import com.liferay.portal.kernel.util.ResourceBundleUtil;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.liferay.asset.kernel.model.AssetRenderer;
import com.liferay.asset.kernel.model.AssetRendererFactory;
import com.liferay.asset.kernel.model.BaseAssetRendererFactory;
import com.liferay.portal.kernel.exception.PortalException;

import eu.strasbourg.service.artwork.model.Artwork;
import eu.strasbourg.service.artwork.service.ArtworkLocalService;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

import java.util.Locale;
import java.util.ResourceBundle;

@Component(
	immediate = true,
	property = {"javax.portlet.name=" + StrasbourgPortletKeys.ARTWORK_WEB},
	service = AssetRendererFactory.class
)
public class ArtworkAssetRendererFactory extends BaseAssetRendererFactory<Artwork> {
	
	public static final String TYPE = "artwork";

	public ArtworkAssetRendererFactory() {
		setClassName(Artwork.class.getName());
		setLinkable(true);
		setPortletId(StrasbourgPortletKeys.ARTWORK_WEB);
		setSearchable(true);
	}

	@Override
	public String getTypeName(Locale locale) {
		String key = getClassName();

		String value = LanguageUtil.get(locale, key, null);

		String portletId = getPortletId();

		if ((value == null) && (portletId != null)) {
			PortletBag portletBag = PortletBagPool.get(portletId);

			ResourceBundle resourceBundle = portletBag.getResourceBundle(
					locale);

			if (resourceBundle != null) {
				value = ResourceBundleUtil.getString(resourceBundle, key);
			}
		}

		if (value == null) {
			value = getClassName();
		}

		return value;
	}

	@Override
	public AssetRenderer<Artwork> getAssetRenderer(long classPK, int type)
		throws PortalException {
		Artwork entry = _artworkLocalService.getArtwork(classPK);

		ArtworkAssetRenderer artworkAssetRenderer =
			new ArtworkAssetRenderer(entry);

		artworkAssetRenderer.setAssetRendererType(type);

		return artworkAssetRenderer;

	}

	@Override
	public String getType() {
		return TYPE;
	}
	
	private ArtworkLocalService _artworkLocalService;

	@Reference(unbind = "-")
	protected void setArtworkLocalService(ArtworkLocalService artworkLocalService) {
		_artworkLocalService = artworkLocalService;
	}
}
