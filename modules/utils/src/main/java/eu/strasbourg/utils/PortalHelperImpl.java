package eu.strasbourg.utils;

import com.liferay.portal.kernel.theme.ThemeDisplay;
import eu.strasbourg.utils.api.AssetPublisherTemplateHelperService;
import eu.strasbourg.utils.api.PortalHelperService;
import org.osgi.service.component.annotations.Component;

@Component(
        immediate = true,
        property = {},
        service = PortalHelperService.class)
public class PortalHelperImpl implements PortalHelperService {

    @Override
    public String getHomeURL(ThemeDisplay themeDisplay) {
        return PortalHelper.getHomeURL(themeDisplay);
    }

    @Override
    public String getPortalURL(ThemeDisplay themeDisplay) {
        return PortalHelper.getPortalURL(themeDisplay);
    }
}
