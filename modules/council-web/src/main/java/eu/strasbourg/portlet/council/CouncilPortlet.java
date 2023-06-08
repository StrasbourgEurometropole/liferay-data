package eu.strasbourg.portlet.council;

import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import javax.portlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.SessionParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import eu.strasbourg.portlet.council.display.context.CouncilDisplayContext;
import eu.strasbourg.service.council.model.CouncilSession;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import org.osgi.service.component.annotations.Component;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Component(
        immediate = true,
        property = {
                "com.liferay.portlet.display-category=Strasbourg",
                "com.liferay.portlet.header-portlet-css=/css/main.css",
                "com.liferay.portlet.instanceable=true",
                "javax.portlet.display-name=Council",
                "javax.portlet.init-param.template-path=/",
                "javax.portlet.init-param.view-template=/council-dynamic-view.jsp",
                "javax.portlet.name=" + StrasbourgPortletKeys.COUNCIL_WEB,
                "javax.portlet.resource-bundle=content.Language",
                "javax.portlet.security-role-ref=power-user,user"
        },
        service = Portlet.class
)
public class CouncilPortlet extends MVCPortlet {

    @Override
    public void render(RenderRequest request, RenderResponse response) throws IOException, PortletException {

        boolean isCouncilPresentInSession = false;
        HttpSession session = getSession(request);

        // Si on passe un paramètre de retour, alors on veut supprimer le conseil présent en session
        String returnParam = ParamUtil.getString(request, "returnParam");
        if (returnParam.equals("true")) {
            session.removeAttribute("councilSessionId");
        }

        // Si un councilSessionId est passé en paramètre de la requête, alors on l'enregistre en session
        String councilId = ParamUtil.getString(request, "councilSessionId");
        if (!councilId.isEmpty()) {
            session.setAttribute("councilSessionId", councilId);
            isCouncilPresentInSession = true;
        }

        // Récupération du contexte de la requete
        PortletPreferences preferences = request.getPreferences();

        // Création du display context
        CouncilDisplayContext dc = new CouncilDisplayContext(preferences, request);

        // Attribution des variables à fournir à la vue
        request.setAttribute("dc", dc);

        boolean isAuthorized = dc.isConfirmedCouncilUser();
        if (isAuthorized) {

            List<CouncilSession> councilSessions = dc.getCouncilSessions();

            if (councilSessions.size() == 1) {
                CouncilSession councilSession = councilSessions.get(0);
                councilId = String.valueOf(councilSession.getCouncilSessionId());
                session.setAttribute("councilSessionId", councilId);
                isCouncilPresentInSession = true;
            }

            if (isCouncilPresentInSession) {
                String finalCouncilId = councilId;
                Optional<CouncilSession> optionalCouncilSession = councilSessions.stream().filter(c -> String.valueOf(c.getCouncilSessionId()).equals(finalCouncilId)).findFirst();

                if (optionalCouncilSession.isPresent()) {

                    request.setAttribute("councilSessionId", councilId);
                    request.setAttribute(getMVCPathAttributeName(response.getNamespace()), "/council-dynamic-view.jsp");
                }
            } else if (councilSessions.size() > 1){
                request.setAttribute(getMVCPathAttributeName(response.getNamespace()), "/councils-view.jsp");
            } else {
                request.setAttribute(getMVCPathAttributeName(response.getNamespace()), "/council-dynamic-view.jsp");
            }
        }

        super.render(request, response);
    }

    /**
     * Permet de récupérer la session à partir de la renderRequest
     */
    private HttpSession getSession(RenderRequest request) {
        HttpServletRequest originalRequest = PortalUtil.getHttpServletRequest(request);
        return originalRequest.getSession();
    }

}