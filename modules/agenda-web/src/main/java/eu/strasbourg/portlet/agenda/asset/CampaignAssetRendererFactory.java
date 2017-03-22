package eu.strasbourg.portlet.agenda.asset;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.liferay.asset.kernel.model.AssetRenderer;
import com.liferay.asset.kernel.model.AssetRendererFactory;
import com.liferay.asset.kernel.model.BaseAssetRendererFactory;
import com.liferay.portal.kernel.exception.PortalException;

import eu.strasbourg.service.agenda.model.Campaign;
import eu.strasbourg.service.agenda.service.CampaignLocalService;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

@Component(
	immediate = true,
	property = {"javax.portlet.name=" + StrasbourgPortletKeys.AGENDA_WEB},
	service = AssetRendererFactory.class
)
public class CampaignAssetRendererFactory extends BaseAssetRendererFactory<Campaign> {
	
	public static final String TYPE = "campaign";

	public CampaignAssetRendererFactory() {
		setClassName(Campaign.class.getName());
		setLinkable(true);
		setPortletId(StrasbourgPortletKeys.AGENDA_WEB);
		setSearchable(true);
	}

	@Override
	public AssetRenderer<Campaign> getAssetRenderer(long classPK, int type)
		throws PortalException {
		Campaign entry = _campaignLocalService.getCampaign(classPK);

		CampaignAssetRenderer campaignAssetRenderer =
			new CampaignAssetRenderer(entry);

		campaignAssetRenderer.setAssetRendererType(type);

		return campaignAssetRenderer;

	}

	@Override
	public String getType() {
		return TYPE;
	}
	
	
	private CampaignLocalService _campaignLocalService;

	@Reference(unbind = "-")
	protected void setCampaignLocalService(CampaignLocalService campaignLocalService) {
		_campaignLocalService = campaignLocalService;
	}
}
