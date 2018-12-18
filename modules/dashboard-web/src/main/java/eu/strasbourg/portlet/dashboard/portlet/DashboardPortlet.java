package eu.strasbourg.portlet.dashboard.portlet;

import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import eu.strasbourg.portlet.dashboard.utils.DashBoardUtils;
import eu.strasbourg.service.agenda.model.Event;
import eu.strasbourg.service.agenda.service.EventLocalServiceUtil;
import eu.strasbourg.service.oidc.model.PublikUser;
import eu.strasbourg.service.oidc.service.PublikUserLocalServiceUtil;
import eu.strasbourg.service.project.model.BudgetParticipatif;
import eu.strasbourg.service.project.model.BudgetPhase;
import eu.strasbourg.service.project.model.Initiative;
import eu.strasbourg.service.project.model.Petition;
import eu.strasbourg.service.project.model.Project;
import eu.strasbourg.service.project.service.BudgetParticipatifLocalServiceUtil;
import eu.strasbourg.service.project.service.BudgetPhaseLocalServiceUtil;
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
    public void render(RenderRequest request, RenderResponse response) throws IOException, PortletException {
    	
    	// Recuperation du contexte de la requete
    	ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
    	long groupId = new Long(themeDisplay.getLayout().getGroupId());
    	
        String publikId = DashBoardUtils.getPublikID(request);

        if (Validator.isNotNull(publikId)) {
            PublikUser user = PublikUserLocalServiceUtil.getByPublikUserId(publikId);
            JSONObject userConnected = PublikApiClient.getUserDetails(publikId);
            request.setAttribute("hasUserSigned", Validator.isNotNull(user.getPactSignature()));
            request.setAttribute("isUserloggedIn", true);
            request.setAttribute("userConnected",userConnected);
        } else request.setAttribute("isUserloggedIn", false);

        /**
         *  Petitions
         */
        List<Petition> petitionFiledList = PetitionLocalServiceUtil.getPetitionByPublikUserID(publikId);
        List<Petition> petitionSignedList = PetitionLocalServiceUtil.getPetitionBySignatairePublikId(publikId);
        
        request.setAttribute("petitionsFiledCount",petitionFiledList.size());
        request.setAttribute("petitionSignedCount",petitionSignedList.size());
        request.setAttribute("petitionsFiled",petitionFiledList);
        request.setAttribute("petitionSigned",petitionSignedList);
        
        /**
         * Projets
         */
        List<Project> projectFolloweds = ProjectLocalServiceUtil.findProjectFollowedByProjectId(publikId);
        
        request.setAttribute("projectFollowedsCount",projectFolloweds.size());
        request.setAttribute("projectFolloweds",projectFolloweds);
        
        /**
         * Evenements
         */
        List<Event> events = EventLocalServiceUtil.findEventByUserPublikId(publikId);
        
        request.setAttribute("eventCount",events.size());
        request.setAttribute("event",events);
        
        /**
         * Initiatives : à implémenter
         */
        List<Initiative> initiativesFiled = InitiativeLocalServiceUtil.findByPublikUserId(publikId);
        List<Initiative> initiativesAides = new ArrayList<>();
        
        request.setAttribute("initiativeFiledsCount",initiativesFiled.size());
        request.setAttribute("initiativeFileds",initiativesFiled);
        request.setAttribute("initiativeAidesCount",initiativesAides.size());
        request.setAttribute("initiativeAides",initiativesAides);
        
        /**
         * Budget participatif
         */
        List<BudgetParticipatif> budgetFiled = new ArrayList<>();
        List<BudgetParticipatif> budgetVoted = new ArrayList<>();
        int voteLeft = 0;
        
        BudgetPhase activePhase  = BudgetPhaseLocalServiceUtil.getActivePhase(groupId);
        
        if (activePhase != null) {
        	budgetFiled = BudgetParticipatifLocalServiceUtil.getBudgetParticipatifByPublikUserID(publikId);
	        budgetVoted = BudgetParticipatifLocalServiceUtil.getPublishedAndVotedByPublikUserInPhase(publikId, activePhase.getBudgetPhaseId());
	        voteLeft = 5 - BudgetParticipatifLocalServiceUtil.countBudgetSupportedByPublikUserInPhase(publikId, activePhase.getBudgetPhaseId());
        }
        
        request.setAttribute("budgetFiled", budgetFiled);
        request.setAttribute("budgetVoted", budgetVoted);
        request.setAttribute("voteLeft", voteLeft);

        super.render(request, response);
    }

}