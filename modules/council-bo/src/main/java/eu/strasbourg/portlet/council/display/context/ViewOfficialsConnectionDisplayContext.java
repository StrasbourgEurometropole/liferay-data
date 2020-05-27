package eu.strasbourg.portlet.council.display.context;

import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

public class ViewOfficialsConnectionDisplayContext {
	
	/** Contexte de la requête */
	private RenderRequest request;
	private RenderResponse response;
	private ThemeDisplay themeDisplay;
	
	public ViewOfficialsConnectionDisplayContext(RenderRequest request, RenderResponse response) {
        this.request = request;
        this.response = response;
        this.themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
    }

}
