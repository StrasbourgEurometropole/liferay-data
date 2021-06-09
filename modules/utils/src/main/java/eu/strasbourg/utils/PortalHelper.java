package eu.strasbourg.utils;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;

import javax.portlet.RenderRequest;
import java.util.logging.Logger;

public class PortalHelper {

    public static String getHomeURL(ThemeDisplay themeDisplay) {
        String home = "";
        String virtualHostName = themeDisplay.getScopeGroup().getPublicLayoutSet().getVirtualHostname();
        boolean stagingGroup = themeDisplay.getScopeGroup().isStagingGroup();
        if(Validator.isNotNull(virtualHostName) && ! stagingGroup)
            home = "";
        else {
            home = "/web" + themeDisplay.getLayout().getGroup().getFriendlyURL();
        }
        return home;
    }

    public static String getPortalURL(ThemeDisplay themeDisplay)  {
        String home = "";
        try {
            String virtualHostName = themeDisplay.getScopeGroup().getPublicLayoutSet().getVirtualHostname();
            boolean stagingGroup = themeDisplay.getScopeGroup().isStagingGroup();
            if (Validator.isNotNull(virtualHostName) && !stagingGroup) {
                home = virtualHostName;
            } else {
                home = PortalUtil.getPortalURL(themeDisplay);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return home;
    }
}
