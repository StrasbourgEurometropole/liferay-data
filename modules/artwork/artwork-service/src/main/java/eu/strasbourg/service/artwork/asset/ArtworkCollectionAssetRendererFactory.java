package eu.strasbourg.service.artwork.asset;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.liferay.asset.kernel.model.AssetRenderer;
import com.liferay.asset.kernel.model.AssetRendererFactory;
import com.liferay.asset.kernel.model.BaseAssetRendererFactory;
import com.liferay.portal.kernel.exception.PortalException;

import eu.strasbourg.service.artwork.model.ArtworkCollection;
import eu.strasbourg.service.artwork.service.ArtworkCollectionLocalService;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

@Component(
	immediate = true,
	property = {"javax.portlet.name=" + StrasbourgPortletKeys.ARTWORK_BO},
	service = AssetRendererFactory.class
)
public class ArtworkCollectionAssetRendererFactory extends BaseAssetRendererFactory<ArtworkCollection> {
	
	public static final String TYPE = "artworkCollection";

	public ArtworkCollectionAssetRendererFactory() {
		setClassName(ArtworkCollection.class.getName());
		setLinkable(true);
		setPortletId(StrasbourgPortletKeys.ARTWORK_BO);
		setSearchable(true);
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
