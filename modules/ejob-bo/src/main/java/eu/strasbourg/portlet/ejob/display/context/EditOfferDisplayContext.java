package eu.strasbourg.portlet.ejob.display.context;

import com.liferay.portal.kernel.service.WorkflowDefinitionLinkLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import eu.strasbourg.service.ejob.model.Offer;
import eu.strasbourg.service.ejob.service.OfferLocalServiceUtil;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

import javax.portlet.RenderRequest;
import java.util.ArrayList;
import java.util.List;

public class EditOfferDisplayContext {

    private Offer offer;
    private final RenderRequest request;
    private final ThemeDisplay themeDisplay;

    public EditOfferDisplayContext(RenderRequest request) {
        this.request = request;
        this.themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
    }

    public Offer getOffer() {
        long offerId = ParamUtil.getLong(this.request, "offerId");
        if (this.offer == null && offerId > 0) {
            this.offer = OfferLocalServiceUtil.fetchOffer(offerId);
        }
        return offer;
    }

    @SuppressWarnings("unused")
    public long getGroupId() {
        return this.themeDisplay.getScopeGroupId();
    }

    /**
     * @return True si le framework workflow est actif pour ce type d'entité
     */
    @SuppressWarnings("unused")
    public boolean isWorkflowEnabled() {
        return WorkflowDefinitionLinkLocalServiceUtil.hasWorkflowDefinitionLink(
                this.themeDisplay.getCompanyId(), this.themeDisplay.getCompanyGroupId(),
                Offer.class.getName());
    }

    /**
     * Wrapper autour du permission checker pour les permissions de module
     */
    @SuppressWarnings("unused")
    public boolean hasPermission(String actionId) {
        return this.themeDisplay.getPermissionChecker().hasPermission(
                this.themeDisplay.getScopeGroupId(), StrasbourgPortletKeys.COUNCIL_BO,
                StrasbourgPortletKeys.COUNCIL_BO, actionId);
    }

}
