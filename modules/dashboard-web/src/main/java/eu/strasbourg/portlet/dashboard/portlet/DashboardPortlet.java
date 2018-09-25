package eu.strasbourg.portlet.dashboard.portlet;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.SessionParamUtil;
import com.liferay.portal.kernel.util.Validator;
import eu.strasbourg.service.oidc.model.PublikUser;
import eu.strasbourg.service.oidc.service.PublikUserLocalServiceUtil;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import org.osgi.service.component.annotations.Component;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.PortletRequest;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author alexandre.quere
 */
@Component(
        immediate = true,
        property = {
                "com.liferay.portlet.display-category=category.hidden",
                "com.liferay.portlet.instanceable=true",
                "javax.portlet.init-param.template-path=/",
                "javax.portlet.init-param.view-template=/view.jsp",
                "javax.portlet.name=" + StrasbourgPortletKeys.DASHBOARD_WEB,
                "javax.portlet.resource-bundle=content.Language",
                "javax.portlet.security-role-ref=power-user,user"
        },
        service = Portlet.class
)
public class DashboardPortlet extends MVCPortlet {
    private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());

    @Override
    public void render(RenderRequest renderRequest, RenderResponse renderResponse) throws IOException, PortletException {
        String publicId = getPublikID(renderRequest);
        _log.info("je passe par le render");
        if (Validator.isNotNull(publicId)) {
            PublikUser user = PublikUserLocalServiceUtil.getByPublikUserId(publicId);
            renderRequest.setAttribute("hasUserSigned", Validator.isNotNull(user.getPactSignature()));
            renderRequest.setAttribute("isUserloggedIn", true);
        } else renderRequest.setAttribute("isUserloggedIn", false);

        super.render(renderRequest, renderResponse);
    }

    // Récupération du publik ID avec la session
    private String getPublikID(PortletRequest resourceRequest) {

        LiferayPortletRequest liferayPortletRequest = PortalUtil.getLiferayPortletRequest(resourceRequest);
        HttpServletRequest originalRequest = liferayPortletRequest.getHttpServletRequest();

        return SessionParamUtil.getString(originalRequest, "publik_internal_id");
    }
}