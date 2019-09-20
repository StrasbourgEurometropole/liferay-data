package eu.strasbourg.portlet.activity.asset;

import com.liferay.asset.kernel.model.AssetRenderer;
import com.liferay.asset.kernel.model.AssetRendererFactory;
import com.liferay.asset.kernel.model.BaseAssetRendererFactory;
import com.liferay.portal.kernel.exception.PortalException;
import eu.strasbourg.service.activity.model.Practice;
import eu.strasbourg.service.activity.service.PracticeLocalService;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
	immediate = true,
	property = {"javax.portlet.name=" + StrasbourgPortletKeys.ACTIVITY_WEB,
		},
	service = AssetRendererFactory.class
)
public class PracticeAssetRendererFactory extends BaseAssetRendererFactory<Practice> {

	public static final String TYPE = "practice";

	public PracticeAssetRendererFactory() {
		setClassName(Practice.class.getName());
		setLinkable(true);
		setPortletId(StrasbourgPortletKeys.ACTIVITY_BO);
		setSearchable(true);
	}

	@Override
	public AssetRenderer<Practice> getAssetRenderer(long classPK, int type)
		throws PortalException {
		Practice entry = _practiceLocalService.getPractice(classPK);

		PracticeAssetRenderer practiceAssetRenderer =
			new PracticeAssetRenderer(entry);

		practiceAssetRenderer.setAssetRendererType(type);

		return practiceAssetRenderer;

	}

	@Override
	public String getType() {
		return TYPE;
	}
	
	
	private PracticeLocalService _practiceLocalService;

	@Reference(unbind = "-")
	protected void setPracticeLocalService(PracticeLocalService practiceLocalService) {
		_practiceLocalService = practiceLocalService;
	}
}
