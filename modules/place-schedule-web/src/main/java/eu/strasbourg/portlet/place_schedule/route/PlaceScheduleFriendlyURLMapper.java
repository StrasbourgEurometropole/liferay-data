package eu.strasbourg.portlet.place_schedule.route;

import org.osgi.service.component.annotations.Component;

import com.liferay.portal.kernel.portlet.DefaultFriendlyURLMapper;
import com.liferay.portal.kernel.portlet.FriendlyURLMapper;

import eu.strasbourg.utils.constants.StrasbourgPortletKeys;


@Component(
	property = {
		"com.liferay.portlet.friendly-url-routes=META-INF/friendly-url-routes/place-schedule-routes.xml",
		"javax.portlet.name=" + StrasbourgPortletKeys.PLACE_SCHEDULE_WEB
	},
	service = FriendlyURLMapper.class
)
public class PlaceScheduleFriendlyURLMapper extends DefaultFriendlyURLMapper {
	
	@Override
    public String getMapping() {
        return _MAPPING;
    }

    private static final String _MAPPING = "schedules";
	
}
