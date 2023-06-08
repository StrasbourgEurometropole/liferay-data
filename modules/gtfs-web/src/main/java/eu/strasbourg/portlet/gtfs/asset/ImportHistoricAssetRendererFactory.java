package eu.strasbourg.portlet.gtfs.asset;

import com.liferay.asset.kernel.model.AssetRenderer;
import com.liferay.asset.kernel.model.AssetRendererFactory;
import com.liferay.asset.kernel.model.BaseAssetRendererFactory;
import com.liferay.portal.kernel.exception.PortalException;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import eu.strasbourg.service.gtfs.model.ImportHistoric;
import eu.strasbourg.service.gtfs.service.ImportHistoricLocalService;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + StrasbourgPortletKeys.GTFS_WEB
	},
	service = AssetRendererFactory.class
)
public class ImportHistoricAssetRendererFactory extends BaseAssetRendererFactory<ImportHistoric> {

	private ImportHistoricLocalService _importHistoricLocalService;

	public static final String TYPE = "importHistoric";

	public ImportHistoricAssetRendererFactory() {
		setClassName(ImportHistoric.class.getName());
		setLinkable(true);
		setPortletId(StrasbourgPortletKeys.GTFS_WEB);
		setSearchable(true);
	}

	@Override
	public AssetRenderer<ImportHistoric> getAssetRenderer(long classPK, int type)
		throws PortalException {
		ImportHistoric entry = _importHistoricLocalService.getImportHistoric(classPK);

		ImportHistoricAssetRenderer importHistoricAssetRenderer = new ImportHistoricAssetRenderer(entry);

		importHistoricAssetRenderer.setAssetRendererType(type);

		return importHistoricAssetRenderer;

	}

	@Override
	public String getType() {
		return TYPE;
	}

	@Reference(unbind = "-")
	protected void setImportHistoricLocalService(ImportHistoricLocalService importHistoricLocalService) {
		_importHistoricLocalService = importHistoricLocalService;
	}
	
}
