package eu.strasbourg.portlet.place.asset;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.liferay.asset.kernel.model.AssetRenderer;
import com.liferay.asset.kernel.model.AssetRendererFactory;
import com.liferay.asset.kernel.model.BaseAssetRendererFactory;
import com.liferay.portal.kernel.exception.PortalException;

import eu.strasbourg.service.place.model.Place;
import eu.strasbourg.service.place.service.PlaceLocalService;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

@Component(
	immediate = true,
	property = {"javax.portlet.name=" + StrasbourgPortletKeys.PLACE_WEB},
	service = AssetRendererFactory.class
)
public class PlaceAssetRendererFactory extends BaseAssetRendererFactory<Place> {
	
	public static final String TYPE = "place";

	public PlaceAssetRendererFactory() {
		setClassName(Place.class.getName());
		setLinkable(true);
		setPortletId(StrasbourgPortletKeys.PLACE_WEB);
		setSearchable(true);
	}

	@Override
	public AssetRenderer<Place> getAssetRenderer(long classPK, int type)
		throws PortalException {
		Place entry = _placeLocalService.getPlace(classPK);

		PlaceAssetRenderer placeAssetRenderer =
			new PlaceAssetRenderer(entry);

		placeAssetRenderer.setAssetRendererType(type);

		return placeAssetRenderer;

	}

	@Override
	public String getType() {
		return TYPE;
	}
	
	private PlaceLocalService _placeLocalService;

	@Reference(unbind = "-")
	protected void setPlaceLocalService(PlaceLocalService placeLocalService) {
		_placeLocalService = placeLocalService;
	}
}
