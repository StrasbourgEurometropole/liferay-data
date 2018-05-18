package eu.strasbourg.portlet.graveyard.portlet;

import java.io.IOException;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.util.ParamUtil;

import eu.strasbourg.portlet.graveyard.portlet.context.GraveyardDisplayContext;

/**
 * @author angelique.champougny
 */
@Component(immediate = true, property = { "com.liferay.portlet.display-category=Strasbourg",
		"com.liferay.portlet.instanceable=true", "javax.portlet.display-name=Rechercher un d&eacute;funt",
		"javax.portlet.init-param.template-path=/", "javax.portlet.init-param.view-template=/graveyard-view.jsp",
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user" }, service = Portlet.class)
public class GraveyardWebPortlet extends MVCPortlet {


	@Override
	public void render(RenderRequest request, RenderResponse response) throws IOException, PortletException {
		GraveyardDisplayContext dc = new GraveyardDisplayContext(request, response);
		String rechercher = ParamUtil.getString(request, "rechercher");
		if(rechercher.equals("lancer")){
			dc.recherche(request, response);
		}
		request.setAttribute("dc", dc);
		super.render(request, response);
	}

}