package eu.strasbourg.portlet.project.asset;

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

import eu.strasbourg.service.project.model.Participation;
import eu.strasbourg.service.project.service.ParticipationLocalService;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

import java.util.Locale;
import java.util.ResourceBundle;

@Component(
		immediate = true,
		property = {"javax.portlet.name=" + StrasbourgPortletKeys.PROJECT_WEB},
		service = AssetRendererFactory.class
	)
public class ParticipationAssetRendererFactory extends BaseAssetRendererFactory<Participation> {
	
	public static final String TYPE = "participation";

	public ParticipationAssetRendererFactory() {
		setClassName(Participation.class.getName());
		setLinkable(true);
		setPortletId(StrasbourgPortletKeys.PROJECT_WEB);
		setSearchable(true);
	}

	@Override
	public AssetRenderer<Participation> getAssetRenderer(long classPK, int type)
		throws PortalException {
		Participation entry = _participationLocalService.getParticipation(classPK);

		ParticipationAssetRenderer participationAssetRenderer =
			new ParticipationAssetRenderer(entry);

		participationAssetRenderer.setAssetRendererType(type);

		return participationAssetRenderer;

	}

	@Override
	public String getType() {
		return TYPE;
	}

	/**
	 * Notes : surcharge de la méthode pour enlever le préfix du className "model.resource" non présent sur les modules
	 * 			custom en 7.0
	 */
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
	
	private ParticipationLocalService _participationLocalService;

	@Reference(unbind = "-")
	protected void setInterestLocalService(ParticipationLocalService participationLocalService) {
		_participationLocalService = participationLocalService;
	}
}

