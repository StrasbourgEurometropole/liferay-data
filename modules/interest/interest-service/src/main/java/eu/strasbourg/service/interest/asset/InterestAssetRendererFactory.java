package eu.strasbourg.service.interest.asset;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.liferay.asset.kernel.model.AssetRenderer;
import com.liferay.asset.kernel.model.AssetRendererFactory;
import com.liferay.asset.kernel.model.BaseAssetRendererFactory;
import com.liferay.portal.kernel.exception.PortalException;

import eu.strasbourg.service.interest.model.Interest;
import eu.strasbourg.service.interest.service.InterestLocalService;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

@Component(
	immediate = true,
	property = {"javax.portlet.name=" + StrasbourgPortletKeys.INTEREST_BO},
	service = AssetRendererFactory.class
)
public class InterestAssetRendererFactory extends BaseAssetRendererFactory<Interest> {
	
	public static final String TYPE = "interest";

	public InterestAssetRendererFactory() {
		setClassName(Interest.class.getName());
		setLinkable(true);
		setPortletId(StrasbourgPortletKeys.AGENDA_WEB);
		setSearchable(true);
	}

	@Override
	public AssetRenderer<Interest> getAssetRenderer(long classPK, int type)
		throws PortalException {
		Interest entry = _interestLocalService.getInterest(classPK);

		InterestAssetRenderer interestAssetRenderer =
			new InterestAssetRenderer(entry);

		interestAssetRenderer.setAssetRendererType(type);

		return interestAssetRenderer;

	}

	@Override
	public String getType() {
		return TYPE;
	}
	
	
	private InterestLocalService _interestLocalService;

	@Reference(unbind = "-")
	protected void setInterestLocalService(InterestLocalService interestLocalService) {
		_interestLocalService = interestLocalService;
	}
}
