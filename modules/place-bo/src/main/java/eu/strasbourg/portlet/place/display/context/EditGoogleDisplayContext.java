package eu.strasbourg.portlet.place.display.context;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.service.WorkflowDefinitionLinkLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import eu.strasbourg.service.place.model.GoogleMyBusinessHistoric;
import eu.strasbourg.service.place.service.GoogleMyBusinessHistoricLocalServiceUtil;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import java.util.Locale;
import java.util.Set;

public class EditGoogleDisplayContext {

    private GoogleMyBusinessHistoric _googleMyBusinessHistoric;

    private final RenderRequest _request;
    private final ThemeDisplay _themeDisplay;

    public EditGoogleDisplayContext(RenderRequest request,
                                                    RenderResponse response) {
        this._request = request;
        this._themeDisplay = (ThemeDisplay) request
                .getAttribute(WebKeys.THEME_DISPLAY);
    }

    public GoogleMyBusinessHistoric getGoogleMyBusinessHistoric() {
        long googleMyBusinessHistoricId = ParamUtil.getLong(_request, "googleMyBusinessHistoricId");
        if (_googleMyBusinessHistoric == null && googleMyBusinessHistoricId > 0) {
            _googleMyBusinessHistoric = GoogleMyBusinessHistoricLocalServiceUtil.fetchGoogleMyBusinessHistoric(googleMyBusinessHistoricId);
        }
        return _googleMyBusinessHistoric;
    }

    /**
     * @return True si le framework workflow est actif pour ce type d'entité
     */
    public boolean isWorkflowEnabled() {
        return WorkflowDefinitionLinkLocalServiceUtil.hasWorkflowDefinitionLink(
                _themeDisplay.getCompanyId(), _themeDisplay.getCompanyGroupId(),
                GoogleMyBusinessHistoric.class.getName());
    }

    public Locale[] getAvailableLocales() {
        Set<Locale> availableLocalesSet = LanguageUtil.getSupportedLocales();
        Locale[] availableLocales = availableLocalesSet
                .toArray(new Locale[availableLocalesSet.size()]);
        return availableLocales;
    }

    /**
     * Wrapper autour du permission checker pour les permissions de module
     */
    public boolean hasPermission(String actionId) throws PortalException {
        return _themeDisplay.getPermissionChecker().hasPermission(
                this._themeDisplay.getCompanyGroupId(),
                StrasbourgPortletKeys.PLACE_BO, StrasbourgPortletKeys.PLACE_BO,
                actionId);
    }
}
