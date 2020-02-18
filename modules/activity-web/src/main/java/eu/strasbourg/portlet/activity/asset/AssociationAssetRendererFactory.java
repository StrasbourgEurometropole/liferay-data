package eu.strasbourg.portlet.activity.asset;

import com.liferay.asset.kernel.model.AssetRenderer;
import com.liferay.asset.kernel.model.AssetRendererFactory;
import com.liferay.asset.kernel.model.BaseAssetRendererFactory;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.portlet.PortletBag;
import com.liferay.portal.kernel.portlet.PortletBagPool;
import com.liferay.portal.kernel.util.ResourceBundleUtil;
import eu.strasbourg.service.activity.model.ActivityOrganizer;
import eu.strasbourg.service.activity.model.Association;
import eu.strasbourg.service.activity.service.ActivityOrganizerLocalService;
import eu.strasbourg.service.activity.service.AssociationLocalService;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.util.Locale;
import java.util.ResourceBundle;

@Component(
	immediate = true,
	property = {"javax.portlet.name=" + StrasbourgPortletKeys.ACTIVITY_WEB,
		},
	service = AssetRendererFactory.class
)
public class AssociationAssetRendererFactory extends BaseAssetRendererFactory<Association> {

	public static final String TYPE = "association";

	public AssociationAssetRendererFactory() {
		setClassName(Association.class.getName());
		setLinkable(true);
		setPortletId(StrasbourgPortletKeys.ACTIVITY_BO);
		setSearchable(true);
	}

	@Override
	public AssetRenderer<Association> getAssetRenderer(long classPK, int type)
		throws PortalException {
		Association entry = _associationLocalService.getAssociation(classPK);

		AssociationAssetRenderer associationAssetRenderer =
			new AssociationAssetRenderer(entry);

		associationAssetRenderer.setAssetRendererType(type);

		return associationAssetRenderer;

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
	
	private AssociationLocalService _associationLocalService;

	@Reference(unbind = "-")
	protected void setAssociationLocalService(AssociationLocalService associationLocalService) {
		_associationLocalService = associationLocalService;
	}
}
