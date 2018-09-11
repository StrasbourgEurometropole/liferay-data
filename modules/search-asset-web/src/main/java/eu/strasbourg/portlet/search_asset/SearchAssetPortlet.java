package eu.strasbourg.portlet.search_asset;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.service.JournalArticleLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.search.*;
import com.liferay.portal.kernel.service.LayoutLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.*;
import com.liferay.portal.kernel.xml.SAXReaderUtil;
import eu.strasbourg.portlet.search_asset.action.ExportPDF;
import eu.strasbourg.portlet.search_asset.configuration.SearchAssetConfiguration;
import eu.strasbourg.portlet.search_asset.display.context.SearchAssetDisplayContext;
import eu.strasbourg.service.agenda.model.Event;
import eu.strasbourg.service.agenda.service.EventLocalServiceUtil;
import eu.strasbourg.service.project.model.Participation;
import eu.strasbourg.service.project.model.Petition;
import eu.strasbourg.service.project.model.Project;
import eu.strasbourg.service.project.service.ParticipationLocalServiceUtil;
import eu.strasbourg.service.project.service.PetitionLocalServiceUtil;
import eu.strasbourg.service.project.service.ProjectLocalServiceUtil;
import eu.strasbourg.service.video.model.Video;
import eu.strasbourg.service.video.service.VideoLocalServiceUtil;
import eu.strasbourg.utils.AssetVocabularyHelper;
import eu.strasbourg.utils.JSONHelper;
import eu.strasbourg.utils.LayoutHelper;
import eu.strasbourg.utils.SearchHelper;
import org.osgi.service.component.annotations.Component;

import javax.portlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

@Component(immediate = true, configurationPid = "eu.strasbourg.portlet.page_header.configuration.PageHeaderConfiguration", property = {
        "com.liferay.portlet.display-category=Strasbourg",
        "com.liferay.portlet.instanceable=false",
        "com.liferay.portlet.css-class-wrapper=search-asset-portlet",
        "com.liferay.portlet.single-page-application=false",
        "javax.portlet.init-param.template-path=/",
        "javax.portlet.init-param.view-template=/search-asset-view.jsp",
        "javax.portlet.init-param.check-auth-token=false",
        "javax.portlet.init-param.config-template=/search-asset-configuration.jsp",
        "javax.portlet.resource-bundle=content.Language",
        "javax.portlet.security-role-ref=power-user,user"}, service = Portlet.class)
public class SearchAssetPortlet extends MVCPortlet {

    @Override
    public void render(RenderRequest renderRequest,
                       RenderResponse renderResponse)
            throws IOException, PortletException {
        try {
            ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest
                    .getAttribute(WebKeys.THEME_DISPLAY);
            SearchAssetConfiguration configuration = themeDisplay
                    .getPortletDisplay().getPortletInstanceConfiguration(
                            SearchAssetConfiguration.class);
            getClassNames();

            // On set le DisplayContext
            SearchAssetDisplayContext dc = new SearchAssetDisplayContext(
                    renderRequest, renderResponse);
            renderRequest.setAttribute("dc", dc);


            // Boolean pour dire qu'on vient du portlet de recherche et non d'un
            // asset publisher (pour être utilisé dans les ADT si besoin
            renderRequest.setAttribute("fromSearchPortlet", true);

            // On envoie a la jsp la map className / layout qui fait
            // correspondre à chaque type d'asset une page de détail
            int i = 0;
            Map<String, Long> className_layoutId = new HashMap<String, Long>();
            for (String className :  configuration.assetClassNames()
                    .split(",")) {
                String layoutFriendlyURL =  configuration.layoutsFriendlyURLs()
                        .split(",")[i];
                Layout layout = LayoutLocalServiceUtil.fetchLayoutByFriendlyURL(
                        themeDisplay.getScopeGroupId(), false,
                        layoutFriendlyURL);
                if (layout != null) {
                    className_layoutId.put(className, layout.getPlid());
                }
                i++;
            }

            renderRequest.setAttribute("classNameLayoutId", className_layoutId);

            // Recuperation de l'URL de "base" du site
            String homeURL = "/";
            if (themeDisplay.getScopeGroup().getPublicLayoutSet().getVirtualHostname().isEmpty() || themeDisplay.getScopeGroup().isStagingGroup()) {
                homeURL = "/web" + themeDisplay.getLayout().getGroup().getFriendlyURL() + "/";
            }
            renderRequest.setAttribute("homeURL", homeURL);

            super.render(renderRequest, renderResponse);
        } catch (Exception e) {
            _log.error(e);
        }
    }

