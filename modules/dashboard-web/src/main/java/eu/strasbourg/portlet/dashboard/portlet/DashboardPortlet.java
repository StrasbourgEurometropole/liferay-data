package eu.strasbourg.portlet.dashboard.portlet;

import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.util.Validator;
import eu.strasbourg.portlet.dashboard.utils.DashBoardUtils;
import eu.strasbourg.service.agenda.model.Event;
import eu.strasbourg.service.agenda.service.EventLocalServiceUtil;
import eu.strasbourg.service.oidc.model.PublikUser;
import eu.strasbourg.service.oidc.service.PublikUserLocalServiceUtil;
import eu.strasbourg.service.project.model.Initiative;
import eu.strasbourg.service.project.model.Petition;
import eu.strasbourg.service.project.model.Project;
import eu.strasbourg.service.project.service.InitiativeLocalServiceUtil;
import eu.strasbourg.service.project.service.PetitionLocalServiceUtil;
import eu.strasbourg.service.project.service.ProjectLocalServiceUtil;
import eu.strasbourg.utils.PublikApiClient;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import org.osgi.service.component.annotations.Component;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import java.io.IOException;
import java.util.ArrayList;
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

    /**
     * le log
     */
    private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());
    private static final String SHARED_ASSET_ID = "LIFERAY_SHARED_assetEntryID";
    private static final String CITY_NAME = "Strasbourg";
    public static final String REDIRECT_URL_PARAM = "redirectURL";

    @Override
    public void render(RenderRequest renderRequest, RenderResponse renderResponse) throws IOException, PortletException {
        String publicId = DashBoardUtils.getPublikID(renderRequest);

        if (Validator.isNotNull(publicId)) {
            PublikUser user = PublikUserLocalServiceUtil.getByPublikUserId(publicId);
            JSONObject userConnected = PublikApiClient.getUserDetails(publicId);
            renderRequest.setAttribute("hasUserSigned", Validator.isNotNull(user.getPactSignature()));
            renderRequest.setAttribute("isUserloggedIn", true);
            renderRequest.setAttribute("userConnected",userConnected);
        } else renderRequest.setAttribute("isUserloggedIn", false);

        List<Petition> petitionFiledList = PetitionLocalServiceUtil.getPetitionByPublikUserID(publicId);
        List<Petition> petitionSignedList = PetitionLocalServiceUtil.getPetitionBySignatairePublikId(publicId);
        List<Project> projectFolloweds = ProjectLocalServiceUtil.findProjectFollowedByProjectId(publicId);
        List<Event> events = EventLocalServiceUtil.findEventByUserPublikId(publicId);
        List<Initiative>initiativesFiled = InitiativeLocalServiceUtil.findByPublikUserId(publicId);
        List<Initiative> initiativesAides=new ArrayList<>();
        //TODO Mock des budgets participatif à implémenter
        List<Project> budgetFiled = new ArrayList<>();
        List<Project> budgetVoted = new ArrayList<>();
        List<Project> voteLeft = new ArrayList<>();

        renderRequest.setAttribute("petitionsFiledCount",petitionFiledList.size());
        renderRequest.setAttribute("petitionSignedCount",petitionSignedList.size());
        renderRequest.setAttribute("projectFollowedsCount",projectFolloweds.size());
        renderRequest.setAttribute("initiativeFiledCount",initiativesFiled.size());
        renderRequest.setAttribute("initiativeAidesCount",initiativesAides.size());
        renderRequest.setAttribute("budgetFiledCount",budgetFiled.size());
        renderRequest.setAttribute("budgetVotedCount",budgetVoted.size());
        renderRequest.setAttribute("voteLeft",voteLeft.size());
        renderRequest.setAttribute("eventCount",events.size());
        renderRequest.setAttribute("petitionsFiled",petitionFiledList);
        renderRequest.setAttribute("petitionSigned",petitionSignedList);
        renderRequest.setAttribute("projectFolloweds",projectFolloweds);
        renderRequest.setAttribute("event",events);

        super.render(renderRequest, renderResponse);
    }

}