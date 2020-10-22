package eu.strasbourg.portlet.official.asset;

import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.portlet.PortletBag;
import com.liferay.portal.kernel.portlet.PortletBagPool;
import com.liferay.portal.kernel.util.ResourceBundleUtil;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.liferay.asset.kernel.model.AssetRenderer;
import com.liferay.asset.kernel.model.AssetRendererFactory;
import com.liferay.asset.kernel.model.BaseAssetRendererFactory;
import com.liferay.portal.kernel.exception.PortalException;

import eu.strasbourg.service.official.model.Official;
import eu.strasbourg.service.official.service.OfficialLocalService;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

import java.util.Locale;
import java.util.ResourceBundle;

@Component(
	immediate = true,
	property = {"javax.portlet.name=" + StrasbourgPortletKeys.OFFICIAL_WEB},
	service = AssetRendererFactory.class
)
public class OfficialAssetRendererFactory extends BaseAssetRendererFactory<Official> {
	
	public static final String TYPE = "official";

	public OfficialAssetRendererFactory() {
		setClassName(Official.class.getName());
		setLinkable(true);
		setPortletId(StrasbourgPortletKeys.OFFICIAL_WEB);
		setSearchable(true);
	}

	@Override
	public AssetRenderer<Official> getAssetRenderer(long classPK, int type)
		throws PortalException {
		Official entry = _officialLocalService.getOfficial(classPK);

		OfficialAssetRenderer officialAssetRenderer =
			new OfficialAssetRenderer(entry);

		officialAssetRenderer.setAssetRendererType(type);

		return officialAssetRenderer;

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
	
	
	private OfficialLocalService _officialLocalService;

	@Reference(unbind = "-")
	protected void setOfficialLocalService(OfficialLocalService officialLocalService) {
		_officialLocalService = officialLocalService;
	}
}