    /**
     * L'utilisateur a fait une recherche, on en profite pour set un attribut
     */
    @Override
    public void processAction(ActionRequest actionRequest,
                              ActionResponse actionResponse)
            throws IOException, PortletException {

        actionRequest.setAttribute("userSearch", true);

        super.processAction(actionRequest, actionResponse);
    }

    @Override
    public void serveResource(ResourceRequest resourceRequest,
                              ResourceResponse resourceResponse)
            throws IOException, PortletException {

        try {
            this._themeDisplay = (ThemeDisplay) resourceRequest
                    .getAttribute(WebKeys.THEME_DISPLAY);
            this._configuration = this._themeDisplay
                    .getPortletDisplay().getPortletInstanceConfiguration(
                            SearchAssetConfiguration.class);
            this._request = resourceRequest;
            this._response = resourceResponse;

            String resourceID = resourceRequest.getResourceID();

            // Verifions qu'il n'y ait pas d'entourloupe dans la solicitation
            // et réaction au type de la demande
            if (resourceID != null && resourceID.startsWith("entrySelection")) { // Nouvelle sélection de videos

                if (resourceID.equals("entrySelectionVideo")) {
                    this._keywords = ParamUtil.getString(resourceRequest, "selectedKeyWords");
                    this._startDay = ParamUtil.getInteger(resourceRequest, "selectedStartDay");
                    this._startMonth = ParamUtil.getString(resourceRequest, "selectedStartMonth");
                    this._startYear = ParamUtil.getInteger(resourceRequest, "selectedStartYear");
                    this._endDay = ParamUtil.getInteger(resourceRequest, "selectedEndDay");
                    this._endMonth = ParamUtil.getString(resourceRequest, "selectedEndMonth");
                    this._endYear = ParamUtil.getInteger(resourceRequest, "selectedEndYear");
                    this._states = new long[]{};
                    this._statuts = new long[]{};
                    this._projects = ParamUtil.getLongValues(resourceRequest, "selectedProject");
                    this._districts = ParamUtil.getLongValues(resourceRequest, "selectedDistricts");
                    this._thematics = ParamUtil.getLongValues(resourceRequest, "selectedThematics");
                    this._types = new long[]{};
                    this._sortFieldAndType = ParamUtil.getString(resourceRequest, "sortFieldAndType");
                }

                if (resourceID.equals("entrySelectionProject")) {
                    this._keywords = ParamUtil.getString(resourceRequest, "selectedKeyWords");
                    this._startDay = -1;
                    this._startMonth = null;
                    this._startYear = -1;
                    this._endDay = -1;
                    this._endMonth = null;
                    this._endYear = -1;
                    this._states = new long[]{};
                    this._statuts = ParamUtil.getLongValues(resourceRequest, "selectedStatut");
                    this._projects = new long[]{};
                    this._districts = ParamUtil.getLongValues(resourceRequest, "selectedDistricts");
                    this._thematics = ParamUtil.getLongValues(resourceRequest, "selectedThematics");
                    this._types = new long[]{};
                    this._sortFieldAndType = ParamUtil.getString(resourceRequest, "sortFieldAndType");
                }

                if (resourceID.equals("entrySelectionParticipation")) {
                    this._keywords = ParamUtil.getString(resourceRequest, "selectedKeyWords");
                    this._startDay = ParamUtil.getInteger(resourceRequest, "selectedStartDay");
                    this._startMonth = ParamUtil.getString(resourceRequest, "selectedStartMonth");
                    this._startYear = ParamUtil.getInteger(resourceRequest, "selectedStartYear");
                    this._endDay = ParamUtil.getInteger(resourceRequest, "selectedEndDay");
                    this._endMonth = ParamUtil.getString(resourceRequest, "selectedEndMonth");
                    this._endYear = ParamUtil.getInteger(resourceRequest, "selectedEndYear");
                    this._states = ParamUtil.getLongValues(resourceRequest, "selectedStates");
                    this._statuts = new long[]{};
                    this._projects = new long[]{};
                    this._districts = ParamUtil.getLongValues(resourceRequest, "selectedDistricts");
                    this._thematics = ParamUtil.getLongValues(resourceRequest, "selectedThematics");
                    this._types = ParamUtil.getLongValues(resourceRequest, "selectedTypes");
                    this._sortFieldAndType = ParamUtil.getString(resourceRequest, "sortFieldAndType");
                }

                if (resourceID.equals("entrySelectionAgenda")) {
                    this._keywords = ParamUtil.getString(resourceRequest, "selectedKeyWords");
                    this._startDay = ParamUtil.getInteger(resourceRequest, "selectedStartDay");
                    this._startMonth = ParamUtil.getString(resourceRequest, "selectedStartMonth");
                    this._startYear = ParamUtil.getInteger(resourceRequest, "selectedStartYear");
                    this._endDay = ParamUtil.getInteger(resourceRequest, "selectedEndDay");
                    this._endMonth = ParamUtil.getString(resourceRequest, "selectedEndMonth");
                    this._endYear = ParamUtil.getInteger(resourceRequest, "selectedEndYear");
                    this._states = new long[]{};
                    this._statuts = new long[]{};
                    this._projects = ParamUtil.getLongValues(resourceRequest, "selectedProject");
                    this._districts = ParamUtil.getLongValues(resourceRequest, "selectedDistricts");
                    this._thematics = ParamUtil.getLongValues(resourceRequest, "selectedThematics");
                    this._types = new long[]{};
                    this._sortFieldAndType = ParamUtil.getString(resourceRequest, "sortFieldAndType");
                }

                if (resourceID.equals("entrySelectionPetition")) {
                    this._keywords = ParamUtil.getString(resourceRequest, "selectedKeyWords");
                    this._startDay = ParamUtil.getInteger(resourceRequest, "selectedStartDay");
                    this._startMonth = ParamUtil.getString(resourceRequest, "selectedStartMonth");
                    this._startYear = ParamUtil.getInteger(resourceRequest, "selectedStartYear");
                    this._endDay = ParamUtil.getInteger(resourceRequest, "selectedEndDay");
                    this._endMonth = ParamUtil.getString(resourceRequest, "selectedEndMonth");
                    this._endYear = ParamUtil.getInteger(resourceRequest, "selectedEndYear");
                    this._states = ParamUtil.getLongValues(resourceRequest, "selectedStates");
                    this._statuts = new long[]{};
                    this._projects = new long[]{};
                    this._districts = ParamUtil.getLongValues(resourceRequest, "selectedDistricts");
                    this._thematics = ParamUtil.getLongValues(resourceRequest, "selectedThematics");
                    this._types = new long[]{};
                    this._sortFieldAndType = ParamUtil.getString(resourceRequest, "sortFieldAndType");
                }

                if (resourceID.equals("entrySelectionNews")) {
                    this._keywords = null;
                    this._startDay = ParamUtil.getInteger(resourceRequest, "selectedStartDay");
                    this._startMonth = ParamUtil.getString(resourceRequest, "selectedStartMonth");
                    this._startYear = ParamUtil.getInteger(resourceRequest, "selectedStartYear");
                    this._endDay = ParamUtil.getInteger(resourceRequest, "selectedEndDay");
                    this._endMonth = ParamUtil.getString(resourceRequest, "selectedEndMonth");
                    this._endYear = ParamUtil.getInteger(resourceRequest, "selectedEndYear");
                    this._states = ParamUtil.getLongValues(resourceRequest, "selectedStates");
                    this._statuts = new long[]{};
                    this._projects = new long[]{};
                    this._districts = ParamUtil.getLongValues(resourceRequest, "selectedDistricts");
                    this._thematics = ParamUtil.getLongValues(resourceRequest, "selectedThematics");
                    this._types = new long[]{};
                    this._sortFieldAndType = ParamUtil.getString(resourceRequest, "sortFieldAndType");
                }

                long delta = this._configuration.delta();

                // Recherche des vidéos
                List<AssetEntry> entries = searchEntries();

                // Récupération du json des entités
                JSONObject jsonResponse = JSONFactoryUtil.createJSONObject();
                JSONArray jsonEntries = JSONFactoryUtil.createJSONArray();
                for (AssetEntry entry : entries) {
                    String className = entry.getClassName();

                    switch (className) {
                        /*case "eu.strasbourg.service.activity.model.Activity":
                            Activity activity = ActivityLocalServiceUtil.fetchActivity(entry.getClassPK());
                            jsonEntries.put(activity.toJSON());
                            break;
                        case "eu.strasbourg.service.activity.model.ActivityCourse":
                            ActivityCourse activityCourse = ActivityCourseLocalServiceUtil.fetchActivityCourse(entry.getClassPK());
                            jsonEntries.put(activityCourse.toJSON());
                            break;
                        case "eu.strasbourg.service.activity.model.ActivityOrganizer":
                            ActivityOrganizer activityOrganizer = ActivityOrganizerLocalServiceUtil.fetchActivityOrganizer(entry.getClassPK());
                            jsonEntries.put(activityOrganizer.toJSON());
                            break;
                        case "eu.strasbourg.service.agenda.model.Campaign":
                            Campaign campaign = CampaignLocalServiceUtil.fetchCampaign(entry.getClassPK());
                            jsonEntries.put(campaign.toJSON());
                            break;*/
                        case "eu.strasbourg.service.agenda.model.Event":
                            Event event = EventLocalServiceUtil.fetchEvent(entry.getClassPK());
                            JSONObject jsonEvent = JSONFactoryUtil.createJSONObject();
                            jsonEvent.put("class", className);
                            JSONObject json = event.toJSON();
                            json.put("eventScheduleDisplay", event.getEventScheduleDisplay(Locale.FRANCE));
                            json.put("placeAlias", event.getPlaceAlias(Locale.FRANCE));
                            jsonEvent.put("json", json);
                            jsonEntries.put(jsonEvent);
                            break;
                        /*case "eu.strasbourg.service.agenda.model.Manifestation":
                            Manifestation manifestation = ManifestationLocalServiceUtil.fetchManifestation(entry.getClassPK());
                            jsonEntries.put(manifestation.toJSON());
                            break;
                        case "eu.strasbourg.service.artwork.model.Artwork":
                            Artwork artwork = ArtworkLocalServiceUtil.fetchArtwork(entry.getClassPK());
                            jsonEntries.put(artwork.toJSON());
                            break;
                        case "eu.strasbourg.service.artwork.model.ArtworkCollection":
                            ArtworkCollection artworkCollection = ArtworkCollectionLocalServiceUtil.fetchArtworkCollection(entry.getClassPK());
                            jsonEntries.put(artworkCollection.toJSON());
                            break;
                        case "eu.strasbourg.service.edition.model.Edition":
                            Edition edition = EditionLocalServiceUtil.fetchEdition(entry.getClassPK());
                            jsonEntries.put(edition.toJSON());
                            break;
                        case "eu.strasbourg.service.edition.model.EditionGallery":
                            EditionGallery editionGallery = EditionGalleryLocalServiceUtil.fetchEditionGallery(entry.getClassPK());
                            jsonEntries.put(editionGallery.toJSON());
                            break;
                        case "eu.strasbourg.service.interest.model.Interest":
                            Interest interest = InterestLocalServiceUtil.fetchInterest(entry.getClassPK());
                            jsonEntries.put(interest.toJSON());
                            break;
                        case "eu.strasbourg.service.link.model.Link":
                            Link link = LinkLocalServiceUtil.fetchLink(entry.getClassPK());
                            jsonEntries.put(link.toJSON());
                            break;
                        case "eu.strasbourg.service.official.model.Official":
                            Official official = OfficialLocalServiceUtil.fetchOfficial(entry.getClassPK());
                            jsonEntries.put(official.toJSON());
                            break;
                        case "eu.strasbourg.service.place.model.Place":
                            Place place = PlaceLocalServiceUtil.fetchPlace(entry.getClassPK());
                            jsonEntries.put(place.toJSON());
                            break;*/
                        case "eu.strasbourg.service.project.model.Project":
                            Project project = ProjectLocalServiceUtil.fetchProject(entry.getClassPK());
                            JSONObject jsonProject = JSONFactoryUtil.createJSONObject();
                            jsonProject.put("class", className);
                            json = project.toJSON();
                            json.put("nbParticipations", project.getParticipations().size());
                            json.put("nbEvents", project.getEvents().size());
                            JSONArray jsonThematicCategoriesTitle = JSONFactoryUtil.createJSONArray();
                            List<AssetCategory> thematicCategories = project.getThematicCategories();
                            for (AssetCategory assetCategory : thematicCategories) {
                                jsonThematicCategoriesTitle.put(JSONHelper.getJSONFromI18nMap(assetCategory.getTitleMap()));
                            }
                            json.put("jsonThematicCategoriesTitle", jsonThematicCategoriesTitle);
                            jsonProject.put("json", json);
                            jsonEntries.put(jsonProject);
                            break;
                        case "eu.strasbourg.service.project.model.Participation":
                            Participation participation = ParticipationLocalServiceUtil.fetchParticipation(entry.getClassPK());
                            JSONObject jsonParticipation = JSONFactoryUtil.createJSONObject();
                            jsonParticipation.put("class", className);
                            json = participation.toJSON();
                            json.put("todayExpirationDifferenceDays", participation.getTodayExpirationDifferenceDays());
                            json.put("isJudgeable", participation.isJudgeable());
                            json.put("groupId", participation.getGroupId());
                            HttpServletRequest request = PortalUtil.getHttpServletRequest(resourceRequest);
                            LiferayPortletRequest liferayPortletRequest = PortalUtil.getLiferayPortletRequest(resourceRequest);
                            HttpServletRequest originalRequest = liferayPortletRequest.getHttpServletRequest();
                            json.put("hasPactSigned", originalRequest.getSession().getAttribute("has_pact_signed"));
                            jsonThematicCategoriesTitle = JSONFactoryUtil.createJSONArray();
                            thematicCategories = participation.getThematicCategories();
                            for (AssetCategory assetCategory : thematicCategories) {
                                jsonThematicCategoriesTitle.put(JSONHelper.getJSONFromI18nMap(assetCategory.getTitleMap()));
                            }
                            json.put("jsonThematicCategoriesTitle", jsonThematicCategoriesTitle);
                            jsonParticipation.put("json", json);
                            jsonEntries.put(jsonParticipation);
                            break;
                        case "eu.strasbourg.service.project.model.Petition":
                            Petition petition = PetitionLocalServiceUtil.fetchPetition(entry.getClassPK());
                            JSONObject jsonPetition = JSONFactoryUtil.createJSONObject();
                            jsonPetition.put("class", className);
                            json = petition.toJSON();
                            jsonThematicCategoriesTitle = JSONFactoryUtil.createJSONArray();
                            thematicCategories = petition.getThematicCategories();
                            for (AssetCategory assetCategory : thematicCategories) {
                                jsonThematicCategoriesTitle.put(JSONHelper.getJSONFromI18nMap(assetCategory.getTitleMap()));
                            }
                            json.put("jsonThematicCategoriesTitle", jsonThematicCategoriesTitle);
                            json.put("jsonProjectCategoryTitle", JSONHelper.getJSONFromI18nMap(petition.getProjectCategory().getTitleMap()));
                            jsonPetition.put("json", json);
                            jsonEntries.put(jsonPetition);
                            break;
                        case "eu.strasbourg.service.video.model.Video":
                            Video video = VideoLocalServiceUtil.fetchVideo(entry.getClassPK());
                            JSONObject jsonVideo = JSONFactoryUtil.createJSONObject();
                            jsonVideo.put("class", className);
                            jsonVideo.put("json", video.toJSON());
                            jsonEntries.put(jsonVideo);
                            break;
                        /*case "eu.strasbourg.service.video.model.VideoGallery":
                            VideoGallery videoGallery = VideoGalleryLocalServiceUtil.fetchVideoGallery(entry.getClassPK());
                            jsonEntries.put(videoGallery.toJSON());
                            break;*/
                        case "com.liferay.journal.model.JournalArticle":
                            JournalArticle journalArticle = JournalArticleLocalServiceUtil.getLatestArticle(entry.getClassPK());
                            JSONObject jsonJournalArticle = JSONFactoryUtil.createJSONObject();
                            jsonJournalArticle.put("class", className);
                            json = JSONFactoryUtil.createJSONObject();
                            json.put("detailURL", LayoutHelper.getJournalArticleLayoutURL(journalArticle.getGroupId(), journalArticle.getArticleId(), this._themeDisplay));
                            String document = journalArticle.getContentByLocale(LocaleUtil.toLanguageId(Locale.FRANCE));
                            com.liferay.portal.kernel.xml.Document docXML = SAXReaderUtil.read(document);
                            String title = docXML.valueOf("//dynamic-element[@name='title']/dynamic-content/text()");
                            if(Validator.isNull(title)){
                                title = journalArticle.getTitle(Locale.FRANCE);
                            }
                            json.put("title", title);
                            String thumbnail = docXML.valueOf("//dynamic-element[@name='thumbnail']/dynamic-content/text()");
                            json.put("thumbnail", thumbnail);
                            JSONArray jsonVocabulariesTitle = JSONFactoryUtil.createJSONArray();
                            AssetEntry asset = AssetEntryLocalServiceUtil.getAssetEntry(entry.getEntryId());
                            List<AssetCategory> listVocabulary = AssetVocabularyHelper.getAssetEntryCategoriesByVocabulary(asset, "territoire");
                            for (AssetCategory assetCategory : listVocabulary) {
                                jsonVocabulariesTitle.put(JSONHelper.getJSONFromI18nMap(assetCategory.getTitleMap()));
                            }
                            json.put("jsonVocabulariesTitle", jsonVocabulariesTitle);
                            SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy");
                            json.put("modifiedDate", dateFormat.format(journalArticle.getModifiedDate()));
                            String chapo = docXML.valueOf("//dynamic-element[@name='chapo']/dynamic-content/text()");
                            json.put("chapo", chapo.replaceAll("<[^>]*>", "").substring(0,chapo.length() > 100 ? 100 : chapo.length()));
                            jsonJournalArticle.put("json", json);
                            jsonEntries.put(jsonJournalArticle);
                            break;
                        /*case "com.liferay.document.library.kernel.model.DLFileEntry":
                            DLFileEntry dlFileEntry = DLFileEntryLocalServiceUtil.fetchDLFileEntry(entry.getClassPK());
                            jsonEntries.put(dlFileEntry.toJSON());
                            break;*/
                    }
                }
                jsonResponse.put("entries", jsonEntries);

                // Recuperation de l'élément d'écriture de la réponse
                PrintWriter writer = resourceResponse.getWriter();
                writer.print(jsonResponse.toString());

            } else { // pour l'export PDF
                String exportType = this._configuration.exportType();
                ExportPDF.printPDFWithXMLWorker(resourceRequest, resourceResponse, exportType);
            }
        } catch (Exception e2) {
            _log.error(e2);
        }
        super.serveResource(resourceRequest, resourceResponse);
    }

