package eu.strasbourg.portlet.gtfs.asset;

import com.liferay.asset.kernel.model.AssetRenderer;
import com.liferay.asset.kernel.model.AssetRendererFactory;
import com.liferay.asset.kernel.model.BaseAssetRendererFactory;
import com.liferay.portal.kernel.exception.PortalException;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import eu.strasbourg.service.gtfs.model.Arret;
import eu.strasbourg.service.gtfs.service.ArretLocalService;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + StrasbourgPortletKeys.GTFS_WEB
	},
	service = AssetRendererFactory.class
)
public class ArretAssetRendererFactory extends BaseAssetRendererFactory<Arret> {

	private ArretLocalService _arretLocalService;

	public static final String TYPE = "arret";

	public ArretAssetRendererFactory() {
		setClassName(Arret.class.getName());
		setLinkable(true);
		setPortletId(StrasbourgPortletKeys.GTFS_WEB);
		setSearchable(true);
	}

	@Override
	public AssetRenderer<Arret> getAssetRenderer(long classPK, int type)
		throws PortalException {
		Arret entry = _arretLocalService.getArret(classPK);

		ArretAssetRenderer arretAssetRenderer = new ArretAssetRenderer(entry);

		arretAssetRenderer.setAssetRendererType(type);

		return arretAssetRenderer;

	}

	@Override
	public String getType() {
		return TYPE;
	}

	@Reference(unbind = "-")
	protected void setArretLocalService(ArretLocalService arretLocalService) {
		_arretLocalService = arretLocalService;
	}
	
}
