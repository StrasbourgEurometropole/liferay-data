package eu.strasbourg.portlet.comment.asset;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.liferay.asset.kernel.model.AssetRenderer;
import com.liferay.asset.kernel.model.AssetRendererFactory;
import com.liferay.asset.kernel.model.BaseAssetRendererFactory;
import com.liferay.portal.kernel.exception.PortalException;

import eu.strasbourg.service.comment.model.Signalement;
import eu.strasbourg.service.comment.service.SignalementLocalService;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

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

	@Reference(unbind = "-")
	protected void setInterestLocalService(SignalementLocalService signalementLocalService) {
		_signalementLocalService = signalementLocalService;
	}
}
