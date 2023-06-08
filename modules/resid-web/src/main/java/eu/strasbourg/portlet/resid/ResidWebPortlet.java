package eu.strasbourg.portlet.resid;

import java.io.IOException;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import eu.strasbourg.portlet.resid.dossier.DossiersResponse;
import eu.strasbourg.portlet.resid.dossier.DossiersWebService;
import eu.strasbourg.utils.PortletHelper;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

/**
 * @author angelique.champougny
 */
@Component(immediate = true, property = { "com.liferay.portlet.display-category=Strasbourg",
		"com.liferay.portlet.instanceable=true", "com.liferay.portlet.required-namespaced-parameters=false",
		"javax.portlet.init-param.template-path=/", "javax.portlet.init-param.view-template=/resid-view.jsp",
		"com.liferay.portlet.render-weight=0",
		"javax.portlet.init-param.config-template=/configuration/resid-configuration.jsp", "javax.portlet.display-name=Stationnement residant",
		"javax.portlet.name=" + StrasbourgPortletKeys.RESID_WEB, "javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user" }, service = Portlet.class)
public class ResidWebPortlet extends MVCPortlet {

	@Override
	public void render(RenderRequest request, RenderResponse response) throws IOException, PortletException {

		ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
		ResidDisplayContext dc = new ResidDisplayContext(themeDisplay);
		String publikInternalId = dc.getPublikID(request);
		String template = "";

		if(dc.isUnderMaintenance()) {
			template = "etape0";
		}else {
			DossiersResponse dossierResponse = DossiersWebService.getResponse(publikInternalId);

			// si l'utilisateur a activé son lien
			if (Validator.isNull(dossierResponse)) {
				template = "etape0";
			} else {
				dc.setDossierResponse(dossierResponse);
				if (dossierResponse.dossiers.isEmpty()) {
					template = "etape1";
				} else {
					template = "etape2";
					if (dossierResponse.getCodeRetour() != 0) {
						request.setAttribute("error", dossierResponse.getErreurDescription());
					}
				}
			}
		}
		request.setAttribute("dc", dc);
		
		// titre personnalisable
		request.setAttribute("title", PortletHelper.getPortletTitle("account-resid", request));

		include("/templates/" + template + ".jsp", request, response);
	}
}