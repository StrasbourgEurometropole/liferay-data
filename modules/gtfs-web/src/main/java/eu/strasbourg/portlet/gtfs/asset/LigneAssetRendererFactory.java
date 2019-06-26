package eu.strasbourg.portlet.gtfs.asset;

import com.liferay.asset.kernel.model.AssetRenderer;
import com.liferay.asset.kernel.model.AssetRendererFactory;
import com.liferay.asset.kernel.model.BaseAssetRendererFactory;
import com.liferay.portal.kernel.exception.PortalException;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import eu.strasbourg.service.gtfs.model.Ligne;
import eu.strasbourg.service.gtfs.service.LigneLocalService;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + StrasbourgPortletKeys.GTFS_WEB
	},
	service = AssetRendererFactory.class
)
public class LigneAssetRendererFactory extends BaseAssetRendererFactory<Ligne> {

	private LigneLocalService _ligneLocalService;

	public static final String TYPE = "ligne";

	public LigneAssetRendererFactory() {
		setClassName(Ligne.class.getName());
		setLinkable(true);
		setPortletId(StrasbourgPortletKeys.GTFS_WEB);
		setSearchable(true);
	}

	@Override
	public AssetRenderer<Ligne> getAssetRenderer(long classPK, int type)
		throws PortalException {
		Ligne entry = _ligneLocalService.getLigne(classPK);

		LigneAssetRenderer ligneAssetRenderer = new LigneAssetRenderer(entry);

		ligneAssetRenderer.setAssetRendererType(type);

		return ligneAssetRenderer;

	}

	@Override
	public String getType() {
		return TYPE;
	}

	@Reference(unbind = "-")
	protected void setLigneLocalService(LigneLocalService ligneLocalService) {
		_ligneLocalService = ligneLocalService;
	}
	
}
