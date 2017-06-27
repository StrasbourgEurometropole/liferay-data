package eu.strasbourg.portlet.tipi_stats_portlet;

import java.io.IOException;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

/**
 * @author 01i454
 */
@Component(
		immediate = true,
		property = { "com.liferay.portlet.instanceable=false",
			"com.liferay.portlet.footer-portlet-javascript=/js/main.js",
			"com.liferay.portlet.header-portlet-css=/css/main.css",
			"com.liferay.portlet.single-page-application=false",
			"javax.portlet.init-param.template-path=/",
			"javax.portlet.init-param.view-template=/view.jsp",
			"javax.portlet.resource-bundle=content.Language",
			"javax.portlet.security-role-ref=power-user,user" },
		service = Portlet.class)
public class TipiStatsPortlet extends MVCPortlet {

	@Override
	public void render(RenderRequest request, RenderResponse response)
			throws PortletException, IOException {
		super.render(request, response);
	}
}