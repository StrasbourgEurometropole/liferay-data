package eu.strasbourg.portlet.tipi_portlet;

import java.io.IOException;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.PortletPreferences;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import eu.strasbourg.utils.StrasbourgPropsUtil;

/**
 * @author 01i454
 */
@Component(immediate = true, property = {
		"com.liferay.portlet.display-category=Strasbourg",
		"com.liferay.portlet.instanceable=false",
		"javax.portlet.display-name=Tipi Portlet",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.init-param.config-template=/config/config.jsp",
		"com.liferay.portlet.footer-portlet-javascript=/js/main.js",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user" }, service = Portlet.class)
public class TipiPortlet extends MVCPortlet {

	@Override
	public void render(RenderRequest request, RenderResponse response)
			throws PortletException, IOException {
		// On récupère les préférences afin de les renvoyer existantes à la JSP
		PortletPreferences prefs = request.getPreferences();

		// Type de formulaire
		String form = prefs.getValue("form", "");
		request.setAttribute("form", form);

		String appCode = "";
		String clientNumber = "";
		String formTitle = "";
		if (form != null && form.length() > 0) {
			if (form.equals("childhood")) {
				appCode = "PF";
				clientNumber = "000696";
				formTitle = "Facturation petite enfance";
			} else if (form.equals("schoolRestaurant")) {
				appCode = "RS";
				clientNumber = "000696";
				formTitle = "Facturation restauration scolaire";
			} else if (form.equals("afterSchool")) {
				appCode = "GA";
				clientNumber = "000696";
				formTitle = "Facturation services p&eacute;riscolaires (APM-ALM)";
			} else if (form.equals("water")) {
				appCode = "EA";
				clientNumber = "007964";
				formTitle = "Facturation eau et assainissement";
			}
		}
		request.setAttribute("appCode", appCode);
		request.setAttribute("clientNumber", clientNumber);
		request.setAttribute("formTitle", formTitle);

		// On récupère l'URL de paiement dans config.properties
		String billingURL = "";
		String callbackURL = "";
		billingURL = "http://www.jepaiemesserviceslocaux.dgfip.finances.gouv.fr/tpa/paiement.web?saisie=T&";
		callbackURL = "https://webhook.site/42ef84dd-89fd-4cb8-af24-a057e88cdecb";
				//callbackURL = "http://ldkimmwffo.localtunnel.me/web/tipi/accueil";
		request.setAttribute("billingURL", billingURL);
		request.setAttribute("callbackURL", callbackURL);

		super.render(request, response);
	}
}