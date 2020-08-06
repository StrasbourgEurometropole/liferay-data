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
     * Renvoie les types de recrutements
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
     * Renvoie les Direction
     */
    @SuppressWarnings("unused")
    public List<AssetCategory> getDirections() {
        if (this.directions == null) {
            long groupId = themeDisplay.getLayout().getGroupId();
            this.directions = new ArrayList<>();
            AssetVocabulary direction_voca = AssetVocabularyAccessor.getEJobDirection(groupId);
            for (AssetCategory direction : direction_voca.getCategories()) {
                if (direction.getParentCategory() == null)
                    this.directions.add(direction);
            }
        }

        return this.directions;
    }

    /**
     * Renvoie les Services
     */
    @SuppressWarnings("unused")
    public List<AssetCategory> getServices() {
        long groupId = themeDisplay.getLayout().getGroupId();
        List<AssetCategory> services = new ArrayList<>();
        AssetVocabulary service_voca = AssetVocabularyAccessor.getEJobDirection(groupId);
        for (AssetCategory service: service_voca.getCategories()) {
            if (this.getDirections().contains(service.getParentCategory()))
                services.add(service);
        }

        return services;
    }

    /**
     * Renvoie les filieres
     */
    @SuppressWarnings("unused")
    public List<AssetCategory> getFilieres() {
        if (this.filieres == null) {
            long groupId = themeDisplay.getLayout().getGroupId();
            this.filieres = new ArrayList<>();
            AssetVocabulary filieres_voca = AssetVocabularyAccessor.getEJobFilieres(groupId);
            for (AssetCategory filiere : filieres_voca.getCategories()) {
                if (filiere.getParentCategory() == null) {
                    this.filieres.add(filiere);
                }
            }
        }

        return this.filieres;
    }

    /**
     * Renvoie les categories des filieres
     */
    @SuppressWarnings("unused")
    public List<AssetCategory> getFilieresCategories() {
        if (this.filieresCategories == null) {
            long groupId = themeDisplay.getLayout().getGroupId();
            this.filieresCategories = new ArrayList<>();
            AssetVocabulary filieres_voca = AssetVocabularyAccessor.getEJobFilieres(groupId);
            for (AssetCategory category : filieres_voca.getCategories()) {
                if (this.getFilieres().contains(category.getParentCategory())) {
                    this.filieresCategories.add(category);
                }
            }
        }

        return this.filieresCategories;
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
     * Renvoie les Niveau d'étude
     */
    @SuppressWarnings("unused")
    public List<AssetCategory> getNiveauEtudes() {
        long groupId = themeDisplay.getLayout().getGroupId();
        List<AssetCategory> niveauEtudes = new ArrayList<>();
        AssetVocabulary niveauEtude_voca = AssetVocabularyAccessor.getEJobNiveauEtude(groupId);
        for (AssetCategory niveauEtude: niveauEtude_voca.getCategories()) {
            niveauEtudes.add(niveauEtude);
        }

        return niveauEtudes;
    }

    /**
     * Renvoie les Famille de métiers
     */
    @SuppressWarnings("unused")
    public List<AssetCategory> getFamilles() {
        long groupId = themeDisplay.getLayout().getGroupId();
        List<AssetCategory> familles = new ArrayList<>();
        AssetVocabulary famille_voca = AssetVocabularyAccessor.getEJobFamille(groupId);
        for (AssetCategory famille: famille_voca.getCategories()) {
            familles.add(famille);
        }

        return familles;
    }

    /**
     * Renvoie les contact RE
     */
    @SuppressWarnings("unused")
    public List<AssetCategory> getContacts() {
        long groupId = themeDisplay.getLayout().getGroupId();
        List<AssetCategory> contacts = new ArrayList<>();
        AssetVocabulary contact_voca = AssetVocabularyAccessor.getEJobContact(groupId);
        for (AssetCategory contact: contact_voca.getCategories()) {
            contacts.add(contact);
        }

        return contacts;
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
