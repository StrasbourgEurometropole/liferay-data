package eu.strasbourg.portlet.ejob.asset;

import com.liferay.asset.kernel.model.AssetRenderer;
import com.liferay.asset.kernel.model.AssetRendererFactory;
import com.liferay.asset.kernel.model.BaseAssetRendererFactory;
import com.liferay.portal.kernel.exception.PortalException;
import eu.strasbourg.service.ejob.model.Offer;
import eu.strasbourg.service.ejob.service.OfferLocalService;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
	immediate = true,
	property = {"javax.portlet.name=" + StrasbourgPortletKeys.EJOB_WEB},
	service = AssetRendererFactory.class
)
public class OfferAssetRendererFactory extends BaseAssetRendererFactory<Offer> {
	
	public static final String TYPE = "offer";

	public OfferAssetRendererFactory() {
		setClassName(Offer.class.getName());
		setLinkable(true);
		setPortletId(StrasbourgPortletKeys.EJOB_WEB);
		setSearchable(true);
	}

	@Override
	public AssetRenderer<Offer> getAssetRenderer(long classPK, int type)
		throws PortalException {
		Offer entry = _offerLocalService.getOffer(classPK);

		OfferAssetRenderer offerAssetRenderer =
			new OfferAssetRenderer(entry);

		offerAssetRenderer.setAssetRendererType(type);

		return offerAssetRenderer;

	}

	@Override
	public String getType() {
		return TYPE;
	}
	
	
	private OfferLocalService _offerLocalService;

	@Reference(unbind = "-")
	protected void setOfferLocalService(OfferLocalService offerLocalService) {
		_offerLocalService = offerLocalService;
	}
}
