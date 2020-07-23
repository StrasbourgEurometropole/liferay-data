package eu.strasbourg.portlet.ejob.display.context;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.portal.kernel.service.WorkflowDefinitionLinkLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import eu.strasbourg.service.council.service.TypeLocalServiceUtil;
import eu.strasbourg.service.ejob.model.Offer;
import eu.strasbourg.service.ejob.service.OfferLocalServiceUtil;
import eu.strasbourg.utils.AssetVocabularyAccessor;
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
     * Renvoie les filieres
     */
    @SuppressWarnings("unused")
    public List<AssetCategory> getFilieres() {
        long groupId = themeDisplay.getLayout().getGroupId();
        List<AssetCategory> filieres = new ArrayList<>();
        AssetVocabulary filieres_voca = AssetVocabularyAccessor.getEJobFilieres(groupId);
        for (AssetCategory filiere: filieres_voca.getCategories()) {
            if(filiere.getParentCategory()==null) {
                filieres.add(filiere);
            }
        }

        return filieres;
    }

    /**
     * Renvoie les categories
     */
    @SuppressWarnings("unused")
    public List<AssetCategory> getCategories() {
        long groupId = themeDisplay.getLayout().getGroupId();
        List<AssetCategory> categories = new ArrayList<>();
        AssetVocabulary categories_voca = AssetVocabularyAccessor.getEJobCategories(groupId);
        for (AssetCategory categorie: categories_voca.getCategories()) {
            if(categorie.getParentCategory()==null) {
                categories.add(categorie);
            }
        }

        return categories;
    }

    /**
     * Renvoie les categories des filieres
     */
    @SuppressWarnings("unused")
    public List<AssetCategory> getFilieresCategories() {
        long groupId = themeDisplay.getLayout().getGroupId();
        List<AssetCategory> filieresCategories = new ArrayList<>();
        AssetVocabulary filieres_voca = AssetVocabularyAccessor.getEJobFilieres(groupId);
        for (AssetCategory category: filieres_voca.getCategories()) {
            if(this.getFilieres().contains(category.getParentCategory())) {
                filieresCategories.add(category);
            }
        }

        return filieresCategories;
    }

    /**
     * Renvoie les grades
     */
    @SuppressWarnings("unused")
    public List<AssetCategory> getGrades() {
        long groupId = themeDisplay.getLayout().getGroupId();
        List<AssetCategory> grades = new ArrayList<>();
        AssetVocabulary filieres_voca = AssetVocabularyAccessor.getEJobFilieres(groupId);
        for (AssetCategory grade: filieres_voca.getCategories()) {
            if(this.getFilieresCategories().contains(grade.getParentCategory())) {
                grades.add(grade);
            }
        }

        return grades;
    }

    /**
     * Renvoie les grades
     */
    @SuppressWarnings("unused")
    public List<AssetCategory> getTypeRecrutements() {
        long groupId = themeDisplay.getLayout().getGroupId();
        List<AssetCategory> typeRecrutements = new ArrayList<>();
        AssetVocabulary typeRecrutement_voca = AssetVocabularyAccessor.getEJobTypeRecrutement(groupId);
        for (AssetCategory typeRecrutement: typeRecrutement_voca.getCategories()) {
                typeRecrutements.add(typeRecrutement);
        }

        return typeRecrutements;
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
