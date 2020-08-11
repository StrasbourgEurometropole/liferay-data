package eu.strasbourg.portlet.ejob.asset;

import com.liferay.asset.kernel.model.AssetRenderer;
import com.liferay.asset.kernel.model.AssetRendererFactory;
import com.liferay.asset.kernel.model.BaseAssetRendererFactory;
import com.liferay.portal.kernel.exception.PortalException;
import eu.strasbourg.service.ejob.model.Alert;
import eu.strasbourg.service.ejob.service.AlertLocalService;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
	immediate = true,
	property = {"javax.portlet.name=" + StrasbourgPortletKeys.EJOB_WEB},
	service = AssetRendererFactory.class
)
public class AlertAssetRendererFactory extends BaseAssetRendererFactory<Alert> {

	private AlertLocalService _alertLocalService;

	public static final String TYPE = "alert";

	public AlertAssetRendererFactory() {
		setClassName(Alert.class.getName());
		setLinkable(true);
		setPortletId(StrasbourgPortletKeys.EJOB_WEB);
		setSearchable(true);
	}

	@Override
	public AssetRenderer<Alert> getAssetRenderer(long classPK, int type)
		throws PortalException {
		Alert entry = _alertLocalService.getAlert(classPK);

		AlertAssetRenderer alertAssetRenderer =
			new AlertAssetRenderer(entry);

		alertAssetRenderer.setAssetRendererType(type);

		return alertAssetRenderer;

	}

	@Override
	public String getType() {
		return TYPE;
	}


	@Reference(unbind = "-")
	protected void setAlertLocalService(AlertLocalService alertLocalService) {
		_alertLocalService = alertLocalService;
	}
}
