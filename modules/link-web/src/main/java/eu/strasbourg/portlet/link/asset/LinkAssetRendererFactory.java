package eu.strasbourg.portlet.link.asset;

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

import eu.strasbourg.service.link.model.Link;
import eu.strasbourg.service.link.service.LinkLocalService;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

import java.util.Locale;
import java.util.ResourceBundle;

@Component(
	immediate = true,
	property = {"javax.portlet.name=" + StrasbourgPortletKeys.LINK_WEB},
	service = AssetRendererFactory.class
)
public class LinkAssetRendererFactory extends BaseAssetRendererFactory<Link> {
	
	public static final String TYPE = "link";

	public LinkAssetRendererFactory() {
		setClassName(Link.class.getName());
		setLinkable(true);
		setPortletId(StrasbourgPortletKeys.LINK_WEB);
		setSearchable(true);
	}

	@Override
	public AssetRenderer<Link> getAssetRenderer(long classPK, int type)
		throws PortalException {
		Link entry = _linkLocalService.getLink(classPK);

		LinkAssetRenderer linkAssetRenderer =
			new LinkAssetRenderer(entry);

		linkAssetRenderer.setAssetRendererType(type);

		return linkAssetRenderer;

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
	
	private LinkLocalService _linkLocalService;

	@Reference(unbind = "-")
	protected void setLinkLocalService(LinkLocalService linkLocalService) {
		_linkLocalService = linkLocalService;
	}
}
