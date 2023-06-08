package eu.strasbourg.portlet.entity_detail.route;

import org.osgi.service.component.annotations.Component;

import com.liferay.portal.kernel.portlet.DefaultFriendlyURLMapper;
import com.liferay.portal.kernel.portlet.FriendlyURLMapper;

import eu.strasbourg.utils.constants.StrasbourgPortletKeys;


@Component(
	property = {
		"com.liferay.portlet.friendly-url-routes=META-INF/friendly-url-routes/entity-detail-routes.xml",
		"javax.portlet.name=" + StrasbourgPortletKeys.ENTITY_DETAIL_WEB
	},
	service = FriendlyURLMapper.class
)
public class EntityDetailFriendlyURLMapper extends DefaultFriendlyURLMapper {
	
	@Override
    public String getMapping() {
        return _MAPPING;
    }

    private static final String _MAPPING = "entity";
	
}
