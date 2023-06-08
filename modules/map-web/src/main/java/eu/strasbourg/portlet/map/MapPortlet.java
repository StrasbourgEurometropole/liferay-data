package eu.strasbourg.portlet.map;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetCategoryModel;
import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.asset.kernel.service.AssetCategoryLocalServiceUtil;
import com.liferay.asset.kernel.service.AssetVocabularyLocalServiceUtil;
import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.module.configuration.ConfigurationException;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.SessionParamUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import eu.strasbourg.portlet.map.configuration.MapConfiguration;
import eu.strasbourg.service.interest.model.Interest;
import eu.strasbourg.service.interest.model.InterestModel;
import eu.strasbourg.service.interest.service.InterestLocalServiceUtil;
import eu.strasbourg.service.oidc.model.PublikUser;
import eu.strasbourg.service.oidc.service.PublikUserLocalServiceUtil;
import eu.strasbourg.service.opendata.geo.city.OpenDataGeoCityService;
import eu.strasbourg.service.opendata.geo.district.OpenDataGeoDistrictService;
import eu.strasbourg.utils.AssetVocabularyHelper;
import eu.strasbourg.utils.PortletHelper;
import eu.strasbourg.utils.PublikApiClient;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import eu.strasbourg.utils.constants.VocabularyNames;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author romain.vergnais
 */
@Component(
	immediate = true, 
	property = {
		"com.liferay.portlet.display-category=Strasbourg",
        "com.liferay.portlet.instanceable=true", "javax.portlet.display-name=Cartes / Autour de moi",
        "javax.portlet.init-param.add-process-action-success-action=false", "javax.portlet.init-param.template-path=/",
        "javax.portlet.init-param.view-template=/map-view.jsp",
        "javax.portlet.init-param.config-template=/map-configuration.jsp",
        "javax.portlet.name=" + StrasbourgPortletKeys.MAP_WEB, "javax.portlet.resource-bundle=content.Language",
        "javax.portlet.security-role-ref=power-user,user"
	}, 
	service = Portlet.class
)
public class MapPortlet extends MVCPortlet {


