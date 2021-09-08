package eu.strasbourg.portlet.agenda.csmap.display.context;

import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;

import javax.portlet.RenderRequest;

public class ViewCsmapAgendaCategoriesDisplayContext {
    private final RenderRequest request;
    private final ThemeDisplay themeDisplay;

    public ViewCsmapAgendaCategoriesDisplayContext(RenderRequest request) {
        this.request = request;
        this.themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
    }
}
