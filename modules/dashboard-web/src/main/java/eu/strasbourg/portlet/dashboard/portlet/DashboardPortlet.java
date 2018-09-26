package eu.strasbourg.portlet.dashboard.portlet;

import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.SessionParamUtil;
import com.liferay.portal.kernel.util.Validator;
import eu.strasbourg.service.agenda.model.EventParticipation;
import eu.strasbourg.service.agenda.service.EventParticipationLocalServiceUtil;
import eu.strasbourg.service.oidc.model.PublikUser;
import eu.strasbourg.service.oidc.service.PublikUserLocalServiceUtil;
import eu.strasbourg.service.project.model.Initiative;
import eu.strasbourg.service.project.model.Petition;
import eu.strasbourg.service.project.model.ProjectFollowed;
import eu.strasbourg.service.project.service.InitiativeLocalServiceUtil;
import eu.strasbourg.service.project.service.PetitionLocalServiceUtil;
import eu.strasbourg.service.project.service.ProjectFollowedServiceUtil;
import eu.strasbourg.utils.PublikApiClient;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import org.osgi.service.component.annotations.Component;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.PortletRequest;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

/**
 * @author alexandre.quere
 */
@Component(
        immediate = true,
        property = {
                "com.liferay.portlet.display-category=Strasbourg",
                "com.liferay.portlet.instanceable=true",
                "javax.portlet.display-name=Tableau de bord",
                "javax.portlet.init-param.add-process-action-success-action=false",
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

        if (Validator.isNotNull(publicId)) {
            PublikUser user = PublikUserLocalServiceUtil.getByPublikUserId(publicId);
            JSONObject userConnected = PublikApiClient.getUserDetails(publicId);
            renderRequest.setAttribute("hasUserSigned", Validator.isNotNull(user.getPactSignature()));
            renderRequest.setAttribute("isUserloggedIn", true);
            renderRequest.setAttribute("userConnected",userConnected);
        } else renderRequest.setAttribute("isUserloggedIn", false);

        List<Petition> petitionFiledList = PetitionLocalServiceUtil.getPetitionByPublikUserID(publicId);
        List<Petition> petitionSignedList = PetitionLocalServiceUtil.getPetitionBySignatairePublikId(publicId);
        List<ProjectFollowed> projectFolloweds = ProjectFollowedServiceUtil.findProjectFollowedByPublikUserId(publicId);
        List<EventParticipation> eventParticipations = EventParticipationLocalServiceUtil.getByPublikUser(publicId);
        List<Initiative>initiativeList = InitiativeLocalServiceUtil.findByPublikUserId(publicId);

        renderRequest.setAttribute("petitionFiledCount",petitionFiledList.size());
        renderRequest.setAttribute("petitionSignedCount",petitionSignedList.size());
        renderRequest.setAttribute("projectFollowedsCount",projectFolloweds.size());
        renderRequest.setAttribute("eventParticipationsCount",eventParticipations.size());

        super.render(renderRequest, renderResponse);
    }

    // Récupération du publik ID avec la session
    private String getPublikID(PortletRequest resourceRequest) {

        LiferayPortletRequest liferayPortletRequest = PortalUtil.getLiferayPortletRequest(resourceRequest);
        HttpServletRequest originalRequest = liferayPortletRequest.getHttpServletRequest();

        return SessionParamUtil.getString(originalRequest, "publik_internal_id");
    }
}