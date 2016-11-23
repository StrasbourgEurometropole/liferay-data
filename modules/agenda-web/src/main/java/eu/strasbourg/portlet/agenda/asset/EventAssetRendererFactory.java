package eu.strasbourg.portlet.agenda.asset;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.liferay.asset.kernel.model.AssetRenderer;
import com.liferay.asset.kernel.model.AssetRendererFactory;
import com.liferay.asset.kernel.model.BaseAssetRendererFactory;
import com.liferay.portal.kernel.exception.PortalException;

import eu.strasbourg.service.agenda.model.Event;
import eu.strasbourg.service.agenda.service.EventLocalService;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

@Component(
	immediate = true,
	property = {"javax.portlet.name=" + StrasbourgPortletKeys.AGENDA_WEB},
	service = AssetRendererFactory.class
)
public class EventAssetRendererFactory extends BaseAssetRendererFactory<Event> {
	
	public static final String TYPE = "event";

	public EventAssetRendererFactory() {
		setClassName(Event.class.getName());
		setLinkable(true);
		setPortletId(StrasbourgPortletKeys.AGENDA_WEB);
		setSearchable(true);
	}

	@Override
	public AssetRenderer<Event> getAssetRenderer(long classPK, int type)
		throws PortalException {
		Event entry = _eventLocalService.getEvent(classPK);

		EventAssetRenderer eventAssetRenderer =
			new EventAssetRenderer(entry);

		eventAssetRenderer.setAssetRendererType(type);

		return eventAssetRenderer;

	}

	@Override
	public String getType() {
		return TYPE;
	}
	
	
	private EventLocalService _eventLocalService;

	@Reference(unbind = "-")
	protected void setEventLocalService(EventLocalService eventLocalService) {
		_eventLocalService = eventLocalService;
	}
}
