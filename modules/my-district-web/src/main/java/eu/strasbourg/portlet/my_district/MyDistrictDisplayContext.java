package eu.strasbourg.portlet.my_district;

import com.liferay.asset.entry.rel.model.AssetEntryAssetCategoryRel;
import com.liferay.asset.entry.rel.service.AssetEntryAssetCategoryRelLocalServiceUtil;
import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
import com.liferay.journal.model.JournalArticle;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.module.configuration.ConfigurationException;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchContextFactory;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.SessionParamUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import eu.strasbourg.portlet.my_district.configuration.MyDistrictConfiguration;
import eu.strasbourg.service.adict.AdictService;
import eu.strasbourg.service.agenda.model.Event;
import eu.strasbourg.service.agenda.service.EventLocalServiceUtil;
import eu.strasbourg.service.official.model.Official;
import eu.strasbourg.service.official.service.OfficialLocalServiceUtil;
import eu.strasbourg.service.opendata.geo.district.OpenDataGeoDistrictService;
import eu.strasbourg.service.place.model.Place;
import eu.strasbourg.service.place.service.PlaceLocalServiceUtil;
import eu.strasbourg.utils.*;
import eu.strasbourg.utils.constants.VocabularyNames;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.PortletRequest;
import javax.portlet.RenderRequest;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class MyDistrictDisplayContext {

    private ThemeDisplay themeDisplay;
    private RenderRequest request;
    private MyDistrictConfiguration configuration;
    private Boolean hasError;
    private String address;
    private String zipCode;
    private String city;
    private Boolean isStrasbourg = false;
    private AssetCategory district;
    private AdictService adictService;
    private List<AssetEntry> actuAndWebMag;
    private List<AssetEntry> events;

    public MyDistrictDisplayContext(ThemeDisplay themeDisplay, RenderRequest request, AdictService adict, OpenDataGeoDistrictService openDataGeoDistrictService) {
        this.themeDisplay = themeDisplay;
        this.request = request;
        this.adictService = adict;
        this.openDataGeoDistrictService = openDataGeoDistrictService;
        try {
            this.configuration = themeDisplay.getPortletDisplay()
                    .getPortletInstanceConfiguration(MyDistrictConfiguration.class);
        } catch (ConfigurationException e) {
            _log.error(e.getMessage(), e);
        }
    }

    public MyDistrictConfiguration getConfiguration() {
        return configuration;
    }

    public String getVirtualHostName() {
        return themeDisplay.getScopeGroup().getPublicLayoutSet().getVirtualHostname();
    }

    public String getVirtualStrasbourgHostName() {
        Group group = GroupLocalServiceUtil.fetchFriendlyURLGroup(this.themeDisplay.getCompanyId(), "/strasbourg.eu");
        return group.getPublicLayoutSet().getVirtualHostname();
    }

    // Récupération de l'id utilisateur
    private String getPublikID(PortletRequest resourceRequest) {

        LiferayPortletRequest liferayPortletRequest = PortalUtil.getLiferayPortletRequest(resourceRequest);
        HttpServletRequest originalRequest = liferayPortletRequest.getHttpServletRequest();

        return SessionParamUtil.getString(originalRequest, "publik_internal_id");
    }

    // vérifie si l'adresse est sur Strasbourg ou pas
    public boolean isStrasbourg(){
        return isStrasbourg;
    }


    // récupération de l'adresse de l'utilisateureventservicelocal
    public String getAddress() {
        if(Validator.isNotNull(address) && Validator.isNotNull(zipCode) && Validator.isNotNull(city))
            return address + " " + zipCode + " " + city;
        return null;
    }

    // récupération du texte à afficher si l'utilisateur n'a pas renseigné son
    // adresse
    public String getNoAddressText() {
        String noAddress = "";
        Map<Locale, String> mapText = LocalizationUtil.getLocalizationMap(configuration.noAddressXML());
        for (Map.Entry<Locale, String> map : mapText.entrySet()) {
            if (themeDisplay.getLocale().toString().equals(map.getKey().toString())) {
                noAddress = HtmlUtil.unescape(map.getValue());
                break;
            }
        }
        if (Validator.isNull(noAddress)) {
            noAddress = "No configuration";
        }
        return noAddress;
    }

    // récupération de la catégorie "quartier" de l'utilisateur
    public AssetCategory getDistrict() {
        hasError = false;

        // Récupération de l'adresse
        String internalId = getPublikID(request);
        if (Validator.isNotNull(internalId)) {
            JSONObject userDetail = PublikApiClient.getUserDetails(internalId);
            if (Validator.isNotNull(userDetail.get("address")))
                address = userDetail.get("address").toString();
            if (Validator.isNotNull(userDetail.get("zipcode")))
                zipCode = userDetail.get("zipcode").toString();
            if (Validator.isNotNull(userDetail.get("city")))
                city = userDetail.get("city").toString();
            if(userDetail.get("city").toString().toLowerCase().equals("strasbourg"))
                isStrasbourg = true;
        }

        if (isStrasbourg && district == null) {
            try {
                district = openDataGeoDistrictService.getDistrictByAddress(address, zipCode, city);
            } catch (Exception e) {
                _log.error(e.getMessage() + " : address -> " + address + ", zipCode -> " + zipCode + ", city -> " + city);
                hasError = true;
            }
        }
        if (!isStrasbourg ||district == null) {
            HttpServletRequest servletRequest = PortalUtil.getHttpServletRequest(request);
            HttpServletRequest originalRequest = PortalUtil.getOriginalServletRequest(servletRequest);
            String districtId = ParamUtil.getString(originalRequest, "district");
            if (Validator.isNotNull(districtId)) {
                try {
                    AssetVocabulary territoryVocabulary = AssetVocabularyHelper
                            .getGlobalVocabulary(VocabularyNames.TERRITORY);
                    district = AssetVocabularyHelper.getCategoryByExternalId(territoryVocabulary, districtId);
                } catch (PortalException e) {
                    _log.error(e.getMessage());
                }
            }
        }
        return district;
    }

    // vérifi si il y a eu une erreur au niveau d'un webService
    public Boolean hasError() {
        return hasError;
    }

    // récupération de l'adjoint de quartier
    public Official getOfficial() {
        Official official = null;
        if (Validator.isNotNull(district)) {
            List<AssetEntryAssetCategoryRel> entriesRel = AssetEntryAssetCategoryRelLocalServiceUtil.getAssetEntryAssetCategoryRelsByAssetCategoryId(district.getCategoryId());

            //transforme les AssetEntriesAssetCategories en AssetEntries
            List<AssetEntry> assetEntries = new ArrayList<AssetEntry>();
            for (AssetEntryAssetCategoryRel entryRel : entriesRel) {
                if (Validator.isNotNull(entryRel)) {
                    try {
                        assetEntries.add(AssetEntryLocalServiceUtil.getAssetEntry(entryRel.getAssetEntryId()));
                    } catch (PortalException e) {
                        _log.error(e.getMessage());
                    }
                }
            }
            // ne garde que les élus
            assetEntries.removeIf(e -> !(e.getClassName().equals(Official.class.getName())));
            // ne garde que s'il est publié
            assetEntries.removeIf(e -> !e.getVisible());
            if (!assetEntries.isEmpty()) {
                official = OfficialLocalServiceUtil.fetchOfficial(assetEntries.get(0).getClassPK());
            }
        }
        return official;
    }

    // récupération du texte à afficher pour la mairie de proximité
    public String getTownHallText() {
        String townHall = "";
        Map<Locale, String> mapText = LocalizationUtil.getLocalizationMap(configuration.townHallXML());
        for (Map.Entry<Locale, String> map : mapText.entrySet()) {
            if (themeDisplay.getLocale().toString().equals(map.getKey().toString())) {
                townHall = HtmlUtil.unescape(map.getValue());
                break;
            }
        }
        if (Validator.isNull(townHall)) {
            townHall = "No configuration";
        }
        return townHall;
    }

    // récupération de la mairie de proximité
    public Place getTownHall() {
        Place townHall = null;
        if (Validator.isNotNull(district)) {
            List<AssetEntryAssetCategoryRel> entriesRel = AssetEntryAssetCategoryRelLocalServiceUtil.getAssetEntryAssetCategoryRelsByAssetCategoryId(district.getCategoryId());

            //transforme les AssetEntriesAssetCategories en AssetEntries
            List<AssetEntry> assetEntries = new ArrayList<AssetEntry>();
            for (AssetEntryAssetCategoryRel entryRel : entriesRel) {
                if (Validator.isNotNull(entryRel)) {
                    try {
                        assetEntries.add(AssetEntryLocalServiceUtil.getAssetEntry(entryRel.getAssetEntryId()));
                    } catch (PortalException e) {
                        _log.error(e.getMessage() + " : " + entryRel);
                    }
                }
            }
            // ne garde que les lieux
            assetEntries.removeIf(e -> !(e.getClassName().equals(Place.class.getName())));
            // ne garde que les mairies
            AssetVocabulary placeTypeVocabulary;
            try {
                placeTypeVocabulary = AssetVocabularyHelper.getGlobalVocabulary(VocabularyNames.PLACE_TYPE);
                AssetCategory townHallCategory = AssetVocabularyHelper.getCategoryByExternalId(placeTypeVocabulary,
                        "Cat_12_07");
                assetEntries.removeIf(e -> !(e.getCategories().contains(townHallCategory)));
                // ne garde que s'il est publié
                assetEntries.removeIf(e -> !e.getVisible());
            } catch (PortalException e1) {
                _log.error(e1.getMessage());
            }
            if (!assetEntries.isEmpty()) {
                townHall = PlaceLocalServiceUtil.fetchPlace(assetEntries.get(0).getClassPK());
            }
        }
        return townHall;
    }

    // récupération du texte à afficher pour la direction de territoire
    public String getTerritoryDirectionText() {
        String territoryDirection = "";
        Map<Locale, String> mapText = LocalizationUtil.getLocalizationMap(configuration.territoryDirectionXML());
        for (Map.Entry<Locale, String> map : mapText.entrySet()) {
            if (themeDisplay.getLocale().toString().equals(map.getKey().toString())) {
                territoryDirection = HtmlUtil.unescape(map.getValue());
                break;
            }
        }
        if (Validator.isNull(territoryDirection)) {
            territoryDirection = "No configuration";
        }
        return territoryDirection;
    }

    // récupération de la direction de territoire
    public Place getTerritoryDirection() {
        Place territoryDirection = null;
        if (Validator.isNotNull(district)) {
            List<AssetEntryAssetCategoryRel> entriesRel = AssetEntryAssetCategoryRelLocalServiceUtil.getAssetEntryAssetCategoryRelsByAssetCategoryId(district.getCategoryId());

            //transforme les AssetEntriesAssetCategories en AssetEntries
            List<AssetEntry> assetEntries = new ArrayList<AssetEntry>();
            for (AssetEntryAssetCategoryRel entryRel : entriesRel) {
                if (Validator.isNotNull(entryRel)) {
                    try {
                        assetEntries.add(AssetEntryLocalServiceUtil.getAssetEntry(entryRel.getAssetEntryId()));
                    } catch (PortalException e) {
                        _log.error(e.getMessage() + " : " + entryRel);
                    }
                }
            }
            // ne garde que les lieux
            assetEntries.removeIf(e -> !(e.getClassName().equals(Place.class.getName())));
            // ne garde que les direction de territoire
            AssetVocabulary placeTypeVocabulary;
            try {
                placeTypeVocabulary = AssetVocabularyHelper.getGlobalVocabulary(VocabularyNames.PLACE_TYPE);
                AssetCategory territoryDirectionCategory = AssetVocabularyHelper
                        .getCategoryByExternalId(placeTypeVocabulary, "Cat_12_05");
                assetEntries.removeIf(e -> !(e.getCategories().contains(territoryDirectionCategory)));
                // ne garde que s'il est publié
                assetEntries.removeIf(e -> !e.getVisible());
            } catch (PortalException e1) {
                _log.error(e1.getMessage());
            }
            if (!assetEntries.isEmpty()) {
                territoryDirection = PlaceLocalServiceUtil.fetchPlace(assetEntries.get(0).getClassPK());
            }
        }
        return territoryDirection;
    }

    // récupération des écoles du secteur
    public List<Place> getSectorSchools() {
        hasError = false;
        List<Place> sectorSchools = new ArrayList<Place>();
        if(isStrasbourg) {
            try {
                List<String> sigIds = adictService.getSchoolsByAddress(address +" " + zipCode + " " + city);
                for (String sigId : sigIds) {
                    Place place = PlaceLocalServiceUtil.getPlaceBySIGId(sigId);
                    if (place != null && place.getStatus() == WorkflowConstants.STATUS_APPROVED) {
                        sectorSchools.add(place);
                    }
                }
            } catch (Exception e) {
                _log.info(e.getMessage() + " : address -> " + address + ", zipCode -> " + zipCode + ", city -> " + city);
                hasError = true;
            }
        }
        return sectorSchools;
    }

    // récupération des actus et webmag
    public List<AssetEntry> getActusAndWebmags() {
        if (actuAndWebMag == null && district != null) {
            List<AssetEntry> actuWebmag = new ArrayList<AssetEntry>();

            // Search context
            HttpServletRequest servletRequest = PortalUtil.getHttpServletRequest(request);
            SearchContext searchContext = SearchContextFactory.getInstance(servletRequest);

            // ClassNames
            String[] classNames = new String[1];
            classNames[0] = JournalArticle.class.getName();

            // GroupId
            Group group = GroupLocalServiceUtil.fetchFriendlyURLGroup(this.themeDisplay.getCompanyId(),
                    "/strasbourg.eu");
            long groupId = group.getGroupId();

            // Catégories de la recherche utilisateur (non existantes pour ce
            // portlet)
            List<Long[]> categoriesRechercheIds = new ArrayList<Long[]>();
            Long[] categId = new Long[1];
            categId[0] = district.getCategoryId();
            categoriesRechercheIds.add(categId);

            // Recherche
            Hits hits = SearchHelper.getGlobalSearchHits(searchContext, classNames, groupId,
                    this.themeDisplay.getCompanyGroupId(), true, "", false, "", null, null, categoriesRechercheIds,
                    new ArrayList<Long[]>(), StringUtil.split("actu,webmag"), false, this.themeDisplay.getLocale(), 0,
                    12, "modified_sortable", true);

            // On renvoie la liste des actualités classés par date de
            // publication
            for (Document document : hits.getDocs()) {
                AssetEntry entry = null;
                entry = AssetEntryLocalServiceUtil.fetchEntry(JournalArticle.class.getName(),
                        GetterUtil.getLong(document.get(Field.ENTRY_CLASS_PK)));
                if (entry != null) {
                    actuWebmag.add(entry);
                }
            }
            actuAndWebMag = actuWebmag;
        }

        return actuAndWebMag;
    }

    public String getJournalArticleTitle(JournalArticle article, Locale locale) {
        return JournalArticleHelper.getJournalArticleFieldValue(article, "title", locale);
    }

    public String getJournalArticleCatcher(JournalArticle article, Locale locale) {
        return JournalArticleHelper.getJournalArticleFieldValue(article, "chapo", locale);
    }

    public String getJournalArticleImage(JournalArticle article, Locale locale) {
        String documentStructure = JournalArticleHelper.getJournalArticleFieldValue(article, "thumbnail", locale);
        return AssetPublisherTemplateHelper.getDocumentUrl(documentStructure);
    }

    public String getJSONEncodedString(String source) {
        return HtmlUtil.escapeJS(source);
    }

    public boolean isMag(String[] tagNames) {
        if (Arrays.toString(tagNames).contains("euromag") || Arrays.toString(tagNames).contains("villemag")
                || Arrays.toString(tagNames).contains("webmag"))
            return true;
        else
            return false;
    }

    // récupération des évènements
    public List<AssetEntry> getEvents() {
        if (events == null) {
            List<AssetEntryAssetCategoryRel> entriesRel = new ArrayList<AssetEntryAssetCategoryRel>();
            entriesRel.addAll(AssetEntryAssetCategoryRelLocalServiceUtil.getAssetEntryAssetCategoryRelsByAssetCategoryId(district.getCategoryId()));

            //transforme les AssetEntriesAssetCategories en AssetEntries
            List<AssetEntry> entries = new ArrayList<>();
            for (AssetEntryAssetCategoryRel entryRel : entriesRel) {
                if (Validator.isNotNull(entryRel)) {
                    try {
                        entries.add(AssetEntryLocalServiceUtil.getAssetEntry(entryRel.getAssetEntryId()));
                    } catch (PortalException e) {
                        _log.error(e.getMessage() + " : " + entryRel);
                    }
                }
            }

            List<Long> classPks = entries.stream().map(AssetEntry::getClassPK).collect(Collectors.toList());
            List<Event> listEvent = EventLocalServiceUtil.findByNextHappening();
            listEvent = listEvent.stream().filter(e -> classPks.contains(e.getEventId())).collect(Collectors.toList());

            events = new ArrayList<AssetEntry>();
            for (Event event: listEvent) {
                if(events.size() < 12)
                    events.add(entries.stream().filter(a -> a.getClassPK() == event.getEventId()).collect(Collectors.toList()).get(0));
                else
                    break;
            }
        }
        return events;
    }

    public String DeleteTag(String html) {

        Pattern p = Pattern.compile("<[^>]*>");
        Matcher m = p.matcher(html);
        return m.replaceAll("");
    }

    private int getDaysBetweenTodayAndPublicationDate(AssetEntry entry) {
        LocalDate today = LocalDate.now();
        LocalDate publicationDate = entry.getPublishDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return (int) Math.abs(ChronoUnit.DAYS.between(today, publicationDate));
    }

    /**
     * Retourne les quartiers de strasbourg
     */
    public List<String[]> getAllDistricts() {
        List<String[]> districts = new ArrayList<>();
        for (AssetCategory categ : AssetVocabularyHelper.getAllDistrictsFromCity("Strasbourg")) {
            String SIGId = AssetVocabularyHelper.getCategoryProperty(categ.getCategoryId(), "SIG");
            String[] district = {categ.getTitleCurrentValue(), SIGId};
            districts.add(district);
        }
        return districts;
    }

    private OpenDataGeoDistrictService openDataGeoDistrictService;

    @Reference(unbind = "-")
    public void setOpenDataGeoDistrictService(OpenDataGeoDistrictService openDataGeoDistrictService) {
        this.openDataGeoDistrictService = openDataGeoDistrictService;
    }

    private static final Log _log = LogFactoryUtil.getLog(MyDistrictDisplayContext.class.getName());
}