    /**
     * Effectue concrètement la recherche
     */
    private List<AssetEntry> searchEntries() throws PortalException {
        HttpServletRequest servletRequest = PortalUtil.getHttpServletRequest(this._request);

        SearchContext searchContext = SearchContextFactory.getInstance(servletRequest);

        // Mots clés
        String keywords = this._keywords;

        // ClassNames de la configuration
        String[] classNames = ArrayUtil.toStringArray(getClassNames());

        // Inclusion ou non du scope global
        boolean globalScope = this._configuration.globalScope();
        long globalGroupId = this._themeDisplay.getCompanyGroupId();

        // Group ID courant
        long groupId = this._themeDisplay.getScopeGroupId();

        // Catégories sélectionnées par l'utilisateur
        List<Long[]> categoriesIds = this.getFilterCategoriesIds();

        // Préfiltre catégories
        String prefilterCategoriesIdsString = this._configuration.prefilterCategoriesIds();
        List<Long[]> prefilterCategoriesIds = new ArrayList<Long[]>();
        for (String prefilterCategoriesIdsGroupByVocabulary : prefilterCategoriesIdsString.split(";")) {
            Long[] prefilterCategoriesIdsForVocabulary = ArrayUtil
                    .toLongArray(StringUtil.split(prefilterCategoriesIdsGroupByVocabulary, ",", 0));
            prefilterCategoriesIds.add(prefilterCategoriesIdsForVocabulary);
        }

        // Préfiltre tags
        String prefilterTagsNamesString = this._configuration.prefilterTagsNames();
        String[] prefilterTagsNames = StringUtil.split(prefilterTagsNamesString);

        // Champ date
        boolean dateField = this._startDay == -1 ? false : this._configuration.dateField();
        String dateFieldName = this._configuration.defaultSortField();
        LocalDate fromDate = null;
        LocalDate toDate = null;
        if (dateField) {
            fromDate = LocalDate.of(this.getFromYear(), this.getFromMonthValue(), this.getFromDay());
            toDate = LocalDate.of(this.getToYear(), this.getToMonthValue(), this.getToDay());
        }

        // Ordre
        String sortField = this.getSortField();
        boolean isSortDesc = "desc".equals(this.getSortType());

        // Permet de remonter la hiérarchie des Request
        HttpServletRequest originalRequest = PortalUtil.getOriginalServletRequest(servletRequest);

        // Lieu (pour la recherche agenda)
        String idSIGPlace = ParamUtil.getString(originalRequest, "idSIGPlace");

        // Recherche
        this._hits = SearchHelper.getGlobalSearchHits(searchContext, classNames, groupId, globalGroupId, globalScope,
                keywords, dateField, dateFieldName, fromDate, toDate, categoriesIds, prefilterCategoriesIds,
                prefilterTagsNames, idSIGPlace, this._themeDisplay.getLocale(), -1,
                -1, sortField, isSortDesc);

        List<AssetEntry> results = new ArrayList<AssetEntry>();
        if (this._hits != null) {
            int i = 0;
            for (float s : this._hits.getScores()) {
                _log.info(GetterUtil.getString(this._hits.getDocs()[i].get(Field.TITLE)) + " : " + s);
                i++;
                if (i > 10)
                    break;
            }

            for (Document document : this._hits.getDocs()) {
                AssetEntry entry = AssetEntryLocalServiceUtil.fetchEntry(
                        GetterUtil.getString(document.get(Field.ENTRY_CLASS_NAME)),
                        GetterUtil.getLong(document.get(Field.ENTRY_CLASS_PK)));
                if (entry != null) {
                    results.add(entry);
                }
            }
            long count = SearchHelper.getGlobalSearchCount(searchContext, classNames, groupId, globalGroupId,
                    globalScope, keywords, dateField, dateFieldName, fromDate, toDate, categoriesIds,
                    prefilterCategoriesIds, prefilterTagsNames, idSIGPlace, this._themeDisplay.getLocale());
        }

        return results;
    }

