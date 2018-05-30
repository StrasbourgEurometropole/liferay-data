package eu.strasbourg.porlet.comment;

import java.io.IOException;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.PortletSession;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

/**
 * @author romain.vergnais
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=Strasbourg",
		"com.liferay.portlet.instanceable=false",
		"javax.portlet.display-name=Commentaires",
		"javax.portlet.init-param.add-process-action-success-action=false",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/comments-view.jsp",
		"javax.portlet.name=" + StrasbourgPortletKeys.COMMENT_WEB,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user",
		"javax.portlet.supported-public-render-parameter=message"
	},
	service = Portlet.class
)
public class CommentPortlet extends MVCPortlet {
	
	@Override
	public void render(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		
		long entryID = 0;
		PortletSession portletSession = renderRequest.getPortletSession(); 
		
		if(portletSession.getAttribute("LIFERAY_SHARED_assetEntryID", PortletSession.APPLICATION_SCOPE) != null)
			entryID = (long)portletSession.getAttribute("LIFERAY_SHARED_assetEntryID", PortletSession.APPLICATION_SCOPE);
		
		
		super.render(renderRequest, renderResponse);
	}
	
}