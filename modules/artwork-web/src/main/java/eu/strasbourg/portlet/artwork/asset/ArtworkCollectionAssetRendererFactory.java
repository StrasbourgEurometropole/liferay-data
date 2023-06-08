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

import eu.strasbourg.service.artwork.model.ArtworkCollection;
import eu.strasbourg.service.artwork.service.ArtworkCollectionLocalService;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

import java.util.Locale;
import java.util.ResourceBundle;

@Component(
	immediate = true,
	property = {"javax.portlet.name=" + StrasbourgPortletKeys.ARTWORK_WEB},
	service = AssetRendererFactory.class
)
public class ArtworkCollectionAssetRendererFactory extends BaseAssetRendererFactory<ArtworkCollection> {
	
	public static final String TYPE = "artworkCollection";

	public ArtworkCollectionAssetRendererFactory() {
		setClassName(ArtworkCollection.class.getName());
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
	public AssetRenderer<ArtworkCollection> getAssetRenderer(long classPK, int type)
		throws PortalException {
		ArtworkCollection entry = _artworkCollectionLocalService.getArtworkCollection(classPK);

		ArtworkCollectionAssetRenderer artworkCollectionAssetRenderer =
			new ArtworkCollectionAssetRenderer(entry);

		artworkCollectionAssetRenderer.setAssetRendererType(type);

		return artworkCollectionAssetRenderer;

	}

	@Override
	public String getType() {
		return TYPE;
	}
	
	private ArtworkCollectionLocalService _artworkCollectionLocalService;

	@Reference(unbind = "-")
	protected void setArtworkCollectionLocalService(ArtworkCollectionLocalService artworkCollectionLocalService) {
		_artworkCollectionLocalService = artworkCollectionLocalService;
	}
}
