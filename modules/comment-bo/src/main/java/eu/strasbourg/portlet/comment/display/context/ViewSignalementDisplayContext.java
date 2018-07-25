package eu.strasbourg.portlet.comment.display.context;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.util.GetterUtil;
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
        _log.info("debut getSignalements");
        if (_signalements==null){
            Hits hits= getHits(this._themeDisplay.getScopeGroupId());
            this._signalements=populateSignalements(hits);
        }

        return _signalements;
    }
    private List<Signalement> populateSignalements(Hits hits) {
        List<Signalement> results = new ArrayList<>();
        if (hits != null) {
            for (Document document :
                    hits.getDocs()) {
                Signalement signalement = SignalementLocalServiceUtil.fetchSignalement(GetterUtil.getLong(document.get(Field.ENTRY_CLASS_PK)));
                if (signalement != null) {

                    results.add(signalement);
                }
            }
        }
        return results;
    }

    public boolean hasPermission(String actionId) throws PortalException {
        return _themeDisplay.getPermissionChecker().hasPermission(
                this._themeDisplay.getScopeGroupId(),
                StrasbourgPortletKeys.COMMENT_BO,
                StrasbourgPortletKeys.COMMENT_BO,
                actionId);
    }
}
