package eu.strasbourg.portlet.council.display.context;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.util.GetterUtil;
import eu.strasbourg.service.council.constants.StageDeliberation;
import eu.strasbourg.service.council.model.Deliberation;
import eu.strasbourg.service.council.service.DeliberationLocalServiceUtil;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import eu.strasbourg.utils.display.context.ViewListBaseDisplayContext;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import java.util.ArrayList;
import java.util.List;

public class ViewDeliberationsDisplayContext extends ViewListBaseDisplayContext<Deliberation> {

    private List<Deliberation> deliberations;

    public ViewDeliberationsDisplayContext(RenderRequest request, RenderResponse response) {
        super(Deliberation.class, request, response);
    }

    @SuppressWarnings("unused")
    public List<Deliberation> getDeliberations() throws PortalException {
        if (this.deliberations == null) {
            Hits hits = getHits(this._themeDisplay.getScopeGroupId());

            List<Deliberation> results = new ArrayList<>();
            if (hits != null) {
                for (Document document : hits.getDocs()) {
                    Deliberation deliberation = DeliberationLocalServiceUtil.fetchDeliberation(
                            GetterUtil.getLong(document.get(Field.ENTRY_CLASS_PK)));
                    if (deliberation != null) {
                        results.add(deliberation);
                    }
                }
            }
            this.deliberations = results;
        }
        return this.deliberations;
    }


    /**
     * Retourne la liste des délibérations correspondant à la recherche lancée en ignorant la pagination
     */
    private List<Deliberation> getAllDeliberations() throws PortalException {
        Hits hits = getAllHits(this._themeDisplay.getCompanyGroupId());

        List<Deliberation> results = new ArrayList<>();
        if (hits != null) {
            for (Document document : hits.getDocs()) {
                Deliberation deliberation = DeliberationLocalServiceUtil
                        .fetchDeliberation(GetterUtil.getLong(document.get(Field.ENTRY_CLASS_PK)));
                if (deliberation != null) {
                    results.add(deliberation);
                }
            }
        }
        return results;
    }

    /**
     * Retourne la liste des PK de toutes les délibérations
     * @return liste de PK (ex: "1,5,7,8")
     */
    @SuppressWarnings("unused")
    public String getAllDeliberationIds() throws PortalException {
        StringBuilder deliberationsIds = new StringBuilder();
        for (Deliberation deliberation : this.getAllDeliberations()) {
            if (deliberationsIds.length() > 0) {
                deliberationsIds.append(",");
            }
            deliberationsIds.append(deliberation.getDeliberationId());
        }
        return deliberationsIds.toString();
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

    public String getStageDeliberationName(long id) {
        return StageDeliberation.get(id).getName();
    }

    /**
     * Class CSS de la couleur du Statut
     * @param deliberation
     * @return
     */
    public String getCSSClassColor(Deliberation deliberation) {
        String cssClass="";
        if(deliberation.isAdopte()) {
            cssClass="green";
        } else if (deliberation.isRejete()) {
            cssClass="red";
        }

        return cssClass;
    }
}
