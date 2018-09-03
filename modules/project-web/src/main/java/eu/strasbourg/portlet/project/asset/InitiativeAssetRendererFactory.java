package eu.strasbourg.portlet.project.asset;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.liferay.asset.kernel.model.AssetRenderer;
import com.liferay.asset.kernel.model.AssetRendererFactory;
import com.liferay.asset.kernel.model.BaseAssetRendererFactory;
import com.liferay.portal.kernel.exception.PortalException;

import eu.strasbourg.service.project.model.Initiative;
import eu.strasbourg.service.project.service.InitiativeLocalService;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

@Component(
		immediate = true,
		property = {"javax.portlet.name=" + StrasbourgPortletKeys.PROJECT_WEB},
		service = AssetRendererFactory.class
	)
public class InitiativeAssetRendererFactory extends BaseAssetRendererFactory<Initiative> {
	
	public static final String TYPE = "initiative";

	public InitiativeAssetRendererFactory() {
		setClassName(Initiative.class.getName());
		setLinkable(true);
		setPortletId(StrasbourgPortletKeys.PROJECT_WEB);
		setSearchable(true);
	}

	@Override
	public AssetRenderer<Initiative> getAssetRenderer(long classPK, int type)
		throws PortalException {
		Initiative entry = _initiativeLocalService.getInitiative(classPK);

		InitiativeAssetRenderer initiativeAssetRenderer =
			new InitiativeAssetRenderer(entry);

		initiativeAssetRenderer.setAssetRendererType(type);

		return initiativeAssetRenderer;

	}

	@Override
	public String getType() {
		return TYPE;
	}
	
	
	private InitiativeLocalService _initiativeLocalService;

	@Reference(unbind = "-")
	protected void setInterestLocalService(InitiativeLocalService initiativeLocalService) {
		_initiativeLocalService = initiativeLocalService;
	}
}

