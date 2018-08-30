package eu.strasbourg.portlet.project.asset;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.liferay.asset.kernel.model.AssetRenderer;
import com.liferay.asset.kernel.model.AssetRendererFactory;
import com.liferay.asset.kernel.model.BaseAssetRendererFactory;
import com.liferay.portal.kernel.exception.PortalException;

import eu.strasbourg.service.project.model.Participation;
import eu.strasbourg.service.project.service.ParticipationLocalService;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

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
	
	private ParticipationLocalService _participationLocalService;

	@Reference(unbind = "-")
	protected void setInterestLocalService(ParticipationLocalService participationLocalService) {
		_participationLocalService = participationLocalService;
	}
}