    /**
     * Retourne la liste des class names sur lesquelles on recherche
     */
    public List<String> getClassNames() {
        List<String> classNames = new ArrayList<String>();
        for (String className : this._configuration.assetClassNames().split(",")) {
            if (Validator.isNotNull(className)) {
                classNames.add(className);
            }
        }
        if (this._configuration.searchJournalArticle()) {
            classNames.add("com.liferay.journal.model.JournalArticle");
        }
        if (this._configuration.searchDocument()) {
            classNames.add(DLFileEntry.class.getName());
        }

        return classNames;
    }

    /**
     * Renvoie la liste des catégories sur lesquelles on souhaite filtrer les
     * entries. L'opérateur entre chaque id de catégorie d'un array est un "OU", celui entre chaque liste d'array est un "ET"
     */
    private List<Long[]> getFilterCategoriesIds() {
        List<Long[]> filterCategoriesIds = new ArrayList<Long[]>();
        List<Long> categoriesIds = new ArrayList<Long>();

        // On récupère les états s'il y en a
        for (long state : this._states) {
            if (state > 0) {
                categoriesIds.add(state);
            }
        }
        if (categoriesIds.size() > 0) {
            filterCategoriesIds.add(ArrayUtil.toLongArray(categoriesIds.stream().mapToLong(l -> l).toArray()));
        }

        // On récupère les statuts s'il y en a
        for (long statut : this._statuts) {
            if (statut > 0) {
                categoriesIds.add(statut);
            }
        }
        if (categoriesIds.size() > 0) {
            filterCategoriesIds.add(ArrayUtil.toLongArray(categoriesIds.stream().mapToLong(l -> l).toArray()));
        }

        // On récupère les projets s'il y en a
        for (long project : this._projects) {
            if (project > 0) {
                categoriesIds.add(project);
            }
        }
        if (categoriesIds.size() > 0) {
            filterCategoriesIds.add(ArrayUtil.toLongArray(categoriesIds.stream().mapToLong(l -> l).toArray()));
        }

        // On récupère les quartiers s'il y en a
        categoriesIds = new ArrayList<Long>();
        for (long district : this._districts) {
            if (district > 0) {
                categoriesIds.add(district);
            }
        }
        if (categoriesIds.size() > 0) {
            filterCategoriesIds.add(ArrayUtil.toLongArray(categoriesIds.stream().mapToLong(l -> l).toArray()));
        }

        // On récupère les thématiques s'il y en a
        categoriesIds = new ArrayList<Long>();
        for (long thematic : this._thematics) {
            if (thematic > 0) {
                categoriesIds.add(thematic);
            }
        }
        if (categoriesIds.size() > 0) {
            filterCategoriesIds.add(ArrayUtil.toLongArray(categoriesIds.stream().mapToLong(l -> l).toArray()));
        }

        // On récupère les types s'il y en a
        for (long type : this._types) {
            if (type > 0) {
                categoriesIds.add(type);
            }
        }
        if (categoriesIds.size() > 0) {
            filterCategoriesIds.add(ArrayUtil.toLongArray(categoriesIds.stream().mapToLong(l -> l).toArray()));
        }

        return filterCategoriesIds;
    }

