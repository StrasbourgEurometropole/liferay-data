package eu.strasbourg.service.csmap.utils;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.asset.kernel.service.AssetCategoryPropertyLocalServiceUtil;
import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.FriendlyURLNormalizerUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import eu.strasbourg.service.agenda.model.Campaign;
import eu.strasbourg.service.agenda.model.CsmapCacheJson;
import eu.strasbourg.service.agenda.model.Event;
import eu.strasbourg.service.agenda.model.Historic;
import eu.strasbourg.service.agenda.service.CampaignLocalServiceUtil;
import eu.strasbourg.service.agenda.service.CsmapCacheJsonLocalServiceUtil;
import eu.strasbourg.service.agenda.service.HistoricLocalServiceUtil;
import eu.strasbourg.service.csmap.exception.NoDefaultPictoException;
import eu.strasbourg.service.csmap.model.Agenda;
import eu.strasbourg.service.csmap.service.AgendaLocalServiceUtil;
import eu.strasbourg.service.csmap.service.PlaceCategoriesLocalServiceUtil;
import eu.strasbourg.utils.AssetVocabularyHelper;
import eu.strasbourg.utils.DateHelper;
import eu.strasbourg.utils.FileEntryHelper;
import eu.strasbourg.utils.SearchHelper;
import eu.strasbourg.utils.StrasbourgPropsUtil;
import eu.strasbourg.utils.UriHelper;
import eu.strasbourg.utils.constants.CategoryNames;
import eu.strasbourg.utils.constants.VocabularyNames;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ApiCsmapUtil {

    public static JSONObject getEvents(String lastUpdateTimeString) throws JSONException {

        // On transforme la date string en date
        Date lastUpdateTime;
        long lastUpdateTimeLong = Long.parseLong(lastUpdateTimeString);
        lastUpdateTime = DateHelper.getDateFromUnixTimestamp(lastUpdateTimeLong);

        JSONObject json = JSONFactoryUtil.createJSONObject();

        // On récupère tous les events qui ont été ajoutés
        List<CsmapCacheJson> ajouts = CsmapCacheJsonLocalServiceUtil.getByCreatedDateAndIsActiveAndWithSchedules(lastUpdateTime);
        JSONArray jsonAjout = JSONFactoryUtil.createJSONArray();
        for (CsmapCacheJson cache: ajouts) {
            jsonAjout.put(JSONFactoryUtil.createJSONObject(cache.getJsonEvent()));
        }
        json.put("ADD", jsonAjout);

        // On récupère tous les events qui ont été modifiés
        List<CsmapCacheJson> modifications = CsmapCacheJsonLocalServiceUtil.getByCreatedDateAndModifiedDateAndIsActiveAndWithSchedules(lastUpdateTime);
        JSONArray jsonModif = JSONFactoryUtil.createJSONArray();
        for (CsmapCacheJson cache: modifications) {
            jsonModif.put(JSONFactoryUtil.createJSONObject(cache.getJsonEvent()));
        }
        json.put("UPDATE", jsonModif);

        JSONArray jsonSuppr = JSONFactoryUtil.createJSONArray();

        if(!lastUpdateTimeString.equals("0")) {
            // On récupère tous les events qui ont été dépubliés
            List<CsmapCacheJson> depublications = CsmapCacheJsonLocalServiceUtil.getByModifiedDateAndIsNotActive(lastUpdateTime);
            for (CsmapCacheJson cache: depublications) {
                jsonSuppr.put(cache.getEventId());
            }

            // On récupère tous les events qui ont été supprimés
            List<Historic> suppressions = HistoricLocalServiceUtil.getBySuppressionDate(lastUpdateTime);
            for (Historic histo : suppressions) {
                jsonSuppr.put(histo.getEventId());
            }
        }
        json.put("DELETE", jsonSuppr);

        return json;
    }

    public static JSONObject getCategories(String lastUpdateTimeString, String sigIds) throws PortalException, NoDefaultPictoException {

        // On transforme la date string en date
        long lastUpdateTimeLong = Long.parseLong(lastUpdateTimeString);
        Date lastUpdateTime = DateHelper.getDateFromUnixTimestamp(lastUpdateTimeLong);

        // On récupère toutes les catégories qui ont été ajoutées modifiées ou supprimées
        JSONObject json = JSONFactoryUtil.createJSONObject();
        JSONArray jsonAjout = JSONFactoryUtil.createJSONArray();
        JSONArray jsonModif = JSONFactoryUtil.createJSONArray();
        JSONArray jsonSuppr = JSONFactoryUtil.createJSONArray();

        // On récupère les catégories des sigId (externalId)
        Map<String, AssetCategory> mapExternalIdTypeLieu = new HashMap<>();
        // On récupère les sigId (externalId) des catégoriesId
        Map<Long, String> mapIdTypeLieuExternalId = new HashMap<>();
        // Catégories de lieu renvoyer
        List<AssetCategory> categoriesBO = new ArrayList<>();
        // Catégories de lieu existantes choisies pour CSMAP (fait dans le BO)
        String sigIdCategoriesBo = "";

        AssetVocabulary placeTypeVocabulary = AssetVocabularyHelper
                .getGlobalVocabulary(VocabularyNames.PLACE_TYPE);
        if (Validator.isNotNull(placeTypeVocabulary)) {
            // On récupère les catégories du vocabulaire des lieux
            List<AssetCategory> categories = placeTypeVocabulary.getCategories();
            mapExternalIdTypeLieu = AssetVocabularyHelper.getMapCategoriesByExternalId(placeTypeVocabulary, "SIG");
            mapIdTypeLieuExternalId = AssetVocabularyHelper.getMapExternalIdsByCategory(placeTypeVocabulary, "SIG");

            // On récupère les catégories de lieu choisies pour CSMAP (fait dans le BO)
            String categoriesBoString = PlaceCategoriesLocalServiceUtil.getPlaceCategories().getCategoriesIds();
            for (AssetCategory category : categories) {
                if (Validator.isNotNull(categoriesBoString)) {
                    if (categoriesBoString.contains(String.valueOf(category.getCategoryId()))) {
                        categoriesBO.add(category);
                        sigIdCategoriesBo += "," + mapIdTypeLieuExternalId.get(category.getCategoryId());
                    }
                } else {
                    // Dans le cas où categoriesBo est null on ajoute toutes les catégories
                    categoriesBO.add(category);
                }
            }
        }

        // On récupère les pictos du vocabulaire
        Map<String, DLFileEntry> pictos = FileEntryHelper.getPictoForVocabulary(VocabularyNames.PLACE_TYPE, "CSMap");

        // On récupère l'URL du picto par défaut
        String pictoDefaultURL = "";
        DLFileEntry picto = pictos.get("Defaut");
        if (Validator.isNull(picto))
            throw new NoDefaultPictoException();
        pictoDefaultURL = FileEntryHelper.getFileEntryURLWithTimeStamp(picto);

        // On récupère les sigId des catégories passées en paramètre de l'API
        List<String> sigIdsSplitted = Arrays.asList(sigIds.split(","));

        for (AssetCategory categ : categoriesBO) {
            // récupère l'URL du picto de la catégorie
            String pictoURL;
            String externalId = mapIdTypeLieuExternalId.get(categ.getCategoryId());
            if(Validator.isNotNull(externalId))
                picto = pictos.get(externalId);
            boolean updatePicto = false;

            if (picto != null) {
                pictoURL = FileEntryHelper.getFileEntryURLWithTimeStamp(picto);
                updatePicto = lastUpdateTime.before(picto.getModifiedDate());
            } else
                pictoURL = pictoDefaultURL;

            // Si le sigId de la catégorie n'existe pas dans les sigIds passés en paramètre c'est un ajout
            if (!sigIdsSplitted.contains(mapIdTypeLieuExternalId.get(categ.getCategoryId())))
                jsonAjout.put(placeCategoryCSMapJSON(categ, pictoURL, true));
            else if (lastUpdateTime.before(categ.getModifiedDate()) || updatePicto)
                // si la catégorie et/ou le picto a/ont été modifié(s) c'est une modifiation
                // ATTENTION, si le picto a été supprimé, il n'entre pas en modifié
                jsonModif.put(placeCategoryCSMapJSON(categ, pictoURL, updatePicto));
        }

        if (!sigIds.equals("")) {
            if (Validator.isNotNull(placeTypeVocabulary)) {
                // On récupère les sigId des catégories BO
                List<String> sigIdCategoriesBoList = Arrays.asList(sigIdCategoriesBo.split(","));
                for (String idCategory : sigIdsSplitted) {
                    // Si le sigId passé en paramètre n'existe pas dans le vocabulaire type de lieu
                    // Si la catégorie n'est pas choisie dans le BO
                    if (mapExternalIdTypeLieu.get(idCategory) == null ||
                            (Validator.isNotNull(sigIdCategoriesBo) && !sigIdCategoriesBoList.contains(String.valueOf(idCategory))))
                        jsonSuppr.put(idCategory);
                }
            }
        }

        json.put("ADD", jsonAjout);
        json.put("UPDATE", jsonModif);
        json.put("DELETE", jsonSuppr);

        return json;
    }

    public static JSONObject getTerritoires(String lastUpdateTimeString, String idsTerritoires) throws PortalException {

        // On transforme la date string en date
        Date lastUpdateTime;
        long lastUpdateTimeLong = Long.parseLong(lastUpdateTimeString);
        lastUpdateTime = DateHelper.getDateFromUnixTimestamp(lastUpdateTimeLong);

        JSONObject json = JSONFactoryUtil.createJSONObject();

        // On récupère les catégories du vocabulaire des territoires
        AssetVocabulary territoryVocabulary = AssetVocabularyHelper
                .getGlobalVocabulary(VocabularyNames.TERRITORY);
        List<AssetCategory> categories = new ArrayList<>();
        if (Validator.isNotNull(territoryVocabulary))
            categories = territoryVocabulary.getCategories();

        // On récupère les catégories enfants de old_france
        AssetCategory oldFrance = categories.stream().filter(c -> c.getName().equals(CategoryNames.OLD_FRANCE)).findFirst().get();
        if (Validator.isNotNull(oldFrance)){
            List<AssetCategory> categoriesOldFrance = AssetVocabularyHelper.getCategoriesWithChild(oldFrance);
            List<AssetCategory> categoriesOldFranceWithChild = AssetVocabularyHelper.getCategoriesWithChild(categoriesOldFrance);
            // on enlève les catégories de oldFrance aux catégories
            categories = categories.stream().filter(c -> !categoriesOldFranceWithChild.contains(c)).collect(Collectors.toList());
        }

        // On récupère toutes les catégories qui ont été ajoutées ou modifiées
        JSONArray jsonAjout = JSONFactoryUtil.createJSONArray();
        JSONArray jsonModif = JSONFactoryUtil.createJSONArray();

        for (AssetCategory categ : categories) {
            if (lastUpdateTime.before(categ.getCreateDate()))
                jsonAjout.put(territoriesCSMapJSON(categ));
            else if (lastUpdateTime.before(categ.getModifiedDate()))
                jsonModif.put(territoriesCSMapJSON(categ));
        }

        json.put("ADD", jsonAjout);
        json.put("UPDATE", jsonModif);

        // On récupère toutes les catégories qui ont été supprimées
        JSONArray jsonSuppr = JSONFactoryUtil.createJSONArray();

        if (Validator.isNotNull(idsTerritoires)) {
            if (Validator.isNotNull(territoryVocabulary)) {

                Map<String, AssetCategory> mapTerritories = new HashMap<>();
                mapTerritories = AssetVocabularyHelper.getMapCategoriesByExternalId(territoryVocabulary, "SIG");

                for (String idCategory : idsTerritoires.split(",")) {
                    if (Validator.isNull(mapTerritories.get(idCategory))) {
                        jsonSuppr.put(idCategory);
                    }
                }
            }
        }
        json.put("DELETE", jsonSuppr);

        return json;
    }

    public static JSONObject getThemes(String lastUpdateTimeString, String idsThemes) throws PortalException {

        // On transforme la date string en date
        Date lastUpdateTime;
        long lastUpdateTimeLong = Long.parseLong(lastUpdateTimeString);
        lastUpdateTime = DateHelper.getDateFromUnixTimestamp(lastUpdateTimeLong);

        JSONObject json = JSONFactoryUtil.createJSONObject();

        // On récupère les catégories du vocabulaire des thèmes agenda
        AssetVocabulary eventThemeVocabulary = AssetVocabularyHelper
                .getGlobalVocabulary(VocabularyNames.EVENT_THEME);
        List<AssetCategory> categories = new ArrayList<>();
        if (Validator.isNotNull(eventThemeVocabulary))
            categories = eventThemeVocabulary.getCategories();

        // On récupère toutes les catégories qui ont été ajoutées ou modifiées
        JSONArray jsonAjout = JSONFactoryUtil.createJSONArray();
        JSONArray jsonModif = JSONFactoryUtil.createJSONArray();

        for (AssetCategory categ : categories) {
            if (lastUpdateTime.before(categ.getCreateDate()))
                jsonAjout.put(eventThemesCSMapJSON(categ));
            else if (lastUpdateTime.before(categ.getModifiedDate()))
                jsonModif.put(eventThemesCSMapJSON(categ));
        }

        json.put("ADD", jsonAjout);
        json.put("UPDATE", jsonModif);
        json.put("UPDATE", jsonModif);

        // On récupère toutes les catégories qui ont été supprimées
        JSONArray jsonSuppr = JSONFactoryUtil.createJSONArray();

        if (Validator.isNotNull(idsThemes)) {

            if (Validator.isNotNull(eventThemeVocabulary)) {

                Map<String, AssetCategory> mapThemes = new HashMap<>();
                mapThemes = AssetVocabularyHelper.getMapCategoriesByExternalId(eventThemeVocabulary, "externalId");

                for (String idCategory : idsThemes.split(",")) {
                    if (Validator.isNull(mapThemes.get(idCategory))) {
                        jsonSuppr.put(idCategory);
                    }
                }
            }
        }
        json.put("DELETE", jsonSuppr);

        return json;
    }

    public static JSONObject getTypes(String lastUpdateTimeString, String idsTypes) throws PortalException {

        // On transforme la date string en date
        Date lastUpdateTime;
        long lastUpdateTimeLong = Long.parseLong(lastUpdateTimeString);
        lastUpdateTime = DateHelper.getDateFromUnixTimestamp(lastUpdateTimeLong);

        JSONObject json = JSONFactoryUtil.createJSONObject();

        // On récupère les catégories du vocabulaire des types agenda
        AssetVocabulary eventTypeVocabulary = AssetVocabularyHelper
                .getGlobalVocabulary(VocabularyNames.EVENT_TYPE);
        List<AssetCategory> categories = new ArrayList<>();
        if (Validator.isNotNull(eventTypeVocabulary))
            categories = eventTypeVocabulary.getCategories();

        // On récupère toutes les catégories qui ont été ajoutées ou modifiées
        JSONArray jsonAjout = JSONFactoryUtil.createJSONArray();
        JSONArray jsonModif = JSONFactoryUtil.createJSONArray();

        for (AssetCategory categ : categories) {
            if (lastUpdateTime.before(categ.getCreateDate()))
                jsonAjout.put(eventTypesCSMapJSON(categ));
            else if (lastUpdateTime.before(categ.getModifiedDate()))
                jsonModif.put(eventTypesCSMapJSON(categ));
        }

        json.put("ADD", jsonAjout);
        json.put("UPDATE", jsonModif);

        // On récupère toutes les catégories qui ont été supprimées
        JSONArray jsonSuppr = JSONFactoryUtil.createJSONArray();

        if (Validator.isNotNull(idsTypes)) {
            if (Validator.isNotNull(eventTypeVocabulary)) {

                Map<String, AssetCategory> mapTypes = new HashMap<>();
                mapTypes = AssetVocabularyHelper.getMapCategoriesByExternalId(eventTypeVocabulary, "externalId");

                for (String idCategory : idsTypes.split(",")) {
                    if (Validator.isNull(mapTypes.get(idCategory))) {
                        jsonSuppr.put(idCategory);
                    }
                }
            }
        }
        json.put("DELETE", jsonSuppr);

        return json;
    }

    public static JSONObject getAgenda() throws URISyntaxException {
        JSONObject json = JSONFactoryUtil.createJSONObject();

        // On récupère tous les ids events de l'agenda principal
        Agenda principal = AgendaLocalServiceUtil.getAgendaPrincipal();
        JSONObject jsonPrincipal = JSONFactoryUtil.createJSONObject();
        JSONArray jsonIds = getJsonIds(principal);
        jsonPrincipal.put("ids", jsonIds);
        json.put("principal", jsonPrincipal);

        // On récupère tous les ids events de l'agenda thématique actif
        // s'il y en a un
        Agenda thematique = AgendaLocalServiceUtil.getAgendaThematiqueActif();
        if (Validator.isNotNull(thematique)) {
            // vérifie qu'il est publié
            Date now = new Date();
            if ((Validator.isNull(thematique.getPublicationStartDate()) && Validator.isNull(thematique.getPublicationEndDate()))
                || (Validator.isNotNull(thematique.getPublicationStartDate()) && Validator.isNull(thematique.getPublicationEndDate())
                && now.after(thematique.getPublicationStartDate()))
                || (Validator.isNull(thematique.getPublicationStartDate()) && Validator.isNotNull(thematique.getPublicationEndDate())
                && now.before(thematique.getPublicationEndDate()))
                || (Validator.isNotNull(thematique.getPublicationStartDate()) && Validator.isNotNull(thematique.getPublicationEndDate())
                && now.after(thematique.getPublicationStartDate()) && now.before(thematique.getPublicationEndDate()))){

                JSONObject jsonThematique = JSONFactoryUtil.createJSONObject();
                jsonIds = getJsonIds(thematique);
                jsonThematique.put("ids", jsonIds);

                JSONObject jsonTitle = JSONFactoryUtil.createJSONObject();
                jsonTitle.put("fr_FR", thematique.getEditorialTitle(Locale.FRANCE));
                jsonThematique.put("title", jsonTitle);

                if (Validator.isNotNull(thematique.getSubtitle(Locale.FRANCE))) {
                    JSONObject jsonSubtitle = JSONFactoryUtil.createJSONObject();
                    jsonSubtitle.put("fr_FR", thematique.getSubtitle(Locale.FRANCE));
                    jsonThematique.put("subtitle", jsonSubtitle);
                }

                String imageURL = "";
                if (thematique.getImageId() != null && thematique.getImageId() > 0)
                    imageURL = StrasbourgPropsUtil.getURL() + UriHelper.appendUriImagePreview(FileEntryHelper.getFileEntryURLWithTimeStamp(thematique.getImageId()));

                jsonThematique.put("imageURL", imageURL);

                if (Validator.isNotNull(thematique.getLabelLink(Locale.FRANCE))) {
                    JSONObject jsonLabelLink = JSONFactoryUtil.createJSONObject();
                    jsonLabelLink.put("fr_FR", thematique.getLabelLink(Locale.FRANCE));
                    jsonThematique.put("labelLink", jsonLabelLink);
                }

                if (Validator.isNotNull(thematique.getLink(Locale.FRANCE))) {
                    JSONObject jsonLink = JSONFactoryUtil.createJSONObject();
                    jsonLink.put("fr_FR", thematique.getLink(Locale.FRANCE));
                    jsonThematique.put("link", jsonLink);
                }

                json.put("thematique", jsonThematique);
            }
        }

        if(jsonPrincipal.length() == 0)
            throw new NullPointerException("agenda principal inexistant");

        return json;
    }

    private static JSONArray getJsonIds(Agenda agenda) {

        // ClassNames de la configuration
        String className = Event.class.getName();

        // catégories
        List<Long[]> categoriesIds = new ArrayList<>();
        Long[] categoriesIdsForTheme = ArrayUtil
                .toLongArray(StringUtil.split(agenda.getThemesIds(), ",", 0));
        categoriesIds.add(categoriesIdsForTheme);
        Long[] categoriesIdsForType = ArrayUtil
                .toLongArray(StringUtil.split(agenda.getTypesIds(), ",", 0));
        categoriesIds.add(categoriesIdsForType);
        Long[] categoriesIdsForTerritory = ArrayUtil
                .toLongArray(StringUtil.split(agenda.getTerritoriesIds(), ",", 0));
        categoriesIds.add(categoriesIdsForTerritory);

        // tags
        String[] tagsArray = StringUtil.split(agenda.getTags());

        // campaigns
        // on récupère le nom de la campagne et non l'id
        StringBuilder campaignsTitle = new StringBuilder();
        if (!agenda.getCampaignsIds().isEmpty()){
            for (String campaignId : agenda.getCampaignsIds().split(",")) {
                Campaign campaign = CampaignLocalServiceUtil.fetchCampaign(Long.parseLong(campaignId));
                if (Validator.isNotNull(campaign)) {
                    if (campaignsTitle.length() > 0)
                        campaignsTitle.append(",");
                    campaignsTitle.append(FriendlyURLNormalizerUtil
                            .normalize(campaign.getTitleCurrentValue()));
                }
            }
        }

        // Recherche
        Hits hits = SearchHelper.getEventsAgendaWebServiceSearchHits(className, categoriesIds, tagsArray);

        JSONArray jsonIds = JSONFactoryUtil.createJSONArray();
        List<Long> longIds = new ArrayList<>();
        if (hits != null) {
            List<CsmapCacheJson> csmapCacheJsons = CsmapCacheJsonLocalServiceUtil.getCsmapCacheJsons(-1,-1);
            for (Document document : hits.getDocs()) {
                long id = Long.parseLong(document.get(Field.ENTRY_CLASS_PK));

                if((campaignsTitle.length() == 0) || campaignsTitle.toString().contains(document.get("campaign"))) {
                    // on ne prend que les event présent dans csmapCacheJson avec des schedules
                    CsmapCacheJson csmapCacheJson = csmapCacheJsons.stream().filter(c -> c.getEventId() == id).findFirst().orElse(null);
                    if(Validator.isNotNull(csmapCacheJson) && csmapCacheJson.getHasSchedules())
                        longIds.add(id);
                }
            }
        }
        Collections.sort(longIds);
        for(long id : longIds){
            jsonIds.put(id);
        }
        return jsonIds;
    }

    public static JSONObject placeCategoryCSMapJSON(AssetCategory category, String urlPicto, boolean maj) {
        JSONObject jsonCategory = JSONFactoryUtil.createJSONObject();
        if (category != null) {
            String externalId = AssetVocabularyHelper.getExternalId(category);
            jsonCategory.put("id", externalId);
            String parentExternalId = AssetVocabularyHelper.getExternalId(category.getParentCategory());
            if (Validator.isNotNull(parentExternalId)) {
                jsonCategory.put("parentId", parentExternalId);
            }
            JSONObject nameJSON = JSONFactoryUtil.createJSONObject();
            nameJSON.put("fr_FR", category.getTitle(Locale.FRANCE));
            jsonCategory.put("name", nameJSON);
            JSONObject jsonPicto = JSONFactoryUtil.createJSONObject();
            jsonPicto.put("pictoURL", StrasbourgPropsUtil.getURL() + urlPicto);
            jsonPicto.put("maj", maj);
            jsonCategory.put("picto", jsonPicto);
            JSONObject colorJSON = JSONFactoryUtil.createJSONObject();
            String gradient_start = "#939393";
            String gradient_end = "#CECFCF";
            try {
                String gradient = AssetCategoryPropertyLocalServiceUtil.getCategoryProperty(category.getCategoryId(), "csmap_gradient_start").getValue();
                if(isValidHexaCode(gradient)){
                    gradient_start = "#"+gradient;
                }
            } catch(PortalException e){/* Using the default value */}
            try {
                String gradient = AssetCategoryPropertyLocalServiceUtil.getCategoryProperty(category.getCategoryId(), "csmap_gradient_end").getValue();
                if(isValidHexaCode(gradient)){
                    gradient_end = "#"+gradient;
                }
            } catch(PortalException e){/* Using the default value */}
            colorJSON.put("start", gradient_start);
            colorJSON.put("end", gradient_end);
            jsonCategory.put("color_gradient", colorJSON);


        }
        return  jsonCategory;
    }

    // Function to validate hexadecimal color code .
    public static boolean isValidHexaCode(String str)
    {
        // Regex to check valid hexadecimal color code.
        String regex = "([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})$";

        // Compile the ReGex
        Pattern p = Pattern.compile(regex);

        // If the string is empty
        // return false
        if (str == null) {
            return false;
        }

        // Pattern class contains matcher() method
        // to find matching between given string
        // and regular expression.
        Matcher m = p.matcher(str);

        // Return if the string
        // matched the ReGex
        return m.matches();
    }

    public static JSONObject eventTypesCSMapJSON(AssetCategory category) {
        JSONObject jsonCategory = JSONFactoryUtil.createJSONObject();
        if (category != null) {
            String externalId = AssetVocabularyHelper.getExternalId(category);
            jsonCategory.put("id", externalId);
            String parentExternalId = AssetVocabularyHelper.getExternalId(category.getParentCategory());
            if (Validator.isNotNull(parentExternalId)) {
                jsonCategory.put("parentId", parentExternalId);
            }
            JSONObject nameJSON = JSONFactoryUtil.createJSONObject();
            nameJSON.put("fr_FR", category.getTitle(Locale.FRANCE));
            jsonCategory.put("name", nameJSON);
        }
        return  jsonCategory;
    }

    static public JSONObject eventThemesCSMapJSON(AssetCategory category) {
        JSONObject jsonCategory = JSONFactoryUtil.createJSONObject();
        if (category != null) {
            String externalId = AssetVocabularyHelper.getExternalId(category);
            jsonCategory.put("id", externalId);
            String parentExternalId = AssetVocabularyHelper.getExternalId(category.getParentCategory());
            if (Validator.isNotNull(parentExternalId)) {
                jsonCategory.put("parentId", parentExternalId);
            }
            JSONObject nameJSON = JSONFactoryUtil.createJSONObject();
            nameJSON.put("fr_FR", category.getTitle(Locale.FRANCE));
            jsonCategory.put("name", nameJSON);
        }
        return  jsonCategory;
    }

    static public JSONObject territoriesCSMapJSON(AssetCategory category) {
        JSONObject jsonCategory = JSONFactoryUtil.createJSONObject();
        if (category != null) {
            String externalId = AssetVocabularyHelper.getExternalId(category);
            jsonCategory.put("id", externalId);
            String parentExternalId = AssetVocabularyHelper.getExternalId(category.getParentCategory());
            if (Validator.isNotNull(parentExternalId)) {
                jsonCategory.put("parentId", parentExternalId);
            }
            JSONObject nameJSON = JSONFactoryUtil.createJSONObject();
            nameJSON.put("fr_FR", category.getTitle(Locale.FRANCE));
            jsonCategory.put("name", nameJSON);
        }
        return  jsonCategory;
    }
}
