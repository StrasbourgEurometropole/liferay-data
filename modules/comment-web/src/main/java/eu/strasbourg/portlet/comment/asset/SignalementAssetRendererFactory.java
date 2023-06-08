package eu.strasbourg.portlet.comment.asset;

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

import eu.strasbourg.service.comment.model.Signalement;
import eu.strasbourg.service.comment.service.SignalementLocalService;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

import java.util.Locale;
import java.util.ResourceBundle;

@Component(
		immediate = true,
		property = {
			"javax.portlet.name=" + StrasbourgPortletKeys.COMMENT_WEB
		},
		service = AssetRendererFactory.class
	)
public class SignalementAssetRendererFactory extends BaseAssetRendererFactory<Signalement> {
	private SignalementLocalService _signalementLocalService;

	public static final String TYPE = "signalement";

	public SignalementAssetRendererFactory() {
		setClassName(Signalement.class.getName());
		setLinkable(true);
		setPortletId(StrasbourgPortletKeys.PROJECT_WEB);
		setSearchable(true);
	}

	@Override
	public AssetRenderer<Signalement> getAssetRenderer(long classPK, int type)
		throws PortalException {
		Signalement entry = _signalementLocalService.getSignalement(classPK);

		SignalementAssetRenderer signalementAssetRenderer =
			new SignalementAssetRenderer(entry);

		signalementAssetRenderer.setAssetRendererType(type);

		return signalementAssetRenderer;

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

	@Reference(unbind = "-")
	protected void setInterestLocalService(SignalementLocalService signalementLocalService) {
		_signalementLocalService = signalementLocalService;
	}
}
