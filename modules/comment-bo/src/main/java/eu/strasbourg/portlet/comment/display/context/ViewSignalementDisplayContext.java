package eu.strasbourg.portlet.comment.display.context;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import eu.strasbourg.service.comment.model.Signalement;
import eu.strasbourg.service.comment.service.SignalementLocalServiceUtil;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import eu.strasbourg.utils.display.context.ViewListBaseDisplayContext;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * @author alexandre.quere
 */
public class ViewSignalementDisplayContext extends ViewListBaseDisplayContext<Signalement> {

    private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());

    private List<Signalement> _signalements;

    public ViewSignalementDisplayContext(RenderRequest request, RenderResponse response) {
        super(Signalement.class, request, response);
    }

    public List<Signalement> getSignalements() throws PortalException {
        if (_signalements==null){
            Hits hits= getHits(this._themeDisplay.getScopeGroupId());
            this._signalements=populateSignalements(hits);
        }

        return _signalements;
    }

    private List<Signalement> getAllSignalement() throws PortalException{
        Hits hits = getAllHits(this._themeDisplay.getCompanyGroupId());
        return populateSignalements(hits);
    }

    @Override
    public String getOrderByCol() {
        return ParamUtil.getString(this._request, "orderByCol",
                "reporting-date");
    }

    /**
     * Méthode permettant la création de liste d'objets.
     * @param hits les hits d'elastic search
     * @return la liste d'objet.
     */
    private List<Signalement> populateSignalements(Hits hits) {
        List<Signalement> results = new ArrayList<>();
        if (hits != null) {
            for (Document document :
                    hits.getDocs()) {
                long id = GetterUtil.getLong(document.get(Field.ENTRY_CLASS_PK));
                Signalement signalement = SignalementLocalServiceUtil.fetchSignalement(id);
                if (signalement != null) {

                    results.add(signalement);
                }
            }
        }
        return results;
    }

    @Override
    public String getOrderByColSearchField() {
        String param = this.getOrderByCol();
        String result;
        switch (param) {
            case "userName":
                result="userName_String_sortable";
                break;
            case "reporting-date":
                result= "modified_sortable";
                break;
            case "reportType":
                result= "reportType_String_sortable";
                break;
            default:
                result= super.getOrderByColSearchField();
                break;
        }
        return result;
    }

    public boolean hasPermission(String actionId) {
        return _themeDisplay.getPermissionChecker().hasPermission(
                this._themeDisplay.getScopeGroupId(),
                StrasbourgPortletKeys.COMMENT_BO,
                StrasbourgPortletKeys.COMMENT_BO,
                actionId);
    }
}
