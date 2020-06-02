package eu.strasbourg.portlet.council.display.context;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.util.GetterUtil;
import eu.strasbourg.service.council.model.CouncilSession;
import eu.strasbourg.service.council.service.CouncilSessionLocalServiceUtil;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import eu.strasbourg.utils.display.context.ViewListBaseDisplayContext;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import java.util.ArrayList;
import java.util.List;

public class ViewCouncilSessionsDisplayContext extends ViewListBaseDisplayContext<CouncilSession> {

    private List<CouncilSession> councilSessions;

    public ViewCouncilSessionsDisplayContext(RenderRequest request, RenderResponse response) {
        super(CouncilSession.class, request, response);
    }

    @SuppressWarnings("unused")
    public List<CouncilSession> getCouncilSessions() throws PortalException {
        if (this.councilSessions == null) {
            Hits hits = getHits(this._themeDisplay.getScopeGroupId());

            List<CouncilSession> results = new ArrayList<>();
            if (hits != null) {
                for (Document document : hits.getDocs()) {
                    CouncilSession councilSession = CouncilSessionLocalServiceUtil.fetchCouncilSession(
                            GetterUtil.getLong(document.get(Field.ENTRY_CLASS_PK)));
                    if (councilSession != null) {
                        results.add(councilSession);
                    }
                }
            }
            this.councilSessions = results;
        }
        return this.councilSessions;
    }


    /**
     * Retourne la liste des sessions correspondant à la recherche lancée en ignorant la pagination
     */
    private List<CouncilSession> getAllCouncilSessions() throws PortalException {
        Hits hits = getAllHits(this._themeDisplay.getCompanyGroupId());

        List<CouncilSession> results = new ArrayList<>();
        if (hits != null) {
            for (Document document : hits.getDocs()) {
                CouncilSession councilSession = CouncilSessionLocalServiceUtil
                        .fetchCouncilSession(GetterUtil.getLong(document.get(Field.ENTRY_CLASS_PK)));
                if (councilSession != null) {
                    results.add(councilSession);
                }
            }
        }
        return results;
    }

    /**
     * Retourne la liste des PK de toutes les sessions
     * @return liste de PK (ex: "1,5,7,8")
     */
    @SuppressWarnings("unused")
    public String getAllCouncilSessionIds() throws PortalException {
        StringBuilder councilSessionsIds = new StringBuilder();
        for (CouncilSession councilSession : this.getAllCouncilSessions()) {
            if (councilSessionsIds.length() > 0) {
                councilSessionsIds.append(",");
            }
            councilSessionsIds.append(councilSession.getCouncilSessionId());
        }
        return councilSessionsIds.toString();
    }

    /**
     * Wrapper autour du permission checker pour les permissions de module
     */
    @SuppressWarnings("unused")
    public boolean hasPermission(String actionId) {
        return _themeDisplay.getPermissionChecker().hasPermission(
                this._themeDisplay.getScopeGroupId(),
                StrasbourgPortletKeys.COUNCIL_BO, StrasbourgPortletKeys.COUNCIL_BO,
                actionId);
    }

}
