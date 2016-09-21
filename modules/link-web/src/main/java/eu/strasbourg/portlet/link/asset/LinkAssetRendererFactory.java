package eu.strasbourg.portlet.link.asset;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.liferay.asset.kernel.model.AssetRenderer;
import com.liferay.asset.kernel.model.AssetRendererFactory;
import com.liferay.asset.kernel.model.BaseAssetRendererFactory;
import com.liferay.portal.kernel.exception.PortalException;

import eu.strasbourg.service.link.model.Link;
import eu.strasbourg.service.link.service.LinkLocalService;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

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
	
	private LinkLocalService _linkLocalService;

	@Reference(unbind = "-")
	protected void setLinkLocalService(LinkLocalService linkLocalService) {
		_linkLocalService = linkLocalService;
	}
}
