package eu.strasbourg.portlet.council.display.context;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.util.GetterUtil;
import eu.strasbourg.service.council.model.Official;
import eu.strasbourg.service.council.service.OfficialLocalServiceUtil;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import eu.strasbourg.utils.display.context.ViewListBaseDisplayContext;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import java.util.ArrayList;
import java.util.List;

public class ViewOfficialsDisplayContext extends ViewListBaseDisplayContext<Official> {

    private List<Official> officials;

    public ViewOfficialsDisplayContext(RenderRequest request, RenderResponse response) {
        super(Official.class, request, response);
    }

    @SuppressWarnings("unused")
    public List<Official> getOfficials() throws PortalException {
        if (this.officials == null) {
            Hits hits = getHits(this._themeDisplay.getScopeGroupId());

            List<Official> results = new ArrayList<>();
            if (hits != null) {
                for (Document document : hits.getDocs()) {
                    Official official = OfficialLocalServiceUtil.fetchOfficial(
                            GetterUtil.getLong(document.get(Field.ENTRY_CLASS_PK)));
                    if (official != null)
                        results.add(official);
                }
            }
            this.officials = results;
        }
        return this.officials;
    }

    /**
     * Retourne la liste des élus correspondant à la recherche lancée en ignorant la pagination
     */
    private List<Official> getAllOfficials() throws PortalException {
        Hits hits = getAllHits(this._themeDisplay.getCompanyGroupId());

        List<Official> results = new ArrayList<>();
        if (hits != null) {
            for (Document document : hits.getDocs()) {
                Official official = OfficialLocalServiceUtil.fetchOfficial(
                        GetterUtil.getLong(document.get(Field.ENTRY_CLASS_PK)));
                if (official != null)
                    results.add(official);
            }
        }
        return results;
    }

    /**
     * Surcharge le mappage des champs sur lesquelles trier
     */
    @Override
    public String getOrderByColSearchField() {
        switch (this.getOrderByCol()) {
            case "full-name":
                return "localized_title_fr_FR_sortable";
            default:
                return "modified_sortable";
        }
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
