package eu.strasbourg.portlet.zone;

import java.io.IOException;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;

import eu.strasbourg.service.adict.AdictService;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

/**
 * @author angelique.champougny
 */
@Component(immediate = true, property = { "com.liferay.portlet.display-category=Strasbourg",
		"com.liferay.portlet.instanceable=true", 
		"com.liferay.portlet.required-namespaced-parameters=false",
		"javax.portlet.display-name=quelle est ma zone", 
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.config-template=/configuration/zone-configuration.jsp",
		"javax.portlet.init-param.view-template=/zone-view.jsp", 
		"javax.portlet.name=" + StrasbourgPortletKeys.ZONE_WEB,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user" }, service = Portlet.class)
public class ZonePortlet extends MVCPortlet {

	private Log log = LogFactoryUtil.getLog(this.getClass());

	@Override
	public void render(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {

		ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		ZoneDisplayContext dc = new ZoneDisplayContext(themeDisplay);
		renderRequest.setAttribute("dc", dc);
		renderRequest.setAttribute("myQuery", dc.getAddress(renderRequest));
		super.render(renderRequest, renderResponse);
	}

	@Reference
	private AdictService adictService;
}