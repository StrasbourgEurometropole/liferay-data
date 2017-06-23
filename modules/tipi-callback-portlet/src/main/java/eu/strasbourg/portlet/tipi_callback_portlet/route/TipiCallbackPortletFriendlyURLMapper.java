package eu.strasbourg.portlet.tipi_callback_portlet.route;

import org.osgi.service.component.annotations.Component;

import com.liferay.portal.kernel.portlet.DefaultFriendlyURLMapper;
import com.liferay.portal.kernel.portlet.FriendlyURLMapper;

import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

@Component(
	property = {
		"com.liferay.portlet.friendly-url-routes=META-INF/friendly-url-routes/tipi-callback-routes.xml",
		"javax.portlet.name=" + StrasbourgPortletKeys.TIPI_CALLBACK_PORTLET_WEB
	},
	service = FriendlyURLMapper.class
)
public class TipiCallbackPortletFriendlyURLMapper extends DefaultFriendlyURLMapper {
	
	@Override
    public String getMapping() {
        return _MAPPING;
    }

    private static final String _MAPPING = "tipi";
	
}
