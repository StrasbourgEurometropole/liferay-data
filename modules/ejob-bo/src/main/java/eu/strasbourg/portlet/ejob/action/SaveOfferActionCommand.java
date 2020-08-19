package eu.strasbourg.portlet.ejob.action;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.asset.kernel.service.AssetCategoryLocalService;
import com.liferay.asset.kernel.service.AssetCategoryLocalServiceUtil;
import com.liferay.asset.kernel.service.AssetEntryLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import eu.strasbourg.service.ejob.model.Offer;
import eu.strasbourg.service.ejob.service.OfferLocalService;
import eu.strasbourg.utils.AssetVocabularyAccessor;
import eu.strasbourg.utils.AssetVocabularyHelper;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import eu.strasbourg.utils.constants.VocabularyNames;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Component(
        immediate = true,
        property = {
                "javax.portlet.name=" + StrasbourgPortletKeys.EJOB_BO,
                "mvc.command.name=saveOffer"
        },
        service = MVCActionCommand.class
)
public class SaveOfferActionCommand implements MVCActionCommand {

    private final Log log = LogFactoryUtil.getLog(this.getClass().getName());

    private long offerId;
    private String typeRecrutementString;
    private String exportTotem;
    // Défini le format de date à utiliser pour les champs temporels
    private DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    @Override
    public boolean processAction(ActionRequest request, ActionResponse response) {
        try {
            // Récupération du contexte de la requêtes
            ServiceContext sc = ServiceContextFactory.getInstance(request);
            ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);

            // Validation
            boolean isValid = this.validate(request);
            if (!isValid) {
                // Si pas valide : on reste sur la page d'édition
                PortalUtil.copyRequestParameters(request, response);

                String portletName = (String) request.getAttribute(WebKeys.PORTLET_ID);
                PortletURL returnURL = PortletURLFactoryUtil.create(request, portletName, themeDisplay.getPlid(),
                        PortletRequest.RENDER_PHASE);

                response.setRenderParameter("returnURL", returnURL.toString());
                response.setRenderParameter("cmd", "editOffer");
                response.setRenderParameter("mvcPath", "/ejob-bo-edit-offer.jsp");
                return false;
            }

            // Si édition ou création d'une nouvelle entrée
            Offer offer;
            if (this.offerId == 0) {
                offer = this.offerLocalService.createOffer(sc);
            } else {
                offer = this.offerLocalService.getOffer(this.offerId);
            }
            // Pour les catégories de l'assetentry
            List<String> categories = new ArrayList<>();

            // Champs globaux
            // Champ : ejobTypeRecrutement
            long ejobTypeRecrutement = ParamUtil.getLong(request, "ejobTypeRecrutement");
            if (Validator.isNotNull(AssetCategoryLocalServiceUtil
                    .fetchAssetCategory(ejobTypeRecrutement))) {
                categories.add(Long.toString(ejobTypeRecrutement));
            }

            // Champ : duration
            Map<Locale, String> duration = LocalizationUtil
                    .getLocalizationMap(request, "duration");
            offer.setDurationMap(duration);

            // Champ : post
            Map<Locale, String> post = LocalizationUtil
                    .getLocalizationMap(request, "post");
            offer.setPostMap(post);

            // Champ : ejobDirection
            long ejobDirection = ParamUtil.getLong(request, "ejobDirection");
            if (Validator.isNotNull(AssetCategoryLocalServiceUtil
                    .fetchAssetCategory(ejobDirection))) {
                categories.add(""+ejobDirection);
            }

            // Champ : ejobService
            long ejobService = ParamUtil.getLong(request, "ejobService");
            if (Validator.isNotNull(AssetCategoryLocalServiceUtil
                    .fetchAssetCategory(ejobService))) {
                categories.add(""+ejobService);
            }

            // Champ : ejobNiveauEtude
            long ejobNiveauEtude = ParamUtil.getLong(request, "ejobNiveauEtude");
            if (Validator.isNotNull(AssetCategoryLocalServiceUtil
                    .fetchAssetCategory(ejobNiveauEtude))) {
                categories.add(""+ejobNiveauEtude);
            }

            // Champ : introduction
            Map<Locale, String> introduction = LocalizationUtil
                    .getLocalizationMap(request, "introduction");
            offer.setIntroductionMap(introduction);

            // Champ : activities
            Map<Locale, String> activities = LocalizationUtil
                    .getLocalizationMap(request, "activities");
            offer.setActivitiesMap(activities);

            // Champ : profil
            Map<Locale, String> profil = LocalizationUtil
                    .getLocalizationMap(request, "profil");
            offer.setProfilMap(profil);

            // Champ : conditions
            Map<Locale, String> conditions = LocalizationUtil
                    .getLocalizationMap(request, "conditions");
            offer.setConditionsMap(conditions);

            // Champ : ejobFamille
            long ejobFamille = ParamUtil.getLong(request, "ejobFamille");
            if (Validator.isNotNull(AssetCategoryLocalServiceUtil
                    .fetchAssetCategory(ejobFamille))) {
                categories.add(""+ejobFamille);
            }

            // Champ : limitDate
            Date limitDate = ParamUtil.getDate(request,
                    "limitDate" , dateFormat);
            offer.setLimitDate(limitDate);

            // Champ : publicationStartDate
            Date publicationStartDate = ParamUtil.getDate(request,
                    "publicationStartDate" , dateFormat);
            LocalDateTime startPublication = new Timestamp(publicationStartDate.getTime())
                    .toLocalDateTime().withHour(0).withMinute(0).withSecond(0).withNano(0);
            offer.setPublicationStartDate(Timestamp.valueOf(startPublication));

            // Champ : publicationEndDate
            Date publicationEndDate = ParamUtil.getDate(request,
                    "publicationEndDate" , dateFormat);
            LocalDateTime endPublication = new Timestamp(publicationEndDate.getTime())
                    .toLocalDateTime().withHour(23).withMinute(59).withSecond(59).withNano(999999999);
            offer.setPublicationEndDate(Timestamp.valueOf(endPublication));

            // pour les stages
            if(this.typeRecrutementString.equals("Stage")) {
                // Champ : postNumber
                String postNumber = ParamUtil.getString(request,
                        "postNumber");
                offer.setPostNumber(postNumber);

                // Champ : contact
                String contact = ParamUtil.getString(request,
                        "contact");
                offer.setContact(contact);

                // Champ : emails
                String emails = ParamUtil.getString(request,
                        "emails");
                offer.setEmails(emails);

                // Champ : shareLinkedin
                boolean shareLinkedin = ParamUtil.getBoolean(request,
                        "shareLinkedin");
                offer.setShareLinkedin(shareLinkedin);
            }else{
                // pour les offres internes ou  externes
                // Champ : exportTotem
                long groupId = themeDisplay.getLayout().getGroupId();

                AssetVocabulary listExportTotem = AssetVocabularyAccessor.getEJobExportTotem(groupId);
                for (AssetCategory export : listExportTotem.getCategories()) {
                    if (export.getTitle(Locale.FRANCE).toLowerCase().equals(this.exportTotem)) {
                        categories.add("" + export.getCategoryId());
                        break;
                    }
                }

                // Champ : jobCreationDescription
                Map<Locale, String> jobCreationDescription = LocalizationUtil
                        .getLocalizationMap(request, "jobCreationDescription");
                offer.setJobCreationDescriptionMap(jobCreationDescription);

                // Champ : startDate
                Date startDate = ParamUtil.getDate(request,
                        "startDate" , dateFormat);
                offer.setStartDate(startDate);

                // Champ : motif
                Map<Locale, String> motif = LocalizationUtil
                        .getLocalizationMap(request, "motif");
                offer.setMotifMap(motif);

                // Champ : permanentDescription
                Map<Locale, String> permanentDescription = LocalizationUtil
                        .getLocalizationMap(request, "permanentDescription");
                offer.setPermanentDescriptionMap(permanentDescription);

                // Champ : isFullTime
                boolean isFullTime = ParamUtil.getBoolean(request,
                        "isFullTime");
                offer.setIsFullTime(isFullTime);

                if(isFullTime) {
                    // Champ : fullTimeDescription
                    Map<Locale, String> fullTimeDescription = LocalizationUtil
                            .getLocalizationMap(request, "fullTimeDescription");
                    offer.setFullTimeDescriptionMap(fullTimeDescription);
                }

                // Champ : ejobFiliere
                long ejobFiliere = ParamUtil.getLong(request, "ejobFiliere");
                if (Validator.isNotNull(AssetCategoryLocalServiceUtil
                        .fetchAssetCategory(ejobFiliere))) {
                    categories.add(""+ejobFiliere);
                }

                // Champ : ejobCategorie label
                long ejobCategorie = ParamUtil.getLong(request, "ejobCategorie");
                if (Validator.isNotNull(AssetCategoryLocalServiceUtil
                        .fetchAssetCategory(ejobCategorie))) {
                    categories.add(""+ejobCategorie);
                }

                // Champ : ejobCategorie A/B/C
                String linkedCategory = AssetVocabularyHelper.getCategoryProperty(ejobCategorie, "linked-category");
                AssetVocabulary listCategory = AssetVocabularyAccessor.getEJobCategories(groupId);
                for (AssetCategory category : listCategory.getCategories()) {
                    if (category.getTitle(Locale.FRANCE).equals(linkedCategory)) {
                        categories.add("" + category.getCategoryId());
                        break;
                    }
                }

                // Champ : ejobGrade
                long ejobGrade = ParamUtil.getLong(request, "ejobGrade");
                if (Validator.isNotNull(AssetCategoryLocalServiceUtil
                        .fetchAssetCategory(ejobGrade))) {
                    categories.add(""+ejobGrade);
                }

                // Champ : avantages
                Map<Locale, String> avantages = LocalizationUtil
                        .getLocalizationMap(request, "avantages");
                offer.setAvantagesMap(avantages);

                 // pour les offres internes
                if(this.exportTotem.equals("interne")) {
                    // Champ : ejobContact
                    long ejobContact = ParamUtil.getLong(request, "ejobContact");
                    if (Validator.isNotNull(AssetCategoryLocalServiceUtil
                            .fetchAssetCategory(ejobContact))) {
                        categories.add(""+ejobContact);
                    }

                    // Champ : contact
                    String contact = ParamUtil.getString(request,
                            "contact");
                    offer.setContact(contact);

                    // Champ : emails
                    String emails = ParamUtil.getString(request,
                            "emails");
                    offer.setEmails(emails);

                    // Champ : shareLinkedin
                    boolean shareLinkedin = ParamUtil.getBoolean(request,
                            "shareLinkedin");
                    offer.setShareLinkedin(shareLinkedin);
                }

            }

            // Mise à jour de l'entrée en base
            offer = this.offerLocalService.updateOffer(offer, sc);

            if(Validator.isNull(offer.getPublicationId())){
                // calcul de l'id de publication
                // récupération de l'acronyme
                String publicationId = AssetVocabularyHelper.getCategoryProperty(ejobTypeRecrutement, "acro");
                // récupération de l'incrément
                publicationId += String.format("%06d", offer.getOfferId());
                offer.setPublicationId(publicationId);

                // Mise à jour de l'entrée en base
                offer = this.offerLocalService.updateOffer(offer, sc);
            }

            long[] categoryIds = new long[categories.size()];
            for (int i = 0; i < categories.size(); i++) {
                if (Validator.isNotNull(categories.get(i))) {
                    categoryIds[i] = Long.parseLong(categories.get(i));
                }
            }

            this.assetEntryLocalService.updateEntry(sc.getUserId(),
                    offer.getGroupId(), Offer.class.getName(),
                    offer.getOfferId(), categoryIds, null);

        } catch (PortalException e) {
            log.error(e);
        }
        return true;
    }

    /**
     * Validation de la requête
     */
    private boolean validate(ActionRequest request) {
        boolean isValid = true;

        this.offerId = ParamUtil.getLong(request, "offerId");

        // Récupération du type de recrutement
        long typeRecrutement = ParamUtil.getLong(request, "ejobTypeRecrutement");
        if (Validator.isNull(typeRecrutement)) {
            SessionErrors.add(request, "type-recrutement-error");
            isValid = false;
        }
        // Récupération du label du type de recrutement
        this.typeRecrutementString = "";
        AssetCategory category = assetCategoryLocalService.fetchAssetCategory(typeRecrutement);
        if(Validator.isNotNull(category)){
            this.typeRecrutementString = category.getTitle(Locale.FRANCE);
        }


        // Récupération du type d'export
        this.exportTotem = ParamUtil.getString(request, "exportTotem");

        // initulé du post
        if (Validator.isNull(ParamUtil.getString(request, "post"))) {
            SessionErrors.add(request, "post-error");
            isValid = false;
        }

        // direction
        if (Validator.isNull(ParamUtil.getLong(request, "ejobDirection"))) {
            SessionErrors.add(request, "direction-error");
            isValid = false;
        }

        // si offre pas stage
        if(!this.typeRecrutementString.equals("Stage")) {
            // si temps complet
            if (Validator.isNull(ParamUtil.getBoolean(request, "isFullTime"))) {
                SessionErrors.add(request, "full-time-error");
                isValid = false;
            }

            // filière
            if (Validator.isNull(ParamUtil.getLong(request, "ejobFiliere"))) {
                SessionErrors.add(request, "filiere-error");
                isValid = false;
            }

            // catégorie
            if (Validator.isNull(ParamUtil.getLong(request, "ejobCategorie"))) {
                SessionErrors.add(request, "categorie-error");
                isValid = false;
            }

            // grade
            if (Validator.isNull(ParamUtil.getLong(request, "ejobGrade"))) {
                SessionErrors.add(request, "grade-error");
                isValid = false;
            }
        }

        // niveau d'étude
        if (Validator.isNull(ParamUtil.getLong(request, "ejobNiveauEtude"))) {
            SessionErrors.add(request, "etude-error");
            isValid = false;
        }

        // introduction
        if (Validator.isNull(ParamUtil.getString(request, "introductionEditor"))) {
            SessionErrors.add(request, "introduction-error");
            isValid = false;
        }

        // activities
        if (Validator.isNull(ParamUtil.getString(request, "activitiesEditor"))) {
            SessionErrors.add(request, "activities-error");
            isValid = false;
        }

        // profil
        if (Validator.isNull(ParamUtil.getString(request, "profilEditor"))) {
            SessionErrors.add(request, "profil-error");
            isValid = false;
        }

        // conditions
        if (Validator.isNull(ParamUtil.getString(request, "conditionsEditor"))) {
            SessionErrors.add(request, "conditions-error");
            isValid = false;
        }

        // avantages
        if (Validator.isNull(ParamUtil.getString(request, "avantagesEditor")) && !this.typeRecrutementString.equals("Stage")) {
            SessionErrors.add(request, "avantages-error");
            isValid = false;
        }

        // famille
        if (Validator.isNull(ParamUtil.getString(request, "ejobFamille"))) {
            SessionErrors.add(request, "famille-error");
            isValid = false;
        }

        // Date limite
        if (Validator.isNull(ParamUtil.getDate(request, "limitDate", dateFormat))) {
            SessionErrors.add(request, "date-limit-error");
            isValid = false;
        }

        // contact
        if (Validator.isNull(ParamUtil.getString(request, "contact")) && (this.typeRecrutementString.equals("Stage") || this.exportTotem.equals("interne"))) {
            SessionErrors.add(request, "contact-error");
            isValid = false;
        }

        // date de début de publication
        String publicationStartDateString = ParamUtil.getString(request, "publicationStartDate");
        Date publicationStartDate = ParamUtil.getDate(request, "publicationStartDate", dateFormat);
        // date de fin de publication
        String publicationEndDateString = ParamUtil.getString(request, "publicationEndDate");
        Date publicationEndDate = ParamUtil.getDate(request, "publicationEndDate", dateFormat);
        if ((Validator.isNotNull(publicationStartDateString) && Validator.isNotNull(publicationEndDateString)
                && publicationStartDate.compareTo(publicationEndDate) > 0)) {
            SessionErrors.add(request, "publication-dates-error");
            isValid = false;
        }

        return isValid;
    }

    @Reference(unbind = "-")
    protected void setOfferLocalService(OfferLocalService offerLocalService) {
        this.offerLocalService = offerLocalService;
    }

    private OfferLocalService offerLocalService;

    @Reference(unbind = "-")
    protected void setAssetCategoryLocalService(AssetCategoryLocalService assetCategoryLocalService) {
        this.assetCategoryLocalService = assetCategoryLocalService;
    }

    private AssetCategoryLocalService assetCategoryLocalService;

    @Reference(unbind = "-")
    protected void setAssetEntryLocalService(AssetEntryLocalService assetEntryLocalService) {
        this.assetEntryLocalService = assetEntryLocalService;
    }

    private AssetEntryLocalService assetEntryLocalService;

}
