package eu.strasbourg.portlet.council;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import org.osgi.service.component.annotations.Component;

import java.io.IOException;

@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=Strasbourg",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=Council",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + StrasbourgPortletKeys.COUNCIL_WEB,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class CouncilPortlet extends MVCPortlet {

	@Override
	public void render(RenderRequest request, RenderResponse response) throws IOException, PortletException {
		super.render(request, response);
	}

}