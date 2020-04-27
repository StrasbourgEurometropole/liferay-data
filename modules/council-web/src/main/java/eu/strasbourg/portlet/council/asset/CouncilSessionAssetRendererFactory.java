package eu.strasbourg.portlet.council.asset;

import com.liferay.asset.kernel.model.AssetRenderer;
import com.liferay.asset.kernel.model.AssetRendererFactory;
import com.liferay.asset.kernel.model.BaseAssetRendererFactory;
import com.liferay.portal.kernel.exception.PortalException;
import eu.strasbourg.service.council.model.CouncilSession;
import eu.strasbourg.service.council.service.CouncilSessionLocalService;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + StrasbourgPortletKeys.COUNCIL_WEB
	},
	service = AssetRendererFactory.class
)
public class CouncilSessionAssetRendererFactory extends BaseAssetRendererFactory<CouncilSession> {

	private CouncilSessionLocalService councilSessionLocalService;

	public static final String TYPE = "councilSession";

	public CouncilSessionAssetRendererFactory() {
		setClassName(CouncilSession.class.getName());
		setLinkable(true);
		setPortletId(StrasbourgPortletKeys.COUNCIL_WEB);
		setSearchable(true);
	}

	@Override
	public AssetRenderer<CouncilSession> getAssetRenderer(long classPK, int type)
		throws PortalException {
		CouncilSession entry = this.councilSessionLocalService.getCouncilSession(classPK);

		CouncilSessionAssetRenderer councilSessionAssetRenderer = new CouncilSessionAssetRenderer(entry);

		councilSessionAssetRenderer.setAssetRendererType(type);

		return councilSessionAssetRenderer;
	}

	@Override
	public String getType() {
		return TYPE;
	}

	@Reference(unbind = "-")
	protected void setCouncilSessionLocalService(CouncilSessionLocalService councilSessionLocalService) {
		this.councilSessionLocalService = councilSessionLocalService;
	}

}
