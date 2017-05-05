package eu.strasbourg.portlet.official.asset;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.liferay.asset.kernel.model.AssetRenderer;
import com.liferay.asset.kernel.model.AssetRendererFactory;
import com.liferay.asset.kernel.model.BaseAssetRendererFactory;
import com.liferay.portal.kernel.exception.PortalException;

import eu.strasbourg.service.official.model.Official;
import eu.strasbourg.service.official.service.OfficialLocalService;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

@Component(
	immediate = true,
	property = {"javax.portlet.name=" + StrasbourgPortletKeys.OFFICIAL_WEB},
	service = AssetRendererFactory.class
)
public class OfficialAssetRendererFactory extends BaseAssetRendererFactory<Official> {
	
	public static final String TYPE = "official";

	public OfficialAssetRendererFactory() {
		setClassName(Official.class.getName());
		setLinkable(true);
		setPortletId(StrasbourgPortletKeys.OFFICIAL_WEB);
		setSearchable(true);
	}

	@Override
	public AssetRenderer<Official> getAssetRenderer(long classPK, int type)
		throws PortalException {
		Official entry = _officialLocalService.getOfficial(classPK);

		OfficialAssetRenderer officialAssetRenderer =
			new OfficialAssetRenderer(entry);

		officialAssetRenderer.setAssetRendererType(type);

		return officialAssetRenderer;

	}

	@Override
	public String getType() {
		return TYPE;
	}
	
	
	private OfficialLocalService _officialLocalService;

	@Reference(unbind = "-")
	protected void setOfficialLocalService(OfficialLocalService officialLocalService) {
		_officialLocalService = officialLocalService;
	}
}
