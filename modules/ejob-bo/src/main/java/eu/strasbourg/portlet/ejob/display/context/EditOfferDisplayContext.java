package eu.strasbourg.portlet.ejob.display.context;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.portal.kernel.service.WorkflowDefinitionLinkLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import eu.strasbourg.service.ejob.model.Offer;
import eu.strasbourg.service.ejob.service.OfferLocalServiceUtil;
import eu.strasbourg.utils.AssetVocabularyAccessor;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

import javax.portlet.RenderRequest;
import java.util.ArrayList;
import java.util.List;

public class EditOfferDisplayContext {

    private Offer offer;
    private List<AssetCategory> directions;
    private List<AssetCategory> filieres;
    private List<AssetCategory> filieresCategories;
    private final RenderRequest request;
    private final ThemeDisplay themeDisplay;

    public EditOfferDisplayContext(RenderRequest request) {
        this.request = request;
        this.themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
    }

    public Offer getOffer() {
        if (this.offer == null) {
            long offerId = ParamUtil.getLong(this.request, "offerId");
            if (offerId > 0) {
                this.offer = OfferLocalServiceUtil.fetchOffer(offerId);
            }
        }
        return offer;
    }

    @SuppressWarnings("unused")
    public long getGroupId() {
        return this.themeDisplay.getScopeGroupId();
    }


    /**
     * Renvoie les catégories Id de l'offre
     */
    @SuppressWarnings("unused")
    public String getOfferCateg() {
        String categoriesId = "";
        if(Validator.isNotNull(getOffer())) {
            for (AssetCategory category : offer.getCategories()) {
                categoriesId += category.getCategoryId() + " ";
            }
        }

        return categoriesId;
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
                this.themeDisplay.getScopeGroupId(), StrasbourgPortletKeys.EJOB_BO,
                StrasbourgPortletKeys.EJOB_BO, actionId);
    }

}