    /**
     * Retourne la jour du mois de la date de début de la recherche. Soit depuis
     * les paramètres de la requête soit le réglage par défaut via la
     * configuration (date du jour, ou si la période de recherche par défaut par
     * défaut est négative, X jour dans le passé)
     */
    public int getFromDay() {
        int fromParam = this._startDay;
        if (fromParam > 0) {
            return fromParam;
        } else {
            if (this._configuration.defaultDateRange() < 0) {
                return LocalDate.now().plusDays(this._configuration.defaultDateRange()).getDayOfMonth();
            } else {
                return LocalDate.now().getDayOfMonth();
            }
        }

    }

    /**
     * Retourne le mois de la date de début de la recherche depuis les
     * paramètres de la requête ou depuis la configuration, dans l'interval
     * [0;11]
     */
    public int getFromMonthIndex() {
        return getFromMonthValue() - 1;
    }

    /**
     * Retourne le mois de la date de début de la recherche depuis les
     * paramètres de la requête ou depuis la configuration, dans l'interval
     * [1;12]
     */
    public int getFromMonthValue() {
        String fromMonthString = this._startMonth;
        if (Validator.isNull(fromMonthString)) {
            if (this._configuration.defaultDateRange() < 0) {
                return LocalDate.now().plusDays(this._configuration.defaultDateRange()).getMonthValue();
            } else {
                return LocalDate.now().getMonthValue();
            }
        } else {
            return Integer.parseInt(fromMonthString) + 1;
        }
    }

