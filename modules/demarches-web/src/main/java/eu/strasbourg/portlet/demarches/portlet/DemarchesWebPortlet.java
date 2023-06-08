package eu.strasbourg.portlet.demarches.portlet;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.SessionParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import eu.strasbourg.portlet.demarches.configuration.DemarchesConfiguration;
import eu.strasbourg.utils.PortletHelper;
import eu.strasbourg.utils.ProcedureHelper;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import eu.strasbourg.utils.exception.NoUserFormException;
import eu.strasbourg.utils.models.Procedure;
import org.osgi.service.component.annotations.Component;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author angelique.champougny
 */
@Component(immediate = true, property = { "com.liferay.portlet.display-category=Strasbourg",
		"com.liferay.portlet.instanceable=false", "com.liferay.portlet.required-namespaced-parameters=false",
		"javax.portlet.display-name=Suivi des demandes", "javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/demarches-view.jsp",
		"javax.portlet.init-param.config-template=/configuration/demarches-configuration.jsp",
		"javax.portlet.name=" + StrasbourgPortletKeys.DEMARCHES_WEB, "javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user" }, service = Portlet.class)
public class DemarchesWebPortlet extends MVCPortlet {

	@Override
	public void render(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		try {
			// récupération de l'utilisateur
			LiferayPortletRequest liferayPortletRequest = PortalUtil.getLiferayPortletRequest(renderRequest);
			HttpServletRequest originalRequest = liferayPortletRequest.getHttpServletRequest();
			String idUser = SessionParamUtil.getString(originalRequest, "publik_internal_id");
			renderRequest.setAttribute("idUser", idUser);

			// récupération des démarches
			List<Procedure> procedures = new ArrayList();
			try {
				procedures = ProcedureHelper.getProcedures(idUser);
			}catch (NoUserFormException e){
				renderRequest.setAttribute("error", "publik");
			}
			renderRequest.setAttribute("demarches", procedures.subList(0,procedures.size()>3?3:procedures.size()));

			// récupère l'url de la configuration
			ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
			DemarchesConfiguration configuration = themeDisplay.getPortletDisplay()
					.getPortletInstanceConfiguration(DemarchesConfiguration.class);
			String url = configuration.url();
			renderRequest.setAttribute("toutesLesDemarches", url);
			
			// titre personnalisable
			renderRequest.setAttribute("title", PortletHelper.getPortletTitle("follow-up-request", renderRequest));

			// Affichage ou non de la croix de masquage du module
			renderRequest.setAttribute("showDeleteButton",
					PortletHelper.showDeleteButtonOnDashboard(themeDisplay, themeDisplay.getPortletDisplay().getId()));

			// Affichage ou non de l'accordéon
			renderRequest.setAttribute("showRetractableButton",
					PortletHelper.showRetractableButtonOnDashboard(themeDisplay, themeDisplay.getPortletDisplay().getId()));

			// Affichage ou non de l'accordéon
			renderRequest.setAttribute("isFolded",
					PortletHelper.isPortletFoldedOnDashboard(themeDisplay, themeDisplay.getPortletDisplay().getId()));

			super.render(renderRequest, renderResponse);
		} catch (Exception e) {
			_log.error(e);
		}
	}

	private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());

}