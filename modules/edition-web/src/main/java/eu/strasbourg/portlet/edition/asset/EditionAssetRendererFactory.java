package eu.strasbourg.portlet.edition.asset;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.liferay.asset.kernel.model.AssetRenderer;
import com.liferay.asset.kernel.model.AssetRendererFactory;
import com.liferay.asset.kernel.model.BaseAssetRendererFactory;
import com.liferay.portal.kernel.exception.PortalException;

import eu.strasbourg.service.edition.model.Edition;
import eu.strasbourg.service.edition.service.EditionLocalService;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

@Component(
	immediate = true,
	property = {"javax.portlet.name=" + StrasbourgPortletKeys.EDITION_WEB},
	service = AssetRendererFactory.class
)
public class EditionAssetRendererFactory extends BaseAssetRendererFactory<Edition> {
	
	public static final String TYPE = "edition";

	public EditionAssetRendererFactory() {
		setClassName(Edition.class.getName());
		setLinkable(true);
		setPortletId(StrasbourgPortletKeys.EDITION_WEB);
		setSearchable(true);
	}

	@Override
	public AssetRenderer<Edition> getAssetRenderer(long classPK, int type)
		throws PortalException {
		Edition entry = _editionLocalService.getEdition(classPK);

		EditionAssetRenderer editionAssetRenderer =
			new EditionAssetRenderer(entry);

		editionAssetRenderer.setAssetRendererType(type);

		return editionAssetRenderer;

	}

	@Override
	public String getType() {
		return TYPE;
	}
	
	
	private EditionLocalService _editionLocalService;

	@Reference(unbind = "-")
	protected void setEditionLocalService(EditionLocalService editionLocalService) {
		_editionLocalService = editionLocalService;
	}
}
