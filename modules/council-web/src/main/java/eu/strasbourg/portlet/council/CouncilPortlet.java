package eu.strasbourg.portlet.council;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import javax.portlet.*;

import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;
import eu.strasbourg.portlet.council.display.context.CouncilDisplayContext;
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
		"javax.portlet.init-param.view-template=/dynamic-view.jsp",
		"javax.portlet.name=" + StrasbourgPortletKeys.COUNCIL_WEB,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class CouncilPortlet extends MVCPortlet {

	@Override
	public void render(RenderRequest request, RenderResponse response) throws IOException, PortletException {

		// Récupération du contexte de la requete
		ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
		PortletPreferences preferences = request.getPreferences();

		// Création du display context
		CouncilDisplayContext dc = new CouncilDisplayContext(themeDisplay, preferences);

		// Attribution des variables à fournir à la vue
		request.setAttribute("dc", dc);



		super.render(request, response);
	}

}