    @Override
    public void render(RenderRequest request, RenderResponse renderResponse) throws IOException, PortletException {

        try {
            ThemeDisplay  themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);

            String configId = ParamUtil.getString(request, "configId");

            // Récupération de la configuration
            MapConfiguration configuration = themeDisplay.getPortletDisplay()
                    .getPortletInstanceConfiguration(MapConfiguration.class);

            // Récupération du publik ID avec la session
            String internalId = getPublikID(request);

            String address = "";
            String city = "";
            String zipCode = "";
            if (Validator.isNotNull(internalId)) {
                JSONObject userDetail = PublikApiClient.getUserDetails(internalId);
                if (Validator.isNotNull(userDetail.get("address"))) {
                    address = userDetail.get("address").toString();
                }
                if (Validator.isNotNull(userDetail.get("zipcode"))) {
                    zipCode = userDetail.get("zipcode").toString();
                }
                if (Validator.isNotNull(userDetail.get("city"))) {
                    city = userDetail.get("city").toString();
                }
            }

            boolean hasConfig = false; // Permet de cocher tous les POI si aucune configuration
            String mode = ""; // Mode d'affichage
            boolean widgetMod = false;
            String hierarchy = Validator.isNotNull(configuration.hierarchy())?configuration.hierarchy():"h1";; // Choix de la hiérarchie du titre
            boolean defaultConfig = configuration.defaultConfig();

            String typesContenu = ""; // Les type de contenus
            String eventExplanationText = ""; // récupération du texte à afficher pour les évènements

            String backgroundId = Validator.isNotNull(configuration.backgroundId())?configuration.backgroundId():"monstrasbourg"; // Récupération du fond de plan
            boolean showConfig = true; // Affichage de la zone de configuration
            boolean showDeleteFilter = false; // Affichage du lien de suppression des filtres
            boolean showPictos = true; // Affichage des pictos dans la zone de configuration
            boolean showList = false; // Affichage de la liste à droite
            boolean showFiltersReminder = false; // Affichage du rappel des filtres dans la liste des points d'intérêt
            boolean clippingTerritory = false; // Détourage
            String clippingCategoryId = ""; // Zone de détourage
            long groupId = configuration.groupId(); // Group du site dans lequel on doit afficher le détail du POI
            boolean openInNewTab = configuration.openInNewTab(); // Ouvertures du détail des POIS dans la même fenêtre par défaut
            String zoom = configuration.zoom(); // Zoom de la carte
            String cadrageX = configuration.cadrageX(); // Cadrage de la carte
            String cadrageY = configuration.cadrageY(); // Cadrage de la carte

            String prefilterCategoriesIds = ""; // Les préfiltres (catégories)
            String prefilterTags = ""; // Les préfiltres (tags)
            boolean districtUser = false; // Détourage du quartier de l'utilisateur

            boolean displayCheckbox = true;
            String categoriesDefaultsIdsString = ""; // Les catégories affichées coché
            String categoriesIdsString = ""; // Les id des catégories affichées non cochées
            String parentCategoriesIdsString = ""; // Les parents des catégories à afficher
            String vocabulariesIdsString = ""; // Les vocabulaires des catégories à afficher
            boolean dateField = false; // Filtre par date
            long defaultDateRange = 0; // Etendue du filtre par date
            LocalDate fromDate = LocalDate.now(); // Date de début de l'event
            LocalDate toDate = LocalDate.now(); // Date de fin de l'event

            String widgetIntro = "";
            String widgetLink = "";

            String interestsIdsString = ""; // Les id des intérêts affichés
            String interestsDefaultsIdsString = ""; // Les intérêts cochés par l'utilisateur
            boolean showFavorites = false; // Affichage des favoris par défaut

            boolean showTraffic = false; // Affichage de l'info trafic
            String trafficCategoryId = ""; // Liaison de l'affichage de l'info trafic à une catégorie
            String trafficInterestId = ""; // Liaison de l'affichage de l'info trafic à un CI

            JSONObject coordinatesZone = JSONFactoryUtil.createJSONObject(); // détourage d'une commune ou d'un quartier (utile pour le détourage et le filtrage d'un quartier)
            Map<String[], List<AssetCategory>> categoriesVocabularies = new HashMap<>();
            AssetCategory district = null;
            List<Interest> interests = new ArrayList<>(); // Les intérêts actifs

            // Est-ce que la config du portlet est défini ?
            if (configuration.hasConfig()) {
                hasConfig = true;
                mode = configuration.mode();

                // Chargement de la configuration globale pour le mode widget
                if (configuration.widgetMod()) {
                    ExpandoBridge ed = themeDisplay.getScopeGroup().getExpandoBridge();
                    widgetMod = true;
                    try {
                        String globalConfig = GetterUtil.getString(ed.getAttribute("map_global_config"));
                        JSONObject json = JSONFactoryUtil.createJSONObject(globalConfig);
                        JSONArray jsonArray = json.getJSONArray("typesContenu");
                        typesContenu = jsonArray.join(",").replace("\"", "");

                        showConfig = false;
                        showPictos = false;

                        widgetIntro = configuration.widgetIntro();
                        widgetLink = configuration.widgetLink();

                        JSONArray jsonArrayInterests = json.getJSONArray("interestsIds");
                        interestsIdsString = jsonArrayInterests.join(",");
                        showFavorites = json.getBoolean("showFavorites");

                        trafficInterestId = json.getString("trafficInterestId");
                    } catch (Exception ex) {
                        _log.error("Problème à la récupération des données de map_global_config", ex);
                    }
                }
                // Chargement de la configuration du portlet sinon
                else {

                    typesContenu = configuration.typesContenu();
                    if(typesContenu.contains("eu.strasbourg.service.agenda.model.Event")) {
                        Map<Locale, String> mapText = LocalizationUtil.getLocalizationMap(configuration.eventExplanationXML());
                        for (Map.Entry<Locale, String> map : mapText.entrySet()) {
                            if (themeDisplay.getLocale().toString().equals(map.getKey().toString())) {
                                eventExplanationText = HtmlUtil.unescape(map.getValue());
                                break;
                            }
                        }
                    }

                    showConfig = configuration.showConfig();
                    if(showConfig) {
                        showDeleteFilter = configuration.showDeleteFilter();
                        showPictos = configuration.showPictos();
                    }
                    showList = configuration.showList();
                    if(showConfig && showList)
                        showFiltersReminder = configuration.showFiltersReminder();
                    if(mode.equals("normal")) {
                        clippingTerritory = configuration.clippingTerritory();
                        if(clippingTerritory) {
                            clippingCategoryId = configuration.clippingCategoryId();
                            // Récupération de la zone de détourage
                            if(Validator.isNotNull(clippingCategoryId)) {
                                AssetCategory clipping = AssetCategoryLocalServiceUtil.getCategory(Long.parseLong(clippingCategoryId));
                                String sigId = AssetVocabularyHelper.getExternalId(clipping);
                                if (sigId.startsWith("SQ_")) {
                                    coordinatesZone = openDataGeoDistrictService.getCoordinatesForSigId(sigId);
                                } else {
                                    String number = sigId.split("C_")[1];
                                    coordinatesZone = openDataGeoCityService.getCoordinatesForNumCom(number);
                                }
                            }
                        }
                    }

                    if(mode.equals("normal") || mode.equals("district")) {
                        List<AssetCategory> allCategories = AssetCategoryLocalServiceUtil.getAssetCategories(-1, -1);

                        // récupération des préfiltres
                        prefilterCategoriesIds = configuration.prefilterCategoriesIds();

                        // récupération des tags
                        prefilterTags = configuration.prefilterTags();

                        if(mode.equals("district")) {
                            districtUser = configuration.districtUser();

                            if (districtUser) {
                                if (Validator.isNotNull(city) && city.toLowerCase().equals("strasbourg")) {
                                    if (Validator.isNotNull(address)) {
                                        try {
                                            district = openDataGeoDistrictService.getDistrictByAddress(address, zipCode, city);
                                        } catch (Exception e) {
                                            _log.error(e);
                                        }
                                    }
                                }
                                if (district == null) {
                                    // on récupère la quartier choisi par l'utilisateur (page mon-quartier de mon-strasbourg)
                                    HttpServletRequest servletRequest = PortalUtil.getHttpServletRequest(request);
                                    HttpServletRequest originalRequest = PortalUtil.getOriginalServletRequest(servletRequest);
                                    String districtId = ParamUtil.getString(originalRequest, "district");
                                    if (Validator.isNotNull(districtId)) {
                                        try {
                                            AssetVocabulary territoryVocabulary =
                                                    AssetVocabularyHelper.getGlobalVocabulary(VocabularyNames.TERRITORY);
                                            district = AssetVocabularyHelper.getCategoryByExternalId(territoryVocabulary, districtId);
                                        } catch (PortalException e) {
                                            _log.error(e.getMessage() + " : "+ VocabularyNames.TERRITORY);
                                        }
                                    }
                                }
                                if (district != null)
                                    coordinatesZone = openDataGeoDistrictService.getCoordinatesForSigId(AssetVocabularyHelper.getExternalId(district));
                            }
                        }

                        if(showConfig) {
                            String filterType = Validator.isNotNull(configuration.filterType())?configuration.filterType():"checkbox";
                            displayCheckbox = filterType.equals("checkbox");
                        }

                        // récupération des filtres ainsi que des catégories sélectionnées pour POIs
                        List<AssetCategory> categoriesDefaults = new ArrayList<>();
                        List<AssetCategory> categories = new ArrayList<>();
                        List<AssetCategory> parentCategories = new ArrayList<>();
                        List<AssetVocabulary> vocabularies = new ArrayList<>();
                        if(displayCheckbox) {
                            categoriesDefaultsIdsString = configuration.categoriesDefaultsIds();
                            if(showConfig)
                                categoriesIdsString = configuration.categoriesIds();

                            // récupération des catégories cochées par défaut choisies
                            if (!categoriesDefaultsIdsString.equals("")) {
                                List<Long> categoriesDefaultsIds = Arrays.stream(categoriesDefaultsIdsString.split(","))
                                        .map(i -> Long.parseLong(i.replace("\"", ""))).collect(Collectors.toList());
                                categoriesDefaults = allCategories.stream().filter(c -> categoriesDefaultsIds.contains(c.getCategoryId()))
                                        .sorted(Comparator.comparing(c2 -> c2.getTitle(themeDisplay.getLocale())))
                                        .collect(Collectors.toList());
                            }

                            // récupération des catégories affichées non cochées choisies
                            if (!categoriesIdsString.equals("")) {
                                List<Long> categoriesIds = Arrays.stream(categoriesIdsString.split(","))
                                        .map(i -> Long.parseLong(i.replace("\"", ""))).collect(Collectors.toList());
                                categories = allCategories.stream().filter(c -> categoriesIds.contains(c.getCategoryId()))
                                        .sorted(Comparator.comparing(c2 -> c2.getTitle(themeDisplay.getLocale())))
                                        .collect(Collectors.toList());
                            }
                        }else {
                            parentCategoriesIdsString = configuration.parentsCategoriesIds();
                            vocabulariesIdsString = configuration.vocabulariesIds();

                            // Récupération de toutes les catégories à affichées
                            // récupération des catégories choisies
                            if(!parentCategoriesIdsString.isEmpty()) {
                                List<Long> parentCategoriesIds = Arrays.stream(parentCategoriesIdsString.split(","))
                                        .map(i -> Long.parseLong(i.replace("\"", ""))).collect(Collectors.toList());
                                parentCategories = allCategories.stream().filter(c -> parentCategoriesIds.contains(c.getCategoryId()))
                                        .collect(Collectors.toList());
                            }

                            // récupération des catégories des vocabulaires choisis
                            if(!vocabulariesIdsString.isEmpty()){
                                for (String vocabularyId : vocabulariesIdsString.split(",")) {
                                    AssetVocabulary vocabulary = AssetVocabularyLocalServiceUtil.fetchAssetVocabulary(Long.parseLong(vocabularyId));
                                    vocabularies.add(vocabulary);
                                }
                            }
                        }

                        // récupération du quartier de la page mon-quartier de mon-strasbourg
                        // Si on ne veut que les POIs d'un quartier, on supprime le
                        // vocabulaire territoire de la liste de catégories  et on ajoute le quartier de
                        // l'utilisateur en préfiltre
                        if (districtUser) {
                            AssetVocabulary vocabulary = AssetVocabularyHelper.getGlobalVocabulary(VocabularyNames.TERRITORY);
                            if (Validator.isNotNull(vocabulary)) {
                                categoriesDefaults.removeIf(c -> c.getVocabularyId() == vocabulary.getVocabularyId());
                                categories.removeIf(c -> c.getVocabularyId() == vocabulary.getVocabularyId());
                            }
                            if (district != null) {
                                if(!prefilterCategoriesIds.isEmpty())
                                    prefilterCategoriesIds += ",";
                                prefilterCategoriesIds += district.getCategoryId();
                            }
                        }

                        // Récupération de toutes les catégories à affichées
                        // on regroupe toutes les catégories par vocabulaire pour l'affichage dans la config
                        if(displayCheckbox) {
                            List<AssetCategory> allCategoriesToCheck = categoriesDefaults;
                            if(showConfig)
                                allCategoriesToCheck.addAll(categories);
                            allCategoriesToCheck = allCategoriesToCheck.stream()
                                    .sorted(Comparator.comparing(AssetCategoryModel::getVocabularyId))
                                    .collect(Collectors.toList());
                            long oldVocabulary = -1;
                            List<AssetCategory> categoriesVocabulary = null;
                            for (AssetCategory category : allCategoriesToCheck) {
                                if (oldVocabulary != category.getVocabularyId()) {
                                    if (oldVocabulary != -1) {
                                        String[] labelVocabulary = {};
                                        AssetVocabulary vocabulary = AssetVocabularyLocalServiceUtil
                                                .fetchAssetVocabulary(oldVocabulary);
                                        if (vocabulary != null) {
                                            labelVocabulary = new String[]{"" + vocabulary.getVocabularyId(), getLabelocabulary(vocabulary,themeDisplay).toLowerCase()};
                                        }
                                        categoriesVocabularies.put(labelVocabulary, categoriesVocabulary);
                                    }
                                    categoriesVocabulary = new ArrayList<>();
                                    oldVocabulary = category.getVocabularyId();
                                }
                                categoriesVocabulary.add(category);
                            }
                            if (oldVocabulary != -1) {
                                String[] labelVocabulary = {};
                                AssetVocabulary vocabulary = AssetVocabularyLocalServiceUtil.fetchAssetVocabulary(oldVocabulary);
                                if (vocabulary != null) {
                                    labelVocabulary = new String[]{"" + vocabulary.getVocabularyId(), getLabelocabulary(vocabulary,themeDisplay).toLowerCase()};
                                }
                                categoriesVocabularies.put(labelVocabulary, categoriesVocabulary);
                            }
                        }else {
                            if(hasConfig) {
                                List<AssetCategory> categoriesVocabulary = null;
                                for (AssetCategory category : parentCategories) {
                                    String[] labelVocabulary = new String[]{"" + category.getCategoryId(), category.getTitle(themeDisplay.getLocale())};
                                    categoriesVocabulary = new ArrayList<>();
                                    categoriesVocabulary.add(category);
                                    categoriesVocabularies.put(labelVocabulary, categoriesVocabulary);
                                }
                                for (AssetVocabulary vocabulary : vocabularies) {
                                    String[] labelVocabulary = new String[]{"" + vocabulary.getVocabularyId(), getLabelocabulary(vocabulary,themeDisplay)};
                                    categoriesVocabularies.put(labelVocabulary, vocabulary.getCategories().stream().filter(AssetCategory::isRootCategory).collect(Collectors.toList()));
                                }
                            }
                        }

                        if(showConfig) {
                            dateField = configuration.dateField();
                            if (dateField) {
                                defaultDateRange = configuration.defaultDateRange();
                                if (defaultDateRange < 0) {
                                    fromDate = LocalDate.now().plusDays(defaultDateRange);
                                }else{
                                    toDate = LocalDate.now().plusDays(defaultDateRange);
                                }
                            }
                        }
                    }

                    if(mode.equals("aroundme")) {
                        interestsIdsString = configuration.interestsIds();

                        List<Long> interestIds;
                        if (!interestsIdsString.equals(""))
                            interestIds = Arrays.stream(interestsIdsString.split(","))
                                    .map(i -> Long.parseLong(i.replace("\"", ""))).collect(Collectors.toList());
                        else
                            // Si jamais aucun intérêt affiché n'est coché intentionnellement...
                            interestIds = new ArrayList<>();

                        // Récupération de tous les centres d'intérêts affiché avec le
                        // statut publié
                        interests = InterestLocalServiceUtil.getInterests(-1, -1).stream()
                                .filter(i -> i.getStatus() == 0 && interestIds.contains(i.getInterestId()))
                                .sorted(Comparator.comparing(i2 -> i2.getType().getTitle(themeDisplay.getLocale())))
                                .collect(Collectors.toList());
                        showFavorites = configuration.showFavorites();
                    }

                    showTraffic = configuration.showTraffic();
                    if(showTraffic) {
                        if (mode.equals("normal") || mode.equals("district"))
                            trafficCategoryId = configuration.linkCategoryId();
                        if (mode.equals("aroundme"))
                            trafficInterestId = configuration.linkInterestId();
                    }
                }

            } // Si pas de config on ne récupère aucunes catégories ni intérêts

            // Si l'utilisateur est connecté et qu'il a configuré le portlet
            boolean hasUserConfig = false;
            boolean hasUserConfigForPortlet = false;
            PublikUser user;
            String userConfigString = "";
            if (Validator.isNotNull(internalId)) {
                user = PublikUserLocalServiceUtil.getByPublikUserId(internalId);
                userConfigString = user.getMapConfig();
                if (Validator.isNotNull(userConfigString)) {
                    hasUserConfig = true;
                }
            }
            if (hasUserConfig) {
                JSONObject userPortletConfig = null;
                // Une config par portlet (nouvelle façon de faire)
                if (userConfigString != null && userConfigString.startsWith("[")) {
                    JSONArray userConfigs = JSONFactoryUtil.createJSONArray(userConfigString);
                    // On va rechercher le configId correspondant
                    configId = getConfigId(themeDisplay,configId);
                    for (int i = 0; i < userConfigs.length(); i++) {
                        JSONObject config = userConfigs.getJSONObject(i);
                        if (config.getString("configId").equals(configId)) {
                            userPortletConfig = config;
                            hasUserConfigForPortlet = true;
                            break;
                        }
                    }
                } else { // Ca n'a été enregistré que pour le mode widget (legacy)
                    userPortletConfig = JSONFactoryUtil.createJSONObject(userConfigString);
                }


                if (userPortletConfig != null) {
                    JSONArray jsonArrayCategories = userPortletConfig.getJSONArray("categories");
                    if (jsonArrayCategories != null) {
                        categoriesDefaultsIdsString = jsonArrayCategories.join(",");
                    }
                    JSONArray jsonArrayInterests = userPortletConfig.getJSONArray("interests");
                    if (jsonArrayInterests != null) {
                        interestsDefaultsIdsString = jsonArrayInterests.join(",");
                    }
                    showFavorites = userPortletConfig.getBoolean("showFavorites");
                }
            }

            // Si l'utilisateur n'a pas de config pour ce portlet on prend ses centres d'intérêts (s'il en a)
            if (!hasUserConfigForPortlet) {
                if (Validator.isNotNull(internalId)) {
                    String userDefautPOI = StringUtil.merge(InterestLocalServiceUtil.getByPublikUserId(internalId)
                            .stream().map(InterestModel::getInterestId).collect(Collectors.toList()), ",");
                    if (Validator.isNotNull(userDefautPOI)) {
                        interestsDefaultsIdsString += userDefautPOI;
                    }
                }
            }

            request.setAttribute("hasConfig", hasConfig);
            request.setAttribute("mode", mode);
            request.setAttribute("hierarchy", hierarchy);
            request.setAttribute("widgetMod", widgetMod);
            request.setAttribute("defaultConfig", defaultConfig);
            request.setAttribute("typesContenu", typesContenu);
            request.setAttribute("eventExplanationText", eventExplanationText);
            request.setAttribute("backgroundId", backgroundId);
            request.setAttribute("showConfig", showConfig);
            request.setAttribute("showDeleteFilter", showDeleteFilter);
            request.setAttribute("showPictos", showPictos);
            request.setAttribute("showList", showList);
            request.setAttribute("showFiltersReminder", showFiltersReminder);
            request.setAttribute("coordinatesZone", coordinatesZone);
            request.setAttribute("groupId", groupId);
            request.setAttribute("openInNewTab", openInNewTab);
            request.setAttribute("zoom", zoom);
            request.setAttribute("cadrageX", cadrageX);
            request.setAttribute("cadrageY", cadrageY);
            request.setAttribute("prefilterCategoriesIds", prefilterCategoriesIds);
            request.setAttribute("prefilterTags", prefilterTags);
            request.setAttribute("districtUser", districtUser);
            request.setAttribute("displayCheckbox", displayCheckbox);
            request.setAttribute("categoriesVocabularies", categoriesVocabularies);
            request.setAttribute("categoriesCheckedIds", categoriesDefaultsIdsString);
            request.setAttribute("dateField", dateField);
            request.setAttribute("defaultDateRange", defaultDateRange);
            request.setAttribute("fromDay", fromDate.getDayOfMonth());
            request.setAttribute("fromMonth", fromDate.getMonthValue());
            request.setAttribute("fromYear", fromDate.getYear());
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            request.setAttribute("fromDate", fromDate.format(formatter));
            request.setAttribute("toDay", toDate.getDayOfMonth());
            request.setAttribute("toMonth", toDate.getMonthValue());
            request.setAttribute("toYear", toDate.getYear());
            request.setAttribute("toDate", toDate.format(formatter));
            request.setAttribute("district", district);
            request.setAttribute("widgetIntro", widgetIntro);
            request.setAttribute("widgetLink", widgetLink);
            request.setAttribute("interestsCheckedIds", interestsDefaultsIdsString);
            request.setAttribute("interestGroups", InterestGroupDisplay.getInterestGroups(interests));
            request.setAttribute("showFavorites", showFavorites);
            request.setAttribute("showTraffic", showTraffic);
            request.setAttribute("trafficCategoryId", trafficCategoryId);
            request.setAttribute("trafficInterestId", trafficInterestId);
            request.setAttribute("globalGroupId", themeDisplay.getCompanyGroupId());
            request.setAttribute("configId",configId);

            request.setAttribute("address", address);
            request.setAttribute("zipCode", zipCode);
            request.setAttribute("city", city);
            request.setAttribute("internalId", internalId);

            // titre personnalisable en mode widget
            request.setAttribute("title", PortletHelper.getPortletTitle("auround-me", request));

            MapDisplayContext dc = new MapDisplayContext(themeDisplay);
            request.setAttribute("dc", dc);
            if (widgetMod) {
                request.setAttribute(getMVCPathAttributeName(renderResponse.getNamespace()), "/map-widget-view.jsp");
            }
        } catch (Exception e) {
            _log.error(e);
        }
        super.render(request, renderResponse);
    }

    @SuppressWarnings("unused")
    public void resetUserConfiguration(ActionRequest actionRequest, ActionResponse actionResponse)
            throws SystemException {

        try {
            String configId = ParamUtil.getString(actionRequest, "configId");
            // Récupération du publik ID avec la session
            String internalId = getPublikID(actionRequest);
            ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
            if (Validator.isNotNull(internalId)) {
                PublikUser user = PublikUserLocalServiceUtil.getByPublikUserId(internalId);
                String userConfigString = user.getMapConfig();
                JSONArray userConfig = getCurrentPortletUserConfig(userConfigString);
                userConfig = getUserConfigWithoutCurrentPortlet(userConfig, configId);
                userConfigString = userConfig.toJSONString();
                user.setMapConfig(userConfigString);
                PublikUserLocalServiceUtil.updatePublikUser(user);
            }

            // Redirection (évite double
            // requête POST si l'utilisateur actualise sa page)

            String portletName = (String) actionRequest.getAttribute(WebKeys.PORTLET_ID);
            PortletURL renderUrl = PortletURLFactoryUtil.create(actionRequest, portletName, themeDisplay.getPlid(),
                    PortletRequest.RENDER_PHASE);
            actionResponse.sendRedirect(renderUrl.toString());
        } catch (Exception e) {
            _log.error(e);
        }

    }

    /**
     * Enregistrement de la configuration utilisateur
     */
    @Override
    public void serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
            throws IOException, PortletException {
        try {
            String configId = ParamUtil.getString(resourceRequest,"configId");
            //configId=null;
            ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);
            String resourceID = resourceRequest.getResourceID();

            if (resourceID.equals("toggleInterestPoint")) {

                // Récupération du publik ID avec la session
                String internalId = getPublikID(resourceRequest);

                if (Validator.isNull(internalId)) {
                    return;
                }

                PublikUser user = PublikUserLocalServiceUtil.getByPublikUserId(internalId);
                if (Validator.isNull(user)) {
                    return;
                }

                // JSON initialisation
                JSONObject configForPortlet = createUserConfigForPorltet(resourceRequest,configId);
                JSONArray configForUser = addPortletConfigToUserConfig(user.getMapConfig(), configForPortlet);
                user.setMapConfig(configForUser.toJSONString());
                PublikUserLocalServiceUtil.updatePublikUser(user);
            }
        } catch (Exception e) {
            _log.error(e);
        }

        super.serveResource(resourceRequest, resourceResponse);
    }

    /**
     * Retourne la configuration existante du portlet à partir de la String enregistrée
     */
    private JSONArray getCurrentPortletUserConfig(String userConfigString) {
        JSONArray userConfig;
        if (Validator.isNull(userConfigString)) { // Cas où il n'y a pas encore de config utilisateur
            userConfig = JSONFactoryUtil.createJSONArray();
        } else if (userConfigString.startsWith("[")) { // Cas où la config utilisateur existe déjà
            try {
                userConfig = JSONFactoryUtil.createJSONArray(userConfigString);
            } catch (JSONException e) {
                userConfig = JSONFactoryUtil.createJSONArray();
            }
        } else { // Cas où la config utilisateur date du moment où l'unique config sauvée étant pour le widget
            userConfig = JSONFactoryUtil.createJSONArray();
            try {
                JSONObject portletConfigInOlderFormat = JSONFactoryUtil.createJSONObject(userConfigString);
                portletConfigInOlderFormat.put("configId", "widget");
                userConfig.put(portletConfigInOlderFormat);
            } catch (JSONException e) {
                _log.error(e.getMessage() + " : "+ userConfigString);
            }

        }
        return userConfig;
    }

    /**
     * Retourne le configId du portlet en cours
     */
    private String getConfigId(ThemeDisplay themeDisplay,String configId) {

        if (Validator.isNull(configId)) {
            try {

                MapConfiguration configuration = themeDisplay.getPortletDisplay()
                        .getPortletInstanceConfiguration(MapConfiguration.class);
                if (configuration.defaultConfig() || configuration.widgetMod()) {
                    configId = "widget";
                } else {
                    configId = themeDisplay.getPortletDisplay().getInstanceId();
                }
            } catch (ConfigurationException e) {
                configId = themeDisplay.getPortletDisplay().getInstanceId();
            }
        }
        return configId;
    }


    //private String configId;

    /**
     * Retire la configuration du portlet en cours à partir de la configuration userConfig
     */
    private JSONArray getUserConfigWithoutCurrentPortlet(JSONArray userConfig, String configId) {
        JSONArray userConfigWithoutCurrentPortlet = JSONFactoryUtil.createJSONArray();
        for (int i = 0; i < userConfig.length(); i++) {
            JSONObject portletConfig = userConfig.getJSONObject(i);
            String currentConfigId = portletConfig.getString("configId");
            if (!configId.equals(currentConfigId)) {
                userConfigWithoutCurrentPortlet.put(portletConfig);
            }
        }
        return userConfigWithoutCurrentPortlet;
    }

    /**
     * Ajoute la configuration du portlet en cours à la configuration utilisateur
     */
    private JSONArray addPortletConfigToUserConfig(String userConfigString, JSONObject currentPortletConfig) {
        JSONArray userConfig = getCurrentPortletUserConfig(userConfigString);
        String currentPortletConfigId = currentPortletConfig.getString("configId");
        userConfig = getUserConfigWithoutCurrentPortlet(userConfig, currentPortletConfigId);
        userConfig.put(currentPortletConfig);
        return userConfig;
    }

    /**
     * Crée la configuration du portlet en cours
     */
    private JSONObject createUserConfigForPorltet(ResourceRequest resourceRequest,String configId) {
        JSONObject configForPortlet = JSONFactoryUtil.createJSONObject();
        ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);

        // ConfigId
        configForPortlet.put("configId", configId);

        // Catégories
        JSONArray jsonArrayCategories = JSONFactoryUtil.createJSONArray();
        String[] checkboxNamesCategories = ParamUtil.getString(resourceRequest, "checkboxNamesCategories")
                .split(",");
        for (String checkboxName : checkboxNamesCategories) {
            String categoryIdString = ParamUtil.getString(resourceRequest, checkboxName);

            if (Validator.isNotNull(categoryIdString) && !categoryIdString.equals("false"))
                jsonArrayCategories.put(Long.parseLong(categoryIdString));
        }
        configForPortlet.put("categories", jsonArrayCategories);

        // CI
        JSONArray jsonArrayInterests = JSONFactoryUtil.createJSONArray();
        String[] checkboxNamesInterests = ParamUtil.getString(resourceRequest, "checkboxNamesInterests")
                .split(",");
        for (String checkboxName : checkboxNamesInterests) {
            String interestIdString = ParamUtil.getString(resourceRequest, checkboxName);

            if (Validator.isNotNull(interestIdString) && !interestIdString.equals("false"))
                jsonArrayInterests.put(Long.parseLong(interestIdString));
        }
        configForPortlet.put("interests", jsonArrayInterests);

        // Favoris
        configForPortlet.put("showFavorites", ParamUtil.getBoolean(resourceRequest, "showFavorites"));

        return configForPortlet;
    }

    private String getPublikID(PortletRequest resourceRequest) {

        LiferayPortletRequest liferayPortletRequest = PortalUtil.getLiferayPortletRequest(resourceRequest);
        HttpServletRequest originalRequest = liferayPortletRequest.getHttpServletRequest();

        return SessionParamUtil.getString(originalRequest, "publik_internal_id");
    }

    private String getLabelocabulary(AssetVocabulary vocabulary,ThemeDisplay  themeDisplay ){
        String label = vocabulary.getDescription(themeDisplay.getLocale());
        if(Validator.isNull(label))
            label = vocabulary.getTitle(themeDisplay.getLocale());
        if(Validator.isNull(label))
            label = vocabulary.getDescription(Locale.FRANCE);
        if(Validator.isNull(label))
            label = vocabulary.getTitle(Locale.FRANCE);

        return label;
    }

    private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());

    private OpenDataGeoDistrictService openDataGeoDistrictService;

    @Reference(unbind = "-")
    public void setOpenDataGeoDistrictService(OpenDataGeoDistrictService openDataGeoDistrictService) {
        this.openDataGeoDistrictService = openDataGeoDistrictService;
    }

    private OpenDataGeoCityService openDataGeoCityService;

    @Reference(unbind = "-")
    public void setOpenDataGeoCityService(OpenDataGeoCityService openDataGeoCityService) {
        this.openDataGeoCityService = openDataGeoCityService;
    }
}