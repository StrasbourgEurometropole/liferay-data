package eu.strasbourg.portlet.council.display.context;

import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

public class EditDeliberationDisplayContext {

    private final RenderRequest _request;
    private final ThemeDisplay _themeDisplay;

    public EditDeliberationDisplayContext(RenderRequest request,
                                     RenderResponse response) {
        this._request = request;
        this._themeDisplay = (ThemeDisplay) request
                .getAttribute(WebKeys.THEME_DISPLAY);
    }

}
