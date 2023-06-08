package eu.strasbourg.portlet.agenda.asset;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.liferay.asset.kernel.model.AssetRenderer;
import com.liferay.asset.kernel.model.AssetRendererFactory;
import com.liferay.asset.kernel.model.BaseAssetRendererFactory;
import com.liferay.portal.kernel.exception.PortalException;

import eu.strasbourg.service.agenda.model.Manifestation;
import eu.strasbourg.service.agenda.service.ManifestationLocalService;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

@Component(
	immediate = true,
	property = {"javax.portlet.name=" + StrasbourgPortletKeys.AGENDA_WEB},
	service = AssetRendererFactory.class
)
public class ManifestationAssetRendererFactory extends BaseAssetRendererFactory<Manifestation> {
	
	public static final String TYPE = "manifestation";

	public ManifestationAssetRendererFactory() {
		setClassName(Manifestation.class.getName());
		setLinkable(true);
		setPortletId(StrasbourgPortletKeys.AGENDA_WEB);
		setSearchable(true);
	}

	@Override
	public AssetRenderer<Manifestation> getAssetRenderer(long classPK, int type)
		throws PortalException {
		Manifestation entry = _manifestationLocalService.getManifestation(classPK);

		ManifestationAssetRenderer manifestationAssetRenderer =
			new ManifestationAssetRenderer(entry);

		manifestationAssetRenderer.setAssetRendererType(type);

		return manifestationAssetRenderer;

	}

	@Override
	public String getType() {
		return TYPE;
	}
	
	
	private ManifestationLocalService _manifestationLocalService;

	@Reference(unbind = "-")
	protected void setManifestationLocalService(ManifestationLocalService manifestationLocalService) {
		_manifestationLocalService = manifestationLocalService;
	}
}
