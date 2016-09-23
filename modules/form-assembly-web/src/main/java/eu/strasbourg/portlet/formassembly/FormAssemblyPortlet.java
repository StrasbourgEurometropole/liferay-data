package eu.strasbourg.portlet.formassembly;

import java.io.IOException;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;

import eu.strasbourg.portlet.formassembly.configuration.FormAssemblyConfiguration;

@Component(
	immediate = true,
	configurationPid = "eu.strasbourg.portlet.formassembly.configuration.FormAssemblyConfiguration",
	property = { "com.liferay.portlet.display-category=Strasbourg",
		"com.liferay.portlet.instanceable=false",
		"com.liferay.portlet.css-class-wrapper=formassembly-portlet",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/formassembly-view.jsp",
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user" },
	service = Portlet.class)
public class FormAssemblyPortlet extends MVCPortlet {
	@Override
	public void render(RenderRequest request, RenderResponse response)
		throws PortletException, IOException {
		try {

			ThemeDisplay themeDisplay = (ThemeDisplay) request
				.getAttribute(WebKeys.THEME_DISPLAY);
			FormAssemblyConfiguration configuration = themeDisplay
				.getPortletDisplay().getPortletInstanceConfiguration(
					FormAssemblyConfiguration.class);

			// Formulaire à afficher

			// On récupère l'ID du formulaire
			String formIdString = configuration.formId();
			Integer formId = -1;
			if (!formIdString.equals(""))
				formId = Integer.parseInt(formIdString);

			// Erreur si pas de formulaire sélectionné
			if (formId == -1) {
				SessionErrors.add(request, "no-form");
			} else {
				// On récupère l'html du formulaire (ou les résultats si les données viennent d'être saisies)
				HttpServletRequest servletRequest = PortalUtil
					.getHttpServletRequest(request);
				FormAssemblyUtil formAssemblyUtil = new FormAssemblyUtil(
					configuration.path(), configuration.token());
				HttpServletRequest originalServletRequest = formAssemblyUtil
					.getOriginalServletRequest(servletRequest);
				String formHtml = formAssemblyUtil.getHTML(formId,
					originalServletRequest.getParameter("tfa_next"));
				request.setAttribute("formHtml", formHtml);
			}

			super.render(request, response);

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
}