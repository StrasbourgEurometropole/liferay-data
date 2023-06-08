package eu.strasbourg.service.interest.asset;

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

import eu.strasbourg.service.interest.model.Interest;
import eu.strasbourg.service.interest.service.InterestLocalService;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

import java.util.Locale;
import java.util.ResourceBundle;

@Component(
	immediate = true,
	property = {"javax.portlet.name=" + StrasbourgPortletKeys.INTEREST_BO},
	service = AssetRendererFactory.class
)
public class InterestAssetRendererFactory extends BaseAssetRendererFactory<Interest> {
	
	public static final String TYPE = "interest";

	public InterestAssetRendererFactory() {
		setClassName(Interest.class.getName());
		setLinkable(true);
		setPortletId(StrasbourgPortletKeys.AGENDA_WEB);
		setSearchable(true);
	}

	@Override
	public AssetRenderer<Interest> getAssetRenderer(long classPK, int type)
		throws PortalException {
		Interest entry = _interestLocalService.getInterest(classPK);

		InterestAssetRenderer interestAssetRenderer =
			new InterestAssetRenderer(entry);

		interestAssetRenderer.setAssetRendererType(type);

		return interestAssetRenderer;

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
	
	private InterestLocalService _interestLocalService;

	@Reference(unbind = "-")
	protected void setInterestLocalService(InterestLocalService interestLocalService) {
		_interestLocalService = interestLocalService;
	}
}
