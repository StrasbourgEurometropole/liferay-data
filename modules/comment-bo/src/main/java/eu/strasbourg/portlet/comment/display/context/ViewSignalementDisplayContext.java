package eu.strasbourg.portlet.comment.display.context;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import eu.strasbourg.service.comment.model.Signalement;
import eu.strasbourg.service.comment.service.SignalementLocalServiceUtil;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import eu.strasbourg.utils.display.context.ViewListBaseDisplayContext;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
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

    public List<Signalement> getSignalements() {
        _log.info("debut getSignalements");
        if (_signalements==null){
            _signalements = SignalementLocalServiceUtil.getByGroupId(this._themeDisplay.getScopeGroupId());
        }

        return _signalements;
    }

    public boolean hasPermission(String actionId) throws PortalException {
        return _themeDisplay.getPermissionChecker().hasPermission(
                this._themeDisplay.getScopeGroupId(),
                StrasbourgPortletKeys.COMMENT_BO,
                StrasbourgPortletKeys.COMMENT_BO,
                actionId);
    }
}
