package eu.strasbourg.portlet.activity.asset;

import com.liferay.asset.kernel.model.AssetRenderer;
import com.liferay.asset.kernel.model.AssetRendererFactory;
import com.liferay.asset.kernel.model.BaseAssetRendererFactory;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.portlet.PortletBag;
import com.liferay.portal.kernel.portlet.PortletBagPool;
import com.liferay.portal.kernel.util.ResourceBundleUtil;
import eu.strasbourg.service.activity.model.Practice;
import eu.strasbourg.service.activity.service.PracticeLocalService;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.util.Locale;
import java.util.ResourceBundle;

@Component(
	immediate = true,
	property = {"javax.portlet.name=" + StrasbourgPortletKeys.ACTIVITY_WEB},
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

	@Override
	public String getTypeName(Locale locale) {
		String key = getClassName();

		String value = LanguageUtil.get(locale, key, null);

		String portletId = getPortletId();

		if ((value == null) && (portletId != null)) {
			PortletBag portletBag = PortletBagPool.get(portletId);

			ResourceBundle resourceBundle = portletBag.getResourceBundle(
					locale);

			if (resourceBundle != null) {
				value = ResourceBundleUtil.getString(resourceBundle, key);
			}
		}

		if (value == null) {
			value = getClassName();
		}

		return value;
	}
	
	private PracticeLocalService _practiceLocalService;

	@Reference(unbind = "-")
	protected void setPracticeLocalService(PracticeLocalService practiceLocalService) {
		_practiceLocalService = practiceLocalService;
	}
}
