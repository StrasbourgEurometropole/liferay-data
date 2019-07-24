package eu.strasbourg.portlet.activity.asset;

import com.liferay.asset.kernel.model.AssetRenderer;
import com.liferay.asset.kernel.model.AssetRendererFactory;
import com.liferay.asset.kernel.model.BaseAssetRendererFactory;
import com.liferay.portal.kernel.exception.PortalException;
import eu.strasbourg.service.activity.model.AssociationActivity;
import eu.strasbourg.service.activity.service.AssociationActivityLocalService;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
	immediate = true,
	property = {"javax.portlet.name=" + StrasbourgPortletKeys.ACTIVITY_WEB,
		},
	service = AssetRendererFactory.class
)
public class AssociationActivityAssetRendererFactory extends BaseAssetRendererFactory<AssociationActivity> {

	public static final String TYPE = "associationActivity";

	public AssociationActivityAssetRendererFactory() {
		setClassName(AssociationActivity.class.getName());
		setLinkable(true);
		setPortletId(StrasbourgPortletKeys.ACTIVITY_BO);
		setSearchable(true);
	}

	@Override
	public AssetRenderer<AssociationActivity> getAssetRenderer(long classPK, int type)
		throws PortalException {
		AssociationActivity entry = _associationActivityLocalService.getAssociationActivity(classPK);

		AssociationActivityAssetRenderer associationActivityAssetRenderer =
			new AssociationActivityAssetRenderer(entry);

		associationActivityAssetRenderer.setAssetRendererType(type);

		return associationActivityAssetRenderer;

	}

	@Override
	public String getType() {
		return TYPE;
	}
	
	
	private AssociationActivityLocalService _associationActivityLocalService;

	@Reference(unbind = "-")
	protected void setAssociationActivityLocalService(AssociationActivityLocalService associationActivityLocalService) {
		_associationActivityLocalService = associationActivityLocalService;
	}
}
