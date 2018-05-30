package eu.strasbourg.portlet.validation.portlet;

import java.io.IOException;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;

import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.SessionParamUtil;
import com.liferay.portal.kernel.util.WebKeys;

import eu.strasbourg.portlet.validation.ValidationResponse;
import eu.strasbourg.portlet.validation.ValidationWebService;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

/**
 * @author angelique.champougny
 */
@Component(immediate = true, property = { "com.liferay.portlet.display-category=Strasbourg",
		"com.liferay.portlet.instanceable=true", "com.liferay.portlet.required-namespaced-parameters=false",
		"javax.portlet.display-name=validation mediatheque", "javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/validation-view.jsp",
		"javax.portlet.name=" + StrasbourgPortletKeys.VALIDATION_WEB, "javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user" }, service = Portlet.class)
public class ValidationWebPortlet extends MVCPortlet {
	private ThemeDisplay themeDisplay ;

	@Override
	public void render(RenderRequest renderRequest, RenderResponse response) throws IOException, PortletException {
		this.themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);

		// Permet de remonter la hiérarchie des Request
		HttpServletRequest request = PortalUtil
				.getOriginalServletRequest(PortalUtil.getHttpServletRequest(renderRequest));

		String sub = ParamUtil.getString(request, "sub");
		String token = ParamUtil.getString(request, "token");

		ValidationResponse validation = ValidationWebService.getResponse(sub, token);
		
		// On vérifie si l'utilisateur est connectée
		boolean isLoggedIn = SessionParamUtil.getBoolean(request, "publik_logged_in");
		request.setAttribute("isLoggedIn", isLoggedIn);

		if (validation.getResult().equals("OK")) {
			request.setAttribute("liaison", "ok");
		} else {
			switch (validation.getCode_erreur()) {
			case "DEJA_VALIDEE":
				// L'association a déjà été validée
				request.setAttribute("liaison", "already-done");
				break;
			case "DELAI_DEPASSE":
				// le compte n'a pas été activé dans le temps imparti
				request.setAttribute("liaison", "time-excedeed");
				break;
			default:
				// erreur technique -> TECHNIQUE
				// Aucune association trouvée -> AUCUNE_ASSOCIATION
				request.setAttribute("liaison", "ko");
				request.setAttribute("error", validation.getErreur());
				break;
			}
		}
		
		super.render(renderRequest, response);
	}

	public String getVirtualHostName() {
		Group group = GroupLocalServiceUtil.fetchFriendlyURLGroup(this.themeDisplay.getCompanyId(), "/strasbourg.eu");
		return group.getPublicLayoutSet().getVirtualHostname();
	}

}