package eu.strasbourg.portlet.ejob.display.context;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.service.UserGroupRoleLocalServiceUtil;
import com.liferay.portal.kernel.service.WorkflowDefinitionLinkLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import eu.strasbourg.service.ejob.model.Offer;
import eu.strasbourg.service.ejob.service.OfferLocalServiceUtil;
import eu.strasbourg.utils.AssetVocabularyAccessor;
import eu.strasbourg.utils.AssetVocabularyHelper;
import eu.strasbourg.utils.constants.RoleNames;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

import javax.portlet.RenderRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class EditOfferDisplayContext {

    private Offer offer;
    private List<AssetCategory> categories;
    private List<List> filieres;
    private List<List> grades;
    private final RenderRequest request;
    private final ThemeDisplay themeDisplay;
    private final long groupId;

    public EditOfferDisplayContext(RenderRequest request) {
        this.request = request;
        this.themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        this.groupId = this.themeDisplay.getScopeGroupId();
    }

    public Offer getOffer() {
        if (this.offer == null) {
            long offerId = ParamUtil.getLong(this.request, "offerId");
            if (offerId > 0) {
                this.offer = OfferLocalServiceUtil.fetchOffer(offerId);
            }
        }
        String isDuplication = ParamUtil.getString(this.request, "isDuplication");
        if (isDuplication.equals("true")) {
            offer.setNew(true);
            offer.setStatus(WorkflowConstants.STATUS_DRAFT);
        }
        return offer;
    }

    /**
     * Renvoie les types de recrutements
     */
    @SuppressWarnings("unused")
    public List<AssetCategory> getTypeRecrutements() {
        List<AssetCategory> typeRecrutements = new ArrayList<>();
        TreeMap<Integer, AssetCategory> mapRecrutements = new TreeMap<>();
        AssetVocabulary typeRecrutement_voca = AssetVocabularyAccessor.getEJobTypeRecrutement(this.groupId);
        for (AssetCategory typeRecrutement: typeRecrutement_voca.getCategories()) {
            mapRecrutements.put(Integer.parseInt(AssetVocabularyHelper.getCategoryProperty(typeRecrutement.getCategoryId(), "order")),typeRecrutement);
        }

        typeRecrutements = new ArrayList<AssetCategory>(mapRecrutements.values());

        return typeRecrutements;
    }

    /**
     * Renvoie les types de publication
     */
    @SuppressWarnings("unused")
    public List<AssetCategory> getTypePublications() {
        List<AssetCategory> typePublications = new ArrayList<>();
        AssetVocabulary typePublication_voca = AssetVocabularyAccessor.getEJobTypePublication(this.groupId);
        for (AssetCategory typePublication: typePublication_voca.getCategories()) {
            typePublications.add(typePublication);
        }

        return typePublications;
    }

    /**
     * Renvoie les Direction
     */
    @SuppressWarnings("unused")
    public List<AssetCategory> getDirections() {
        List<AssetCategory> directions = new ArrayList<>();
        AssetVocabulary direction_voca = AssetVocabularyAccessor.getEJobDirection(this.groupId);
        for (AssetCategory direction : direction_voca.getCategories()) {
            if (direction.getParentCategory() == null)
                directions.add(direction);
        }

        return directions;
    }

    /**
     * Renvoie les Services
     */
    @SuppressWarnings("unused")
    public List<AssetCategory> getServices() {
        List<AssetCategory> services = new ArrayList<>();
        AssetVocabulary services_voca = AssetVocabularyAccessor.getEJobDirection(this.groupId);
        for (AssetCategory service : services_voca.getCategories()) {
            if (service.getParentCategory() != null) {
                services.add(service);
            }
        }

        return services;
    }

    /**
     * Renvoie les motifs
     */
    @SuppressWarnings("unused")
    public List<AssetCategory> getMotifs() {
        List<AssetCategory> motifs = new ArrayList<>();
        AssetVocabulary motifVoca = AssetVocabularyAccessor.getEJobMotif(this.groupId);
        for (AssetCategory typePublication: motifVoca.getCategories()) {
            motifs.add(typePublication);
        }

        return motifs;
    }

    /**
     * Renvoie les categories A, B ou C
     */
    @SuppressWarnings("unused")
    public List<AssetCategory> getCategories() {
        if (this.categories == null) {
            this.categories = new ArrayList<>();
            AssetVocabulary categories_voca = AssetVocabularyAccessor.getEJobCategories(this.groupId);
            for (AssetCategory category : categories_voca.getCategories()) {
                this.categories.add(category);
            }
        }

        return this.categories;
    }

    /**
     * Renvoie les filieres
     */
    @SuppressWarnings("unused")
    public List<List> getFilieres() {
        if (this.filieres == null) {
            this.filieres = new ArrayList<>();
            AssetVocabulary filieres_voca = AssetVocabularyAccessor.getEJobFilieres(this.groupId);
            for (AssetCategory filiere : filieres_voca.getCategories()) {
                if (filiere.getParentCategory() == null) {
                    List filiereWithCateg = new ArrayList<>();
                    filiereWithCateg.add(filiere);
                    // récupère les catégories de la filière
                    String categoriesList = "";
                    List<AssetCategory> filiereCategories = AssetVocabularyHelper.getChild(filiere.getCategoryId());
                    for (AssetCategory filiereCategory : filiereCategories) {
                        String categ = AssetVocabularyHelper.getCategoryProperty(filiereCategory.getCategoryId(), "linked-category");
                        if(!categoriesList.contains(categ))
                            categoriesList +=  categ;
                    }
                    filiereWithCateg.add(categoriesList);
                    this.filieres.add(filiereWithCateg);
                }
            }
        }

        return this.filieres;
    }

    /**
     * Renvoie les grades
     */
    @SuppressWarnings("unused")
    public List<List> getGrades() {
        if(this.grades == null);
        this.grades = new ArrayList<>();
        AssetVocabulary filieres_voca = AssetVocabularyAccessor.getEJobFilieres(this.groupId);
        for (AssetCategory grade: filieres_voca.getCategories()) {
            if(grade.getParentCategory() != null && grade.getParentCategory().getParentCategory() != null) {
                List gradeWithCateg = new ArrayList<>();
                gradeWithCateg.add(grade);
                // récupère les catégories du grade
                String categ = AssetVocabularyHelper.getCategoryProperty(grade.getParentCategory().getCategoryId(), "linked-category");
                gradeWithCateg.add(categ);
                this.grades.add(gradeWithCateg);
            }
        }

        return this.grades;
    }

    /**
     * Renvoie les Niveau d'étude
     */
    @SuppressWarnings("unused")
    public List<AssetCategory> getNiveauEtudes() {
        List<AssetCategory> niveauEtudes = new ArrayList<>();
        AssetVocabulary niveauEtude_voca = AssetVocabularyAccessor.getEJobNiveauEtude(this.groupId);
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
        List<AssetCategory> familles = new ArrayList<>();
        AssetVocabulary famille_voca = AssetVocabularyAccessor.getEJobFamille(this.groupId);
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
        List<AssetCategory> contacts = new ArrayList<>();
        AssetVocabulary contact_voca = AssetVocabularyAccessor.getEJobContact(this.groupId);
        for (AssetCategory contact: contact_voca.getCategories()) {
            contacts.add(contact);
        }

        return contacts;
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

    public String getDefaultEmailIndexes() {
        if(this.offer != null){
            String indexes = "0";
            for (int i = 1; i < this.offer.getEmails().split(",").length; i++) {
                indexes += "," + i;
            }
            return indexes;
        }
        return "";
    }

    public String getDefaultGradeRangeIndexes() {
        if(this.offer != null){
            String indexes = "0";
            for (int i = 1; i < this.offer.getGradeRanges().size(); i++) {
                indexes += "," + i;
            }
            return indexes;
        }
        return "";
    }


    public boolean isContribOnly(){
        try {
            Role  responsableEmploi = RoleLocalServiceUtil.getRole(this.themeDisplay.getCompanyId(), RoleNames.RESPONSABLE_EMPLOI);
            Role siteAdministrator = RoleLocalServiceUtil.getRole(this.themeDisplay.getCompanyId(), RoleNames.SITE_ADMLINISTRATOR);
            if(themeDisplay.getPermissionChecker().isOmniadmin()
                    || UserGroupRoleLocalServiceUtil.hasUserGroupRole(themeDisplay.getUserId(),themeDisplay.getScopeGroupId(), responsableEmploi.getRoleId())
                    || UserGroupRoleLocalServiceUtil.hasUserGroupRole(themeDisplay.getUserId(),themeDisplay.getScopeGroupId(), siteAdministrator.getRoleId()))
                return false;

            Role assistantRecrutement = RoleLocalServiceUtil.getRole(this.themeDisplay.getCompanyId(), RoleNames.ASSISTANT_RECRUTEMENT);
            return UserGroupRoleLocalServiceUtil.hasUserGroupRole(themeDisplay.getUserId(),themeDisplay.getScopeGroupId(), assistantRecrutement.getRoleId());
        } catch (PortalException e) {
            e.printStackTrace();
        }
        return false;
    }

}
