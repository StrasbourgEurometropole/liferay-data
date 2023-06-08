package eu.strasbourg.portlet.council.display.context;

import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.*;
import java.util.stream.Collectors;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import eu.strasbourg.portlet.council.utils.UserRoleType;
import eu.strasbourg.service.council.model.CouncilSession;
import eu.strasbourg.service.council.model.Official;
import eu.strasbourg.service.council.service.CouncilSessionLocalServiceUtil;
import org.apache.commons.lang3.StringUtils;

public class ViewOfficialsConnectionDisplayContext {

    /**
     * Contexte de la requête
     */
    private RenderRequest request;
    private RenderResponse response;
    private ThemeDisplay themeDisplay;
    private CouncilSession currentCouncilSession;
    private List<Long> typeCouncilIds;

    public ViewOfficialsConnectionDisplayContext(RenderRequest request, RenderResponse response) {
        this.request = request;
        this.response = response;
        this.themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        this.currentCouncilSession = null;
        typeCouncilIds = new ArrayList<>();
        initAuthorizedTypeCouncilsIds();
    }

    private void initAuthorizedTypeCouncilsIds() {
        if(typeCouncilIds.size() == 0) {
            typeCouncilIds = UserRoleType.typeIdsForUser(themeDisplay);
        }
    }

    /**
     * Renvoi la liste des conseils du jour
     */
    @SuppressWarnings("unused")
    public List<CouncilSession> getCouncilSessions() {

        // Calcul de la date
        GregorianCalendar gc = CouncilSessionLocalServiceUtil.calculDateForFindCouncil();
        List<CouncilSession> todayCouncils = CouncilSessionLocalServiceUtil.findByDate(gc.getTime());

        List<CouncilSession> councilSessionList = todayCouncils.stream()
                .filter(council -> typeCouncilIds.stream()
                        .anyMatch(type -> type.equals(council.getTypeCouncil().getTypeId())))
                .collect(Collectors.toList());

        return councilSessionList;
    }

    @SuppressWarnings("unused")
    public long getGroupId() {
        return this.themeDisplay.getScopeGroupId();
    }
}