    /**
     * Retourne l'année de la date de début de la recherche depuis les
     * paramètres de la requête ou depuis la configuration
     */
    public int getFromYear() {
        int fromParam = this._startYear;
        if (fromParam > 0) {
            return fromParam;
        } else {
            if (this._configuration.defaultDateRange() < 0) {
                return LocalDate.now().plusDays(this._configuration.defaultDateRange()).getYear();
            } else {
                return LocalDate.now().getYear();
            }
        }
    }

    /**
     * Retourne la jour du mois de la date de fin de la recherche. Soit depuis
     * les paramètres de la requête soit le réglage par défaut via la
     * configuration (date du jour + config, ou si la période de recherche par
     * défaut par défaut est négative, date du jour)
     */
    public int getToDay() {
        int toParam = this._endDay;
        if (toParam > 0) {
            return toParam;
        } else {
            if (this._configuration.defaultDateRange() > 0) {
                return LocalDate.now().plusDays(this._configuration.defaultDateRange()).getDayOfMonth();
            } else {
                return LocalDate.now().getDayOfMonth();
            }
        }
    }

    /**
     * Retourne le mois de la date de fin de la recherche depuis les paramètres
     * de la requête ou depuis la configuration, dans l'interval [0;11]
     */
    public int getToMonthIndex() {
        return getToMonthValue() - 1;
    }

