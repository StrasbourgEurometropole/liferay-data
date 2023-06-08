package eu.strasbourg.portlet.edition.asset;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.liferay.asset.kernel.model.AssetRenderer;
import com.liferay.asset.kernel.model.AssetRendererFactory;
import com.liferay.asset.kernel.model.BaseAssetRendererFactory;
import com.liferay.portal.kernel.exception.PortalException;

import eu.strasbourg.service.edition.model.EditionGallery;
import eu.strasbourg.service.edition.service.EditionGalleryLocalService;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

@Component(
	immediate = true,
	property = {"javax.portlet.name=" + StrasbourgPortletKeys.EDITION_WEB},
	service = AssetRendererFactory.class
)
public class EditionGalleryAssetRendererFactory extends BaseAssetRendererFactory<EditionGallery> {
	
	public static final String TYPE = "editionGallery";

	public EditionGalleryAssetRendererFactory() {
		setClassName(EditionGallery.class.getName());
		setLinkable(true);
		setPortletId(StrasbourgPortletKeys.EDITION_WEB);
		setSearchable(true);
	}

	@Override
	public AssetRenderer<EditionGallery> getAssetRenderer(long classPK, int type)
		throws PortalException {
		EditionGallery entry = _editionGalleryLocalService.getEditionGallery(classPK);

		EditionGalleryAssetRenderer editionGalleryAssetRenderer =
			new EditionGalleryAssetRenderer(entry);

		editionGalleryAssetRenderer.setAssetRendererType(type);

		return editionGalleryAssetRenderer;

	}

	@Override
	public String getType() {
		return TYPE;
	}
	
	
	private EditionGalleryLocalService _editionGalleryLocalService;

	@Reference(unbind = "-")
	protected void setEditionGalleryLocalService(EditionGalleryLocalService editionGalleryLocalService) {
		_editionGalleryLocalService = editionGalleryLocalService;
	}
}
