package eu.strasbourg.portlet.objtp.portlet;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

import java.io.IOException;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

/**
 * @author jeremy.zwickert
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.instanceable=false",
		"com.liferay.portlet.footer-portlet-javascript=/js/objtp-bo-main.js",
		"com.liferay.portlet.header-portlet-css=/css/objtp-bo-main.css",
		"com.liferay.portlet.single-page-application=false",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/objtp-bo-view.jsp",
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user",
		"javax.portlet.name=" + StrasbourgPortletKeys.OBJTP_BO
	},
	service = Portlet.class
)
public class ObjtpBoPortlet extends MVCPortlet {
	
	@Override
	public void render(RenderRequest renderRequest,
		RenderResponse renderResponse) throws IOException, PortletException {

		super.render(renderRequest, renderResponse);
	}
	
}