    /**
     * Retourne le mois de la date de fin de la recherche depuis les paramètres
     * de la requête ou depuis la configuration, dans l'interval [1;12]
     */
    public int getToMonthValue() {
        String toMonthString = this._endMonth;
        if (Validator.isNull(toMonthString)) {
            if (this._configuration.defaultDateRange() > 0) {
                return LocalDate.now().plusDays(this._configuration.defaultDateRange()).getMonthValue();
            } else {
                return LocalDate.now().getMonthValue();
            }
        } else {
            return Integer.parseInt(toMonthString) + 1;
        }
    }

    /**
     * Retourne l'année de la date de fin de la recherche depuis les paramètres
     * de la requête ou depuis la configuration
     */
    public int getToYear() {
        int toParam = this._endYear;
        if (toParam > 0) {
            return toParam;
        } else {
            if (this._configuration.defaultDateRange() > 0) {
                return LocalDate.now().plusDays(this._configuration.defaultDateRange()).getYear();
            } else {
                return LocalDate.now().getYear();
            }
        }
    }

    /**
     * Retourne le champ sur lequel on classe les résultats
     */
    public String getSortField() {
        String sortFieldFromParam = this._sortFieldAndType;
        if (Validator.isNull(sortFieldFromParam)) {
            if (Validator.isNull(this._keywords)) {
                return Validator.isNotNull(this._configuration.defaultSortField())
                        ? this._configuration.defaultSortField() : "modified_sortable";
            } else {
                return "score";
            }
        } else {
            return sortFieldFromParam.split(",")[0];
        }
    }

    /**
     * Retourne le type de classement des résultats (croissant ou décroissant)
     */
    public String getSortType() {
        if (this.getSortField() == "score") {
            return "desc";
        } else {
            String sortTypeFromParam = this._sortFieldAndType;
            if (Validator.isNull(sortTypeFromParam)) {
                return Validator.isNotNull(this._configuration.defaultSortType())
                        ? this._configuration.defaultSortType() : "desc";
            } else {
                return sortTypeFromParam.split(",")[1];
            }
        }
    }

    private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());

    private ThemeDisplay _themeDisplay;
    private ResourceRequest _request;
    private ResourceResponse _response;
    private SearchAssetConfiguration _configuration;

    private String _keywords;
    private int _startDay;
    private String _startMonth;
    private int _startYear;
    private int _endDay;
    private String _endMonth;
    private int _endYear;
    private long[] _states;
    private long[] _statuts;
    private long[] _projects;
    private long[] _districts;
    private long[] _thematics;
    private long[] _types;
    private String _sortFieldAndType;

    private Hits _hits;
}